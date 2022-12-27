import hooks.useDarkMode
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
    DarkModeContext.Provider {
        val a=useDarkMode()
        val theme = useMemo(a.isDarkMode) {
            createTheme(jso {
                palette = jso {
                    mode = if (a.isDarkMode) PaletteMode.dark
                    else PaletteMode.light
                }
            })
        }
        value = a
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