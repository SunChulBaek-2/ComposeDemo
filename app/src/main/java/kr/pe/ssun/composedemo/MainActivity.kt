package kr.pe.ssun.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import dagger.hilt.android.AndroidEntryPoint
import kr.pe.ssun.composedemo.data.model.Photo
import kr.pe.ssun.composedemo.ui.theme.ComposeDemoTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                ComposeDemoApp()
            }
        }
    }
}

@Composable
private fun ComposeDemoApp(mainViewModel: MainViewModel = viewModel()) {
    LaunchedEffect(true) {
        mainViewModel.search()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .background(color = Color.Black)
                .padding(start = 10.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(text = stringResource(id = R.string.app_name),
            color = Color.White)
        }
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)) {
            mainViewModel.searchResult.forEach { photo ->
                item {
                    PhotoView(item = photo) {
                        // TODO : 상세화면 이동
                    }
                }
            }
        }
    }
}

@Composable
fun PhotoView(item: Photo, onClick: () -> Unit) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .border(width = 1.dp, color = Color.LightGray)
        .clickable { onClick.invoke() }) {

        Image(
            modifier = Modifier
                .width(100.dp)
                .height(100.dp),
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.thumbnailUrl)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop
            ),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop
        )

        Text(
            modifier = Modifier
                .padding(start = 110.dp, top = 10.dp, end = 10.dp, bottom = 10.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            text = item.title
        )
    }
}