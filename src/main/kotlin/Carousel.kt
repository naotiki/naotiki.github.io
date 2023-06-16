import csstype.PropertiesBuilder
import emotion.react.css
import js.core.jso
import mui.icons.material.ArrowBack
import mui.icons.material.ArrowForward
import mui.material.*
import mui.material.styles.Theme
import mui.material.styles.useTheme
import mui.system.responsive
import mui.system.sx
import react.*
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.img
import web.cssom.*
import web.html.HTMLDivElement

private data class CarouselState(
    var itemIndex: Int = 0,
    val itemList:List<String> = emptyList()
)

private sealed interface CarouselAction {
    object IncrementIndex : CarouselAction
    object DecrementIndex : CarouselAction
    class SetIndex(val index: Int) : CarouselAction
    class Add(val key: String) : CarouselAction
    class Remove(val key: String) : CarouselAction
}

private val carouselReducer: Reducer<CarouselState, CarouselAction> = { a, b ->
    when (b) {
        is CarouselAction.Add -> {
            a.copy(itemList = a.itemList+b.key)
        }
        is CarouselAction.Remove -> {
            val l=a.itemList.toMutableList()
            l.remove(b.key)
            a.copy(itemIndex = a.itemIndex.coerceIn(l.indices),
                itemList = l)
        }

        is CarouselAction.SetIndex -> {
            a.copy(itemIndex = b.index.coerceIn(a.itemList.indices))
        }

        CarouselAction.DecrementIndex -> {
            a.copy(itemIndex = (a.itemIndex - 1).coerceIn(a.itemList.indices))
        }

        CarouselAction.IncrementIndex -> {
            a.copy(itemIndex = (a.itemIndex + 1).coerceIn(a.itemList.indices))
        }
    }
}
private val CarouselContext = createContext<ReducerInstance<CarouselState, CarouselAction>>()

val Carousel = FC<PropsWithChildren> {
    val stackRef = createRef<HTMLDivElement>()
    val reducer = useReducer(carouselReducer, CarouselState())
    val (state, dispatch) = reducer
    val theme= useTheme<Theme>()
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
                transform = translatey((-50).pct)
            }
            color= IconButtonColor.secondary
            onClick = {
                dispatch(CarouselAction.DecrementIndex)
            }
            ArrowBack()
        }
        IconButton {
            sx {
                position = Position.absolute
                right = 20.px
                top = 50.pct
                transform = translatey( (-50).pct)
            }
            color= IconButtonColor.secondary
            onClick = {
                dispatch(CarouselAction.IncrementIndex)
            }
            ArrowForward()
        }
        Stack {
            ref = stackRef
            useEffect(state) {
                val r = stackRef.current
                println(state.itemIndex)
                r?.scrollTo(state.itemIndex * r.clientWidth, 0)
            }
            sx {
                overflowX = Overflow.hidden//scroll
                alignItems = AlignItems.center
                width = 100.pct
                set<PropertiesBuilder>(Variable("&::-webkit-scrollbar"), jso {
                    display = None.none
                })
            }
            direction = responsive(StackDirection.row)
            CarouselContext.Provider {
                value = reducer
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
        Stack {
            spacing= responsive(2)
            sx {
                marginTop=10.px
                justifyContent= JustifyContent.center
            }
            direction = responsive(StackDirection.row)
            state.itemList.forEachIndexed { index, str ->
                div {
                    key=str+index
                    css {
                        width = 20.px
                        height = 20.px
                        background= if (state.itemIndex==index) theme.palette.primary.main else theme.palette.text.primary
                        clipPath= circle()
                    }
                }
            }
        }
    }
}

val CarouselItem =
    FC<PropsWithChildren> {
        val (_, dispatch) = useContext(CarouselContext)!!
        useEffectOnce {
            dispatch(CarouselAction.Add(it.key.toString()))
            cleanup {
                dispatch(CarouselAction.Remove(it.key.toString()))
            }
        }
        Box {
            this.ref = ref
            sx {
                minWidth = 100.pct
            }
            child(it.children)
        }
    }
