package utng.edu.mx.proyectoitext.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import utng.edu.mx.proyectoitext.model.Usuario;


public interface UsuarioDAO {
    void agregar(Usuario usuario, SQLiteDatabase db);
    void modificar(Usuario usuario, SQLiteDatabase db);
    void eliminar(Usuario usuario, SQLiteDatabase db);
    Cursor listar(SQLiteDatabase db);
}
