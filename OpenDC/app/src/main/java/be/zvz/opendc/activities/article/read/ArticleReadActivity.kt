package be.zvz.opendc.activities.article.read

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import be.zvz.kotlininside.KotlinInside
import be.zvz.kotlininside.api.article.ArticleRead
import be.zvz.opendc.R
import com.asksira.webviewsuite.WebViewSuite
import com.elyeproj.loaderviewlibrary.LoaderImageView
import com.elyeproj.loaderviewlibrary.LoaderTextView
import org.apache.commons.text.StringEscapeUtils
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URLDecoder


class ArticleReadActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_read)
        val headTextView = findViewById<LoaderTextView>(R.id.articleReadHeadText)
        val titleTextView = findViewById<LoaderTextView>(R.id.articleReadTitle)
        val nameTextView = findViewById<LoaderTextView>(R.id.articleReadName)
        val ipTextView = findViewById<LoaderTextView>(R.id.articleReadIp)
        val timestampTextView = findViewById<LoaderTextView>(R.id.articleReadTimestamp)
        val hitTextView = findViewById<LoaderTextView>(R.id.articleReadHit)
        val commentCount = findViewById<LoaderTextView>(R.id.articleReadCommentCount)
        val imageView = findViewById<LoaderImageView>(R.id.articleReadImage)

        val urlRegex =
            "^(http|https?):\\/\\/([^:\\/\\s]+)(:([^\\/]*))?((\\/[^\\s/\\/]+)*)?\\/([^#\\s\\?]*)(\\?([^#\\s]*))?(#(\\w*))?\$".toRegex()

        val GALL_DCINSIDE_COM_URL = "gall.dcinside.com"
        val M_DCINSIDE_COM_URL = "m.dcinside.com"

        val intent = Intent(this.intent)
        val gallId = intent.getStringExtra("gall_id").apply {
            if (this === null) {
                finish()
            }
        }!!

        val articleId = intent.getIntExtra("article_id", 1)

        val articleRead = ArticleRead(
            gallId = gallId,
            articleId = articleId,
            session = KotlinInside.getInstance().session
        )

        doAsync {
            articleRead.request()

            val viewInfo = articleRead.getViewInfo()
            val viewMain = articleRead.getViewMain()

            uiThread {
                headTextView.text = viewInfo.headTitle
                titleTextView.text = viewInfo.subject
                nameTextView.text = viewInfo.name

                titleTextView.isSelected = true

                if (viewInfo.ip.isNotEmpty()) {
                    ipTextView.text = " (${viewInfo.ip})"
                    imageView.visibility = View.GONE
                } else
                    ipTextView.visibility = View.GONE

                timestampTextView.text = viewInfo.dateTime
                hitTextView.text = getString(R.string.article_read_hit) + viewInfo.views
                commentCount.text = getString(R.string.article_read_comment) + viewInfo.totalComment

                var html = StringEscapeUtils.unescapeHtml4(viewMain.content)
                html = "<style>img{display: inline;height: auto;max-width: 100%;}</style>$html"

                val webViewSuite = findViewById<WebViewSuite>(R.id.articleReadWebViewSuite)
                webViewSuite.customizeClient(object : WebViewSuite.WebViewSuiteCallback {
                    override fun onPageFinished(view: WebView?, url: String?) {
                    }

                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    }

                    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                        urlRegex.matchEntire(url)?.let { matchResult ->
                            when (matchResult.groups[2]?.value) {
                                GALL_DCINSIDE_COM_URL -> {
                                    if (matchResult.groups[6]?.value == "/view" || matchResult.groups[7]?.value == "list.php") {
                                        matchResult.groups[9]?.value?.let { valueString ->
                                            val queryMap = linkedMapOf<String, String>().apply {
                                                valueString.split("&").forEach { pair ->
                                                    val idx = pair.indexOf("=")
                                                    this[URLDecoder.decode(
                                                        pair.substring(0, idx),
                                                        "UTF-8"
                                                    )] = URLDecoder.decode(
                                                        pair.substring(idx + 1),
                                                        "UTF-8"
                                                    )
                                                }
                                            }

                                            val targetGallId = queryMap["id"]!!
                                            val targetArticleId = queryMap["no"]!!.toInt()

                                            val targetIntent = Intent(
                                                applicationContext,
                                                ArticleReadActivity::class.java
                                            )
                                                .putExtra("gall_id", targetGallId)
                                                .putExtra("article_id", targetArticleId)
                                            startActivity(targetIntent)

                                            return false
                                        }
                                    } else {
                                        // 갤러리 글 목록으로 가는 기능 추가 필요
                                        return@let
                                    }
                                }
                                M_DCINSIDE_COM_URL -> {
                                    matchResult.groups[7]?.value?.let { valueString ->
                                        val targetGallId =
                                            matchResult.groups[6]!!.value.substring(1)
                                        val targetArticleId = valueString.toInt()

                                        val targetIntent = Intent(
                                            applicationContext,
                                            ArticleReadActivity::class.java
                                        )
                                            .putExtra("gall_id", targetGallId)
                                            .putExtra("article_id", targetArticleId)
                                        startActivity(targetIntent)

                                        return false
                                    }
                                }
                            }
                        }

                        view.context.startActivity(
                            Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        )
                        return true
                    }

                })
                webViewSuite.startLoadData(html, "text/html", "utf-8")
            }
        }
    }

}