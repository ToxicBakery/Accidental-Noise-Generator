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
package com.sudoplay.joise.noise

import kotlin.jvm.JvmField

@Suppress("LongClass", "MagicNumber")
object NoiseLUT {

    @JvmField
    val gradient2DLUT = arrayOf(
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0),
        doubleArrayOf(0.0, 1.0),
        doubleArrayOf(0.0, -1.0),
        doubleArrayOf(1.0, 0.0),
        doubleArrayOf(-1.0, 0.0)
    )
    @JvmField
    val gradient3DLUT = arrayOf(
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0),
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(-1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, -1.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, -1.0, 0.0)
    )

    @JvmField
    val gradient4DLUT = arrayOf(
        doubleArrayOf(0.22552454980774, 0.66749286953929, 0.53967936463105, -0.46080161668515),
        doubleArrayOf(0.52981737633302, 0.80705123608169, 0.25001068952995, 0.073868160904199),
        doubleArrayOf(0.2551718844115, 0.40169779308827, -0.87581570003174, 0.080455279577005),
        doubleArrayOf(0.2446720608745, -0.57667976542766, -0.10969185644738, 0.77171479667222),
        doubleArrayOf(0.56152621692767, -0.4292067323737, 0.16103874251227, -0.68886603341873),
        doubleArrayOf(
            -0.47332175931021, 0.093442231646057, -0.75172946705559,
            0.44959745313871
        ),
        doubleArrayOf(0.36614231721753, 0.4625818257178, -0.50122840201262, 0.63303076313402),
        doubleArrayOf(
            0.50134394883212, -0.32248055428503, 0.80140206134118,
            -0.049145428633102
        ),
        doubleArrayOf(0.23609302357006, 0.78968939092939, 0.46805407624526, -0.31871010618635),
        doubleArrayOf(0.3566224687564, 0.82280076001748, -0.42198348844058, -0.13322634715498),
        doubleArrayOf(0.53249173494918, 0.35833499357915, 0.62048711191207, -0.45060440359416),
        doubleArrayOf(
            -0.10495021075845, -0.57318889402664, -0.51484484295083,
            -0.62878830516575
        ),
        doubleArrayOf(
            0.48320530716683, -0.57172831979762, -0.61957401150899,
            -0.2361512306338
        ),
        doubleArrayOf(0.56139247202719, -0.59285009197507, -0.5563962302756, 0.15424167961823),
        doubleArrayOf(-0.12102422542118, -0.49213994645356, 0.4794977231538, -0.7164030593571),
        doubleArrayOf(
            -0.39415630019406, -0.16750296810702, -0.77313995219119,
            -0.46780143332237
        ),
        doubleArrayOf(
            -0.077996875735257, -0.72521264783548, 0.21322391118796,
            -0.65001435868195
        ),
        doubleArrayOf(
            0.36675239692246, -0.32607458507754, -0.86882175267727,
            -0.065702407816875
        ),
        doubleArrayOf(
            0.54875576857984, 0.35330602237706, -0.70129121026282,
            -0.28676226985181
        ),
        doubleArrayOf(
            -0.5812728198809, 0.52878141440118, -0.48813116423404,
            -0.37978953534178
        ),
        doubleArrayOf(
            -0.22120336443784, -0.50216367205327, 0.23141872050005,
            -0.80333435992336
        ),
        doubleArrayOf(
            -0.62382494805532, -0.47276765886765, -0.5623445209171,
            -0.26664923533941
        ),
        doubleArrayOf(
            -0.34810294100203, 0.77605534406345, -0.015707133464448,
            -0.52565742777462
        ),
        doubleArrayOf(0.69115837887353, -0.3936531009971, 0.089410248049089, 0.59945236585744),
        doubleArrayOf(0.3133597935846, -0.79750731117234, -0.24841572824554, 0.45174921621572),
        doubleArrayOf(
            0.15712455106329, -0.49358619043723, -0.56123861872675,
            -0.64551976028044
        ),
        doubleArrayOf(0.12957880127654, -0.537223110548, -0.5612004467528, 0.61616127946936),
        doubleArrayOf(0.14715736388495, -0.54550513683857, 0.70400607913576, -0.4302839719035),
        doubleArrayOf(
            -0.27603940501488, 0.66664669430619, -0.20940134756402,
            -0.65995114022784
        ),
        doubleArrayOf(
            -0.28542058243422, -0.26629956698313, -0.48526805245045,
            0.78239027922032
        ),
        doubleArrayOf(
            0.90830597179735, -0.30802834137341, 0.13130250877935,
            -0.25071588241727
        ),
        doubleArrayOf(
            -0.68975034153593, 0.29656664042552, 0.66049372644302,
            -0.0063821209016788
        ),
        doubleArrayOf(
            0.23754231359754, 0.86360891835925, -0.37634599141988,
            -0.23689022800294
        ),
        doubleArrayOf(-0.3008221008451, 0.71840381218331, -0.19107614574202, 0.59740432942188),
        doubleArrayOf(-0.2544530858471, 0.33459224995788, 0.893299034533, 0.15911784398182),
        doubleArrayOf(
            -0.30908481589984, -0.76069512866564, -0.024337358188341,
            0.57027816966892
        ),
        doubleArrayOf(
            0.097288831244174, 0.96921511737704, -0.095017157071808,
            -0.205252720871
        ),
        doubleArrayOf(
            0.63294540707629, -0.14805006160837, -0.42005382286916,
            -0.6332583018104
        ),
        doubleArrayOf(
            -0.67195172729974, -0.10550185088633, 0.18728512185188,
            -0.70871328389651
        ),
        doubleArrayOf(
            -0.69993983031639, -0.27629564601678, 0.47717744770779,
            0.45392359855806
        ),
        doubleArrayOf(0.45229382710377, 0.10953589230206, -0.86039839883114, 0.20771802413423),
        doubleArrayOf(0.57507084430014, 0.49537901571099, 0.63765893106374, 0.13146954956864),
        doubleArrayOf(0.49273255198119, 0.7371708819457, -0.29362806469134, 0.35717822253761),
        doubleArrayOf(0.65308805992157, -0.50561647234808, 0.37455865201808, 0.42134758226026),
        doubleArrayOf(0.015875966700541, 0.70304296752082, 0.628464130577, 0.3324325135707),
        doubleArrayOf(-0.61291521126741, 0.30295474138901, 0.43959142245302, -0.5825055791773),
        doubleArrayOf(
            0.28870150781935, -0.055081790563628, 0.65913922715236,
            -0.69220872210637
        ),
        doubleArrayOf(
            -0.079733017226467, -0.22918659811981, 0.95837698208327,
            -0.15043174338946
        ),
        doubleArrayOf(
            -0.35025365580024, -0.58440279590723, 0.61398669509432,
            -0.39851736095375
        ),
        doubleArrayOf(-0.49583300276112, 0.8320520239064, 0.12466582301665, -0.2151685280373),
        doubleArrayOf(
            -0.57838068316419, -0.20866225618262, -0.25891317127095,
            0.74491598044996
        ),
        doubleArrayOf(0.24517181230967, 0.87106847894657, 0.38643251825943, 0.178326656073),
        doubleArrayOf(
            -0.032939982735198, 0.72735694715047, 0.59789863494626,
            0.33523730594724
        ),
        doubleArrayOf(
            0.46839767570091, 0.28725504887534, -0.82223460003592,
            -0.14838603976579
        ),
        doubleArrayOf(
            -0.68530688721453, 0.30062191695867, -0.65188139443225,
            0.12260334813328
        ),
        doubleArrayOf(
            -0.09481691601797, -0.72846602189708, -0.47810363374399,
            -0.48141865645099
        ),
        doubleArrayOf(
            -0.71095576728456, -0.44889238701561, -0.38663259872485,
            0.37888356449908
        ),
        doubleArrayOf(
            0.65266817536949, -0.048437131039962, 0.073940142453456,
            0.75246990141163
        ),
        doubleArrayOf(
            -0.58567515556267, 0.80836413944392, -0.052196260915081,
            0.028417961999587
        ),
        doubleArrayOf(0.4307524920894, 0.78828122681196, 0.28407778104785, -0.33520861016616),
        doubleArrayOf(
            -0.047152592626151, 0.080906814134336, 0.95331289758053,
            -0.28709796192567
        ),
        doubleArrayOf(
            -0.092934311395387, -0.93376149171941, -0.1711922375466,
            -0.30024308177073
        ),
        doubleArrayOf(0.35964306202515, 0.79090749262634, -0.44772535519024, 0.21133909331798),
        doubleArrayOf(
            0.78826512774619, -0.22601198404862, 0.57215985576402,
            -0.013775735834394
        ),
        doubleArrayOf(
            0.090244004443892, -0.73057802155678, -0.077209310249513,
            -0.6724213682587
        ),
        doubleArrayOf(
            -0.0054007086447244, 0.64607993441819, -0.46651331454209,
            0.60408350254189
        ),
        doubleArrayOf(-0.70185725868085, 0.5369402202072, 0.36637576194925, -0.29130806617212),
        doubleArrayOf(
            -0.32396804158331, 0.70188757706857, -0.30408596246687,
            -0.55671380854088
        ),
        doubleArrayOf(
            0.56090670073868, -0.48169400187882, -0.66248222629843,
            0.12029905011714
        ),
        doubleArrayOf(
            -0.21148730570035, -0.66324178429788, 0.42537806814415,
            -0.57830524312938
        ),
        doubleArrayOf(
            0.49184121803223, -0.53433759959622, 0.41996352447612,
            -0.54424827423881
        ),
        doubleArrayOf(
            -0.62991318148171, 0.6560831504895, -0.018944460160231,
            -0.41521728151487
        ),
        doubleArrayOf(
            -0.74278663987202, -0.57070585901822, -0.26473071464775,
            -0.2290861821062
        ),
        doubleArrayOf(0.5025495101175, -0.37409188240791, 0.47531478480926, 0.61771766102232),
        doubleArrayOf(
            -0.22227660391851, 0.082683771800495, -0.17294940947311,
            0.95595240833119
        ),
        doubleArrayOf(
            0.47549422210941, 0.27384409989549, -0.73038192404162,
            -0.40676393488884
        ),
        doubleArrayOf(0.59221657484784, 0.050200153729467, 0.70240850515692, 0.3916398408286),
        doubleArrayOf(0.89287935537108, -0.18228187488468, 0.20326879532789, 0.35808039835574),
        doubleArrayOf(-0.63303616764545, -0.56090787852805, 0.5182368140323, 0.12679971151219),
        doubleArrayOf(0.78287411316931, 0.49854614640923, 0.30404279624908, -0.2147506481455),
        doubleArrayOf(
            -0.41503470490899, -0.6268605824938, -0.35991041023467,
            0.55250022664371
        ),
        doubleArrayOf(
            -0.53157442679059, 0.048919235389262, -0.84511446630526,
            -0.028584541849774
        ),
        doubleArrayOf(
            0.63177262851732, -0.27840711796915, 0.58586252075951,
            -0.42440302695638
        ),
        doubleArrayOf(-0.44119538483073, 0.5829738945766, -0.51924450975423, 0.44257565425174),
        doubleArrayOf(0.61392386167432, -0.58465170951844, 0.20613607919186, 0.48865917312527),
        doubleArrayOf(0.24475051899163, 0.30754543456076, -0.15222721025769, 0.90682956810601),
        doubleArrayOf(0.41724992835443, -0.13224180301206, 0.89274161403018, 0.10689720951984),
        doubleArrayOf(
            -0.76873245399282, 0.075325506550414, -0.61855849417628,
            0.14408980367632
        ),
        doubleArrayOf(0.53191194272188, 0.53084656080533, 0.3798738941559, -0.5394139770249),
        doubleArrayOf(0.75910942848531, 0.39530213014524, -0.29984000888542, 0.4214084367439),
        doubleArrayOf(
            -0.03541957430639, -0.62483127808284, 0.75776450849439,
            0.18472757604531
        ),
        doubleArrayOf(0.4625891622941, -0.2531420033374, 0.56439885635973, -0.63512543958985),
        doubleArrayOf(0.660548158654, 0.076935839301002, 0.62489061261352, 0.40898499849916),
        doubleArrayOf(
            -0.13772992864036, -0.35663701661207, -0.70063282711493,
            -0.60245675920161
        ),
        doubleArrayOf(0.3331436864299, 0.15331116273714, -0.48119522685978, 0.79621738565511),
        doubleArrayOf(
            0.71571654511597, -0.26028084726799, 0.52940810879996,
            -0.37380578102748
        ),
        doubleArrayOf(
            -0.45879392024731, 0.070422886390706, -0.88044825819407,
            -0.096745131451335
        ),
        doubleArrayOf(-0.3710610095712, 0.49491399293823, -0.19875303607171, 0.76017833264453),
        doubleArrayOf(
            0.67521169438188, -0.21089530160678, 0.30499481128614,
            -0.63764449705041
        ),
        doubleArrayOf(0.37955387270282, 0.37946220745499, 0.64929517854168, -0.53885346983406),
        doubleArrayOf(
            -0.76118100241106, -0.2502648698634, -0.48213952651367,
            0.35427736796742
        ),
        doubleArrayOf(-0.81826013990967, 0.29678177941379, 0.27395924681324, -0.4090443129155),
        doubleArrayOf(
            -0.48735224983659, 0.75897371421789, 0.029778664412101,
            0.43076666172228
        ),
        doubleArrayOf(
            -0.42267571253102, -0.28154084743402, -0.55892959867618,
            0.65549805261817
        ),
        doubleArrayOf(
            -0.34623389175769, -0.19627361980202, 0.62165029251141,
            0.67464781344685
        ),
        doubleArrayOf(
            0.4829697897084, -0.57358816947082, 0.66156267623348,
            0.0084628297236668
        ),
        doubleArrayOf(
            0.74866979663621, -0.0035805505812814, -0.64318857973023,
            -0.16059005625143
        ),
        doubleArrayOf(-0.090152317154269, 0.6862809022405, 0.64123846108899, 0.33121642313131),
        doubleArrayOf(
            -0.78321651600427, -0.19115065168422, -0.56201602070159,
            0.18485483465027
        ),
        doubleArrayOf(-0.73377708689206, 0.0284256453642, 0.67360273611538, 0.08380049722821),
        doubleArrayOf(
            0.49834502400233, -0.35880739756757, -0.78920001543545,
            0.0085337060880708
        ),
        doubleArrayOf(
            -0.8413442675025, -0.49848949817935, -0.033350567138177,
            0.20624205055503
        ),
        doubleArrayOf(
            0.62993466610518, -0.0034911977529077, -0.075714594190885,
            -0.77294076629695
        ),
        doubleArrayOf(
            -0.17272033203772, -0.29580796453879, -0.37548041615855,
            -0.86120833257558
        ),
        doubleArrayOf(
            -0.47432431050844, -0.60995101955389, -0.62418813212925,
            0.11560872767599
        ),
        doubleArrayOf(0.23618736517714, -0.40605404979081, 0.6180271784331, 0.63037928574564),
        doubleArrayOf(
            0.55500851198302, 0.41920843999997, -0.40467056584228,
            -0.59369316031101
        ),
        doubleArrayOf(-0.17861281311101, 0.6681171368602, 0.72039649788735, 0.052400765814444),
        doubleArrayOf(
            0.051032005226464, 0.77077381574869, -0.21368401455548,
            0.59803227448209
        ),
        doubleArrayOf(-0.27130398085814, -0.2356472352851, 0.54860265705132, 0.75491698560123),
        doubleArrayOf(0.34863907202149, -0.1008710712593, 0.31299887927802, -0.87767165045574),
        doubleArrayOf(0.65671708149768, 0.18729913477315, 0.690161625609, 0.23941311476012),
        doubleArrayOf(0.21543450079531, 0.62368781511756, 0.6740194876821, -0.33211325683525),
        doubleArrayOf(
            -0.29368781227182, -0.70194271867824, 0.64731835728572,
            -0.044753021557358
        ),
        doubleArrayOf(
            0.47957288380265, -0.48010386819579, -0.41528424196963,
            -0.60584579163047
        ),
        doubleArrayOf(
            0.77465998420784, 0.2064625496211, -0.59762679675591,
            -0.010832186775088
        ),
        doubleArrayOf(0.59374912564074, 0.44398057182928, -0.628531238492, -0.23514189307461),
        doubleArrayOf(
            0.29933292179378, -0.13849303742408, -0.75484941644759,
            -0.56694077204253
        ),
        doubleArrayOf(
            0.67864785518517, -0.66598612231715, -0.20117390931759,
            -0.23543286037301
        ),
        doubleArrayOf(
            0.41139382663025, -0.50162129081774, 0.76099758294395,
            -0.0037254210934192
        ),
        doubleArrayOf(
            -0.63872455601276, 0.22070744447973, -0.66020520498325,
            -0.32779300299205
        ),
        doubleArrayOf(0.29418192945737, 0.76084869464354, 0.56579367268056, -0.12018226239594),
        doubleArrayOf(
            0.57883638224948, 0.54835864232767, -0.58981423708497,
            -0.12794689406458
        ),
        doubleArrayOf(
            -0.49577506786442, -0.40376295003701, 0.74730897396085,
            -0.18086420237026
        ),
        doubleArrayOf(
            -0.23542938066082, -0.82998760453564, 0.044423043427579,
            0.50370643856465
        ),
        doubleArrayOf(
            0.51460246995974, -0.20375144052087, -0.58171443714984,
            0.59605197928405
        ),
        doubleArrayOf(
            -0.57420458168785, 0.50828481072877, -0.64181398835659,
            -0.0032332492798049
        ),
        doubleArrayOf(
            -0.59114159943176, -0.70202688911205, 0.3951425902627,
            -0.039650847978142
        ),
        doubleArrayOf(0.32348943025434, 0.15426522631209, 0.48865478831089, -0.79547050625742),
        doubleArrayOf(
            0.78091367446976, 0.24332864387958, -0.57453483550803,
            0.029575782012119
        ),
        doubleArrayOf(0.29640854502528, 0.81484848881134, 0.33564412795113, 0.36811266494077),
        doubleArrayOf(0.067881648517454, 0.59549997670039, 0.50990703475037, -0.6170629428644),
        doubleArrayOf(
            0.28894916793968, -0.69198564828725, 0.023585472505226,
            0.66114141180095
        ),
        doubleArrayOf(
            -0.48325755685412, 0.44101587160833, 0.32395739272709,
            -0.68338769555092
        ),
        doubleArrayOf(
            -0.5710256256505, -0.12907895901829, -0.17172158524436,
            0.79232572490761
        ),
        doubleArrayOf(0.6847915554189, 0.46529665784725, 0.53390899832342, 0.17175775770671),
        doubleArrayOf(0.42165492939873, 0.79428961452838, 0.38934026723474, 0.19931202943048),
        doubleArrayOf(-0.63847181078, 0.53274793784908, 0.28633773611669, -0.47596647196249),
        doubleArrayOf(
            0.19156995438377, -0.77779812107245, -0.14338187979369,
            -0.58118213322537
        ),
        doubleArrayOf(0.44339330307, 0.062294908508799, 0.49992742799503, 0.74134626855797),
        doubleArrayOf(
            0.11414857945734, -0.80669520663252, -0.039462641492769,
            0.57849429152793
        ),
        doubleArrayOf(0.51140504554998, 0.77475373616074, 0.37094692794394, 0.024897878221959),
        doubleArrayOf(
            -0.42141421118923, 0.41042157807017, -0.78825942772297,
            -0.18058589498632
        ),
        doubleArrayOf(
            -0.61241893151874, -0.31986126390856, 0.71454099453741,
            0.10983164978162
        ),
        doubleArrayOf(0.42647810613114, -0.16890980399214, -0.58321474597059, 0.670407684315),
        doubleArrayOf(
            -0.62005519270376, 0.24692971568815, 0.55376487937289,
            -0.49789731058161
        ),
        doubleArrayOf(
            -0.2357164754471, 0.80976845323815, -0.53339765737773,
            -0.064805358166192
        ),
        doubleArrayOf(
            -0.45289067914353, -0.60809715423324, -0.18106295928925,
            -0.62635779593756
        ),
        doubleArrayOf(
            -0.60064300362149, -0.55194968809516, 0.27130873161666,
            0.51085330199654
        ),
        doubleArrayOf(
            0.1788394866438, -0.29721754352243, -0.50108833389719,
            -0.79283582882688
        ),
        doubleArrayOf(
            -0.65652402015051, 0.42070413168568, -0.54025124528414,
            0.31640612591564
        ),
        doubleArrayOf(
            0.43620946470649, -0.1249196094834, -0.27642429463064,
            -0.84717530854301
        ),
        doubleArrayOf(0.2543621682741, 0.27962706507339, -0.8372594728531, 0.39510146411661),
        doubleArrayOf(
            -0.81374681782569, -0.49558273399432, -0.30261128321578,
            0.02530378461355
        ),
        doubleArrayOf(-0.67641647228818, 0.48707240260253, 0.33721313271051, 0.43761688012207),
        doubleArrayOf(
            0.28572653298411, 0.63329777351537, -0.019774894218509,
            0.71895982639366
        ),
        doubleArrayOf(
            0.65708200570959, -0.34223476177811, -0.041917555540789,
            -0.67034433251936
        ),
        doubleArrayOf(
            0.071864648185965, -0.59386186862736, 0.59510615312859,
            0.53666769964292
        ),
        doubleArrayOf(
            -0.52113381082923, 0.32294388060376, -0.34686930359901,
            -0.70979467975741
        ),
        doubleArrayOf(
            0.25004346113893, -0.42478362076275, -0.0072114313495278,
            -0.87004892869956
        ),
        doubleArrayOf(
            -0.73610460974203, 0.39085477297004, 0.080434514738008,
            0.54672921890268
        ),
        doubleArrayOf(
            -0.096982051782443, 0.12922152423063, 0.59661992662453,
            -0.78609219717672
        ),
        doubleArrayOf(
            -0.64487244725608, -0.38386246316229, -0.66001973035734,
            0.034104130013256
        ),
        doubleArrayOf(
            0.40625275106386, 0.61435583542081, -0.52718551420461,
            -0.42379363297515
        ),
        doubleArrayOf(0.35878268156959, -0.54570539388485, 0.533316880214, 0.53763715995969),
        doubleArrayOf(0.74780942148728, 0.59755384906022, -0.26296181239253, 0.12067125519944),
        doubleArrayOf(
            0.011316905138058, 0.067102377315201, -0.065593365654346,
            0.99552333423124
        ),
        doubleArrayOf(
            0.49827563594909, -0.084377896327059, -0.53850763396471,
            0.67424868512491
        ),
        doubleArrayOf(
            -0.60044976118633, -0.76761748085432, -0.13587914428152,
            0.17821432469774
        ),
        doubleArrayOf(
            -0.089523490602341, 0.050789198860078, 0.84713826909812,
            0.52130869447859
        ),
        doubleArrayOf(0.46769441316167, 0.50567662542845, 0.70334020343911, 0.175686210668),
        doubleArrayOf(
            -0.072408203487527, -0.31419198049473, 0.093326787268646,
            -0.94198225155181
        ),
        doubleArrayOf(
            -0.47533232374788, 0.74785636631068, 0.46059616621554,
            0.051197744448182
        ),
        doubleArrayOf(
            -0.76253271533295, -0.055650486369972, 0.42686845243099,
            0.48293913253423
        ),
        doubleArrayOf(
            0.55865209759069, 0.49155664008023, -0.11550774589067,
            -0.65798013958615
        ),
        doubleArrayOf(-0.75641809583475, 0.13770124300455, 0.17748717881056, 0.61430312820838),
        doubleArrayOf(
            -0.61344640313317, -0.71640963441606, 0.33111243107609,
            0.028377881980612
        ),
        doubleArrayOf(
            -0.031294256698522, -0.56229739591621, -0.75650470039123,
            -0.3324799938786
        ),
        doubleArrayOf(
            -0.31717913354911, -0.35531289896521, -0.26284619748583,
            -0.83908403484825
        ),
        doubleArrayOf(
            -0.55737524479375, -0.37655617201624, -0.17745250976186,
            0.71836543109117
        ),
        doubleArrayOf(0.48847471582806, 0.27948593392061, 0.6728110320026, -0.4802138897958),
        doubleArrayOf(
            -0.2401291096457, 0.022664846959043, -0.38631652993552,
            -0.89027178665375
        ),
        doubleArrayOf(
            0.28762309079035, -0.16511988268503, 0.68256059934388,
            -0.65124450877502
        ),
        doubleArrayOf(-0.70891164838165, 0.31807128215605, -0.3269335982313, 0.53794921377761),
        doubleArrayOf(
            -0.69925979018203, -0.69254454516254, 0.1409638893828,
            -0.10745687828272
        ),
        doubleArrayOf(
            -0.64120757688022, -0.40145811379293, -0.28410809936898,
            -0.58903889013729
        ),
        doubleArrayOf(0.55206757527974, 0.3654706551822, -0.61301107780799, -0.43112644434103),
        doubleArrayOf(
            0.83974327448309, -0.41693097107897, 0.037330301823741,
            0.34584136085972
        ),
        doubleArrayOf(
            -0.29447692882523, -0.65463017535059, -0.54293549296166,
            -0.43584827909789
        ),
        doubleArrayOf(
            -0.05333405362299, 0.9970375499538, 0.047225492146849,
            -0.029006130347889
        ),
        doubleArrayOf(0.36963990470761, 0.71417634995674, -0.13865235293535, 0.57800865654045),
        doubleArrayOf(
            0.012132225757714, -0.042395617365683, 0.98893274157796,
            0.14165963914058
        ),
        doubleArrayOf(
            -0.58926155813878, -0.12803001087421, 0.79159291473138,
            -0.098791648242605
        ),
        doubleArrayOf(
            -0.19438700623313, 0.67901470490797, -0.70701504134766,
            -0.035811361809222
        ),
        doubleArrayOf(0.34532714734742, 0.46443499574166, 0.60148495729299, 0.55069514450882),
        doubleArrayOf(
            0.081003548026406, 0.72421956716693, -0.17809963017465,
            0.66122988851937
        ),
        doubleArrayOf(
            -0.51806271332971, -0.47124904656651, -0.64746889488023,
            0.30053184744748
        ),
        doubleArrayOf(
            -0.21613493655961, -0.91660583460148, -0.10703233312329,
            -0.31884716219386
        ),
        doubleArrayOf(0.51867393084966, -0.4936987587863, 0.46464576962979, -0.52090613146226),
        doubleArrayOf(
            0.70188550832442, -0.1818312879146, -0.68784926160938,
            -0.034022187509377
        ),
        doubleArrayOf(
            -0.18536271511511, -0.6668126355463, 0.023834990060033,
            0.72141074719593
        ),
        doubleArrayOf(
            -0.15031909443203, -0.33262730296075, -0.48817739687091,
            -0.79274590907714
        ),
        doubleArrayOf(0.20243051601476, -0.5429805329116, 0.6054719468901, 0.54552520436194),
        doubleArrayOf(0.52537152557096, -0.58327808848175, 0.46508915228105, 0.40922305903604),
        doubleArrayOf(0.31485477581576, 0.73539414558825, -0.57616972550483, 0.16760181440614),
        doubleArrayOf(-0.1026518864305, -0.84929281956374, 0.44243387033807, 0.26910326498838),
        doubleArrayOf(
            0.027920771196629, -0.51581324522376, 0.27916735407976,
            -0.80945828490641
        ),
        doubleArrayOf(
            0.52095648805607, -0.31217499797683, 0.78890796559237,
            -0.093676731451121
        ),
        doubleArrayOf(
            -0.32665063346977, -0.42237533445303, -0.29678775049627,
            -0.79171678752876
        ),
        doubleArrayOf(
            -0.64857997918119, 0.22770551134203, -0.62280006685563,
            -0.37364995307697
        ),
        doubleArrayOf(
            -0.60407573890498, -0.71425303787341, -0.057839679785266,
            0.34869710494546
        ),
        doubleArrayOf(0.15576683671559, 0.46059388821688, 0.68108199108405, -0.54746441358124),
        doubleArrayOf(
            -0.42965263988294, -0.4128555315267, -0.80007348682047,
            -0.069507803996135
        ),
        doubleArrayOf(
            0.62468470253348, -0.55608652633181, 0.44736045231719,
            -0.31686814821852
        ),
        doubleArrayOf(-0.5887363856243, 0.15029359337305, 0.75607823697339, 0.24320156991324),
        doubleArrayOf(
            0.78882247105678, -0.55978440598547, -0.19260987829514,
            -0.16523305587714
        ),
        doubleArrayOf(-0.2397762652948, 0.50109182594502, 0.26413811744859, 0.7884449121423),
        doubleArrayOf(
            -0.7830303555834, -0.22084579025076, -0.28393537655337,
            -0.50741629960512
        ),
        doubleArrayOf(
            -0.73073108036106, -0.028836092490741, 0.67193346093654,
            0.11707259302706
        ),
        doubleArrayOf(
            0.024769701828925, -0.022793005056951, -0.61772537208025,
            -0.78567315435879
        ),
        doubleArrayOf(
            -0.58543792858876, -0.21000596085194, -0.34309660713653,
            -0.70387828944198
        ),
        doubleArrayOf(
            -0.73256166113309, -0.39947841108428, -0.55115200779339,
            -0.0013696790967066
        ),
        doubleArrayOf(-0.65553402559555, 0.71965699083607, 0.18362339375227, 0.13657015081697),
        doubleArrayOf(
            0.21014780367008, 0.93277837816337, 0.091205127277356,
            -0.27828766160555
        ),
        doubleArrayOf(
            -0.11787197549632, -0.78483975651814, 0.20342129122119,
            0.57336945528955
        ),
        doubleArrayOf(
            -0.6881964578452, -0.22721428035754, -0.50932656202979,
            -0.46405361696881
        ),
        doubleArrayOf(
            -0.30800922001431, 0.76151734782072, 0.45056635743119,
            -0.34958776709928
        ),
        doubleArrayOf(0.56307840922658, 0.61616965967068, -0.39982036117689, 0.37871009275244),
        doubleArrayOf(0.32091099001565, 0.42988923391959, 0.65069385181346, 0.53740942888953),
        doubleArrayOf(-0.65360317449729, 0.21381907132369, -0.37900175759245, 0.6192269073346),
        doubleArrayOf(
            0.34144172603729, -0.68022974823899, 0.36172522685587,
            -0.53838638317427
        ),
        doubleArrayOf(
            -0.082427387138187, -0.81983951792536, -0.062698716423876,
            0.56314985719281
        ),
        doubleArrayOf(0.53660230478027, -0.10888689641277, 0.49610311211367, 0.67385704154904),
        doubleArrayOf(0.37828905310898, 0.24829585477921, 0.45837561130552, 0.7649433702946),
        doubleArrayOf(
            0.31077471220013, -0.64394458615427, -0.42742602417387,
            -0.55322820072594
        ),
        doubleArrayOf(
            -0.74914861903498, -0.44168063108052, -0.42549539767358,
            0.2502962909931
        ),
        doubleArrayOf(
            0.63772244990386, -0.68871779209415, -0.16359297580573,
            0.30366958690097
        ),
        doubleArrayOf(-0.10823423405648, -0.47886043588247, 0.5951258423328, 0.63624151492365),
        doubleArrayOf(
            -0.58450908643191, -0.23751295715095, 0.68158351804545,
            -0.37064893226977
        ),
        doubleArrayOf(
            -0.51170891961924, 0.51317237144319, -0.3868723565637,
            -0.57020862716758
        ),
        doubleArrayOf(
            -0.65861950006955, 0.33272485253463, -0.34970834814331,
            -0.57725089681422
        ),
        doubleArrayOf(0.67192193031742, 0.52947366261195, 0.23487166423469, -0.46153424737329),
        doubleArrayOf(-0.44355315014768, 0.64728264624511, 0.5825071861006, -0.21206403986806),
        doubleArrayOf(
            0.68832586073433, -0.19106885563402, -0.36416840615895,
            -0.59756302914981
        ),
        doubleArrayOf(
            0.062746887857648, 0.79955877089706, 0.59681863174015,
            -0.02400251556763
        ),
        doubleArrayOf(
            0.28392054221674, -0.049695747604861, -0.95198891214713,
            0.10313374581711
        )
    )

    @JvmField
    val gradient6DLUT = arrayOf(
        doubleArrayOf(
            0.31733186658157, 0.043599150809166, -0.63578104939541,
            0.60224147484783, -0.061995657882187, 0.35587048501823
        ), doubleArrayOf(
            -0.54645425808647, -0.75981513883963, -0.035144342454363,
            0.13137365402959, 0.29650029456531, 0.13289887942467
        ), doubleArrayOf(
            0.72720729277573, -0.0170513084554, 0.10403853926717, 0.57016794579524,
            0.10006650294475, -0.35348266879289
        ), doubleArrayOf(
            0.0524867271859, 0.16599786784909, -0.49406271077513, 0.51847470894887,
            0.63927166664011, -0.21933445140234
        ), doubleArrayOf(
            -0.57224122530978, -0.089985946187774, 0.44829955643248,
            0.53836681748476, -0.051299333576026, -0.41352093713992
        ), doubleArrayOf(
            -0.35034584363296, -0.37367516013323, -0.52676009109159,
            0.12379417201967, 0.42566489477591, 0.51345191723381
        ), doubleArrayOf(
            0.40936909283115, 0.33036021753157, 0.46771483894695, 0.15073372728805,
            0.51541333179083, -0.46491971651678
        ), doubleArrayOf(
            -0.64339751231027, -0.29341468636474, -0.50841617762291,
            -0.080659811936781, -0.46873502824317, -0.12345817650503
        ), doubleArrayOf(
            0.46950904113222, 0.41685007896275, -0.33378791988356,
            -0.39617029121348, 0.54659770033168, 0.19662896748851
        ), doubleArrayOf(
            -0.49213884108338, 0.50450587466563, -0.0073247243900323,
            0.57958418990163, 0.39591449230465, 0.10272980841415
        ), doubleArrayOf(
            0.34572956497624, 0.62770109739866, 0.12165109216674, 0.35267248385686,
            0.34842369637704, -0.47527514024373
        ), doubleArrayOf(
            0.076282233884284, 0.56461194794873, -0.392426730607,
            -0.20639693057567, 0.33197602170266, 0.60711436994661
        ), doubleArrayOf(
            0.46792592791359, -0.38434666353171, -0.46719345820863,
            -0.40169520060432, -0.061343490026986, 0.49993117813162
        ), doubleArrayOf(
            -0.25398819915038, -0.82255018555745, 0.40372967512401,
            0.21051604195389, 0.020384827146984, 0.22621006002887
        ), doubleArrayOf(
            0.23269489013955, -0.42234243708413, -0.18886779174866,
            0.44290933725703, -0.40895242871151, 0.60695810498111
        ), doubleArrayOf(
            -0.13615585122038, 0.26142849716038, 0.68738606675966,
            0.42914965171764, 0.26332301994884, 0.43256061294487
        ), doubleArrayOf(
            0.06145597366231, -0.25432792035414, 0.65050463165568,
            0.35622065678761, -0.52670947710524, -0.32259598080167
        ), doubleArrayOf(
            -0.28027055313228, 0.30275296247348, 0.39083872911587,
            0.17564171472763, 0.25278203996272, 0.76307625890429
        ), doubleArrayOf(
            -0.62937098181034, -0.24958587788613, 0.11855057687171,
            0.52714220921895, 0.47759151204224, -0.14687496867489
        ), doubleArrayOf(
            0.68607574135496, 0.28465344118508, 0.57132493696771, 0.11365238375433,
            -0.32111327299854, -0.076352560636185
        ), doubleArrayOf(
            0.42669573845021, -0.1643996530281, -0.54881376863042,
            -0.56551221465284, 0.4027156095588, -0.087880721039792
        ), doubleArrayOf(
            -0.30211042220321, -0.47278547361731, 0.050137867251391,
            0.46804387457884, -0.39450159355792, 0.55497099667426
        ), doubleArrayOf(
            0.31255895138908, 0.034478918459459, -0.079232996020732,
            0.39803160685016, 0.82281399721198, 0.24369695191021
        ), doubleArrayOf(
            -0.5524321671417, 0.49350231710234, 0.52530668244467, 0.253625789825,
            0.26218499242504, -0.20557247282514
        ), doubleArrayOf(
            0.060763010271891, -0.023938406391206, 0.36557410300471,
            0.55368747615095, 0.25557899769702, -0.70014279913759
        ), doubleArrayOf(
            0.36398574324757, 0.049110464042478, -0.2428951164628,
            -0.18733973495522, 0.020130805835303, 0.87784000694654
        ), doubleArrayOf(
            -0.62385490124849, 0.020947599003133, -0.44548631925386,
            -0.21069894502123, -0.60559127508405, 0.027809382425643
        ), doubleArrayOf(
            0.51562840479369, -0.27416131751628, -0.14365580420426,
            -0.46525735490594, 0.16338488557607, 0.62862302132303
        ), doubleArrayOf(
            0.52085189275139, 0.51359303425374, 0.021844789421786,
            0.53521775458267, -0.23767218281397, -0.34858599348565
        ), doubleArrayOf(
            0.12263603513069, 0.53912951801629, 0.57550729534804,
            -0.10335514143554, 0.57524709075397, 0.14662748040551
        ), doubleArrayOf(
            0.40942178494947, 0.17197663954561, -0.025238012475873,
            -0.20104824969996, -0.60303014654018, 0.63094779803243
        ), doubleArrayOf(
            0.051685704973311, 0.23577798459204, -0.19154992327678,
            -0.67743578708385, -0.51070301615526, 0.43047548181493
        ), doubleArrayOf(
            0.21373839204543, -0.44348268823586, 0.34347986958921,
            -0.49945694096162, 0.45888698118478, -0.42382317871053
        ), doubleArrayOf(
            -0.60376535923059, -0.065300874745824, 0.49448067868339,
            0.12358559784007, 0.58623743735263, -0.16656623971303
        ), doubleArrayOf(
            0.44140930948322, -0.41692548571374, -0.23774988226818,
            -0.27542786466885, 0.39264397083621, 0.58717642823542
        ), doubleArrayOf(
            -0.67860697457746, 0.2070991391515, -0.12832398784247,
            -0.58381216132288, 0.24050209342748, 0.2854077401022
        ), doubleArrayOf(
            -0.021324501342617, 0.0098658783730532, 0.2694901128571,
            0.42580554353158, -0.82903198308789, -0.24128534823695
        ), doubleArrayOf(
            -0.20344882384938, 0.51719618805529, 0.24379623299129,
            0.11303683173372, -0.46058654895958, -0.63777957124993
        ), doubleArrayOf(
            0.15686479897897, -0.67777169905813, -0.04974608057712,
            0.51313211803344, 0.49928667286231, -0.030863149692696
        ), doubleArrayOf(
            0.53527130791104, -0.50102597915466, -0.60754472649714,
            -0.25235098830686, 0.13490559284448, 0.10708155847142
        ), doubleArrayOf(
            -0.20613512232544, 0.39533044356843, -0.34422306275706,
            0.4792145528465, -0.19178040223502, -0.64521804411898
        ), doubleArrayOf(
            0.3304779611047, 0.49148538926455, -0.30004348427342, 0.33473309391851,
            0.31079743137844, 0.59208027276116
        ), doubleArrayOf(
            -0.52688857216953, 0.40250311061529, 0.38833191043333,
            0.50432308135853, -0.33327489215794, -0.21015252001231
        ), doubleArrayOf(
            -0.30306420816123, -0.34460825415019, -0.26894228639121,
            -0.58579646837355, -0.51178483212848, 0.33464319317466
        ), doubleArrayOf(
            -0.20258582390514, -0.29195675136034, 0.11887973573086,
            0.91211540292822, 0.034118810787236, -0.16269371903027
        ), doubleArrayOf(
            0.61207678339522, -0.21883722070929, -0.23415725333464,
            0.0041447691596985, -0.34019274152454, 0.6378827339521
        ), doubleArrayOf(
            0.11272999861808, -0.54780877011146, -0.62497664375172,
            -0.41373740141301, 0.33306010353229, 0.12039112788093
        ), doubleArrayOf(
            0.24918468395037, -0.068734287809286, -0.42234580029763,
            0.12235329631887, -0.26545138767734, 0.81815148205875
        ), doubleArrayOf(
            0.32048708659406, -0.40233908147851, 0.24633289057781,
            -0.37087758270512, -0.55466799718133, -0.47908728788262
        ), doubleArrayOf(
            -0.33748729653627, -0.45507986822699, -0.50597645316527,
            -0.2863701644881, -0.5404199724601, -0.22120318557996
        ), doubleArrayOf(
            -0.23520314824941, 0.82195093398991, -0.22661283339659,
            0.16382454786402, -0.41400232366734, -0.13959354720703
        ), doubleArrayOf(
            -0.30495751902889, -0.47964557116121, -0.68490238495876,
            -0.4324077675155, -0.13521732523742, -0.050887702629247
        ), doubleArrayOf(
            -0.56629250538137, 0.19768903044, -0.080075220953828,
            -0.29952637623112, 0.095974426142512, -0.73136356489112
        ), doubleArrayOf(
            -0.21316607993139, 0.47585902758173, -0.49429850443227,
            -0.24146904800157, 0.45631329089651, 0.46610972545109
        ), doubleArrayOf(
            0.12647584748018, -0.10203700758813, 0.20801341293098,
            0.66418891258418, -0.65219775460192, -0.2526141453282
        ), doubleArrayOf(
            -0.69345279552921, 0.30149980453822, -0.46870940095961,
            0.20092958919922, -0.21817920622376, 0.34721422759447
        ), doubleArrayOf(
            -0.69001417476102, 0.09722776919634, -0.37852252163632,
            -0.24995374433763, 0.24829304775112, 0.4970126640943
        ), doubleArrayOf(
            -0.82278510972964, 0.050748830242865, -0.3934733016285,
            0.00029980431140623, -0.34677214869339, -0.21301870187776
        ), doubleArrayOf(
            -0.51821811089111, -0.22147302694699, 0.53524316281446,
            0.12892242816244, -0.5543955478928, -0.26821451961648
        ), doubleArrayOf(
            -0.21006612796354, 0.26079212570498, -0.021870637510645,
            0.72402587064608, -0.27651658712238, 0.53544979218311
        ), doubleArrayOf(
            -0.099744280251479, -0.4534212871731, 0.71954978543864,
            -0.31082396323078, -0.26933824624449, 0.31233586755618
        ), doubleArrayOf(
            -0.48121951222937, -0.43051247772929, -0.5038415181805,
            0.12342710418307, 0.037467829082858, -0.55909965468017
        ), doubleArrayOf(
            -0.51180831908824, -0.079955485578946, -0.53046702060975,
            0.48748209854708, 0.16148937559829, -0.43191028009105
        ), doubleArrayOf(
            -0.38131649706702, 0.46242477534251, 0.46416075424014,
            -0.20634110277567, -0.53778490132009, 0.30582118902172
        ), doubleArrayOf(
            0.6245043069106, 0.14316692963071, -0.1436103838143, 0.27519251589203,
            -0.60467865310212, -0.35708047307373
        ), doubleArrayOf(
            0.52425890739441, -0.20390682829262, -0.33609142609195,
            0.51803372559413, 0.28921536255925, 0.46756035964091
        ), doubleArrayOf(
            -0.4455164148456, 0.31831805515328, 0.24217750314789, 0.49821219078654,
            -0.47209418708575, 0.41285649844363
        ), doubleArrayOf(
            -0.015857310429397, -0.45214512052441, -0.14591363373753,
            0.74070676188619, 0.0098874230592725, -0.47463489014478
        ), doubleArrayOf(
            0.24260837156464, 0.44639366601915, 0.31528570191456, 0.45334773303464,
            -0.47964168123625, -0.45484996397296
        ), doubleArrayOf(
            0.47123463487178, 0.64525048646519, -0.064257637508608,
            -0.18737730572971, -0.11735335340515, -0.55549853319118
        ), doubleArrayOf(
            -0.025197229767488, -0.257963271803, 0.26277107860996,
            -0.58236203161499, -0.41893538667715, 0.59086294196016
        ), doubleArrayOf(
            -0.48940330017687, 0.33728563842186, -0.057634928591543,
            0.44862021996899, -0.40048256377746, 0.53080564921806
        ), doubleArrayOf(
            0.73350664260388, -0.021482988114587, 0.016568147533453,
            0.0021905972927896, 0.49384961731337, 0.46619710394628
        ), doubleArrayOf(
            -0.25151229880228, -0.62009962583403, -0.26948657433033,
            0.31711936293198, -0.35081923073755, 0.50592112116981
        ), doubleArrayOf(
            0.0094298597779172, -0.35925999444899, 0.47529205807388,
            -0.26709475088579, -0.53352146543694, 0.53754630836074
        ), doubleArrayOf(
            -0.5948549517534, -0.53195924881292, -0.094383768924555,
            -0.41704491211939, -0.41397531920841, -0.09463944474724
        ), doubleArrayOf(
            -0.74917126125127, -0.24166385705367, 0.22864554725283,
            0.31721357549513, 0.06066292638611, -0.47303041351952
        ), doubleArrayOf(
            -0.3300396030254, -0.08758658200966, -0.096726092930468,
            -0.39607089556472, 0.55566932028997, 0.63906648027271
        ), doubleArrayOf(
            -0.58933068378397, -0.38176870540341, 0.46748019640554,
            -0.061358837959321, 0.36268480315292, -0.39127879224432
        ), doubleArrayOf(
            -0.066556695042975, -0.73863083674701, -0.32153946998935,
            0.57454599361106, -0.090856896694743, -0.09082394033963
        ), doubleArrayOf(
            -0.36335404704287, -0.41643677881158, -0.57839830999334,
            -0.030959887755637, 0.5989792522053, -0.016582566905843
        ), doubleArrayOf(
            0.23126668855143, 0.2107790785413, -0.14272193312959,
            -0.29232225134991, -0.48451339172564, -0.74934159314943
        ), doubleArrayOf(
            0.48188197979627, -0.040214759215399, -0.15667971883369,
            0.16054853668069, -0.6083975436752, -0.58796308779952
        ), doubleArrayOf(
            0.31319356064062, -0.19280657835646, 0.76136690598738,
            -0.084506239097717, 0.4768786755523, -0.22472488900872
        ), doubleArrayOf(
            0.67504537519138, 0.36920158913876, 0.40321048682396,
            0.034436041975613, -0.29332731631919, 0.39774172001359
        ), doubleArrayOf(
            -0.1459159803857, -0.59726183207777, -0.036384224081948,
            -0.65093487874945, 0.39515711468056, -0.20198429937477
        ), doubleArrayOf(
            0.60092128630869, 0.18110182176699, 0.2579491954112, -0.39594768022975,
            0.15112959843347, 0.59995268930018
        ), doubleArrayOf(
            -0.42310244265976, -0.26937197256148, 0.074700012546319,
            0.53119510349465, 0.41614374632783, 0.53618944036115
        ), doubleArrayOf(
            0.0071605427687482, -0.69599782505338, -0.053138604739257,
            -0.00054500262230378, 0.69533871546989, 0.1709263483943
        ), doubleArrayOf(
            0.12447149375466, 0.33265313001972, 0.35070015349473, 0.53879932284829,
            0.37648083373421, 0.56463759722353
        ), doubleArrayOf(
            0.29540077719054, 0.04954124873475, -0.48345087234985,
            0.72758494948264, 0.070069102610626, 0.377186640377
        ), doubleArrayOf(
            0.4882414260383, 0.45135801463006, 0.48450857902353, -0.26042407965644,
            -0.4251358047458, 0.2731053563007
        ), doubleArrayOf(
            -0.49806371818291, -0.4719759672029, 0.029647087810764,
            -0.13788472163255, -0.45346141932978, -0.5510470160674
        ), doubleArrayOf(
            -0.5359511936033, -0.53585470245895, 0.1771036246335, -0.4537763243703,
            0.41838964069644, 0.11527149720722
        ), doubleArrayOf(
            -0.36846431808379, -0.46533180802325, 0.65800816763703,
            -0.28691297783558, 0.31521457275327, 0.18178647457201
        ), doubleArrayOf(
            -0.29243126901345, -0.4352956525447, -0.58895978125929,
            -0.49649471729812, 0.29271342931272, 0.21433587621517
        ), doubleArrayOf(
            0.056256690265475, -0.50387710054371, 0.48145041862725,
            0.44723671964597, -0.55771174894027, -0.0092449146014199
        ), doubleArrayOf(
            -0.40973125164006, -0.73147173623276, -0.094076302480945,
            0.43033451471976, 0.014334271843521, -0.32066459724334
        ), doubleArrayOf(
            0.26752725373294, 0.50477344684769, 0.065069516529324,
            0.36001097578267, 0.59393393889869, -0.43247366096278
        ), doubleArrayOf(
            0.48945720845334, 0.6043315650632, 0.12458128550608, -0.48327805813458,
            -0.25681943056744, 0.28316179557217
        ), doubleArrayOf(
            -0.45182760404001, 0.21574002665039, -0.31462623994251,
            0.25279349500371, 0.44865729380505, -0.62058075048081
        ), doubleArrayOf(
            0.44017304540101, 0.43789555905674, 0.58423563606269, 0.41842994331139,
            -0.26836655962348, 0.16143005677844
        ), doubleArrayOf(
            -0.67897032028819, -0.32730885869255, -0.0243997359109,
            0.40649244381227, 0.47711065295824, -0.19596475712206
        ), doubleArrayOf(
            0.57441588138131, 0.09386994843744, 0.28400793066375, 0.59394229842661,
            0.45349906020748, 0.14881354725974
        ), doubleArrayOf(
            -0.3393739967757, -0.54929055652002, 0.26209493900588, 0.0733800373509,
            0.56557076402003, 0.43492125584075
        ), doubleArrayOf(
            0.050007991188197, 0.74652764513134, -0.36432144611385,
            -0.20993543754239, -0.1352041047841, 0.49508839805322
        ), doubleArrayOf(
            -0.041332158875019, -0.20655741061568, 0.52511282214888,
            0.047248635933477, -0.6276121766011, -0.5326844609727
        ), doubleArrayOf(
            -0.1889491176448, 0.05188976739355, -0.45677123586268,
            0.42884456750344, 0.61612085530435, -0.43526216197988
        ), doubleArrayOf(
            -0.65873541163911, -0.094770059351695, 0.40844030815782,
            0.35536013391048, -0.16940065827957, 0.48506226422661
        ), doubleArrayOf(
            -0.45779281442862, -0.46052673126242, 0.34138050378631,
            -0.54943270263121, 0.37140594702643, -0.14826175595089
        ), doubleArrayOf(
            -0.069378715405383, -0.14845488608058, -0.73991837897813,
            0.41519184526768, -0.11098464009855, -0.49088356499611
        ), doubleArrayOf(
            0.46422563805447, 0.46130716873201, -0.44207791495441,
            0.12050605352899, 0.34969556083561, -0.4893349322843
        ), doubleArrayOf(
            -0.35482925073362, 0.28146983672487, -0.35356606227648,
            -0.38774754218768, 0.35979702647173, -0.62454776976122
        ), doubleArrayOf(
            -0.48343191508515, 0.41492185792886, -0.50175316406656,
            0.21953122931153, -0.54083165333237, 0.041040952107647
        ), doubleArrayOf(
            -0.51280508048852, -0.54131124436697, -0.0099287129207481,
            0.23788701199175, 0.4350333223576, 0.44505087885649
        ), doubleArrayOf(
            0.2253837335044, -0.30117119745248, 0.46587685049056,
            -0.46672901001472, -0.59182069765377, 0.27086737661249
        ), doubleArrayOf(
            0.43015756480475, -0.067851118947538, -0.26917802105288,
            -0.57731860676632, -0.53950120703807, -0.33696522367557
        ), doubleArrayOf(
            0.20858352742161, 0.63695057987625, 0.49453142202915,
            -0.046235371593379, -0.54436247241885, -0.088075720520231
        ), doubleArrayOf(
            -0.35626464703623, 0.067539543974725, -0.18142793486226,
            -0.49044207117167, 0.5542388249925, 0.53654796190017
        ), doubleArrayOf(
            0.52238539932434, 0.55175875223621, 0.29070268774296,
            -0.14119026819648, -0.55841587206055, -0.080029639759127
        ), doubleArrayOf(
            -0.025988002903175, 0.46612949273683, -0.56880970348453,
            -0.44824563336003, -0.030000490931808, 0.50663523727173
        ), doubleArrayOf(
            0.047284583258099, -0.26595723160738, 0.21032033434131,
            0.52986834914146, -0.52245334572957, -0.5736534757312
        ), doubleArrayOf(
            -0.31924244568277, -0.13888420092891, 0.30725800370737,
            0.49792332552544, 0.61035592292817, -0.40487771982263
        ), doubleArrayOf(
            0.038758575627018, -0.53813545398707, -0.56167256912901,
            0.46815373895572, -0.14142713486975, 0.39276248966752
        ), doubleArrayOf(
            -0.19936871608885, 0.12488860648831, -0.62990029833727,
            -0.29296146144627, 0.49734531468753, 0.46335923993672
        ), doubleArrayOf(
            -0.078826705546604, -0.15548800857414, 0.57456768467721,
            0.5558854465212, -0.56893054194692, -0.082408823513622
        ), doubleArrayOf(
            0.11678856295109, 0.53358760166951, 0.49302489382249,
            -0.53981846952046, -0.237913367643, -0.33251226509871
        ), doubleArrayOf(
            0.39126928439834, -0.39416116630681, -0.35778844984527,
            -0.39395609960567, 0.50270356681194, -0.39448759513757
        ), doubleArrayOf(
            -0.17961290695406, 0.34239532682819, -0.21870225043453,
            -0.23322835296688, 0.75997835134209, 0.41317237364121
        ), doubleArrayOf(
            0.29699501400111, 0.17195435585404, -0.34903627841034,
            -0.31751884057854, -0.59661546358767, 0.55102732418683
        ), doubleArrayOf(
            -0.2237291316445, -0.51254305965518, -0.31277318571798,
            0.54270199705442, -0.34885011313806, 0.41616819064585
        ), doubleArrayOf(
            0.53534023676892, 0.45905986582643, -0.20308675275303,
            0.019523641323632, 0.3378580580099, 0.58898336258938
        ), doubleArrayOf(
            -0.045038463119119, -0.52553334288797, -0.6098545897634,
            0.46226027841702, -0.36069029000651, 0.077984430434637
        ), doubleArrayOf(
            -0.40129033029845, 0.39526722066586, -0.20379584931963,
            0.45466492237669, 0.46504795737483, -0.46712669863522
        ), doubleArrayOf(
            -0.43845831945339, -0.59284534057943, 0.050241908216277,
            -0.36494839821973, 0.32363879325018, 0.46458051299488
        ), doubleArrayOf(
            -0.46057360356064, -0.34584626825548, -0.12264748451482,
            0.48835437094478, 0.21102526990984, 0.60843919401837
        ), doubleArrayOf(
            -0.086047549693024, -0.16981605114589, -0.37222833669973,
            0.45158609930017, -0.55710254634126, 0.55759406480139
        ), doubleArrayOf(
            0.54697451263099, -0.45070837355303, 0.032962522247893,
            -0.48584332140086, -0.28055687213837, 0.42642516953676
        ), doubleArrayOf(
            0.34061925303691, 0.38443007758012, 0.61614808332652,
            -0.55774172327958, -0.075660378162998, 0.19938218730551
        ), doubleArrayOf(
            0.30626924920956, -0.057939049897675, -0.10461119704504,
            -0.4395638756485, -0.57307193269415, 0.60849886616281
        ), doubleArrayOf(
            -0.52519951444608, -0.42567534157254, -0.19896500097138,
            0.48819483593271, 0.12539008064447, 0.49932157157064
        ), doubleArrayOf(
            -0.10173361116951, -0.07873850987854, 0.3713554090283,
            0.65889542748449, 0.63411890875068, 0.096414235519521
        ), doubleArrayOf(
            0.60342393773609, 0.057617370697663, 0.35558841250938,
            0.20766418929404, 0.030670189501999, -0.67974377143949
        ), doubleArrayOf(
            -0.071971052874019, -0.44567383014704, 0.65917594080871,
            0.44113802003588, -0.29627117199757, 0.28160739274962
        ), doubleArrayOf(
            0.38284479693596, 0.43552320173998, -0.4282368470258,
            -0.54809258921772, -0.27202273485667, 0.32551612927831
        ), doubleArrayOf(
            -0.74755699288716, -0.20979308948438, 0.19268299390085,
            0.27864013929953, -0.39085278833717, 0.36001727246301
        ), doubleArrayOf(
            -0.64575536737195, 0.59253747557756, 0.040885512266333,
            -0.20167391777406, -0.43481684011627, -0.02212841779644
        ), doubleArrayOf(
            0.45874103754271, -0.0066587566394561, -0.30494054091993,
            0.52731059172348, -0.64443887148677, 0.056264275617853
        ), doubleArrayOf(
            0.61573773369959, -0.00074622703454316, 0.25455659350429,
            0.30670278147618, -0.18573195942296, 0.65383825999316
        ), doubleArrayOf(
            -0.089919562456316, -0.28968403215216, -0.60618287937171,
            0.53370861364121, 0.37921556323246, -0.33450055738044
        ), doubleArrayOf(
            -0.47481167613763, 0.3899274103573, -0.1047963185367, 0.45545456567005,
            0.12142073778317, 0.62397625076847
        ), doubleArrayOf(
            0.59154225785278, -0.10812441303593, -0.4685834521013,
            -0.36007270807588, -0.1012374701199, 0.52812407295968
        ), doubleArrayOf(
            -0.01292122984647, -0.23607532114711, -0.57680411110671,
            -0.44955815301222, -0.31913443306122, -0.55448100298376
        ), doubleArrayOf(
            0.54231398466289, -0.31845386154668, -0.38636423612049,
            0.22187979539931, -0.6346425853783, -0.056599490898788
        ), doubleArrayOf(
            -0.41950690366157, -0.4578028963184, 0.31139813874057,
            0.39787962066193, -0.20885901240181, 0.56172180435883
        ), doubleArrayOf(
            -0.031404881097728, 0.56267475273157, -0.5556815383811,
            0.33075363850824, 0.39071115867626, 0.3340294973255
        ), doubleArrayOf(
            -0.51485161085589, -0.34037011091125, -0.46826090820473,
            -0.60086679836276, -0.075069409610657, 0.18202033570633
        ), doubleArrayOf(
            -0.49669644859095, 0.13236483793072, 0.53440735955877, 0.4720120049858,
            -0.05992551666341, -0.47306929861073
        ), doubleArrayOf(
            -0.32796852486185, 0.65593302097807, 0.20800030327303,
            -0.38965914824176, -0.51564565153044, -0.034636725857177
        ), doubleArrayOf(
            -0.30473794783797, 0.12584230588041, 0.63911213518179,
            0.11269477188219, 0.62944339013855, 0.27191006392352
        ), doubleArrayOf(
            -0.53642197294029, 0.50742224701512, -0.22907820767928,
            0.47022559371179, -0.1914125650624, 0.38019261684316
        ), doubleArrayOf(
            -0.28865425091309, 0.76169672032907, -0.36166127667225,
            -0.30555403321368, -0.12541657537884, -0.31081403770203
        ), doubleArrayOf(
            0.0025978417989835, 0.3737146483793, -0.3151511957077,
            0.62032810853005, 0.60524642517936, -0.09939888944988
        ), doubleArrayOf(
            -0.40019833530022, 0.15931480693456, -0.61653030345628,
            -0.49479441153976, -0.021517911098538, -0.43481713333933
        ), doubleArrayOf(
            -0.26445143166732, -0.48401155081335, 0.27737058096082,
            -0.12537486208624, -0.46956235249512, 0.61859207953377
        ), doubleArrayOf(
            -0.49776294425122, 0.6509513246149, -0.20147785800704,
            0.26022926925791, 0.39526195830317, -0.25288299425858
        ), doubleArrayOf(
            0.20792543895216, 0.6725599557329, 0.013296712014115,
            0.069082404776847, -0.37233547685047, 0.60070560947898
        ), doubleArrayOf(
            -0.60329265885108, 0.40708027238668, -0.17229997007444,
            -0.52997954496878, 0.22211745651394, -0.33229784433365
        ), doubleArrayOf(
            0.61826884506104, -0.62582169643111, 0.33820439950773,
            0.23870919720066, -0.20670655096227, -0.10953969425599
        ), doubleArrayOf(
            -0.63678168786213, -0.51101649337563, -0.19131817442969,
            -0.49493417544846, -0.22614515287593, 0.025828539221376
        ), doubleArrayOf(
            0.7068462559507, 0.072932806612059, -0.30827034359477,
            -0.52659704221432, -0.33954839093364, 0.086145323573817
        ), doubleArrayOf(
            -0.52429050496975, 0.39091424683727, 0.52819210715237,
            -0.16569162349745, 0.447191673089, 0.25667977984796
        ), doubleArrayOf(
            0.85033978527922, -0.37311666188152, -0.031585518143925,
            -0.063546921071094, -0.35026506762952, 0.099923633151172
        ), doubleArrayOf(
            -0.43149574251927, 0.16017753208259, -0.36624037246965,
            0.49372029676385, -0.60067103922455, 0.2223896202103
        ), doubleArrayOf(
            -0.43599537393092, -0.360658355506, -0.42475053011196,
            -0.52301759011739, 0.039454536357949, 0.47362064109658
        ), doubleArrayOf(
            -0.35793170214797, -0.43917817788312, -0.49072242572643,
            -0.32880277826743, -0.38509560837703, -0.42636724894184
        ), doubleArrayOf(
            -0.043679644403255, 0.74697226557232, -0.40732954428872,
            -0.48088968590275, 0.18029290312902, -0.10220931735307
        ), doubleArrayOf(
            -0.058902573502295, 0.0082595236590186, 0.7136596141971,
            -0.53043791172483, 0.22906331492979, 0.39155822265168
        ), doubleArrayOf(
            0.43459649233879, 0.18964470832196, 0.15217427204218, 0.59694624534505,
            0.053786588105393, 0.62671041756872
        ), doubleArrayOf(
            -0.48833575031057, 0.068909881680922, 0.60168404074737,
            -0.055455043023162, -0.62426261497771, -0.044461939113733
        ), doubleArrayOf(
            -0.71822145541427, 0.054494951105527, 0.25733756171599,
            -0.42706881935297, -0.44024663347316, 0.19687748949208
        ), doubleArrayOf(
            0.4723221071836, 0.63009683957253, 0.2166256995021, 0.31063720960745,
            0.079455887335627, 0.47974409023622
        ), doubleArrayOf(
            -0.39506538843406, 0.42517729990346, 0.29375773990216,
            0.044503633424429, -0.46173213926286, 0.60139575234582
        ), doubleArrayOf(
            -0.40354126620316, 0.41304136826673, -0.29533980868045,
            -0.45300699221804, 0.23702354154238, -0.56385297528377
        ), doubleArrayOf(
            -0.62315380378984, -0.42397903326965, 0.53044082394843,
            0.37874432092957, 0.054922713129263, 0.063952196248596
        ), doubleArrayOf(
            0.41959045692314, -0.83420441875842, -0.25505372502578,
            0.25012310515014, 0.010974237503127, 0.017675743681809
        ), doubleArrayOf(
            -0.25231575134089, -0.17034034508503, -0.0022254428444259,
            -0.4967771056787, 0.43184899693064, -0.68850194407078
        ), doubleArrayOf(
            -0.1852812882862, -0.48330898597592, 0.13528868642679,
            0.15202104844417, 0.57661281495368, -0.59848767913131
        ), doubleArrayOf(
            0.64287473226568, -0.30923674494923, 0.22234318117192,
            0.099248962994541, 0.64370450011427, 0.13206961744112
        ), doubleArrayOf(
            -0.49018899717866, 0.68654120859156, -0.27238863334662,
            -0.085832423495263, 0.44161945604453, 0.10856057983467
        ), doubleArrayOf(
            0.48795432482822, 0.42184193883513, -0.43797315744756,
            0.35186997012044, -0.46483432791096, 0.22857392808385
        ), doubleArrayOf(
            0.52970834834669, -0.50684486922008, -0.39782161731912,
            -0.3932709335414, -0.34863027587322, 0.16748196501934
        ), doubleArrayOf(
            -0.46048505533, -0.3887126918161, -0.68287320410729, -0.18448530888361,
            -0.25358256326157, 0.26870280714361
        ), doubleArrayOf(
            0.6889557358588, -0.3101022706485, -0.35882194962822, 0.30088738418801,
            -0.039139540883101, -0.45646277242166
        ), doubleArrayOf(
            -0.21954767479275, 0.40838837410593, 0.23284186868997,
            0.30349649888064, 0.57233263099925, 0.55778817953937
        ), doubleArrayOf(
            0.57731035290905, 0.091218309942656, 0.70670016667131,
            0.016358033634041, 0.3939245235472, -0.059352634867484
        ), doubleArrayOf(
            0.50055570130024, -0.021749790970703, 0.56767851040093,
            0.50580176326624, 0.34691320957643, 0.22478399991032
        ), doubleArrayOf(
            -0.37901911159632, 0.53804099887537, -0.46780195460858,
            0.51497346779204, -0.27981005467588, 0.067278440906787
        ), doubleArrayOf(
            0.67241900483514, 0.074099582737, 0.43138117954806, 0.054567519697911,
            -0.37927768894619, 0.45764946429346
        ), doubleArrayOf(
            0.14529189179172, -0.23854982910384, 0.45401647091062,
            0.25466539906731, 0.46182069803887, -0.66160446396375
        ), doubleArrayOf(
            -0.15570980059397, -0.38476787034627, 0.37322840954917,
            -0.43977613626294, -0.61243005550684, -0.34631643815896
        ), doubleArrayOf(
            -0.19590302894013, 0.42065974653653, 0.43447548638809,
            -0.10575548452794, 0.70439951675651, -0.29754920754254
        ), doubleArrayOf(
            -0.13558865796725, 0.1427073453776, 0.49647494823192,
            -0.65533234019218, -0.11714854214663, 0.5211321311867
        ), doubleArrayOf(
            -0.6228374766114, 0.20812698103217, -0.16205154548883,
            0.20384566967497, -0.59321895467652, 0.38604941246779
        ), doubleArrayOf(
            0.44487837128099, -0.37224943035393, -0.22188447638327,
            0.48921538939858, 0.41432418029434, -0.45087099253189
        ), doubleArrayOf(
            0.66422841315008, 0.21517761068003, 0.094012579794123,
            -0.4358159040875, 0.22245680154647, -0.51404116085847
        ), doubleArrayOf(
            -0.11369362736032, 0.32284689991698, -0.38818285117689,
            0.49680024166881, 0.047684866166158, -0.69503480904222
        ), doubleArrayOf(
            -0.5137200731924, -0.50673230867252, 0.32715252974108,
            -0.26799714004956, -0.47616510509846, 0.27153195326233
        ), doubleArrayOf(
            -0.47315177716491, -0.45711495983609, -0.31178280842352,
            -0.51697763052226, -0.14302372043059, -0.42689944315384
        ), doubleArrayOf(
            -0.050442035795027, 0.23609184251469, 0.38634880236106,
            0.56012774305243, 0.38963669840218, -0.57174382424149
        ), doubleArrayOf(
            -0.15472134925391, -0.15333579424307, -0.14189768300467,
            0.032279269476252, -0.66054298438621, -0.70360180527557
        ), doubleArrayOf(
            -0.10345191679557, -0.30503725808375, 0.31038263802383,
            0.36878846502877, -0.76824774853417, 0.2714830658427
        ), doubleArrayOf(
            -0.060212868606223, -0.4172755444983, 0.39199300681258,
            -0.44040104260082, 0.24955102139032, -0.64215903203727
        ), doubleArrayOf(
            0.25443195353315, -0.013789583113498, 0.44365000614699,
            0.53296203342425, -0.55057750350733, -0.38867053403178
        ), doubleArrayOf(
            -0.36068564301268, -0.65616661625162, -0.48495997865466,
            0.24088316031012, -0.18080297655217, -0.33682435258394
        ), doubleArrayOf(
            -0.53824550487673, -0.096728907851005, -0.5208619866167,
            0.33195321221408, -0.032263947064791, 0.56427315050798
        ), doubleArrayOf(
            0.40151657866643, -0.44825725748635, -0.54910020122855,
            -0.095936272447708, 0.5719563905078, 0.00097783623607218
        ), doubleArrayOf(
            0.21961099467771, 0.62823723408945, -0.010045934028323,
            -0.6610564872634, -0.17161595423903, -0.30089924032373
        ), doubleArrayOf(
            0.27961471530636, 0.054523395513076, 0.61485903249347,
            0.11958885677663, -0.61032561244673, -0.39241856813031
        ), doubleArrayOf(
            -0.30223065341134, -0.23605925177166, -0.09697276975263,
            -0.46458104180761, -0.37853464945647, 0.69599203908657
        ), doubleArrayOf(
            0.0023635513043496, 0.62702100484886, 0.49658954056984,
            -0.20369645124455, -0.56457560315907, 0.00021299797811461
        ), doubleArrayOf(
            -0.64198493892962, 0.59676262320476, 0.46274573284143,
            0.088421912306785, 0.098029994490406, -0.012953072012707
        ), doubleArrayOf(
            -0.053965435026011, 0.13439533803278, -0.33103493780685,
            0.55991756423782, -0.58127599631056, -0.46696041830103
        ), doubleArrayOf(
            -0.43965993689353, 0.07544961763381, 0.1509639518808,
            -0.38868406689028, -0.0033436054452783, -0.79191533434483
        ), doubleArrayOf(
            -0.21743914630025, -0.32019630124298, -0.56067107727615,
            0.027284914419519, -0.49444926389798, -0.53908992599417
        ), doubleArrayOf(
            -0.36492599248168, 0.52529904803377, 0.18002253442693,
            0.14829474115897, 0.17212619314998, -0.71194315827942
        ), doubleArrayOf(
            0.0051876209353066, 0.50490293404098, 0.24361032552454,
            0.13688117617809, -0.61381291176911, -0.5386997104485
        ), doubleArrayOf(
            0.66421180843392, 0.21833854629637, -0.087909936660014,
            0.15624552502148, -0.68780724971724, 0.077015056461268
        ), doubleArrayOf(
            0.52710630558705, -0.42143671471468, -0.069964559463205,
            -0.24196341534187, -0.68814841622245, 0.08695091377684
        ), doubleArrayOf(
            0.62392249806692, -0.23663281560035, -0.59058622185178,
            0.22685863859977, -0.36683948058558, -0.14105848121323
        ), doubleArrayOf(
            0.18069852004855, -0.083828559172887, 0.66240167877879,
            0.16722813432165, -0.25503640214793, -0.65462662498637
        ), doubleArrayOf(
            -0.37112528006203, 0.43100319401562, -0.11342774633614,
            0.14418808646988, 0.5753326931164, 0.55842502411684
        ), doubleArrayOf(
            0.55378724068611, 0.21098160548047, -0.3224976646632, 0.31268307369255,
            -0.37624695517597, -0.55269271266764
        ), doubleArrayOf(
            0.2601465870231, 0.56373458886982, -0.21638357910201, 0.41216916619413,
            -0.25078072187299, -0.57873208070982
        ), doubleArrayOf(
            0.11217864148346, 0.54196554704815, -0.31989128683717,
            0.54691221598945, 0.24062434044524, 0.48409277788476
        ), doubleArrayOf(
            0.087564423746579, -0.12083081671284, 0.69931172084498,
            0.35220575672909, 0.28770484569954, -0.53091668762919
        ), doubleArrayOf(
            0.3395702120398, 0.042520943289575, -0.30935928261896,
            0.61022210846475, 0.54650816974112, 0.34079124619266
        ), doubleArrayOf(
            0.32746112891934, 0.32095220193351, -0.61142534799442,
            0.32197324480666, -0.38236071343678, 0.40749411210419
        ), doubleArrayOf(
            0.58741915356593, -0.30916030490652, -0.57642977381104,
            -0.038846190358607, 0.047926713761208, -0.4725265742377
        ), doubleArrayOf(
            0.026224389898652, 0.031768907187292, -0.12510902263321,
            0.36102734397001, -0.72217212865059, 0.57513252722531
        ), doubleArrayOf(
            -0.27510374152496, -0.5153402145828, 0.025774022629799,
            0.59201067073603, 0.40728366085253, -0.37645913420642
        ), doubleArrayOf(
            -0.29983338495183, -0.61017291361195, -0.18551919513643,
            0.50515945610161, 0.18206593801497, -0.46372136367049
        ), doubleArrayOf(
            -0.64290893575119, -0.34887011406157, -0.55318606770362,
            -0.21230198963112, -0.19828983785672, 0.2730419816548
        ), doubleArrayOf(
            -0.32778879906348, -0.094317293167129, 0.57811170538439,
            0.54346692190204, 0.17699503497579, -0.47197676839855
        ), doubleArrayOf(
            -0.075738705663962, 0.53381750682665, -0.13406342524856,
            0.71765386263773, 0.34271060834977, 0.24259408122628
        ), doubleArrayOf(
            -0.30574273227855, 0.17419449782542, -0.78861555508124,
            0.43305678368813, 0.064853328282818, 0.25003806266734
        ), doubleArrayOf(
            0.4397035983709, -0.51651518914239, -0.3972346186176,
            -0.34513492086703, 0.32129829777342, -0.39965829527563
        ), doubleArrayOf(
            -0.25184899643619, -0.35937572373004, 0.15273239148905,
            -0.51640931868766, 0.4218715745627, -0.58261460582976
        ), doubleArrayOf(
            -0.57396000790758, 0.1912786199605, 0.45995634753032,
            -0.43664716984512, 0.4601630113166, 0.14146310231856
        ), doubleArrayOf(
            0.11500068018889, 0.05112652754666, -0.25672855859366,
            -0.54715738035577, 0.67669928552409, 0.40118355777989
        ), doubleArrayOf(
            -0.45252668004418, -0.40809988524453, -0.064931545867856,
            0.19116562077283, 0.76523014995576, 0.048337406798767
        ), doubleArrayOf(
            -0.080075651760374, 0.75305314115418, 0.34797424409913,
            0.29104493928016, 0.0040185919664457, -0.46977598520425
        ), doubleArrayOf(
            -0.3890257668276, 0.49100041230416, -0.17812126809985,
            -0.43787557151231, -0.46923187878333, 0.40489108352503
        ), doubleArrayOf(
            0.37433236324043, -0.29441766760791, -0.066285137006724,
            0.33217472508825, 0.73917165688328, 0.33479099915638
        ), doubleArrayOf(
            -0.02973230696179, -0.51371026289118, 0.34133522703692,
            -0.41361792362786, -0.51561746819514, -0.4263412462482
        ), doubleArrayOf(
            0.51057171220039, -0.23740201245544, 0.26673587003088, 0.5521767379032,
            0.16849318602455, 0.52774964064755
        )
    )

    @JvmField
    val whitenoiseLUT = doubleArrayOf(
        -0.714286, 0.301587, 0.333333, -1.0, 0.396825, -0.0793651, -0.968254, -0.047619, 0.301587, -0.111111,
        0.015873, 0.968254, -0.428571, 0.428571, 0.047619, 0.84127, -0.015873,
        -0.746032, -0.809524, -0.619048, -0.301587, -0.68254, 0.777778, 0.365079,
        -0.460317, 0.714286, 0.142857, 0.047619, -0.0793651, -0.492063,
        -0.873016, -0.269841, -0.84127, -0.809524, -0.396825, -0.777778,
        -0.396825, -0.746032, 0.301587, -0.52381, 0.650794, 0.301587, -0.015873,
        0.269841, 0.492063, -0.936508, -0.777778, 0.555556, 0.68254, -0.650794,
        -0.968254, 0.619048, 0.777778, 0.68254, 0.206349, -0.555556, 0.904762,
        0.587302, -0.174603, -0.047619, -0.206349, -0.68254, 0.111111, -0.52381,
        0.174603, -0.968254, -0.111111, -0.238095, 0.396825, -0.777778,
        -0.206349, 0.142857, 0.904762, -0.111111, -0.269841, 0.777778, -0.015873,
        -0.047619, -0.333333, 0.68254, -0.238095, 0.904762, 0.0793651, 0.68254,
        -0.301587, -0.333333, 0.206349, 0.52381, 0.904762, -0.015873, -0.555556,
        0.396825, 0.460317, -0.142857, 0.587302, 1.0, -0.650794, -0.333333,
        -0.365079, 0.015873, -0.873016, -1.0, -0.777778, 0.174603, -0.84127,
        -0.428571, 0.365079, -0.587302, -0.587302, 0.650794, 0.714286, 0.84127,
        0.936508, 0.746032, 0.047619, -0.52381, -0.714286, -0.746032, -0.206349,
        -0.301587, -0.174603, 0.460317, 0.238095, 0.968254, 0.555556, -0.269841,
        0.206349, -0.0793651, 0.777778, 0.174603, 0.111111, -0.714286, -0.84127,
        -0.68254, 0.587302, 0.746032, -0.68254, 0.587302, 0.365079, 0.492063,
        -0.809524, 0.809524, -0.873016, -0.142857, -0.142857, -0.619048,
        -0.873016, -0.587302, 0.0793651, -0.269841, -0.460317, -0.904762,
        -0.174603, 0.619048, 0.936508, 0.650794, 0.238095, 0.111111, 0.873016,
        0.0793651, 0.460317, -0.746032, -0.460317, 0.428571, -0.714286,
        -0.365079, -0.428571, 0.206349, 0.746032, -0.492063, 0.269841, 0.269841,
        -0.365079, 0.492063, 0.873016, 0.142857, 0.714286, -0.936508, 1.0,
        -0.142857, -0.904762, -0.301587, -0.968254, 0.619048, 0.269841,
        -0.809524, 0.936508, 0.714286, 0.333333, 0.428571, 0.0793651, -0.650794,
        0.968254, 0.809524, 0.492063, 0.555556, -0.396825, -1.0, -0.492063,
        -0.936508, -0.492063, -0.111111, 0.809524, 0.333333, 0.238095, 0.174603,
        0.333333, 0.873016, 0.809524, -0.047619, -0.619048, -0.174603, 0.84127,
        0.111111, 0.619048, -0.0793651, 0.52381, 1.0, 0.015873, 0.52381, -0.619048,
        -0.52381, 1.0, 0.650794, -0.428571, 0.84127, -0.555556, 0.015873, 0.428571,
        0.746032, -0.238095, -0.238095, 0.936508, -0.206349, -0.936508, 0.873016,
        -0.555556, -0.650794, -0.904762, 0.52381, 0.968254, -0.333333, -0.904762,
        0.396825, 0.047619, -0.84127, -0.365079, -0.587302, -1.0, -0.396825,
        0.365079, 0.555556, 0.460317, 0.142857, -0.460317, 0.238095
    )

}
