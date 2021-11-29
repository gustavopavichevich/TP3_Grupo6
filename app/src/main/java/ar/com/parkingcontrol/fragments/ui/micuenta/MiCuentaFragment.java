package ar.com.parkingcontrol.fragments.ui.micuenta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.pa2_tp3_grupo3.InicioUsuarioActivity;
import com.example.pa2_tp3_grupo3.databinding.FragmentMicuentaBinding;

public class MiCuentaFragment extends Fragment  {

    private MiCuentaViewModel miCuentaViewModel;
    private FragmentMicuentaBinding binding;
    private InicioUsuarioActivity main;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        main = (InicioUsuarioActivity)getActivity();
        miCuentaViewModel = new ViewModelProvider(this).get(MiCuentaViewModel.class);
        miCuentaViewModel.setUser(main.getUser());
        binding = FragmentMicuentaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView nombre = binding.nombreUsuario;
        final TextView email = binding.emailUsuario;
        final TextView pass = binding.passUsuario;

        miCuentaViewModel.getNombre().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                nombre.setText(s);
            }
        });

        miCuentaViewModel.getEmail().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                email.setText(s);
            }
        });

        miCuentaViewModel.getPass().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                pass.setText(s);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}