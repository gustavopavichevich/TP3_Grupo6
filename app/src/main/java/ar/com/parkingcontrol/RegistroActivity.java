package ar.com.parkingcontrol;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ar.com.parkingcontrol.model.dao.UsuarioDao;
import ar.com.parkingcontrol.model.entity.Usuario;

public class RegistroActivity extends AppCompatActivity {

    private EditText etNombre, etEmail, etContrasenia, etRecontrasenia;
    private String nombre, email, contrasenia, recontrasenia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etNombre = (EditText) findViewById(R.id.etNombre);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etContrasenia = (EditText) findViewById(R.id.etContrasenia);
        etRecontrasenia = (EditText) findViewById(R.id.etRepetirContrasenia);
    }

    //Méotdo para dar de alta al nuevo usuario de la app
    public void registrar(View view) {

        nombre = this.etNombre.getText().toString();
        email = this.etEmail.getText().toString();
        contrasenia = this.etContrasenia.getText().toString();
        recontrasenia = this.etRecontrasenia.getText().toString();

        if (!nombre.isEmpty() && !email.isEmpty() && !contrasenia.isEmpty() && !recontrasenia.isEmpty()) {
            //Valido las contraseñas
            if (contrasenia.equals(recontrasenia)) {
                //Valido que no existe el email/usuario en la base de datos

                if (validarUsuario(nombre, email)) {
                    Usuario newUser = UsuarioDao.crearNuevoUsuario(new Usuario(
                            nombre,
                            contrasenia,
                            email
                    ), this);

                    this.etNombre.setText("");
                    this.etEmail.setText("");
                    this.etContrasenia.setText("");
                    this.etRecontrasenia.setText("");

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

    public Boolean validarUsuario(String nombre, String correo) {
        if (UsuarioDao.obtenerUsuarioPorNombreDeUsuario(nombre, this) != null) {
            Toast.makeText(this, "El usuario ingresado ya está en uso", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (UsuarioDao.obtenerUsuarioPorEmail(correo, this) != null) {
            Toast.makeText(this, "El mail ya se encuentra registrado", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}