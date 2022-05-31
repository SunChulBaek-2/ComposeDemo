package kr.pe.ssun.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kr.pe.ssun.composedemo.data.model.ShopItem
import kr.pe.ssun.composedemo.ui.theme.ComposeDemoTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                ComposeDemoApp()
            }
        }
    }

    @Composable
    private fun ComposeDemoApp() {
        val coroutineScope = rememberCoroutineScope()

        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.wrapContentHeight()) {
                var textState by remember { mutableStateOf(TextFieldValue("")) }
                TextField(
                    value = textState,
                    onValueChange = { textState = it },
                    modifier = Modifier.weight(1f)
                )
                Button(
                    onClick = {
                        coroutineScope.launch {
                            mainViewModel.search(textState.text)
                        }
                    },
                    modifier = Modifier.wrapContentSize()
                ) {
                    Text("Search")
                }
            }
            val photos by mainViewModel.photos.collectAsState()
            SearchList(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                shopItems = photos?.photos
            )
        }
    }
}

@Composable
fun SearchList(modifier: Modifier, shopItems: List<ShopItem>?) {
    LazyColumn(modifier = modifier) {
        shopItems?.forEach { shopItem ->
            item(shopItem.productId) {
                ShopItem(shopItem = shopItem)
            }
        }
    }
}

@Composable
fun ShopItem(shopItem: ShopItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable { },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = shopItem.image,
            contentDescription = shopItem.title,
            modifier = Modifier
                .padding(start = 10.dp)
                .width(100.dp)
                .height(100.dp),
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop,
        )
        Text(
            text = buildAnnotatedString {
                val indexOfOpenTag = shopItem.title.indexOf("<b>")
                val indexOfCloseTag = shopItem.title.indexOf("</b>")
                if (indexOfOpenTag >= 0) {
                    append(shopItem.title.substring(0, indexOfOpenTag))
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(
                            shopItem.title.substring(
                                indexOfOpenTag + "<b>".length,
                                indexOfCloseTag
                            )
                        )
                    }
                    append(
                        shopItem.title.substring(
                            indexOfCloseTag + "</b>".length,
                            shopItem.title.length
                        )
                    )
                } else {
                    append(shopItem.title)
                }
            },
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}