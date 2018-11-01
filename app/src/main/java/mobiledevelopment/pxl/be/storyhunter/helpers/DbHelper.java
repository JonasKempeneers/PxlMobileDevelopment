package mobiledevelopment.pxl.be.storyhunter.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mobiledevelopment.pxl.be.storyhunter.entities.Book;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "bookDb";
    private static int DATABASE_VERSION = 1;


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Book.FOUNDBOOKS_TABLE_NAME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + Book.PLACEDBOOKS_TABLE_NAME + ";");

        // Create new tables
        db.execSQL(Book.CREATE_FOUNDBOOKS_TABLE);
        db.execSQL(Book.CREATE_PLACEDBOOKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onCreate(db);

    }

    public long addBookToTable(String tableName, Book book){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values  = new ContentValues();

        values.put(Book.COLUMN_TITLE ,book.getTitle());
        values.put(Book.COLUMN_AUTHOR, book.getAuthor());
        values.put(Book.COLUMN_LANGUAGE, book.getLanguage());
        values.put(Book.COLUMN_ISBN, book.getIsbn());
        values.put(Book.COLUMN_DATEFOUND, book.getDateFound());
        values.put(Book.COLUMN_LATITUDE, book.getLatitude());
        values.put(Book.COLUMN_LONGITUDE, book.getLongitude());
        values.put(Book.COLUMN_HINT, book.getHint());

        long rowCount = db.insert(tableName, null, values);

        db.close();

        return rowCount;
    }

    public ArrayList<Book> getBookList(String tableName){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Book> bookList = new ArrayList<Book>();

        String sqlQuery = "SELECT * FROM " + tableName;

        Cursor cursor = db.rawQuery(sqlQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Book book = new Book();
                book.setId(cursor.getInt(cursor.getColumnIndex("id")));
                book.setTitle(cursor.getString(cursor.getColumnIndex(Book.COLUMN_TITLE)));
                book.setAuthor(cursor.getString(cursor.getColumnIndex(Book.COLUMN_AUTHOR)));
                book.setLanguage(cursor.getString(cursor.getColumnIndex(Book.COLUMN_LANGUAGE)));
                book.setIsbn(cursor.getString(cursor.getColumnIndex(Book.COLUMN_ISBN)));
                book.setDateFound(cursor.getString(cursor.getColumnIndex(Book.COLUMN_DATEFOUND)));
                book.setLatitude(cursor.getDouble(cursor.getColumnIndex(Book.COLUMN_LATITUDE)));
                book.setLongitude(cursor.getDouble(cursor.getColumnIndex(Book.COLUMN_LONGITUDE)));
                book.setHint(cursor.getString(cursor.getColumnIndex(Book.COLUMN_HINT)));


                bookList.add(book);
            } while (cursor.moveToNext());
        }

        db.close();

        return bookList;
    }

    public void incrementDatabaseVersion(){
        DATABASE_VERSION++;
    }
}
