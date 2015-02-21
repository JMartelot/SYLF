/**
 * 
 */
package com.imie.sylf.data.Author_Show;

import java.util.ArrayList;

import com.imie.sylf.data.SylfSqlLiteOpenHelper;
import com.imie.sylf.entity.Author;
import com.imie.sylf.entity.AuthorShow;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author Jean
 *
 */
public class AuthorShowSQLiteAdapter {

	/**
	 * Author constraint.
	 */
	public static final String TABLE_AUTHOR_SHOW = "Author_Show";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_ID_AUTHOR = "id_author";
    public static final String COLUMN_ID_SHOW = "id_show";
	public static final String SCHEMA = "CREATE TABLE IF NOT EXISTS " + AuthorShowSQLiteAdapter.TABLE_AUTHOR_SHOW + "(" +
			AuthorShowSQLiteAdapter.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			AuthorShowSQLiteAdapter.COLUMN_ID_AUTHOR + " INTEGER NOT NULL," +
            AuthorShowSQLiteAdapter.COLUMN_ID_SHOW + " INTEGER NOT NULL)";
	private static final String  COLUMNS[] = {
			COLUMN_ID, 
			COLUMN_ID_AUTHOR, 
			COLUMN_ID_SHOW};

	/**
	 * Helper.
	 */
	private SylfSqlLiteOpenHelper helper;

	/**
	 * Database.
	 */
	private SQLiteDatabase db;

	/**
	 * Constructor.
	 * 
	 */
	public AuthorShowSQLiteAdapter(Context context){
		this.helper = new SylfSqlLiteOpenHelper(context);
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
	
	public void createTable(){
	    db.execSQL(this.SCHEMA);
	}

	/**
	 * Function which get a relation between an author and show by their id.
	 * 
	 * @param id
	 * @return
	 */
	public AuthorShow getAuthorShow(int idAuthor, int idShow){

	    AuthorShow author_show = new AuthorShow();
		//Correspond au where ( WHERE ID = ?)
		String selection = COLUMN_ID_AUTHOR + " = ? AND "+ COLUMN_ID_SHOW +" = ? ";
		//Valeur de l'id à regarder pour le where de la requête
		String[] selectionArgs = {String.valueOf(idAuthor), String.valueOf(idShow)};
		
		//Création de la requête pour récuprérer l'utilisateur
		Cursor cursor = this.db.query(
				TABLE_AUTHOR_SHOW, 
				COLUMNS, 
				selection, 
				selectionArgs, 
				null, null, null);

		if(cursor.moveToLast()){
		    author_show = this.cursorToItem(cursor);
		}

		return author_show;
	}
    /**
     * Function which get a relation between an author and show by the show's id.
     * 
     * @param id
     * @return
     */
    public ArrayList<AuthorShow> getAuthorShowByShow(int idShow){

        ArrayList<AuthorShow> authors_shows = new ArrayList<AuthorShow>();
        //Correspond au where ( WHERE ID = ?)
        String selection = COLUMN_ID_SHOW +"= ?";
        //Valeur de l'id à regarder pour le where de la requête
        String[] selectionArgs = {String.valueOf(idShow)};
        
        //Création de la requête pour récuprérer l'utilisateur
        Cursor cursor = this.db.query(
                TABLE_AUTHOR_SHOW, 
                COLUMNS, 
                selection, 
                selectionArgs, 
                null, null, null);

        if(cursor.moveToLast()){
            do{
                authors_shows.add(this.cursorToItem(cursor));                 
            }while(cursor.moveToNext());
        }

        return authors_shows;
    }	
	/**
	 * Function which insert an author in the database.
	 * 
	 * @param author
	 * @return author
	 */
	public int create(AuthorShow author_show){

		ContentValues values = new ContentValues();

        values.put(COLUMN_ID, author_show.getId());
		values.put(COLUMN_ID_AUTHOR, author_show.getId_author());
        values.put(COLUMN_ID_SHOW, author_show.getId_show());
        
		return (int)this.db.insert(TABLE_AUTHOR_SHOW, null, values);
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
	 * @param author
	 * @return number of rows deleted
	 */
	public int delete(Author author){
		String whereClause = COLUMN_ID + " = ?";
		String whereArgs[] = {String.valueOf(author.getId())};
		
		return this.db.delete(TABLE_AUTHOR_SHOW, whereClause, whereArgs);
	}
	
	/**
	 * Convert a cursor to a show.
	 * 
	 * @param cursor
	 * @return
	 */
	private AuthorShow cursorToItem (Cursor cursor){
	    AuthorShow author_show = new AuthorShow();
		
	    author_show.setId(cursor.getInt(
				cursor.getColumnIndex(COLUMN_ID)));
	    author_show.setId_author(cursor.getInt(
		        cursor.getColumnIndex(COLUMN_ID_AUTHOR)));
	    author_show.setId_show(cursor.getInt(
		        cursor.getColumnIndex(COLUMN_ID_SHOW)));
		
		return author_show;
	}
}
