plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.feature.weathers"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        buildFeatures {
            viewBinding = true
            dataBinding = true
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":common"))

    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.6")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.6")

    implementation("androidx.activity:activity-ktx:1.9.3")
    implementation("androidx.fragment:fragment-ktx:1.8.5")

    //ViewPager
    implementation ("androidx.viewpager2:viewpager2:1.0.0")

    //Hilt
    val hilt_version = "2.51.1"
    implementation ("com.google.dagger:hilt-android:$hilt_version")
    kapt ("com.google.dagger:hilt-android-compiler:$hilt_version")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.9.0")

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")
    //Coroutines Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")

    //RxJava
    implementation("io.reactivex.rxjava2:rxjava:2.2.9")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")

    //Splash Screen
    implementation("androidx.core:core-splashscreen:1.0.0")

    //LottieFiles
    var lottieVersion = "3.4.0"
    implementation ("com.airbnb.android:lottie:$lottieVersion")

    implementation("androidx.core:core-ktx:1.13.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

}