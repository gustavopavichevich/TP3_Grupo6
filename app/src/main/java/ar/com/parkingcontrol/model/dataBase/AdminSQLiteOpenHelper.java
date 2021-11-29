package ar.com.parkingcontrol.model.dataBase;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        BaseDeDatos.execSQL("create table usuarios(id INTEGER primary key autoincrement, nombreusuario text, email text, password text, eliminado int)");
        BaseDeDatos.execSQL("create table parqueos(id INTEGER primary key autoincrement, nromatricula text, tiempo INTEGER, eliminado INTEGER, idusuario INTEGER, FOREIGN KEY(idusuario) REFERENCES usuarios(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
