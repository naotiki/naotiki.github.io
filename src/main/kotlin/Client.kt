import browser.document
import js.core.jso
import mui.material.styles.createTheme
import react.create
import react.createElement
import react.dom.client.createRoot
import react.router.Route
import react.router.Routes
import react.router.dom.BrowserRouter
import react.router.dom.HashRouter
import react.router.dom.HistoryRouter

fun main() {
    val container = document.createElement("div").also {
        document.body.appendChild(it)
    }
    val theme = createTheme(options = jso {
    })


    createRoot(container).render(BrowserRouter.create {

        Routes {

            Route {
             //   path = "/"
                element = createElement(AppLayout)

                Route {
                    index = true
                    element = createElement(LandingPage)

                }
                Route {
                    path="/artifacts"
                    element = createElement(Artifacts)
                }


            }

        }


    })
}