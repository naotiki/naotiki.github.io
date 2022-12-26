import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import java.nio.file.Files
plugins {
    kotlin("js") version "1.7.21"
}

group = "me.unity"
version = "1.0-SNAPSHOT"
buildscript {
    dependencies {
        classpath("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.1")
    }
}
repositories {
    mavenCentral()
}
val kotlinWrappersVersion = "1.0.0-pre.462"
dependencies {
    implementation(enforcedPlatform("org.jetbrains.kotlin-wrappers:kotlin-wrappers-bom:$kotlinWrappersVersion"))
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-emotion")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-router-dom")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-redux")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-redux")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-mui")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-mui-icons")

    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

    testImplementation(kotlin("test-js"))
}
kotlin {
    js(IR) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled = true
                }
            }
            /*webpackTask {
                args.add("--history-api-fallback")
            }*/
            /*dceTask {
                dceOptions.devMode = true
            }*/
            testTask {
                useMocha()
            }
        }
    }
}

tasks.build{
    dependsOn("generateHtml")
    doLast {
        val dist=buildDir.resolve("distributions").toPath()
        Files.copy(dist.resolve("index.html"),dist.resolve("404.html"))
    }
}
task("generateHtml") {
    File("src/main/resources/index.html").bufferedWriter().run {
        appendHTML().html {
            lang = "ja"
            head {
                meta(charset = "UTF-8")
                meta(
                    "viewport",
                    content = "initial-scale=1, width=device-width"
                )
                title("なおちきです")
            }
            body {
                script(src = "naotiki-website.js") {}
            }
        }
        appendLine(
            """
<!--
Kotlinで書き切るぜ！！！
            \/
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM90C<<<<+11<<<<<<<<<?1TMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM90I!`                         `?XHMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMMMMMMMMMMM9VC<```                                 _?4MMMMMMMMMMMMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMMMM80C<!~```                                        `~?WMMMMMMMMMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMN6<~`                                                  _?UMMMMMMMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMN> `                                                     _?WMMMMMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMM9C!                                                         ?VHMMMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMH>`                                                           _ZMMMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMM9I_                                                             (ZHMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMM#>_                                                               (dMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMS!                                                                 dMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMM9C_                                          ` ` `                  (WMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMH>_               `                        `  ....... `              _?XMMMMMMMMMMMMMMM
MMMMMMMMMMMMMR!               ..(++--.                    .jXMMNHA+..`            ` zMMMMMMMMMMMMMMM
MMMMMMMMMMMMHC_              .dHMMMMNk+.               ` (dWMMMMMMMHo.              (ZHMMMMMMMMMMMMM
MMMMMMMMMMMNI<              (dHMMMMMMMR<`                JMMMMMMMMMMHk_              (dMMMMMMMMMMMMM
MMMMMMMMMMMHI<            `.dMMMMMMMMMH>``               zMMMMMMMMMMM#>               zMMMMMMMMMMMMM
MMMMMMMMMMMHI~            ` jHMMMMMMMMK:`                _?WMMMMMMMMMK> `             jMMMMMMMMMMMMM
MMMMMMMMMMMHI~               ~?OUHHH9Y>`                   ~?4HMMMH9C!`               jMNMMMMMMMMMMM
MMMMMMMMMMMH>_                  _!!!~`                       _???<!~``               `(XMMMMMMMMMMMM
MMMMMMMMMMMK:                                                                         (wWMMMMMMMMMMM
MMMMMMMMMMMR~                                                                         -?XMMMMMMMMMMM
MMMMMMMMMMMR~                                                                          _zMMMMMMMMMMM
MMMMMMMMMMMR~                                                                         ``jMMMMMMMMMMM
MMMMMMMMMMMR~                                                                           (MMMMMMMMMMM
MMMMMMMMMMMR~                      ` ......................                             (WMMMMMMMMMM
MMMMMMMMMMMR~                     ` (dXWMMMMMMMMMMMMMMMMNky<                            (OWMMMMMMMMM
MMMMMMMMMMMR~                     ` jMMMMMMMMMMMMMMMMMMMMMM3`                           -1XMMMMMMMMM
MMMMMMMMMMMR~                        `~?<<<<<<<<<<<<<<<<<~`                              _zMMMMMMMMM
MMMMMMMMMMM0~                                                                            -jMMMMMMMMM
MMMMMMMMMHC!                                                                            `.jMMMMMMMMM
MMMMMMMMMR~                                                                              .jMMMMMMMMM
MMMMMMMMMR_                                                                              -jMMMMMMMMM
MMMMMMMMMR_                                                                              (dMMMMMMMMM
MMMMMMMMMC`                                                                             (dWMMMMMMMMM
MMMMMMMHC<   `  .- `                                                                 `  (WMMMMMMMMMM
MMMMMMMR<_   .J&QA&-.                                                                 ` jMMMMMMMMMMM
MMMMMMMR<`   jMMMMNR<                    ` ..(-.`                                      _dMMMMMMMMMMM
MMMMMMMR<``.(dMMMMM#>`                `  .(dWMNo.                  `   ...  `      ` `(uXMMMMMMMMMMM
MMMMMMMR<``(dWMMMMMNl``            `   .(dHMMMNk<_                   .jOso-           jMMMMMMMMMMMMM
MMMMMMMR<``(XMMMMMMNI_`        `    ..(dWMMMMMNRz_               ` .(dWMMNk+-  `   `.(dMMMMMMMMMMMMM
MMMMMMMR<-.jMMMMMMMNI_     ` `   ..(dWNMMMMMMMMHZ<             `  .(dMMMMMMNI.      (dMMMMMMMMMMMMMM
MMMMMMMR<jdWMMMMMMMNI_  `    .(+dQNNMMMMMMMMMMMMH>          `    (dWMMMMMMMNHs-   ` jMMMMMMMMMMMMMMM
MMMMMMMNHNMMMMMMMMMMNmx--(+QQNNNMMMMMMMMMMMMMMMMNO- ` `` ``    (dqMMMMMMMMMMMNI ` -jXMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMMMNNNNNNMMMMMMMMMMMMMMMMMMMMMMNms+(((((+uuQQNMMMMMMMMMMMMMNR<_ jWMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNHQQQQHNNMMMMMMMMMMMMMMMMMMNmy+dMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNQMMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
-->
"""
        )
        flush()
        close()
    }
}

