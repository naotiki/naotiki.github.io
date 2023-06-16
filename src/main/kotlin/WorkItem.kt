import csstype.PropertiesBuilder
import emotion.react.css
import js.core.jso
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import mui.material.*
import mui.material.styles.TypographyVariant
import mui.system.responsive
import mui.system.sx
import react.*
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.img
import react.dom.svg.ReactSVG.image
import web.cssom.*


enum class Platform {
    Web,
    Android,
    Desktop
}

external interface WorkItemProps : Props {
    var workItem: WorkItem
}

data class WorkItem constructor(
    val name: String,
    val description: String,
    val update: LocalDate,

    val artifactUrl: String?,
    val jointDevelopment: Boolean,
    val repoUrl: String? = null,
    val award: String? = null,
    private val detailPage: ComponentType<WorkItemProps>? = null
) {
    constructor(
        name: String,
        description: String,
        update: LocalDate,

        artifactUrl: String?,
        jointDevelopment: Boolean,
        repoUrl: String? = null,
        award: String? = null,
        detailPage: (ChildrenBuilder.(WorkItemProps) -> Unit)?
    ) : this(
        name,
        description,
        update,
        artifactUrl,
        jointDevelopment,
        repoUrl,
        award,
        detailPage?.let { FC(block = it) })

    fun createDetail() = detailPage?.create {
        workItem = this@WorkItem
    }
}

val works = listOf(
    WorkItem(
        "ちぃうご", """
        a
    """.trimIndent(), LocalDate(2023, Month.MAY, 21),
        null, false
    ) {
        div {
            css {
                width = 80.pct
            }
            dangerouslySetInnerHTML = jso {
                __html =
                    "<iframe class=\"speakerdeck-iframe\" frameborder=\"0\" src=\"https://speakerdeck.com/player/417741acf2a948d48adc3dc5d170135e\" title=\"ちぃうご\" allowfullscreen=\"true\" style=\"border: 0px; background: padding-box padding-box rgba(0, 0, 0, 0.1); margin: 0px; padding: 0px; border-radius: 6px; box-shadow: rgba(0, 0, 0, 0.2) 0px 5px 40px; width: 100%; height: auto; aspect-ratio: 560 / 314;\" data-ratio=\"1.78343949044586\"></iframe>"
            }
        }
    },
    WorkItem(
        "Easy Shell Environment",
        """
        拡張可能で簡単に操作できるシェルアプリ
    """.trimIndent(),
        LocalDate(2023, Month.JANUARY, 30),
        "https://ese.naotiki.me", false,
        "https://github.com/naotiki/Ese", null,
    ) {
        Typography {
            +it.workItem.name
            variant = TypographyVariant.h3
        }

        Carousel{
            CarouselItem {
                CarouselImg {
                    src = "https://images.wantedly.com/i/TMzN3hm?w=720"
                }
            }
            CarouselItem {
                CarouselImg {
                    src = "https://images.wantedly.com/i/fxmGME9?w=720"
                }
            }
            CarouselItem {
                CarouselImg {
                    src = "https://images.wantedly.com/i/ff2eh8h?w=720"
                }
            }
        }

    },
    WorkItem(
        "たのみ〜る", """
        お手軽食券販売システム
    """.trimIndent(),
        LocalDate(2023, Month.SEPTEMBER, 30),
        "", true
    ),
    WorkItem(
        "Wikiしりとり",
        """しりとりAIもどき
            |AIに例外を吐かせたらあなたの勝ち""".trimMargin(), LocalDate(2022, Month.MAY, 15),
        "https://naotiki.github.io/wiki-shiritori-nuxt", false, "https://github.com/naotiki/wiki-shiritori-nuxt"
    ),
    WorkItem(
        "Draw R4sh",
        """一瞬でお絵かきバトル！！！
            |あなたの絵心を見せつけよう！！！""".trimMargin(), LocalDate(2022, Month.MAY, 5),
        "https://draw-rush.web.app", true, "https://github.com/Yumemi-Hackathon22-D/DRAW-RUSH"
    ),
)

val WorkDetailWrapper = FC<PropsWithChildren> {
    Container {
        fixed=true
        +it.children
    }
}