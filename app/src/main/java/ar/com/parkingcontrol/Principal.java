package ar.com.parkingcontrol;

//import android.app.AlertDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import ar.com.parkingcontrol.databinding.ActivityPrincipalBinding;

public class Principal extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityPrincipalBinding binding;
    private EditText txtNumeroMatricula, txtTiempo;
    private TextView tvUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPrincipalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarPrincipal.toolbar);
        binding.appBarPrincipal.fabMenuMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Programar Salida", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                showEditDialog();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.parqueosFragment, R.id.miCuentaFragment2)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_principal);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        DialogFragment dialogFragment = DialogFragment.newInstance("Some Title");
        dialogFragment.show(fm, "dialog_design");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_principal);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void onClickBtnAceptarDialog(View view) {
//        txtNumeroMatricula = (EditText) view.findViewById(R.id.txtNumeroMatricula);
//        String matricula = txtNumeroMatricula.getText().toString();
//        txtTiempo = (EditText) view.findViewById(R.id.txtTiempo);
//        String tiempo = txtTiempo.getText().toString();
//        tvUsuario = (TextView) view.findViewById(R.id.tvUsuario);
//        String usuario = tvUsuario.getText().toString();
//        FragmentManager fm = getSupportFragmentManager();
//        ParqueosFragment parqueosFragment = ParqueosFragment.newInstance(matricula, tiempo, usuario);
//        parqueosFragment.show(fm, "fragmentParqueos");
//
//        if (matricula.isEmpty() || tiempo.isEmpty()) {
//            Toast.makeText(this, "Por favor, ingrese todos los campos", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
//        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
//
//        ContentValues registro = new ContentValues();
//
//        registro.put("usuario", usuario);
//        registro.put("matricula", matricula);
//        registro.put("tiempo", tiempo);
//
//        BaseDeDatos.insert("parqueos", null, registro);
//
//        BaseDeDatos.close();
//        Toast.makeText(this, "Parqueo grabado exitosamente", Toast.LENGTH_LONG).show();
//        txtNumeroMatricula.setText("Numero de matricula");
//        txtTiempo.setText("Tiempo");

    }

    public void onClicBtnCancelarDialog(View view) {
        return;
    }

}