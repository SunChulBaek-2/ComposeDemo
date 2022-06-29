package kr.pe.ssun.composedemo.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@Composable
fun ShopDetail(productId: String?) {
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