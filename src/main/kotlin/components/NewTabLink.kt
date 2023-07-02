package components

import react.FC
import react.ForwardRef
import react.Props
import react.dom.html.AnchorHTMLAttributes
import react.dom.html.ReactHTML.a
import web.html.HTMLAnchorElement
import web.window.WindowTarget

val NewTabAnchor= ForwardRef<HTMLAnchorElement,AnchorHTMLAttributes<HTMLAnchorElement>> {it->

    a{
        target= WindowTarget._blank
        rel="noopener noreferrer"
        +it
    }
}/*FC<AnchorHTMLAttributes<HTMLAnchorElement>> {
    a{
        target= WindowTarget._blank
        rel="noopener noreferrer"
        +it
    }
}
*/