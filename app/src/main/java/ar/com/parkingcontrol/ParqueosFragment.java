package ar.com.parkingcontrol;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class ParqueosFragment extends Fragment {
    String txtNumeroMatricula;
    String txtTiempo;
    String tvUsuario;

    public ParqueosFragment() {
        // Required empty public constructor
    }

    public static ParqueosFragment newInstance(String tNumeroMatricula, String tTiempo, String tUsuario) {
        ParqueosFragment fragment = new ParqueosFragment();
        Bundle args = new Bundle();
        args.putString("txtNumeroMatricula", tNumeroMatricula);
        args.putString("txtTiempo", tTiempo);
        args.putString("txtUsuario", tUsuario);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parqueos, container, false);
        return view;
    }
}