package kr.pe.ssun.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import dagger.hilt.android.AndroidEntryPoint
import kr.pe.ssun.composedemo.ShopDestinations.SHOP_PRODUCT_ID
import kr.pe.ssun.composedemo.data.model.ShopItem
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

object ShopDestinations {
    const val SHOP_SEARCH = "search"
    const val SHOP_DETAIL = "detail"
    const val SHOP_PRODUCT_ID = "productId"
}

class AppActions(
    navController: NavHostController
) {
    val selectedShop: (String)->Unit = { productId ->
        navController.navigate("${ShopDestinations.SHOP_DETAIL}/$productId")
    }
}

@Composable
private fun ComposeDemoApp(mainViewModel: MainViewModel = viewModel()) {
    val navController = rememberNavController()
    val actions = remember(navController) { AppActions(navController) }
    val startDestination = ShopDestinations.SHOP_SEARCH

    NavHost(navController = navController, startDestination = startDestination) {
        // 검색 목록
        composable(route = ShopDestinations.SHOP_SEARCH) {
            ShopSearch(mainViewModel, selectedShop = actions.selectedShop)
        }
        // 상세화면
        composable(
            route = "${ShopDestinations.SHOP_DETAIL}/{$SHOP_PRODUCT_ID}",
            arguments = listOf(
                navArgument(SHOP_PRODUCT_ID) {
                    type = NavType.StringType
                }
            )) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            ShopDetail(arguments.getString(SHOP_PRODUCT_ID))
        }
    }
}

@Composable
private fun ShopDetail(productId: String?) {
    val url by remember { mutableStateOf("http://search.shopping.naver.com/product/${productId}") }
    val state = rememberWebViewState(url = url)
    WebView(
        state = state,
        modifier = Modifier.fillMaxSize(),
        onCreated = { webView ->
            webView.settings.javaScriptEnabled = true
        }
    )
}

@Composable
private fun ShopSearch(
    mainViewModel: MainViewModel = viewModel(),
    selectedShop: (String)->Unit
) {
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
            mainViewModel.items.forEach { photo ->
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