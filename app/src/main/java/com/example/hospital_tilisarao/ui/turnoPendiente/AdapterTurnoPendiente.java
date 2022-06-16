package com.example.hospital_tilisarao.ui.turnoPendiente;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospital_tilisarao.Modelo.Turno;
import com.example.hospital_tilisarao.R;

import java.util.List;

public class AdapterTurnoPendiente extends RecyclerView.Adapter<AdapterTurnoPendiente.ViewHolder> {

    private Context context;
    private List<Turno> turnos;
    private LayoutInflater inflater;

    public AdapterTurnoPendiente(Context context, List<Turno> turno, LayoutInflater inflater) {
        this.context = context;
        this.turnos = turno;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public AdapterTurnoPendiente.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_turno_pendiente, parent, false);
        return new AdapterTurnoPendiente.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvCodigoTurno.setText(turnos.get(position).getId() + "");
        holder.tvDia.setText(turnos.get(position).getFecha() + "");
        holder.tvHoraro.setText(turnos.get(position).getHoraInicio() + "");
        holder.tvMedico.setText(turnos.get(position).getMedico().getCompleto() + "-" +turnos.get(position).getMedico().especialidad );
    }

    @Override
    public int getItemCount() {
        return turnos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvCodigoTurno , tvDia, tvHoraro, tvMedico;
        Button btCancelarTurno;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCodigoTurno = itemView.findViewById(R.id.tvCodigoTurno);
            tvDia = itemView.findViewById(R.id.tvDia);
            tvHoraro = itemView.findViewById(R.id.tvHorario);
            tvMedico = itemView.findViewById(R.id.tvMedico);
            btCancelarTurno = itemView.findViewById(R.id.btCancelarTurno);

            btCancelarTurno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PendienteViewModel pv;
                    pv = ViewModelProvider.AndroidViewModelFactory.getInstance((Application) context.getApplicationContext()).create(PendienteViewModel.class);
                    Turno t = turnos.get(getAdapterPosition());
                    pv.cancelarTurnos(t.getId());
                    Navigation.findNavController((Activity) context, R.id.nav_host_fragment_content_menu_main).navigate(R.id.nav_pendiente);
                }
            });


        }
    }
}


