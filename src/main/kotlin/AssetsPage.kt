import csstype.AlignItems
import csstype.TextAlign
import csstype.px
import emotion.react.css
import mui.material.Container
import mui.material.Link
import mui.material.Stack
import mui.material.StackDirection
import mui.system.Breakpoint
import mui.system.responsive
import mui.system.sx
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.img

val AssetsPage = FC<Props> {
    Container {

        CCBySA()
    }

}

val CCBySA = FC<Props> {
    Stack {
        direction = responsive(Breakpoint.xs to StackDirection.column, Breakpoint.sm to StackDirection.row)
        spacing = responsive(2)
        sx { alignItems = AlignItems.center }
        Link {
            rel = "license"
            href = "https://creativecommons.org/licenses/by/4.0/"
            img {
                alt = "クリエイティブ・コモンズ・ライセンス"
                css { borderWidth = 0.px }
                src = "https://i.creativecommons.org/l/by/4.0/88x31.png"
            }
        }
        div {
            css {
                textAlign = TextAlign.center
            }
            +"これらの 作品 は"
            Link {
                rel = "license"
                href = "https://creativecommons.org/licenses/by/4.0/"
                +"クリエイティブ・コモンズ 表示 4.0 国際 ライセンス"
            }
            +"の下に提供されています。"
        }
    }
}