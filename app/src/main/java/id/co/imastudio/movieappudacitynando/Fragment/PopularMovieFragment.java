package id.co.imastudio.movieappudacitynando.Fragment;


import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import id.co.imastudio.movieappudacitynando.Adapter.CustomAdapter;
import id.co.imastudio.movieappudacitynando.Model.Film;
import id.co.imastudio.movieappudacitynando.Presenter.PresenterMovie;
import id.co.imastudio.movieappudacitynando.Presenter.PresenterPopular;
import id.co.imastudio.movieappudacitynando.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularMovieFragment extends Fragment implements PresenterPopular {

    PresenterMovie presenterViewMovie;
    static String TITLE = "title";


    @InjectView(R.id.recyler)
    RecyclerView recyler;

    GridLayoutManager grid;
    Parcelable save;


    ArrayList<Film> data2;


    public PopularMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_popular_movie, container, false);
        ButterKnife.inject(this, view);

        // data2 = new ArrayList<>();

        grid = new GridLayoutManager(getActivity(), 2);
        if (savedInstanceState != null) {
            save = savedInstanceState.getParcelable("data");


        }


        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    @Override
    public void ListMovie(ArrayList<Film> data) {

        data2 = data;


        Log.d("error", data.toString());
      CustomAdapter  adapter = new CustomAdapter(getActivity(), data2);
        Log.d("data", data.get(0).getPoster_path());
        recyler.setAdapter(adapter);
        recyler.setHasFixedSize(true);

        recyler.setLayoutManager(grid);
    }

    @Override
    public void Error(String msg) {

        Log.d("error", msg);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        save = grid.onSaveInstanceState();
        outState.putParcelable("data", save);

        super.onSaveInstanceState(outState);


    }

    @Override
    public void onStart() {
        super.onStart();
        presenterViewMovie = new PresenterMovie(this, getActivity());
        presenterViewMovie.Tampil();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (save != null) {
            grid.onRestoreInstanceState(save);
        }
    }
}
