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

class ModuleCellular : Module() {

    var coefficients = DoubleArray(4)
    var generator: ModuleCellGen? = null

    fun setCellularSource(generator: ModuleCellGen?) {
        this.generator = generator
    }

    fun setCoefficients(a: Double, b: Double, c: Double, d: Double) {
        coefficients[0] = a
        coefficients[1] = b
        coefficients[2] = c
        coefficients[3] = d
    }

    fun setCoefficient(index: Int, value: Double) {
        require(!(index > 3 || index < 0))
        coefficients[index] = value
    }

    override fun get(x: Double, y: Double): Double {
        if (generator == null) {
            return 0.0
        }
        val c = generator!!.getCache(x, y)
        return c.f[0] * coefficients[0] + c.f[1] * coefficients[1] + (c.f[2]
                * coefficients[2]) + c.f[3] * coefficients[3]
    }

    override fun get(x: Double, y: Double, z: Double): Double {
        if (generator == null) {
            return 0.0
        }
        val c = generator!!.getCache(x, y, z)
        return c.f[0] * coefficients[0] + c.f[1] * coefficients[1] + (c.f[2]
                * coefficients[2]) + c.f[3] * coefficients[3]
    }

    override fun get(x: Double, y: Double, z: Double, w: Double): Double {
        if (generator == null) {
            return 0.0
        }
        val c = generator!!.getCache(x, y, z, w)
        return c.f[0] * coefficients[0] + c.f[1] * coefficients[1] + (c.f[2]
                * coefficients[2]) + c.f[3] * coefficients[3]
    }

    override fun get(
        x: Double,
        y: Double,
        z: Double,
        w: Double,
        u: Double,
        v: Double
    ): Double {
        if (generator == null) {
            return 0.0
        }
        val c = generator!!.getCache(x, y, z, w, u, v)
        return c.f[0] * coefficients[0] + c.f[1] * coefficients[1] + (c.f[2]
                * coefficients[2]) + c.f[3] * coefficients[3]
    }

    init {
        setCoefficients(1.0, 0.0, 0.0, 0.0)
    }

}
