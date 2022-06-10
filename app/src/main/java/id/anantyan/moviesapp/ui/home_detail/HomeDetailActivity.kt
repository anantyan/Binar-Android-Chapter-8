package id.anantyan.moviesapp.ui.home_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import coil.load
import coil.size.ViewSizeResolver
import coil.transform.RoundedCornersTransformation
import dagger.hilt.android.AndroidEntryPoint
import id.anantyan.moviesapp.R
import id.anantyan.moviesapp.databinding.ActivityHomeDetailBinding
import id.anantyan.moviesapp.model.ResultsItem

@AndroidEntryPoint
class HomeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeDetailBinding
    private lateinit var item: ResultsItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        if (intent.hasExtra("TO_HOME_DETAIL")) {
            item = intent.extras?.getParcelable("TO_HOME_DETAIL")!!
        }

        supportActionBar?.title = item.title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        binding.backdropPath.load(item.backdropPath) {
            crossfade(true)
            placeholder(R.drawable.ic_placeholder)
            error(R.drawable.ic_placeholder)
            size(ViewSizeResolver(binding.backdropPath))
        }
        binding.imgPosterPath.load(item.posterPath) {
            transformations(RoundedCornersTransformation(16F))
            placeholder(R.drawable.ic_placeholder)
            error(R.drawable.ic_placeholder)
            size(ViewSizeResolver(binding.imgPosterPath))
        }
        binding.txtTitle2.text = item.title
        binding.releaseDate.text = item.releaseDate
        binding.overview.text = item.overview
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}