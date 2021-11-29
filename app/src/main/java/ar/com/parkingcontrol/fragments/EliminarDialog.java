package ar.com.parkingcontrol.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import ar.com.parkingcontrol.R;
import ar.com.parkingcontrol.model.entity.Parqueo;

public class EliminarDialog extends AppCompatDialogFragment {
    private Parqueo parqueo;
    private EliminarDialogListener listener;

    public void setParqueo(Parqueo parqueo) {
        this.parqueo = parqueo;
    }

    public void setListener(EliminarDialogListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.eliminar_dialog,null);
        //Agregamos el titulo al dialogo y agegamos las dos acciones
        builder.setView(view)
                .setTitle("Eliminar parqueo")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {   }
                })
                .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.eliminarParqueo(parqueo);
                    }
                });
        return builder.create();
    }

    //Esta interface la usamos para pasar el valor al metodo de eliminar en el fragmento padre
    public interface EliminarDialogListener {
        void eliminarParqueo(Parqueo parqueo);
    }
}
