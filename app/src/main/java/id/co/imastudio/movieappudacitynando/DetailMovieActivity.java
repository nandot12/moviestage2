package id.co.imastudio.movieappudacitynando;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import id.co.imastudio.movieappudacitynando.Fragment.DetailListFragment;
import id.co.imastudio.movieappudacitynando.Presenter.PresenterRevi;
import id.co.imastudio.movieappudacitynando.Presenter.PresenterV;

public class DetailMovieActivity extends AppCompatActivity {


    String img, title, date, overview;
    int id;

    PresenterRevi p;
    PresenterV pVideo;


    Double ratting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detail_list);

        if (savedInstanceState == null) {
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentById(R.id.framedetail);
            if (fragment == null) {
                fragment = new DetailListFragment();
                fm.beginTransaction()

                        .add(R.id.framedetail, fragment)
                        .commit();
            }

        }


    }
}