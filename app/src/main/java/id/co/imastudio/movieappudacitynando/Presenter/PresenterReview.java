package id.co.imastudio.movieappudacitynando.Presenter;

import java.util.List;

import id.co.imastudio.movieappudacitynando.Model.Results;

/**
 * Created by nandoseptianhusni on 7/29/17.
 */

public interface PresenterReview {



    void Result(List<Results> data);
    void Error(String error);
}
