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

import kotlin.experimental.and
import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.native.concurrent.ThreadLocal

object Noise {
    private const val INIT32 = 0x811c9dc5L
    private const val PRIME32 = 0x01000193
    private const val FNV_MASK_8 = (1 shl 8) - 1
    private const val INV_BYTEVAL = 1.0 / 255.0
    private const val F2 = 0.36602540378443864676372317075294
    private const val G2 = 0.21132486540518711774542560974902
    private const val F3 = 1.0 / 3.0
    private const val G3 = 1.0 / 6.0

    private class Buffer(size: Int) {

        private val b = ByteArray(size)
        private var index = 0

        fun clear() {
            b.fill(0x0)
            index = 0
        }

        fun writeInt(value: Int) {
            b[index] = (value shr 24 and 0xff).toByte()
            b[index + 1] = (value shr 16 and 0xff).toByte()
            b[index + 2] = (value shr 8 and 0xff).toByte()
            b[index + 3] = (value and 0xff).toByte()
            index += 4
        }

        fun writeLong(value: Long) {
            b[index] = (value shr 56 and 0xff).toByte()
            b[index + 1] = (value shr 48 and 0xff).toByte()
            b[index + 2] = (value shr 40 and 0xff).toByte()
            b[index + 3] = (value shr 32 and 0xff).toByte()
            b[index + 4] = (value shr 24 and 0xff).toByte()
            b[index + 5] = (value shr 16 and 0xff).toByte()
            b[index + 6] = (value shr 8 and 0xff).toByte()
            b[index + 7] = (value and 0xff).toByte()
            index += 8
        }

        fun array(): ByteArray = b

    }

    @ThreadLocal
    private val buf16 = Buffer(16)

    @ThreadLocal
    private val buf20 = Buffer(20)

    @ThreadLocal
    private val buf24 = Buffer(24)

    @ThreadLocal
    private val buf32 = Buffer(32)

    @ThreadLocal
    private val buf40 = Buffer(40)

    @ThreadLocal
    private val buf56 = Buffer(56)

    // ==========================================================================
    // = Common
    // ==========================================================================
    private fun lerp(s: Double, v1: Double, v2: Double): Double {
        return v1 + s * (v2 - v1)
    }

    private fun fastFloor(t: Double): Int {
        return if (t > 0) t.toInt() else t.toInt() - 1
    }

    private fun arrayDot2(arr: DoubleArray, a: Double, b: Double): Double {
        return a * arr[0] + b * arr[1]
    }

    private fun arrayDot3(
        arr: DoubleArray,
        a: Double,
        b: Double,
        c: Double
    ): Double {
        return a * arr[0] + b * arr[1] + c * arr[2]
    }

    private fun arrayDot4(
        arr: DoubleArray, x: Double, y: Double, z: Double,
        w: Double
    ): Double {
        return x * arr[0] + y * arr[1] + z * arr[2] + w * arr[3]
    }

    private fun addDist(
        f: DoubleArray, disp: DoubleArray, testDist: Double,
        testDisp: Double
    ) {
        var index: Int
        if (testDist < f[3]) {
            index = 3
            while (index > 0 && testDist < f[index - 1]) index--
            var i = 3
            while (i-- > index) {
                f[i + 1] = f[i]
                disp[i + 1] = disp[i]
            }
            f[index] = testDist
            disp[index] = testDisp
        }
    }

    private fun sortBy6(l1: DoubleArray, l2: IntArray) {
        val a: Array<SVectorOrdering> = Array(6) { i -> SVectorOrdering(l1[i], l2[i]) }
        a.sortWith(object : Comparator<SVectorOrdering> {
            override fun compare(a: SVectorOrdering, b: SVectorOrdering): Int = a.value.compareTo(b.value)
        })
        a.forEachIndexed { index, vec -> l2[index] = vec.axis }

    }

    // ==========================================================================
    // = Hashing functions
    // ==========================================================================
    private fun fnv32ABuf(buf: ByteArray): Int {
        var hval = INIT32
        for (i in buf.indices) {
            hval = hval xor buf[i].toLong()
            hval *= PRIME32.toLong()
        }
        return (hval and 0x00000000ffffffffL).toInt()
    }

    private fun xorFoldHash(hash: Int): Int =
        (hash shr 8 xor (hash and FNV_MASK_8) and 0xFF)

    private fun hashCoords2(x: Int, y: Int, seed: Long): Int {
        buf16.clear()
        buf16.writeInt(x)
        buf16.writeInt(y)
        buf16.writeLong(seed)
        return xorFoldHash(fnv32ABuf(buf16.array()))
    }

    private fun hashCoords3(x: Int, y: Int, z: Int, seed: Long): Int {
        buf20.clear()
        buf20.writeInt(x)
        buf20.writeInt(y)
        buf20.writeInt(z)
        buf20.writeLong(seed)
        return xorFoldHash(fnv32ABuf(buf20.array()))
    }

    private fun hashCoords4(x: Int, y: Int, z: Int, w: Int, seed: Long): Int {
        buf24.clear()
        buf24.writeInt(x)
        buf24.writeInt(y)
        buf24.writeInt(z)
        buf24.writeInt(w)
        buf24.writeLong(seed)
        return xorFoldHash(fnv32ABuf(buf24.array()))
    }

    private fun hashCoords6(
        x: Int, y: Int, z: Int, w: Int, u: Int, v: Int,
        seed: Long
    ): Int {
        buf32.clear()
        buf32.writeInt(x)
        buf32.writeInt(y)
        buf32.writeInt(z)
        buf32.writeInt(w)
        buf32.writeInt(u)
        buf32.writeInt(v)
        buf32.writeLong(seed)
        return xorFoldHash(fnv32ABuf(buf32.array()))
    }

    private fun computeHashDouble2(x: Double, y: Double, seed: Long): Int {
        buf24.clear()
        buf24.writeDouble(x)
        buf24.writeDouble(y)
        buf24.writeLong(seed)
        return xorFoldHash(fnv32ABuf(buf24.array()))
    }

    private fun computeHashDouble3(x: Double, y: Double, z: Double, seed: Long): Int {
        buf32.clear()
        buf32.writeDouble(x)
        buf32.writeDouble(y)
        buf32.writeDouble(z)
        buf32.writeLong(seed)
        return xorFoldHash(fnv32ABuf(buf32.array()))
    }

    private fun computeHashDouble4(
        x: Double, y: Double, z: Double, w: Double,
        seed: Long
    ): Int {
        buf40.clear()
        buf40.writeDouble(x)
        buf40.writeDouble(y)
        buf40.writeDouble(z)
        buf40.writeDouble(w)
        buf40.writeLong(seed)
        return xorFoldHash(fnv32ABuf(buf40.array()))
    }

    private fun computeHashDouble6(
        x: Double, y: Double, z: Double, w: Double,
        u: Double, v: Double, seed: Long
    ): Int {
        buf56.clear()
        buf56.writeDouble(x)
        buf56.writeDouble(y)
        buf56.writeDouble(z)
        buf56.writeDouble(w)
        buf56.writeDouble(u)
        buf56.writeDouble(v)
        buf56.writeLong(seed)
        return xorFoldHash(fnv32ABuf(buf56.array()))
    }

    private fun Buffer.writeDouble(value: Double) = writeLong(value.toBits())

