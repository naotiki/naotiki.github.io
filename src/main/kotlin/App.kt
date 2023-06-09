import js.core.jso
import mui.material.*
import mui.material.styles.ThemeProvider
import mui.material.styles.TypographyVariant
import mui.material.styles.createTheme
import react.*
import react.router.IndexRoute
import react.router.LayoutRoute
import react.router.PathRoute
import react.router.Routes
import react.router.dom.BrowserRouter
import react.router.dom.HashRouter
import usehooks.UseDarkModeOutput
import usehooks.useDarkMode

val DarkModeContext = createContext<UseDarkModeOutput>()


enum class Pages(
    val page: ElementType<Props>, val path: String = "/", private val routeName: String? = null
) {
    About(AboutPage, "/"),
    Works(WorksPage, "/works"),
    Assets(AssetsPage, "/assets");

    fun getName() = routeName ?: name
}

val App = FC<Props> {
    DarkModeContext.Provider {
        val darkModeOutput = useDarkMode(null)
        val theme = useMemo(darkModeOutput.isDarkMode) {
            createTheme(jso {
                palette = jso {
                    mode = if (darkModeOutput.isDarkMode) PaletteMode.dark
                    else PaletteMode.light
                }
            })
        }
        value = darkModeOutput
        ThemeProvider {
            this.theme = theme
            CssBaseline()
            BrowserRouter {
                Routes {
                    LayoutRoute {
                        element = AppLayout.create()
                        Pages.values().forEach {
                            if (it.path == "/") {
                                IndexRoute {
                                    index = true
                                    element = it.page.create()
                                }
                            } else {
                                PathRoute {
                                    path = it.path
                                    element = it.page.create()
                                }
                            }

                        }
                        PathRoute {
                            path = "*"
                            element = Container.create {
                                Typography {
                                    variant = TypographyVariant.h1
                                    align = TypographyAlign.center
                                    +"404 Not Found"
                                }
                                Typography {
                                    variant = TypographyVariant.h6
                                    align = TypographyAlign.center
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