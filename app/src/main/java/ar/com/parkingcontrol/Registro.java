package ar.com.parkingcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import ar.com.parkingcontrol.entity.Usuario;

public class Registro extends AppCompatActivity {

    private EditText _nombre, _email, _contrasenia, _recontrasenia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        _nombre = (EditText)findViewById(R.id.txtNombre);
        _email = (EditText)findViewById(R.id.edEmail);
        _contrasenia = (EditText)findViewById(R.id.etContrasenia);
        _recontrasenia = (EditText)findViewById(R.id.etRepetirContrasenia);
    }
    //Méotdo para dar de alta al nuevo Usuario de la app
    public void Registrar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "parkingControl", null, 1);
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase();


        Usuario usuario =new Usuario(_nombre.getText().toString(),_email.getText().toString(),_contrasenia.getText().toString());

        //Valido que no queden campos vacios
        if(!usuario.getNombre().isEmpty() && !usuario.getEmail().isEmpty() && !usuario.getContrasenia().isEmpty() && !_recontrasenia.getText().toString().isEmpty()){
            //Valido las contraseñas
            if (usuario.getContrasenia().trim().equals(_recontrasenia.getText().toString().trim())) {
                //Valido que no existe el email/Usuario en la base de datos
                if (ValidarUsuario(usuario)) {
                    Toast.makeText(this, "El usuario ya se encuentra registrado", Toast.LENGTH_SHORT).show();
                }else
                {
                    //si no existe inicio para grabar el registro
                    ContentValues registro = new ContentValues();

                    registro.put("nombre", usuario.getNombre());
                    registro.put("Usuario", usuario.getEmail());
                    registro.put("contrasenia", usuario.getContrasenia());

                    baseDeDatos.insert("usuarios", null, registro);

                    baseDeDatos.close();
                    _nombre.setText("");
                    _email.setText("");
                    _contrasenia.setText("");
                    _recontrasenia.setText("");

                    Toast.makeText(this,"Registro ingresado exitosamente", Toast.LENGTH_SHORT).show();
                }
            } else{
                Toast.makeText(this, "Las contraseñas no son iguales", Toast.LENGTH_SHORT).show();
            }
        } else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
    public boolean ValidarUsuario(Usuario usuario){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "parkingControl", null, 1);
        SQLiteDatabase BaseDeDatabase = admin.getWritableDatabase();
        boolean result = false;
        Cursor fila = BaseDeDatabase.rawQuery
                ("select nombre from usuarios where nombre =' " + usuario.getNombre() + "' or email=' " + usuario.getEmail() + "'" , null);
        if(fila.moveToFirst())
            result = true;
        BaseDeDatabase.close();
        return result;
    }
}