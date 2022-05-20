package kr.pe.ssun.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import kr.pe.ssun.composedemo.ui.theme.ComposeDemoTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                    val (button, text) = createRefs()
                    Button(onClick = {},
                        modifier = Modifier
                            .width(100.dp)
                            .height(60.dp)
                            .constrainAs(button) {
                                top.linkTo(parent.top)
                                end.linkTo(parent.end)
                            }) {
                        Text("Search")
                    }

                    var textState by remember { mutableStateOf(TextFieldValue("")) }
                    TextField(
                        value = textState,
                        onValueChange = { textState = it },
                        modifier = Modifier.constrainAs(text) {
                            top.linkTo(button.top)
                            bottom.linkTo(button.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(button.start)
                        }
                    )
                }
            }
        }
    }
}