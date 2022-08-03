import kotlinx.browser.document
import react.create
import react.createElement
import react.dom.client.createRoot
import react.router.Route
import react.router.Routes
import react.router.dom.BrowserRouter

fun main() {
    val container = document.createElement("div")
    document.body!!.appendChild(container)



    createRoot(container).render(BrowserRouter.create {
        Routes {
            Route {
                path = "/"
                element = createElement(AppLayout)
                Route {
                    index = true
                    element = createElement(LandingPage)
                }
                /*Route {
                    path="/.bin"
                }*/
            }
        }
    })
}