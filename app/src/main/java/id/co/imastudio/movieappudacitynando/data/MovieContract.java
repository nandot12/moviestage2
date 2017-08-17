package id.co.imastudio.movieappudacitynando.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class MovieContract {

	public static final String CONTENT_AUTHORITY = "id.co.imastudio.movieappudacitynando.app";

	public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);


	public static final class FlavorEntry implements BaseColumns {
		// table name
		public static final String TABLE_MOVIE = "movie";
		// columns
		public static final String _ID = "_id";
		public static final String COLUMN_TITLE = "title";
		public static final String COLUMN_OVERVIEW = "overview";
		public static final String COLUMN_PATH = "path";
		public static final String COLUMN_DATE = "date";
		public static final String COLUMN_RATTING = "ratting";

		// create content uri
		public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
			.appendPath(TABLE_MOVIE).build();
		// create cursor of base type directory for multiple entries
		public static final String CONTENT_DIR_TYPE =
		ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_MOVIE;
		// create cursor of base type item for single entry
		public static final String CONTENT_ITEM_TYPE =
			ContentResolver.CURSOR_ITEM_BASE_TYPE +"/" + CONTENT_AUTHORITY + "/" + TABLE_MOVIE;

		// for building URIs on insertion
		public static Uri buildFlavorsUri(long id){
        		return ContentUris.withAppendedId(CONTENT_URI, id);
		}
	}
}
