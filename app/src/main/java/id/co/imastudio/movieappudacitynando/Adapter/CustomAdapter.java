package id.co.imastudio.movieappudacitynando.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.co.imastudio.movieappudacitynando.DetailMovieActivity;
import id.co.imastudio.movieappudacitynando.Helper.Constan;
import id.co.imastudio.movieappudacitynando.Model.Film;
import id.co.imastudio.movieappudacitynando.R;

import static id.co.imastudio.movieappudacitynando.R.id.imageView;

/**
 * Created by nandoseptianhusni on 7/9/17.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyHolder> {
    private Activity C ;
    ArrayList<Film> data ;
    public CustomAdapter(Activity activity,ArrayList<Film> data) {
        this.data =data ;
        C = activity ;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        LayoutInflater inflater = (LayoutInflater) C.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.row_movie,parent,false);
        final MyHolder holder = new MyHolder(v );
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int posisi = holder.getAdapterPosition();
                Intent i = new Intent(C,DetailMovieActivity.class);
                i.putExtra(Constan.TITLE,data.get(posisi).getTitle());
                i.putExtra(Constan.OVERVIEW,data.get(posisi).getOverview());
                i.putExtra(Constan.PATH,data.get(posisi).getPoster_path());
                i.putExtra(Constan.RATTING,data.get(posisi).getVote_average());
                i.putExtra(Constan.DATE,data.get(posisi).getRelease_date());
                i.putExtra(Constan.ID,data.get(posisi).getId());


                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                C.startActivity(i);


            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        String  url ="http://image.tmdb.org/t/p/w500"+data.get(position).getPoster_path();
        Glide.with(C)
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.noimage)
                .into(holder.img);

        //Log.d("data photo","http://image.tmdb.org/t/p/w500"+data.get(position).getPoster_path());



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView img ;
        public MyHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(imageView);
        }
    }




}
