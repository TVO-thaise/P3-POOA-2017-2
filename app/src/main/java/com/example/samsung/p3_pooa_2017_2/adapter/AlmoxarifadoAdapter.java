package com.example.samsung.p3_pooa_2017_2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.samsung.p3_pooa_2017_2.R;
import com.example.samsung.p3_pooa_2017_2.model.Almoxarifado;

import java.util.List;

/**
 * Created by Samsung on 26/03/2018.
 */

public class AlmoxarifadoAdapter extends RecyclerView.Adapter {

    private List<Almoxarifado> almoxarifados;
    private Context context;
    private static ClickRecyclerViewListener clickRecyclerViewListener;

    public AlmoxarifadoAdapter(List<Almoxarifado> almoxarifados, Context context, ClickRecyclerViewListener clickRecyclerViewListener) {
        this.almoxarifados = almoxarifados;
        this.context = context;
        this.clickRecyclerViewListener = clickRecyclerViewListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_almoxarifado, parent, false);
        AlmoxarifadoAdapter.AlmoxarifadoViewHolder almoxarifadoViewHolder = new AlmoxarifadoAdapter.AlmoxarifadoViewHolder(view);
        return almoxarifadoViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        AlmoxarifadoAdapter.AlmoxarifadoViewHolder holder = (AlmoxarifadoAdapter.AlmoxarifadoViewHolder) viewHolder;

        Almoxarifado almoxarifado  = almoxarifados.get(position) ;

        holder.ferramenta.setText(almoxarifado.getFerramenta());
        holder.horaRetirada.setText(almoxarifado.getHoraRetirada());
        holder.horaEntrega.setText(almoxarifado.getHoraEntrega());
        holder.funcionario.setText(almoxarifado.getFuncionario());
    }

    @Override
    public int getItemCount() {
        return almoxarifados.size();
    }

    private void updateItem(int position) {
    }

    private void removerItem(int position) {
    }

    public class AlmoxarifadoViewHolder extends RecyclerView.ViewHolder {

        private final TextView ferramenta;
        private final TextView horaRetirada;
        private final TextView horaEntrega;
        private final TextView funcionario;

        public AlmoxarifadoViewHolder(View itemView) {
            super(itemView);
            ferramenta = (TextView) itemView.findViewById(R.id.ferramenta);
            horaRetirada = (TextView) itemView.findViewById(R.id.horaRetirada);
            horaEntrega = (TextView) itemView.findViewById(R.id.horaEntrega);
            funcionario = (TextView) itemView.findViewById(R.id.funcionario);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickRecyclerViewListener.onClick(almoxarifados.get(getLayoutPosition()));
                }
            });
        }
    }
}