package id.co.imastudio.movieappudacitynando.Response;

import java.util.List;

import id.co.imastudio.movieappudacitynando.Model.Results;

/**
 * Created by nandoseptianhusni on 7/29/17.
 */

public class ResponseReview {
//

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    Double id ;
    List<Results> results ;

}
