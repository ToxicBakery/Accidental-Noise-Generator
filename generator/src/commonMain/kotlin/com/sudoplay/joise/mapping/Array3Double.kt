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
package com.sudoplay.joise.mapping

import kotlin.jvm.JvmOverloads

class Array3Double @JvmOverloads constructor(
    x: Int = 0,
    y: Int = 0,
    z: Int = 0
) {

    val size = IntArray(DIMENSIONS)

    var data: DoubleArray
        private set

    init {
        size[X.toInt()] = x
        size[Y.toInt()] = y
        size[Z.toInt()] = z
        size[XY.toInt()] = x * y
        data = DoubleArray(x * y * z)
    }

    operator fun set(x: Int, y: Int, z: Int, v: Double) {
        data[x + size[X.toInt()] * y + size[XY.toInt()] * z] = v
    }

    operator fun get(x: Int, y: Int, z: Int): Double = data[x + size[X.toInt()] * y + size[XY.toInt()] * z]

    val width: Int
        get() = size[X.toInt()]

    val height: Int
        get() = size[Y.toInt()]

    val depth: Int
        get() = size[Z.toInt()]

    companion object {
        private const val DIMENSIONS = 4
        const val X: Byte = 0
        const val Y: Byte = 1
        const val Z: Byte = 2
        const val XY: Byte = 3
    }

}
