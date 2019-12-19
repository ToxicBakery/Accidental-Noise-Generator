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

import com.sudoplay.joise.noise.Noise
import kotlin.jvm.JvmField

@Suppress("ComplexCondition")
class ModuleCellGen : SeedableModule() {

    var c2 = CellularCache()
    var c3 = CellularCache()
    var c4 = CellularCache()
    var c6 = CellularCache()

    private val _id = ++nextId

    override fun getId(): String = "func_${_id}"

    override var seed: Long
        get() = super.seed
        set(value) {
            super.seed = value
            c2.valid = false
            c3.valid = false
            c4.valid = false
            c6.valid = false
        }

    fun getCache(x: Double, y: Double): CellularCache {
        if (!c2.valid || c2.x != x || c2.y != y) {
            Noise.cellularFunction2D(x, y, seed, c2.f, c2.d)
            c2.x = x
            c2.y = y
            c2.valid = true
        }
        return c2
    }

    fun getCache(x: Double, y: Double, z: Double): CellularCache {
        if (!c3.valid || c3.x != x || c3.y != y || c3.z != z) {
            Noise.cellularFunction3D(x, y, z, seed, c3.f, c3.d)
            c3.x = x
            c3.y = y
            c3.z = z
            c3.valid = true
        }
        return c3
    }

    fun getCache(x: Double, y: Double, z: Double, w: Double): CellularCache {
        if (!c4.valid || c4.x != x || c4.y != y || c4.z != z || c4.w != w) {
            Noise.cellularFunction4D(x, y, z, w, seed, c4.f, c4.d)
            c4.x = x
            c4.y = y
            c4.z = z
            c4.w = w
            c4.valid = true
        }
        return c4
    }

    fun getCache(
        x: Double, y: Double, z: Double, w: Double,
        u: Double, v: Double
    ): CellularCache {
        if (!c6.valid || c6.x != x || c6.y != y || c6.z != z || c6.w != w || c6.u != u || c6.v != v
        ) {
            Noise.cellularFunction6D(x, y, z, w, u, v, seed, c6.f, c6.d)
            c6.x = x
            c6.y = y
            c6.z = z
            c6.w = w
            c6.u = u
            c6.v = v
            c6.valid = true
        }
        return c6
    }

    override operator fun get(x: Double, y: Double): Double = throw UnsupportedOperationException()

    override operator fun get(x: Double, y: Double, z: Double): Double = throw UnsupportedOperationException()

    override operator fun get(x: Double, y: Double, z: Double, w: Double): Double =
        throw UnsupportedOperationException()

    override operator fun get(
        x: Double,
        y: Double,
        z: Double,
        w: Double,
        u: Double,
        v: Double
    ): Double = throw UnsupportedOperationException()

    inner class CellularCache {
        @JvmField
        var f = DoubleArray(4)
        var d = DoubleArray(4)
        var x = 0.0
        var y = 0.0
        var z = 0.0
        var w = 0.0
        var u = 0.0
        var v = 0.0
        var valid = false
    }

}
