import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import java.nio.file.Files
import java.nio.file.StandardCopyOption

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
val kotlinWrappersVersion = "1.0.0-pre.463"
fun kotlinw(target:String)="org.jetbrains.kotlin-wrappers:kotlin-$target"

dependencies {
    //wrappers
    implementation(enforcedPlatform(kotlinw("wrappers-bom:$kotlinWrappersVersion")))
    implementation(kotlinw("react"))
    implementation(kotlinw("react-dom"))
    implementation(kotlinw("emotion"))
    implementation(kotlinw("react-router-dom"))
    implementation(kotlinw("redux"))
    implementation(kotlinw("react-redux"))
    implementation(kotlinw("mui"))
    implementation(kotlinw("mui-icons"))
    //npm
    implementation(npm("usehooks-ts", "2.8.0"))
    //others...
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
            /*dceTask {
                dceOptions.devMode = true
            }*/
        }
        nodejs {
            testTask {
                useMocha()
            }
        }
    }
}
rootProject.plugins.withType<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin> {
    rootProject.the<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension>().nodeVersion="16.15.0"
    // or true for default behavior
}
tasks.named("kotlinNodeJsSetup"){
    doFirst {
        val dir = rootDir.resolve("webpack.config.d")
        dir.mkdir()
        dir.resolve("webpack.config.js").writeText(
            """
                if (config.devServer){config.devServer=Object.assign(config.devServer,{historyApiFallback:true});}
                """.trimIndent()
        )
    }
    dependsOn("generateHtml")
}
tasks.build{
    doLast {
        val dist = buildDir.resolve("distributions").toPath()
        Files.copy(dist.resolve("index.html"), dist.resolve("404.html"),StandardCopyOption.REPLACE_EXISTING)
    }
}
task("generateHtml") {
    File("src/main/resources/index.html").bufferedWriter().run {
        appendLine("<!DOCTYPE html>")
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

