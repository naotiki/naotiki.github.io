import js.core.jso
import mui.material.*
import mui.material.styles.ThemeProvider
import mui.material.styles.TypographyVariant
import mui.material.styles.createTheme
import react.*
import react.router.Route
import react.router.Routes
import react.router.dom.BrowserRouter

val App = FC<Props> {
    ColorModeContext.Provider {
        val (colorModeState, setColorModeState) = useState(PaletteMode.light)
        val colorMode = useMemo(Unit) {
            ColorModeContextData {
                setColorModeState {
                    if (it == PaletteMode.light) PaletteMode.dark
                    else PaletteMode.light
                }
            }
        }
        val theme = useMemo(colorModeState) {
            createTheme(jso {
                palette = jso {
                    mode = colorModeState
                }
            })
        }
        value = colorMode
        ThemeProvider {
            this.theme = theme
            CssBaseline()
            BrowserRouter {
                Routes {
                    Route {
                        element = createElement(AppLayout)

                        Route {
                            index = true
                            element = createElement(LandingPage)
                        }
                        Route {
                            path = "/works"
                            element = createElement(Works)
                        }
                        Route {
                            path = "*"
                            element =
                                Container.create{
                                    Typography {
                                        variant=TypographyVariant.h1
                                        align=TypographyAlign.center
                                        +"404 Not Found"
                                    }
                                    Typography {
                                        variant=TypographyVariant.h6
                                        align=TypographyAlign.center
                                        +"ページが見つかりませんでした"
                                    }
                                }
                        }
                    }

                }

            }

        }
    }
}