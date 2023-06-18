import emotion.react.css
import emotion.styled.styled
import mui.icons.material.*
import mui.material.*
import mui.material.Link
import mui.material.Size
import mui.material.styles.Theme
import mui.material.styles.TypographyVariant
import mui.material.styles.useTheme
import mui.system.responsive
import mui.system.sx
import mui.types.PropsWithComponent
import react.FC
import react.Props
import react.ReactNode
import react.create
import react.dom.html.ReactHTML.div
import web.cssom.*

val flexSpacer = div.styled {
    flexGrow = 1.unsafeCast<FlexGrow>()
}
val WorksPage = FC<Props> {
    val theme = useTheme<Theme>()
    Container {
        maxWidth = "md"
        Typography {
            sx {
                margin = Margin(10.px, 0.px)
            }
            variant = TypographyVariant.h4
            align = TypographyAlign.center
            +"今までに作ったもの"
        }
        Grid {
            container = true
            spacing = responsive(2)
            sx {
                justifyContent = JustifyContent.flexStart
                alignItems = AlignItems.stretch
            }
            works.forEach {
                Grid {
                    item = true
                    asDynamic().sm = 6
                    asDynamic().xs = 12
                    WorkCard {
                        workItem=it
                    }
                }
            }
        }
    }
}

val WorkCard= FC<WorkItemProps> {
    val theme=useTheme<Theme>()
    div {
        css {
            height = 100.pct
            if (it.workItem.thumbnailUrl != null) {
                backgroundImage = url(it.workItem.thumbnailUrl.toString())
                backgroundSize = BackgroundSize.cover
                backgroundColor = if (theme.palette.mode == PaletteMode.dark) {
                    rgb(0, 0, 0, 0.7)
                } else rgb(255, 255, 255, 0.7)
                //B3=A:0.7
                backgroundBlendMode = "color".unsafeCast<BackgroundBlendMode>()
                /*if (theme.palette.mode== PaletteMode.dark) {
                    "darken".unsafeCast<BackgroundBlendMode>()
                } else "lighten".unsafeCast<BackgroundBlendMode>()*/
            }
        }

        Card {
            component(Stack) {
                direction = responsive(StackDirection.column)
            }
            sx {
                height = 100.pct
                if (it.workItem.thumbnailUrl != null) {
                    backgroundColor = rgb(0, 0, 0, 0.0)
                    backdropFilter = blur(2.px)
                }
                //      justifyContent= JustifyContent.spaceBetween
            }

            CardActionArea {
                unsafeProps<PropsWithComponent> {
                    component(Link) {
                        href = "/works/${it.workItem.itemUrlComponent}"
                    }
                }
                sx {
                    flexGrow=1.asDynamic()
                }
                CardHeader {
                    title = ReactNode(it.workItem.name)
                    subheader = ReactNode(it.workItem.update.toString())
                }

                CardContent {
                    component(Stack) {
                        spacing = responsive(1)
                    }


                    Stack {
                        direction = responsive(StackDirection.row)
                        spacing = responsive(1)
                        asDynamic().useFlexGap = true
                        sx {
                            flexWrap = FlexWrap.wrap
                        }
                        it.workItem.sortedAttributes.forEach {
                            Chip {
                                size = Size.small

                                when (it) {
                                    is Attribute.Award -> {
                                        label = ReactNode(it.name)
                                        icon = EmojiEvents.create()
                                    }

                                    Attribute.JointDevelopment -> {
                                        label = ReactNode("共同開発")
                                        icon = Group.create()
                                    }

                                    is Attribute.Platform -> {
                                        label = ReactNode(it.platform.name)
                                        icon = it.platform.icon.create()
                                    }

                                    is Attribute.Stack -> TODO()
                                }
                            }
                        }
                    }
                    Typography {
                        variant = TypographyVariant.body2
                        sx {
                            color = theme.palette.text.secondary
                            whiteSpace = WhiteSpace.preWrap
                        }
                        +it.workItem.description
                    }
                }
                flexSpacer()
            }
            CardActions {
                if (it.workItem.hasDetailPage) {
                    Button {
                        component(Link) {
                            href = "/works/${it.workItem.itemUrlComponent}"
                        }
                        startIcon = Info.create()
                        +"詳細"
                    }
                }
                if (it.workItem.artifactUrl != null) {
                    Button {
                        href = it.workItem.artifactUrl
                        startIcon = Launch.create()
                        +"作品を開く"
                    }
                }
                if (it.workItem.repoUrl != null) {
                    IconButton {
                        color = IconButtonColor.primary
                        asDynamic().href = it.workItem.repoUrl
                        GitHub()
                    }
                }
            }
        }
    }
}