package id.co.imastudio.movieappudacitynando.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;


public class MovieProvider extends ContentProvider {
	private static final String LOG_TAG = MovieProvider.class.getSimpleName();
	private static final UriMatcher sUriMatcher = buildUriMatcher();

	private MovieDBHelper mOpenHelper;

	private static String[] projection =
			{
					MovieContract.FlavorEntry._ID, MovieContract.FlavorEntry.COLUMN_TITLE, MovieContract.FlavorEntry.COLUMN_PATH
			};

	// Codes for the UriMatcher //////
	private static final int FLAVOR = 100;
	private static final int FLAVOR_WITH_ID = 200;
	////////

	private static UriMatcher buildUriMatcher(){
		// Build a UriMatcher by adding a specific code to return based on a match
		// It's common to use NO_MATCH as the code for this case.
		final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
		final String authority = MovieContract.CONTENT_AUTHORITY;

		// add a code for each type of URI you want
		matcher.addURI(authority, MovieContract.FlavorEntry.TABLE_MOVIE, FLAVOR);
		matcher.addURI(authority, MovieContract.FlavorEntry.TABLE_MOVIE + "/#", FLAVOR_WITH_ID);

		return matcher;
	}

	@Override
	public boolean onCreate(){
		mOpenHelper = new MovieDBHelper(getContext());

		return true;
	}

	@Override
	public String getType(Uri uri){
		final int match = sUriMatcher.match(uri);

		switch (match){
			case FLAVOR:{
				return MovieContract.FlavorEntry.CONTENT_DIR_TYPE;
			}
			case FLAVOR_WITH_ID:{
				return MovieContract.FlavorEntry.CONTENT_ITEM_TYPE;
			}
			default:{
				throw new UnsupportedOperationException("Unknown uri: " + uri);
			}
		}
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder){
		Cursor retCursor;
		switch(sUriMatcher.match(uri)){
			// All Flavors selected
			case FLAVOR:{
				retCursor = mOpenHelper.getReadableDatabase().query(
						MovieContract.FlavorEntry.TABLE_MOVIE,
						projection,
						selection,
						selectionArgs,
						null,
						null,
						sortOrder);
				return retCursor;
			}
			// Individual flavor based on Id selected
			case FLAVOR_WITH_ID:{
				retCursor = mOpenHelper.getReadableDatabase().query(
						MovieContract.FlavorEntry.TABLE_MOVIE,
						projection,
						MovieContract.FlavorEntry._ID + " = ?",
						new String[] {String.valueOf(ContentUris.parseId(uri))},
						null,
						null,
						sortOrder);
				return retCursor;
			}
			default:{
				// By default, we assume a bad URI
				throw new UnsupportedOperationException("Unknown uri: " + uri);
			}
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values){
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Uri returnUri;
		switch (sUriMatcher.match(uri)) {
			case FLAVOR: {


				long _id = db.insert(MovieContract.FlavorEntry.TABLE_MOVIE, null, values);
				// insert unless it is already contained in the database
				if (_id > 0) {
					returnUri = MovieContract.FlavorEntry.buildFlavorsUri(_id);
				} else {
					throw new android.database.SQLException("Failed to insert row into: " + uri);
				}
				break;
			}

			default: {
				throw new UnsupportedOperationException("Unknown uri: " + uri);

			}
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return returnUri;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs){
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		final int match = sUriMatcher.match(uri);
		int numDeleted;
		switch(match){
			case FLAVOR:

				numDeleted = db.delete(
						MovieContract.FlavorEntry.TABLE_MOVIE, selection, selectionArgs);
				// reset _ID
				db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE ID = '" +
						MovieContract.FlavorEntry.TABLE_MOVIE + "'");
				break;
			case FLAVOR_WITH_ID:
				numDeleted = db.delete(MovieContract.FlavorEntry.TABLE_MOVIE,
						MovieContract.FlavorEntry._ID + " = ?",
						new String[]{String.valueOf(ContentUris.parseId(uri))});
				// reset _ID
				db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE ID = '" +
						MovieContract.FlavorEntry.TABLE_MOVIE + "'");

				break;
			default:
				throw new UnsupportedOperationException("Unknown uri: " + uri);
		}

		return numDeleted;
	}

	@Override
	public int bulkInsert(Uri uri, ContentValues[] values){
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		final int match = sUriMatcher.match(uri);
		switch(match){
			case FLAVOR:
				// allows for multiple transactions
				db.beginTransaction();

				// keep track of successful inserts
				int numInserted = 0;
				try{
					for(ContentValues value : values){
						if (value == null){
							throw new IllegalArgumentException("Cannot have null content values");
						}
						long _id = -1;
						try{
							_id = db.insertOrThrow(MovieContract.FlavorEntry.TABLE_MOVIE,
									null, value);
						}catch(SQLiteConstraintException e) {

							System.out.println(Log.d("error inserr", e.getMessage()));
//							Log.w(LOG_TAG, "Attempting to insert " +
//									value.getAsString(
//											MovieContract.FlavorEntry.COLUMN_VERSION_NAME)
//									+ " but value is already in database.");
						}
						if (_id != -1){
							numInserted++;
						}
					}
					if(numInserted > 0){
						// If no errors, declare a successful transaction.
						// database will not populate if this is not called
						db.setTransactionSuccessful();
					}
				} finally {
					// all transactions occur at once
					db.endTransaction();
				}
				if (numInserted > 0){
					// if there was successful insertion, notify the content resolver that there
					// was a change
					getContext().getContentResolver().notifyChange(uri, null);
				}
				return numInserted;
			default:
				return super.bulkInsert(uri, values);
		}
	}

	@Override
	public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs){
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int numUpdated = 0;

		if (contentValues == null){
			throw new IllegalArgumentException("Cannot have null content values");
		}

		switch(sUriMatcher.match(uri)){
			case FLAVOR:{
				numUpdated = db.update(MovieContract.FlavorEntry.TABLE_MOVIE,
						contentValues,
						selection,
						selectionArgs);
				break;
			}
			case FLAVOR_WITH_ID: {
				numUpdated = db.update(MovieContract.FlavorEntry.TABLE_MOVIE,
						contentValues,
						MovieContract.FlavorEntry._ID + " = ?",
						new String[] {String.valueOf(ContentUris.parseId(uri))});
				break;
			}
			default:{
				throw new UnsupportedOperationException("Unknown uri: " + uri);
			}
		}

		if (numUpdated > 0){
			getContext().getContentResolver().notifyChange(uri, null);
		}

		return numUpdated;
	}

	public static int isFavorited(Context context, int id) {
		Cursor cursor = context.getContentResolver().query(
				MovieContract.FlavorEntry.CONTENT_URI,
				null,   // projection
				MovieContract.FlavorEntry._ID + " = ?", // selection
				new String[] { Integer.toString(id) },   // selectionArgs
				null    // sort order
		);
		int numRows = cursor.getCount();
		cursor.close();
		return numRows;
	}

	public static void deleteMovie(Context c , int id) {

		try {
			c.getContentResolver().delete(MovieContract.FlavorEntry.CONTENT_URI,
					MovieContract.FlavorEntry._ID + " = " + id, null);
			Cursor newCursor = c.getContentResolver().query(MovieContract.FlavorEntry.CONTENT_URI, projection,
					null, null, null);


		}catch (SQLException E){

		}
		///Boolean b = newCursor.isClosed() ;

	}



}
