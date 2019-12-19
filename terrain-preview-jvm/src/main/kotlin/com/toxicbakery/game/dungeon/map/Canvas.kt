package com.toxicbakery.game.dungeon.map

import com.sudoplay.joise.module.Module
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import javax.swing.JPanel
import kotlin.math.max
import kotlin.math.min

class Canvas internal constructor(
    width: Int,
    height: Int,
    private val heightFunc: HeightFunction = DefaultHeightFunction,
    private val colorFunc: ColorFunction = DefaultColorFunction
) : JPanel() {

    private val image: BufferedImage = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)

    fun updateImage(mod: Module) {
        heightFunc.reset()
        val width = image.width
        val height = image.height
        val map = FloatArray(width * height)
        for (x in 0 until width) {
            for (y in 0 until height) {
                map[x * width + y] = heightFunc.height(
                    module = mod,
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
                image.setRGB(x, y, colorFunc.color(heightValue))
            }
        }
        repaint()
    }

    public override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        val g2 = g as Graphics2D
        g2.drawImage(image, null, null)
        g2.dispose()
    }

    interface HeightFunction {
        fun reset()
        fun height(
            module: Module,
            width: Int,
            height: Int,
            x: Int,
            y: Int
        ): Float
    }

    interface ColorFunction {
        fun color(heightValue: Float): Int
    }

    private object DefaultHeightFunction : HeightFunction {
        override fun reset() = Unit
        override fun height(module: Module, width: Int, height: Int, x: Int, y: Int): Float {
            val px = x / width.toFloat() * SCALE
            val py = y / height.toFloat() * SCALE
            return max(0f, min(1f, module[px.toDouble(), py.toDouble()].toFloat()))
        }
    }

    private object DefaultColorFunction : ColorFunction {
        override fun color(heightValue: Float): Int = Color(heightValue, heightValue, heightValue).rgb
    }

    companion object {
        private const val SCALE = 1.0f
    }

}
