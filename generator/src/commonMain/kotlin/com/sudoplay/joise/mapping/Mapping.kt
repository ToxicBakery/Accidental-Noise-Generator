@file:Suppress("LocalVariableName", "ReturnCount")

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

import com.sudoplay.joise.module.Module
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

object Mapping {

    private const val TWO_PI = PI * 2.0

    fun map2D(
        mode: MappingMode?, width: Int, height: Int, m: Module,
        range: MappingRange, writer: Mapping2DWriter?,
        listener: MappingUpdateListener?, z: Double
    ) {
        var writer1 = writer
        var listener1 = listener
        if (writer1 == null) {
            writer1 = Mapping2DWriter.NULL_WRITER
        }
        if (listener1 == null) {
            listener1 = MappingUpdateListener.NULL_LISTENER
        }
        var p: Double
        var q: Double
        var nx: Double
        var ny: Double
        var nz: Double
        var nw: Double
        var nu: Double
        var nv: Double
        val r: Double
        val zval: Double
        val dx1: Double
        val dy1: Double
        val dz: Double
        val dx_div_2pi: Double
        val dy_div_2pi: Double
        val dz_div_2pi: Double
        val iw = 1.0 / width.toDouble()
        val ih = 1.0 / height.toDouble()
        val total = width * height.toDouble()
        var current = 0.0
        when (mode) {
            MappingMode.NORMAL -> {
                dx1 = range.map1.x - range.map0.x
                dy1 = range.map1.y - range.map0.y
                var x = 0
                while (x < width) {
                    var y = 0
                    while (y < height) {
                        p = x.toDouble() * iw
                        q = y.toDouble() * ih
                        nx = range.map0.x + p * dx1
                        ny = range.map0.y + q * dy1
                        nz = z
                        writer1.write(x, y, m[nx, ny, nz])
                        listener1.update(++current, total)
                        y++
                    }
                    x++
                }
                return
            }
            MappingMode.SEAMLESS_X -> {
                dx1 = range.loop1.x - range.loop0.x
                dy1 = range.map1.y - range.map0.y
                dx_div_2pi = dx1 / TWO_PI
                var x = 0
                while (x < width) {
                    var y = 0
                    while (y < height) {
                        p = x.toDouble() * iw
                        q = y.toDouble() * ih
                        p = p * (range.map1.x - range.map0.x) / dx1
                        nx = range.loop0.x + cos(p * TWO_PI) * dx_div_2pi
                        ny = range.loop0.x + sin(p * TWO_PI) * dx_div_2pi
                        nz = range.map0.y + q * dy1
                        nw = z
                        writer1.write(x, y, m[nx, ny, nz, nw])
                        listener1.update(++current, total)
                        y++
                    }
                    x++
                }
                return
            }
            MappingMode.SEAMLESS_Y -> {
                dx1 = range.map1.x - range.map0.x
                dy1 = range.loop1.y - range.loop0.y
                dy_div_2pi = dy1 / TWO_PI
                var x = 0
                while (x < width) {
                    var y = 0
                    while (y < height) {
                        p = x.toDouble() * iw
                        q = y.toDouble() * ih
                        q = q * (range.map1.y - range.map0.y) / dy1
                        nx = range.map0.x + p * dx1
                        ny = range.loop0.y + cos(q * TWO_PI) * dy_div_2pi
                        nz = range.loop0.y + sin(q * TWO_PI) * dy_div_2pi
                        nw = z
                        writer1.write(x, y, m[nx, ny, nz, nw])
                        listener1.update(++current, total)
                        y++
                    }
                    x++
                }
                return
            }
            MappingMode.SEAMLESS_Z -> {
                dx1 = range.map1.x - range.map0.x
                //dy1 = range.map1.y - range.map0.y
                dz = range.loop1.z - range.loop0.z
                r = (z - range.map0.z) / (range.map1.z - range.map0.z)
                zval = r * (range.map1.z - range.map0.z) / dz
                dz_div_2pi = dz / TWO_PI
                var x = 0
                while (x < width) {
                    var y = 0
                    while (y < height) {
                        p = x.toDouble() * iw
                        //q = y.toDouble() * ih
                        nx = range.map0.x + p * dx1
                        ny = range.map0.y + p * dx1
                        nz = range.loop0.z + cos(zval * TWO_PI) * dz_div_2pi
                        nw = range.loop0.z + sin(zval * TWO_PI) * dz_div_2pi
                        writer1.write(x, y, m[nx, ny, nz, nw])
                        listener1.update(++current, total)
                        y++
                    }
                    x++
                }
                return
            }
            MappingMode.SEAMLESS_XY -> {
                dx1 = range.loop1.x - range.loop0.x
                dy1 = range.loop1.y - range.loop0.y
                dx_div_2pi = dx1 / TWO_PI
                dy_div_2pi = dy1 / TWO_PI
                var x = 0
                while (x < width) {
                    var y = 0
                    while (y < height) {
                        p = x.toDouble() * iw
                        q = y.toDouble() * ih
                        p = p * (range.map1.x - range.map0.x) / dx1
                        q = q * (range.map1.y - range.map0.y) / dy1
                        nx = range.loop0.x + cos(p * TWO_PI) * dx_div_2pi
                        ny = range.loop0.x + sin(p * TWO_PI) * dx_div_2pi
                        nz = range.loop0.y + cos(q * TWO_PI) * dy_div_2pi
                        nw = range.loop0.y + sin(q * TWO_PI) * dy_div_2pi
                        nu = z
                        nv = 0.0
                        writer1.write(x, y, m[nx, ny, nz, nw, nu, nv])
                        listener1.update(++current, total)
                        y++
                    }
                    x++
                }
                return
            }
            MappingMode.SEAMLESS_XZ -> {
                dx1 = range.loop1.x - range.loop0.x
                dy1 = range.map1.y - range.map0.y
                dz = range.loop1.z - range.loop0.z
                dx_div_2pi = dx1 / TWO_PI
                dz_div_2pi = dz / TWO_PI
                r = (z - range.map0.z) / (range.map1.z - range.map0.z)
                zval = r * (range.map1.z - range.map0.z) / dz
                var x = 0
                while (x < width) {
                    var y = 0
                    while (y < height) {
                        p = x.toDouble() * iw
                        q = y.toDouble() * ih
                        p = p * (range.map1.x - range.map0.x) / dz
                        nx = range.loop0.x + cos(p * TWO_PI) * dx_div_2pi
                        ny = range.loop0.x + sin(p * TWO_PI) * dx_div_2pi
                        nz = range.map0.y + q * dy1
                        nw = range.loop0.z + cos(zval * TWO_PI) * dz_div_2pi
                        nu = range.loop0.z + sin(zval * TWO_PI) * dz_div_2pi
                        nv = 0.0
                        writer1.write(x, y, m[nx, ny, nz, nw, nu, nv])
                        listener1.update(++current, total)
                        y++
                    }
                    x++
                }
                return
            }
            MappingMode.SEAMLESS_YZ -> {
                dx1 = range.map1.x - range.map0.x
                dy1 = range.loop1.y - range.loop0.y
                dz = range.loop1.z - range.loop0.z
                dy_div_2pi = dy1 / TWO_PI
                dz_div_2pi = dz / TWO_PI
                r = (z - range.map0.z) / (range.map1.z - range.map0.z)
                zval = r * (range.map1.z - range.map0.z) / dz
                var x = 0
                while (x < width) {
                    var y = 0
                    while (y < height) {
                        p = x.toDouble() * iw
                        q = y.toDouble() * ih
                        q = q * (range.map1.y - range.map0.y) / dy1
                        nx = range.map0.x + p * dx1
                        ny = range.loop0.y + cos(q * TWO_PI) * dy_div_2pi
                        nz = range.loop0.y + sin(q * TWO_PI) * dy_div_2pi
                        nw = range.loop0.z + cos(zval * TWO_PI) * dz_div_2pi
                        nu = range.loop0.z + sin(zval * TWO_PI) * dz_div_2pi
                        nv = 0.0
                        writer1.write(x, y, m[nx, ny, nz, nw, nu, nv])
                        listener1.update(++current, total)
                        y++
                    }
                    x++
                }
                return
            }
            MappingMode.SEAMLESS_XYZ -> {
                dx1 = range.loop1.x - range.loop0.x
                dy1 = range.loop1.y - range.loop0.y
                dz = range.loop1.z - range.loop0.z
                dx_div_2pi = dx1 / TWO_PI
                dy_div_2pi = dy1 / TWO_PI
                dz_div_2pi = dz / TWO_PI
                r = (z - range.map0.z) / (range.map1.z - range.map0.z)
                zval = r * (range.map1.z - range.map0.z) / dz
                var x = 0
                while (x < width) {
                    var y = 0
                    while (y < height) {
                        p = x.toDouble() * iw
                        q = y.toDouble() * ih
                        p = p * (range.map1.x - range.map0.x) / dx1
                        q = q * (range.map1.y - range.map0.y) / dy1
                        nx = range.loop0.x + cos(p * TWO_PI) * dx_div_2pi
                        ny = range.loop0.x + sin(p * TWO_PI) * dx_div_2pi
                        nz = range.loop0.y + cos(q * TWO_PI) * dy_div_2pi
                        nw = range.loop0.y + sin(q * TWO_PI) * dy_div_2pi
                        nu = range.loop0.z + cos(zval * TWO_PI) * dz_div_2pi
                        nv = range.loop0.z + sin(zval * TWO_PI) * dz_div_2pi
                        writer1.write(x, y, m[nx, ny, nz, nw, nu, nv])
                        listener1.update(++current, total)
                        y++
                    }
                    x++
                }
                return
            }
            else -> throw AssertionError()
        }
    }

