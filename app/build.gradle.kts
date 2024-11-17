import java.util.Properties


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

        // KAKAO_MAP_KEY 설정
        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())
        buildConfigField("String", "KAKAO_MAP_KEY", "\"${properties.getProperty("KAKAO_MAP_KEY")}\"")

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

    // buildConfig 활성화
    buildFeatures {
        buildConfig = true
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
    implementation("com.squareup.okhttp3:okhttp:4.9.0") // OkHttp
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.google.android.material:material:1.9.0")  // Material Design
    implementation("com.squareup.picasso:picasso:2.71828")
    implementation("com.github.bumptech.glide:glide:4.15.1") // Glide for image loading
    implementation("com.kakao.maps.open:android:2.9.5") // Kakao Maps
}
