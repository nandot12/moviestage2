package id.co.imastudio.movieappudacitynando.Presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import java.util.List;

import id.co.imastudio.movieappudacitynando.R;
import id.co.imastudio.movieappudacitynando.Response.ResponseVideos;
import id.co.imastudio.movieappudacitynando.Response.ResultVideo;
import id.co.imastudio.movieappudacitynando.Retro.ApiService;
import id.co.imastudio.movieappudacitynando.Retro.RetrofitConf;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nandoseptianhusni on 7/29/17.
 */

public class PresenterV {


    PresenterVideos presenterReview;
    Context c;

    public PresenterV(PresenterVideos presenterReview, Context c) {
        this.presenterReview = presenterReview;
        this.c = c;
    }

    public void Tampil(String id) {
        ApiService api = RetrofitConf.getInstance();

        final ProgressDialog dialog = new ProgressDialog(c);
        dialog.setMessage("loading");
        dialog.show();


        Call<ResponseVideos> call = api.request_videos(id,c.getString(R.string.THE_MOVIE_DB_API_TOKEN),
                c.getString(R.string.BAHASA), c.getString(R.string.PAGE));

        call.enqueue(new Callback<ResponseVideos>() {
            @Override
            public void onResponse(Call<ResponseVideos> call, Response<ResponseVideos> response) {
                dialog.dismiss();
                Log.d("data video", response.message());

                if (response.isSuccessful()) {

                    Log.d("data review", response.message());
                    List<ResultVideo> data = response.body().getResults();
                    presenterReview.ResultVideo(data);


                }

            }

            @Override
            public void onFailure(Call<ResponseVideos> call, Throwable t) {
                dialog.dismiss();
                Log.d("eror video", t.getMessage());

                presenterReview.Error(t.getMessage());

            }
        });

    }
}
