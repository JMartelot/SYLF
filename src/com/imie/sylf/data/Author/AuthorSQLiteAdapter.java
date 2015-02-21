/**
 * 
 */
package com.imie.sylf.data.Author;

import java.util.ArrayList;

import com.imie.sylf.data.SylfSqlLiteOpenHelper;
import com.imie.sylf.entity.Author;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author Jean
 *
 */
public class AuthorSQLiteAdapter {

	/**
	 * Author constraint.
	 */
	public static final String TABLE_AUTHOR = "Author";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";
    public static final String COLUMN_POSTER = "poster";
	public static final String SCHEMA = "CREATE TABLE IF NOT EXISTS " + AuthorSQLiteAdapter.TABLE_AUTHOR + "(" +
			AuthorSQLiteAdapter.COLUMN_ID + " INTEGER PRIMARY KEY, " +
			AuthorSQLiteAdapter.COLUMN_NAME + " TEXT NOT NULL," +
            AuthorSQLiteAdapter.COLUMN_POSTER + " TEXT)";
	private static final String  COLUMNS[] = {
			COLUMN_ID, 
			COLUMN_NAME, 
			COLUMN_POSTER};

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
	public AuthorSQLiteAdapter(Context context){
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
	 * Function which get an author by this api id.
	 * 
	 * @param id
	 * @return
	 */
	public Author getAuthor(int id){

	    Author author = new Author();
		//Correspond au where ( WHERE ID = ?)
		String selection = COLUMN_ID + " = ?";
		//Valeur de l'id à regarder pour le where de la requête
		String[] selectionArgs = {String.valueOf(id)};
		
		//Création de la requête pour récuprérer l'utilisateur
		Cursor cursor = this.db.query(
				TABLE_AUTHOR, 
				COLUMNS, 
				selection, 
				selectionArgs, 
				null, null, null);

		if(cursor.moveToLast()){
		    author = this.cursorToItem(cursor);
		}

		return author;
	}
	
	/**
	 * Function which return a list of all authors in database.
	 * @return
	 */
	public ArrayList<Author> getAuthors(){
		
		ArrayList<Author> authors = new ArrayList<Author>();	

		Cursor cursor = this.db.query(
				TABLE_AUTHOR, 
				COLUMNS, 
				null, null, null, null, null);

		if(cursor.moveToFirst()){
			do{
			    authors.add(this.cursorToItem(cursor));					
			}while(cursor.moveToNext());
		}

		return authors;
	}
	
	

	/**
	 * Function which insert an author in the database.
	 * 
	 * @param author
	 * @return author
	 */
	public int create(Author author){

		ContentValues values = new ContentValues();

        values.put(COLUMN_ID, author.getId());
		values.put(COLUMN_NAME, author.getName());
        values.put(COLUMN_POSTER, author.getProfil_picture());

		return (int)this.db.insert(TABLE_AUTHOR, null, values);
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
		
		return this.db.delete(TABLE_AUTHOR, whereClause, whereArgs);
	}
	
	/**
	 * Convert a cursor to a show.
	 * 
	 * @param cursor
	 * @return
	 */
	private Author cursorToItem (Cursor cursor){
	    Author author = new Author();
		
	    author.setId(cursor.getInt(
				cursor.getColumnIndex(COLUMN_ID)));
	    author.setName(cursor.getString(
		        cursor.getColumnIndex(COLUMN_NAME)));
		author.setProfil_picture(cursor.getString(
		        cursor.getColumnIndex(COLUMN_POSTER)));
		
		return author;
	}
}