    // ==========================================================================
    // = Interpolation functions
    // ==========================================================================
    private fun interpolateX2(
        x: Double, y: Double, xs: Double, x0: Int,
        x1: Int, iy: Int, seed: Long, noisefunc: WorkerNoise2
    ): Double {
        val v1 = noisefunc.calculate(x, y, x0, iy, seed)
        val v2 = noisefunc.calculate(x, y, x1, iy, seed)
        return lerp(xs, v1, v2)
    }

    private fun interpolateXY2(
        x: Double, y: Double, xs: Double,
        ys: Double, x0: Int, x1: Int, y0: Int, y1: Int, seed: Long,
        noisefunc: WorkerNoise2
    ): Double {
        val v1 = interpolateX2(x, y, xs, x0, x1, y0, seed, noisefunc)
        val v2 = interpolateX2(x, y, xs, x0, x1, y1, seed, noisefunc)
        return lerp(ys, v1, v2)
    }

    private fun interpolateX3(
        x: Double, y: Double, z: Double, xs: Double,
        x0: Int, x1: Int, iy: Int, iz: Int, seed: Long, noisefunc: WorkerNoise3
    ): Double {
        val v1 = noisefunc.calculate(x, y, z, x0, iy, iz, seed)
        val v2 = noisefunc.calculate(x, y, z, x1, iy, iz, seed)
        return lerp(xs, v1, v2)
    }

    private fun interpolateXY3(
        x: Double, y: Double, z: Double, xs: Double,
        ys: Double, x0: Int, x1: Int, y0: Int, y1: Int, iz: Int, seed: Long,
        noisefunc: WorkerNoise3
    ): Double {
        val v1 = interpolateX3(x, y, z, xs, x0, x1, y0, iz, seed, noisefunc)
        val v2 = interpolateX3(x, y, z, xs, x0, x1, y1, iz, seed, noisefunc)
        return lerp(ys, v1, v2)
    }

    private fun interpolateXYZ3(
        x: Double, y: Double, z: Double,
        xs: Double, ys: Double, zs: Double, x0: Int, x1: Int, y0: Int, y1: Int, z0: Int,
        z1: Int, seed: Long, noisefunc: WorkerNoise3
    ): Double {
        val v1 = interpolateXY3(x, y, z, xs, ys, x0, x1, y0, y1, z0, seed, noisefunc)
        val v2 = interpolateXY3(x, y, z, xs, ys, x0, x1, y0, y1, z1, seed, noisefunc)
        return lerp(zs, v1, v2)
    }

    private fun interpolateX4(
        x: Double, y: Double, z: Double, w: Double,
        xs: Double, x0: Int, x1: Int, iy: Int, iz: Int, iw: Int, seed: Long,
        noisefunc: WorkerNoise4
    ): Double {
        val v1 = noisefunc.calculate(x, y, z, w, x0, iy, iz, iw, seed)
        val v2 = noisefunc.calculate(x, y, z, w, x1, iy, iz, iw, seed)
        return lerp(xs, v1, v2)
    }

    private fun interpolateXY4(
        x: Double, y: Double, z: Double, w: Double,
        xs: Double, ys: Double, x0: Int, x1: Int, y0: Int, y1: Int, iz: Int, iw: Int,
        seed: Long, noisefunc: WorkerNoise4
    ): Double {
        val v1 = interpolateX4(x, y, z, w, xs, x0, x1, y0, iz, iw, seed, noisefunc)
        val v2 = interpolateX4(x, y, z, w, xs, x0, x1, y1, iz, iw, seed, noisefunc)
        return lerp(ys, v1, v2)
    }

    private fun interpolateXYZ4(
        x: Double, y: Double, z: Double, w: Double,
        xs: Double, ys: Double, zs: Double, x0: Int, x1: Int, y0: Int, y1: Int, z0: Int,
        z1: Int, iw: Int, seed: Long, noisefunc: WorkerNoise4
    ): Double {
        val v1 = interpolateXY4(
            x, y, z, w, xs, ys, x0, x1, y0, y1, z0, iw,
            seed, noisefunc
        )
        val v2 = interpolateXY4(
            x, y, z, w, xs, ys, x0, x1, y0, y1, z1, iw,
            seed, noisefunc
        )
        return lerp(zs, v1, v2)
    }

    private fun interpolateXYZW4(
        x: Double, y: Double, z: Double,
        w: Double, xs: Double, ys: Double, zs: Double, ws: Double, x0: Int, x1: Int,
        y0: Int, y1: Int, z0: Int, z1: Int, w0: Int, w1: Int, seed: Long,
        noisefunc: WorkerNoise4
    ): Double {
        val v1 = interpolateXYZ4(
            x, y, z, w, xs, ys, zs, x0, x1, y0, y1, z0, z1,
            w0, seed, noisefunc
        )
        val v2 = interpolateXYZ4(
            x, y, z, w, xs, ys, zs, x0, x1, y0, y1, z0, z1,
            w1, seed, noisefunc
        )
        return lerp(ws, v1, v2)
    }

    private fun interpolateX6(
        x: Double, y: Double, z: Double, w: Double,
        u: Double, v: Double, xs: Double, x0: Int, x1: Int, iy: Int, iz: Int, iw: Int,
        iu: Int, iv: Int, seed: Long, noisefunc: WorkerNoise6
    ): Double {
        val v1 = noisefunc.calculate(
            x, y, z, w, u, v, x0, iy, iz, iw, iu, iv,
            seed
        )
        val v2 = noisefunc.calculate(
            x, y, z, w, u, v, x1, iy, iz, iw, iu, iv,
            seed
        )
        return lerp(xs, v1, v2)
    }

    private fun interpolateXY6(
        x: Double, y: Double, z: Double, w: Double,
        u: Double, v: Double, xs: Double, ys: Double, x0: Int, x1: Int, y0: Int, y1: Int,
        iz: Int, iw: Int, iu: Int, iv: Int, seed: Long, noisefunc: WorkerNoise6
    ): Double {
        val v1 = interpolateX6(
            x, y, z, w, u, v, xs, x0, x1, y0, iz, iw, iu, iv,
            seed, noisefunc
        )
        val v2 = interpolateX6(
            x, y, z, w, u, v, xs, x0, x1, y1, iz, iw, iu, iv,
            seed, noisefunc
        )
        return lerp(ys, v1, v2)
    }

    private fun interpolateXYZ6(
        x: Double, y: Double, z: Double, w: Double,
        u: Double, v: Double, xs: Double, ys: Double, zs: Double, x0: Int, x1: Int,
        y0: Int, y1: Int, z0: Int, z1: Int, iw: Int, iu: Int, iv: Int, seed: Long,
        noisefunc: WorkerNoise6
    ): Double {
        val v1 = interpolateXY6(
            x, y, z, w, u, v, xs, ys, x0, x1, y0, y1, z0,
            iw, iu, iv, seed, noisefunc
        )
        val v2 = interpolateXY6(
            x, y, z, w, u, v, xs, ys, x0, x1, y0, y1, z1,
            iw, iu, iv, seed, noisefunc
        )
        return lerp(zs, v1, v2)
    }

    private fun interpolateXYZW6(
        x: Double, y: Double, z: Double, w: Double, u: Double, v: Double,
        xs: Double, ys: Double, zs: Double, ws: Double,
        x0: Int, x1: Int, y0: Int, y1: Int, z0: Int, z1: Int, w0: Int, w1: Int, iu: Int, iv: Int,
        seed: Long, noisefunc: WorkerNoise6
    ): Double {
        val v1 = interpolateXYZ6(
            x, y, z, w, u, v, xs, ys, zs, x0, x1, y0, y1,
            z0, z1, w0, iu, iv, seed, noisefunc
        )
        val v2 = interpolateXYZ6(
            x, y, z, w, u, v, xs, ys, zs, x0, x1, y0, y1,
            z0, z1, w1, iu, iv, seed, noisefunc
        )
        return lerp(ws, v1, v2)
    }

