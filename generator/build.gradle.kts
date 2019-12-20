import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    kotlin("multiplatform")
    id("io.gitlab.arturbosch.detekt")
    id("org.jetbrains.dokka").version("0.10.0")
    id("maven-publish")
    id("signing")
}

kotlin {
    sourceSets {
        all {
            languageSettings.enableLanguageFeature("InlineClasses")
            languageSettings.useExperimentalAnnotation("kotlin.time.ExperimentalTime")
            languageSettings.useExperimentalAnnotation("kotlin.ExperimentalUnsignedTypes")
        }
    }
    js {
        browser {}
        nodejs {}
    }
    jvm {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    sourceSets["commonMain"].dependencies {
        implementation(kotlin("stdlib-common"))
    }
    sourceSets["commonTest"].dependencies {
        implementation(kotlin("test-common"))
        implementation(kotlin("test-annotations-common"))
    }
    sourceSets["jvmMain"].dependencies {
        implementation(kotlin("stdlib-jdk8"))
        implementation(kotlin("test-junit"))
    }
    sourceSets["jsMain"].dependencies {
        implementation(kotlin("stdlib-js"))
    }
    sourceSets["jsTest"].dependencies {
        implementation(kotlin("test-js"))
    }
}

detekt {
    failFast = true
    buildUponDefaultConfig = true
    config = files("${rootProject.projectDir}/detekt/config.yml")
    input = files(
        kotlin.sourceSets
            .flatMap { sourceSet -> sourceSet.kotlin.srcDirs }
            .map { file -> file.relativeTo(projectDir) }
    )
    reports {
        html.enabled = true
    }
}

tasks {
    val dokka by getting(DokkaTask::class) {
        outputFormat = "html"
        outputDirectory = "${project.projectDir}/gh-pages"
        multiplatform {}
        doLast {
            File(outputDirectory, "CNAME")
                .writeText("accidental-noise-generator.toxicbakery.dev")
        }
    }

    getByName("build").dependsOn(dokka)
}

tasks.register("dokkaCommon", DokkaTask::class) {
    outputFormat = "html"
    outputDirectory = "$buildDir/javadoc/common"
    multiplatform {}
}

tasks.register("dokkaJvm", DokkaTask::class) {
    outputFormat = "html"
    outputDirectory = "$buildDir/javadoc/jvm"
    multiplatform {}
}

tasks.register("dokkaJs", DokkaTask::class) {
    outputFormat = "html"
    outputDirectory = "$buildDir/javadoc/js"
    multiplatform {}
}

val emptySourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
}

val emptyJavadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

val dokkaJavadocCommonJar by tasks.registering(Jar::class) {
    dependsOn("dokkaCommon")
    from("$buildDir/javadoc/common")
}

val dokkaJavadocJsJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
    dependsOn("dokkaJs")
    from("$buildDir/javadoc/js")
}

val dokkaJavadocJvmJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
    dependsOn("dokkaJvm")
    from("$buildDir/javadoc/jvm")
}

val POM_DESCRIPTION: String by project
val POM_NAME: String by project
val POM_URL: String by project
val POM_SCM_URL: String by project
val POM_SCM_CONNECTION: String by project
val POM_SCM_DEV_CONNECTION: String by project
val POM_LICENCE_NAME: String by project
val POM_LICENCE_URL: String by project
val POM_LICENCE_DIST: String by project
val POM_DEVELOPER_ID: String by project
val POM_DEVELOPER_NAME: String by project
val POM_DEVELOPER_EMAIL: String by project
val POM_DEVELOPER_ORGANIZATION: String by project
val POM_DEVELOPER_ORGANIZATION_URL: String by project

publishing {
    publications.withType<MavenPublication>().all {
        pom {
            description.set(POM_DESCRIPTION)
            name.set(POM_NAME)
            url.set(POM_URL)
            scm {
                url.set(POM_SCM_URL)
                connection.set(POM_SCM_CONNECTION)
                developerConnection.set(POM_SCM_DEV_CONNECTION)
            }
            licenses {
                license {
                    name.set(POM_LICENCE_NAME)
                    url.set(POM_LICENCE_URL)
                    distribution.set(POM_LICENCE_DIST)
                }
            }
            developers {
                developer {
                    id.set(POM_DEVELOPER_ID)
                    name.set(POM_DEVELOPER_NAME)
                    email.set(POM_DEVELOPER_EMAIL)
                    organization.set(POM_DEVELOPER_ORGANIZATION)
                    organizationUrl.set(POM_DEVELOPER_ORGANIZATION_URL)
                }
            }
        }

        when (name) {
            "jvm" -> artifact(dokkaJavadocJvmJar.get())
            "js" -> artifact(dokkaJavadocJsJar.get())
        }
    }

    repositories {
        val releaseUrl = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
        val snapshotUrl = uri("https://oss.sonatype.org/content/repositories/snapshots")
        val sonatypeUsername = System.getenv("SONATYPE_USERNAME") ?: ""
        val sonatypePassword = System.getenv("SONATYPE_PASSWORD") ?: ""
        maven {
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotUrl else releaseUrl
            credentials {
                username = if (sonatypeUsername.isBlank()) "" else sonatypeUsername
                password = if (sonatypePassword.isBlank()) "" else sonatypePassword
            }
        }
    }
}

if (!version.toString().endsWith("SNAPSHOT")) {
    signing {
        isRequired = false
        sign(publishing.publications)
    }
}
