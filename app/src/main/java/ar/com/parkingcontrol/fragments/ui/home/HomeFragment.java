package ar.com.parkingcontrol.fragments.ui.home;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ui.AppBarConfiguration;

import java.util.ArrayList;

import ar.com.parkingcontrol.MainActivity;
import ar.com.parkingcontrol.databinding.FragmentHomeBinding;
import ar.com.parkingcontrol.fragments.DialogoParqueosFragment;
import ar.com.parkingcontrol.model.dao.ParqueoDao;
import ar.com.parkingcontrol.model.entity.Parqueo;

public class HomeFragment extends Fragment implements DialogoParqueosFragment.DialogFragmentListener, EliminarDialog.EliminarDialogListener {
    private HomeViewModel homeViewModel;
    private AppBarConfiguration mAppBarConfiguration;
    private FragmentHomeBinding binding;
    private EditText txtNumeroMatricula, txtTiempo;
    private TextView tvUsuario;
    private MainActivity main;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        main = (MainActivity)getActivity();
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        DialogoParqueosFragment.DialogFragmentListener list = this;
        //Agregamos el listener en el boton de agregar para que abra el
        //dialogo de agregar parqueo
        binding.fabMenuMas.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DialogFragment dialog = new DialogFragment();
                dialog.setListener(list);
                dialog.setIdParqueo(null);
                dialog.setUser(tvUsuario.getText().toString());
                dialog.show(getActivity().getSupportFragmentManager(), null);
            }
        });
        cargarMatriculas();
        return view;
    }

    private void cargarMatriculas() {
        ArrayList<Parqueo> parqueos = ParqueoDao.obtenerParqueosPorIdUsuario(main.getUser().getId(), getActivity());

        TableLayout table = binding.TableMatriculas;
        if (!parqueos.isEmpty()) {
            EliminarDialog.EliminarDialogListener list = this;
            //Vaciamos la tabla
            table.removeAllViews();

            //Seteamos sus propiedades
            table.setStretchAllColumns(true);
            table.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));
            table.setWeightSum(3);

            TableRow tableRow = new TableRow(getActivity());
            Boolean noIncluida = true;
            for (int i = 0; i < parqueos.size(); i++) {

                Parqueo p = parqueos.get(i);
                if (i % 2 == 0) { // Cada 2 matriculas, creamos una nueva linea en la tabla
                    if (i != 0) {
                        noIncluida = false;
                        table.addView(tableRow);
                    }
                    //Creamos las filas y seteamos sus propiedades
                    tableRow = new TableRow(getActivity());
                    tableRow.setGravity(Gravity.CENTER);
                    tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1.0f));
                }
                //Creamos las entradas en la tabla con cada matricula
                Button button = new Button(getActivity());
                button.setId(p.getId());
                button.setText(p.getMatricula() + "\n" + p.getTiempo().toString());
                button.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0));
                button.setGravity(Gravity.CENTER);
                button.setPadding(3, 30, 3, 30);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EliminarDialog dialog = new EliminarDialog();
                        dialog.setListener(list);
                        Parqueo subp = new Parqueo();
                        dialog.setParqueo(p);
                        dialog.show(getActivity().getSupportFragmentManager(), null);
                    }
                });
                tableRow.addView(button);
                noIncluida = true;
            }
            if (noIncluida) { //Si nos quedo una linea sin agregar, la agregamos
                //Si en la linea hay solo un elemento, agregamos un textview vacio para alinear
                if (tableRow.getChildCount() != 2) {
                    TextView t = new TextView(getActivity());
                    t.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0));
                    tableRow.addView(t);
                }
                table.addView(tableRow);
            }
        } else {
            table.removeAllViews();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void salvarParqueo(Integer id, String matricula, String tiempo, Integer idUsuario) {
        Parqueo p = new Parqueo(matricula, Integer.valueOf(tiempo), idUsuario);
        if (id != null) {
            p = ParqueoDao.modificarParqueo(p, getActivity());
        } else {
            p = ParqueoDao.crearNuevoParqueo(p, getActivity());
        }
        if (p != null && p.getId() != null) {
            String mensaje = "Parqueo creado correctamente";
            if (id != null) {
                mensaje = "Parqueo modificado correctamente";
            }
            Toast.makeText(getActivity(), mensaje, Toast.LENGTH_SHORT).show();
            cargarMatriculas();
        } else {
            String mensaje = "Error creando parqueo";
            if (id != null) {
                mensaje = "Error modificando parqueo";
            }
            Toast.makeText(getActivity(), mensaje, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void eliminarParqueo(Parqueo parqueo) {
        if (ParqueoDao.eliminarParqueoPorId(parqueo.getId(), getActivity())) {
            Toast.makeText(getActivity(), "Parqueo eliminado correctamente!", Toast.LENGTH_SHORT).show();
            this.cargarMatriculas();
        }
    }
}