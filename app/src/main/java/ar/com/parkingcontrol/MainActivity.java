package ar.com.parkingcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText _usuario, _contrasenia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _usuario = (EditText)findViewById(R.id.txtUsuario);
        _contrasenia = (EditText)findViewById(R.id.txtContrasenia);
    }
    public void Registrar (View view)
    {
        Intent registrar = new Intent( this, Registro.class);
        startActivity(registrar );
    }
    //Método para el inicio de sesion
    public void IniciarSesion(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatabase = admin.getWritableDatabase();

        String usuario = _usuario.getText().toString();
        String contrasenia = _contrasenia.getText().toString();

        boolean result = false;

        if(!usuario.isEmpty() && !contrasenia.isEmpty()){
            Cursor fila = BaseDeDatabase.rawQuery
                    ("select usuario from usuarios where usuario ='" + usuario + "' and contrasenia='" + contrasenia + "'", null);

            if(fila.moveToFirst()){
                //aca abro la pantalla del menu "Mi cuenta" y "Parqueos"
                Toast.makeText(this, "Sesion Exitosa", Toast.LENGTH_SHORT).show();
                BaseDeDatabase.close();
            } else {
                Toast.makeText(this, "Usuario o Contaseña erroneos", Toast.LENGTH_SHORT).show();
                BaseDeDatabase.close();
            }

        } else {
            Toast.makeText(this, "Debes introducir los datos, son obligatorios", Toast.LENGTH_SHORT).show();
        }
    }
}