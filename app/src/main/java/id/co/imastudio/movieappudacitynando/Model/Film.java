package id.co.imastudio.movieappudacitynando.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nandoseptianhusni on 7/9/17.
 */

public class Film implements Parcelable {


    int id ;
    @SerializedName("vote_average")
    Double voteAverage ;
    @SerializedName("title")
    String originalTitle ;
    @SerializedName("poster_path")
    String posterPath ;
    String overview ;
    @SerializedName("release_date")
    String releaseDate ;

    public Double getPopularity() {
        return popularity;
    }

    Boolean adult ;
    Double popularity ;



    public int getId() {
        return id;
    }

    public Double getVote_average() {
        return voteAverage;
    }

    public String getTitle() {
        return originalTitle;
    }

    public String getPoster_path() {
        return posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return releaseDate;
    }

    public Boolean getAdult() {
        return adult;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeValue(this.voteAverage);
        dest.writeString(this.originalTitle);
        dest.writeString(this.posterPath);
        dest.writeString(this.overview);
        dest.writeString(this.releaseDate);
        dest.writeValue(this.adult);
        dest.writeValue(this.popularity);
    }

    public Film() {
    }

    protected Film(Parcel in) {
        this.id = in.readInt();
        this.voteAverage = (Double) in.readValue(Double.class.getClassLoader());
        this.originalTitle = in.readString();
        this.posterPath = in.readString();
        this.overview = in.readString();
        this.releaseDate = in.readString();
        this.adult = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.popularity = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<Film> CREATOR = new Parcelable.Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel source) {
            return new Film(source);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };
}
