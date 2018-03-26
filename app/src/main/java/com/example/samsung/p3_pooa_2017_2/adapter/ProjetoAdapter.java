package com.example.samsung.p3_pooa_2017_2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.samsung.p3_pooa_2017_2.R;
import com.example.samsung.p3_pooa_2017_2.model.Projeto;

import java.util.List;

/**
 * Created by Samsung on 26/03/2018.
 */

public class ProjetoAdapter extends RecyclerView.Adapter {

    private List<Projeto> projetos;
    private Context context;
    private static ClickRecyclerViewListener clickRecyclerViewListener;

    public ProjetoAdapter(List<Projeto> projetos, Context context, ClickRecyclerViewListener clickRecyclerViewListener) {
        this.projetos = projetos;
        this.context = context;
        this.clickRecyclerViewListener = clickRecyclerViewListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_projeto, parent, false);
        ProjetoViewHolder projetoViewHolder = new ProjetoViewHolder(view);
        return projetoViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ProjetoViewHolder holder = (ProjetoViewHolder) viewHolder;

        Projeto projeto  = projetos.get(position) ;

        holder.codigo.setText(projeto.getCodigo());
        holder.dataInicial.setText(projeto.getDataInicial());
        holder.dataPrevista.setText(projeto.getDataPrevista());
        holder.engenheiro.setText(projeto.getEngenheiro());
        holder.descricao.setText(projeto.getDescricao());
    }

    @Override
    public int getItemCount() {
        return projetos.size();
    }

    private void updateItem(int position) {
    }

    private void removerItem(int position) {
    }

    public class ProjetoViewHolder extends RecyclerView.ViewHolder {

        private final TextView codigo;
        private final TextView dataInicial;
        private final TextView dataPrevista;
        private final TextView engenheiro;
        private final TextView descricao;

        public ProjetoViewHolder(View itemView) {
            super(itemView);
            codigo = (TextView) itemView.findViewById(R.id.codigo);
            dataInicial = (TextView) itemView.findViewById(R.id.dataInicial);
            dataPrevista = (TextView) itemView.findViewById(R.id.dataPrevista);
            engenheiro = (TextView) itemView.findViewById(R.id.engenheiro);
            descricao = (TextView) itemView.findViewById(R.id.descricao);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickRecyclerViewListener.onClick(projetos.get(getLayoutPosition()));
                }
            });
        }
    }
}