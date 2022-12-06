plugins {
    kotlin("jvm") version "1.7.22"
}

repositories {
    mavenCentral()
}

tasks {
    sourceSets {
        main {
            java.srcDirs("src")
        }
    }

    wrapper {
        gradleVersion = "7.6"
    }
}

tasks.register("generateSourceFiles") {
    doLast {
        val sourceTemplateText = file(layout.projectDirectory.file("src/Day00_template.kt")).readText()
        for (i in 1..24) {
            val iFormatted = if (i < 10) "0$i" else "$i"
            val sourceFile = file(layout.projectDirectory.file("src/Day${iFormatted}.kt"))
            if (!sourceFile.exists()) {
                sourceFile.writeText(sourceTemplateText)
            }
            val inputFile = file(layout.projectDirectory.file("src/Day${iFormatted}_input.txt"))
            if (!inputFile.exists()) {
                inputFile.writeText("")
            }
            val testFile = file(layout.projectDirectory.file("src/Day${iFormatted}_test.txt"))
            if (!testFile.exists()) {
                testFile.writeText("")
            }
        }
    }
}
