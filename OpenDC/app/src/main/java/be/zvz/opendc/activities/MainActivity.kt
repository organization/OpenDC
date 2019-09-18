package be.zvz.opendc.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import be.zvz.opendc.R
import com.lapism.search.widget.SearchView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val totalSearchView = findViewById<SearchView>(R.id.totalSearchView)
    }
}
