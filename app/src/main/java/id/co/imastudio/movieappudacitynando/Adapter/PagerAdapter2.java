package id.co.imastudio.movieappudacitynando.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import id.co.imastudio.movieappudacitynando.Fragment.FavoriteFragment;
import id.co.imastudio.movieappudacitynando.Fragment.PopularKidsFragment;
import id.co.imastudio.movieappudacitynando.Fragment.PopularMovieFragment;

/**
 * Created by nandoseptianhusni on 8/8/17.
 */

public class PagerAdapter2 extends FragmentStatePagerAdapter {

    public PagerAdapter2(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new PopularMovieFragment();
        }
        else if(position ==1){
            return new PopularKidsFragment();
        }
        else {
            return new FavoriteFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

}