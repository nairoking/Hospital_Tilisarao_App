package com.example.inmobiliariamiguel.ui.logout;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.inmobiliariamiguel.R;

public class LogoutFragment extends Fragment {

    private LogoutViewModel mViewModel;


    public static LogoutFragment newInstance() {
        return new LogoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


       View root = inflater.inflate(R.layout.logout_fragment, container, false);
       /*mViewModel.getMutableLiveData().observe(getViewLifecycleOwner(), new Observer<AlertDialog.Builder>() {
           @Override
           public void onChanged(AlertDialog.Builder builder) {
               builder.show();
           }
       });
       mViewModel.mostrarDialog();*/
        mostrarDialog();

        return root;
    }

    public void mostrarDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this.getContext())
                .setTitle("Titulo")
                .setMessage("Desea cerrar la app")
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences sp = getContext().getSharedPreferences("datos",0);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("token", "Bearer " );
                        editor.commit();
                        System.exit(0);

                    }
                }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main).navigate(R.id.nav_inicio);

                    }
                });
        alert.show();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LogoutViewModel.class);
        // TODO: Use the ViewModel
    }

}