package components

import component
import mui.icons.material.ArrowForward
import mui.material.Link
import mui.material.LinkProps
import mui.system.sx
import react.ForwardRef
import remix.run.router.To
import web.cssom.px
import web.html.HTMLAnchorElement

external interface AppLinkProps : LinkProps{
    var to :To
    var routerLinkProps:react.router.dom.LinkProps
}
val AppLink= ForwardRef<HTMLAnchorElement,AppLinkProps> {it->
    Link {
        component(react.router.dom.Link){
            to=it.to
            +it.routerLinkProps
        }
        color="inherit"
        underline= mui.material.LinkUnderline.hover
        +it
        child(it.children)
    }
}