    private fun interpolateXYZWU6(
        x: Double, y: Double, z: Double, w: Double, u: Double, v: Double,
        xs: Double, ys: Double, zs: Double, ws: Double, us: Double,
        x0: Int, x1: Int, y0: Int, y1: Int, z0: Int, z1: Int, w0: Int, w1: Int, u0: Int, u1: Int, iv: Int,
        seed: Long, noisefunc: WorkerNoise6
    ): Double {
        val v1 = interpolateXYZW6(
            x, y, z, w, u, v, xs, ys, zs, ws, x0, x1, y0,
            y1, z0, z1, w0, w1, u0, iv, seed, noisefunc
        )
        val v2 = interpolateXYZW6(
            x, y, z, w, u, v, xs, ys, zs, ws, x0, x1, y0,
            y1, z0, z1, w0, w1, u1, iv, seed, noisefunc
        )
        return lerp(us, v1, v2)
    }

    private fun interpolateXYZWUV6(
        x: Double, y: Double, z: Double, w: Double, u: Double, v: Double,
        xs: Double, ys: Double, zs: Double, ws: Double, us: Double, vs: Double,
        x0: Int, x1: Int, y0: Int, y1: Int, z0: Int, z1: Int, w0: Int, w1: Int, u0: Int, u1: Int, v0: Int, v1: Int,
        seed: Long, noisefunc: WorkerNoise6
    ): Double {
        val val1 = interpolateXYZWU6(
            x, y, z, w, u, v, xs, ys, zs, ws, us, x0,
            x1, y0, y1, z0, z1, w0, w1, u0, u1, v0, seed, noisefunc
        )
        val val2 = interpolateXYZWU6(
            x, y, z, w, u, v, xs, ys, zs, ws, us, x0,
            x1, y0, y1, z0, z1, w0, w1, u0, u1, v1, seed, noisefunc
        )
        return lerp(vs, val1, val2)
    }

    // ==========================================================================
    // = Cellular functions
    // ==========================================================================
    @JvmStatic
    fun cellularFunction2D(
        x: Double, y: Double, seed: Long,
        f: DoubleArray, disp: DoubleArray
    ): Unit {
        val xint = fastFloor(x)
        val yint = fastFloor(y)
        for (c in 0..3) {
            f[c] = 99999.0
            disp[c] = 0.0
        }
        for (ycur in yint - 3..yint + 3) {
            for (xcur in xint - 3..xint + 3) {
                val xpos = (xcur.toDouble()
                        + WorkerNoise2.VALUE.calculate(x, y, xcur, ycur, seed))
                val ypos = (ycur.toDouble()
                        + WorkerNoise2.VALUE.calculate(x, y, xcur, ycur, seed + 1))
                val xdist = xpos - x
                val ydist = ypos - y
                val dist = xdist * xdist + ydist * ydist
                val xval = fastFloor(xpos)
                val yval = fastFloor(ypos)
                val dsp = WorkerNoise2.VALUE.calculate(x, y, xval, yval, seed + 3)
                addDist(f, disp, dist, dsp)
            }
        }
    }

    @JvmStatic
    fun cellularFunction3D(
        x: Double, y: Double, z: Double,
        seed: Long, f: DoubleArray, disp: DoubleArray
    ): Unit {
        val xint = fastFloor(x)
        val yint = fastFloor(y)
        val zint = fastFloor(z)
        for (c in 0..3) {
            f[c] = 99999.0
            disp[c] = 0.0
        }
        for (zcur in zint - 2..zint + 2) {
            for (ycur in yint - 2..yint + 2) {
                for (xcur in xint - 2..xint + 2) {
                    val xpos = (xcur.toDouble()
                            + WorkerNoise3.VALUE.calculate(x, y, z, xcur, ycur, zcur, seed))
                    val ypos = (ycur.toDouble()
                            + WorkerNoise3.VALUE.calculate(
                        x, y, z, xcur, ycur, zcur,
                        seed + 1
                    ))
                    val zpos = (zcur.toDouble()
                            + WorkerNoise3.VALUE.calculate(
                        x, y, z, xcur, ycur, zcur,
                        seed + 2
                    ))
                    val xdist = xpos - x
                    val ydist = ypos - y
                    val zdist = zpos - z
                    val dist = xdist * xdist + ydist * ydist + zdist * zdist
                    val xval = fastFloor(xpos)
                    val yval = fastFloor(ypos)
                    val zval = fastFloor(zpos)
                    val dsp = WorkerNoise3.VALUE.calculate(
                        x, y, z, xval, yval, zval,
                        seed + 3
                    )
                    addDist(f, disp, dist, dsp)
                }
            }
        }
    }

    @JvmStatic
    fun cellularFunction4D(
        x: Double, y: Double, z: Double, w: Double,
        seed: Long, f: DoubleArray, disp: DoubleArray
    ): Unit {
        val xint = fastFloor(x)
        val yint = fastFloor(y)
        val zint = fastFloor(z)
        val wint = fastFloor(w)
        for (c in 0..3) {
            f[c] = 99999.0
            disp[c] = 0.0
        }
        for (wcur in wint - 2..wint + 2) {
            for (zcur in zint - 2..zint + 2) {
                for (ycur in yint - 2..yint + 2) {
                    for (xcur in xint - 2..xint + 2) {
                        val xpos = (xcur.toDouble()
                                + WorkerNoise4.VALUE.calculate(
                            x, y, z, w, xcur, ycur, zcur,
                            wcur, seed
                        ))
                        val ypos = (ycur.toDouble()
                                + WorkerNoise4.VALUE.calculate(
                            x, y, z, w, xcur, ycur, zcur,
                            wcur, seed + 1
                        ))
                        val zpos = (zcur.toDouble()
                                + WorkerNoise4.VALUE.calculate(
                            x, y, z, w, xcur, ycur, zcur,
                            wcur, seed + 2
                        ))
                        val wpos = (wcur.toDouble()
                                + WorkerNoise4.VALUE.calculate(
                            x, y, z, w, xcur, ycur, zcur,
                            wcur, seed + 3
                        ))
                        val xdist = xpos - x
                        val ydist = ypos - y
                        val zdist = zpos - z
                        val wdist = wpos - w
                        val dist = xdist * xdist + ydist * ydist + zdist * zdist + (wdist
                                * wdist)
                        val xval = fastFloor(xpos)
                        val yval = fastFloor(ypos)
                        val zval = fastFloor(zpos)
                        val wval = fastFloor(wpos)
                        val dsp = WorkerNoise4.VALUE.calculate(
                            x, y, z, w, xval, yval,
                            zval, wval, seed + 3
                        )
                        addDist(f, disp, dist, dsp)
                    }
                }
            }
        }
    }

