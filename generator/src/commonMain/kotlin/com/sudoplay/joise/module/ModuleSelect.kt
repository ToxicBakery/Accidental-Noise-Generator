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

import com.sudoplay.joise.noise.Util.lerp
import com.sudoplay.joise.noise.Util.quinticBlend

class ModuleSelect : Module() {

    var low: ScalarParameter = ScalarParameter(0.0)
    var high: ScalarParameter = ScalarParameter(0.0)
    var control: ScalarParameter = ScalarParameter(0.0)
    var threshold: ScalarParameter = ScalarParameter(0.0)
    var falloff: ScalarParameter = ScalarParameter(0.0)

    fun setLowSource(source: Double) {
        low.value = source
    }

    fun setHighSource(source: Double) {
        high.value = source
    }

    fun setControlSource(source: Double) {
        control.value = source
    }

    fun setThreshold(source: Double) {
        threshold.value = source
    }

    fun setFalloff(source: Double) {
        falloff.value = source
    }

    fun setLowSource(source: Module?) {
        low.module = source
    }

    fun setHighSource(source: Module?) {
        high.module = source
    }

    fun setControlSource(source: Module?) {
        control.module = source
    }

    fun setThreshold(source: Module?) {
        threshold.module = source
    }

    fun setFalloff(source: Module?) {
        falloff.module = source
    }

    override fun get(x: Double, y: Double): Double {
        val c = control[x, y]
        val f = falloff[x, y]
        val t = threshold[x, y]
        return if (f > 0.0) {
            if (c < t - f) {
                low[x, y]
            } else if (c > t + f) {
                high[x, y]
            } else {
                val lower = t - f
                val upper = t + f
                val blend = quinticBlend((c - lower) / (upper - lower))
                lerp(blend, low[x, y], high[x, y])
            }
        } else {
            if (c < t) {
                low[x, y]
            } else {
                high[x, y]
            }
        }
    }

    override fun get(x: Double, y: Double, z: Double): Double {
        val c = control[x, y, z]
        val f = falloff[x, y, z]
        val t = threshold[x, y, z]
        return if (f > 0.0) {
            if (c < t - f) {
                low[x, y, z]
            } else if (c > t + f) {
                high[x, y, z]
            } else {
                val lower = t - f
                val upper = t + f
                val blend = quinticBlend((c - lower) / (upper - lower))
                lerp(blend, low[x, y, z], high[x, y, z])
            }
        } else {
            if (c < t) {
                low[x, y, z]
            } else {
                high[x, y, z]
            }
        }
    }

    override fun get(x: Double, y: Double, z: Double, w: Double): Double {
        val c = control[x, y, z, w]
        val f = falloff[x, y, z, w]
        val t = threshold[x, y, z, w]
        return if (f > 0.0) {
            if (c < t - f) {
                low[x, y, z, w]
            } else if (c > t + f) {
                high[x, y, z, w]
            } else {
                val lower = t - f
                val upper = t + f
                val blend = quinticBlend((c - lower) / (upper - lower))
                lerp(blend, low[x, y, z, w], high[x, y, z, w])
            }
        } else {
            if (c < t) {
                low[x, y, z, w]
            } else {
                high[x, y, z, w]
            }
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
        val c = control[x, y, z, w, u, v]
        val f = falloff[x, y, z, w, u, v]
        val t = threshold[x, y, z, w, u, v]
        return if (f > 0.0) {
            when {
                c < t - f -> {
                    low[x, y, z, w, u, v]
                }
                c > t + f -> {
                    high[x, y, z, w, u, v]
                }
                else -> {
                    val lower = t - f
                    val upper = t + f
                    val blend = quinticBlend((c - lower) / (upper - lower))
                    lerp(
                        blend, low[x, y, z, w, u, v],
                        high[x, y, z, w, u, v]
                    )
                }
            }
        } else {
            if (c < t) {
                low[x, y, z, w, u, v]
            } else {
                high[x, y, z, w, u, v]
            }
        }
    }

}
