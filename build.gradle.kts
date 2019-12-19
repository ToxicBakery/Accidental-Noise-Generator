
plugins {
    id("org.jetbrains.kotlin.multiplatform").version("1.3.61").apply(false)
}

allprojects {
    val tagName = System.getenv("CIRCLE_TAG")
    val isTag = tagName != null && !tagName.isEmpty()
    val buildNumber = System.getenv("CIRCLE_BUILD_NUM") ?: "0"

    group = "com.ToxicBakery.library.noise"
    version = "1.0.0-SNAPSHOT"
    version = "1.0.$buildNumber${if(isTag) "" else "-SNAPSHOT"}"

    repositories {
        mavenCentral()
        jcenter()
        maven { setUrl("http://dl.bintray.com/kotlin/kotlinx.html/") }
    }
}
