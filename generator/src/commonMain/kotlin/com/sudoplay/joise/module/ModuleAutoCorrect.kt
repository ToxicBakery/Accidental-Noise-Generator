/*
 * Copyright (C) 2013 Jason Taylor.
 * Released as open-source under the Apache License, Version 2.0.
 *
 * ============================================================================
 * | Joise
 * ============================================================================
 *
 * Copyright (C) 2013 Jason Taylor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ============================================================================
 * | Accidental Noise Library
 * | --------------------------------------------------------------------------
 * | Joise is a derivative work based on Josua Tippetts' C++ library:
 * | http://accidentalnoise.sourceforge.net/index.html
 * ============================================================================
 *
 * Copyright (C) 2011 Joshua Tippetts
 *
 *   This software is provided 'as-is', without any express or implied
 *   warranty.  In no event will the authors be held liable for any damages
 *   arising from the use of this software.
 *
 *   Permission is granted to anyone to use this software for any purpose,
 *   including commercial applications, and to alter it and redistribute it
 *   freely, subject to the following restrictions:
 *
 *   1. The origin of this software must not be misrepresented; you must not
 *      claim that you wrote the original software. If you use this software
 *      in a product, an acknowledgment in the product documentation would be
 *      appreciated but is not required.
 *   2. Altered source versions must be plainly marked as such, and must not be
 *      misrepresented as being the original software.
 *   3. This notice may not be removed or altered from any source distribution.
 */
package com.sudoplay.joise.module

import com.sudoplay.joise.generator.LCG
import com.sudoplay.joise.noise.Util.clamp
import com.sudoplay.util.Checked.safeLongToInt
import kotlin.jvm.JvmOverloads

class ModuleAutoCorrect @JvmOverloads constructor(
    var low: Double = DEFAULT_LOW,
    var high: Double = DEFAULT_HIGH
) : SourcedModule() {

    var sampleScale = DEFAULT_SAMPLE_SCALE

    var scale2D = 0.0
        set
    var offset2D = 0.0
        set
    var scale3D = 0.0
        set
    var offset3D = 0.0
        set
    var scale4D = 0.0
        set
    var offset4D = 0.0
        set
    var scale6D = 0.0
        set
    var offset6D = 0.0
        set

    var locked = false

    private var _samples = DEFAULT_SAMPLES
    var samples: Long
        get() = _samples.toLong()
        set(value) {
            _samples = safeLongToInt(value)
        }

    fun setRange(low: Double, high: Double) {
        this.low = low
        this.high = high
    }

    fun calculate() {
        if (!source.isModule() || locked) return
        var mn: Double
        var mx: Double
        val lcg = LCG()
        // Calculate 2D
        mn = 10000.0
        mx = -10000.0
        for (c in 0 until samples) {
            val nx = (lcg.get01() * 4.0 - 2.0) * sampleScale
            val ny = (lcg.get01() * 4.0 - 2.0) * sampleScale
            val v = source[nx, ny]
            if (v < mn) mn = v
            if (v > mx) mx = v
        }
        scale2D = (high - low) / (mx - mn)
        offset2D = low - mn * scale2D
        // Calculate 3D
        mn = 10000.0
        mx = -10000.0
        for (c in 0 until samples) {
            val nx = (lcg.get01() * 4.0 - 2.0) * sampleScale
            val ny = (lcg.get01() * 4.0 - 2.0) * sampleScale
            val nz = (lcg.get01() * 4.0 - 2.0) * sampleScale
            val v = source[nx, ny, nz]
            if (v < mn) mn = v
            if (v > mx) mx = v
        }
        scale3D = (high - low) / (mx - mn)
        offset3D = low - mn * scale3D
        // Calculate 4D
        mn = 10000.0
        mx = -10000.0
        for (c in 0 until samples) {
            val nx = (lcg.get01() * 4.0 - 2.0) * sampleScale
            val ny = (lcg.get01() * 4.0 - 2.0) * sampleScale
            val nz = (lcg.get01() * 4.0 - 2.0) * sampleScale
            val nw = (lcg.get01() * 4.0 - 2.0) * sampleScale
            val v = source[nx, ny, nz, nw]
            if (v < mn) mn = v
            if (v > mx) mx = v
        }
        scale4D = (high - low) / (mx - mn)
        offset4D = low - mn * scale4D
        // Calculate 6D
        mn = 10000.0
        mx = -10000.0
        for (c in 0 until samples) {
            val nx = (lcg.get01() * 4.0 - 2.0) * sampleScale
            val ny = (lcg.get01() * 4.0 - 2.0) * sampleScale
            val nz = (lcg.get01() * 4.0 - 2.0) * sampleScale
            val nw = (lcg.get01() * 4.0 - 2.0) * sampleScale
            val nu = (lcg.get01() * 4.0 - 2.0) * sampleScale
            val nv = (lcg.get01() * 4.0 - 2.0) * sampleScale
            val v = source[nx, ny, nz, nw, nu, nv]
            if (v < mn) mn = v
            if (v > mx) mx = v
        }
        scale6D = (high - low) / (mx - mn)
        offset6D = low - mn * scale6D
    }

    override fun get(x: Double, y: Double): Double {
        val v = source[x, y]
        return clamp(v * scale2D + offset2D, low, high)
    }

    override fun get(x: Double, y: Double, z: Double): Double {
        val v = source[x, y, z]
        return clamp(v * scale3D + offset3D, low, high)
    }

    override fun get(x: Double, y: Double, z: Double, w: Double): Double {
        val v = source[x, y, z, w]
        return clamp(v * scale4D + offset4D, low, high)
    }

    override fun get(
        x: Double,
        y: Double,
        z: Double,
        w: Double,
        u: Double,
        v: Double
    ): Double {
        val value = source[x, y, z, w, u, v]
        return clamp(value * scale6D + offset6D, low, high)
    }

    companion object {
        const val DEFAULT_LOW = 0.0
        const val DEFAULT_HIGH = 1.0
        const val DEFAULT_SAMPLES = 100
        const val DEFAULT_SAMPLE_SCALE = 1.0
    }

}
