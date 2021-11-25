plugins {
    kotlin("jvm") version "1.5.10"
    application
}

group = "com.github.lant"
version = "1.0"

repositories {
    mavenCentral()
}

application {
    mainClass.set("com.github.lant.rss.RssKt")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jsoup:jsoup:1.14.3")
    implementation("rome:rome:1.0")
}