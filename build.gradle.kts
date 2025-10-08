plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}
tasks.register<JavaExec>("runLesson") {
    group = "Execution"
    description = "Run a specified main class"
    if (project.hasProperty("mainClass")) {
        mainClass.set(project.property("mainClass") as String)
        classpath = sourceSets.main.get().runtimeClasspath
    } else {
        throw GradleException("Please specify mainClass. Example: ./gradlew runLesson -PmainClass=lessons.lesson02.Main")
    }

    standardInput = System.`in`
}
