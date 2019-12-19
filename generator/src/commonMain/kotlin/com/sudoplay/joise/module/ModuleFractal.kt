package com.sudoplay.joise.module

import com.sudoplay.joise.module.ModuleBasisFunction.BasisType
import com.sudoplay.joise.module.ModuleBasisFunction.InterpolationType
import com.sudoplay.joise.noise.Util.clamp
import kotlin.jvm.JvmField
import kotlin.math.abs
import kotlin.math.pow

class ModuleFractal(
    type: FractalType = FractalType.FBM,
    basisType: BasisType = BasisType.GRADVAL,
    interpolationType: InterpolationType = InterpolationType.QUINTIC
) : SeedableModule() {

    var basis = arrayOfNulls<ModuleBasisFunction>(MAX_SOURCES)
    var source = arrayOfNulls<Module>(MAX_SOURCES)
    var exparray = DoubleArray(MAX_SOURCES)
    var correct = Array(MAX_SOURCES) { DoubleArray(2) }
    var offset = 0.0
    var gain: Double = 0.0
    var h: Double = 0.0
    var frequency = 1.0
    var lacunarity: Double = 2.0

    private var _numOctaves = 2
    var numOctaves: Int
        get() = _numOctaves
        set(value) {
            if (value > MAX_SOURCES) throw IllegalArgumentException("number of octaves must be <= $MAX_SOURCES")
            _numOctaves = value
        }

    private var _type: FractalType = FractalType.FBM
    var type: FractalType
        get() = _type
        set(value) {
            _type = value
            when (value) {
                FractalType.BILLOW -> {
                    h = 1.0
                    gain = 0.5
                    offset = 0.0
                }
                FractalType.DECARPENTIERSWISS -> {
                    h = 0.9
                    gain = 1.0
                    offset = 0.7
                }
                FractalType.FBM -> {
                    h = 1.0
                    gain = 0.5
                    offset = 0.0
                }
                FractalType.HYBRIDMULTI -> {
                    h = 0.25
                    gain = 1.0
                    offset = 0.7
                }
                FractalType.MULTI -> {
                    h = 1.0
                    gain = 0.0
                    offset = 0.0
                }
                FractalType.RIDGEMULTI -> {
                    h = 0.9
                    gain = 0.5
                    offset = 1.0
                }
            }
            calcWeights(value)
        }

    init {
        for (i in 0 until MAX_SOURCES) {
            basis[i] = ModuleBasisFunction()
        }
        setAllSourceTypes(basisType, interpolationType)
        this.type = type
        resetAllSources()
    }

    fun setAllSourceTypes(
        basisType: BasisType?,
        interpolationType: InterpolationType?
    ) {
        for (i in 0 until MAX_SOURCES) {
            basis[i]!!.basisType = basisType!!
            basis[i]!!.interpolationType = interpolationType!!
        }
    }

    fun setAllSourceBasisTypes(basisType: BasisType?) {
        for (i in 0 until MAX_SOURCES) {
            basis[i]!!.basisType = basisType!!
        }
    }

    fun setAllSourceInterpolationTypes(interpolationType: InterpolationType?) {
        for (i in 0 until MAX_SOURCES) {
            basis[i]!!.interpolationType = interpolationType!!
        }
    }

    fun setSourceType(
        index: Int, basisType: BasisType?,
        interpolationType: InterpolationType?
    ) {
        assertMaxSources(index)
        basis[index]!!.basisType = basisType!!
        basis[index]!!.interpolationType = interpolationType!!
    }

    fun overrideSource(index: Int, source: Module?) {
        if (index < 0 || index >= MAX_SOURCES) {
            throw IllegalArgumentException("expecting index < $MAX_SOURCES but was $index")
        }
        this.source[index] = source
    }

    fun resetSource(index: Int) {
        assertMaxSources(index)
        source[index] = basis[index]
    }

    fun resetAllSources() {
        for (i in 0 until MAX_SOURCES) {
            source[i] = basis[i]
        }
    }

    override var seed: Long
        get() = super.seed
        set(value) {
            super.seed = value
            for (i in 0 until MAX_SOURCES) {
                if (source[i] is SeedableModule) {
                    (source[i] as SeedableModule).seed = value
                }
            }
        }

    fun getBasis(index: Int): ModuleBasisFunction? {
        assertMaxSources(index)
        return basis[index]
    }

    override fun get(x: Double, y: Double): Double {
        return when (type) {
            FractalType.BILLOW -> getBillow(x, y)
            FractalType.DECARPENTIERSWISS -> getDeCarpentierSwiss(x, y)
            FractalType.FBM -> getFBM(x, y)
            FractalType.HYBRIDMULTI -> getHybridMulti(x, y)
            FractalType.MULTI -> getMulti(x, y)
            FractalType.RIDGEMULTI -> getRidgedMulti(x, y)
        }
    }

    override fun get(x: Double, y: Double, z: Double): Double {
        return when (type) {
            FractalType.BILLOW -> getBillow(x, y, z)
            FractalType.DECARPENTIERSWISS -> getDeCarpentierSwiss(
                x,
                y,
                z
            )
            FractalType.FBM -> getFBM(x, y, z)
            FractalType.HYBRIDMULTI -> getHybridMulti(x, y, z)
            FractalType.MULTI -> getMulti(x, y, z)
            FractalType.RIDGEMULTI -> getRidgedMulti(x, y, z)
        }
    }

    override fun get(x: Double, y: Double, z: Double, w: Double): Double {
        return when (type) {
            FractalType.BILLOW -> getBillow(x, y, z, w)
            FractalType.DECARPENTIERSWISS -> getDeCarpentierSwiss(
                x,
                y,
                z,
                w
            )
            FractalType.FBM -> getFBM(x, y, z, w)
            FractalType.HYBRIDMULTI -> getHybridMulti(x, y, z, w)
            FractalType.MULTI -> getMulti(x, y, z, w)
            FractalType.RIDGEMULTI -> getRidgedMulti(x, y, z, w)
        }
    }

    override fun get(
        x: Double,
        y: Double,
        z: Double,
        w: Double,
        u: Double,
        v: Double
    ): Double {
        return when (type) {
            FractalType.BILLOW -> getBillow(x, y, z, w, u, v)
            FractalType.DECARPENTIERSWISS -> getDeCarpentierSwiss(
                x,
                y,
                z,
                w,
                u,
                v
            )
            FractalType.FBM -> getFBM(x, y, z, w, u, v)
            FractalType.HYBRIDMULTI -> getHybridMulti(x, y, z, w, u, v)
            FractalType.MULTI -> getMulti(x, y, z, w, u, v)
            FractalType.RIDGEMULTI -> getRidgedMulti(x, y, z, w, u, v)
        }
    }

    fun getFBM(x: Double, y: Double): Double {
        var x1 = x
        var y1 = y
        var sum = 0.0
        var amp = 1.0
        x1 *= frequency
        y1 *= frequency
        for (i in 0 until numOctaves) {
            val n = source[i]!![x1, y1]
            sum += n * amp
            amp *= gain
            x1 *= lacunarity
            y1 *= lacunarity
        }
        return sum
    }

    fun getFBM(x: Double, y: Double, z: Double): Double {
        var x1 = x
        var y1 = y
        var z1 = z
        var sum = 0.0
        var amp = 1.0
        x1 *= frequency
        y1 *= frequency
        z1 *= frequency
        for (i in 0 until numOctaves) {
            val n = source[i]!![x1, y1, z1]
            sum += n * amp
            amp *= gain
            x1 *= lacunarity
            y1 *= lacunarity
            z1 *= lacunarity
        }
        return sum
    }

    fun getFBM(x: Double, y: Double, z: Double, w: Double): Double {
        var x1 = x
        var y1 = y
        var z1 = z
        var w1 = w
        var sum = 0.0
        var amp = 1.0
        x1 *= frequency
        y1 *= frequency
        z1 *= frequency
        w1 *= frequency
        for (i in 0 until numOctaves) {
            val n = source[i]!![x1, y1, z1, w1]
            sum += n * amp
            amp *= gain
            x1 *= lacunarity
            y1 *= lacunarity
            z1 *= lacunarity
            w1 *= lacunarity
        }
        return sum
    }

    fun getFBM(
        x: Double, y: Double, z: Double, w: Double, u: Double,
        v: Double
    ): Double {
        var x1 = x
        var y1 = y
        var z1 = z
        var w1 = w
        var u1 = u
        var v1 = v
        var sum = 0.0
        var amp = 1.0
        x1 *= frequency
        y1 *= frequency
        z1 *= frequency
        w1 *= frequency
        u1 *= frequency
        v1 *= frequency
        for (i in 0 until numOctaves) {
            val n = source[i]!![x1, y1, z1, w1]
            sum += n * amp
            amp *= gain
            x1 *= lacunarity
            y1 *= lacunarity
            z1 *= lacunarity
            w1 *= lacunarity
            u1 *= lacunarity
            v1 *= lacunarity
        }
        return sum
    }

    fun getMulti(x: Double, y: Double): Double {
        var x1 = x
        var y1 = y
        var value = 1.0
        x1 *= frequency
        y1 *= frequency
        for (i in 0 until numOctaves) {
            value *= source[i]!![x1, y1] * exparray[i] + 1.0
            x1 *= lacunarity
            y1 *= lacunarity
        }
        return value * correct[numOctaves - 1][0] + correct[numOctaves - 1][1]
    }

    fun getMulti(x: Double, y: Double, z: Double): Double {
        var x1 = x
        var y1 = y
        var z1 = z
        var value = 1.0
        x1 *= frequency
        y1 *= frequency
        z1 *= frequency
        for (i in 0 until numOctaves) {
            value *= source[i]!![x1, y1, z1] * exparray[i] + 1.0
            x1 *= lacunarity
            y1 *= lacunarity
            z1 *= lacunarity
        }
        return value * correct[numOctaves - 1][0] + correct[numOctaves - 1][1]
    }

    fun getMulti(x: Double, y: Double, z: Double, w: Double): Double {
        var x1 = x
        var y1 = y
        var z1 = z
        var w1 = w
        var value = 1.0
        x1 *= frequency
        y1 *= frequency
        z1 *= frequency
        w1 *= frequency
        for (i in 0 until numOctaves) {
            value *= source[i]!![x1, y1, z1, w1] * exparray[i] + 1.0
            x1 *= lacunarity
            y1 *= lacunarity
            z1 *= lacunarity
            w1 *= lacunarity
        }
        return value * correct[numOctaves - 1][0] + correct[numOctaves - 1][1]
    }

    fun getMulti(
        x: Double, y: Double, z: Double, w: Double, u: Double,
        v: Double
    ): Double {
        var x1 = x
        var y1 = y
        var z1 = z
        var w1 = w
        var u1 = u
        var v1 = v
        var value = 1.0
        x1 *= frequency
        y1 *= frequency
        z1 *= frequency
        w1 *= frequency
        u1 *= frequency
        v1 *= frequency
        for (i in 0 until numOctaves) {
            value *= source[i]!![x1, y1, z1, w1, u1, v1] * exparray[i] + 1.0
            x1 *= lacunarity
            y1 *= lacunarity
            z1 *= lacunarity
            w1 *= lacunarity
            u1 *= lacunarity
            v1 *= lacunarity
        }
        return value * correct[numOctaves - 1][0] + correct[numOctaves - 1][1]
    }

    fun getBillow(x: Double, y: Double): Double {
        var x1 = x
        var y1 = y
        var sum = 0.0
        var amp = 1.0
        x1 *= frequency
        y1 *= frequency
        for (i in 0 until numOctaves) {
            val n = source[i]!![x1, y1]
            sum += (2.0 * abs(n) - 1.0) * amp
            amp *= gain
            x1 *= lacunarity
            y1 *= lacunarity
        }
        return sum
    }

    fun getBillow(x: Double, y: Double, z: Double): Double {
        var x1 = x
        var y1 = y
        var z1 = z
        var sum = 0.0
        var amp = 1.0
        x1 *= frequency
        y1 *= frequency
        z1 *= frequency
        for (i in 0 until numOctaves) {
            val n = source[i]!![x1, y1, z1]
            sum += (2.0 * abs(n) - 1.0) * amp
            amp *= gain
            x1 *= lacunarity
            y1 *= lacunarity
            z1 *= lacunarity
        }
        return sum
    }

    fun getBillow(
        x: Double,
        y: Double,
        z: Double,
        w: Double
    ): Double {
        var x1 = x
        var y1 = y
        var z1 = z
        var w1 = w
        var sum = 0.0
        var amp = 1.0
        x1 *= frequency
        y1 *= frequency
        z1 *= frequency
        w1 *= frequency
        for (i in 0 until numOctaves) {
            val n = source[i]!![x1, y1, z1, w1]
            sum += (2.0 * abs(n) - 1.0) * amp
            amp *= gain
            x1 *= lacunarity
            y1 *= lacunarity
            z1 *= lacunarity
            w1 *= lacunarity
        }
        return sum
    }

    fun getBillow(
        x: Double, y: Double, z: Double, w: Double, u: Double,
        v: Double
    ): Double {
        var x1 = x
        var y1 = y
        var z1 = z
        var w1 = w
        var u1 = u
        var v1 = v
        var sum = 0.0
        var amp = 1.0
        x1 *= frequency
        y1 *= frequency
        z1 *= frequency
        w1 *= frequency
        u1 *= frequency
        v1 *= frequency
        for (i in 0 until numOctaves) {
            val n = source[i]!![x1, y1, z1, w1, u1, v1]
            sum += (2.0 * abs(n) - 1.0) * amp
            amp *= gain
            x1 *= lacunarity
            y1 *= lacunarity
            z1 *= lacunarity
            w1 *= lacunarity
            u1 *= lacunarity
            v1 *= lacunarity
        }
        return sum
    }

    fun getRidgedMulti(x: Double, y: Double): Double {
        var x1 = x
        var y1 = y
        var sum = 0.0
        var amp = 1.0
        x1 *= frequency
        y1 *= frequency
        for (i in 0 until numOctaves) {
            var n = source[i]!![x1, y1]
            n = 1.0 - abs(n)
            sum += amp * n
            amp *= gain
            x1 *= lacunarity
            y1 *= lacunarity
        }
        return sum
    }

    fun getRidgedMulti(x: Double, y: Double, z: Double): Double {
        var x1 = x
        var y1 = y
        var z1 = z
        var sum = 0.0
        var amp = 1.0
        x1 *= frequency
        y1 *= frequency
        z1 *= frequency
        for (i in 0 until numOctaves) {
            var n = source[i]!![x1, y1, z1]
            n = 1.0 - abs(n)
            sum += amp * n
            amp *= gain
            x1 *= lacunarity
            y1 *= lacunarity
            z1 *= lacunarity
        }
        return sum
    }

    fun getRidgedMulti(
        x: Double,
        y: Double,
        z: Double,
        w: Double
    ): Double {
        var x1 = x
        var y1 = y
        var z1 = z
        var w1 = w
        var result = 0.0
        var signal: Double
        x1 *= frequency
        y1 *= frequency
        z1 *= frequency
        w1 *= frequency
        for (i in 0 until numOctaves) {
            signal = source[i]!![x1, y1, z1, w1]
            signal = offset - abs(signal)
            signal *= signal
            result += signal * exparray[i]
            x1 *= lacunarity
            y1 *= lacunarity
            z1 *= lacunarity
            w1 *= lacunarity
        }
        return result * correct[numOctaves - 1][0] + correct[numOctaves - 1][1]
    }

    fun getRidgedMulti(
        x: Double, y: Double, z: Double, w: Double,
        u: Double, v: Double
    ): Double {
        var x1 = x
        var y1 = y
        var z1 = z
        var w1 = w
        var u1 = u
        var v1 = v
        var result = 0.0
        var signal: Double
        x1 *= frequency
        y1 *= frequency
        z1 *= frequency
        w1 *= frequency
        u1 *= frequency
        v1 *= frequency
        for (i in 0 until numOctaves) {
            signal = source[i]!![x1, y1, z1, w1, u1, v1]
            signal = offset - abs(signal)
            signal *= signal
            result += signal * exparray[i]
            x1 *= lacunarity
            y1 *= lacunarity
            z1 *= lacunarity
            w1 *= lacunarity
            u1 *= lacunarity
            v1 *= lacunarity
        }
        return result * correct[numOctaves - 1][0] + correct[numOctaves - 1][1]
    }

    fun getHybridMulti(x: Double, y: Double): Double {
        var x1 = x
        var y1 = y
        var value: Double
        var signal: Double
        var weight: Double
        x1 *= frequency
        y1 *= frequency
        value = source[0]!![x1, y1] + offset
        weight = gain * value
        x1 *= lacunarity
        y1 *= lacunarity
        for (i in 1 until numOctaves) {
            if (weight > 1.0) weight = 1.0
            signal = (source[i]!![x1, y1] + offset) * exparray[i]
            value += weight * signal
            weight *= gain * signal
            x1 *= lacunarity
            y1 *= lacunarity
        }
        return value * correct[numOctaves - 1][0] + correct[numOctaves - 1][1]
    }

    fun getHybridMulti(x: Double, y: Double, z: Double): Double {
        var x1 = x
        var y1 = y
        var z1 = z
        var value: Double
        var signal: Double
        var weight: Double
        x1 *= frequency
        y1 *= frequency
        z1 *= frequency
        value = source[0]!![x1, y1, z1] + offset
        weight = gain * value
        x1 *= lacunarity
        y1 *= lacunarity
        z1 *= lacunarity
        for (i in 1 until numOctaves) {
            if (weight > 1.0) weight = 1.0
            signal = (source[i]!![x1, y1, z1] + offset) * exparray[i]
            value += weight * signal
            weight *= gain * signal
            x1 *= lacunarity
            y1 *= lacunarity
            z1 *= lacunarity
        }
        return value * correct[numOctaves - 1][0] + correct[numOctaves - 1][1]
    }

    fun getHybridMulti(
        x: Double,
        y: Double,
        z: Double,
        w: Double
    ): Double {
        var x1 = x
        var y1 = y
        var z1 = z
        var w1 = w
        var value: Double
        var signal: Double
        var weight: Double
        x1 *= frequency
        y1 *= frequency
        z1 *= frequency
        w1 *= frequency
        value = source[0]!![x1, y1, z1, w1] + offset
        weight = gain * value
        x1 *= lacunarity
        y1 *= lacunarity
        z1 *= lacunarity
        w1 *= lacunarity
        for (i in 1 until numOctaves) {
            if (weight > 1.0) weight = 1.0
            signal = (source[i]!![x1, y1, z1, w1] + offset) * exparray[i]
            value += weight * signal
            weight *= gain * signal
            x1 *= lacunarity
            y1 *= lacunarity
            z1 *= lacunarity
            w1 *= lacunarity
        }
        return value * correct[numOctaves - 1][0] + correct[numOctaves - 1][1]
    }

    fun getHybridMulti(
        x: Double, y: Double, z: Double, w: Double,
        u: Double, v: Double
    ): Double {
        var x1 = x
        var y1 = y
        var z1 = z
        var w1 = w
        var u1 = u
        var v1 = v
        var value: Double
        var signal: Double
        var weight: Double
        x1 *= frequency
        y1 *= frequency
        z1 *= frequency
        w1 *= frequency
        u1 *= frequency
        v1 *= frequency
        value = source[0]!![x1, y1, z1, w1, u1, v1] + offset
        weight = gain * value
        x1 *= lacunarity
        y1 *= lacunarity
        z1 *= lacunarity
        w1 *= lacunarity
        u1 *= lacunarity
        v1 *= lacunarity
        for (i in 1 until numOctaves) {
            if (weight > 1.0) weight = 1.0
            signal = (source[i]!![x1, y1, z1, w1, u1, v1] + offset) * exparray[i]
            value += weight * signal
            weight *= gain * signal
            x1 *= lacunarity
            y1 *= lacunarity
            z1 *= lacunarity
            w1 *= lacunarity
            u1 *= lacunarity
            v1 *= lacunarity
        }
        return value * correct[numOctaves - 1][0] + correct[numOctaves - 1][1]
    }

    fun getDeCarpentierSwiss(x: Double, y: Double): Double {
        var x1 = x
        var y1 = y
        var sum = 0.0
        var amp = 1.0
        var dxSum = 0.0
        var dySum = 0.0
        x1 *= frequency
        y1 *= frequency
        for (i in 0 until numOctaves) {
            val n = source[i]!![x1 + offset * dxSum, y1 + offset * dySum]
            val dx = source[i]!!.getDX(x1 + offset * dxSum, y1 + offset * dySum)
            val dy = source[i]!!.getDY(x1 + offset * dxSum, y1 + offset * dySum)
            sum += amp * (1.0 - abs(n))
            dxSum += amp * dx * -n
            dySum += amp * dy * -n
            amp *= gain * clamp(sum, 0.0, 1.0)
            x1 *= lacunarity
            y1 *= lacunarity
        }
        return sum
    }

    fun getDeCarpentierSwiss(x: Double, y: Double, z: Double): Double {
        var x1 = x
        var y1 = y
        var z1 = z
        var sum = 0.0
        var amp = 1.0
        var dxSum = 0.0
        var dySum = 0.0
        var dzSum = 0.0
        x1 *= frequency
        y1 *= frequency
        z1 *= frequency
        for (i in 0 until numOctaves) {
            val n = source[i]!![x1 + offset * dxSum, y1 + offset * dySum, z1
                    + offset * dzSum]
            val dx = source[i]!!.getDX(
                x1 + offset * dxSum, y1 + offset * dySum, z1
                        + offset * dzSum
            )
            val dy = source[i]!!.getDY(
                x1 + offset * dxSum, y1 + offset * dySum, z1
                        + offset * dzSum
            )
            val dz = source[i]!!.getDZ(
                x1 + offset * dxSum, y1 + offset * dySum, z1
                        + offset * dzSum
            )
            sum += amp * (1.0 - abs(n))
            dxSum += amp * dx * -n
            dySum += amp * dy * -n
            dzSum += amp * dz * -n
            amp *= gain * clamp(sum, 0.0, 1.0)
            x1 *= lacunarity
            y1 *= lacunarity
            z1 *= lacunarity
        }
        return sum
    }

    fun getDeCarpentierSwiss(
        x: Double,
        y: Double,
        z: Double,
        w: Double
    ): Double {
        var x1 = x
        var y1 = y
        var z1 = z
        var w1 = w
        var sum = 0.0
        var amp = 1.0
        var dxSum = 0.0
        var dySum = 0.0
        var dzSum = 0.0
        var dwSum = 0.0
        x1 *= frequency
        y1 *= frequency
        z1 *= frequency
        w1 *= frequency
        for (i in 0 until numOctaves) {
            val n = source[i]!![x1 + offset * dxSum, y1 + offset * dySum, z1
                    + offset * dzSum, w1 + offset * dwSum]
            val dx = source[i]!!.getDX(
                x1 + offset * dxSum, y1 + offset * dySum, z1
                        + offset * dzSum, w1 + offset * dwSum
            )
            val dy = source[i]!!.getDY(
                x1 + offset * dxSum, y1 + offset * dySum, z1
                        + offset * dzSum, w1 + offset * dwSum
            )
            val dz = source[i]!!.getDZ(
                x1 + offset * dxSum, y1 + offset * dySum, z1
                        + offset * dzSum, w1 + offset * dwSum
            )
            val dw = source[i]!!.getDW(
                x1 + offset * dxSum, y1 + offset * dySum, z1
                        + offset * dzSum, w1 + offset * dwSum
            )
            sum += amp * (1.0 - abs(n))
            dxSum += amp * dx * -n
            dySum += amp * dy * -n
            dzSum += amp * dz * -n
            dwSum += amp * dw * -n
            amp *= gain * clamp(sum, 0.0, 1.0)
            x1 *= lacunarity
            y1 *= lacunarity
            z1 *= lacunarity
            w1 *= lacunarity
        }
        return sum
    }

    fun getDeCarpentierSwiss(
        x: Double, y: Double, z: Double, w: Double,
        u: Double, v: Double
    ): Double {
        var x1 = x
        var y1 = y
        var z1 = z
        var w1 = w
        var u1 = u
        var v1 = v
        var sum = 0.0
        var amp = 1.0
        var dxSum = 0.0
        var dySum = 0.0
        var dzSum = 0.0
        var dwSum = 0.0
        var duSum = 0.0
        var dvSum = 0.0
        x1 *= frequency
        y1 *= frequency
        z1 *= frequency
        w1 *= frequency
        u1 *= frequency
        v1 *= frequency
        for (i in 0 until numOctaves) {
            val n = source[i]!![x1 + offset * dxSum, y1 + offset * dySum, z1
                    + offset * dzSum]
            val dx = source[i]!!.getDX(
                x1 + offset * dxSum, y1 + offset * dySum, z1
                        + offset * dxSum, w1 + offset * dwSum, u1 + offset * duSum, v1
                        + offset * dvSum
            )
            val dy = source[i]!!.getDY(
                x1 + offset * dxSum, y1 + offset * dySum, z1
                        + offset * dzSum, w1 + offset * dwSum, u1 + offset * duSum, v1
                        + offset * dvSum
            )
            val dz = source[i]!!.getDZ(
                x1 + offset * dxSum, y1 + offset * dySum, z1
                        + offset * dzSum, w1 + offset * dwSum, u1 + offset * duSum, v1
                        + offset * dvSum
            )
            val dw = source[i]!!.getDW(
                x1 + offset * dxSum, y1 + offset * dySum, z1
                        + offset * dzSum, w1 + offset * dwSum, u1 + offset * duSum, v1
                        + offset * dvSum
            )
            val du = source[i]!!.getDU(
                x1 + offset * dxSum, y1 + offset * dySum, z1
                        + offset * dzSum, w1 + offset * dwSum, u1 + offset * duSum, v1
                        + offset * dvSum
            )
            val dv = source[i]!!.getDV(
                x1 + offset * dxSum, y1 + offset * dySum, z1
                        + offset * dzSum, w1 + offset * dwSum, u1 + offset * duSum, v1
                        + offset * dvSum
            )
            sum += amp * (1.0 - abs(n))
            dxSum += amp * dx * -n
            dySum += amp * dy * -n
            dzSum += amp * dz * -n
            dwSum += amp * dw * -n
            duSum += amp * du * -n
            dvSum += amp * dv * -n
            amp *= gain * clamp(sum, 0.0, 1.0)
            x1 *= lacunarity
            y1 *= lacunarity
            z1 *= lacunarity
            w1 *= lacunarity
            u1 *= lacunarity
            v1 *= lacunarity
        }
        return sum
    }

    fun calcWeights(type: FractalType?) {
        var minvalue: Double
        var maxvalue: Double
        when (type) {
            FractalType.FBM -> {
                run {
                    var i = 0
                    while (i < MAX_SOURCES) {
                        exparray[i] = lacunarity.pow(-i * h)
                        i++
                    }
                }
                minvalue = 0.0
                maxvalue = 0.0
                var i = 0
                while (i < MAX_SOURCES) {
                    minvalue += -1.0 * exparray[i]
                    maxvalue += 1.0 * exparray[i]
                    val a = -1.0
                    val b = 1.0
                    val scale = (b - a) / (maxvalue - minvalue)
                    val bias = a - minvalue * scale
                    correct[i][0] = scale
                    correct[i][1] = bias
                    i++
                }
            }
            FractalType.RIDGEMULTI -> {
                run {
                    var i = 0
                    while (i < MAX_SOURCES) {
                        exparray[i] = lacunarity.pow(-i * h)
                        ++i
                    }
                }
                minvalue = 0.0
                maxvalue = 0.0
                var i = 0
                while (i < MAX_SOURCES) {
                    minvalue += (offset - 1.0) * (offset - 1.0) * exparray[i]
                    maxvalue += offset * offset * exparray[i]
                    val a = -1.0
                    val b = 1.0
                    val scale = (b - a) / (maxvalue - minvalue)
                    val bias = a - minvalue * scale
                    correct[i][0] = scale
                    correct[i][1] = bias
                    ++i
                }
            }
            FractalType.DECARPENTIERSWISS -> {
                run {
                    var i = 0
                    while (i < MAX_SOURCES) {
                        exparray[i] = lacunarity.pow(-i * h)
                        ++i
                    }
                }
                minvalue = 0.0
                maxvalue = 0.0
                var i = 0
                while (i < MAX_SOURCES) {
                    minvalue += (offset - 1.0) * (offset - 1.0) * exparray[i]
                    maxvalue += offset * offset * exparray[i]
                    val a = -1.0
                    val b = 1.0
                    val scale = (b - a) / (maxvalue - minvalue)
                    val bias = a - minvalue * scale
                    correct[i][0] = scale
                    correct[i][1] = bias
                    ++i
                }
            }
            FractalType.BILLOW -> {
                run {
                    var i = 0
                    while (i < MAX_SOURCES) {
                        exparray[i] = lacunarity.pow(-i * h)
                        ++i
                    }
                }
                minvalue = 0.0
                maxvalue = 0.0
                var i = 0
                while (i < MAX_SOURCES) {
                    minvalue += -1.0 * exparray[i]
                    maxvalue += 1.0 * exparray[i]
                    val a = -1.0
                    val b = 1.0
                    val scale = (b - a) / (maxvalue - minvalue)
                    val bias = a - minvalue * scale
                    correct[i][0] = scale
                    correct[i][1] = bias
                    ++i
                }
            }
            FractalType.MULTI -> {
                run {
                    var i = 0
                    while (i < MAX_SOURCES) {
                        exparray[i] = lacunarity.pow(-i * h)
                        ++i
                    }
                }
                minvalue = 1.0
                maxvalue = 1.0
                var i = 0
                while (i < MAX_SOURCES) {
                    minvalue *= -1.0 * exparray[i] + 1.0
                    maxvalue *= 1.0 * exparray[i] + 1.0
                    val a = -1.0
                    val b = 1.0
                    val scale = (b - a) / (maxvalue - minvalue)
                    val bias = a - minvalue * scale
                    correct[i][0] = scale
                    correct[i][1] = bias
                    ++i
                }
            }
            FractalType.HYBRIDMULTI -> {
                run {
                    var i = 0
                    while (i < MAX_SOURCES) {
                        exparray[i] = lacunarity.pow(-i * h)
                        ++i
                    }
                }
                val a = -1.0
                val b = 1.0
                var scale: Double
                var bias: Double
                minvalue = offset - 1.0
                maxvalue = offset + 1.0
                var weightmin: Double = gain * minvalue
                var weightmax: Double = gain * maxvalue
                scale = (b - a) / (maxvalue - minvalue)
                bias = a - minvalue * scale
                correct[0][0] = scale
                correct[0][1] = bias
                var i = 1
                while (i < MAX_SOURCES) {
                    if (weightmin > 1.0) weightmin = 1.0
                    if (weightmax > 1.0) weightmax = 1.0
                    var signal = (offset - 1.0) * exparray[i]
                    minvalue += signal * weightmin
                    weightmin *= gain * signal
                    signal = (offset + 1.0) * exparray[i]
                    maxvalue += signal * weightmax
                    weightmax *= gain * signal
                    scale = (b - a) / (maxvalue - minvalue)
                    bias = a - minvalue * scale
                    correct[i][0] = scale
                    correct[i][1] = bias
                    ++i
                }
            }
        }
    }

    enum class FractalType {
        FBM, RIDGEMULTI, BILLOW, MULTI, HYBRIDMULTI, DECARPENTIERSWISS
    }

    companion object {
        @JvmField
        val defaultFractalType: FractalType = FractalType.FBM

        @JvmField
        val defaultBasisType: BasisType = BasisType.GRADVAL

        @JvmField
        val defaultInterpolationType: InterpolationType = InterpolationType.QUINTIC

        const val DEFAULT_OCTAVES = 2
        const val DEFAULT_FREQUENCY = 1.0
        const val DEFAULT_LACUNARITY = 2.0
    }

}
