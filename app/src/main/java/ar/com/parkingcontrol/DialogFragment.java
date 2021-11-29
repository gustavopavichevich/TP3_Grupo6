package ar.com.parkingcontrol;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import ar.com.parkingcontrol.Entidades.Parqueo;

public class DialogFragment extends AppCompatDialogFragment {
    private EditText matricula;
    private EditText tiempo;
    private Parqueo parqueo;
    private TextView tvUsuario;
    private Integer idParqueo;
    private ParqueoDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_design, null);
        //Construimos la vista del dialog y agregamos las dos acciones.
        builder.setView(view)
                .setTitle("Registrar Parqueo")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //En caso de click en cancelar no hacemos nada.
                    }
                })
                .setPositiveButton("Registrar Parqueo", null); //Seteamos el listener en null para poder validar sin cerrar el dialogo
        tvUsuario = view.findViewById(R.id.tvUsuario);
        matricula = view.findViewById(R.id.txtNumeroMatricula);
        tiempo = view.findViewById(R.id.txtTiempo);

        Dialog d = builder.create();

        //Agregamos el listener al boton registrar del dialogo y las validaciones
        d.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button button = ((AlertDialog) d).getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        String usuario = tvUsuario.getText().toString();
                        String mat = matricula.getText().toString();
                        String time = tiempo.getText().toString();
                        //Si hace click en registrar, validamos los campos y agregamos el parqueo
                        if (mat.isEmpty() || time.isEmpty()) {
                            Toast.makeText(getActivity(), "Por favor, ingrese todos los campos", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            Parqueo p = new Parqueo(mat, time, usuario);
                            listener.salvarParqueo(p);
                            d.dismiss();
                        }
                    }
                });
            }
        });
        return d;
    }

    public void setUser(String usuario) {
        this.parqueo.setUsuario(usuario);
    }

    public void setIdParqueo(Integer idParqueo) {
        this.idParqueo = idParqueo;
    }

    //En este metodo se validan los campos, devuelve true cuando
    //los campos cumplen con la validacion, si no false.
    // Ademas si un campo no es valido, se encarga de mostrar el toast
    /*private Boolean camposValidos(){
        if(Validador.textoVacio(matricula.getText().toString())){
            matricula.setError("La matricula no puede estar vacia");
            return false;
        }
        if(Validador.textoVacio(tiempo.getText().toString())){
            tiempo.setError("El tiempo no puede estar vacia");
            return false;
        }
        if(Integer.valueOf(tiempo.getText().toString()) == 0){
            tiempo.setError("El tiempo no puede ser 0");
            return false;
        }
        return true;
    }*/

    public void setListener(ParqueoDialogListener listener) {
        this.listener = listener;
    }

    //Esta interface nos sirve para hacer llegar los datos del parque al Fragment que inicio el dialog
    public interface ParqueoDialogListener {
        void salvarParqueo(Parqueo p );
    }

//    private EditText txtNumeroMatricula, txtTiempo;
//    private TextView tvUsuario;
//    private Button btnAceptarParqueo;
//
//    public DialogFragment() {
//        // Empty constructor is required for DialogFragment
//        // Make sure not to add arguments to the constructor
//        // Use `newInstance` instead as shown below
//    }
//
//    public static DialogFragment newInstance(String title) {
//        DialogFragment frag = new DialogFragment();
//        Bundle args = new Bundle();
//        args.putString("title", title);
//        frag.setArguments(args);
//        return frag;
//    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.dialog_design, container);
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
//        return view;
//    }

//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        // Get field from view
//        txtNumeroMatricula = view.findViewById(R.id.txtNumeroMatricula);
//        // Fetch arguments from bundle and set title
//        String title = "Registrar Parqueos";
////                getArguments().getString("title", "Enter Name");
//        getDialog().setTitle(title);
//        // Show soft keyboard automatically and request focus to field
//        txtNumeroMatricula.requestFocus();
//        getDialog().getWindow().setSoftInputMode(
//                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//    }

}
