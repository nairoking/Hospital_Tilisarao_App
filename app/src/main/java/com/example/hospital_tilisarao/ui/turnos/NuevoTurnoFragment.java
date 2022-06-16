package com.example.hospital_tilisarao.ui.turnos;

import static com.example.hospital_tilisarao.R.drawable.bt_white;
import static com.example.hospital_tilisarao.R.drawable.btn_style;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.model.ByteArrayLoader;
import com.example.hospital_tilisarao.Modelo.Medico;
import com.example.hospital_tilisarao.Modelo.Turno;
import com.example.hospital_tilisarao.Modelo.TurnoView;
import com.example.hospital_tilisarao.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NuevoTurnoFragment extends Fragment {

    private NuevoTurnoViewModel mViewModel;
    private RecyclerView rvTurnos;
    private Button btBuscar;
    private TextView t1,t2,t3,t4,t5,t6,hoy;
    private LinearLayout lyTurnos;
    private CalendarView calendario;

    private LocalDate fecha;
    ArrayList<Medico> medicoArrayList;
    private Turno otroTurno = new Turno();
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;



    public static NuevoTurnoFragment newInstance() {
        return new NuevoTurnoFragment();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_nuevo_turno, container, false);

        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(NuevoTurnoViewModel.class);

        fecha = LocalDate.now();

       inicializarVista(root);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }

    public void inicializarVista(View root ){

        autoCompleteTextView = root.findViewById(R.id.auto_complete_txt);
        calendario = root.findViewById(R.id.calendarView);
        t1 = root.findViewById(R.id.etTurno1);
        t2 = root.findViewById(R.id.etTurno2);
        t3 = root.findViewById(R.id.etTurno3);
        t4 = root.findViewById(R.id.etTurno4);
        t5 = root.findViewById(R.id.etTurno5);
        t6 = root.findViewById(R.id.etTurno6);
        hoy = root.findViewById(R.id.tvHoy);
        btBuscar = root.findViewById(R.id.btBuscar);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateandTime = simpleDateFormat.format(new Date());
        hoy.setText(currentDateandTime);



        mViewModel.getItems().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> items) {
                adapterItems = new ArrayAdapter<String>(getContext(),R.layout.list_item,items);
                autoCompleteTextView.setAdapter(adapterItems);
                autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String item = adapterView.getItemAtPosition(i).toString();
                        Toast.makeText(getContext().getApplicationContext(), "Item: "+item + " id: ", Toast.LENGTH_SHORT).show();

                        otroTurno.setMedicoId(medicoArrayList.get(i).getId());
                    }
                });
            }
        });
        mViewModel.getMedicos().observe(getViewLifecycleOwner(), new Observer<List<Medico>>() {
            @Override
            public void onChanged(List<Medico> medicos) {
                medicoArrayList = (ArrayList<Medico>) medicos;
            }
        });


        mViewModel.getTurnos().observe(getViewLifecycleOwner(), new Observer<List<Turno>>() {
            @Override
            public void onChanged(List<Turno> turnos) {
                t1.setVisibility(View.VISIBLE);
                t2.setVisibility(View.VISIBLE);
                t3.setVisibility(View.VISIBLE);
                t4.setVisibility(View.VISIBLE);
                t5.setVisibility(View.VISIBLE);
                t6.setVisibility(View.VISIBLE);
                for(int i = 0; i < turnos.size(); i ++)
                {
                    switch (turnos.get(i).getHoraInicio()){
                        case "8:00hs":
                            t1.setEnabled(false);
                            break;
                        case "9:00hs":
                            t2.setEnabled(false);
                            break;
                        case "10:00hs":
                            t3.setEnabled(false);
                            break;
                        case "11:00hs":
                            t4.setEnabled(false);
                            break;
                        case "12:00hs":
                            t5.setEnabled(false);
                            break;
                        case "13:00hs":
                            t6.setEnabled(false);
                            break;
                    }
                }
            }
        });

        calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {

                //hoy.setText(i +"-"+i1+"-"+i2);
                fecha = LocalDate.of(i,i1 + 1,i2);
                hoy.setText(fecha.toString());
                Log.d("salida", fecha.toString() + "");
            }
        });

        btBuscar.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {



                    hoy.setText(fecha.toString());
                    otroTurno.setFecha(fecha.toString());


                mViewModel.cargarTurnos(otroTurno);



                //t6.setBackgroundColor(btn_style);
            }
        });


        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), " " + t1.getText(), Toast.LENGTH_SHORT).show();
                    otroTurno.setHoraInicio(t1.getText().toString());
                    mViewModel.nuevoTurno(otroTurno);

            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), " " + t2.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), " " + t3.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), " " + t4.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), " " + t5.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        t6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), " " + t6.getText(), Toast.LENGTH_SHORT).show();
            }
        });


        mViewModel.cargarMedico();


    }
}

