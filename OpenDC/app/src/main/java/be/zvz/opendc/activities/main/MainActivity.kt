package be.zvz.opendc.activities.main

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.zvz.kotlininside.KotlinInside
import be.zvz.kotlininside.api.generic.GallerySearch
import be.zvz.kotlininside.api.generic.MainPage
import be.zvz.kotlininside.session.user.Anonymous
import be.zvz.opendc.R
import be.zvz.opendc.http.FANHttpInterface
import br.com.mauker.materialsearchview.MaterialSearchView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.BitmapRequestListener
import com.jacksonandroidnetworking.JacksonParserFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {
    private lateinit var totalSearchView: MaterialSearchView
    private lateinit var searchButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        totalSearchView = findViewById(R.id.totalSearchView)
        totalSearchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.length < 2)
                    return false

                thread(name = "query-text-search") {
                    totalSearchView.clearSuggestions()
                    val gallerySearchResult = GallerySearch(newText).search()

                    totalSearchView.addSuggestions(mutableListOf<String>().apply {
                        gallerySearchResult.mainGallery.forEach {
                            add(it.title)
                        }

                        gallerySearchResult.minorGallery.forEach {
                            add(it.title)
                        }
                    })
                }


                return false
            }

        })
        totalSearchView.setCloseOnTintClick(true)
        totalSearchView.setSearchViewListener(object : MaterialSearchView.SearchViewListener {
            override fun onSearchViewOpened() {
                searchButton.visibility = View.GONE
            }

            override fun onSearchViewClosed() {
                searchButton.visibility = View.VISIBLE
            }

        })
        searchButton = findViewById(R.id.searchButton)
        searchButton.setOnClickListener {
            totalSearchView.openSearch()
        }

        val hitRecyclerViewAdapter = HitRecyclerViewAdapter(this)
        val hitRecyclerView = findViewById<RecyclerView>(R.id.hitRecyclerView)
        hitRecyclerView.adapter = hitRecyclerViewAdapter
        hitRecyclerView.layoutManager = GridLayoutManager(this, 2)

        val cacheSize: Long = 50 * 1024 * 1024 // 50MB

        val okHttpClient = OkHttpClient().newBuilder()
            .followRedirects(true)
            .followSslRedirects(true)
            .cache(Cache(cacheDir, cacheSize))
            .build()

        AndroidNetworking.initialize(applicationContext, okHttpClient)
        //AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY)
        // dcinside app_id 인증 서버가 올바르지 않은 Content-Length 반환, 로거를 꺼버리면 작동함
        AndroidNetworking.setParserFactory(JacksonParserFactory())

        val httpClient = FANHttpInterface()


        doAsync {
            try {
                KotlinInside.createInstance(Anonymous("ㅇㅇ", "1234"), httpClient)

                val mainPage = MainPage()
                mainPage.request()

                val hit = mainPage.getHit()

                hit.forEach {
                    AndroidNetworking
                        .get(it.thumbnail)
                        .setTag("get-hit-thumbnail")
                        .setPriority(Priority.HIGH)
                        .build()
                        .getAsBitmap(object : BitmapRequestListener {
                            override fun onResponse(response: Bitmap) {
                                uiThread { _ ->
                                    hitRecyclerViewAdapter.addItem(
                                        image = BitmapDrawable(resources, response),
                                        title = it.title,
                                        id = it.articleId
                                    )
                                    hitRecyclerViewAdapter.notifyDataSetChanged()
                                }
                            }

                            override fun onError(anError: ANError) {
                                Log.e("OpenDC", "exception in get-hit-thumbnail", anError)
                            }

                        })
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    override fun onBackPressed() {
        if (totalSearchView.isOpen) { // Close the search on the back button press.
            totalSearchView.closeSearch()
        } else {
            super.onBackPressed()
        }
    }
}
