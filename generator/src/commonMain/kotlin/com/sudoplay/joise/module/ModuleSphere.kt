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

import kotlin.math.sqrt

class ModuleSphere : Module() {

    var cx: ScalarParameter = ScalarParameter(0.0)
    var cy: ScalarParameter = ScalarParameter(0.0)
    var cz: ScalarParameter = ScalarParameter(0.0)
    var cw: ScalarParameter = ScalarParameter(0.0)
    var cu: ScalarParameter = ScalarParameter(0.0)
    var cv: ScalarParameter = ScalarParameter(0.0)
    var radius: ScalarParameter = ScalarParameter(1.0)

    fun setCenterX(source: Double) {
        cx.value = source
    }

    fun setCenterY(source: Double) {
        cy.value = source
    }

    fun setCenterZ(source: Double) {
        cz.value = source
    }

    fun setCenterW(source: Double) {
        cw.value = source
    }

    fun setCenterU(source: Double) {
        cu.value = source
    }

    fun setCenterV(source: Double) {
        cv.value = source
    }

    fun setRadius(source: Double) {
        radius.value = source
    }

    fun setCenterX(source: Module?) {
        cx.module = source
    }

    fun setCenterY(source: Module?) {
        cy.module = source
    }

    fun setCenterZ(source: Module?) {
        cz.module = source
    }

    fun setCenterW(source: Module?) {
        cw.module = source
    }

    fun setCenterU(source: Module?) {
        cu.module = source
    }

    fun setCenterV(source: Module?) {
        cv.module = source
    }

    fun setRadius(source: Module?) {
        radius.module = source
    }

    override fun get(x: Double, y: Double): Double {
        val dx = x - cx[x, y]
        val dy = y - cy[x, y]
        val len = sqrt(dx * dx + dy * dy)
        val r = radius[x, y]
        var i = (r - len) / r
        if (i < 0) i = 0.0
        if (i > 1) i = 1.0
        return i
    }

    override fun get(x: Double, y: Double, z: Double): Double {
        val dx = x - cx[x, y, z]
        val dy = y - cy[x, y, z]
        val dz = (z
                - cz[x, y, z])
        val len = sqrt(dx * dx + dy * dy + dz * dz)
        val r = radius[x, y, z]
        var i = (r - len) / r
        if (i < 0) i = 0.0
        if (i > 1) i = 1.0
        return i
    }

    override fun get(x: Double, y: Double, z: Double, w: Double): Double {
        val dx = x - cx[x, y, z, w]
        val dy = y - cy[x, y, z, w]
        val dz = (z
                - cz[x, y, z, w])
        val dw = w - cw[x, y, z, w]
        val len = sqrt(dx * dx + dy * dy + dz * dz + dw * dw)
        val r = radius[x, y, z, w]
        var i = (r - len) / r
        if (i < 0) i = 0.0
        if (i > 1) i = 1.0
        return i
    }

    override fun get(
        x: Double,
        y: Double,
        z: Double,
        w: Double,
        u: Double,
        v: Double
    ): Double {
        val dx = x - cx[x, y, z, w, u, v]
        val dy = y - cy[x, y, z, w, u, v]
        val dz = (z
                - cz[x, y, z, w, u, v])
        val dw = w - cw[x, y, z, w, u, v]
        val du = (u
                - cu[x, y, z, w, u, v])
        val dv = v - cv[x, y, z, w, u, v]
        val len = sqrt(
            dx * dx + dy * dy + dz * dz + dw * dw + du * du + (dv
                    * dv)
        )
        val r = radius[x, y, z, w, u, v]
        var i = (r - len) / r
        if (i < 0) i = 0.0
        if (i > 1) i = 1.0
        return i
    }

}
