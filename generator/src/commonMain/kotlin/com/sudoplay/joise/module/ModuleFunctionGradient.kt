package com.sudoplay.joise.module

class ModuleFunctionGradient : SourcedModule() {

    var axis: FunctionGradientAxis = FunctionGradientAxis.X_AXIS
    private var _spacing = DEFAULT_SPACING

    override var spacing: Double
        get() = _spacing
        set(value) {
            _spacing = value
            invSpacing = 1.0 / value
        }

    var invSpacing = 0.0

    override fun get(x: Double, y: Double): Double = when (axis) {
        FunctionGradientAxis.X_AXIS -> (super.source[x - spacing, y] - super.source[x + spacing, y]) * invSpacing
        FunctionGradientAxis.Y_AXIS -> (super.source[x, y - spacing] - super.source[x, y + spacing]) * invSpacing
        FunctionGradientAxis.Z_AXIS -> 0.0
        FunctionGradientAxis.W_AXIS -> 0.0
        FunctionGradientAxis.U_AXIS -> 0.0
        FunctionGradientAxis.V_AXIS -> 0.0
    }

    override fun get(x: Double, y: Double, z: Double): Double = when (axis) {
        FunctionGradientAxis.X_AXIS -> (super.source[x - spacing, y, z] - super.source[x + spacing, y, z]) * invSpacing
        FunctionGradientAxis.Y_AXIS -> (super.source[x, y - spacing, z] - super.source[x, y + spacing, z]) * invSpacing
        FunctionGradientAxis.Z_AXIS -> (super.source[x, y, z - spacing] - super.source[x, y, z + spacing]) * invSpacing
        FunctionGradientAxis.W_AXIS -> 0.0
        FunctionGradientAxis.U_AXIS -> 0.0
        FunctionGradientAxis.V_AXIS -> 0.0
    }

    override fun get(x: Double, y: Double, z: Double, w: Double): Double = when (axis) {
        FunctionGradientAxis.X_AXIS ->
            (super.source[x - spacing, y, z, w] - super.source[x + spacing, y, z, w]) * invSpacing
        FunctionGradientAxis.Y_AXIS ->
            (super.source[x, y - spacing, z, w] - super.source[x, y + spacing, z, w]) * invSpacing
        FunctionGradientAxis.Z_AXIS ->
            (super.source[x, y, z - spacing, w] - super.source[x, y, z + spacing, w]) * invSpacing
        FunctionGradientAxis.W_AXIS ->
            (super.source[x, y, z, w - spacing] - super.source[x, y, z, w + spacing]) * invSpacing
        FunctionGradientAxis.U_AXIS -> 0.0
        FunctionGradientAxis.V_AXIS -> 0.0
    }

    override fun get(
        x: Double,
        y: Double,
        z: Double,
        w: Double,
        u: Double,
        v: Double
    ): Double = when (axis) {
        FunctionGradientAxis.X_AXIS ->
            (super.source[x - spacing, y, z, w, u, v] - super.source[x + spacing, y, z, w, u, v]) * invSpacing
        FunctionGradientAxis.Y_AXIS ->
            (super.source[x, y - spacing, z, w, u, v] - super.source[x, y + spacing, z, w, u, v]) * invSpacing
        FunctionGradientAxis.Z_AXIS ->
            (super.source[x, y, z - spacing, w, u, v] - super.source[x, y, z + spacing, w, u, v]) * invSpacing
        FunctionGradientAxis.W_AXIS ->
            (super.source[x, y, z, w - spacing, u, v] - super.source[x, y, z, w + spacing, u, v]) * invSpacing
        FunctionGradientAxis.U_AXIS ->
            (super.source[x, y, z, w, u - spacing, v] - super.source[x, y, z, w, u + spacing, v]) * invSpacing
        FunctionGradientAxis.V_AXIS ->
            (super.source[x, y, z, w, u, v - spacing] - super.source[x, y, z, w, u, v + spacing]) * invSpacing
    }

    enum class FunctionGradientAxis {
        X_AXIS, Y_AXIS, Z_AXIS, W_AXIS, U_AXIS, V_AXIS
    }

    companion object {
        private const val DEFAULT_SPACING = 0.01
    }

}
