package utng.edu.mx.proyectoitext;


import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



/**
 * Created by MD on 29/02/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Temas.db";
    private static final String TABLE_NAME_TEME = "Temas";
    private static final String COLUMN_TEME_ID = "id";
    private static final String COLUMN_TEME = "teme";



    private SQLiteDatabase dbSql;


    private static final String TABLE_CREATE_TEME = "CREATE TABLE "+ TABLE_NAME_TEME +"(" +
            COLUMN_TEME_ID +" INTEGER PRIMARY KEY NOT NULL, "+
            COLUMN_TEME +" INTEGER NOT NULL"+");";


    public DatabaseHelper(Context context){
        super(context , DATABASE_NAME , null , DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        this.dbSql = db;
        db.execSQL(TABLE_CREATE_TEME);
        db.execSQL("INSERT INTO " + TABLE_NAME_TEME + " VALUES (1,1)");

    }
   public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
       super(context, name, factory, version);
   }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_TEME);

        this.onCreate(db);

    }


    public  int  tema (){
        dbSql = this.getReadableDatabase();
        String selectQuery =  "SELECT "+ COLUMN_TEME +" FROM "+ TABLE_NAME_TEME ;
        Cursor cursor = dbSql.rawQuery(selectQuery, null);
        int score=0;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    score = cursor.getInt(0);
                } while (cursor.moveToNext());
            }
        }
        return score;
    }
    public void actualizarTema(int date){

        dbSql = this.getWritableDatabase();
        //Actualizamos el registro en la base de datos
        dbSql.execSQL("UPDATE "+ TABLE_NAME_TEME +" SET "+ COLUMN_TEME +
                " = "+date+" WHERE id = 1");
        dbSql.close();
    }



}
