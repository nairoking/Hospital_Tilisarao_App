package com.example.hospital_tilisarao.ui.turnos;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.hospital_tilisarao.R;

public class NuevoTurnoFragment extends Fragment {

    private NuevoTurnoViewModel mViewModel;
    private RecyclerView rvTurnos;
    private Button btBuscar;

    String[] items = {"Fonoaudiologia","Psicologia","Pediatria","Odontologia","Kinesiologia","Nutricionista","Ginecologia",
    "Obstetra","Rayos","Laboratorio","Ecografia"};

    String[] t = {"08:00hs", "09:00hs", "10:00hs", "11:00hs", "12:00hs"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems, turnos;


    public static NuevoTurnoFragment newInstance() {
        return new NuevoTurnoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_nuevo_turno, container, false);

       inicializarVista(root);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NuevoTurnoViewModel.class);
        // TODO: Use the ViewModel
    }

    public void inicializarVista(View root ){

        autoCompleteTextView = root.findViewById(R.id.auto_complete_txt);
        rvTurnos = root.findViewById(R.id.rvTurnosDisponibles);
        btBuscar = root.findViewById(R.id.btBuscar);

        adapterItems = new ArrayAdapter<String>(getContext(), R.layout.list_item,items);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getContext().getApplicationContext(), "Item: "+item, Toast.LENGTH_SHORT).show();

            }
        });

        turnos = new ArrayAdapter<String>(getContext(),R.layout.item_turno_disponible,t);


    }

}