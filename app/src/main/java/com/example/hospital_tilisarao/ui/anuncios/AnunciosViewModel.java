package com.example.hospital_tilisarao.ui.anuncios;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AnunciosViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AnunciosViewModel() {
        mText = new MutableLiveData<>();

    }

    public LiveData<String> getText() {
        return mText;
    }
}