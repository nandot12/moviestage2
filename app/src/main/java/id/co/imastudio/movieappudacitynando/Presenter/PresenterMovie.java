package id.co.imastudio.movieappudacitynando.Presenter;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import id.co.imastudio.movieappudacitynando.Model.Film;
import id.co.imastudio.movieappudacitynando.Model.Movie;
import id.co.imastudio.movieappudacitynando.R;
import id.co.imastudio.movieappudacitynando.Retro.ApiService;
import id.co.imastudio.movieappudacitynando.Retro.RetrofitConf;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nandoseptianhusni on 7/29/17.
 */

public class PresenterMovie {


    PresenterPopular presenterViewMovie ;
    Context c ;

    public PresenterMovie(PresenterPopular presenterViewMovie, Context c) {
        this.presenterViewMovie = presenterViewMovie;
        this.c = c;
    }


    public void Tampil(){
        ApiService api = RetrofitConf.getInstance();
//
//        final ProgressDialog dialog = new ProgressDialog(c);
//        dialog.setMessage("loading");
//        dialog.show();

        Call<Movie> call = api.get_popular(c.getString(R.string.THE_MOVIE_DB_API_TOKEN),
                c.getString(R.string.BAHASA),c.getString(R.string.PAGE));

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
              //  dialog.dismiss();

                if(response.isSuccessful()) {

                    Log.d("DATA FILM POPULER", response.message());
                    ArrayList<Film> data = response.body().getResults();

                    presenterViewMovie.ListMovie(data);
                }

            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
               // dialog.dismiss();
                Log.d("eror movie",t.getMessage());

                presenterViewMovie.Error(t.getMessage());

            }
        });


    }


}
