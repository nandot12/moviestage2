package id.co.imastudio.movieappudacitynando.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by nandoseptianhusni on 7/9/17.
 */

public class Movie {



    public ArrayList<Film> getResults() {
        return results;
    }

    public void setResults(ArrayList<Film> results) {
        this.results = results;
    }
        @SerializedName("results")
    private  ArrayList<Film> results = new ArrayList<>();
}
