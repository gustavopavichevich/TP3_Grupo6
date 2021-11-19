package ar.com.parkingcontrol;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import ar.com.parkingcontrol.Entidades.Parqueo;

public class MainActivity extends AppCompatActivity {
    private EditText _usuario, _contrasenia, txtNumeroMatricula, txtTiempo;
    private TextView tvUsuario;
    private ArrayList<Parqueo> listaParqueos = new ArrayList<Parqueo>();;
private GridView gvParquos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _usuario = (EditText) findViewById(R.id.txtUsuario);
        _contrasenia = (EditText) findViewById(R.id.txtContrasenia);

        gvParquos = (GridView) findViewById(R.id.gvParqueos);

        ArrayAdapter<Parqueo> adapter = new ArrayAdapter<Parqueo>(this, android.R.layout.simple_list_item_1, listaParqueos);
        gvParquos.setAdapter(adapter);
    }

    public void Registrar(View view) {
        Intent registrar = new Intent(this, Registro.class);
        startActivity(registrar);
    }

    //Método para el inicio de sesion
    public void IniciarSesion(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatabase = admin.getWritableDatabase();

        String usuario = _usuario.getText().toString();
        String contrasenia = _contrasenia.getText().toString();

        boolean result = false;

        if (!usuario.isEmpty() && !contrasenia.isEmpty()) {
            Cursor fila = BaseDeDatabase.rawQuery
                    ("select nombre, usuario from usuarios where usuario ='" + usuario + "' and contrasenia='" + contrasenia + "'", null);

            if (fila.moveToFirst()) {
                //aca abro la pantalla del menu "Mi cuenta" y "Parqueos"
                //Toast.makeText(this, "Sesion Exitosa", Toast.LENGTH_SHORT).show();
                BaseDeDatabase.close();
                Intent principal = new Intent(this, Principal.class);
                principal.putExtra("nombre", fila.getString(0));
                principal.putExtra("email", fila.getString(1));
                startActivity(principal);
            } else {
                Toast.makeText(this, "Usuario o Contaseña erroneos", Toast.LENGTH_SHORT).show();
                BaseDeDatabase.close();
            }

        } else {
            Toast.makeText(this, "Debes introducir los datos, son obligatorios", Toast.LENGTH_SHORT).show();
        }
    }

    public void OnClickBtnAceptarDialog(View view) {
        txtNumeroMatricula = (EditText) view.findViewById(R.id.txtNumeroMatricula);
        String matricula = txtNumeroMatricula.getText().toString();
        txtTiempo = (EditText) view.findViewById(R.id.txtTiempo);
        String tiempo = txtTiempo.getText().toString();
        tvUsuario = (TextView) view.findViewById(R.id.tvUsuario);
        String usuario = tvUsuario.getText().toString();
        if (matricula.isEmpty() || tiempo.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        ContentValues registro = new ContentValues();

        registro.put("usuario", usuario);
        registro.put("matricula", matricula);
        registro.put("tiempo", tiempo);

        BaseDeDatos.insert("parqueos", null, registro);

        BaseDeDatos.close();
        Toast.makeText(this,"Parqueo grabado exitosamente", Toast.LENGTH_LONG).show();
        Intent mainActivity = new Intent( this, MainActivity.class);
        startActivity(mainActivity);
    }

    public void OnClicBtnCancelarDialog(View view) {
        return;
    }
    public ArrayList<Parqueo> regargarParqueos() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatabase = admin.getReadableDatabase();
        Cursor cursor = null;
        cursor = BaseDeDatabase.rawQuery("select * from parqueos", null);
        if(cursor.moveToFirst()) {
            do {
                Parqueo parqueo = new Parqueo();
                parqueo.setUsuario(cursor.getString(0));
                parqueo.setMatricula(cursor.getString(1));
                parqueo.setTiempo(cursor.getString(2));
                listaParqueos.add(parqueo);
            }
            while (cursor.moveToNext());
        }
        BaseDeDatabase.close();
        return listaParqueos;
    }
}