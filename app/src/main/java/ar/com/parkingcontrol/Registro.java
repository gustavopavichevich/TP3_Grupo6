package ar.com.parkingcontrol;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Registro extends AppCompatActivity {

    private EditText _nombre, _email, _contrasenia, _recontrasenia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        _nombre = (EditText) findViewById(R.id.etNombre);
        _email = (EditText) findViewById(R.id.edEmail);
        _contrasenia = (EditText) findViewById(R.id.etContrasenia);
        _recontrasenia = (EditText) findViewById(R.id.etRepetirContrasenia);
    }

    //Méotdo para dar de alta al nuevo usuario de la app
    public void registrar(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String nombre = _nombre.getText().toString();
        String email = _email.getText().toString();
        String contrasenia = _contrasenia.getText().toString();
        String recontrasenia = _recontrasenia.getText().toString();

        if (!nombre.isEmpty() && !email.isEmpty() && !contrasenia.isEmpty() && !recontrasenia.isEmpty()) {
            //Valido las contraseñas
            if (contrasenia.equals(recontrasenia)) {
                //Valido que no existe el email/usuario en la base de datos
                if (validarUsuario(email)) {
                    Toast.makeText(this, "El email ya se encuentra registrado", Toast.LENGTH_SHORT).show();
                } else {
                    //si no existe inicio para grabar el registro
                    ContentValues registro = new ContentValues();

                    registro.put("nombre", nombre);
                    registro.put("usuario", email);
                    registro.put("contrasenia", contrasenia);

                    BaseDeDatos.insert("usuarios", null, registro);

                    BaseDeDatos.close();
                    _nombre.setText("");
                    _email.setText("");
                    _contrasenia.setText("");
                    _recontrasenia.setText("");

                    Toast.makeText(this, "Registro ingresado exitosamente", Toast.LENGTH_SHORT).show();
                    Intent mainActivity = new Intent(this, MainActivity.class);
                    startActivity(mainActivity);
                }
            } else {
                Toast.makeText(this, "Las contraseñas no son iguales", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean validarUsuario(String usuario) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatabase = admin.getWritableDatabase();
        boolean result = false;

        if (!usuario.isEmpty()) {
            Cursor fila = BaseDeDatabase.rawQuery
                    ("select usuario from usuarios where usuario ='" + usuario + "'", null);

            if (fila.moveToFirst()) {
                result = true;
            }
            BaseDeDatabase.close();
        } else {
            Toast.makeText(this, "El usuario ingresado no existe", Toast.LENGTH_SHORT).show();
        }
        return result;
    }

}