import components.ExperimentalDangerousRawHTML
import components.NewTabAnchor
import components.TextWithIcon
import components.dangerousRawHtml
import emotion.react.css
import js.core.jso
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import mui.icons.material.*
import mui.material.*
import mui.material.styles.Theme
import mui.material.styles.TypographyVariant
import mui.material.styles.useTheme
import mui.system.Breakpoint
import mui.system.PropsWithSx
import mui.system.responsive
import mui.system.sx
import react.*
import react.dom.html.ReactHTML.div
import web.cssom.*
import web.cssom.LineStyle

@OptIn(ExperimentalDangerousRawHTML::class)
val works = listOf(
    WorkItem(
        "ちぃうご", """
        もうコーディングは寂しくない。かわいいあなたの相棒。
    """.trimIndent(),
        LocalDate(2023, Month.MAY, 21),
        setOf(
            Attribute.Platform(Platforms.Desktop),
            Attribute.JointDevelopment,
            Attribute.Award("Kloudハッカソン#2 最優秀賞"),
            Attribute.Award("Kloudハッカソン#2 参加者投票賞")
        ),
        null,
        "https://github.com/naotiki/chiiugo",
        "/images/works/chiiugo/1.png"
    ) {
        DetailHeader {
            +it
        }
        Carousel {
            sx {
                height = 50.vh
            }
            CarouselItem {
                dangerousRawHtml {
                    sx {
                        maxWidth = 100.pct
                        margin=Auto.auto
                        height=100.pct
                        textAlign= TextAlign.center
                    }
                    rawHtml =
                        """<iframe class="speakerdeck-iframe" frameborder="0" src="https://speakerdeck.com/player/417741acf2a948d48adc3dc5d170135e" title="ちぃうご" allowfullscreen="true" style="border: 0px; background: padding-box padding-box rgba(0, 0, 0, 0.1); margin: auto; padding: 0px; border-radius: 6px; box-shadow: rgba(0, 0, 0, 0.2) 0px 5px 40px; width: auto; height: 100%; aspect-ratio: 560/314;" data-ratio="1.78343949044586"></iframe>"""
                }
            }
            CarouselItem{
                CarouselImg{
                    src="/images/works/chiiugo/1.png"
                }
            }
        }

    },
    WorkItem(
        "Easy Shell Environment",
        """
        拡張可能で簡単に操作できるシェルアプリ
    """.trimIndent(),
        LocalDate(2023, Month.JANUARY, 30),
        setOf(
            Attribute.Platform(Platforms.Desktop),
            Attribute.Platform(Platforms.Android),
            Attribute.Platform(Platforms.Web),
        ),
        "https://ese.naotiki.me",
        "https://github.com/naotiki/Ese",
        "/images/works/ese/1.png"
    ) {
        DetailHeader {
            +it
        }
        Carousel {
            sx {
                height = 50.vh
            }
            CarouselItem {
                CarouselImg {
                    src = "/images/works/ese/1.png"
                }
            }
            CarouselItem {
                CarouselImg {
                    src = "/images/works/ese/2.png"
                }
            }
            CarouselItem {
                CarouselImg {
                    src = "/images/works/ese/3.png"
                }
            }
            CarouselItem {
                CarouselImg {
                    src = "/images/works/ese/4.png"
                }
            }
        }
    },
    WorkItem(
        "たのみ〜る", """
        お手軽食券販売システム
    """.trimIndent(),
        LocalDate(2022, Month.SEPTEMBER, 30),
        setOf(
            Attribute.Award("Civictech Challenge Cup 2022 TIS賞"),
            Attribute.Platform(Platforms.Web),
            Attribute.JointDevelopment
        ),
        null, null
    ) {
        DetailHeader {
            +it
        }
        Carousel {
            sx {
                height = 50.vh
            }
            CarouselItem {
                dangerousRawHtml {
                    sx {
                        width = 100.pct
                        height = 100.pct
                        aspectRatio = "16/9".unsafeCast<AspectRatio>()
                    }
                    rawHtml =
                        "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/Lh3CK4Jy8U8?start=4400\" title=\"CCC U-22 2022_最終審査会\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>"
                }
            }
        }
    },
    WorkItem(
        "Draw R4sh",
        """一瞬でお絵かきバトル！！！
            |あなたの絵心を見せつけよう！！！""".trimMargin(), LocalDate(2022, Month.MAY, 5),
        setOf(
            Attribute.Award("YUMEMI杯 高専ハッカソン2022春　最優秀賞"),
            Attribute.Platform(Platforms.Web),
            Attribute.JointDevelopment
        ),
        "https://draw-rush.web.app", "https://github.com/Yumemi-Hackathon22-D/DRAW-RUSH"
    ) {
        DetailHeader {
            +it
        }
    },
)


