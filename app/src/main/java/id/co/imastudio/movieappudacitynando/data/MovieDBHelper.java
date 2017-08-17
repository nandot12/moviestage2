package id.co.imastudio.movieappudacitynando.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MovieDBHelper extends SQLiteOpenHelper {
	public static final String LOG_TAG = MovieDBHelper.class.getSimpleName();

	//name & version
	private static final String DATABASE_NAME = "movie.db";
	private static final int DATABASE_VERSION = 1;

	public MovieDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Create the database
	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " +
				MovieContract.FlavorEntry.TABLE_MOVIE + "(" + MovieContract.FlavorEntry._ID +
				" INTEGER NOT NULL, " +
				MovieContract.FlavorEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
				MovieContract.FlavorEntry.COLUMN_OVERVIEW +
				" TEXT NOT NULL, " +
				MovieContract.FlavorEntry.COLUMN_PATH +
				" TEXT NOT NULL, " +
				MovieContract.FlavorEntry.COLUMN_RATTING +
				" TEXT NOT NULL, " +

				MovieContract.FlavorEntry.COLUMN_DATE +
				" TEXT NOT NULL);";

		sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_TABLE);
	}

	// Upgrade database when version is changed.
	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

	}
}
