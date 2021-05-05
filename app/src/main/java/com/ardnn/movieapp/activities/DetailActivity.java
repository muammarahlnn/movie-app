package com.ardnn.movieapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ardnn.movieapp.R;
import com.ardnn.movieapp.networks.Const;
import com.ardnn.movieapp.utils.Util;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    // extras
    public static final String EXTRA_TITLE = "extra_title";
    public static final String EXTRA_SYNOPSIS = "extra_synopsis";
    public static final String EXTRA_IMAGE_URL = "extra_image_url";
    public static final String EXTRA_RELEASE_DATE = "extra_release_date";
    public static final String EXTRA_VOTE = "extra_vote";

    // widgets
    ImageView ivPoster;
    TextView tvTitle, tvSynopsis, tvVote, tvReleaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // initialize widgets
        ivPoster = findViewById(R.id.iv_poster_detail);
        tvTitle = findViewById(R.id.tv_title_detail);
        tvSynopsis = findViewById(R.id.tv_synopsis_detail);
        tvVote = findViewById(R.id.tv_vote_detail);
        tvReleaseDate = findViewById(R.id.tv_release_date_detail);

        // set movie data to widgets
        setMovieData();
    }

    private void setMovieData() {
        // get data from previous intent
        String title = getIntent().getStringExtra(EXTRA_TITLE);
        String synopsis = getIntent().getStringExtra(EXTRA_SYNOPSIS);
        String imageUrl = getIntent().getStringExtra(EXTRA_IMAGE_URL);
        String releaseDate = Util.convertToDate(getIntent().getStringExtra(EXTRA_RELEASE_DATE));
        double vote = getIntent().getDoubleExtra(EXTRA_VOTE, 0);

        // set to widgets
        tvTitle.setText(title);
        tvSynopsis.setText(synopsis);
        tvReleaseDate.setText(releaseDate);
        tvVote.setText(String.valueOf(vote));
        Glide.with(this)
                .load(Const.IMG_URL_500 + imageUrl)
                .into(ivPoster);

        // change title action bar
        assert getSupportActionBar() != null : "Tenai action bar na";
        Util.changeActionBarTitle(this, title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    // if button back on action bar is clicked
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}