    @JvmStatic
    fun cellularFunction6D(
        x: Double, y: Double, z: Double, w: Double,
        u: Double, v: Double, seed: Long, f: DoubleArray, disp: DoubleArray
    ): Unit {
        val xint = fastFloor(x)
        val yint = fastFloor(y)
        val zint = fastFloor(z)
        val wint = fastFloor(w)
        val uint = fastFloor(u)
        val vint = fastFloor(v)
        for (c in 0..3) {
            f[c] = 99999.0
            disp[c] = 0.0
        }
        for (vcur in vint - 1..vint + 1) {
            for (ucur in uint - 1..uint + 1) {
                for (wcur in wint - 2..wint + 2) {
                    for (zcur in zint - 2..zint + 2) {
                        for (ycur in yint - 2..yint + 2) {
                            for (xcur in xint - 2..xint + 2) {
                                val xpos = (xcur.toDouble()
                                        + WorkerNoise6.VALUE.calculate(
                                    x, y, z, w, u, v, xcur,
                                    ycur, zcur, wcur, ucur, vcur, seed
                                ))
                                val ypos = (ycur.toDouble()
                                        + WorkerNoise6.VALUE.calculate(
                                    x, y, z, w, u, v, xcur,
                                    ycur, zcur, wcur, ucur, vcur, seed + 1
                                ))
                                val zpos = (zcur.toDouble()
                                        + WorkerNoise6.VALUE.calculate(
                                    x, y, z, w, u, v, xcur,
                                    ycur, zcur, wcur, ucur, vcur, seed + 2
                                ))
                                val wpos = (wcur.toDouble()
                                        + WorkerNoise6.VALUE.calculate(
                                    x, y, z, w, u, v, xcur,
                                    ycur, zcur, wcur, ucur, vcur, seed + 3
                                ))
                                val upos = (ucur.toDouble()
                                        + WorkerNoise6.VALUE.calculate(
                                    x, y, z, w, u, v, xcur,
                                    ycur, zcur, wcur, ucur, vcur, seed + 4
                                ))
                                val vpos = (vcur.toDouble()
                                        + WorkerNoise6.VALUE.calculate(
                                    x, y, z, w, u, v, xcur,
                                    ycur, zcur, wcur, ucur, vcur, seed + 5
                                ))
                                val xdist = xpos - x
                                val ydist = ypos - y
                                val zdist = zpos - z
                                val wdist = wpos - w
                                val udist = upos - u
                                val vdist = vpos - v
                                val dist = xdist * xdist + ydist * ydist + zdist * zdist +
                                        wdist * wdist + udist * udist + vdist * vdist
                                val xval = fastFloor(xpos)
                                val yval = fastFloor(ypos)
                                val zval = fastFloor(zpos)
                                val wval = fastFloor(wpos)
                                val uval = fastFloor(upos)
                                val vval = fastFloor(vpos)
                                val dsp = WorkerNoise6.VALUE.calculate(
                                    x, y, z, w, u, v,
                                    xval, yval, zval, wval, uval, vval, seed + 6
                                )
                                addDist(f, disp, dist, dsp)
                            }
                        }
                    }
                }
            }
        }
    }

    @JvmStatic
    fun intValueNoise3D(x: Int, y: Int, z: Int, seed: Int): Double {
        var n = 1619 * x + 31337 * y + 6971 * z + 1013 * seed and 0x7fffffff
        n = n shr 13 xor n
        return (n * (n * n * 60493 + 19990303) + 1376312589 and 0x7fffffff).toDouble()
    }

    @JvmStatic
    fun valueNoise3D(x: Int, y: Int, z: Int, seed: Int): Double {
        return 1.0 - intValueNoise3D(x, y, z, seed) / 1073741824.0
    }

    // ==========================================================================
    // = Worker noise functions
    // ==========================================================================
    private interface WorkerNoise2 {
        fun calculate(x: Double, y: Double, ix: Int, iy: Int, seed: Long): Double

        companion object {
            val VALUE: WorkerNoise2 = object :
                WorkerNoise2 {
                override fun calculate(
                    x: Double,
                    y: Double,
                    ix: Int,
                    iy: Int,
                    seed: Long
                ): Double {
                    val n = hashCoords2(ix, iy, seed)
                    val noise = n.toDouble() * INV_BYTEVAL
                    return noise * 2.0 - 1.0
                }
            }
            val GRADIENT: WorkerNoise2 = object :
                WorkerNoise2 {
                override fun calculate(
                    x: Double,
                    y: Double,
                    ix: Int,
                    iy: Int,
                    seed: Long
                ): Double {
                    val hash = hashCoords2(ix, iy, seed)
                    val vec = NoiseLUT.gradient2DLUT[hash]
                    val dx = x - ix.toDouble()
                    val dy = y - iy.toDouble()
                    return dx * vec[0] + dy * vec[1]
                }
            }
        }
    }

    private interface WorkerNoise3 {
        fun calculate(
            x: Double, y: Double, z: Double, ix: Int, iy: Int,
            iz: Int, seed: Long
        ): Double

        companion object {
            val VALUE: WorkerNoise3 = object :
                WorkerNoise3 {
                override fun calculate(
                    x: Double, y: Double, z: Double, ix: Int, iy: Int,
                    iz: Int, seed: Long
                ): Double {
                    val n = hashCoords3(ix, iy, iz, seed)
                    val noise = n.toDouble() * INV_BYTEVAL
                    return noise * 2.0 - 1.0
                }
            }
            val GRADIENT: WorkerNoise3 = object :
                WorkerNoise3 {
                override fun calculate(
                    x: Double, y: Double, z: Double, ix: Int, iy: Int,
                    iz: Int, seed: Long
                ): Double {
                    val hash = hashCoords3(ix, iy, iz, seed)
                    val vec = NoiseLUT.gradient3DLUT[hash]
                    val dx = x - ix.toDouble()
                    val dy = y - iy.toDouble()
                    val dz = z - iz.toDouble()
                    return dx * vec[0] + dy * vec[1] + dz * vec[2]
                }
            }
        }
    }

    private interface WorkerNoise4 {
        fun calculate(
            x: Double, y: Double, z: Double, w: Double, ix: Int,
            iy: Int, iz: Int, iw: Int, seed: Long
        ): Double

        companion object {
            val VALUE: WorkerNoise4 = object :
                WorkerNoise4 {
                override fun calculate(
                    x: Double, y: Double, z: Double, w: Double, ix: Int,
                    iy: Int, iz: Int, iw: Int, seed: Long
                ): Double {
                    val n = hashCoords4(ix, iy, iz, iw, seed)
                    val noise = n.toDouble() * INV_BYTEVAL
                    return noise * 2.0 - 1.0
                }
            }
            val GRADIENT: WorkerNoise4 = object :
                WorkerNoise4 {
                override fun calculate(
                    x: Double, y: Double, z: Double, w: Double, ix: Int,
                    iy: Int, iz: Int, iw: Int, seed: Long
                ): Double {
                    val hash = hashCoords4(ix, iy, iz, iw, seed)
                    val vec = NoiseLUT.gradient4DLUT[hash]
                    val dx = x - ix.toDouble()
                    val dy = y - iy.toDouble()
                    val dz = z - iz.toDouble()
                    val dw = w - iw.toDouble()
                    return dx * vec[0] + dy * vec[1] + dz * vec[2] + dw * vec[3]
                }
            }
        }
    }

    private interface WorkerNoise6 {
        fun calculate(
            x: Double, y: Double, z: Double, w: Double, u: Double,
            v: Double, ix: Int, iy: Int, iz: Int, iw: Int, iu: Int, iv: Int, seed: Long
        ): Double

