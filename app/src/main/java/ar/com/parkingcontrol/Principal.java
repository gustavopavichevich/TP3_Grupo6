package ar.com.parkingcontrol;

//import android.app.AlertDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import ar.com.parkingcontrol.Entidades.Parqueo;
import ar.com.parkingcontrol.databinding.ActivityPrincipalBinding;
import ar.com.parkingcontrol.ui.home.*;


public class Principal extends Fragment implements DialogFragment.ParqueoDialogListener {

    private HomeViewModel homeViewModel;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityPrincipalBinding binding;
    private EditText txtNumeroMatricula, txtTiempo;
    private TextView tvUsuario;
    private MainActivity main;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        main = (MainActivity)getActivity();
        binding = ActivityPrincipalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        DialogFragment.ParqueoDialogListener list = this;
        //Agregamos el listener en el boton de agregar para que abra el
        //dialogo de agregar parqueo

        FloatingActionButton  floatingactionbutton = container.findViewById(R.id.fabMenuMas);

        floatingactionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialog = new DialogFragment();
                dialog.setListener(list);
                dialog.setIdParqueo(null);
                dialog.setUser(tvUsuario.getText().toString());
                dialog.show(getActivity().getSupportFragmentManager(),null );
            }
        });
        cargarMatriculas();
        return root;
    }

//    private void showEditDialog() {
//        FragmentManager fm = getSupportFragmentManager();
//        DialogFragment dialogFragment = DialogFragment.newInstance("Some Title");
//        dialogFragment.show(fm, "dialog_design");
//    }

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

    @Override
    public void salvarParqueo(Parqueo p ) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        ContentValues registro = new ContentValues();

        registro.put("usuario", p.getUsuario());
        registro.put("matricula", p.getMatricula());
        registro.put("tiempo", p.getTiempo());

        BaseDeDatos.insert("parqueos", null, registro);

        BaseDeDatos.close();
        Toast.makeText(this, "Parqueo grabado exitosamente", Toast.LENGTH_LONG).show();
        txtNumeroMatricula.setText("Numero de matricula");
        txtTiempo.setText("Tiempo");

    }
}