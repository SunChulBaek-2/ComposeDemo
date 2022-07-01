plugins {
    id("java-platform")
    id("maven-publish")
}

val accompanistWebview = "0.24.12-rc"
val activityCompose = "1.4.0"
val coil = "2.1.0"
val compose = "1.2.0-rc03"
val composeUi = "1.1.1"
val constraintLayoutCompose = "1.0.1"
val coreKtx = "1.7.0"
val espressoCore = "3.4.0"
val extJunit = "1.1.3"
val hilt = Versions.HILT_AGP
val junit = "4.13.2"
val lifecycle = "2.4.1"
val navigation = "2.4.2"
val okhttp = "4.9.3"
val retrofit = "2.9.0"
val timber = "5.0.1"

dependencies {
    constraints {
        api("${Libs.ACCOMPANIST_WEBVIEW}:$accompanistWebview")
        api("${Libs.ACTIVITY_COMPOSE}:$activityCompose")
        api("${Libs.COMPOSE_UI}:$compose")
        api("${Libs.COMPOSE_MATERIAL}:$compose")
        api("${Libs.COMPOSE_UI_TOOLING}:$composeUi")
        api("${Libs.COMPOSE_UI_TOOLING_PREVIEW}:$composeUi")
        api("${Libs.COMPOSE_UI_TEST_JUNIT}:$composeUi")
        api("${Libs.COMPOSE_UI_TEST_MANIFEST}:$composeUi")
        api("${Libs.CONSTRAINT_LAYOUT_COMPOSE}:$constraintLayoutCompose")
        api("${Libs.CORE_KTX}:$coreKtx")
        api("${Libs.COIL}:$coil")
        api("${Libs.ESPRESSO_CORE}:$espressoCore")
        api("${Libs.EXT_JUNIT}:$extJunit")
        api("${Libs.HILT_ANDROID}:$hilt")
        api("${Libs.HILT_COMPILER}:$hilt")
        api("${Libs.JUNIT}:$junit")
        api("${Libs.LIFECYCLE_RUNTIME_KTX}:$lifecycle")
        api("${Libs.LIFECYCLE_VIEW_MODEL_COMPOSE}:$lifecycle")
        api("${Libs.NAVIGATION_COMPOSE}:$navigation")
        api("${Libs.OKHTTP_LOGGING_INTERCEPTER}:$okhttp")
        api("${Libs.RETROFIT}:$retrofit")
        api("${Libs.RETROFIT_GSON_CONVERTER}:$retrofit")
        api("${Libs.TIMBER}:$timber")
    }
}
