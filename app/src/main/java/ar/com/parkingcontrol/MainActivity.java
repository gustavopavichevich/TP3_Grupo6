package ar.com.parkingcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        txtId
        setContentView(R.layout.activity_main);
    }
    public void Registrar (View view)
    {
        Intent registrar = new Intent( this, Registro.class);
        startActivity(registrar );
    }
}