val DetailHeader = FC<WorkItemProps> {
    val theme = useTheme<Theme>()
    Stack {
        direction = responsive(Breakpoint.md to StackDirection.row, Breakpoint.sm to StackDirection.column)

        sx {
            justifyContent = JustifyContent.spaceBetween
        }
        Typography {
            +it.workItem.name
            variant = TypographyVariant.h3
        }
        Stack {
            direction = responsive(StackDirection.row)
            spacing = responsive(2)
            sx {
                alignItems = AlignItems.center
            }
            if (it.workItem.artifactUrl != null)
                Button {
                    component(NewTabAnchor) {
                        href = it.workItem.artifactUrl
                    }
                    variant = ButtonVariant.contained
                    startIcon = OpenInBrowser.create()
                    +"Open Website"
                }
            if (it.workItem.repoUrl != null)
                Button {
                    component(NewTabAnchor) {
                        href = it.workItem.artifactUrl
                    }
                    variant = ButtonVariant.outlined
                    href = it.workItem.repoUrl
                    startIcon = GitHub.create()
                    +"GitHub"
                }
        }
    }
    Typography {
        +it.workItem.update.toString()
        variant = TypographyVariant.body2
    }
    Stack {
        direction = responsive(StackDirection.column)
        spacing = responsive(2)
        sx {
            margin = Margin(10.px, Auto.auto)
            width = 50.vw
        }
        if (it.workItem.sortedAttributes.contains(Attribute.JointDevelopment))
            Stack {
                direction = responsive(Breakpoint.md to StackDirection.row, Breakpoint.sm to StackDirection.column)
                TextWithIcon {
                    css {
                        padding = 10.px
                        border = Border(2.px, LineStyle.solid, theme.palette.primary.main)
                        borderRadius = 45.px
                    }
                    icon = Group.create()
                    label = ReactNode("Joint Development")
                }
                flexSpacer()
            }
        val awards = it.workItem.sortedAttributes.filterIsInstance<Attribute.Award>()
        if (awards.isNotEmpty())
            Stack {
                direction = responsive(Breakpoint.md to StackDirection.row, Breakpoint.sm to StackDirection.column)
                sx { alignItems = AlignItems.center }
                spacing = responsive(2)
                TextWithIcon {
                    css {
                        padding = 10.px
                        border = Border(2.px, LineStyle.solid, theme.palette.primary.main)
                        borderRadius = 45.px
                    }
                    icon = EmojiEvents.create()
                    label = ReactNode("Awards")
                }
                flexSpacer()



                Stack {
                    direction = responsive(StackDirection.row)
                    spacing = responsive(2)
                    divider = Divider.create {
                        orientation = Orientation.vertical
                        flexItem = true
                    }
                    sx {
                        flexWrap = FlexWrap.nowrap
                        wordBreak = WordBreak.keepAll
                        textAlign = TextAlign.center
                    }
                    awards.forEach {
                        div {
                            css {
                                display = Display.inlineFlex
                                flexWrap = FlexWrap.wrap
                                justifyContent = JustifyContent.center
                            }
                            +it.name
                        }
                    }
                }
            }
        val platforms = it.workItem.sortedAttributes.filterIsInstance<Attribute.Platform>()
        if (platforms.isNotEmpty())
            Stack {
                direction = responsive(Breakpoint.md to StackDirection.row, Breakpoint.sm to StackDirection.column)
                sx { alignItems = AlignItems.center }
                spacing = responsive(2)
                TextWithIcon {
                    css {
                        padding = 10.px
                        border = Border(2.px, LineStyle.solid, useTheme<Theme>().palette.primary.main)
                        borderRadius = 45.px
                    }
                    icon = Devices.create()
                    label = ReactNode("Platforms")
                }
                flexSpacer()
                Stack {
                    direction = responsive(StackDirection.row)
                    spacing = responsive(2)
                    divider = Divider.create {
                        orientation = Orientation.vertical
                        flexItem = true
                    }
                    sx {
                        flexWrap = FlexWrap.nowrap
                        wordBreak = WordBreak.keepAll
                        textAlign = TextAlign.center
                    }
                    platforms.forEach {
                        TextWithIcon {
                            icon = it.platform.icon.create()
                            label = ReactNode(it.platform.name)
                        }
                    }
                }
            }
    }
}