package com.example.inmobiliariamiguel.ui.inquilinos;

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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.inmobiliariamiguel.R;
import com.example.inmobiliariamiguel.modelo.Inmueble;
import com.example.inmobiliariamiguel.ui.contratos.AdapterInmuebleAlquilado;

import java.util.List;

public class AdapterInquilino  extends RecyclerView.Adapter<AdapterInquilino.ViewHolder>{

    private Context context;

    private List<Inmueble> inmuebles;

    private LayoutInflater inflater;

        public AdapterInquilino(Context context, List<Inmueble> inmuebles, LayoutInflater inflater) {
        this.context = context;
        this.inmuebles = inmuebles;
        this.inflater = inflater;
        }
    @NonNull
    @Override
    public AdapterInquilino.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_inmueble_inquilino, parent, false);
        return new AdapterInquilino.ViewHolder(view);
        }



    @Override
    public void onBindViewHolder(@NonNull AdapterInquilino.ViewHolder holder, int position) {

            holder.tvDireccion.setText("Direccion: " +inmuebles.get(position).getDireccion());

        Glide.with(context)
                .load(inmuebles.get(position).getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivImagenInmueble);
        }

@Override
public int getItemCount() {
        return inmuebles.size();
        }

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView tvDireccion;
    ImageView ivImagenInmueble;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        ivImagenInmueble = itemView.findViewById(R.id.ivImagenInmuebleInquilino);

        tvDireccion = itemView.findViewById(R.id.tvDireccionInquilino);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                Inmueble inmueble = inmuebles.get(getAdapterPosition());
                bundle.putSerializable("inquilino", inmueble);
                Navigation.findNavController((Activity) context, R.id.nav_host_fragment_content_main).navigate(R.id.detalleInquilinoFragment, bundle);
            }
        });

    }
}
}
