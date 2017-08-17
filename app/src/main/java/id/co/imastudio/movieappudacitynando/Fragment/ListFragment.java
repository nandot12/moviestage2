package id.co.imastudio.movieappudacitynando.Fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;
import id.co.imastudio.movieappudacitynando.Adapter.PagerAdapter2;
import id.co.imastudio.movieappudacitynando.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {


    @InjectView(R.id.tab)
    TabLayout tab;
    @InjectView(R.id.pager)
    ViewPager pager;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.inject(this, view);


        tab.addTab(tab.newTab().setText("POPULAR"));
        tab.addTab(tab.newTab().setText("TOP RATE"));
        tab.addTab(tab.newTab().setText("FAVORITE"));

        PagerAdapter adapter = new PagerAdapter2(getActivity().getSupportFragmentManager());
        pager.setAdapter(adapter);



        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
