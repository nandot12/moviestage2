package id.co.imastudio.movieappudacitynando.Presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import java.util.List;

import id.co.imastudio.movieappudacitynando.Model.Results;
import id.co.imastudio.movieappudacitynando.R;
import id.co.imastudio.movieappudacitynando.Response.ResponseReview;
import id.co.imastudio.movieappudacitynando.Retro.ApiService;
import id.co.imastudio.movieappudacitynando.Retro.RetrofitConf;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nandoseptianhusni on 7/29/17.
 */

public class PresenterRevi {

    PresenterReview presenterReview;
    Context c;

    public PresenterRevi(PresenterReview presenterReview, Context c) {
        this.presenterReview = presenterReview;
        this.c = c;
    }

    public void Tampil(String id) {
        ApiService api = RetrofitConf.getInstance();

        final ProgressDialog dialog = new ProgressDialog(c);
        dialog.setMessage("loading");
        dialog.show();


        Call<ResponseReview> call = api.request_review(id,c.getString(R.string.THE_MOVIE_DB_API_TOKEN),
                c.getString(R.string.BAHASA), c.getString(R.string.PAGE));

        call.enqueue(new Callback<ResponseReview>() {
            @Override
            public void onResponse(Call<ResponseReview> call, Response<ResponseReview> response) {
                dialog.dismiss();
                Log.d("data review", response.message());

                if (response.isSuccessful()) {

                    Log.d("data review", response.message());
                    List<Results> data = response.body().getResults();
                    presenterReview.Result(data);


                }

            }

            @Override
            public void onFailure(Call<ResponseReview> call, Throwable t) {
                dialog.dismiss();
                Log.d("eror review", t.getMessage());

                presenterReview.Error(t.getMessage());

            }
        });

    }
}