import com.sudoplay.joise.module.Module
import com.sudoplay.joise.module.ModuleAutoCorrect
import com.sudoplay.joise.module.ModuleBasisFunction
import com.sudoplay.joise.module.ModuleFractal
import org.w3c.dom.HTMLCanvasElement
import kotlin.browser.document
import kotlin.browser.window
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.sin
import kotlin.random.Random

fun main() {
    println("main")
    window.onload = { configureCanvas() }
    println("hello")
}

private val module: Module
    get() {
        val gen = ModuleFractal()
        gen.setAllSourceBasisTypes(ModuleBasisFunction.BasisType.SIMPLEX)
        gen.setAllSourceInterpolationTypes(ModuleBasisFunction.InterpolationType.QUINTIC)
        gen.numOctaves = 6
        gen.frequency = 1.25
        gen.type = ModuleFractal.FractalType.MULTI
        gen.seed = 898458

        val ac = ModuleAutoCorrect()
        ac.setSource(gen)
        ac.setRange(0.0, 1.0)
        ac.samples = 100
        ac.calculate()

        return ac
    }

private val drawingFunction: DrawingFunction = FourDimensionalDrawingFunction

private fun configureCanvas() {
    println("Document loaded")
    val canvas = document.getElementById("canvas") as HTMLCanvasElement

    fun fullScreenCanvas() {
        println("Resize")
        canvas.width = window.innerWidth
        canvas.height = window.innerHeight
    }
//    window.onload = { fullScreenCanvas() }
//    window.onresize = { fullScreenCanvas() }

    draw(canvas)
}

private fun draw(canvas: HTMLCanvasElement) {
    println("draw start")
    val width = 16
    val height = 16

    val context = canvas.getContext("2d").asDynamic()
    val imageData = context.createImageData(width, height)

    val map = FloatArray(width * height)
    for (x in 0 until width) {
        for (y in 0 until height) {
            map[x * width + y] = drawingFunction.calculateHeight(
                module = module,
                width = width,
                height = height,
                x = x,
                y = y
            )
        }
    }

    for (x in 0 until width) {
        for (y in 0 until height) {
            val heightValue = map[x * width + y]
            val pixelOffset = (y * width + x) * 4;
            val color = drawingFunction.color(heightValue)
            imageData.data[pixelOffset] = (color.r * 255).toInt()
            imageData.data[pixelOffset + 1] = (color.g * 255).toInt()
            imageData.data[pixelOffset + 2] = (color.b * 255).toInt()
            imageData.data[pixelOffset + 3] = (color.a * 255).toInt()
        }
    }

    context.putImageData(imageData, 0, 0)
    println("draw end")
}

private interface DrawingFunction {
    val name: String
    fun calculateHeight(module: Module, width: Int, height: Int, x: Int, y: Int): Float
    fun color(heightValue: Float): Color
}

/**
 * World wraps on the horizontal and vertical axis
 */
private object FourDimensionalDrawingFunction : DrawingFunction {

    private const val PI_F = PI.toFloat()
    private val terrainFeatures = Terrain.values()

    override val name: String = "4D Height Map"

    var min: Float = 0f
        private set

    var max: Float = 0f
        private set

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

        val heightValue = module[px, py, pz, pw].toFloat()
        if (heightValue > max) max = heightValue
        else if (heightValue < min) min = heightValue
        return heightValue
    }

    override fun color(heightValue: Float): Color {
        val normalizedHeightValue = (heightValue - min) / (max - min)
        return terrainFeatures
            .first { terrain -> normalizedHeightValue <= terrain.minDepth }
            .color
    }

}

private data class Color(
    val r: Float,
    val g: Float,
    val b: Float,
    val a: Float = 1f
)

@Suppress("MagicNumber")
private enum class Terrain(
    val minDepth: Float,
    val color: Color
) {

    DeepWater(0.2f, Color(0f, 0f, 0.5f)),
    ShallowWater(0.4f, Color(25f / 255f, 25f / 255f, 150f / 255f)),
    Sand(0.5f, Color(240f / 255f, 240f / 255f, 64f / 255f)),
    Grass(0.7f, Color(50f / 255f, 220f / 255f, 20f / 255f)),
    Forest(0.8f, Color(16f / 255f, 160f / 255f, 0f)),
    Rock(0.9f, Color(0.5f, 0.5f, 0.5f)),
    Snow(1f, Color(1f, 1f, 1f)),

}
