package be.zvz.opendc.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import be.zvz.opendc.R
import com.lapism.search.widget.SearchView
import com.jacksonandroidnetworking.JacksonParserFactory
import com.androidnetworking.AndroidNetworking
import be.zvz.kotlininside.KotlinInside
import be.zvz.kotlininside.session.user.Anonymous
import be.zvz.opendc.http.FANHttpInterface
import com.androidnetworking.interceptors.GzipRequestInterceptor
import okhttp3.OkHttpClient

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val totalSearchView = findViewById<SearchView>(R.id.totalSearchView)

        val okHttpClient = OkHttpClient().newBuilder()
            .followRedirects(true)
            .followSslRedirects(true)
            .addInterceptor(GzipRequestInterceptor())
            .build()

        AndroidNetworking.initialize(applicationContext, okHttpClient)
        AndroidNetworking.setParserFactory(JacksonParserFactory())

        KotlinInside.createInstance(Anonymous("ㅇㅇ", "1234"), FANHttpInterface())
    }
}
