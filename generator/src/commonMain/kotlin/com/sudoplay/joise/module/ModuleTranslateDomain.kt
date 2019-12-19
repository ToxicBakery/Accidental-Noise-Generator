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

class ModuleTranslateDomain : SourcedModule() {

    var ax: ScalarParameter = ScalarParameter(0.0)
    var ay: ScalarParameter = ScalarParameter(0.0)
    var az: ScalarParameter = ScalarParameter(0.0)
    var aw: ScalarParameter = ScalarParameter(0.0)
    var au: ScalarParameter = ScalarParameter(0.0)
    var av: ScalarParameter = ScalarParameter(0.0)

    fun setAxisXSource(source: Double) {
        ax.value = source
    }

    fun setAxisYSource(source: Double) {
        ay.value = source
    }

    fun setAxisZSource(source: Double) {
        az.value = source
    }

    fun setAxisWSource(source: Double) {
        aw.value = source
    }

    fun setAxisUSource(source: Double) {
        au.value = source
    }

    fun setAxisVSource(source: Double) {
        av.value = source
    }

    fun setAxisXSource(source: Module) {
        ax.module = source
    }

    fun setAxisYSource(source: Module) {
        ay.module = source
    }

    fun setAxisZSource(source: Module) {
        az.module = source
    }

    fun setAxisWSource(source: Module) {
        aw.module = source
    }

    fun setAxisUSource(source: Module) {
        au.module = source
    }

    fun setAxisVSource(source: Module) {
        av.module = source
    }

    override fun get(x: Double, y: Double): Double = source[x + ax[x, y], y + ay[x, y]]

    override fun get(x: Double, y: Double, z: Double): Double =
        source[x + ax[x, y, z], y + ay[x, y, z], z + az[x, y, z]]

    override fun get(x: Double, y: Double, z: Double, w: Double): Double =
        source[x + ax[x, y, z, w], y + ay[x, y, z, w], z + az[x, y, z, w], w + aw[x, y, z, w]]

    override fun get(
        x: Double,
        y: Double,
        z: Double,
        w: Double,
        u: Double,
        v: Double
    ): Double = source[
            x + ax[x, y, z, w, u, v],
            y + ay[x, y, z, w, u, v],
            z + az[x, y, z, w, u, v],
            w + aw[x, y, z, w, u, v],
            u + au[x, y, z, w, u, v],
            v + av[x, y, z, w, u, v]
    ]

}
