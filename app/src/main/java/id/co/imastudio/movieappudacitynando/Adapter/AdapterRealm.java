package id.co.imastudio.movieappudacitynando.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import id.co.imastudio.movieappudacitynando.DetailMovieActivity;
import id.co.imastudio.movieappudacitynando.Helper.Constan;
import id.co.imastudio.movieappudacitynando.R;
import id.co.imastudio.movieappudacitynando.data.MovieContract;

/**
 * Created by nandoseptianhusni on 7/29/17.
 */


public class AdapterRealm extends CursorAdapter {
    private static final String LOG_TAG = AdapterRealm.class.getSimpleName();
    private Context mContext;
    private static int sLoaderID;



    public static class ViewHolder {
        public final ImageView imageView;


        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.imageView);

        }
    }
    public AdapterRealm(Activity activity, Cursor c, int i, int cursorLoaderId) {
        super(activity,c,i);
        Log.d(LOG_TAG, "FlavAdapter");
        mContext = activity ;
        sLoaderID = cursorLoaderId ;



    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        int layoutId = R.layout.row_movie;

        Log.d(LOG_TAG, "In new View");

        View view = LayoutInflater.from(context).inflate(layoutId, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);

        return view;
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {

        final ViewHolder viewHolder = (ViewHolder) view.getTag();

        Log.d(LOG_TAG, "In bind View");

//        int versionIndex = cursor.getColumnIndex(MovieContract.FlavorEntry.COLUMN_VERSION_NAME);
//        final String versionName = cursor.getString(versionIndex);
//        viewHolder.textView.setText(versionName);

        int imageIndex = cursor.getColumnIndex(MovieContract.FlavorEntry.COLUMN_PATH);
        String image = cursor.getString(imageIndex);
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(mContext,DetailMovieActivity.class);
                int pathIndex = cursor.getColumnIndex(MovieContract.FlavorEntry.COLUMN_PATH);
                int TitleIndex = cursor.getColumnIndex(MovieContract.FlavorEntry.COLUMN_TITLE);
                int dateIndex = cursor.getColumnIndex(MovieContract.FlavorEntry.COLUMN_DATE);
                int rattingIndex = cursor.getColumnIndex(MovieContract.FlavorEntry.COLUMN_RATTING);
                int overviewIndex = cursor.getColumnIndex(MovieContract.FlavorEntry.COLUMN_OVERVIEW);
                int id = cursor.getColumnIndex(MovieContract.FlavorEntry._ID);
                String title = cursor.getString(TitleIndex);
                String path = cursor.getString(pathIndex);
                String ratting = cursor.getString(rattingIndex);
                String overview = cursor.getString(overviewIndex);
                String date = cursor.getString(dateIndex);
                int idd = cursor.getInt(id);

                i.putExtra(Constan.TITLE,title);
                i.putExtra(Constan.OVERVIEW,overview);
                i.putExtra(Constan.PATH,path);
                i.putExtra(Constan.RATTING,ratting);
                i.putExtra(Constan.DATE,date);
                i.putExtra(Constan.ID,idd);


                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);
            }
        });


        Log.d("gambar grid",image) ;
        Log.i(LOG_TAG, "Image reference extracted: " + image);
        String  url ="http://image.tmdb.org/t/p/w500"+image;
        Glide.with(mContext)
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.noimage)
                .into(viewHolder.imageView);
       // viewHolder.imageView.setImageResource(image);

    }

//    public class MyHolder extends RecyclerView.ViewHolder {
//        ImageView img;
//
//        public MyHolder(View itemView) {
//            super(itemView);
//            img = (ImageView) itemView.findViewById(imageView);
//        }
//    }
}