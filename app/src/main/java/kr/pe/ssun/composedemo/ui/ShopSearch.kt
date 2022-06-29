package kr.pe.ssun.composedemo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import kr.pe.ssun.composedemo.MainViewModel
import kr.pe.ssun.composedemo.R
import kr.pe.ssun.composedemo.data.model.ShopItem

@Composable
fun ShopSearch(
    mainViewModel: MainViewModel = viewModel(),
    selectedShop: (String)->Unit
) {
    val uiState = mainViewModel.uiState

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.app_name)) },
            modifier = Modifier,
            backgroundColor = Color.Blue,
            contentColor = Color.White,
            elevation = 10.dp
        )
        Row(
        ) {
            var text by remember { mutableStateOf("") }
            TextField(modifier = Modifier.weight(1f), value = text, onValueChange = { text = it })
            Button(modifier = Modifier,
                onClick = {
                    mainViewModel.getShop(text)
                }) { Text(text = "검색") }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            uiState.items.forEach { photo ->
                item {
                    ShopItemView(item = photo) {
                        selectedShop(photo.id)
                    }
                }
            }
        }
    }
}

@Composable
fun ShopItemView(item: ShopItem, onClick: () -> Unit) {
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
                    .data(item.image)
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
            text = buildAnnotatedString {
                append(item.title.substring(0, item.title.indexOf("<b>")))
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(item.title.substring(item.title.indexOf("<b>") + 3, item.title.indexOf("</b>")))
                }
                append(item.title.substring(item.title.indexOf("</b>") + 4))
            }
        )
    }
}