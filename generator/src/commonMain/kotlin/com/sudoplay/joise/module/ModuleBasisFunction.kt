package com.sudoplay.joise.module

import com.sudoplay.joise.generator.LCG
import com.sudoplay.joise.noise.Interpolator
import com.sudoplay.joise.noise.Noise
import kotlin.jvm.JvmOverloads
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class ModuleBasisFunction @JvmOverloads constructor(
    type: BasisType = BasisType.GRADIENT,
    interpolationType: InterpolationType = InterpolationType.QUINTIC,
    seed: Long = 10000L
) : SeedableModule() {

    var scale = DoubleArray(4)
    var offset = DoubleArray(4)
    var rotMatrix = Array(3) { DoubleArray(3) }
    var cos2d = 0.0
    var sin2d: Double = 0.0
    var func2D: Noise.Function2D? = null
    var func3D: Noise.Function3D? = null
    var func4D: Noise.Function4D? = null
    var func6D: Noise.Function6D? = null
    var interpolator: Interpolator? = null

    override var seed: Long
        get() = super.seed
        set(value) {
            super.seed = value
            val lcg = LCG()
            lcg.setSeed(value)
            var ax: Double
            var ay: Double
            var az: Double
            val len: Double
            ax = lcg.get01()
            ay = lcg.get01()
            az = lcg.get01()
            len = sqrt(ax * ax + ay * ay + az * az)
            ax /= len
            ay /= len
            az /= len
            setRotationAngle(ax, ay, az, lcg.get01() * 3.141592 * 2.0)
            val angle = lcg.get01() * 3.141592 * 2.0
            cos2d = cos(angle)
            sin2d = sin(angle)
        }

    private lateinit var _interpolationType: InterpolationType
    var interpolationType: InterpolationType
        get() = _interpolationType
        set(value) {
            _interpolationType = value
            interpolator = when (value) {
                InterpolationType.CUBIC -> Interpolator.HERMITE
                InterpolationType.LINEAR -> Interpolator.LINEAR
                InterpolationType.NONE -> Interpolator.NONE
                else -> Interpolator.QUINTIC
            }
        }

    private lateinit var _basisType: BasisType
    var basisType: BasisType
        get() = _basisType
        set(value) {
            _basisType = value
            when (value) {
                BasisType.GRADVAL -> {
                    func2D = Noise.Function2D.GRADVAL
                    func3D = Noise.Function3D.GRADVAL
                    func4D = Noise.Function4D.GRADVAL
                    func6D = Noise.Function6D.GRADVAL
                }
                BasisType.SIMPLEX -> {
                    func2D = Noise.Function2D.SIMPLEX
                    func3D = Noise.Function3D.SIMPLEX
                    func4D = Noise.Function4D.SIMPLEX
                    func6D = Noise.Function6D.SIMPLEX
                }
                BasisType.VALUE -> {
                    func2D = Noise.Function2D.VALUE
                    func3D = Noise.Function3D.VALUE
                    func4D = Noise.Function4D.VALUE
                    func6D = Noise.Function6D.VALUE
                }
                BasisType.WHITE -> {
                    func2D = Noise.Function2D.WHITE
                    func3D = Noise.Function3D.WHITE
                    func4D = Noise.Function4D.WHITE
                    func6D = Noise.Function6D.WHITE
                }
                BasisType.GRADIENT -> {
                    func2D = Noise.Function2D.GRADIENT
                    func3D = Noise.Function3D.GRADIENT
                    func4D = Noise.Function4D.GRADIENT
                    func6D = Noise.Function6D.GRADIENT
                }
            }
            setMagicNumbers(value)
        }

    init {
        basisType = type
        this.interpolationType = interpolationType
        this.seed = seed
    }

    /**
     * Set the rotation axis and angle to use for 3D, 4D and 6D noise.
     *
     * @param x
     * @param y
     * @param z
     * @param angle
     */
    fun setRotationAngle(x: Double, y: Double, z: Double, angle: Double) {
        val sin: Double = sin(angle)
        val cos: Double = cos(angle)
        rotMatrix[0][0] = 1 + (1 - cos) * (x * x - 1)
        rotMatrix[1][0] = -z * sin + (1 - cos) * x * y
        rotMatrix[2][0] = y * sin + (1 - cos) * x * z
        rotMatrix[0][1] = z * sin + (1 - cos) * x * y
        rotMatrix[1][1] = 1 + (1 - cos) * (y * y - 1)
        rotMatrix[2][1] = -x * sin + (1 - cos) * y * z
        rotMatrix[0][2] = -y * sin + (1 - cos) * x * z
        rotMatrix[1][2] = x * sin + (1 - cos) * y * z
        rotMatrix[2][2] = 1 + (1 - cos) * (z * z - 1)
    }

    override fun get(x: Double, y: Double): Double {
        val nx: Double = x * cos2d - y * sin2d
        val ny: Double = y * cos2d + x * sin2d
        return func2D!![nx, ny, super.seed, interpolator!!]
    }

    override fun get(x: Double, y: Double, z: Double): Double {
        val nx: Double = rotMatrix[0][0] * x + rotMatrix[1][0] * y + rotMatrix[2][0] * z
        val ny: Double = rotMatrix[0][1] * x + rotMatrix[1][1] * y + rotMatrix[2][1] * z
        val nz: Double = rotMatrix[0][2] * x + rotMatrix[1][2] * y + rotMatrix[2][2] * z
        return func3D!![nx, ny, nz, super.seed, interpolator!!]
    }

    override fun get(x: Double, y: Double, z: Double, w: Double): Double {
        val nx: Double = rotMatrix[0][0] * x + rotMatrix[1][0] * y + rotMatrix[2][0] * z
        val ny: Double = rotMatrix[0][1] * x + rotMatrix[1][1] * y + rotMatrix[2][1] * z
        val nz: Double = rotMatrix[0][2] * x + rotMatrix[1][2] * y + rotMatrix[2][2] * z
        return func4D!![nx, ny, nz, w, super.seed, interpolator!!]
    }

    override fun get(
        x: Double,
        y: Double,
        z: Double,
        w: Double,
        u: Double,
        v: Double
    ): Double {
        val nx: Double = rotMatrix[0][0] * x + rotMatrix[1][0] * y + rotMatrix[2][0] * z
        val ny: Double = rotMatrix[0][1] * x + rotMatrix[1][1] * y + rotMatrix[2][1] * z
        val nz: Double = rotMatrix[0][2] * x + rotMatrix[1][2] * y + rotMatrix[2][2] * z
        return func6D!![nx, ny, nz, w, u, v, super.seed, interpolator!!]
    }

    fun setMagicNumbers(type: BasisType?) {
        when (type) {
            BasisType.VALUE -> {
                scale[0] = 1.0
                offset[0] = 0.0
                scale[1] = 1.0
                offset[1] = 0.0
                scale[2] = 1.0
                offset[2] = 0.0
                scale[3] = 1.0
                offset[3] = 0.0
            }
            BasisType.GRADIENT -> {
                scale[0] = 1.86848
                offset[0] = -0.000118
                scale[1] = 1.85148
                offset[1] = -0.008272
                scale[2] = 1.64127
                offset[2] = -0.01527
                scale[3] = 1.92517
                offset[3] = 0.03393
            }
            BasisType.GRADVAL -> {
                scale[0] = 0.6769
                offset[0] = -0.00151
                scale[1] = 0.6957
                offset[1] = -0.133
                scale[2] = 0.74622
                offset[2] = 0.01916
                scale[3] = 0.7961
                offset[3] = -0.0352
            }
            BasisType.WHITE -> {
                scale[0] = 1.0
                offset[0] = 0.0
                scale[1] = 1.0
                offset[1] = 0.0
                scale[2] = 1.0
                offset[2] = 0.0
                scale[3] = 1.0
                offset[3] = 0.0
            }
            else -> {
                scale[0] = 1.0
                offset[0] = 0.0
                scale[1] = 1.0
                offset[1] = 0.0
                scale[2] = 1.0
                offset[2] = 0.0
                scale[3] = 1.0
                offset[3] = 0.0
            }
        }
    }

    enum class BasisType {
        VALUE, GRADIENT, GRADVAL, SIMPLEX, WHITE
    }

    enum class InterpolationType {
        NONE, LINEAR, CUBIC, QUINTIC
    }

}
