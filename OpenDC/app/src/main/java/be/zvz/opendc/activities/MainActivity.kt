package be.zvz.opendc.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import be.zvz.kotlininside.KotlinInside
import be.zvz.kotlininside.api.article.ArticleList
import be.zvz.kotlininside.session.user.Anonymous
import be.zvz.opendc.R
import be.zvz.opendc.http.FANHttpInterface
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.interceptors.GzipRequestInterceptor
import com.jacksonandroidnetworking.JacksonParserFactory
import okhttp3.OkHttpClient
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val totalSearchView = findViewById<SearchView>(R.id.totalSearchView)

        val okHttpClient = OkHttpClient().newBuilder()
            .followRedirects(true)
            .followSslRedirects(true)
            .addInterceptor(GzipRequestInterceptor())
            .build()

        AndroidNetworking.initialize(applicationContext, okHttpClient)
        AndroidNetworking.setParserFactory(JacksonParserFactory())

        val textView = findViewById<TextView>(R.id.textView)

        thread {
            KotlinInside.createInstance(Anonymous("ㅇㅇ", "1234"), FANHttpInterface())

            val articleList = ArticleList("hit", 1)
            articleList.request()

            val gallList = articleList.getGallList() // 글 목록
            val gallInfo = articleList.getGallInfo() // 갤러리 정보

            var text = "app_id : ${KotlinInside.getInstance().app.id}\n"

            text += "gallInfo : $gallInfo\n"
            gallList.forEachIndexed { index, it ->
                text += "gallList $index : $it\n"
            }

            runOnUiThread {
                textView.text = text
            }

        }

    }
}
