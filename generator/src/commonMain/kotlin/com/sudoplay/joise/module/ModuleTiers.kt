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

import com.sudoplay.joise.noise.Util.quinticBlend
import kotlin.math.floor

class ModuleTiers : SourcedModule() {

    var numTiers = DEFAULT_NUM_TIERS
    var smooth = DEFAULT_SMOOTH

    override fun get(x: Double, y: Double): Double {
        var numsteps = numTiers
        if (smooth) --numsteps
        val value = source[x, y]
        var tb = floor(value * numsteps.toDouble())
        var tt = tb + 1.0
        val t = value * numsteps.toDouble() - tb
        tb /= numsteps.toDouble()
        tt /= numsteps.toDouble()
        val u: Double
        u = if (smooth) quinticBlend(t) else 0.0
        return tb + u * (tt - tb)
    }

    override fun get(x: Double, y: Double, z: Double): Double {
        var numsteps = numTiers
        if (smooth) --numsteps
        val value = source[x, y, z]
        var tb = floor(value * numsteps.toDouble())
        var tt = tb + 1.0
        val t = value * numsteps.toDouble() - tb
        tb /= numsteps.toDouble()
        tt /= numsteps.toDouble()
        val u: Double
        u = if (smooth) quinticBlend(t) else 0.0
        return tb + u * (tt - tb)
    }

    override fun get(x: Double, y: Double, z: Double, w: Double): Double {
        var numsteps = numTiers
        if (smooth) --numsteps
        val value = source[x, y, z, w]
        var tb = floor(value * numsteps.toDouble())
        var tt = tb + 1.0
        val t = value * numsteps.toDouble() - tb
        tb /= numsteps.toDouble()
        tt /= numsteps.toDouble()
        val u: Double
        u = if (smooth) quinticBlend(t) else 0.0
        return tb + u * (tt - tb)
    }

    override fun get(
        x: Double,
        y: Double,
        z: Double,
        w: Double,
        u: Double,
        v: Double
    ): Double {
        var numsteps = numTiers
        if (smooth) --numsteps
        val value = source[x, y, z, w, u, v]
        var tb = floor(value * numsteps.toDouble())
        var tt = tb + 1.0
        val t = value * numsteps.toDouble() - tb
        tb /= numsteps.toDouble()
        tt /= numsteps.toDouble()
        val s: Double
        s = if (smooth) quinticBlend(t) else 0.0
        return tb + s * (tt - tb)
    }

    companion object {
        const val DEFAULT_NUM_TIERS = 0
        const val DEFAULT_SMOOTH = true
    }

}
