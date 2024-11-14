plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.modunuri"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.modunuri"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Retrofit and Gson dependencies
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.okhttp3:okhttp:4.9.0") // OkHttp
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.google.android.material:material:1.9.0")  // 또는 최신 버전
    implementation ("com.squareup.picasso:picasso:2.71828")
    implementation ("com.github.bumptech.glide:glide:4.15.1") // 최신 버전 확인 후 사용
    implementation ("com.kakao.sdk:v2-all:2.20.6") // 전체 모듈 설치, 2.11.0 버전부터 지원
    implementation ("com.kakao.sdk:v2-user:2.20.6") // 카카오 로그인 API 모듈
    implementation ("com.kakao.sdk:v2-share:2.20.6") // 카카오톡 공유 API 모듈
    implementation ("com.kakao.sdk:v2-talk:2.20.6") // 카카오톡 채널, 카카오톡 소셜, 카카오톡 메시지 API 모듈
    implementation ("com.kakao.sdk:v2-friend:2.20.6") // 피커 API 모듈
    implementation ("com.kakao.sdk:v2-navi:2.20.6") // 카카오내비 API 모듈
    implementation ("com.kakao.sdk:v2-cert:2.20.6") // 카카오톡 인증 서비스 API 모듈
    implementation(libs.retrofit)               // Retrofit
    implementation(libs.converter.gson)          // Gson converter
}