        companion object {
            val VALUE: WorkerNoise6 = object :
                WorkerNoise6 {
                override fun calculate(
                    x: Double, y: Double, z: Double, w: Double, u: Double,
                    v: Double, ix: Int, iy: Int, iz: Int, iw: Int, iu: Int, iv: Int, seed: Long
                ): Double {
                    val n = hashCoords6(ix, iy, iz, iw, iu, iv, seed)
                    val noise = n.toDouble() * INV_BYTEVAL
                    return noise * 2.0 - 1.0
                }
            }
            val GRADIENT: WorkerNoise6 = object :
                WorkerNoise6 {
                override fun calculate(
                    x: Double, y: Double, z: Double, w: Double, u: Double,
                    v: Double, ix: Int, iy: Int, iz: Int, iw: Int, iu: Int, iv: Int, seed: Long
                ): Double {
                    val hash =
                        hashCoords6(ix, iy, iz, iw, iu, iv, seed)
                    val vec = NoiseLUT.gradient6DLUT[hash]
                    val dx = x - ix.toDouble()
                    val dy = y - iy.toDouble()
                    val dz = z - iz.toDouble()
                    val dw = w - iw.toDouble()
                    val du = u - iu.toDouble()
                    val dv = v - iv.toDouble()
                    return dx * vec[0] + dy * vec[1] + dz * vec[2] + dw * vec[3] + (du
                            * vec[4]) + dv * vec[5]
                }
            }
        }
    }

    private class SVectorOrdering(var value: Double, var axis: Int) : Comparable<SVectorOrdering> {
        override fun compareTo(other: SVectorOrdering): Int = value.compareTo(other.value)
    }

    // ==========================================================================
    // = 2D noise functions
    // ==========================================================================
    interface Function2D {
        operator fun get(x: Double, y: Double, seed: Long, interpolator: Interpolator): Double

        companion object {
            @JvmField
            val VALUE: Function2D = object :
                Function2D {
                override fun get(
                    x: Double,
                    y: Double,
                    seed: Long,
                    interpolator: Interpolator
                ): Double {
                    val x0 = fastFloor(x)
                    val y0 = fastFloor(y)
                    val x1 = x0 + 1
                    val y1 = y0 + 1
                    val xs = interpolator.interpolate(x - x0.toDouble())
                    val ys = interpolator.interpolate(y - y0.toDouble())
                    return interpolateXY2(x, y, xs, ys, x0, x1, y0, y1, seed, WorkerNoise2.VALUE)
                }
            }
            @JvmField
            val GRADIENT: Function2D = object :
                Function2D {
                override fun get(
                    x: Double,
                    y: Double,
                    seed: Long,
                    interpolator: Interpolator
                ): Double {
                    val x0 = fastFloor(x)
                    val y0 = fastFloor(y)
                    val x1 = x0 + 1
                    val y1 = y0 + 1
                    val xs = interpolator.interpolate(x - x0.toDouble())
                    val ys = interpolator.interpolate(y - y0.toDouble())
                    return interpolateXY2(x, y, xs, ys, x0, x1, y0, y1, seed, WorkerNoise2.GRADIENT)
                }
            }
            @JvmField
            val GRADVAL: Function2D = object :
                Function2D {
                override fun get(
                    x: Double,
                    y: Double,
                    seed: Long,
                    interpolator: Interpolator
                ): Double = Function2D.VALUE[x, y, seed, interpolator] +
                        Function2D.GRADIENT[x, y, seed, interpolator]
            }

            @JvmField
            val WHITE: Function2D = object :
                Function2D {
                override fun get(
                    x: Double,
                    y: Double,
                    seed: Long,
                    interpolator: Interpolator
                ): Double {
                    val hash = computeHashDouble2(x, y, seed)
                    return NoiseLUT.whitenoiseLUT[hash]
                }
            }

            @JvmField
            val SIMPLEX: Function2D = object :
                Function2D {
                override fun get(
                    x: Double,
                    y: Double,
                    seed: Long,
                    interpolator: Interpolator
                ): Double {
                    val s = (x + y) * F2
                    val i = fastFloor(x + s)
                    val j = fastFloor(y + s)
                    val t = (i + j) * G2
                    val X0 = i - t
                    val Y0 = j - t
                    val x0 = x - X0
                    val y0 = y - Y0
                    val i1: Int
                    val j1: Int
                    if (x0 > y0) {
                        i1 = 1
                        j1 = 0
                    } else {
                        i1 = 0
                        j1 = 1
                    }
                    val x1 = x0 - i1.toDouble() + G2
                    val y1 = y0 - j1.toDouble() + G2
                    val x2 = x0 - 1.0 + 2.0 * G2
                    val y2 = y0 - 1.0 + 2.0 * G2
                    val h0 = hashCoords2(i, j, seed)
                    val h1 = hashCoords2(i + i1, j + j1, seed)
                    val h2 = hashCoords2(i + 1, j + 1, seed)
                    val g0 = NoiseLUT.gradient2DLUT[h0]
                    val g1 = NoiseLUT.gradient2DLUT[h1]
                    val g2 = NoiseLUT.gradient2DLUT[h2]
                    val n0: Double
                    val n1: Double
                    val n2: Double
                    var t0 = 0.5 - x0 * x0 - y0 * y0
                    if (t0 < 0) n0 = 0.0 else {
                        t0 *= t0
                        n0 = t0 * t0 * arrayDot2(g0, x0, y0)
                    }
                    var t1 = 0.5 - x1 * x1 - y1 * y1
                    if (t1 < 0) n1 = 0.0 else {
                        t1 *= t1
                        n1 = t1 * t1 * arrayDot2(g1, x1, y1)
                    }
                    var t2 = 0.5 - x2 * x2 - y2 * y2
                    if (t2 < 0) n2 = 0.0 else {
                        t2 *= t2
                        n2 = t2 * t2 * arrayDot2(g2, x2, y2)
                    }
                    return 70.0 * (n0 + n1 + n2) * 1.42188695 + 0.001054489
                }
            }
        }
    }

    // ==========================================================================
    // = 3D noise functions
    // ==========================================================================
    interface Function3D {
        operator fun get(
            x: Double, y: Double, z: Double, seed: Long,
            interpolator: Interpolator
        ): Double

