import js.core.jso
import mui.material.CssBaseline
import mui.material.PaletteMode
import mui.material.styles.ThemeProvider
import mui.material.styles.createTheme
import react.*
import react.router.Route
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


                react.router.Routes {

                    Route {
                        //   path = "/"
                        element = createElement(AppLayout)

                        Route {
                            index = true
                            element = createElement(LandingPage)

                        }
                        Route {
                            path = "/artifacts"
                            element = createElement(Artifacts)
                        }


                    }

                }

            }

        }
    }
}