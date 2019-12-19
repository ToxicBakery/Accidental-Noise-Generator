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

import kotlin.experimental.and

/**
 * Multiply with carry.
 */
open class MWC256 : BasePRNG() {

    var q = IntArray(BYTE_MAX_INDICES)
    var c = 0
    var lcg = LCG()
    var b = FF

    init {
        setSeed(INIT_SEED)
    }

    override fun get(): Int {
        val idx = (++b and FF).toInt()
        val t: Long = INIT * q[idx] + c
        c = (t shr INT_BITS).toInt()
        q[idx] = t.toInt()
        return t.toInt()
    }

    override fun setSeed(seed: Long) {
        lcg.setSeed(seed)
        for (i in 0..UBYTE_MAX) {
            q[i] = lcg.get()
        }
        c = lcg.getTarget(INIT.toInt())
    }

    companion object {
        private const val INIT = 809430660L
        private const val INIT_SEED = 10000L
        private const val INT_BITS = 32
        private const val UBYTE_MAX = 255
        private const val FF = UBYTE_MAX.toByte()
        private const val BYTE_MAX_INDICES = 256
    }
}