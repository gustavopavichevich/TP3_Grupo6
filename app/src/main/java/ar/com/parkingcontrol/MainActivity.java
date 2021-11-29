package ar.com.parkingcontrol;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ar.com.parkingcontrol.model.dao.UsuarioDao;
import ar.com.parkingcontrol.model.entity.Usuario;

public class MainActivity extends AppCompatActivity {
    private EditText etUsuario, etContrasenia;
    private String usuario, contrasenia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsuario = findViewById(R.id.txtUsuario);
        etContrasenia = findViewById(R.id.txtContrasenia);
    }

    public void registrar(View view) {
        Intent registrar = new Intent(this, RegistroActivity.class);
        startActivity(registrar);
    }

    //Método para el inicio de sesion
    public void iniciarSesion(View view) {
        usuario = this.etUsuario.getText().toString();
        contrasenia = this.etContrasenia.getText().toString();

        if (!usuario.isEmpty() && !contrasenia.isEmpty()) {
            Usuario user = UsuarioDao.obtenerUsuarioPorNombreDeUsuario(usuario, this);
            if (user != null) {
                if (user.getPassword().equals(contrasenia)) {

                    Intent principal = new Intent(this, PrincipalActivity.class);
                    principal.putExtra("usuario", user);

                    startActivity(principal);

                } else {
                    Toast.makeText(this, "Corrobore los datos ingresados", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "El usuario no existe", Toast.LENGTH_LONG).show();
            }

        } else {
        Toast.makeText(this, "Corrobore los datos ingresados", Toast.LENGTH_LONG).show();
    }
    }
}