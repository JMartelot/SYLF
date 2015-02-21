/**
 * 
 */
package com.imie.sylf.data.Show;

import java.util.ArrayList;
import java.util.List;

import com.imie.sylf.ShowActivity;
import com.imie.sylf.data.SylfSqlLiteOpenHelper;
import com.imie.sylf.data.Author.AuthorSQLiteAdapter;
import com.imie.sylf.data.Author.AuthorTask;
import com.imie.sylf.entity.Author;
import com.imie.sylf.entity.Show;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Table which contain shows, it's use for the favourites list
 * 
 * @author Jean
 *
 */
public class ShowSQLiteAdapter {

	/**
	 * Show constraint.
	 */
	public static final String TABLE_SHOW = "Show";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_TITLE = "title";
	public static final String COLUMN_PLOT = "plot";
	public static final String COLUMN_RUNTIME = "runtime";
	public static final String COLUMN_RELEASED = "released";
    public static final String COLUMN_POSTER = "poster";
    public static final String COLUMN_BACKDROP = "backdrop";
    public static final String COLUMN_AUTHORS = "authors";
    public static final String COLUMN_GENRES = "genres";
	public static final String SCHEMA = "CREATE TABLE " + ShowSQLiteAdapter.TABLE_SHOW + "(" +
			ShowSQLiteAdapter.COLUMN_ID + " INTEGER PRIMARY KEY, " +
			ShowSQLiteAdapter.COLUMN_TITLE + " TEXT NOT NULL," +
			ShowSQLiteAdapter.COLUMN_PLOT + " TEXT NOT NULL, " +
			ShowSQLiteAdapter.COLUMN_RUNTIME + " TEXT, " +
            ShowSQLiteAdapter.COLUMN_POSTER + " TEXT, " +
            ShowSQLiteAdapter.COLUMN_BACKDROP + " TEXT, " +
			ShowSQLiteAdapter.COLUMN_RELEASED + " TEXT)";
	private static final String  COLUMNS[] = {
			COLUMN_ID, 
			COLUMN_TITLE, 
			COLUMN_PLOT, 
			COLUMN_RUNTIME, 
			COLUMN_RELEASED,
			COLUMN_POSTER, 
			COLUMN_BACKDROP};

	/**
	 * Helper.
	 */
	private SylfSqlLiteOpenHelper helper;

	/**
	 * Database.
	 */
	private SQLiteDatabase db;
	
	private Context context;

	/**
	 * Constructor.
	 * 
	 */
	public ShowSQLiteAdapter(Context context){
		this.helper = new SylfSqlLiteOpenHelper(context);
		this.context = context;
	}

	/**
	 * Function to open the database.
	 */
	public void open() throws SQLException{
		this.db = this.helper.getWritableDatabase();
	}

	/**
	 * Function to close the database.
	 */
	public void close(){
		this.helper.close();
	}

	/**
	 * Function which get a show by this api id.
	 * 
	 * @param id
	 * @return
	 */
	public Show getShow(int id){

		Show user = new Show();
		//Correspond au where ( WHERE ID = ?)
		String selection = COLUMN_ID + " = ?";
		//Valeur de l'id à regarder pour le where de la requête
		String[] selectionArgs = {String.valueOf(id)};
		
		//Création de la requête pour récuprérer l'utilisateur
		Cursor cursor = this.db.query(
				TABLE_SHOW, 
				COLUMNS, 
				selection, 
				selectionArgs, 
				null, null, null);

		if(cursor.moveToLast()){
			user = this.cursorToItem(cursor);
		}

		return user;
	}
	
	/**
	 * Function which return a list of all shows in database.
	 * @return
	 */
	public ArrayList<Show> getShows(){
		
		ArrayList<Show> shows = new ArrayList<Show>();	

		Cursor cursor = this.db.query(
				TABLE_SHOW, 
				COLUMNS, 
				null, null, null, null, null);

		if(cursor.moveToFirst()){
			do{
				shows.add(this.cursorToItem(cursor));					
			}while(cursor.moveToNext());
		}

		return shows;
	}
	
	

	/**
	 * Function which insert a show in the database.
	 * 
	 * @param show
	 * @return show
	 */
	public int create(Show show){

		ContentValues values = new ContentValues();

        values.put(COLUMN_ID, show.getId());
		values.put(COLUMN_TITLE, show.getTitle());
		values.put(COLUMN_PLOT, show.getPlot());
		values.put(COLUMN_RUNTIME, show.getRuntime());
		values.put(COLUMN_RELEASED, show.getReleased());
        values.put(COLUMN_POSTER, show.getPoster());
        values.put(COLUMN_BACKDROP, show.getBackdrop_path());
        
        
        
		return (int)this.db.insert(TABLE_SHOW, null, values);
	}

	/**
	 * Function which update a show in database
	 * 
	 * @param show
	 * @return number of rows updated
	 */
//	public int update(Show user){
//		ContentValues values = new ContentValues();
//
//		values.put(COLUMN_LOGIN, user.getLogin());
//		values.put(COLUMN_FIRSTNAME, user.getFirstname());
//		values.put(COLUMN_LASTNAME, user.getLastname());
//		values.put(COLUMN_PASSWORD, user.getPassword());
//		
//		String whereClause = COLUMN_ID + " = ?";
//		String whereArgs[] = {String.valueOf(user.getId())};
//		
//		return this.db.update(TABLE_USER, values, whereClause, whereArgs);
//	}

	/**
	 * Function which delete a show in database by this id
	 * @param user
	 * @return number of rows deleted
	 */
	public int delete(Show show){
		String whereClause = COLUMN_ID + " = ?";
		String whereArgs[] = {String.valueOf(show.getId())};
		
		return this.db.delete(TABLE_SHOW, whereClause, whereArgs);
	}
	
	/**
	 * Convert a cursor to a show.
	 * 
	 * @param cursor
	 * @return
	 */
	private Show cursorToItem (Cursor cursor){
		Show show = new Show();
		
		show.setId(cursor.getInt(
				cursor.getColumnIndex(COLUMN_ID)));
		show.setPlot(cursor.getString(
		        cursor.getColumnIndex(COLUMN_PLOT)));
		show.setReleased(cursor.getString(
		        cursor.getColumnIndex(COLUMN_RELEASED)));
		show.setRuntime(cursor.getString(
		        cursor.getColumnIndex(COLUMN_RUNTIME)));
		show.setPoster(cursor.getString(
		        cursor.getColumnIndex(COLUMN_POSTER)));
		show.setBackdrop_path(cursor.getString(
		        cursor.getColumnIndex(COLUMN_BACKDROP)));
		
		return show;
	}
}
