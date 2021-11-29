package ar.com.parkingcontrol;

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

import com.example.pa2_tp3_grupo3.databinding.PrincipalActivity;
import com.google.android.material.navigation.NavigationView;

import ar.com.parkingcontrol.databinding.ContentPrincipalBinding;
import ar.com.parkingcontrol.model.entity.Usuario;

public class InicioUsuarioActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private PrincipalActivity binding;
    private Usuario user;

    public Usuario getUser() {
        return this.user;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Modificamos el usuario y el email del menu drawer
        this.user = (Usuario)getIntent().getSerializableExtra("usuario");

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
        userName.setText(this.user.getNombreUsuario());
        userEmail.setText(this.user.getEmail());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.inicio_usuario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem opcion_menu){
        //Manejamos el cierre de sesion
        int id=opcion_menu.getItemId();
        if(id==R.id.action_cerrarsesion){
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


}