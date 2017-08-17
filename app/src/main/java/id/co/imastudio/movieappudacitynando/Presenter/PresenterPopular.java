package id.co.imastudio.movieappudacitynando.Presenter;

import java.util.ArrayList;

import id.co.imastudio.movieappudacitynando.Model.Film;

/**
 * Created by nandoseptianhusni on 7/29/17.
 */

public interface PresenterPopular {

    void ListMovie(ArrayList<Film> data);

    void Error(String msg);

}
