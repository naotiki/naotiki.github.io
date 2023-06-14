import mui.icons.material.CalendarMonth
import mui.icons.material.GitHub
import mui.icons.material.Group
import mui.icons.material.Launch
import mui.material.*
import mui.material.styles.TypographyVariant
import mui.system.responsive
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import react.create
import web.cssom.JustifyContent
import web.cssom.WhiteSpace

val WorksPage = FC<Props> {
    Container {
        Typography {
            variant = TypographyVariant.h4
            align = TypographyAlign.center
            +"つくったもの"
        }
        Grid {
            container = true
            spacing = responsive(2)
            sx {
                justifyContent = JustifyContent.flexStart
            }
            works.forEachIndexed {i,it->
                Grid {
                    item = true
                    asDynamic().sm = 6
                    asDynamic().xs = 12
                    Card {
                        CardHeader {
                            title = ReactNode(it.name)
                        }
                        CardContent {
                            Typography {
                                sx {
                                    whiteSpace = WhiteSpace.preWrap
                                }
                                +it.description
                            }

                            Stack {
                                direction = responsive(StackDirection.row)
                                spacing = responsive(1)

                                Chip {
                                    size = Size.small
                                    label = ReactNode(it.update.toString())
                                    icon = CalendarMonth.create()
                                }
                                if (it.jointDevelopment) {
                                    Chip {
                                        size = Size.small
                                        label = ReactNode("共同開発")
                                        icon = Group.create()
                                    }
                                }

                            }
                        }
                        CardActions {
                            Button {
                                component(Link){
                                    href="/works/$i"
                                }
                                startIcon = Launch.create()
                                +"詳細"
                            }
                            Button {
                                href = it.artifactUrl
                                startIcon = Launch.create()
                                +"作品を開く"
                            }
                            if (it.repoUrl != null) {
                                Button {
                                    href = it.repoUrl
                                    startIcon = GitHub.create()
                                    +"GitHub"
                                }
                            }
                        }
                    }
                }
            }

        }
    }

}