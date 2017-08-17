package id.co.imastudio.movieappudacitynando.Presenter;

import java.util.List;

import id.co.imastudio.movieappudacitynando.Response.ResultVideo;

/**
 * Created by nandoseptianhusni on 7/29/17.
 */

public interface PresenterVideos {


    void ResultVideo(List<ResultVideo> data);
    void Error(String error);
}
