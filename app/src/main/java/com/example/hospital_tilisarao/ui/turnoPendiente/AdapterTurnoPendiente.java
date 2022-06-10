package com.example.hospital_tilisarao.ui.turnoPendiente;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
/*
        TextView tvCodigoPago , tvNumeroPago, tvCodigoContratoPagos, tvImporte, tvFechaPago;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCodigoContratoPagos = itemView.findViewById(R.id.tvCodigoContratoPagos);
            tvCodigoPago = itemView.findViewById(R.id.tvCodigoPago);
            tvNumeroPago = itemView.findViewById(R.id.tvNumeroPago);
            tvImporte = itemView.findViewById(R.id.tvImporte);
            tvFechaPago = itemView.findViewById(R.id.tvFechaPago);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    Contrato cont = contratoPago;
                    bundle.putSerializable("contrato", cont);
                    Navigation.findNavController((Activity) context, R.id.nav_host_fragment_content_main).navigate(R.id.pagoContratoFragment, bundle);
                }
            });
        }
    }*/
    }

}
