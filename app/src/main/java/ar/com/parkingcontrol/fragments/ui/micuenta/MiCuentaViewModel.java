package ar.com.parkingcontrol.fragments.ui.micuenta;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pa2_tp3_grupo3.Usuario;

public class MiCuentaViewModel extends ViewModel {

    private Usuario user;

    public MiCuentaViewModel() { }

    public LiveData<String> getNombre() {
        MutableLiveData<String> nombre = new MutableLiveData<>();
        nombre.setValue(user.getNombreUsuario());
        return nombre;
    }
    public LiveData<String> getEmail() {
        MutableLiveData<String> email = new MutableLiveData<>();
        email.setValue(user.getEmail());
        return email;
    }
    public LiveData<String> getPass() {
        MutableLiveData<String> pass = new MutableLiveData<>();
        pass.setValue(user.getPassword());
        return pass;
    }

    public void setUser(Usuario user){
        this.user = user;
    }
}