        companion object {
            @JvmField
            val VALUE: Function3D = object :
                Function3D {
                override fun get(
                    x: Double, y: Double, z: Double, seed: Long,
                    interpolator: Interpolator
                ): Double {
                    val x0 = fastFloor(x)
                    val y0 = fastFloor(y)
                    val z0 = fastFloor(z)
                    val x1 = x0 + 1
                    val y1 = y0 + 1
                    val z1 = z0 + 1
                    val xs = interpolator.interpolate(x - x0.toDouble())
                    val ys = interpolator.interpolate(y - y0.toDouble())
                    val zs = interpolator.interpolate(z - z0.toDouble())
                    return interpolateXYZ3(x, y, z, xs, ys, zs, x0, x1, y0, y1, z0, z1, seed, WorkerNoise3.VALUE)
                }
            }
            @JvmField
            val GRADIENT: Function3D = object :
                Function3D {
                override fun get(
                    x: Double, y: Double, z: Double, seed: Long,
                    interpolator: Interpolator
                ): Double {
                    val x0 = fastFloor(x)
                    val y0 = fastFloor(y)
                    val z0 = fastFloor(z)
                    val x1 = x0 + 1
                    val y1 = y0 + 1
                    val z1 = z0 + 1
                    val xs = interpolator.interpolate(x - x0.toDouble())
                    val ys = interpolator.interpolate(y - y0.toDouble())
                    val zs = interpolator.interpolate(z - z0.toDouble())
                    return interpolateXYZ3(x, y, z, xs, ys, zs, x0, x1, y0, y1, z0, z1, seed, WorkerNoise3.GRADIENT)
                }
            }
            @JvmField
            val GRADVAL: Function3D = object :
                Function3D {
                override fun get(
                    x: Double, y: Double, z: Double, seed: Long,
                    interpolator: Interpolator
                ): Double = Function3D.VALUE[x, y, z, seed, interpolator] +
                        Function3D.GRADIENT[x, y, z, seed, interpolator]
            }
            @JvmField
            val WHITE: Function3D = object :
                Function3D {
                override fun get(
                    x: Double, y: Double, z: Double, seed: Long,
                    interpolator: Interpolator
                ): Double {
                    val hash = computeHashDouble3(x, y, z, seed)
                    return NoiseLUT.whitenoiseLUT[hash]
                }
            }
            @JvmField
            val SIMPLEX: Function3D = object :
                Function3D {
                override fun get(
                    x: Double, y: Double, z: Double, seed: Long,
                    interpolator: Interpolator
                ): Double {
                    val n0: Double
                    val n1: Double
                    val n2: Double
                    val n3: Double
                    val s = (x + y + z) * F3
                    val i = fastFloor(x + s)
                    val j = fastFloor(y + s)
                    val k = fastFloor(z + s)
                    val t = (i + j + k) * G3
                    val X0 = i - t
                    val Y0 = j - t
                    val Z0 = k - t
                    val x0 = x - X0
                    val y0 = y - Y0
                    val z0 = z - Z0
                    val i1: Int
                    val j1: Int
                    val k1: Int
                    val i2: Int
                    val j2: Int
                    val k2: Int
                    if (x0 >= y0) {
                        if (y0 >= z0) {
                            i1 = 1
                            j1 = 0
                            k1 = 0
                            i2 = 1
                            j2 = 1
                            k2 = 0
                        } else if (x0 >= z0) {
                            i1 = 1
                            j1 = 0
                            k1 = 0
                            i2 = 1
                            j2 = 0
                            k2 = 1
                        } else {
                            i1 = 0
                            j1 = 0
                            k1 = 1
                            i2 = 1
                            j2 = 0
                            k2 = 1
                        }
                    } else {
                        if (y0 < z0) {
                            i1 = 0
                            j1 = 0
                            k1 = 1
                            i2 = 0
                            j2 = 1
                            k2 = 1
                        } else if (x0 < z0) {
                            i1 = 0
                            j1 = 1
                            k1 = 0
                            i2 = 0
                            j2 = 1
                            k2 = 1
                        } else {
                            i1 = 0
                            j1 = 1
                            k1 = 0
                            i2 = 1
                            j2 = 1
                            k2 = 0
                        }
                    }
                    val x1 = x0 - i1 + G3
                    val y1 = y0 - j1 + G3
                    val z1 = z0 - k1 + G3
                    val x2 = x0 - i2 + 2.0 * G3
                    val y2 = y0 - j2 + 2.0 * G3
                    val z2 = z0 - k2 + 2.0 * G3
                    val x3 = x0 - 1.0 + 3.0 * G3
                    val y3 = y0 - 1.0 + 3.0 * G3
                    val z3 = z0 - 1.0 + 3.0 * G3
                    val h0: Int
                    val h1: Int
                    val h2: Int
                    val h3: Int
                    h0 = hashCoords3(i, j, k, seed)
                    h1 = hashCoords3(i + i1, j + j1, k + k1, seed)
                    h2 = hashCoords3(i + i2, j + j2, k + k2, seed)
                    h3 = hashCoords3(i + 1, j + 1, k + 1, seed)
                    val g0 = NoiseLUT.gradient3DLUT[h0]
                    val g1 = NoiseLUT.gradient3DLUT[h1]
                    val g2 = NoiseLUT.gradient3DLUT[h2]
                    val g3 = NoiseLUT.gradient3DLUT[h3]
                    var t0 = 0.6 - x0 * x0 - y0 * y0 - z0 * z0
                    if (t0 < 0.0) n0 = 0.0 else {
                        t0 *= t0
                        n0 = t0 * t0 * arrayDot3(g0, x0, y0, z0)
                    }
                    var t1 = 0.6 - x1 * x1 - y1 * y1 - z1 * z1
                    if (t1 < 0.0) n1 = 0.0 else {
                        t1 *= t1
                        n1 = t1 * t1 * arrayDot3(g1, x1, y1, z1)
                    }
                    var t2 = 0.6 - x2 * x2 - y2 * y2 - z2 * z2
                    if (t2 < 0) n2 = 0.0 else {
                        t2 *= t2
                        n2 = t2 * t2 * arrayDot3(g2, x2, y2, z2)
                    }
                    var t3 = 0.6 - x3 * x3 - y3 * y3 - z3 * z3
                    if (t3 < 0) n3 = 0.0 else {
                        t3 *= t3
                        n3 = t3 * t3 * arrayDot3(g3, x3, y3, z3)
                    }
                    return 32.0 * (n0 + n1 + n2 + n3) * 1.25086885 + 0.0003194984
                }
            }
        }
    }

    // ==========================================================================
    // = 4D noise functions
    // ==========================================================================
    interface Function4D {
        operator fun get(
            x: Double, y: Double, z: Double, w: Double, seed: Long,
            interpolator: Interpolator
        ): Double

