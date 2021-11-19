package ar.com.parkingcontrol;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText _usuario, _contrasenia, txtNumeroMatricula, txtTiempo;
    private TextView tvUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _usuario = (EditText) findViewById(R.id.txtUsuario);
        _contrasenia = (EditText) findViewById(R.id.txtContrasenia);
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

    public void OnClicBtnAceptarDialog(View view) {
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

    }

    public void OnClicBtnCancelarDialog(View view) {
        this.finish();
    }
}