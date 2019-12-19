package com.sudoplay.joise.map

import com.sudoplay.joise.generator.LCG
import com.sudoplay.joise.module.Module
import com.sudoplay.joise.module.ModuleAbs
import com.sudoplay.joise.module.ModuleBasisFunction
import com.sudoplay.joise.module.ModuleBasisFunction.BasisType
import com.sudoplay.joise.module.ModuleBasisFunction.InterpolationType
import com.sudoplay.joise.module.SeedableModule
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class MapTest {

    private lateinit var func1: ModuleBasisFunction
    private lateinit var func2: ModuleBasisFunction
    private lateinit var func3: ModuleBasisFunction

    @BeforeTest
    fun init() {
        func1 = ModuleBasisFunction()
        func1.basisType = BasisType.SIMPLEX
        func1.interpolationType = InterpolationType.LINEAR
        func1.seed = 424242
        func2 = ModuleBasisFunction()
        func2.basisType = BasisType.GRADVAL
        func2.interpolationType = InterpolationType.QUINTIC
        func2.seed = 684684654
        func3 = ModuleBasisFunction()
        func3.basisType = BasisType.VALUE
        func3.interpolationType = InterpolationType.CUBIC
        func3.seed = 12233815
    }

    @Test
    fun testModuleAbs() {
        val mod = ModuleAbs()
        mod.setSource(func2)
        test(mod)
    }
/*
    @Test
    fun testModuleAutoCorrect() {
        val mod = ModuleAutoCorrect()
        mod.high = 0.8
        mod.low = 0.6
        mod.samples = 1000
        mod.sampleScale = 5.0
        mod.setSource(func2)
        mod.calculate()
        mod.locked = true
        test(mod)
    }

    @Test
    fun testModuleBasisFunction() {
        test(func1)
    }

    @Test
    fun testModuleBias() {
        val bias = ModuleBias()
        bias.setBias(0.6554)
        bias.setSource(func1)
        test(bias)
        bias.setBias(func2)
        test(bias)
    }

    @Test
    fun testModuleBlend() {
        val blend = ModuleBlend()
        blend.setHighSource(func1)
        blend.setLowSource(func2)
        blend.setControlSource(0.23)
        test(blend)
        blend.setControlSource(func3)
        test(blend)
    }

    @Test
    fun testModuleBrightContrast() {
        val mod = ModuleBrightContrast()
        mod.setBrightness(0.36)
        mod.setContrastFactor(func2)
        mod.setContrastThreshold(func3)
        mod.setSource(func1)
        test(mod)
    }

    @Test
    fun testModuleCache() {
        val mod = ModuleCache()
        mod.setSource(func2)
        test(mod)
    }

    @Test
    fun testModuleCellular() {
        val gen = ModuleCellGen()
        gen.seed = 6816846
        val mod = ModuleCellular()
        mod.setCellularSource(gen)
        mod.setCoefficients(0.2, 0.9, 3.6, 1.7)
        test(mod)
    }

    @Test
    fun testModuleClamp() {
        val mod = ModuleClamp()
        mod.setRange(0.5, 1.5)
        mod.setSource(func3)
        test(mod)
    }

    @Test
    fun testModuleCombiner() {
        val mod = ModuleCombiner(CombinerType.ADD)
        mod.setSource(0, func1)
        mod.setSource(1, func2)
        test(mod)
        mod.setSource(5, func3)
        test(mod)
    }

    @Test
    fun testModuleCos() {
        val mod = ModuleCos()
        mod.setSource(func2)
        test(mod)
    }

    @Test
    fun testModuleFloor() {
        val mod = ModuleFloor()
        mod.setSource(func3)
        test(mod)
    }

    @Test
    fun testModuleFractal() {
        val mod = ModuleFractal()
        mod.setAllSourceBasisTypes(BasisType.SIMPLEX)
        mod.setAllSourceInterpolationTypes(InterpolationType.CUBIC)
        mod.numOctaves = 5
        mod.frequency = 2.34
        mod.type = FractalType.RIDGEMULTI
        mod.seed = 898456
        test(mod)
    }

    @Test
    fun testModuleFractalOverrideSource() {
        val mod = ModuleFractal()
        mod.setAllSourceBasisTypes(BasisType.SIMPLEX)
        mod.setAllSourceInterpolationTypes(InterpolationType.CUBIC)
        mod.numOctaves = 5
        mod.frequency = 2.34
        mod.type = FractalType.RIDGEMULTI
        mod.seed = 898456
        mod.overrideSource(2, func2)
        test(mod)
    }

    @Test
    fun testModuleFunctionGradient() {
        val mod = ModuleFunctionGradient()
        mod.axis = FunctionGradientAxis.X_AXIS
        mod.spacing = 0.365458556
        test(mod)
    }

    @Test
    fun testModuleGain() {
        val mod = ModuleGain()
        mod.setSource(func2)
        mod.setGain(0.76)
        test(mod)
        mod.setGain(func1)
        test(mod)
    }

    @Test
    fun testModuleNormalizedCoords() {
        val mod = ModuleNormalizedCoords()
        mod.setSource(func2)
        mod.setLength(0.36)
        test(mod)
        mod.setLength(func3)
        test(mod)
    }

    @Test
    fun testModuleGradient() {
        val mod = ModuleGradient()
        mod.setGradient(0.0, 0.0, 0.0, 1.0)
        test(mod)
    }

    @Test
    fun testModuleInvert() {
        val mod = ModuleInvert()
        mod.setSource(func3)
        test(mod)
    }

    @Test
    fun testModuleMagnitude() {
        val mod = ModuleMagnitude()
        mod.setX(func3)
        mod.setY(2.156)
        test(mod)
    }

    @Test
    fun testModulePow() {
        val mod = ModulePow()
        mod.setSource(func1)
        mod.setPower(6.245)
        test(mod)
        mod.setPower(func2)
        test(mod)
    }

    @Test
    fun testModuleRotateDomain() {
        val mod = ModuleRotateDomain()
        mod.setAxis(0.36, 1.25, 0.45)
        mod.setAngle(2.556)
        mod.setSource(func3)
        test(mod)
    }

    @Test
    fun testModuleSawtooth() {
        val mod = ModuleSawtooth()
        mod.setSource(func2)
        mod.setPeriod(23.56)
        test(mod)
    }

    @Test
    fun testModuleScaleDomain() {
        val mod = ModuleScaleDomain()
        mod.setScaleX(23.54)
        mod.setScaleY(func1)
        mod.setSource(func3)
        test(mod)
    }

    @Test
    fun testModuleScaleOffset() {
        val mod = ModuleScaleOffset()
        mod.setSource(func1)
        mod.setScale(648.231)
        mod.setOffset(func2)
        test(mod)
    }

    @Test
    fun testModuleSelect() {
        val mod = ModuleSelect()
        mod.setControlSource(1.2)
        mod.setHighSource(func1)
        mod.setLowSource(func3)
        mod.setFalloff(0.5)
        mod.setThreshold(func2)
        test(mod)
    }

    @Test
    fun testModuleSin() {
        val mod = ModuleSin()
        mod.setSource(func2)
        test(mod)
    }

    @Test
    fun testModuleSphere() {
        val mod = ModuleSphere()
        mod.setCenterX(0.3)
        mod.setCenterY(0.6)
        mod.setCenterZ(func2)
        mod.setRadius(0.9)
        test(mod)
    }

    @Test
    fun testModuleTiers() {
        val mod = ModuleTiers()
        mod.numTiers = 5
        mod.smooth = false
        mod.setSource(func2)
        test(mod)
        mod.setSource(func1)
        mod.smooth = true
        test(mod)
    }

    @Test
    fun testModuleTranslateDomain() {
        val mod = ModuleTranslateDomain()
        mod.setAxisXSource(2.6)
        mod.setAxisYSource(84.23)
        mod.setSource(func3)
        test(mod)
    }

    @Test
    fun testModuleTriangle() {
        val mod = ModuleTriangle()
        mod.setOffset(0.23)
        mod.setPeriod(4.89)
        mod.setSource(func1)
        test(mod)
    }

    @Test
    fun testSeedName() {
        testWithSeedName(func2)
    }*/

    private fun test(module: Module) {
        val lcg = LCG()
        var x: Double
        var y: Double
        for (i in 0..99) {
            x = lcg.get01() * 8.0
            y = lcg.get01() * 8.0
            assertEquals(module[x, y], module[x, y])
        }
    }

    private fun testWithSeedName(module: SeedableModule) {
        module.seedName = "externalSeed"
        val lcg = LCG()
        var x: Double
        var y: Double
        var seed: Long
        for (i in 0..99) {
            x = lcg.get01() * 8.0
            y = lcg.get01() * 8.0
            seed = lcg.get().toLong()
            module.seed = seed
            assertEquals(module[x, y], module[x, y])
        }
    }

}
