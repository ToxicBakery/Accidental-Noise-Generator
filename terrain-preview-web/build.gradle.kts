import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    kotlin("js")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-js")
    implementation(project(":generator"))

    testImplementation("org.jetbrains.kotlin:kotlin-test-js")
}

kotlin {
    target {
        browser {
            runTask {
                outputFileName = "terrain-preview-web.js"
                devServer = KotlinWebpackConfig.DevServer(
                    contentBase = listOf("$buildDir/processedResources/Js/main"),
                    port = 8081
                )
            }
        }
    }
}
