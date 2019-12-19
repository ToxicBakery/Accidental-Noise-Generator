
pluginManagement {
    val detekt_version: String by settings

    plugins {
        id("io.gitlab.arturbosch.detekt").version(detekt_version)
    }
}

enableFeaturePreview("GRADLE_METADATA")

include(":generator")
include(":terrain-preview-jvm")
include(":terrain-preview-web")