        companion object {
            @JvmField
            val VALUE: Function4D = object :
                Function4D {
                override fun get(
                    x: Double, y: Double, z: Double, w: Double, seed: Long,
                    interpolator: Interpolator
                ): Double {
                    val x0 = fastFloor(x)
                    val y0 = fastFloor(y)
                    val z0 = fastFloor(z)
                    val w0 = fastFloor(w)
                    val x1 = x0 + 1
                    val y1 = y0 + 1
                    val z1 = z0 + 1
                    val w1 = w0 + 1
                    val xs = interpolator.interpolate(x - x0.toDouble())
                    val ys = interpolator.interpolate(y - y0.toDouble())
                    val zs = interpolator.interpolate(z - z0.toDouble())
                    val ws = interpolator.interpolate(w - w0.toDouble())
                    return interpolateXYZW4(
                        x, y, z, w, xs, ys, zs, ws, x0, x1, y0, y1, z0,
                        z1, w0, w1, seed, WorkerNoise4.VALUE
                    )
                }
            }
            @JvmField
            val GRADIENT: Function4D = object :
                Function4D {
                override fun get(
                    x: Double, y: Double, z: Double, w: Double, seed: Long,
                    interpolator: Interpolator
                ): Double {
                    val x0 = fastFloor(x)
                    val y0 = fastFloor(y)
                    val z0 = fastFloor(z)
                    val w0 = fastFloor(w)
                    val x1 = x0 + 1
                    val y1 = y0 + 1
                    val z1 = z0 + 1
                    val w1 = w0 + 1
                    val xs = interpolator.interpolate(x - x0.toDouble())
                    val ys = interpolator.interpolate(y - y0.toDouble())
                    val zs = interpolator.interpolate(z - z0.toDouble())
                    val ws = interpolator.interpolate(w - w0.toDouble())
                    return interpolateXYZW4(
                        x, y, z, w, xs, ys, zs, ws,
                        x0, x1, y0, y1, z0, z1, w0, w1, seed, WorkerNoise4.GRADIENT
                    )
                }
            }
            @JvmField
            val GRADVAL: Function4D = object :
                Function4D {
                override fun get(
                    x: Double, y: Double, z: Double, w: Double, seed: Long,
                    interpolator: Interpolator
                ): Double = Function4D.VALUE[x, y, z, w, seed, interpolator] +
                        Function4D.GRADIENT[x, y, z, w, seed, interpolator]
            }
            @JvmField
            val WHITE: Function4D = object :
                Function4D {
                override fun get(
                    x: Double, y: Double, z: Double, w: Double, seed: Long,
                    interpolator: Interpolator
                ): Double {
                    val hash = computeHashDouble4(x, y, z, w, seed)
                    return NoiseLUT.whitenoiseLUT[hash]
                }
            }
            @JvmField
            val SIMPLEX: Function4D = object :
                Function4D {
                override fun get(
                    x: Double, y: Double, z: Double, w: Double, seed: Long,
                    interpolator: Interpolator
                ): Double {
                    val simplex = arrayOf(
                        intArrayOf(0, 1, 2, 3),
                        intArrayOf(0, 1, 3, 2),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(0, 2, 3, 1),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(1, 2, 3, 0),
                        intArrayOf(0, 2, 1, 3),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(0, 3, 1, 2),
                        intArrayOf(0, 3, 2, 1),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(1, 3, 2, 0),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(1, 2, 0, 3),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(1, 3, 0, 2),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(2, 3, 0, 1),
                        intArrayOf(2, 3, 1, 0),
                        intArrayOf(1, 0, 2, 3),
                        intArrayOf(1, 0, 3, 2),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(2, 0, 3, 1),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(2, 1, 3, 0),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(2, 0, 1, 3),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(3, 0, 1, 2),
                        intArrayOf(3, 0, 2, 1),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(3, 1, 2, 0),
                        intArrayOf(2, 1, 0, 3),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(3, 1, 0, 2),
                        intArrayOf(0, 0, 0, 0),
                        intArrayOf(3, 2, 0, 1),
                        intArrayOf(3, 2, 1, 0)
                    )
                    val F4 = (sqrt(5.0) - 1.0) / 4.0
                    val G4 = (5.0 - sqrt(5.0)) / 20.0
                    val n0: Double
                    val n1: Double
                    val n2: Double
                    val n3: Double
                    val n4: Double
                    val s = (x + y + z + w) * F4
                    val i = fastFloor(x + s)
                    val j = fastFloor(y + s)
                    val k = fastFloor(z + s)
                    val l = fastFloor(w + s)
                    val t = (i + j + k + l) * G4
                    val X0 = i - t
                    val Y0 = j - t
                    val Z0 = k - t
                    val W0 = l - t
                    val x0 = x - X0
                    val y0 = y - Y0
                    val z0 = z - Z0
                    val w0 = w - W0
                    val c1 = if (x0 > y0) 32 else 0
                    val c2 = if (x0 > z0) 16 else 0
                    val c3 = if (y0 > z0) 8 else 0
                    val c4 = if (x0 > w0) 4 else 0
                    val c5 = if (y0 > w0) 2 else 0
                    val c6 = if (z0 > w0) 1 else 0
                    val c = c1 + c2 + c3 + c4 + c5 + c6
                    val i1: Int
                    val j1: Int
                    val k1: Int
                    val l1: Int
                    val i2: Int
                    val j2: Int
                    val k2: Int
                    val l2: Int
                    val i3: Int
                    val j3: Int
                    val k3: Int
                    val l3: Int
                    i1 = if (simplex[c][0] >= 3) 1 else 0
                    j1 = if (simplex[c][1] >= 3) 1 else 0
                    k1 = if (simplex[c][2] >= 3) 1 else 0
                    l1 = if (simplex[c][3] >= 3) 1 else 0
                    i2 = if (simplex[c][0] >= 2) 1 else 0
                    j2 = if (simplex[c][1] >= 2) 1 else 0
                    k2 = if (simplex[c][2] >= 2) 1 else 0
                    l2 = if (simplex[c][3] >= 2) 1 else 0
                    i3 = if (simplex[c][0] >= 1) 1 else 0
                    j3 = if (simplex[c][1] >= 1) 1 else 0
                    k3 = if (simplex[c][2] >= 1) 1 else 0
                    l3 = if (simplex[c][3] >= 1) 1 else 0
                    val x1 = x0 - i1 + G4
                    val y1 = y0 - j1 + G4
                    val z1 = z0 - k1 + G4
                    val w1 = w0 - l1 + G4
                    val x2 = x0 - i2 + 2.0 * G4
                    val y2 = y0 - j2 + 2.0 * G4
                    val z2 = z0 - k2 + 2.0 * G4
                    val w2 = w0 - l2 + 2.0 * G4
                    val x3 = x0 - i3 + 3.0 * G4
                    val y3 = y0 - j3 + 3.0 * G4
                    val z3 = z0 - k3 + 3.0 * G4
                    val w3 = w0 - l3 + 3.0 * G4
                    val x4 = x0 - 1.0 + 4.0 * G4
                    val y4 = y0 - 1.0 + 4.0 * G4
                    val z4 = z0 - 1.0 + 4.0 * G4
                    val w4 = w0 - 1.0 + 4.0 * G4
                    val h0: Int
                    val h1: Int
                    val h2: Int
                    val h3: Int
                    val h4: Int
                    h0 = hashCoords4(i, j, k, l, seed)
                    h1 = hashCoords4(
                        i + i1,
                        j + j1,
                        k + k1,
                        l + l1,
                        seed
                    )
                    h2 = hashCoords4(
                        i + i2,
                        j + j2,
                        k + k2,
                        l + l2,
                        seed
                    )
                    h3 = hashCoords4(
                        i + i3,
                        j + j3,
                        k + k3,
                        l + l3,
                        seed
                    )
                    h4 = hashCoords4(i + 1, j + 1, k + 1, l + 1, seed)
                    val g0 = NoiseLUT.gradient4DLUT[h0]
                    val g1 = NoiseLUT.gradient4DLUT[h1]
                    val g2 = NoiseLUT.gradient4DLUT[h2]
                    val g3 = NoiseLUT.gradient4DLUT[h3]
                    val g4 = NoiseLUT.gradient4DLUT[h4]
                    var t0 = 0.6 - x0 * x0 - y0 * y0 - z0 * z0 - w0 * w0
                    if (t0 < 0) n0 = 0.0 else {
                        t0 *= t0
                        n0 = t0 * t0 * arrayDot4(g0, x0, y0, z0, w0)
                    }
                    var t1 = 0.6 - x1 * x1 - y1 * y1 - z1 * z1 - w1 * w1
                    if (t1 < 0) n1 = 0.0 else {
                        t1 *= t1
                        n1 = t1 * t1 * arrayDot4(g1, x1, y1, z1, w1)
                    }
                    var t2 = 0.6 - x2 * x2 - y2 * y2 - z2 * z2 - w2 * w2
                    if (t2 < 0) n2 = 0.0 else {
                        t2 *= t2
                        n2 = t2 * t2 * arrayDot4(g2, x2, y2, z2, w2)
                    }
                    var t3 = 0.6 - x3 * x3 - y3 * y3 - z3 * z3 - w3 * w3
                    if (t3 < 0) n3 = 0.0 else {
                        t3 *= t3
                        n3 = t3 * t3 * arrayDot4(g3, x3, y3, z3, w3)
                    }
                    var t4 = 0.6 - x4 * x4 - y4 * y4 - z4 * z4 - w4 * w4
                    if (t4 < 0) n4 = 0.0 else {
                        t4 *= t4
                        n4 = t4 * t4 * arrayDot4(g4, x4, y4, z4, w4)
                    }
                    return 27.0 * (n0 + n1 + n2 + n3 + n4)
                }
            }
        }
    }

