package com.example.hospital_tilisarao.ui.historial;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospital_tilisarao.Modelo.HistorialMedico;
import com.example.hospital_tilisarao.R;

import java.util.List;

public class HistorialAdapter extends RecyclerView.Adapter<HistorialAdapter.ViewHolder> {
    private Context context;
    private List<HistorialMedico> historial;
    private LayoutInflater inflater;

    public HistorialAdapter(Context context, List<HistorialMedico> histo, LayoutInflater inflater) {
        this.context = context;
        this.historial = histo;
        this.inflater = inflater;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_historial_fragment,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvFechaHistorial.setText(historial.get(position).getFecha() + "");
        holder.tvMedicoHistorial.setText(historial.get(position).getMedico().getItem() + "");
    }

    @Override
    public int getItemCount() {
        return historial.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMedicoHistorial;
        TextView tvFechaHistorial;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMedicoHistorial = itemView.findViewById(R.id.tvMedicoHistorial);
            tvFechaHistorial = itemView.findViewById(R.id.tvFechaHistorial);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    HistorialMedico historialMedico = historial.get(getAdapterPosition());
                    bundle.putSerializable("historial", historialMedico);
                    Navigation.findNavController((Activity) context, R.id.nav_host_fragment_content_menu_main).navigate(R.id.historialDetalleFragment, bundle);
                }
            });
        }
    }
}
