@file:Suppress("MagicNumber", "SameParameterValue")

package com.toxicbakery.game.dungeon.map

import com.sudoplay.joise.module.Module
import com.sudoplay.joise.module.ModuleAutoCorrect
import com.sudoplay.joise.module.ModuleBasisFunction.BasisType
import com.sudoplay.joise.module.ModuleBasisFunction.InterpolationType
import com.sudoplay.joise.module.ModuleFractal
import com.sudoplay.joise.module.ModuleFractal.FractalType
import java.awt.Color
import javax.swing.JFrame
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

private val frameConfigs = listOf<Pair<TerrainHeightFunction, Canvas.ColorFunction>>(
    Pair(TwoDimensionalWorldHeightFunction, TerrainColorFunction(TwoDimensionalWorldHeightFunction)),
    Pair(ThreeDimensionalHeightFunction, TerrainColorFunction(ThreeDimensionalHeightFunction)),
    Pair(FourDimensionalHeightFunction, TerrainColorFunction(FourDimensionalHeightFunction))
)

private val module: Module
    get() {
        val gen = ModuleFractal()
        gen.setAllSourceBasisTypes(BasisType.SIMPLEX)
        gen.setAllSourceInterpolationTypes(InterpolationType.QUINTIC)
        gen.numOctaves = 6
        gen.frequency = 1.25
        gen.type = FractalType.MULTI
        gen.seed = 898458

        val ac = ModuleAutoCorrect()
        ac.setSource(gen)
        ac.setRange(0.0, 1.0)
        ac.samples = 10000
        ac.calculate()

        return ac
    }

fun main() {
    val width = 768
    val height = 768
    val source = module
    frameConfigs.forEach { (heightFunction, colorFunction) ->
            createFrame(
                width = width,
                height = height,
                name = heightFunction.name,
                module = source,
                heightFunction = heightFunction,
                colorFunction = colorFunction
            )
        }
}


private fun createFrame(
    width: Int,
    height: Int,
    name: String,
    module: Module,
    heightFunction: Canvas.HeightFunction,
    colorFunction: Canvas.ColorFunction
) {
    val canvas = Canvas(
        width = width,
        height = height,
        heightFunc = heightFunction,
        colorFunc = colorFunction
    )
    canvas.updateImage(module)

    val frame = JFrame(name)
    frame.setSize(width, height)
    frame.add(canvas)
    frame.isVisible = true
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.setLocationRelativeTo(null)
}


/**
 * World does not wrap
 */
private object TwoDimensionalWorldHeightFunction : TerrainHeightFunction() {

    override val name: String = "2D Height Map"

    override fun calculateHeight(module: Module, width: Int, height: Int, x: Int, y: Int): Float {
        //Sample noise at smaller intervals
        val px = x / width.toDouble()
        val py = y / height.toDouble()
        return module[px, py].toFloat()
    }

}

/**
 * World wraps on the horizontal axis
 */
private object ThreeDimensionalHeightFunction : TerrainHeightFunction() {

    override val name: String = "3D Height Map"

    private const val PI_F = PI.toFloat()

    override fun calculateHeight(module: Module, width: Int, height: Int, x: Int, y: Int): Float {
        //Noise range
        //Noise range
        val x1 = 0f
        val x2 = 1f
        val y1 = 0f
        val y2 = 1f
        val dx = x2 - x1
        val dy = y2 - y1

        //Sample noise at smaller intervals
        val s = x / width.toFloat()
        val t = y / height.toFloat()

        // Calculate 3D coordinates
        val px = (x1 + cos(s * 2 * PI_F) * dx / (2 * PI_F)).toDouble()
        val py = (x1 + sin(s * 2 * PI_F) * dy / (2 * PI_F)).toDouble()
        val pz = t.toDouble()

        return module[px, py, pz].toFloat()
    }

}

/**
 * World wraps on the horizontal and vertical axis
 */
private object FourDimensionalHeightFunction : TerrainHeightFunction() {

    override val name: String = "4D Height Map"

    private const val PI_F = PI.toFloat()

    override fun calculateHeight(module: Module, width: Int, height: Int, x: Int, y: Int): Float {
        //Noise range
        //Noise range
        val x1 = 0f
        val x2 = 2f
        val y1 = 0f
        val y2 = 2f
        val dx = x2 - x1
        val dy = y2 - y1

        //Sample noise at smaller intervals
        val s = x / width.toFloat()
        val t = y / height.toFloat()

        // Calculate 4D coordinates
        val px = (x1 + cos(s * 2 * PI_F) * dx / (2 * PI_F)).toDouble()
        val py = (y1 + cos(t * 2 * PI_F) * dy / (2 * PI_F)).toDouble()
        val pz = (x1 + sin(s * 2 * PI_F) * dx / (2 * PI_F)).toDouble()
        val pw = (y1 + sin(t * 2 * PI_F) * dy / (2 * PI_F)).toDouble()

        return module[px, py, pz, pw].toFloat()
    }

}

private abstract class TerrainHeightFunction : Canvas.HeightFunction {

    abstract val name: String

    var min: Float = 0f
        private set

    var max: Float = 0f
        private set

    abstract fun calculateHeight(module: Module, width: Int, height: Int, x: Int, y: Int): Float

    override fun reset() {
        min = 0f
        max = 0f
    }

    override fun height(module: Module, width: Int, height: Int, x: Int, y: Int): Float {
        val heightValue = calculateHeight(module, width, height, x, y)
        if (heightValue > max) max = heightValue
        else if (heightValue < min) min = heightValue
        return heightValue
    }

}

private class TerrainColorFunction(
    private val thf: TerrainHeightFunction
) : Canvas.ColorFunction {

    private val terrainFeatures = Terrain.values()

    override fun color(heightValue: Float): Int {
        val normalizedHeightValue = (heightValue - thf.min) / (thf.max - thf.min)
        return terrainFeatures
            .first { terrain -> normalizedHeightValue <= terrain.minDepth }
            .color
    }

}

@Suppress("MagicNumber")
private enum class Terrain(
    val minDepth: Float,
    val color: Int
) {

    DeepWater(0.2f, Color(0f, 0f, 0.5f).rgb),
    ShallowWater(0.4f, Color(25f / 255f, 25f / 255f, 150f / 255f).rgb),
    Sand(0.5f, Color(240f / 255f, 240f / 255f, 64f / 255f).rgb),
    Grass(0.7f, Color(50f / 255f, 220f / 255f, 20f / 255f).rgb),
    Forest(0.8f, Color(16f / 255f, 160f / 255f, 0f).rgb),
    Rock(0.9f, Color(0.5f, 0.5f, 0.5f).rgb),
    Snow(1f, Color(1f, 1f, 1f).rgb),

}
