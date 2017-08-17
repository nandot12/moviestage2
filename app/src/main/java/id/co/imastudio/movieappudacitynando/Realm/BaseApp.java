//package id.co.imastudio.movieappudacitynando.Realm;
//
//import android.app.Application;
//
//import io.realm.DynamicRealm;
//import io.realm.Realm;
//import io.realm.RealmConfiguration;
//import io.realm.RealmMigration;
//import io.realm.RealmSchema;
//
///**
// * Created by nandoseptianhusni on 7/29/17.
// */
//
//public class BaseApp extends Application{
//
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//
//        //kode konfigurasi Realm
//        RealmConfiguration config = new RealmConfiguration.Builder(this)
//                //versi database
//                .schemaVersion(0)
//                .migration(new DataMigration())
//                .build();
//
//        Realm.setDefaultConfiguration(config);
//
//    }
//
//    private class DataMigration implements RealmMigration {
//        @Override
//        public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
//
//            //Mengambil schema
//            RealmSchema schema = realm.getSchema();
//
//            //membuat schema baru jika versi 0
//            if (oldVersion == 0) {
//                schema.create("Movie")
//                        .addField("id", int.class)
//                        .addField("title", String.class)
//                        .addField("overview", String.class)
//                        .addField("date", String.class)
//                        .addField("rate", String.class)
//                        .addField("overview", String.class);
//                oldVersion++;
//            }
//
//        }
//    }
//}
