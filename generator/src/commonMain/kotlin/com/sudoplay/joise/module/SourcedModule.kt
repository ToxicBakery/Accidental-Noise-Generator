package com.sudoplay.joise.module

abstract class SourcedModule : Module() {

    var source = ScalarParameter(0.0)
        protected set

    open fun setSource(source: Double) {
        this.source.value = source
    }

    open fun setSource(source: Module) {
        this.source.module = source
    }

}
