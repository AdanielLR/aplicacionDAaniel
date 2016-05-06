

package utng.edu.mx.proyectoitext.agenda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class NotesDbAdapter {

    public static final String KEY_TITLE = "title";
    public static final String KEY_BODY = "body";
    public static final String KEY_ROWID = "_id";

    private static final String TAG = "NotesDbAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    /**
     * sentencia de creación de base de datos SQL
     */
    private static final String DATABASE_CREATE =
        "create table notes (_id integer primary key autoincrement, "
        + "title text not null, body text not null);";

    private static final String DATABASE_NAME = "data";
    private static final String DATABASE_TABLE = "notes";
    private static final int DATABASE_VERSION = 2;

    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS notes");
            onCreate(db);
        }
    }

    /**
     * Constructor - toma el contexto para permitir que la base de datos sea
     * opened/created
     * 
     * @param ctx contexto con el que va a trabajar
     */
    public NotesDbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    /**
     * Abré la base de notas. Si no se puede abrir, tratar de crear un nuevo
           * Instancia de la base de datos. Si no se puede crear, lanzar una excepción a
           * Señalizar el fallo
     * 
     * @return esto (autorreferencia, lo que permite que esto se encadenó en una
          * Llamada de inicialización)
     * @throws SQLException Si la base de datos podría ser abierto o creado
     */
    public NotesDbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }


    /**
     * Crear una nueva nota con el título y el cuerpo proporcionado. Si la nota es
           * Creado con éxito devolver los nuevos RowId para esa nota, si regreso
           * A -1 para indicar fallo.
     * 
     * @param title titulo de la nota
     * @param body cuerpo de la nota
     * @return rowId o -1 si falla
     */
    public long createNote(String title, String body) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TITLE, title);
        initialValues.put(KEY_BODY, body);

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    /**
     * Borra la nota con el identificador dado rowId
     * 
     * @param rowId id para borrar la nota
     * @return true si es eliminada, falso en caso contrario
     */
    public boolean deleteNote(long rowId) {

        return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    /**
     * Devolver un cursor sobre la lista de todas las notas de la base de datos
           *
           * @return Cursor sobre todas las notas
     */
    public Cursor fetchAllNotes() {

        return mDb.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_TITLE,
                KEY_BODY}, null, null, null, null, null);
    }

    /**
     * Devolver un cursor ubicado en la nota que coincide con la dada RowId
           *
           * @param RowId Identificación de la nota para recuperar
           * @return Cursor posicionado para nota a juego, si se encuentra
           * @throws SQLException si la nota no se pudo encontrar / recuperada
     */
    public Cursor fetchNote(long rowId) throws SQLException {

        Cursor mCursor =

            mDb.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                    KEY_TITLE, KEY_BODY}, KEY_ROWID + "=" + rowId, null,
                    null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    /**
     * Actualización de la nota mediante los datos proporcionados. La nota es que actualizarse
           * Especifica utilizando el identificador, y se altera para utilizar el título y el cuerpo
           * Valores pasados en
           *
           * @param RowId Identificación de la nota para actualizar
           * @ Param valor del título para establecer el título de la nota
           * @ Param valor cuerpo para fijar el cuerpo de la nota
           * @return True si la nota se ha actualizado correctamente, false en caso contrario
     */
    public boolean updateNote(long rowId, String title, String body) {
        ContentValues args = new ContentValues();
        args.put(KEY_TITLE, title);
        args.put(KEY_BODY, body);

        return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
}
