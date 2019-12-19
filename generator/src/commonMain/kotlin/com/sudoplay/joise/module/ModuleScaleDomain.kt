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

class ModuleScaleDomain : SourcedModule() {

    var sx = ScalarParameter(1.0)
    var sy = ScalarParameter(1.0)
    var sz = ScalarParameter(1.0)
    var sw = ScalarParameter(1.0)
    var su = ScalarParameter(1.0)
    var sv = ScalarParameter(1.0)

    fun setScaleX(x: Double) {
        sx.value = x
    }

    fun setScaleY(y: Double) {
        sy.value = y
    }

    fun setScaleZ(z: Double) {
        sz.value = z
    }

    fun setScaleW(w: Double) {
        sw.value = w
    }

    fun setScaleU(u: Double) {
        su.value = u
    }

    fun setScaleV(v: Double) {
        sv.value = v
    }

    fun setScaleX(x: Module?) {
        sx.module = x
    }

    fun setScaleY(y: Module?) {
        sy.module = y
    }

    fun setScaleZ(z: Module?) {
        sz.module = z
    }

    fun setScaleW(w: Module?) {
        sw.module = w
    }

    fun setScaleU(u: Module?) {
        su.module = u
    }

    fun setScaleV(v: Module?) {
        sv.module = v
    }

    override fun get(x: Double, y: Double): Double = source[x * sx[x, y], y * sy[x, y]]

    override fun get(x: Double, y: Double, z: Double): Double =
        source[x * sx[x, y, z], y * sy[x, y, z], z * sz[x, y, z]]

    override fun get(x: Double, y: Double, z: Double, w: Double): Double =
        source[x * sx[x, y, z, w], y * sy[x, y, z, w], z * sz[x, y, z, w], w * sw[x, y, z, w]]

    override fun get(
        x: Double,
        y: Double,
        z: Double,
        w: Double,
        u: Double,
        v: Double
    ): Double = source[
            x * sx[x, y, z, w, u, v],
            y * sy[x, y, z, w, u, v],
            z * sz[x, y, z, w, u, v],
            w * sw[x, y, z, w, u, v],
            u * su[x, y, z, w, u, v],
            v * sv[x, y, z, w, u, v]
    ]

    companion object {
        const val DEFAULT_SCALE = 1.0
    }

}
