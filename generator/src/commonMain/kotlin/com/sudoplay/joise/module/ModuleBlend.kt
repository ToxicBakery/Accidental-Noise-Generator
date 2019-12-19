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

class ModuleBlend : Module() {

    var low = ScalarParameter(0.0)
    var high = ScalarParameter(1.0)
    var control = ScalarParameter(0.5)

    fun setLowSource(source: Module?) {
        low.module = source
    }

    fun setLowSource(source: Double) {
        low.value = source
    }

    fun setHighSource(source: Module?) {
        high.module = source
    }

    fun setHighSource(source: Double) {
        high.value = source
    }

    fun setControlSource(source: Module?) {
        control.module = source
    }

    fun setControlSource(source: Double) {
        control.value = source
    }

    override fun get(x: Double, y: Double): Double {
        val v1 = low[x, y]
        val v2 = high[x, y]
        var bl = control[x, y]
        bl = (bl + 1.0) * 0.5
        return lerp(bl, v1, v2)
    }

    override fun get(x: Double, y: Double, z: Double): Double {
        val v1 = low[x, y, z]
        val v2 = high[x, y, z]
        val bl = control[x, y, z]
        return lerp(bl, v1, v2)
    }

    override fun get(x: Double, y: Double, z: Double, w: Double): Double {
        val v1 = low[x, y, z, w]
        val v2 = high[x, y, z, w]
        val bl = control[x, y, z, w]
        return lerp(bl, v1, v2)
    }

    override fun get(
        x: Double,
        y: Double,
        z: Double,
        w: Double,
        u: Double,
        v: Double
    ): Double {
        val v1 = low[x, y, z, w, u, v]
        val v2 = high[x, y, z, w, u, v]
        val bl = control[x, y, z, w, u, v]
        return lerp(bl, v1, v2)
    }

}
