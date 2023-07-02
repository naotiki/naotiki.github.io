package components

import emotion.css.cx
import emotion.react.css
import emotion.styled.styled
import mui.material.Stack
import mui.system.responsive
import react.*
import react.dom.html.ReactHTML.div
import web.cssom.*

external interface TextWithIconProps:PropsWithClassName{
    var icon:ReactNode?
    var label:ReactNode?
    var spacing:AutoLength?
}
val TextWithIcon= FC<TextWithIconProps>{
    div {
        css {
            display = Display.inlineFlex
            flexWrap = FlexWrap.wrap
            justifyContent = JustifyContent.center
            alignItems= AlignItems.center
        }
        className = cx(className,it.className)
        child(it.icon)
        div{css { margin= Margin(Auto.auto,it.spacing?:2.px) }}
        child(it.label)
    }
}