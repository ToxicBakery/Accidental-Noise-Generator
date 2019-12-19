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

@Suppress("ComplexCondition")
class ModuleCache : SourcedModule() {

    var c2 = Cache()
    var c3 = Cache()
    var c4 = Cache()
    var c6 = Cache()

    override operator fun get(x: Double, y: Double): Double {
        if (!c2.valid || c2.x != x || c2.y != y) {
            c2.x = x
            c2.y = y
            c2.valid = true
            c2.value = source[x, y]
        }
        return c2.value
    }

    override operator fun get(x: Double, y: Double, z: Double): Double {
        if (!c3.valid || c3.x != x || c3.y != y || c3.z != z) {
            c3.x = x
            c3.y = y
            c3.z = z
            c3.valid = true
            c3.value = source[x, y, z]
        }
        return c3.value
    }

    override operator fun get(x: Double, y: Double, z: Double, w: Double): Double {
        if (!c4.valid || c4.x != x || c4.y != y || c4.z != z || c4.w != w) {
            c4.x = x
            c4.y = y
            c4.z = z
            c4.w = w
            c4.valid = true
            c4.value = source[x, y, z, w]
        }
        return c4.value
    }

    override operator fun get(
        x: Double,
        y: Double,
        z: Double,
        w: Double,
        u: Double,
        v: Double
    ): Double {
        if (!c6.valid || c6.x != x || c6.y != y || c6.z != z || c6.w != w || c6.u != u || c6.v != v) {
            c6.x = x
            c6.y = y
            c6.z = z
            c6.w = w
            c6.u = u
            c6.v = v
            c6.valid = true
            c6.value = source[x, y, z, w, u, v]
        }
        return c6.value
    }

    inner class Cache {
        var x = 0.0
        var y = 0.0
        var z = 0.0
        var w = 0.0
        var u = 0.0
        var v = 0.0
        var value = 0.0
        var valid = false
    }

}
