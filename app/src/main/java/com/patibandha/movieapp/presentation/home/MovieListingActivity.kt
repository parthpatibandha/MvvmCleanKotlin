package com.patibandha.movieapp.presentation.home

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.patibandha.movieapp.R
import com.patibandha.movieapp.data.models.Movie
import com.patibandha.movieapp.presentation.core.BaseActivity
import com.patibandha.movieapp.presentation.home.adapterItem.MovieListAdapter
import com.patibandha.movieapp.presentation.home.listener.OnMovieItemClickListener
import com.patibandha.movieapp.presentation.utility.EndlessRecyclerOnScrollListener
import com.patibandha.movieapp.presentation.utility.gone
import com.patibandha.movieapp.presentation.utility.isNetworkAvailable
import com.patibandha.movieapp.presentation.utility.visible
import eu.davidea.flexibleadapter.common.FlexibleItemAnimator
import kotlinx.android.synthetic.main.activity_movie_listing.*
import kotlinx.android.synthetic.main.layout_empty.*
import org.koin.android.viewmodel.ext.android.viewModel

class MovieListingActivity : BaseActivity(), OnMovieItemClickListener {

    private val movieListingViewModel: MovieListingViewModel by viewModel()

    override fun getViewModel() = movieListingViewModel

    private lateinit var movieListAdapter: MovieListAdapter

    private var page: Int = 0
    private var totalPage: Int = 1
    private var isLoading = false
    private var isSearching = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_listing)

        init()
    }

    private fun init() {
        attachObserver()
        setupAdapter()
        getMovieList()

        searchView.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                movieListAdapter.filter(s.toString())
                isSearching = s.toString().isNotEmpty()
            }

            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
        })
    }

    private fun attachObserver() {
        movieListingViewModel.movieListRSLiveData.observe(this, Observer {
            it?.apply {
                val sortedList = this.items?.sortedBy {
                    it.dateTaken
                }
                if (sortedList?.isNotEmpty() == true) {
                    sortedList.forEachIndexed { index, movie ->
                        if(movieListAdapter.list.isEmpty()) {
                            movie.id = movie.id.toInt() + index
                        } else {
                            movie.id = movieListAdapter.list.size + index
                        }
                        movie.imageUrl = movie?.media?.imageUrl
                    }
                    movieListingViewModel.insertMovieList(sortedList.orEmpty())
                }
                updateDataset(sortedList)
                hideProgress()
                isLoading = false
            }
        })
        movieListingViewModel.movieListLocalLiveData.observe(this, Observer {
            it?.apply {
                updateDataset(this)
                hideProgress()
                isLoading = false
            }
        })
    }

    private fun getMovieList() {
        showProgress()
        isLoading = true
        if (isNetworkAvailable()) {
            movieListingViewModel.getMovieList(page.toString())
        } else {
            if(movieListAdapter?.list?.isEmpty()) {
                movieListingViewModel.getMovieListLocal()
            } else{
                hideProgress()
            }
        }
    }

    private fun setupAdapter() {
        movieListAdapter = MovieListAdapter(arrayListOf(), this)
        recyclerView.adapter = movieListAdapter
        recyclerView.itemAnimator = FlexibleItemAnimator()
        recyclerView.addOnScrollListener(object : EndlessRecyclerOnScrollListener() {
            override fun onLoadMore(current_page: Int) {
                if (!isLoading && !isSearching) {
                    page++
                    getMovieList()
                }
            }
        })
    }

    private fun updateDataset(list: List<Movie>?) {
        if (list?.isEmpty() == true) {
            tvNoDataFound.visible()
            recyclerView.gone()
        } else {
            tvNoDataFound.gone()
            recyclerView.visible()
            movieListAdapter.updateDataSet(list.orEmpty())
        }
    }

    override fun onMovieItemClick(movie: Movie) {

        val bundle = Bundle()
        bundle.putParcelable(Movie::class.java.simpleName, movie)
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)

    }
}
