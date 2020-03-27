# Accidental-Noise-Generator [![CircleCI](https://circleci.com/gh/ToxicBakery/Accidental-Noise-Generator.svg?style=svg)](https://circleci.com/gh/ToxicBakery/Accidental-Noise-Generator) [![Maven Central](https://img.shields.io/maven-central/v/com.ToxicBakery.library.noise/generator.svg)](https://oss.sonatype.org/content/repositories/releases/com/ToxicBakery/library/noise/) [![Maven Central](https://img.shields.io/maven-metadata/v/https/oss.sonatype.org/content/repositories/snapshots/com/ToxicBakery/library/noise/generator/maven-metadata.xml.svg)](https://oss.sonatype.org/content/repositories/snapshots/com/ToxicBakery/library/noise/)  
Accidental-Noise-Generator
is a 2D, 3D, 4D and 6D modular noise library written in Kotlin multiplatform and compiled for Java and JavaScript based on the Joise library.

Joise is derived from Joshua Tippetts' [Accidental Noise Library](http://accidentalnoise.sourceforge.net/index.html) written in C++ and ported directly from [Joise for Java](https://github.com/AlrikG/AccidentalNoiseLibraryForJava).

## Install
The output artifacts are maven artifacts for common code, Javascript, and Java
 
Common: Use this if you are depending on Joise in a common module of an MPP project.
```
implementation("com.ToxicBakery.library.noise:generator:+")
```

Java
```
implementation("com.ToxicBakery.library.noise:generator-jvm:+")
```

JavaScript
```
implementation("com.ToxicBakery.library.noise:generator-js:+")
```

## Build
Requires OpenJDK 8+

```bash
./gradlew build
```

## Sample
Samples can be run directly from the CLI.

JVM
```bash
./gradlew :terrain-preview-jvm:run
```

JS Browser
```bash
./gradlew :terrain-preview-web:run
```

* Note the browser demo is limited to an extremely small tile as the JS performance is extremely poor.
