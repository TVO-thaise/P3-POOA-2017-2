package com.example.samsung.p3_pooa_2017_2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.samsung.p3_pooa_2017_2.R;
import com.example.samsung.p3_pooa_2017_2.model.Descricao;

import java.util.List;

/**
 * Created by Samsung on 26/03/2018.
 */

public class DescricaoAdapter extends RecyclerView.Adapter {

    private List<Descricao> descricaos;
    private Context context;
    private static ClickRecyclerViewListener clickRecyclerViewListener;

    public DescricaoAdapter(List<Descricao> decricaos, Context context, ClickRecyclerViewListener clickRecyclerViewListener) {
        this.descricaos = descricaos;
        this.context = context;
        this.clickRecyclerViewListener = clickRecyclerViewListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_descricao, parent, false);
        DescricaoAdapter.DescricaoViewHolder descricaoViewHolder = new DescricaoAdapter.DescricaoViewHolder(view);
        return descricaoViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        DescricaoAdapter.DescricaoViewHolder holder = (DescricaoAdapter.DescricaoViewHolder) viewHolder;

        Descricao descricao  = descricaos.get(position) ;

        holder.codigo.setText(descricao.getCodigo());
        holder.complemento.setText(descricao.getComplemento());
        holder.quartos.setText(descricao.getQuartos());
        holder.andares.setText(descricao.getAndares());
        holder.garagem.setText(descricao.getGaragem());
        holder.churrasqueira.setText(descricao.getChurrasqueira());
        holder.valor.setText(descricao.getValor());
        holder.banheiros.setText(descricao.getBanheiros());
        holder.outros.setText(descricao.getOutros());
    }

    @Override
    public int getItemCount() {
        return descricaos.size();
    }

    private void updateItem(int position) {
    }

    private void removerItem(int position) {
    }

    public class DescricaoViewHolder extends RecyclerView.ViewHolder {

        private final TextView codigo;
        private final TextView complemento;
        private final TextView quartos;
        private final TextView andares;
        private final TextView garagem;
        private final TextView churrasqueira;
        private final TextView valor;
        private final TextView banheiros;
        private final TextView outros;


        public DescricaoViewHolder(View itemView) {
            super(itemView);
            codigo = (TextView) itemView.findViewById(R.id.codigo);
            complemento = (TextView) itemView.findViewById(R.id.complemento);
            quartos = (TextView) itemView.findViewById(R.id.quartos);
            andares = (TextView) itemView.findViewById(R.id.andares);
            garagem = (TextView) itemView.findViewById(R.id.garagem);
            churrasqueira = (TextView) itemView.findViewById(R.id.churrasqueira);
            valor = (TextView) itemView.findViewById(R.id.valor);
            banheiros = (TextView) itemView.findViewById(R.id.banheiros);
            outros = (TextView) itemView.findViewById(R.id.outros);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickRecyclerViewListener.onClick(descricaos.get(getLayoutPosition()));
                }
            });
        }
    }
}