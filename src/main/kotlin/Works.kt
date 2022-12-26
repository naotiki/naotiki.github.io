import csstype.JustifyContent
import csstype.WhiteSpace
import mui.icons.material.*
import mui.material.*
import mui.material.styles.TypographyVariant
import mui.system.responsive
import mui.system.sx
import react.FC
import react.ReactNode
import react.create

val Works = FC<WelcomeProps> { props ->
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
            works.forEach {
                Grid {
                    item = true
                    asDynamic().sm=6
                    asDynamic().xs=12
                    Card {
                        CardHeader {
                            title = ReactNode(it.name)
                        }
                        CardContent {
                            Typography{
                                sx{
                                    whiteSpace= WhiteSpace.preWrap
                                }
                                +it.description
                            }
                            if (it.jointDevelopment) {
                                Stack{
                                    direction= responsive(StackDirection.row)
                                    spacing= responsive(1)
                                    Chip{
                                        size=Size.small
                                        label= ReactNode("共同開発")
                                        icon= Group.create()
                                    }
                                }
                            }
                        }
                        CardActions {

                            Button {
                                href=it.artifactUrl
                                startIcon= Launch.create()
                                +"開く"
                            }
                            if (it.githubUrl!=null){
                                Button {
                                    href=it.githubUrl
                                    startIcon= GitHub.create()
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