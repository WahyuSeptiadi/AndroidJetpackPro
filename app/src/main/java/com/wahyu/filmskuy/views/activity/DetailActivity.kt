package com.wahyu.filmskuy.views.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import com.wahyu.filmskuy.R
import com.wahyu.filmskuy.models.MovieModel
import com.wahyu.filmskuy.utils.DataDummy
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIES = "extra_movies"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val extras = intent.extras
        if (extras != null){
            val movieId = extras.getString(EXTRA_MOVIES)
            if (movieId != null){
                for (movie in DataDummy.generateDummyMovies()) {
                    if (movie.moviesId == movieId){
                        getDataMovie(movie)
                    }
                }
            }
        }
    }

    private fun getDataMovie(movieModel: MovieModel) {
        Picasso.get().load(movieModel.image).into(backgroundDetailFilm)
        Picasso.get().load(movieModel.image).into(imageDetailFilm)

        titleDetailFilm.text = movieModel.title
        releaseDetailFilm.text = movieModel.release
        ratingDetailFilm.text = movieModel.rating
        overviewDetailFilm.text = movieModel.overview
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_detail_course)
//        setSupportActionBar(toolbar)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//
//        val adapter = DetailCourseAdapter()
//        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailCourseViewModel::class.java]
//
//        val extras = intent.extras
//        if (extras != null) {
//            val courseId = extras.getString(EXTRA_COURSE)
//            if (courseId != null) {
////                val modules = DataDummy.generateDummyModules(courseId)
////                adapter.setModules(modules)
////                for(course in DataDummy.generateDummyCourses()) {
////                    if(course.courseId == courseId) {
////                        populateCourse(course)
////                    }
////                }
//                viewModel.setSelectedCourse(courseId)
//                val modules = viewModel.getModules()
//                adapter.setModules(modules)
//                populateCourse(viewModel.getCourse())
//            }
//        }
//
//        with(rv_module) {
//            isNestedScrollingEnabled = false
//            layoutManager = LinearLayoutManager(this@DetailCourseActivity)
//            setHasFixedSize(true)
//            this.adapter = adapter
//            val dividerItemDecoration = DividerItemDecoration(rv_module.context, DividerItemDecoration.VERTICAL)
//            addItemDecoration(dividerItemDecoration)
//        }
//    }
//
//    private fun populateCourse(courseEntity: CourseEntity) {
//        text_title.text = courseEntity.title
//        text_desc.text = courseEntity.description
//        text_date.text = resources.getString(R.string.deadline_date, courseEntity.deadline)
//
//        Glide.with(this)
//            .load(courseEntity.imagePath)
//            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
//                .error(R.drawable.ic_error))
//            .into(image_poster)
//
//        btn_start.setOnClickListener {
//            val intent = Intent(this@DetailCourseActivity, CourseReaderActivity::class.java)
//            intent.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, courseEntity.courseId)
//            startActivity(intent)
//        }
//    }
}