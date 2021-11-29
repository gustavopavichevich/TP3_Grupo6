package ar.com.parkingcontrol.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import ar.com.parkingcontrol.model.dataBase.AdminSQLiteOpenHelper;
import ar.com.parkingcontrol.model.entity.Parqueo;

public class ParqueoDao {

    private static final String DATABASE = "APPDATABASE";
    private static final String TABLE = "parqueos";

    public static ArrayList<Parqueo> obtenerParqueosPorIdUsuario(Integer idUsuario, Context contexto) {
        SQLiteDatabase db = null;
        try {
            //Abrimos la conexion
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
            db = admin.getWritableDatabase();

            //Obtenemos los resultados de la query
            String query = "SELECT id, nromatricula, tiempo " + "FROM " + TABLE + " WHERE idusuario = " + idUsuario + " AND eliminado = 0";
            Cursor row = db.rawQuery(query, null);

            //Llenamos el array list con lo obtenido de la base de datos
            ArrayList<Parqueo> result = new ArrayList<Parqueo>();
            if (row.moveToFirst()) {
                while (!row.isAfterLast()) {
                    Parqueo p = new Parqueo(
                            row.getString(1),
                            row.getInt(2),
                            idUsuario
                    );
                    p.setId(row.getInt(0));
                    result.add(p);
                    row.moveToNext();
                }
            }
            return result;
        } catch (Exception e) {
            return null;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public static Boolean eliminarParqueoPorId(Integer idParqueo, Context contexto) {
        SQLiteDatabase db = null;
        try {
            //Preparamos el registro a insertar
            ContentValues registro = new ContentValues();
            registro.put("eliminado", 1);

            //Abrimos conexion con la base de datos
            AdminSQLiteOpenHelper servicio = new AdminSQLiteOpenHelper(contexto, DATABASE, null, 1);
            db = servicio.getWritableDatabase();
            //insertamos
            int cantidad = db.update(TABLE, registro, "id=" + idParqueo, null);
            db.close();
            return cantidad == 1;
        } catch (Exception e) {
            return null;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public static Parqueo modificarParqueo(Parqueo parqueo, Context contexto) {
        SQLiteDatabase db = null;
        try {
            //Preparamos el registro a modificar
            ContentValues registro = new ContentValues();
            registro.put("nromatricula", parqueo.getMatricula());
            registro.put("tiempo", parqueo.getTiempo());
            registro.put("idusuario", parqueo.getIdUsuario());

            //Abrimos conexion con la base de datos
            AdminSQLiteOpenHelper servicio = new AdminSQLiteOpenHelper(contexto, DATABASE, null, 1);
            db = servicio.getWritableDatabase();
            //modificamos
            int cantidad = db.update(TABLE, registro, "id=" + parqueo.getId(), null);
            db.close();
            if (cantidad == 1) {
                return parqueo;
            }
            return null;
        } catch (Exception e) {
            return null;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public static Parqueo crearNuevoParqueo(Parqueo parqueo, Context contexto) {
        SQLiteDatabase db = null;
        try {
            //Preparamos el registro a insertar
            ContentValues registro = new ContentValues();
            registro.put("nromatricula", parqueo.getMatricula());
            registro.put("tiempo", parqueo.getTiempo());
            registro.put("idusuario", parqueo.getIdUsuario());
            registro.put("eliminado", 0);
            //Abrimos conexion
            AdminSQLiteOpenHelper servicio = new AdminSQLiteOpenHelper(contexto, DATABASE, null, 1);
            db = servicio.getWritableDatabase();
            //Insertamos
            long id = db.insert(TABLE, null, registro);
            db.close();
            //Asignamos el id y retornamos
            parqueo.setId((int) id);
            return parqueo;

        } catch (Exception e) {
            return null;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }
}
