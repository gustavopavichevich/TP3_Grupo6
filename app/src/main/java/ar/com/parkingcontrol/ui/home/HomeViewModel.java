package ar.com.parkingcontrol.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.databinding.ObservableArrayList;
import ar.com.parkingcontrol.Entidades.Parqueo;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<ObservableArrayList<Parqueo>> parqueos;

    public HomeViewModel() {
        parqueos = new MutableLiveData<ObservableArrayList<Parqueo>>();
    }

    public LiveData<ObservableArrayList<Parqueo>> getParqueos() {
        return parqueos;
    }
}