plugins {
    id("java")
    jacoco
}

group = "ru.sinvic"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val mockitoAgent = configurations.create("mockitoAgent")


dependencies {
    implementation ("ch.qos.logback:logback-classic:1.5.12")

    testImplementation ("org.assertj:assertj-core:3.26.3")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation ("org.mockito:mockito-core:5.14.2")
    testImplementation ("org.mockito:mockito-junit-jupiter:5.14.2")

    mockitoAgent("org.mockito:mockito-core:5.14.2") { isTransitive = false }
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
    jvmArgs("-javaagent:${mockitoAgent.asPath}")
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}

tasks.jacocoTestReport {
    reports {
        csv.required.set(true)
        xml.required.set(true)
    }
}