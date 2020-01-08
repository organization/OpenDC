package be.zvz.opendc.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import be.zvz.kotlininside.KotlinInside
import be.zvz.kotlininside.api.article.ArticleList
import be.zvz.kotlininside.http.DefaultHttpClient
import be.zvz.kotlininside.session.user.Anonymous
import be.zvz.opendc.R
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val totalSearchView = findViewById<SearchView>(R.id.totalSearchView)

        /*val okHttpClient = OkHttpClient().newBuilder()
            .followRedirects(true)
            .followSslRedirects(true)
            .build()

        AndroidNetworking.initialize(applicationContext, okHttpClient)
        AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY)
        AndroidNetworking.setParserFactory(JacksonParserFactory())*/

        // dc app_id 인증 서버가 올바르지 않은 Content-Length

        val textView = findViewById<TextView>(R.id.textView)

        thread {
            try {
                Thread.sleep(5000)
                KotlinInside.createInstance(Anonymous("ㅇㅇ", "1234"), DefaultHttpClient(false))

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
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
}
