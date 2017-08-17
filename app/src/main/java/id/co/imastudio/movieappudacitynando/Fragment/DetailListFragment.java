package id.co.imastudio.movieappudacitynando.Fragment;


import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import id.co.imastudio.movieappudacitynando.Helper.Constan;
import id.co.imastudio.movieappudacitynando.MainActivity;
import id.co.imastudio.movieappudacitynando.Model.Results;
import id.co.imastudio.movieappudacitynando.Presenter.PresenterRevi;
import id.co.imastudio.movieappudacitynando.Presenter.PresenterReview;
import id.co.imastudio.movieappudacitynando.Presenter.PresenterV;
import id.co.imastudio.movieappudacitynando.Presenter.PresenterVideos;
import id.co.imastudio.movieappudacitynando.R;
import id.co.imastudio.movieappudacitynando.Response.ResultVideo;
import id.co.imastudio.movieappudacitynando.data.MovieContract;
import id.co.imastudio.movieappudacitynando.data.MovieProvider;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailListFragment extends Fragment implements PresenterReview, PresenterVideos {

    String img, title, date, overview;
    int id;

    PresenterRevi p;
    PresenterV pVideo;


    Double ratting;


    @InjectView(R.id.movie_detail_image)
    ImageView movieDetailImage;
    @InjectView(R.id.movie_detail_title)
    TextView movieDetailTitle;
    @InjectView(R.id.movie_detail_date)
    TextView movieDetailDate;
    @InjectView(R.id.movie_detail_rating)
    TextView movieDetailRating;
    @InjectView(R.id.movie_detail_overview)
    TextView movieDetailOverview;
    @InjectView(R.id.movie_detail_videos_header)
    TextView movieDetailVideosHeader;
    @InjectView(R.id.movie_detail_videos_layout)
    LinearLayout movieDetailVideosLayout;
    @InjectView(R.id.movie_detail_reviews_header)
    TextView movieDetailReviewsHeader;
    @InjectView(R.id.movie_detail_reviews_layout)
    LinearLayout movieDetailReviewsLayout;
    @InjectView(R.id.fab)
    ImageView fab;
    @InjectView(R.id.scrollview)
    NestedScrollView scrollview;

    public DetailListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_detail_movie, container, false);
        ButterKnife.inject(this, view);

        if(savedInstanceState != null){
            final int position1 = savedInstanceState.getInt("x");
            final int position2 = savedInstanceState.getInt("x");
            if(position1 != 0)
           scrollview.post(new Runnable() {
                    public void run() {
                        scrollview.scrollBy(position1,position2);

                    }
               });
        }
        final ImageView fab = (ImageView) view.findViewById(R.id.fab);
        id = getActivity().getIntent().getIntExtra(Constan.ID, 0);

        if (MovieProvider.isFavorited(getActivity(), id) > 0) {

            fab.setImageResource(R.drawable.ic_star_border_black_24dp);


        } else {
            fab.setImageResource(R.drawable.ic_star_yellow_24dp);

        }

        //  getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (MovieProvider.isFavorited(getActivity(), id) > 0) {

                    Toast.makeText(getActivity(), "unfavorite", Toast.LENGTH_SHORT).show();

                    MovieProvider.deleteMovie(getActivity(), id);

                } else {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(MovieContract.FlavorEntry._ID, id);
                    contentValues.put(MovieContract.FlavorEntry.COLUMN_TITLE, title);
                    contentValues.put(MovieContract.FlavorEntry.COLUMN_PATH, img);
                    contentValues.put(MovieContract.FlavorEntry.COLUMN_OVERVIEW, overview);
                    contentValues.put(MovieContract.FlavorEntry.COLUMN_DATE, date);
                    contentValues.put(MovieContract.FlavorEntry.COLUMN_RATTING, ratting);

                    int uri = getActivity().getContentResolver().bulkInsert(MovieContract.FlavorEntry.CONTENT_URI, new ContentValues[]{contentValues});


                    // Toast.makeText(DetailMovieActivity.this,uri.toStri,Toast.LENGTH_SHORT).show();


                    //set relm


                }
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });


        setView();
        return view;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("x", scrollview.getScrollX());
        outState.putInt("y", scrollview.getScrollY());

//        Log.d("x :" , String.valueOf(scrollview.getScaleX()));
//        Log.d("y :", String.valueOf(scrollview.getScaleY()));



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    private void setView() {

        img = getActivity().getIntent().getStringExtra(Constan.PATH);
        title = getActivity().getIntent().getStringExtra(Constan.TITLE);
        date = getActivity().getIntent().getStringExtra(Constan.DATE);
        ratting = getActivity().getIntent().getDoubleExtra(Constan.RATTING, 0);
        overview = getActivity().getIntent().getStringExtra(Constan.OVERVIEW);


        p = new PresenterRevi(this, getActivity());


        pVideo = new PresenterV(this, getActivity());

        p.Tampil(String.valueOf(id));

        pVideo.Tampil(String.valueOf(id));

        String url = "http://image.tmdb.org/t/p/w500" + img;
        Glide.with(this)
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.noimage)
                .into(movieDetailImage);

        movieDetailTitle.setText(title);
        movieDetailDate.setText(date);
        movieDetailOverview.setText(overview);
        movieDetailRating.setText(String.valueOf(ratting + "/10"));


    }


    @Override
    public void Result(List<Results> data) {

        //  Log.e("review nando :",data.get(0).toString());
        for (Results review : data) {
            LinearLayout layout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.layout_list_review, movieDetailReviewsLayout, false);
            TextView author = (TextView) layout.findViewById(R.id.list_item_review_author);
            author.setText(review.getAuthor());
            TextView content = (TextView) layout.findViewById(R.id.list_item_review_content);
            content.setText(review.getContent());
            movieDetailReviewsLayout.addView(layout);
        }
    }

    @Override
    public void ResultVideo(List<ResultVideo> data) {
        for (final ResultVideo video : data) {
            TextView text = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.list_item_video, movieDetailVideosLayout, false);
            text.setText(video.getName());
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + video.getKey())));
                }
            });
            movieDetailVideosLayout.addView(text);
        }

    }

    @Override
    public void Error(String error) {

        Log.e("Error review", error);

    }
}
