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
package com.sudoplay.joise.generator

open class KISS : BasePRNG() {

    var lcg = LCG()
    var z = 0
    var w = 0
    var jsr = 0
    var jcong = 0

    init {
        setSeed(INIT_SEED)
    }

    @Suppress("MagicNumber")
    override fun get(): Int {
        z = 36969 * (z and USHORT_MAX_VALUE) + (z shr SHORT_BITS)
        w = 18000 * (w and USHORT_MAX_VALUE) + (w shr SHORT_BITS)
        val mwc = (z shl SHORT_BITS) + w
        jcong = 69069 * jcong + 1234567
        jsr = jsr xor (jsr shl 17)
        jsr = jsr xor (jsr shr 13)
        jsr = jsr xor (jsr shl 5)
        return (mwc xor jcong) + jsr
    }

    override fun setSeed(seed: Long) {
        lcg.setSeed(seed)
        z = lcg.get()
        w = lcg.get()
        jsr = lcg.get()
        jcong = lcg.get()
    }

    companion object {
        private const val INIT_SEED = 10000L
        private const val SHORT_BITS = 16
        private const val USHORT_MAX_VALUE = 65535
    }

}
