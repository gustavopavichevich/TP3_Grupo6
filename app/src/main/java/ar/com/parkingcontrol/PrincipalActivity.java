package ar.com.parkingcontrol;

//import android.app.AlertDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import ar.com.parkingcontrol.model.entity.Usuario;


public class PrincipalActivity extends AppCompatActivity {


    private AppBarConfiguration mAppBarConfiguration;
    private PrincipalActivity binding;
    private Usuario usuario;

    public Usuario getUsuario() {
        return this.usuario;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Modificamos el usuario y el email del menu drawer
        this.usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        binding = PrincipalActivity.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarInicioUsuario.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_micuenta)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_inicio_usuario);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        //Lo anterior fue credo por android al agregar el layout
        // lo siguiente es la asignacion de los valores de nombre de usuaro e email que se ven
        // en el menu que se oculta a la izquierda

        NavigationView mNavigation = findViewById(R.id.nav_view);
        //Obtenemos el menu lateral
        View headerView = mNavigation.getHeaderView(0);
        // Obtenemos los campos de nombre e email
        TextView userName = headerView.findViewById(R.id.txtUserNameLabel);
        TextView userEmail = headerView.findViewById(R.id.txtUserEmailLabel);
        // Les cambiamos el valor
        userName.setText(this.usuario.getNombreUsuario());
        userEmail.setText(this.usuario.getEmail());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.inicio_usuario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem opcion_menu) {
        //Manejamos el cierre de sesion
        int id = opcion_menu.getItemId();
        if (id == R.id.action_cerrarsesion) {
            Intent i = new Intent(this, MainActivity.class);
            //Con estas banderas logramos que borre el historial de atras para salir realmente de la sesion.
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(opcion_menu);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_inicio_usuario);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }


//    private HomeViewModel homeViewModel;
//    private AppBarConfiguration mAppBarConfiguration;
//    private FragmentHomeBinding binding;
//    private EditText txtNumeroMatricula, txtTiempo;
//    private TextView tvUsuario;
//    private Usuario usuario;
//
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
//        View view = inflater.inflate(R.layout.fragment_principal, container, false);
//        DialogoParqueosFragment.DialogFragmentListener list = this;
//        //Agregamos el listener en el boton de agregar para que abra el
//        //dialogo de agregar parqueo
//
//        FloatingActionButton  floatingactionbutton = container.findViewById(R.id.fabMenuMas);
//
//        floatingactionbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DialogoParqueosFragment dialog = new DialogoParqueosFragment();
//                dialog.setListener(list);
//                dialog.setIdParqueo(null);
//                dialog.setUser(tvUsuario.getText().toString());
//                dialog.show(getActivity().getSupportFragmentManager(),null );
//            }
//        });
//        cargarMatriculas();
//        return view;
//    }
//
////    private void showEditDialog() {
////        FragmentManager fm = getSupportFragmentManager();
////        DialogoParqueosFragment dialogFragment = DialogoParqueosFragment.newInstance("Some Title");
////        dialogFragment.show(fm, "dialog_design");
////    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.principal, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_principal);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }
//    public void onClickBtnAceptarDialog(View view) {
////        txtNumeroMatricula = (EditText) view.findViewById(R.id.txtNumeroMatricula);
////        String matricula = txtNumeroMatricula.getText().toString();
////        txtTiempo = (EditText) view.findViewById(R.id.txtTiempo);
////        String tiempo = txtTiempo.getText().toString();
////        tvUsuario = (TextView) view.findViewById(R.id.tvUsuario);
////        String usuario = tvUsuario.getText().toString();
////        FragmentManager fm = getSupportFragmentManager();
////        ParqueosFragment parqueosFragment = ParqueosFragment.newInstance(matricula, tiempo, usuario);
////        parqueosFragment.show(fm, "fragmentParqueos");
////
////        if (matricula.isEmpty() || tiempo.isEmpty()) {
////            Toast.makeText(this, "Por favor, ingrese todos los campos", Toast.LENGTH_SHORT).show();
////            return;
////        }
////
////        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
////        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
////
////        ContentValues registro = new ContentValues();
////
////        registro.put("usuario", usuario);
////        registro.put("matricula", matricula);
////        registro.put("tiempo", tiempo);
////
////        BaseDeDatos.insert("parqueos", null, registro);
////
////        BaseDeDatos.close();
////        Toast.makeText(this, "Parqueo grabado exitosamente", Toast.LENGTH_LONG).show();
////        txtNumeroMatricula.setText("Numero de matricula");
////        txtTiempo.setText("Tiempo");
//
//    }
//
//    public void onClicBtnCancelarDialog(View view) {
//        return;
//    }
//
//    @Override
//    public void salvarParqueo(Parqueo p ) {
//        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
//        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
//
//        ContentValues registro = new ContentValues();
//
//        registro.put("usuario", p.getUsuario());
//        registro.put("matricula", p.getMatricula());
//        registro.put("tiempo", p.getTiempo());
//
//        BaseDeDatos.insert("parqueos", null, registro);
//
//        BaseDeDatos.close();
//        Toast.makeText(this, "Parqueo grabado exitosamente", Toast.LENGTH_LONG).show();
//        txtNumeroMatricula.setText("Numero de matricula");
//        txtTiempo.setText("Tiempo");
//
//    }
}