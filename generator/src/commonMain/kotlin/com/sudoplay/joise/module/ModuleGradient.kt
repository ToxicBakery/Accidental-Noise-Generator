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

class ModuleGradient : Module() {

    var gx1 = 0.0
    var gx2 = 0.0
    var gy1 = 0.0
    var gy2 = 0.0
    var gz1 = 0.0
    var gz2 = 0.0
    var gw1 = 0.0
    var gw2 = 0.0
    var gu1 = 0.0
    var gu2 = 0.0
    var gv1 = 0.0
    var gv2 = 0.0
    var x = 0.0
    var y = 0.0
    var z = 0.0
    var w = 0.0
    var u = 0.0
    var v = 0.0
    var vlen = 0.0

    fun setGradient(x1: Double, x2: Double, y1: Double, y2: Double) {
        setGradient(x1, x2, y1, y2, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
    }

    fun setGradient(
        x1: Double, x2: Double, y1: Double, y2: Double,
        z1: Double, z2: Double
    ) {
        setGradient(x1, x2, y1, y2, z1, z2, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
    }

    fun setGradient(
        x1: Double, x2: Double, y1: Double, y2: Double,
        z1: Double, z2: Double, w1: Double, w2: Double
    ) {
        setGradient(x1, x2, y1, y2, z1, z2, w1, w2, 0.0, 0.0, 0.0, 0.0)
    }

    fun setGradient(
        x1: Double,
        x2: Double,
        y1: Double,
        y2: Double,
        z1: Double,
        z2: Double,
        w1: Double,
        w2: Double,
        u1: Double,
        u2: Double,
        v1: Double,
        v2: Double
    ) {
        gx1 = x1
        gx2 = x2
        gy1 = y1
        gy2 = y2
        gz1 = z1
        gz2 = z2
        gw1 = w1
        gw2 = w2
        gu1 = u1
        gu2 = u2
        gv1 = v1
        gv2 = v2
        x = x2 - x1
        y = y2 - y1
        z = z2 - z1
        w = w2 - w1
        u = u2 - u1
        v = v2 - v1
        vlen = x * x + y * y + z * z + w * w + u * u + v * v
    }

    override fun get(x: Double, y: Double): Double {
        val dx = x - gx1
        val dy = y - gy1
        var dp = dx * this.x + dy * this.y
        dp /= vlen
        return dp
    }

    override fun get(x: Double, y: Double, z: Double): Double {
        val dx = x - gx1
        val dy = y - gy1
        val dz = z - gz1
        var dp = dx * this.x + dy * this.y + dz * this.z
        dp /= vlen
        return dp
    }

    override fun get(x: Double, y: Double, z: Double, w: Double): Double {
        val dx = x - gx1
        val dy = y - gy1
        val dz = z - gz1
        val dw = w - gw1
        var dp = dx * this.x + dy * this.y + dz * this.z + dw * this.w
        dp /= vlen
        return dp
    }

    override fun get(
        x: Double,
        y: Double,
        z: Double,
        w: Double,
        u: Double,
        v: Double
    ): Double {
        val dx = x - gx1
        val dy = y - gy1
        val dz = z - gz1
        val dw = w - gw1
        val du = u - gu1
        val dv = v - gv1
        var dp = dx * this.x + dy * this.y + dz * this.z + dw * this.w + (du
                * this.u) + dv * this.v
        dp /= vlen
        return dp
    }

    companion object {
        const val DEFAULT_X1 = 0.0
        const val DEFAULT_X2 = 1.0
        const val DEFAULT_Y1 = 0.0
        const val DEFAULT_Y2 = 1.0
        const val DEFAULT_Z1 = 0.0
        const val DEFAULT_Z2 = 0.0
        const val DEFAULT_W1 = 0.0
        const val DEFAULT_W2 = 0.0
        const val DEFAULT_U1 = 0.0
        const val DEFAULT_U2 = 0.0
        const val DEFAULT_V1 = 0.0
        const val DEFAULT_V2 = 0.0
    }

    init {
        setGradient(
            DEFAULT_X1,
            DEFAULT_X2,
            DEFAULT_Y1,
            DEFAULT_Y2,
            DEFAULT_Z1,
            DEFAULT_Z2,
            DEFAULT_W1,
            DEFAULT_W2,
            DEFAULT_U1,
            DEFAULT_U2,
            DEFAULT_V1,
            DEFAULT_V2
        )
    }

}
