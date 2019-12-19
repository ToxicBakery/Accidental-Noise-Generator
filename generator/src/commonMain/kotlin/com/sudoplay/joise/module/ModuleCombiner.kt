package com.sudoplay.joise.module

class ModuleCombiner(
    private var type: CombinerType
) : Module() {

    var sources = arrayOfNulls<ScalarParameter>(Module.MAX_SOURCES)

    fun setSource(index: Int, source: Module) {
        sources[index] = ScalarParameter(source)
    }

    fun setSource(index: Int, source: Double) {
        sources[index] = ScalarParameter(source)
    }

    fun clearAllSources() {
        for (i in 0 until Module.MAX_SOURCES) {
            sources[i] = null
        }
    }

    override operator fun get(x: Double, y: Double): Double = when (type) {
        CombinerType.ADD -> addGet(x, y)
        CombinerType.AVG -> avgGet(x, y)
        CombinerType.MAX -> maxGet(x, y)
        CombinerType.MIN -> minGet(x, y)
        CombinerType.MULT -> multGet(x, y)
    }

    override operator fun get(x: Double, y: Double, z: Double): Double = when (type) {
        CombinerType.ADD -> addGet(x, y, z)
        CombinerType.AVG -> avgGet(x, y, z)
        CombinerType.MAX -> maxGet(x, y, z)
        CombinerType.MIN -> minGet(x, y, z)
        CombinerType.MULT -> multGet(x, y, z)
    }

    override operator fun get(x: Double, y: Double, z: Double, w: Double): Double = when (type) {
        CombinerType.ADD -> addGet(x, y, z, w)
        CombinerType.AVG -> avgGet(x, y, z, w)
        CombinerType.MAX -> maxGet(x, y, z, w)
        CombinerType.MIN -> minGet(x, y, z, w)
        CombinerType.MULT -> multGet(x, y, z, w)
    }

    override operator fun get(
        x: Double,
        y: Double,
        z: Double,
        w: Double,
        u: Double,
        v: Double
    ): Double = when (type) {
        CombinerType.ADD -> addGet(x, y, z, w, u, v)
        CombinerType.AVG -> avgGet(x, y, z, w, u, v)
        CombinerType.MAX -> maxGet(x, y, z, w, u, v)
        CombinerType.MIN -> minGet(x, y, z, w, u, v)
        CombinerType.MULT -> multGet(x, y, z, w, u, v)
    }

    fun addGet(x: Double, y: Double): Double {
        var value = 0.0
        for (i in 0 until Module.MAX_SOURCES) {
            if (sources[i] != null) value += sources[i]!![x, y]
        }
        return value
    }

    // ==========================================================================
    // = ADD
    // ==========================================================================

    // ==========================================================================
    // = ADD
    // ==========================================================================
    fun addGet(x: Double, y: Double, z: Double): Double {
        var value = 0.0
        for (i in 0 until Module.MAX_SOURCES) {
            if (sources[i] != null) value += sources[i]!![x, y, z]
        }
        return value
    }

    fun addGet(x: Double, y: Double, z: Double, w: Double): Double {
        var value = 0.0
        for (i in 0 until Module.MAX_SOURCES) {
            if (sources[i] != null) value += sources[i]!![x, y, z, w]
        }
        return value
    }

    fun addGet(
        x: Double, y: Double, z: Double, w: Double, u: Double,
        v: Double
    ): Double {
        var value = 0.0
        for (i in 0 until Module.MAX_SOURCES) {
            if (sources[i] != null) value += sources[i]!![x, y, z, w, u, v]
        }
        return value
    }

    fun avgGet(x: Double, y: Double): Double {
        var count = 0
        var value = 0.0
        for (i in 0 until Module.MAX_SOURCES) {
            if (sources[i] != null) {
                value += sources[i]!![x, y]
                count++
            }
        }
        return if (count == 0) 0.0 else value / count.toDouble()
    }

    // ==========================================================================
    // = AVG
    // ==========================================================================

    // ==========================================================================
    // = AVG
    // ==========================================================================
    fun avgGet(x: Double, y: Double, z: Double): Double {
        var count = 0
        var value = 0.0
        for (i in 0 until Module.MAX_SOURCES) {
            if (sources[i] != null) {
                value += sources[i]!![x, y, z]
                count++
            }
        }
        return if (count == 0) 0.0 else value / count.toDouble()
    }

    fun avgGet(x: Double, y: Double, z: Double, w: Double): Double {
        var count = 0
        var value = 0.0
        for (i in 0 until Module.MAX_SOURCES) {
            if (sources[i] != null) {
                value += sources[i]!![x, y, z, w]
                count++
            }
        }
        return if (count == 0) 0.0 else value / count.toDouble()
    }

    fun avgGet(
        x: Double, y: Double, z: Double, w: Double, u: Double,
        v: Double
    ): Double {
        var count = 0
        var value = 0.0
        for (i in 0 until Module.MAX_SOURCES) {
            if (sources[i] != null) {
                value += sources[i]!![x, y, z, w, u, v]
                count++
            }
        }
        return if (count == 0) 0.0 else value / count.toDouble()
    }

    fun maxGet(x: Double, y: Double): Double {
        var mx: Double
        var c = 0
        while (c < Module.MAX_SOURCES && sources[c] == null) {
            c++
        }
        if (c == Module.MAX_SOURCES) return 0.0
        mx = sources[c]!![x, y]
        for (d in c until Module.MAX_SOURCES) {
            if (sources[d] != null) {
                val value = sources[d]!![x, y]
                if (value > mx) mx = value
            }
        }
        return mx
    }

    // ==========================================================================
    // = MAX
    // ==========================================================================

    // ==========================================================================
    // = MAX
    // ==========================================================================
    fun maxGet(x: Double, y: Double, z: Double): Double {
        var mx: Double
        var c = 0
        while (c < Module.MAX_SOURCES && sources[c] == null) {
            c++
        }
        if (c == Module.MAX_SOURCES) return 0.0
        mx = sources[c]!![x, y, z]
        for (d in c until Module.MAX_SOURCES) {
            if (sources[d] != null) {
                val value = sources[d]!![x, y, z]
                if (value > mx) mx = value
            }
        }
        return mx
    }

    fun maxGet(x: Double, y: Double, z: Double, w: Double): Double {
        var mx: Double
        var c = 0
        while (c < Module.MAX_SOURCES && sources[c] == null) {
            c++
        }
        if (c == Module.MAX_SOURCES) return 0.0
        mx = sources[c]!![x, y, z, w]
        for (d in c until Module.MAX_SOURCES) {
            if (sources[d] != null) {
                val value = sources[d]!![x, y, z, w]
                if (value > mx) mx = value
            }
        }
        return mx
    }

    fun maxGet(
        x: Double, y: Double, z: Double, w: Double, u: Double,
        v: Double
    ): Double {
        var mx: Double
        var c = 0
        while (c < Module.MAX_SOURCES && sources[c] == null) {
            c++
        }
        if (c == Module.MAX_SOURCES) return 0.0
        mx = sources[c]!![x, y, z, w, u, v]
        for (d in c until Module.MAX_SOURCES) {
            if (sources[d] != null) {
                val value = sources[d]!![x, y, z, w, u, v]
                if (value > mx) mx = value
            }
        }
        return mx
    }

    fun minGet(x: Double, y: Double): Double {
        var mn: Double
        var c = 0
        while (c < Module.MAX_SOURCES && sources[c] == null) {
            c++
        }
        if (c == Module.MAX_SOURCES) return 0.0
        mn = sources[c]!![x, y]
        for (d in c until Module.MAX_SOURCES) {
            if (sources[d] != null) {
                val value = sources[d]!![x, y]
                if (value < mn) mn = value
            }
        }
        return mn
    }

    // ==========================================================================
    // = MIN
    // ==========================================================================

    // ==========================================================================
    // = MIN
    // ==========================================================================
    fun minGet(x: Double, y: Double, z: Double): Double {
        var mn: Double
        var c = 0
        while (c < Module.MAX_SOURCES && sources[c] == null) {
            c++
        }
        if (c == Module.MAX_SOURCES) return 0.0
        mn = sources[c]!![x, y, z]
        for (d in c until Module.MAX_SOURCES) {
            if (sources[d] != null) {
                val value = sources[d]!![x, y, z]
                if (value < mn) mn = value
            }
        }
        return mn
    }

    fun minGet(x: Double, y: Double, z: Double, w: Double): Double {
        var mn: Double
        var c = 0
        while (c < Module.MAX_SOURCES && sources[c] == null) {
            c++
        }
        if (c == Module.MAX_SOURCES) return 0.0
        mn = sources[c]!![x, y, z, w]
        for (d in c until Module.MAX_SOURCES) {
            if (sources[d] != null) {
                val value = sources[d]!![x, y, z, w]
                if (value < mn) mn = value
            }
        }
        return mn
    }

    fun minGet(
        x: Double, y: Double, z: Double, w: Double, u: Double,
        v: Double
    ): Double {
        var mn: Double
        var c = 0
        while (c < Module.MAX_SOURCES && sources[c] == null) {
            c++
        }
        if (c == Module.MAX_SOURCES) return 0.0
        mn = sources[c]!![x, y, z, w, u, v]
        for (d in c until Module.MAX_SOURCES) {
            if (sources[d] != null) {
                val value = sources[d]!![x, y, z, w, u, v]
                if (value < mn) mn = value
            }
        }
        return mn
    }

    fun multGet(x: Double, y: Double): Double {
        var value = 1.0
        for (i in 0 until Module.MAX_SOURCES) {
            if (sources[i] != null) value *= sources[i]!![x, y]
        }
        return value
    }

    // ==========================================================================
    // = MULT
    // ==========================================================================

    // ==========================================================================
    // = MULT
    // ==========================================================================
    fun multGet(x: Double, y: Double, z: Double): Double {
        var value = 1.0
        for (i in 0 until Module.MAX_SOURCES) {
            if (sources[i] != null) value *= sources[i]!![x, y, z]
        }
        return value
    }

    fun multGet(x: Double, y: Double, z: Double, w: Double): Double {
        var value = 1.0
        for (i in 0 until Module.MAX_SOURCES) {
            if (sources[i] != null) value *= sources[i]!![x, y, z, w]
        }
        return value
    }

    fun multGet(
        x: Double, y: Double, z: Double, w: Double, u: Double,
        v: Double
    ): Double {
        var value = 1.0
        for (i in 0 until Module.MAX_SOURCES) {
            if (sources[i] != null) value *= sources[i]!![x, y, z, w, u, v]
        }
        return value
    }

    enum class CombinerType {
        ADD, MULT, MAX, MIN, AVG
    }

}
