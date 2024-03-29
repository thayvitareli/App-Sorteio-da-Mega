package com.example.sorteiosdamega;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    private Context context;
    private static final String DATABASE_NAME = "megasena.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "sorteio";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FIRST_NUMBER= "first_number";
    private static final String COLUMN_SECOND_NUMBER= "second_number";
    private static final String COLUMN_THIRD_NUMBER= "third_number";
    private static final String COLUMN_FOURTH_NUMBER= "fourth_number";
    private static final String COLUMN_FIFTH_NUMBER= "fifth_number";
    private static final String COLUMN_SIXTH_NUMBER= "sixth_number";


    DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public void saveOnDatabase(List<Integer> numerousSorted){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_FIRST_NUMBER,  numerousSorted.get(0));
        cv.put(COLUMN_SECOND_NUMBER,  numerousSorted.get(1));
        cv.put(COLUMN_THIRD_NUMBER,  numerousSorted.get(2));
        cv.put(COLUMN_FOURTH_NUMBER, numerousSorted.get(3));
        cv.put(COLUMN_FIFTH_NUMBER, numerousSorted.get(4));
        cv.put(COLUMN_SIXTH_NUMBER, numerousSorted.get(5));

        long result = db.insert(TABLE_NAME,null, cv);

        if(result == -1){
            Toast.makeText(context, "Falha ao salvar", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
        }
    }

    /*
    public void saveOnDatabase(List<Integer> numerousSorted){
        SQLiteDatabase database = openOrCreateDatabase("sorteio", MODE_PRIVATE, null);
        System.out.println("save on database"+ numerousSorted.toString());

        // Insira os números sorteados na tabela
        ContentValues values = new ContentValues();
        if (numerousSorted.size() >= 6) {
            values.put("first_number", numerousSorted.get(0));
            values.put("second_number", numerousSorted.get(1));
            values.put("third_number", numerousSorted.get(2));
            values.put("fourth_number", numerousSorted.get(3));
            values.put("fifth_number", numerousSorted.get(4));
            values.put("sixth_number", numerousSorted.get(5));
        }

        long id = database.insert("sorteio", null, values);
        System.out.println("id -> " + id);
        database.close();
    } */

    Cursor readAllData(){

        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    public Cursor selectFromDatabase(){
        System.out.println("select from database");
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM sorteio";

        Cursor cursor = null;
        try  {
            cursor = database.rawQuery(query, null);

            int i = 0;
            while(cursor.moveToNext()){
                System.out.println(cursor.getColumnName(0) + cursor.getInt(0));
                System.out.println(cursor.getInt(1));
                System.out.println(cursor.getInt(2));
                System.out.println(cursor.getInt(3));
                System.out.println(cursor.getInt(4));
                System.out.println(cursor.getInt(5));
                System.out.println(cursor.getInt(6));
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            database.close();
        }

        return cursor;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS sorteio("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + " first_number TINYINT, "
                + " second_number TINYINT, "
                + " third_number TINYINT, "
                + " fourth_number TINYINT, "
                + " fifth_number TINYINT, "
                + " sixth_number TINYINT " + ")";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
