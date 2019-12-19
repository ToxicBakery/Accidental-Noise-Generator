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

import kotlin.jvm.JvmOverloads
import kotlin.math.sqrt

@Suppress("ComplexCondition")
class ModuleNormalizedCoords @JvmOverloads constructor(
    length: Double = DEFAULT_LENGTH
) : SourcedModule() {

    var length = ScalarParameter(DEFAULT_LENGTH)

    init {
        setLength(length)
    }

    fun setLength(source: Double) {
        length.value = source
    }

    fun setLength(source: Module?) {
        length.module = source
    }

    override fun get(x: Double, y: Double): Double {
        if (x == 0.0 && y == 0.0) return source[x, y]
        val len = sqrt(x * x + y * y)
        val r = length[x, y]
        return source[x / len * r, y / len * r]
    }

    override fun get(x: Double, y: Double, z: Double): Double {
        if (x == 0.0 && y == 0.0 && z == 0.0) return source[x, y, z]
        val len = sqrt(x * x + y * y + z * z)
        val r = length[x, y, z]
        return source[x / len * r, y / len * r, z / len * r]
    }

    override fun get(x: Double, y: Double, z: Double, w: Double): Double {
        if (x == 0.0 && y == 0.0 && z == 0.0 && w == 0.0) return source[x, y, z, w]
        val len = sqrt(x * x + y * y + z * z + w * w)
        val r = length[x, y, z, w]
        return source[x / len * r, y / len * r, z / len * r, w / len * r]
    }

    override fun get(
        x: Double,
        y: Double,
        z: Double,
        w: Double,
        u: Double,
        v: Double
    ): Double {
        if (x == 0.0 && y == 0.0 && z == 0.0 && w == 0.0 && u == 0.0 && v == 0.0) return source[x, y, z, w, u, v]
        val len = sqrt(x * x + y * y + z * z + w * w + u * u + v * v)
        val r = length[x, y, z, w, u, v]
        return source[x / len * r, y / len * r, z / len * r, w / len * r, u
                / len * r, v / len * r]
    }

    companion object {
        const val DEFAULT_LENGTH = 1.0

    }

}