    // ==========================================================================
    // = 6D noise functions
    // ==========================================================================
    interface Function6D {
        operator fun get(
            x: Double, y: Double, z: Double, w: Double, u: Double,
            v: Double, seed: Long, interpolator: Interpolator
        ): Double

        companion object {
            @JvmField
            val VALUE: Function6D = object :
                Function6D {
                override fun get(
                    x: Double, y: Double, z: Double, w: Double, u: Double,
                    v: Double, seed: Long, interpolator: Interpolator
                ): Double {
                    val x0 = fastFloor(x)
                    val y0 = fastFloor(y)
                    val z0 = fastFloor(z)
                    val w0 = fastFloor(w)
                    val u0 = fastFloor(u)
                    val v0 = fastFloor(v)
                    val x1 = x0 + 1
                    val y1 = y0 + 1
                    val z1 = z0 + 1
                    val w1 = w0 + 1
                    val u1 = u0 + 1
                    val v1 = v0 + 1
                    val xs = interpolator.interpolate(x - x0.toDouble())
                    val ys = interpolator.interpolate(y - y0.toDouble())
                    val zs = interpolator.interpolate(z - z0.toDouble())
                    val ws = interpolator.interpolate(w - w0.toDouble())
                    val us = interpolator.interpolate(u - u0.toDouble())
                    val vs = interpolator.interpolate(v - v0.toDouble())
                    return interpolateXYZWUV6(
                        x, y, z, w, u, v, xs, ys, zs, ws, us, vs, x0,
                        x1, y0, y1, z0, z1, w0, w1, u0, u1, v0, v1, seed,
                        WorkerNoise6.VALUE
                    )
                }
            }
            @JvmField
            val GRADIENT: Function6D = object :
                Function6D {
                override fun get(
                    x: Double, y: Double, z: Double, w: Double, u: Double,
                    v: Double, seed: Long, interpolator: Interpolator
                ): Double {
                    val x0 = fastFloor(x)
                    val y0 = fastFloor(y)
                    val z0 = fastFloor(z)
                    val w0 = fastFloor(w)
                    val u0 = fastFloor(u)
                    val v0 = fastFloor(v)
                    val x1 = x0 + 1
                    val y1 = y0 + 1
                    val z1 = z0 + 1
                    val w1 = w0 + 1
                    val u1 = u0 + 1
                    val v1 = v0 + 1
                    val xs = interpolator.interpolate(x - x0.toDouble())
                    val ys = interpolator.interpolate(y - y0.toDouble())
                    val zs = interpolator.interpolate(z - z0.toDouble())
                    val ws = interpolator.interpolate(w - w0.toDouble())
                    val us = interpolator.interpolate(u - u0.toDouble())
                    val vs = interpolator.interpolate(v - v0.toDouble())
                    return interpolateXYZWUV6(
                        x, y, z, w, u, v, xs, ys, zs, ws, us, vs, x0,
                        x1, y0, y1, z0, z1, w0, w1, u0, u1, v0, v1, seed,
                        WorkerNoise6.GRADIENT
                    )
                }
            }
            @JvmField
            val GRADVAL: Function6D = object :
                Function6D {
                override fun get(
                    x: Double, y: Double, z: Double, w: Double, u: Double,
                    v: Double, seed: Long, interpolator: Interpolator
                ): Double = Function6D.VALUE[x, y, z, w, u, v, seed, interpolator] +
                        Function6D.GRADIENT[x, y, z, w, u, v, seed, interpolator]
            }
            @JvmField
            val WHITE: Function6D = object :
                Function6D {
                override fun get(
                    x: Double, y: Double, z: Double, w: Double, u: Double,
                    v: Double, seed: Long, interpolator: Interpolator
                ): Double {
                    val hash =
                        computeHashDouble6(x, y, z, w, u, v, seed)
                    return NoiseLUT.whitenoiseLUT[hash]
                }
            }
            @JvmField
            val SIMPLEX: Function6D = object :
                Function6D {
                override fun get(
                    x: Double, y: Double, z: Double, w: Double, u: Double,
                    v: Double, seed: Long, interpolator: Interpolator
                ): Double {
                    val F4 = (sqrt(7.0) - 1.0) / 6.0
                    val G4 = F4 / (1.0 + 6.0 * F4)
                    val sideLength = sqrt(6.0) / (6.0 * F4 + 1.0)
                    val a = sqrt(
                        sideLength * sideLength
                                - sideLength / 2.0 * (sideLength / 2.0)
                    )
                    val cornerFace = sqrt(a * a + a / 2.0 * (a / 2.0))
                    val cornerFaceSqrd = cornerFace * cornerFace
                    var valueScaler = 5.0.pow(-0.5)
                    valueScaler *= 5.0.pow(-3.5) * 100 + 13
                    val loc = doubleArrayOf(x, y, z, w, u, v)
                    var s = 0.0
                    for (c in 0..5) s += loc[c]
                    s *= F4
                    val skewLoc = intArrayOf(
                        fastFloor(x + s),
                        fastFloor(y + s),
                        fastFloor(z + s),
                        fastFloor(w + s),
                        fastFloor(u + s),
                        fastFloor(v + s)
                    )
                    val intLoc = intArrayOf(
                        fastFloor(x + s),
                        fastFloor(y + s),
                        fastFloor(z + s),
                        fastFloor(w + s),
                        fastFloor(u + s),
                        fastFloor(v + s)
                    )
                    var unskew = 0.0
                    for (c in 0..5) unskew += skewLoc[c]
                    unskew *= G4
                    val cellDist = doubleArrayOf(
                        loc[0] - skewLoc[0].toDouble() + unskew,
                        loc[1] - skewLoc[1].toDouble() + unskew,
                        loc[2] - skewLoc[2].toDouble() + unskew,
                        loc[3] - skewLoc[3].toDouble() + unskew,
                        loc[4] - skewLoc[4].toDouble() + unskew,
                        loc[5] - skewLoc[5].toDouble() + unskew
                    )
                    val distOrder = intArrayOf(0, 1, 2, 3, 4, 5)
                    sortBy6(cellDist, distOrder)
                    val newDistOrder = intArrayOf(
                        -1, distOrder[0], distOrder[1], distOrder[2],
                        distOrder[3], distOrder[4], distOrder[5]
                    )
                    var n = 0.0
                    var skewOffset = 0.0
                    for (c in 0..6) {
                        val i = newDistOrder[c]
                        if (i != -1) intLoc[i] += 1
                        val m = DoubleArray(6)
                        for (d in 0..5) {
                            m[d] = cellDist[d] - (intLoc[d] - skewLoc[d]) + skewOffset
                        }
                        var t = cornerFaceSqrd
                        for (d in 0..5) {
                            t -= m[d] * m[d]
                        }
                        if (t > 0.0) {
                            val h = hashCoords6(
                                intLoc[0], intLoc[1], intLoc[2], intLoc[3],
                                intLoc[4], intLoc[5], seed
                            )
                            val vec = NoiseLUT.gradient6DLUT[h]
                            var gr = 0.0
                            for (d in 0..5) {
                                gr += vec[d] * m[d]
                            }
                            n += gr * t * t * t * t
                        }
                        skewOffset += G4
                    }
                    n *= valueScaler
                    return n
                }
            }
        }
    }

}
