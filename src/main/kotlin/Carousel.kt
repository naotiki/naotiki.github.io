import csstype.PropertiesBuilder
import emotion.react.css
import emotion.styled.styled
import js.core.jso
import mui.icons.material.ArrowBack
import mui.icons.material.ArrowForward
import mui.material.*
import mui.material.styles.Theme
import mui.material.styles.useTheme
import mui.system.PropsWithSx
import mui.system.SxProps
import mui.system.responsive
import mui.system.sx
import react.*
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.img
import usehooks.useWindowSize
import web.cssom.*
import web.html.HTMLDivElement

private data class CarouselState(
    var itemIndex: Int = 0,
    val itemList: List<String> = emptyList()
)

private sealed interface CarouselAction {
    object IncrementIndex : CarouselAction
    object DecrementIndex : CarouselAction
    class SetIndex(val index: Int) : CarouselAction
    class Add(val key: String) : CarouselAction
    class Remove(val key: String) : CarouselAction
}

val CarouselImg = img.styled {
    width = 100.pct
    height = 100.pct
    objectFit= ObjectFit.contain
    /*display = Display.block
    marginRight = Auto.auto
    marginLeft = Auto.auto*/
}
private val carouselReducer: Reducer<CarouselState, CarouselAction> = { a, b ->
    when (b) {
        is CarouselAction.Add -> {
            a.copy(itemList = a.itemList + b.key)
        }

        is CarouselAction.Remove -> {
            val l = a.itemList.toMutableList()
            l.remove(b.key)
            a.copy(
                itemIndex = a.itemIndex.coerceIn(l.indices),
                itemList = l
            )
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

external interface CarouselProps : PropsWithChildren, PropsWithSx{
    var smoothScroll:Boolean?
}

val Carousel = FC<CarouselProps> {

    val stackRef = createRef<HTMLDivElement>()
    val reducer = useReducer(carouselReducer, CarouselState())
    val (state, dispatch) = reducer
    val theme = useTheme<Theme>()
    val windowSize = useWindowSize()
    Stack {
        direction = responsive(StackDirection.row)
        sx {
            // position = Position.relative
            margin=Auto.auto
            overflow = Overflow.hidden
            +it.sx
        }
        Tooltip {
            title = ReactNode("Previous")
            IconButton {
                sx {
                    //   position = Position.absolute
                    /*   left = 20.px
                       top = 50.pct
                       transform = translatey((-50).pct)*/
                    margin=Auto.auto
                    height = Length.fitContent
                }
                color = IconButtonColor.secondary
                onClick = {
                    dispatch(CarouselAction.DecrementIndex)
                }
                ArrowBack()
            }
        }

        Stack {
            ref = stackRef
            useEffect(state, windowSize) {
                val r = stackRef.current
                println(state.itemIndex)
                r?.scrollTo(state.itemIndex * r.clientWidth, 0)
            }
            sx {
                overflowX = Overflow.hidden//.scroll
                scrollBehavior= if(it.smoothScroll != false)ScrollBehavior.smooth else Auto.auto
                alignItems = AlignItems.center
                height = 100.pct
                /* width = 100.pct
                 height=100.pct*/
            }
            direction = responsive(StackDirection.row)
            CarouselContext.Provider {
                value = reducer
                child(it.children)
            }

        }


        Tooltip {
            title = ReactNode("Next")
            IconButton {
                sx {
                    margin=Auto.auto
                    height = Length.fitContent
                    //  position = Position.absolute
                    /*right = 20.px
                    top = 50.pct
                    transform = translatey((-50).pct)*/
                }
                color = IconButtonColor.secondary
                onClick = {
                    dispatch(CarouselAction.IncrementIndex)
                }
                ArrowForward()
            }
        }
    }

    Stack {
        spacing = responsive(2)
        sx {
            marginTop = 10.px
            justifyContent = JustifyContent.center
        }
        direction = responsive(StackDirection.row)
        state.itemList.forEachIndexed { index, str ->
            div {
                key = str + index
                css {
                    width = 20.px
                    height = 20.px
                    background =
                        if (state.itemIndex == index) theme.palette.primary.main else theme.palette.text.primary
                    clipPath = circle()
                    cursor = Cursor.pointer
                }
                onClick = {
                    dispatch(CarouselAction.SetIndex(index))
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
                height = 100.pct
                overflow = Overflow.hidden
            }
            div{
                css {
                    display= Display.block
                    width=Auto.auto//Length.fitContent
                    margin=Auto.auto
                    height = 100.pct
                }
                child(it.children)
            }
        }
    }
