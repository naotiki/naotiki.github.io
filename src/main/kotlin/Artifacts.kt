import csstype.JustifyContent
import csstype.WhiteSpace
import mui.material.*
import mui.material.styles.TypographyVariant
import mui.system.responsive
import mui.system.sx
import react.FC
import react.create

val Artifacts = FC<WelcomeProps> { props ->


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
                justifyContent = JustifyContent.center
            }
            artifacts.forEach {
                Grid {
                    item = true
                    columns = responsive(2)
                    Card {
                        CardMedia {

                        }
                        CardHeader {
                            title = Typography.create {
                                variant = TypographyVariant.h6
                                +it.name
                            }

                        }
                        CardContent {
                            Typography{
                                sx{
                                    whiteSpace= WhiteSpace.preWrap
                                }
                                +it.description
                            }
                        }
                        CardActions {
                            Button {
                                +"詳細"
                            }
                            Button {
                                +"開く"
                            }
                        }
                    }
                }
            }

        }
    }

}