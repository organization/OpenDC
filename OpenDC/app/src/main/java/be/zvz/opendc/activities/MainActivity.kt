package be.zvz.opendc.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import be.zvz.opendc.R
import com.lapism.search.widget.SearchView
import com.jacksonandroidnetworking.JacksonParserFactory
import com.androidnetworking.AndroidNetworking
import be.zvz.kotlininside.KotlinInside
import be.zvz.opendc.http.FANHttpInterface


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val totalSearchView = findViewById<SearchView>(R.id.totalSearchView)

        AndroidNetworking.initialize(applicationContext)
        AndroidNetworking.setParserFactory(JacksonParserFactory())

        val kotlinInside = KotlinInside(FANHttpInterface())
    }
}
