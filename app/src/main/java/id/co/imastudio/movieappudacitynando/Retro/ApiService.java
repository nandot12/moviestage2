package id.co.imastudio.movieappudacitynando.Retro;

import id.co.imastudio.movieappudacitynando.Model.Movie;
import id.co.imastudio.movieappudacitynando.Response.ResponseReview;
import id.co.imastudio.movieappudacitynando.Response.ResponseVideos;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by nandoseptianhusni on 7/9/17.
 */

public interface ApiService {



    @GET("popular")
    Call<Movie> get_popular(
            @Query("api_key") String api_key,
            @Query("language") String bahasa,
            @Query("page") String status

    );


    @GET("top_rated")
   Call<Movie> get_kid(
            @Query("api_key") String api_key,
            @Query("language") String bahasa,
            @Query("page") String status

   );


    @GET("upcoming")
    Call<Movie> get_2017(
            @Query("api_key") String api_key,
            @Query("language") String bahasa,
            @Query("page") String status

    );

    @GET("{id}/reviews")
    Call<ResponseReview> request_review(
            @Path("id") String id,
            @Query("api_key") String api_key,
            @Query("language") String bahasa,
            @Query("page") String status


    );
     @GET("{id}/videos")
    Call<ResponseVideos> request_videos(
             @Path("id") String id,
            @Query("api_key") String api_key,
            @Query("language") String bahasa,
            @Query("page") String status


    );



   // https://api.themoviedb.org/3/movie/119450/reviews?
    // api_key=61a732264fa2977939306018c139391c&language=en-US&page=1


}
