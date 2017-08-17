package id.co.imastudio.movieappudacitynando.Fragment;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import id.co.imastudio.movieappudacitynando.Adapter.AdapterRealm;
import id.co.imastudio.movieappudacitynando.R;
import id.co.imastudio.movieappudacitynando.Realm.Movie1;
import id.co.imastudio.movieappudacitynando.data.MovieContract;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {


    private static final int CURSOR_LOADER_ID =0 ;
    ArrayList<Movie1> data;
    Movie1 m;
    private AdapterRealm adapter ;
    @InjectView(R.id.flavors_grid)
    GridView flavorsGrid;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_popular2017, container, false);
        ButterKnife.inject(this, view);

        data = new ArrayList<>();


        movieServer();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    private void movieServer() {


        adapter = new AdapterRealm(getActivity(), null, 0, CURSOR_LOADER_ID);
        flavorsGrid.setAdapter(adapter);



        Log.d("adapter grib",adapter.toString());
//        LinearLayoutManager linear = new LinearLayoutManager(getActivity());
//        recyler.setLayoutManager(linear);
//        recyler.setHasFixedSize(true);
//        GridLayoutManager grid = new GridLayoutManager(getActivity(),2);
//        recyler.setLayoutManager(grid);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        getLoaderManager().initLoader(CURSOR_LOADER_ID, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(),
                MovieContract.FlavorEntry.CONTENT_URI,
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        adapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        adapter.swapCursor(null);

    }
}
