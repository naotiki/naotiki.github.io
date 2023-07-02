import components.ExperimentalDangerousRawHTML
import components.NewTabAnchor
import components.TextWithIcon
import components.dangerousRawHtml
import emotion.react.css
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import mui.icons.material.Devices
import mui.icons.material.EmojiEvents
import mui.icons.material.GitHub
import mui.icons.material.Group
import mui.icons.material.Layers
import mui.icons.material.OpenInBrowser
import mui.icons.material.OpenInNew
import mui.material.Button
import mui.material.ButtonVariant
import mui.material.Container
import mui.material.Divider
import mui.material.Orientation
import mui.material.Stack
import mui.material.StackDirection
import mui.material.Typography
import mui.material.styles.Theme
import mui.material.styles.TypographyVariant
import mui.material.styles.useTheme
import mui.material.useMediaQuery
import mui.system.Breakpoint
import mui.system.responsive
import mui.system.sx
import react.FC
import react.ReactNode
import react.create
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.iframe
import react.dom.html.ReactHTML.img
import web.cssom.AlignItems
import web.cssom.AspectRatio
import web.cssom.Auto
import web.cssom.Border
import web.cssom.Display
import web.cssom.FlexWrap
import web.cssom.JustifyContent
import web.cssom.LineStyle
import web.cssom.Margin
import web.cssom.None
import web.cssom.TextAlign
import web.cssom.WordBreak
import web.cssom.pct
import web.cssom.px
import web.cssom.rem
import web.cssom.vh
import web.cssom.vmin
import web.cssom.vw
import web.html.Loading

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
            Attribute.Award("Kloudハッカソン#2 参加者投票賞"),
            Attribute.Stack(Stacks.Kotlin),
            Attribute.Stack(Stacks.Compose),
        ),
        null,
        "https://github.com/naotiki/chiiugo",
        "/images/works/chiiugo/1.webp"
    ) {
        val isDownSm = useMediaQuery<Theme>({
            it.breakpoints.down(Breakpoint.sm)
        })
        DetailHeader {
            +it
        }
        Container {
            maxWidth = "md"
            Typography {
                variant = TypographyVariant.body1
                +"""
                「ちぃうご」こと「なんかちぃさくてうごいてるやつ」は、デスクトップ上をかわいいキャラが動き回るアプリです。
                IntelliJやVSCodeなど各種エディタとプラグインや拡張機能を介して連携し、ビルド失敗やタイピングに応じて反応してくれます。
                """.trimIndent()
            }
            Carousel {
                sx {
                    marginTop = 50.px
                    height = 50.vmin
                }
                CarouselItem {
                    styledDangerousRawHtml {
                        sx {
                            aspectRatio = (16.0 / 9.0).unsafeCast<AspectRatio>()
                        }
                        rawHtml =
                            """<iframe loading="lazy" width="100%" height="100%" src="https://www.youtube.com/embed/DOHeNfu2HlE" title="Chiiugo Demo" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>"""
                    }
                }
                CarouselItem {
                    styledDangerousRawHtml {
                        sx {
                            aspectRatio = (560.0 / 314.0).unsafeCast<AspectRatio>()
                        }
                        rawHtml =
                            """<iframe loading="lazy" width="100%" height="100%" class="speakerdeck-iframe" frameborder="0" src="https://speakerdeck.com/player/417741acf2a948d48adc3dc5d170135e" title="ちぃうご" allowfullscreen="true" style="border: 0px; background: padding-box padding-box rgba(0, 0, 0, 0.1); margin: auto; padding: 0px; border-radius: 6px; box-shadow: rgba(0, 0, 0, 0.2) 0px 5px 40px; " data-ratio="1.78343949044586"></iframe>"""
                    }
                }
                CarouselItem {
                    CarouselImg {
                        src = "/images/works/chiiugo/1.webp"
                    }
                }
            }
            Stack {
                spacing = responsive(2)
                Typography {
                    variant = TypographyVariant.h4
                    +"ちぃうご アプリ"
                }
                Button {
                    variant = ButtonVariant.contained
                    component(NewTabAnchor) {
                        href = "https://github.com/naotiki/chiiugo/releases/latest"
                    }
                    startIcon = OpenInNew.create()
                    +"ちぃうご (Github Releases)"
                }
                Typography {
                    variant = TypographyVariant.h4
                    +"IntelliJ Plugin"
                }

                if (isDownSm) {
                    iframe {
                        loading = Loading.lazy
                        css {
                            border = None.none
                            width = 245.px
                            height = 48.px
                        }
                        src = "https://plugins.jetbrains.com/embeddable/install/21851"
                    }
                } else {
                    iframe {
                        loading = Loading.lazy
                        css {
                            border = None.none
                            width = 384.px
                            height = 319.px
                        }
                        src = "https://plugins.jetbrains.com/embeddable/card/21851"
                    }
                }



                Typography {
                    variant = TypographyVariant.h4
                    +"VSCode Extension"
                }
                Button {
                    variant = ButtonVariant.outlined
                    component(NewTabAnchor) {
                        href = "https://marketplace.visualstudio.com/items?itemName=naotiki.chiiugo-vsc"
                    }
                    startIcon = OpenInNew.create()
                    +"Chiiugo / ちぃうご (Visual Studio Marketplace)"
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
            Attribute.Stack(Stacks.Kotlin),
            Attribute.Stack(Stacks.Compose),
        ),
        "https://ese.naotiki.me",
        "https://github.com/naotiki/Ese",
        "/images/works/ese/1.webp"
    ) {
        DetailHeader {
            +it
        }
        Container {
            maxWidth = "md"
            Typography {
                variant = TypographyVariant.body1
                +"""
                「Easy Shell Environment (Ese)」は簡単で手軽に試せるシェルアプリです。仮想のファイル操作などができ、権限の設定までもできます。
                現在、拡張機能開発用SDKを公開しており、今後はSDKで開発された拡張機能を簡単にダウンロードできる機能を実装予定です。
                Compose Multiplatformを採用し、幅広いプラットフォームに対応しています。
                """.trimIndent()
            }
        }
        Carousel {
            sx {
                height = 50.vh
            }
            CarouselItem {
                CarouselImg {
                    src = "/images/works/ese/1.webp"
                }
            }
            CarouselItem {
                CarouselImg {
                    src = "/images/works/ese/2.webp"
                }
            }
            CarouselItem {
                CarouselImg {
                    src = "/images/works/ese/3.webp"
                }
            }
            CarouselItem {
                CarouselImg {
                    src = "/images/works/ese/4.webp"
                }
            }
        }
    },
    WorkItem(
        "たのみ〜る", """
        お手軽食券販売システム
    """.trimIndent(),
        LocalDate(2022, Month.SEPTEMBER, 17),
        setOf(
            Attribute.Award("Civictech Challenge Cup 2022 TIS賞"),
            Attribute.Platform(Platforms.Web),
            Attribute.JointDevelopment,
            Attribute.Stack(Stacks.Vue),
            Attribute.Stack(Stacks.Nuxt),
            Attribute.Stack(Stacks.Firebase),
        ),
        null, null
    ) {
        DetailHeader {
            +it
        }
        Container {
            maxWidth = "md"
            Typography {
                variant = TypographyVariant.body1
                +"""
                「たのみ～る」は簡単に導入できるキャッシュレス決済システムです。決済機能のほか注文・売上情報の管理、ユーザー(店舗の顧客)側での商品の売り切れ情報の確認などができます。
                キャッシュレス決済の敷居を下げた点が評価され、
                """.trimIndent()
                mui.material.Link {
                    href = "https://ccc2022.code4japan.org/"
                    +"Civictech Challenge Cup 2022"
                }
                +" TIS賞 を頂きました。"
            }
            Carousel {
                sx {

                    height = 50.vmin
                }
                CarouselItem {
                    styledDangerousRawHtml {
                        sx {
                            aspectRatio = (16.0 / 9.0).unsafeCast<AspectRatio>()
                        }
                        rawHtml =
                            """<iframe width="100%" height="100%" src="https://www.youtube.com/embed/Lh3CK4Jy8U8?start=4400" title="CCC U-22 2022_最終審査会" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>"""
                    }
                }
                CarouselItem {
                    CarouselImg {
                        src = "/images/works/tanomeal/1.webp"
                    }
                }
                CarouselItem {
                    CarouselImg {
                        src = "/images/works/tanomeal/2.webp"
                    }
                }
                CarouselItem {
                    CarouselImg {
                        src = "/images/works/tanomeal/3.webp"
                    }
                }
                CarouselItem {
                    CarouselImg {
                        src = "/images/works/tanomeal/4.webp"
                    }
                }
            }
        }
    },
    WorkItem(
        "Draw R4sh",
        """
        一瞬でお絵かきバトル！！！
        あなたの絵心を見せつけよう！！！
        """.trimIndent(), LocalDate(2022, Month.MAY, 5),
        setOf(
            Attribute.Award("YUMEMI杯 高専ハッカソン2022春　最優秀賞"),
            Attribute.Platform(Platforms.Web),
            Attribute.JointDevelopment,
            Attribute.Stack(Stacks.React),
            Attribute.Stack(Stacks.Firebase),
        ),
        "https://draw-rush.web.app", "https://github.com/Yumemi-Hackathon22-D/DRAW-RUSH"
    ){
        DetailHeader {
            +it
        }
        Container {
            maxWidth = "md"
            Typography {
                variant = TypographyVariant.body1
                +"""
                「Draw R4sh」はオンラインお絵かき推理ゲームです。5秒以内に指定されたお題の絵を書き、他の人が当てるという流れを繰り返します。
                リモートかつ初めて喋る人とでも楽しく遊べるアプリを目標に開発しました。
                """.trimIndent()
            }
        }
    },
)


val DetailHeader = FC<WorkItemProps> {
    val theme = useTheme<Theme>()
    Stack {
        direction = responsive(Breakpoint.md to StackDirection.row, Breakpoint.sm to StackDirection.column)

        sx {
            margin = Margin(10.px, Auto.auto)
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
                        href = it.workItem.repoUrl
                    }
                    variant = ButtonVariant.outlined
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
            margin = Margin(20.px, Auto.auto)
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
                    label = ReactNode("Award")
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
                    label = ReactNode("Platform")
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
        val stacks = it.workItem.sortedAttributes.filterIsInstance<Attribute.Stack>()
        if (stacks.isNotEmpty())
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
                    icon = Layers.create()
                    label = ReactNode("Stack")
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
                    stacks.forEach {
                        TextWithIcon {
                            icon = img.create {
                                css {
                                    width = 1.5.rem
                                    height = 1.5.rem
                                }
                                src = it.stack.url
                            }
                            label = ReactNode(it.stack.name)
                        }
                    }
                }
            }
    }
}