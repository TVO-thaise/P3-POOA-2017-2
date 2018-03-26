package com.example.samsung.p3_pooa_2017_2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.samsung.p3_pooa_2017_2.R;
import com.example.samsung.p3_pooa_2017_2.model.Engenheiro;

import java.util.List;

/**
 * Created by Samsung on 26/03/2018.
 */

public class EngenheiroAdapter extends RecyclerView.Adapter {

    private List<Engenheiro> engenheiros;
    private Context context;
    private static ClickRecyclerViewListener clickRecyclerViewListener;

    public EngenheiroAdapter(List<Engenheiro> engenheiros, Context context, ClickRecyclerViewListener clickRecyclerViewListener) {
        this.engenheiros = engenheiros;
        this.context = context;
        this.clickRecyclerViewListener = clickRecyclerViewListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_engenheiro, parent, false);
        EngenheiroViewHolder engenheiroViewHolder = new EngenheiroViewHolder(view);
        return engenheiroViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        EngenheiroViewHolder holder = (EngenheiroViewHolder) viewHolder;

        Engenheiro engenheiro  = engenheiros.get(position) ;

        holder.nome.setText(engenheiro.getNome());
        holder.cnpj.setText(engenheiro.getCnpj());
        holder.projeto.setText(engenheiro.getProjeto());
        holder.tel_fixo.setText(engenheiro.getTel_fixo());
        holder.celular.setText(engenheiro.getCelular());
        holder.endereco.setText(engenheiro.getEndereco());
        holder.email.setText(engenheiro.getEmail());
        holder.identificacao.setText(engenheiro.getIdentificacao());
    }

    @Override
    public int getItemCount() {
        return engenheiros.size();
    }

    private void updateItem(int position) {
    }

    private void removerItem(int position) {
    }

    public class EngenheiroViewHolder extends RecyclerView.ViewHolder {

        private final TextView nome;
        private final TextView cnpj;
        private final TextView projeto;
        private final TextView tel_fixo;
        private final TextView celular;
        private final TextView endereco;
        private final TextView email;
        private final TextView identificacao;

        public EngenheiroViewHolder(View itemView) {
            super(itemView);
            nome = (TextView) itemView.findViewById(R.id.nome);
            cnpj = (TextView) itemView.findViewById(R.id.cnpj);
            projeto = (TextView) itemView.findViewById(R.id.projeto);
            tel_fixo = (TextView) itemView.findViewById(R.id.tel_fixo);
            celular = (TextView) itemView.findViewById(R.id.celular);
            endereco = (TextView) itemView.findViewById(R.id.endereco);
            email = (TextView) itemView.findViewById(R.id.email);
            identificacao = (TextView) itemView.findViewById(R.id.identificacao);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickRecyclerViewListener.onClick(engenheiros.get(getLayoutPosition()));
                }
            });
        }
    }
}