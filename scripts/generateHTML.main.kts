@file:Repository("https://jcenter.bintray.com")
@file:DependsOn("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.1")

import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import java.io.File

File("index.html").bufferedWriter().run {
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
            script(src = "/naotiki-website.js") {}
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

