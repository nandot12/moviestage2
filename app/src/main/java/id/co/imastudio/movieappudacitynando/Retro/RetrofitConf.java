package id.co.imastudio.movieappudacitynando.Retro;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nandoseptianhusni on 7/9/17.
 */

public class RetrofitConf {



    public static Retrofit getRetrofit(){
        return new Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiService getInstance(){
        return RetrofitConf.getRetrofit().create(ApiService.class);
    }


}
