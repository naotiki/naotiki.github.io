import mui.material.*
import mui.system.responsive
import react.FC

val Artifacts= FC<WelcomeProps>{props->
    Grid{
        container=true
        Grid{
            item=true
            columns= responsive(1)
            Card{
                CardMedia{

                }
                CardActionArea{
                    CardContent {
                        +"Lorem ipsum dolor sit amet ex accusam lorem stet volutpat stet autem takimata. Ea kasd nobis magna tempor et est dolore elitr accumsan amet feugait ipsum. Nostrud kasd et autem lorem quis et et justo. Ea erat tempor. Magna vero et feugiat no sed in et elitr eos consequat exerci consetetur sed kasd sadipscing lorem et eleifend."
                    }
                }
                CardActions{
                    Button{
                        +"詳細"
                    }
                    Button{
                        +"開く"
                    }
                }
            }
        }

    }

}