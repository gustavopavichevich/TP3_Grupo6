package ar.com.parkingcontrol.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import ar.com.parkingcontrol.model.dataBase.AdminSQLiteOpenHelper;
import ar.com.parkingcontrol.model.entity.Usuario;

public class UsuarioDao {

    private static final String DATABASE = "parkingControl";
    private static final String TABLE = "usuarios";

    public static Usuario obtenerUsuarioPorNombreDeUsuario(String nombre, Context contexto) {
        SQLiteDatabase db = null;
        try {
            //Abrimos la conexion
            AdminSQLiteOpenHelper servicio = new AdminSQLiteOpenHelper(contexto, DATABASE, null, 1);
            db = servicio.getWritableDatabase();

            //Obtenemos los resultados de la query
            String query = "SELECT id, nombreusuario,password, email  " + "FROM " + TABLE + " WHERE nombreusuario = \"" + nombre + "\" AND eliminado = 0";
            Cursor row = db.rawQuery(query, null);

            //Llenamos el array list con lo obtenido de la base de datos
            Usuario us = null;
            if (row.moveToFirst()) {
                us = new Usuario(
                        row.getString(1),
                        row.getString(2),
                        row.getString(3)
                );
                us.setId(row.getInt(0));
            }
            return us;
        } catch (Exception e) {
            return null;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public static Usuario obtenerUsuarioPorEmail(String email, Context contexto) {
        SQLiteDatabase db = null;
        try {
            //Abrimos la conexion
            AdminSQLiteOpenHelper servicio = new AdminSQLiteOpenHelper(contexto, DATABASE, null, 1);
            db = servicio.getWritableDatabase();

            //Obtenemos los resultados de la query
            String query = "SELECT id, nombreusuario,password, email  " + "FROM " + TABLE + " WHERE email = \"" + email + "\" AND eliminado = 0";
            Cursor row = db.rawQuery(query, null);

            //Llenamos el array list con lo obtenido de la base de datos
            Usuario us = null;
            if (row.moveToFirst()) {
                us = new Usuario(
                        row.getString(1),
                        row.getString(2),
                        row.getString(3)
                );
                us.setId(row.getInt(0));
            }
            return us;
        } catch (Exception e) {
            return null;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }


    public static Usuario crearNuevoUsuario(Usuario user, Context contexto) {
        SQLiteDatabase db = null;
        try {
            //Preparamos el registro a insertar
            ContentValues registro = new ContentValues();
            registro.put("nombreusuario", user.getNombreUsuario());
            registro.put("email", user.getEmail());
            registro.put("password", user.getPassword());
            registro.put("eliminado", 0);

            //Abrimos conexion con la base de datos
            AdminSQLiteOpenHelper servicio = new AdminSQLiteOpenHelper(contexto, DATABASE, null, 1);
            db = servicio.getWritableDatabase();
            //insertamos
            long id = db.insert(TABLE, null, registro);
            db.close();
            //Agregamos al objeto el id recien creado
            user.setId((int) id);
            return user;

        } catch (Exception e) {
            return null;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

}
