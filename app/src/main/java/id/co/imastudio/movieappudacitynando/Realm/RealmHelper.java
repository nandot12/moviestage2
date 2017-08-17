//package id.co.imastudio.movieappudacitynando.Realm;
//
//import android.content.Context;
//import android.util.Log;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//
//import io.realm.Realm;
//import io.realm.RealmResults;
//import io.realm.Sort;
//
///**
// * Created by nandoseptianhusni on 7/29/17.
// */
//
//public class RealmHelper {
//
//
//    private static final String TAG = "RealmHelper";
//
//
//    private Realm realm;
//    private RealmResults<Movie1> realmResult;
//    public Context context;
//
//    /**
//     * constructor untuk membuat instance realm
//     *
//     * @param context
//     */
//    public RealmHelper(Context context) {
//        this.context = context;
//        realm = Realm.getInstance(context);
//
//    }
//
//
//    public void addArticle(int id, String title, String overview, String date, double ratting, String path) {
//        Movie1 movie = new Movie1();
//        movie.setId(id);
//        movie.setDate(date);
//        movie.setTitle(title);
//        movie.setOverview(overview);
//        movie.setPath(path);
//        movie.setRatting(ratting);
//
//
//        realm.beginTransaction();
//        realm.copyToRealm(movie);
//        realm.commitTransaction();
//
//
//        showLog("Added ; " + title);
//        showToast(title + " berhasil disimpan.");
//    }
//
//
//    /**
//     * method mencari semua article
//     */
//    public ArrayList<MovieRealm> findAllArticle() {
//        ArrayList<MovieRealm> data = new ArrayList<>();
//
//
//        realmResult = realm.where(Movie1.class).findAll();
//        realmResult.sort("id", Sort.DESCENDING);
//        if (realmResult.size() > 0) {
//            showLog("Size : " + realmResult.size());
//
////int id, String title, String path, String overview, String date, Double ratting
//            for (int i = 0; i < realmResult.size(); i++) {
//                String title, description;
//                int id = realmResult.get(i).getId();
//                title = realmResult.get(i).getTitle();
//                description = realmResult.get(i).getOverview();
//                String date = realmResult.get(i).getDate();
//                Double ratting = realmResult.get(i).getRatting();
//                String path = realmResult.get(i).getPath();
//                data.add(new MovieRealm(id, title, path, description, date, ratting));
//            }
//
//        } else {
//            showLog("Size : 0");
//            showToast("Database Kosong!");
//        }
//
//        return data;
//    }
//
//
//    /**
//     * method menghapus article berdasarkan id
//     *
//     * @param id
//     */
//    public void deleteData(int id) {
//        RealmResults<Movie1> dataDesults = realm.where(Movie1.class).equalTo("id", id).findAll();
//        realm.beginTransaction();
//        dataDesults.remove(0);
//        dataDesults.removeLast();
//        dataDesults.clear();
//        realm.commitTransaction();
//
//        // showToast("Hapus data berhasil.");
//    }
//
//
//    public Integer checkFavorit(int id) {
//
//        int i;
//
//        RealmResults<Movie1> dataDesults = realm.where(Movie1.class).equalTo("id", id).findAll();
//
//        if (dataDesults.size() == 0) {
//
//            i = 1;
//
//
//        } else {
//            i = 2;
//        }
//
//        return i;
//    }
//
//    /**
//     * membuat log
//     *
//     * @param s
//     */
//    private void showLog(String s) {
//        Log.d(TAG, s);
//
//    }
//
//    /**
//     * Membuat Toast Informasi
//     */
//    private void showToast(String s) {
//        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
//    }
//}
