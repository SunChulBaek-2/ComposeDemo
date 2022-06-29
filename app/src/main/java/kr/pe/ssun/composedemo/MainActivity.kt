package kr.pe.ssun.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import kr.pe.ssun.composedemo.ShopDestinations.SHOP_PRODUCT_ID
import kr.pe.ssun.composedemo.ui.ShopDetail
import kr.pe.ssun.composedemo.ui.ShopSearch
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