    fun map2DNoZ(
        mode: MappingMode, width: Int, height: Int,
        m: Module, range: MappingRange, writer: Mapping2DWriter?,
        listener: MappingUpdateListener?
    ) {
        var writer1 = writer
        var listener1 = listener
        if (writer1 == null) {
            writer1 = Mapping2DWriter.NULL_WRITER
        }
        if (listener1 == null) {
            listener1 = MappingUpdateListener.NULL_LISTENER
        }
        var p: Double
        var q: Double
        var nx: Double
        var ny: Double
        var nz: Double
        var nw: Double
        val dx: Double
        val dy: Double
        val dx_div_2pi: Double
        val dy_div_2pi: Double
        val iw = 1.0 / width.toDouble()
        val ih = 1.0 / height.toDouble()
        val total = width * height.toDouble()
        var current = 0.0
        when (mode) {
            MappingMode.NORMAL -> {
                dx = range.map1.x - range.map0.x
                dy = range.map1.y - range.map0.y
                var x = 0
                while (x < width) {
                    var y = 0
                    while (y < height) {
                        p = x.toDouble() * iw
                        q = y.toDouble() * ih
                        nx = range.map0.x + p * dx
                        ny = range.map0.y + q * dy
                        writer1.write(x, y, m[nx, ny])
                        listener1.update(++current, total)
                        y++
                    }
                    x++
                }
                return
            }
            MappingMode.SEAMLESS_X -> {
                dx = range.loop1.x - range.loop0.x
                dy = range.map1.y - range.map0.y
                dx_div_2pi = dx / TWO_PI
                var x = 0
                while (x < width) {
                    var y = 0
                    while (y < height) {
                        p = x.toDouble() * iw
                        q = y.toDouble() * ih
                        p = p * (range.map1.x - range.map0.x) / dx
                        nx = range.loop0.x + cos(p * TWO_PI) * dx_div_2pi
                        ny = range.loop0.x + sin(p * TWO_PI) * dx_div_2pi
                        nz = range.map0.y + q * dy
                        writer1.write(x, y, m[nx, ny, nz])
                        listener1.update(++current, total)
                        y++
                    }
                    x++
                }
                return
            }
            MappingMode.SEAMLESS_Y -> {
                dx = range.map1.x - range.map0.x
                dy = range.loop1.y - range.loop0.y
                dy_div_2pi = dy / TWO_PI
                var x = 0
                while (x < width) {
                    var y = 0
                    while (y < height) {
                        p = x.toDouble() * iw
                        q = y.toDouble() * ih
                        q = q * (range.map1.y - range.map0.y) / dy
                        nx = range.map0.x + p * dx
                        ny = range.loop0.y + cos(q * TWO_PI) * dy_div_2pi
                        nz = range.loop0.y + sin(q * TWO_PI) * dy_div_2pi
                        writer1.write(x, y, m[nx, ny, nz])
                        listener1.update(++current, total)
                        y++
                    }
                    x++
                }
                return
            }
            MappingMode.SEAMLESS_XY -> {
                dx = range.loop1.x - range.loop0.x
                dy = range.loop1.y - range.loop0.y
                dx_div_2pi = dx / TWO_PI
                dy_div_2pi = dy / TWO_PI
                var x = 0
                while (x < width) {
                    var y = 0
                    while (y < height) {
                        p = x.toDouble() * iw
                        q = y.toDouble() * ih
                        p = p * (range.map1.x - range.map0.x) / dx
                        q = q * (range.map1.y - range.map0.y) / dy
                        nx = range.loop0.x + cos(p * TWO_PI) * dx_div_2pi
                        ny = range.loop0.x + sin(p * TWO_PI) * dx_div_2pi
                        nz = range.loop0.y + cos(q * TWO_PI) * dy_div_2pi
                        nw = range.loop0.y + sin(q * TWO_PI) * dy_div_2pi
                        writer1.write(x, y, m[nx, ny, nz, nw])
                        listener1.update(++current, total)
                        y++
                    }
                    x++
                }
                return
            }
            MappingMode.SEAMLESS_Z,
            MappingMode.SEAMLESS_XZ,
            MappingMode.SEAMLESS_YZ,
            MappingMode.SEAMLESS_XYZ -> throw UnsupportedOperationException(mode.toString())
            else -> throw AssertionError()
        }
    }

