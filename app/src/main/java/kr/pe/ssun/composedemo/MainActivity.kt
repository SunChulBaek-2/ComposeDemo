package kr.pe.ssun.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kr.pe.ssun.composedemo.data.model.Photo
import kr.pe.ssun.composedemo.ui.theme.ComposeDemoTheme
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                    val (button, text, column) = createRefs()
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

                    val photos by mainViewModel.photos.collectAsState()
                    photoList(modifier = Modifier.fillMaxWidth().padding(top = 60.dp).constrainAs(column) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }, photos = photos?.photos)
                }
            }
        }

        lifecycleScope.launch {
            mainViewModel.getPhotos()
        }
    }
}

@Composable
fun photoList(modifier: Modifier, photos: List<Photo>?) {
    LazyColumn(modifier = modifier) {
        photos?.forEach {
            item {
                Text(text = it.id.toString())
            }
        }
    }
}