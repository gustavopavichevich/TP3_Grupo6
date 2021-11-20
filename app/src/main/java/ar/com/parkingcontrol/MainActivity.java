package ar.com.parkingcontrol;

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
    private final ArrayList<Parqueo> listaParqueos = new ArrayList<Parqueo>();
    private GridView gvParquos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _usuario = findViewById(R.id.txtUsuario);
        _contrasenia = findViewById(R.id.txtContrasenia);

//        gvParquos = findViewById(R.id.gvParqueos);

//        recargarParqueos();

    }

    public void registrar(View view) {
        Intent registrar = new Intent(this, Registro.class);
        startActivity(registrar);
    }

    //Método para el inicio de sesion
    public void iniciarSesion(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatabase = admin.getWritableDatabase();

        String usuario = _usuario.getText().toString();
        String contrasenia = _contrasenia.getText().toString();
        usuario = "gp@gmail.com";
        contrasenia = "123";

        boolean result = false;

        if (!usuario.isEmpty() && !contrasenia.isEmpty()) {
            Cursor fila = BaseDeDatabase.rawQuery
                    ("select nombre, usuario from usuarios where usuario ='" + usuario + "' and contrasenia='" + contrasenia + "'", null);

            if (fila.moveToFirst()) {
                //aca abro la pantalla del menu "Mi cuenta" y "Parqueos"
                //Toast.makeText(this, "Sesion Exitosa", Toast.LENGTH_SHORT).show();
                BaseDeDatabase.close();
                Intent principal = new Intent(this, Principal.class);
                principal.putExtra("usuario", usuario);
//                TextView tvUsuario = (TextView) .findViewById(R.id.tvUsuario);
//                TextView tvEmail = (TextView) view.findViewById(R.id.tvUsuario);
//                tvUsuario.setText(usuario);
//                tvEmail.setText(usuario.split("@")[0]+"@parking.com");
                startActivity(principal);
            } else {
                Toast.makeText(this, "Usuario o Contaseña erroneos", Toast.LENGTH_SHORT).show();
                BaseDeDatabase.close();
            }

        } else {
            Toast.makeText(this, "Debes introducir los datos, son obligatorios", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickBtnAceptarDialog(View view) {

    }

    public void onClicBtnCancelarDialog(View view) {
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
    }

    public void recargarParqueos() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatabase = admin.getReadableDatabase();
        Cursor cursor = null;
        listaParqueos.clear();
        cursor = BaseDeDatabase.rawQuery("select * from parqueos", null);
        if (cursor.moveToFirst()) {
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
        ArrayAdapter<Parqueo> adapter = new ArrayAdapter<Parqueo>(this, android.R.layout.simple_list_item_1, listaParqueos);
        gvParquos.setAdapter(adapter);
    }
}