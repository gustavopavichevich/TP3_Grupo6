package ar.com.parkingcontrol;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

public class DialogFragment extends androidx.fragment.app.DialogFragment {

    private EditText txtNumeroMatricula, txtTiempo;
    private TextView tvUsuario;
    private Button btnAceptarParqueo;

    public DialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static DialogFragment newInstance(String title) {
        DialogFragment frag = new DialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_design, container);
//        txtNumeroMatricula = (EditText) view.findViewById(R.id.txtNumeroMatricula);
//        String matricula = txtNumeroMatricula.getText().toString();
//        txtTiempo = (EditText) view.findViewById(R.id.txtTiempo);
//        String tiempo = txtTiempo.getText().toString();
//        tvUsuario = (TextView) view.findViewById(R.id.tvUsuario);
//        String usuario = tvUsuario.getText().toString();
//        btnAceptarParqueo = (Button) view.findViewById(R.id.btnAceptarParqueo);
//        btnAceptarParqueo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                txtNumeroMatricula = (EditText) getActivity().findViewById(R.id.txtNumeroMatricula);
//                String matricula = txtNumeroMatricula.getText().toString();
//                txtTiempo = (EditText) getActivity().findViewById(R.id.txtTiempo);
//                String tiempo = txtTiempo.getText().toString();
//                tvUsuario = (TextView) getActivity().findViewById(R.id.tvUsuario);
//                String usuario = tvUsuario.getText().toString();
//
//                if (matricula.isEmpty() || tiempo.isEmpty()) {
//                    Toast.makeText(getActivity(), "Por favor, ingrese todos los campos", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                final AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getActivity(), "administracion", null, 1);
//                final SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
//
//                final ContentValues registro = new ContentValues();
//
//                registro.put("usuario", usuario);
//                registro.put("matricula", matricula);
//                registro.put("tiempo", tiempo);
//
//                BaseDeDatos.insert("parqueos", null, registro);
//
//                BaseDeDatos.close();
//                Toast.makeText(
//
//                        getActivity(), "Parqueo grabado exitosamente", Toast.LENGTH_LONG).
//
//                        show();
//                txtNumeroMatricula.setText("Numero de matricula");
//                txtTiempo.setText("Tiempo");
//
//            }
//        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        txtNumeroMatricula = view.findViewById(R.id.txtNumeroMatricula);
        // Fetch arguments from bundle and set title
        String title = "Registrar Parqueos";
//                getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
        // Show soft keyboard automatically and request focus to field
        txtNumeroMatricula.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

}
