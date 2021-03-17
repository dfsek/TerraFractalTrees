plugins {
    java
}

group = "com.dfsek"
version = "1.0.0"

repositories {
    mavenCentral()
    flatDir {
        dirs("../libs")
    }
}

dependencies {
    testCompile("junit", "junit", "4.12")

    //compileOnly("com.dfsek.terra.common:common:5.0.0-BETA+7f11373f")

    compileOnly(fileTree("./libs/"))
    //compileOnly("com.dfsek:Tectonic:1.2.3")
    //compileOnly("com.dfsek:Paralithic:0.3.2")
    implementation("net.jafama:jafama:2.3.2")
}