    fun map3D(
        mode: MappingMode?, width: Int, height: Int, depth: Int,
        m: Module, range: MappingRange, writer: Mapping3DWriter?,
        listener: MappingUpdateListener?
    ) {
        var writer1 = writer
        var listener1 = listener
        if (writer1 == null) {
            writer1 = Mapping3DWriter.NULL_WRITER
        }
        if (listener1 == null) {
            listener1 = MappingUpdateListener.NULL_LISTENER
        }
        var p: Double
        var q: Double
        var r: Double
        var nx: Double
        var ny: Double
        var nz: Double
        var nw: Double
        var nu: Double
        var nv: Double
        val dx: Double
        val dy: Double
        val dz: Double
        val dx_div_2pi: Double
        val dy_div_2pi: Double
        val dz_div_2pi: Double
        val iw = 1.0 / width.toDouble()
        val ih = 1.0 / height.toDouble()
        val id = 1.0 / depth.toDouble()
        val total = width * height * depth.toDouble()
        var current = 0.0
        when (mode) {
            MappingMode.NORMAL -> {
                dx = range.map1.x - range.map0.x
                dy = range.map1.y - range.map0.y
                dz = range.map1.z - range.map0.z
                var x = 0
                while (x < width) {
                    var y = 0
                    while (y < height) {
                        var z = 0
                        while (z < depth) {
                            p = x.toDouble() * iw
                            q = y.toDouble() * ih
                            r = z.toDouble() * id
                            nx = range.map0.x + p * dx
                            ny = range.map0.y + q * dy
                            nz = range.map0.z + r * dz
                            writer1.write(x, y, z, m[nx, ny, nz])
                            listener1.update(++current, total)
                            z++
                        }
                        y++
                    }
                    x++
                }
                return
            }
            MappingMode.SEAMLESS_X -> {
                dx = range.loop1.x - range.loop0.x
                dy = range.map1.y - range.map0.y
                dz = range.map1.z - range.map0.z
                dx_div_2pi = dx / TWO_PI
                var x = 0
                while (x < width) {
                    var y = 0
                    while (y < height) {
                        var z = 0
                        while (z < depth) {
                            p = x.toDouble() * iw
                            q = y.toDouble() * ih
                            r = z.toDouble() * id
                            p = p * (range.map1.x - range.map0.x) / dx
                            nx = range.loop0.x + cos(p * TWO_PI) * dx_div_2pi
                            ny = range.loop0.x + sin(p * TWO_PI) * dx_div_2pi
                            nz = range.map0.y + q * dy
                            nw = range.map0.z + r * dz
                            writer1.write(x, y, z, m[nx, ny, nz, nw])
                            listener1.update(++current, total)
                            z++
                        }
                        y++
                    }
                    x++
                }
                return
            }
            MappingMode.SEAMLESS_Y -> {
                dx = range.map1.x - range.map0.x
                dy = range.loop1.y - range.loop0.y
                dz = range.map1.z - range.map0.z
                dy_div_2pi = dy / TWO_PI
                var x = 0
                while (x < width) {
                    var y = 0
                    while (y < height) {
                        var z = 0
                        while (z < depth) {
                            p = x.toDouble() * iw
                            q = y.toDouble() * ih
                            r = z.toDouble() * id
                            q = q * (range.map1.y - range.map0.y) / dy
                            nx = range.map0.x + p * dx
                            ny = range.loop0.y + cos(q * TWO_PI) * dy_div_2pi
                            nz = range.loop0.y + sin(q * TWO_PI) * dy_div_2pi
                            nw = range.map0.z + r * dz
                            writer1.write(x, y, z, m[nx, ny, nz, nw])
                            listener1.update(++current, total)
                            z++
                        }
                        y++
                    }
                    x++
                }
                return
            }
            MappingMode.SEAMLESS_Z -> {
                dx = range.map1.x - range.map0.x
                dy = range.map1.y - range.map0.y
                dz = range.loop1.z - range.loop0.z
                dz_div_2pi = dz / TWO_PI
                var x = 0
                while (x < width) {
                    var y = 0
                    while (y < height) {
                        var z = 0
                        while (z < depth) {
                            p = x.toDouble() * iw
                            q = y.toDouble() * ih
                            r = z.toDouble() * id
                            r = r * (range.map1.z - range.map0.z) / dz
                            nx = range.map0.x + p * dx
                            ny = range.map0.y + q * dy
                            nz = range.loop0.z + cos(r * TWO_PI) * dz_div_2pi
                            nw = range.loop0.z + sin(r * TWO_PI) * dz_div_2pi
                            writer1.write(x, y, z, m[nx, ny, nz, nw])
                            listener1.update(++current, total)
                            z++
                        }
                        y++
                    }
                    x++
                }
                return
            }
            MappingMode.SEAMLESS_XY -> {
                dx = range.loop1.x - range.loop0.x
                dy = range.loop1.y - range.loop0.y
                dz = range.map1.z - range.map0.z
                dx_div_2pi = dx / TWO_PI
                dy_div_2pi = dy / TWO_PI
                var x = 0
                while (x < width) {
                    var y = 0
                    while (y < height) {
                        var z = 0
                        while (z < depth) {
                            p = x.toDouble() * iw
                            q = y.toDouble() * ih
                            r = z.toDouble() * id
                            p = p * (range.map1.x - range.map0.x) / dx
                            q = q * (range.map1.y - range.map0.y) / dy
                            nx = range.loop0.x + cos(p * TWO_PI) * dx_div_2pi
                            ny = range.loop0.x + sin(p * TWO_PI) * dx_div_2pi
                            nz = range.loop0.y + cos(q * TWO_PI) * dy_div_2pi
                            nw = range.loop0.y + sin(q * TWO_PI) * dy_div_2pi
                            nu = range.map0.z + r * dz
                            nv = 0.0
                            writer1.write(x, y, z, m[nx, ny, nz, nw, nu, nv])
                            listener1.update(++current, total)
                            z++
                        }
                        y++
                    }
                    x++
                }
                return
            }
            MappingMode.SEAMLESS_XZ -> {
                dx = range.loop1.x - range.loop0.x
                dy = range.map1.y - range.map0.y
                dz = range.loop1.z - range.loop0.z
                dx_div_2pi = dx / TWO_PI
                dz_div_2pi = dz / TWO_PI
                var x = 0
                while (x < width) {
                    var y = 0
                    while (y < height) {
                        var z = 0
                        while (z < depth) {
                            p = x.toDouble() * iw
                            q = y.toDouble() * ih
                            r = z.toDouble() * id
                            p = p * (range.map1.x - range.map0.x) / dx
                            r = r * (range.map1.z - range.map0.z) / dz
                            nx = range.loop0.x + cos(p * TWO_PI) * dx_div_2pi
                            ny = range.loop0.x + sin(p * TWO_PI) * dx_div_2pi
                            nz = range.map0.y + q * dy
                            nw = range.loop0.z + cos(r * TWO_PI) * dz_div_2pi
                            nu = range.loop0.z + sin(r * TWO_PI) * dz_div_2pi
                            nv = 0.0
                            writer1.write(x, y, z, m[nx, ny, nz, nw, nu, nv])
                            listener1.update(++current, total)
                            z++
                        }
                        y++
                    }
                    x++
                }
                return
            }
            MappingMode.SEAMLESS_YZ -> {
                dx = range.map1.x - range.map0.x
                dy = range.loop1.y - range.loop0.y
                dz = range.loop1.z - range.loop0.z
                dy_div_2pi = dy / TWO_PI
                dz_div_2pi = dz / TWO_PI
                var x = 0
                while (x < width) {
                    var y = 0
                    while (y < height) {
                        var z = 0
                        while (z < depth) {
                            p = x.toDouble() * iw
                            q = y.toDouble() * ih
                            r = z.toDouble() * id
                            q = q * (range.map1.y - range.map0.y) / dy
                            r = r * (range.map1.z - range.map0.z) / dz
                            nx = range.map0.x + p * dx
                            ny = range.loop0.y + cos(q * TWO_PI) * dy_div_2pi
                            nz = range.loop0.y + sin(q * TWO_PI) * dy_div_2pi
                            nw = range.loop0.z + cos(r * TWO_PI) * dz_div_2pi
                            nu = range.loop0.z + sin(r * TWO_PI) * dz_div_2pi
                            nv = 0.0
                            writer1.write(x, y, z, m[nx, ny, nz, nw, nu, nv])
                            listener1.update(++current, total)
                            z++
                        }
                        y++
                    }
                    x++
                }
                return
            }
            MappingMode.SEAMLESS_XYZ -> {
                dx = range.loop1.x - range.loop0.x
                dy = range.loop1.y - range.loop0.y
                dz = range.loop1.z - range.loop0.z
                dx_div_2pi = dx / TWO_PI
                dy_div_2pi = dy / TWO_PI
                dz_div_2pi = dz / TWO_PI
                var x = 0
                while (x < width) {
                    var y = 0
                    while (y < height) {
                        var z = 0
                        while (z < depth) {
                            p = x.toDouble() * iw
                            q = y.toDouble() * ih
                            r = z.toDouble() * id
                            p = p * (range.map1.x - range.map0.x) / dx
                            q = q * (range.map1.y - range.map0.y) / dy
                            r = r * (range.map1.z - range.map0.z) / dz
                            nx = range.loop0.x + cos(p * TWO_PI) * dx_div_2pi
                            ny = range.loop0.x + sin(p * TWO_PI) * dx_div_2pi
                            nz = range.loop0.y + cos(q * TWO_PI) * dy_div_2pi
                            nw = range.loop0.y + sin(q * TWO_PI) * dy_div_2pi
                            nu = range.loop0.z + cos(r * TWO_PI) * dz_div_2pi
                            nv = range.loop0.z + sin(r * TWO_PI) * dz_div_2pi
                            writer1.write(x, y, z, m[nx, ny, nz, nw, nu, nv])
                            listener1.update(++current, total)
                            z++
                        }
                        y++
                    }
                    x++
                }
                return
            }
            else -> throw AssertionError()
        }
    }

}
