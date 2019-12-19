import org.gradle.kotlin.dsl.implementation

plugins {
    kotlin("jvm")
    id("io.gitlab.arturbosch.detekt")
    application
}

application {
    mainClassName = "com.toxicbakery.game.dungeon.map.MainKt"
}

repositories {
    mavenCentral()
}

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = "1.8"
compileKotlin.kotlinOptions.freeCompilerArgs += "-Xinline-classes"
compileKotlin.kotlinOptions.freeCompilerArgs += "-Xuse-experimental=kotlin.Experimental"

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(project(":generator"))
    testImplementation("junit:junit:4.12")
}

detekt {
    failFast = true
    buildUponDefaultConfig = true
    config = files("${rootProject.projectDir}/detekt/config.yml")
    reports {
        html.enabled = true
    }
}
