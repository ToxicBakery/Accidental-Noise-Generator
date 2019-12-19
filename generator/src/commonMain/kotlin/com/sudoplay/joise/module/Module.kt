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

import kotlin.jvm.JvmField

abstract class Module {

    @JvmField
    var nextId = 0

    open var spacing = 0.0001
    private val id = setId()

    abstract operator fun get(x: Double, y: Double): Double
    abstract operator fun get(x: Double, y: Double, z: Double): Double
    abstract operator fun get(x: Double, y: Double, z: Double, w: Double): Double
    abstract operator fun get(
        x: Double, y: Double, z: Double, w: Double, u: Double,
        v: Double
    ): Double

    fun setId(): String = "func_${++nextId}"

    open fun getId(): String = id

    fun setDerivativeSpacing(spacing: Double) {
        this.spacing = spacing
    }

    fun getDX(x: Double, y: Double): Double {
        return (get(x - spacing, y) - get(x + spacing, y)) / spacing
    }

    fun getDY(x: Double, y: Double): Double {
        return (get(x, y - spacing) - get(x, y + spacing)) / spacing
    }

    fun getDX(x: Double, y: Double, z: Double): Double {
        return (get(x - spacing, y, z) - get(x + spacing, y, z)) / spacing
    }

    fun getDY(x: Double, y: Double, z: Double): Double {
        return (get(x, y - spacing, z) - get(x, y + spacing, z)) / spacing
    }

    fun getDZ(x: Double, y: Double, z: Double): Double {
        return (get(x, y, z - spacing) - get(x, y, z + spacing)) / spacing
    }

    fun getDX(x: Double, y: Double, z: Double, w: Double): Double {
        return (get(x - spacing, y, z, w) - get(x + spacing, y, z, w)) / spacing
    }

    fun getDY(x: Double, y: Double, z: Double, w: Double): Double {
        return (get(x, y - spacing, z, w) - get(x, y + spacing, z, w)) / spacing
    }

    fun getDZ(x: Double, y: Double, z: Double, w: Double): Double {
        return (get(x, y, z - spacing, w) - get(x, y, z + spacing, w)) / spacing
    }

    fun getDW(x: Double, y: Double, z: Double, w: Double): Double {
        return (get(x, y, z, w - spacing) - get(x, y, z, w + spacing)) / spacing
    }

    fun getDX(
        x: Double,
        y: Double,
        z: Double,
        w: Double,
        u: Double,
        v: Double
    ): Double {
        return ((get(x - spacing, y, z, w, u, v) - get(x + spacing, y, z, w, u, v)) / spacing)
    }

    fun getDY(
        x: Double,
        y: Double,
        z: Double,
        w: Double,
        u: Double,
        v: Double
    ): Double {
        return ((get(x, y - spacing, z, w, u, v) - get(x, y + spacing, z, w, u, v)) / spacing)
    }

    fun getDZ(
        x: Double,
        y: Double,
        z: Double,
        w: Double,
        u: Double,
        v: Double
    ): Double {
        return ((get(x, y, z - spacing, w, u, v) - get(x, y, z + spacing, w, u, v)) / spacing)
    }

    fun getDW(
        x: Double,
        y: Double,
        z: Double,
        w: Double,
        u: Double,
        v: Double
    ): Double {
        return ((get(x, y, z, w - spacing, u, v) - get(x, y, z, w + spacing, u, v)) / spacing)
    }

    fun getDU(
        x: Double,
        y: Double,
        z: Double,
        w: Double,
        u: Double,
        v: Double
    ): Double {
        return ((get(x, y, z, w, u - spacing, v) - get(x, y, z, w, u + spacing, v)) / spacing)
    }

    fun getDV(
        x: Double,
        y: Double,
        z: Double,
        w: Double,
        u: Double,
        v: Double
    ): Double {
        return ((get(x, y, z, w, u, v - spacing) - get(x, y, z, w, u, v + spacing)) / spacing)
    }

    fun assertMaxSources(index: Int) {
        require(!(index < 0 || index >= MAX_SOURCES)) { ("expecting index < $MAX_SOURCES but was $index") }
    }

    companion object {
        const val DEFAULT_SEED: Long = 10000
        const val MAX_SOURCES = 10
    }
}
