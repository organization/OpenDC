package be.zvz.opendc.activities.article.read

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import be.zvz.kotlininside.KotlinInside
import be.zvz.kotlininside.api.article.ArticleRead
import be.zvz.opendc.R
import com.asksira.webviewsuite.WebViewSuite
import org.apache.commons.text.StringEscapeUtils
import kotlin.concurrent.thread

class ArticleReadActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_read)

        val headTextView = findViewById<TextView>(R.id.articleReadHeadText)
        val titleTextView = findViewById<TextView>(R.id.articleReadTitle)
        val nameTextView = findViewById<TextView>(R.id.articleReadName)
        val ipTextView = findViewById<TextView>(R.id.articleReadIp)
        val timestampTextView = findViewById<TextView>(R.id.articleReadTimestamp)
        val hitTextView = findViewById<TextView>(R.id.articleReadHit)
        val commentCount = findViewById<TextView>(R.id.articleReadCommentCount)

        val intent = Intent(this.intent)
        val gallId = intent.getStringExtra("gall_id")!!
        val articleId = intent.getIntExtra("article_id", 1)

        val articleRead = ArticleRead(
            gallId = gallId,
            articleId = articleId,
            session = KotlinInside.getInstance().session
        )

        thread(name = "article-read-request") {
            articleRead.request()
        }.join()

        val viewInfo = articleRead.getViewInfo()
        val viewMain = articleRead.getViewMain()

        headTextView.text = viewInfo.headTitle
        titleTextView.text = viewInfo.subject
        nameTextView.text = viewInfo.name
        if (viewInfo.ip.isNotEmpty())
            ipTextView.text = " (${viewInfo.ip})"
        else
            ipTextView.text = ""
        timestampTextView.text = viewInfo.dateTime
        hitTextView.text = "조회: ${viewInfo.views}"
        commentCount.text = "댓글: ${viewInfo.totalComment}"

        var html = StringEscapeUtils.unescapeHtml4(viewMain.content)
        //html = "<!doctype html><html>$html</html>"

        val webViewSuite = findViewById<WebViewSuite>(R.id.articleReadWebViewSuite)
        webViewSuite.startLoadData(html, "text/html", "utf-8")

    }
}