package id.co.imastudio.movieappudacitynando.Response;

import java.util.List;

/**
 * Created by nandoseptianhusni on 7/29/17.
 */

public class ResponseVideos {



    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public List<ResultVideo> getResults() {
        return results;
    }

    public void setResults(List<ResultVideo> results) {
        this.results = results;
    }

    Double id ;
    List<ResultVideo> results ;
}
