import csstype.PropertiesBuilder
import emotion.react.css
import js.core.jso
import mui.icons.material.ArrowBack
import mui.icons.material.ArrowForward
import mui.material.*
import mui.system.responsive
import mui.system.sx
import react.*
import react.dom.html.ReactHTML.img
import web.cssom.*
import web.html.HTMLDivElement

val Carousel = FC<PropsWithChildren> {
    val ref = createRef<HTMLDivElement>()
    val ref2 = createRef<HTMLDivElement>()
    Box {
        sx {
            position = Position.relative
            overflow = Overflow.hidden
        }
        IconButton {
            sx {
                position = Position.absolute
                left = 20.px

                top = 50.pct
                transform = translate((-50).pct, (-50).pct)
            }

            ArrowBack()
        }
        IconButton {
            sx {
                position = Position.absolute
                right = 20.px
                //top=0.px//50.pct
                //bottom=0.px
                //margin=Auto.auto

                top = 50.pct
                transform = translate((-50).pct, (-50).pct)
            }
            onClick = {
                ref2.current?.scrollIntoView()
            }
            ArrowForward()
        }
        Stack {
            this.ref = ref
            sx {
                overflowX = Overflow.scroll
                alignItems = AlignItems.center
                width = 100.pct
                set<PropertiesBuilder>(Variable("&::-webkit-scrollbar"), jso {
                    display = None.none
                })
            }
            direction = responsive(StackDirection.row)

            CarouselItem {
                img {
                    css {
                        display = Display.block
                        marginRight = Auto.auto
                        marginLeft = Auto.auto
                    }
                    src = "https://images.wantedly.com/i/TMzN3hm?w=720"
                }
            }

            CarouselItem {

                img {
                    css {
                        display = Display.block
                        marginRight = Auto.auto
                        marginLeft = Auto.auto
                    }
                    src = "https://images.wantedly.com/i/fxmGME9?w=720"
                }
            }

            CarouselItem {

                this.ref=ref2
                img {
                    css {
                        display = Display.block
                        marginRight = Auto.auto
                        marginLeft = Auto.auto
                    }
                    src = "https://images.wantedly.com/i/ff2eh8h?w=720"
                }
            }
        }
    }
}

interface PropsChildAndRef<T : Any> : PropsWithChildren, PropsWithRef<T>

val CarouselItem = forwardRef<HTMLDivElement, PropsChildAndRef<HTMLDivElement>> { p, ref ->
    +FC<PropsWithChildren> {
        Box {
            this.ref=ref
            sx {
                minWidth = 100.pct
            }
            child(it.children)
        }
    }.create {
        +p.children
    }
}