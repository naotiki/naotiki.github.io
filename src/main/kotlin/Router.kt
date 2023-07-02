import js.core.jso
import kotlinx.browser.document
import kotlinx.html.dom.append
import kotlinx.html.js.meta
import mui.material.Container
import mui.material.Typography
import mui.material.TypographyAlign
import mui.material.styles.TypographyVariant
import react.create
import react.router.RouteObject
import react.router.dom.createBrowserRouter

val appRouter = createBrowserRouter(
    arrayOf(
        jso {
            element = AppLayout.create()
            children=(PageRoutes.values().map {
                jso<RouteObject> {
                    if (it.path=="/"){
                        index=true
                    }else{
                        path=it.path
                    }
                    element=it.page.create()
                }
            }+works.map{
                jso {
                    path = "/works/${it.itemUrlComponent}"
                    element = WorkDetailWrapper.create {
                        child( it.createDetail() )
                    }
                }
            }+jso<RouteObject>{
                path="*"
                loader={
                    console.error("404 Not Found")
                    document.head!!.append.meta {
                        name="robots"
                        content="noindex"
                    }
                }
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
            }).toTypedArray()
        }
    )
)