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

class ModuleMagnitude : Module() {

    var sX: ScalarParameter = ScalarParameter(0.0)
    var sY: ScalarParameter = ScalarParameter(0.0)
    var sZ: ScalarParameter = ScalarParameter(0.0)
    var sW: ScalarParameter = ScalarParameter(0.0)
    var sU: ScalarParameter = ScalarParameter(0.0)
    var sV: ScalarParameter = ScalarParameter(0.0)

    fun setX(source: Double) {
        sX.value = source
    }

    fun setY(source: Double) {
        sY.value = source
    }

    fun setZ(source: Double) {
        sZ.value = source
    }

    fun setW(source: Double) {
        sW.value = source
    }

    fun setU(source: Double) {
        sU.value = source
    }

    fun setV(source: Double) {
        sV.value = source
    }

    fun setX(source: Module?) {
        sX.module = source
    }

    fun setY(source: Module?) {
        sY.module = source
    }

    fun setZ(source: Module?) {
        sZ.module = source
    }

    fun setW(source: Module?) {
        sW.module = source
    }

    fun setU(source: Module?) {
        sU.module = source
    }

    fun setV(source: Module?) {
        sV.module = source
    }

    override fun get(x: Double, y: Double): Double {
        val xx = sX[x, y]
        val yy = sY[x, y]
        return sqrt(xx * xx + yy * yy)
    }

    override fun get(x: Double, y: Double, z: Double): Double {
        val xx = sX[x, y, z]
        val yy = sY[x, y, z]
        val zz = sZ[x, y, z]
        return sqrt(xx * xx + yy * yy + zz * zz)
    }

    override fun get(x: Double, y: Double, z: Double, w: Double): Double {
        val xx = sX[x, y, z, w]
        val yy = sY[x, y, z, w]
        val zz = sZ[x, y, z, w]
        val ww = sW[x, y, z, w]
        return sqrt(xx * xx + yy * yy + zz * zz + ww * ww)
    }

    override fun get(
        x: Double,
        y: Double,
        z: Double,
        w: Double,
        u: Double,
        v: Double
    ): Double {
        val xx = sX[x, y, z, w, u, v]
        val yy = sY[x, y, z, w, u, v]
        val zz = sZ[x, y, z, w, u, v]
        val ww = sW[x, y, z, w, u, v]
        val uu = sU[x, y, z, w, u, v]
        val vv = sV[x, y, z, w, u, v]
        return sqrt(xx * xx + yy * yy + zz * zz + ww * ww + uu * uu + vv * vv)
    }

}
