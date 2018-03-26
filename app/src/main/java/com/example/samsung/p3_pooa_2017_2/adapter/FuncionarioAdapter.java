package com.example.samsung.p3_pooa_2017_2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.samsung.p3_pooa_2017_2.R;
import com.example.samsung.p3_pooa_2017_2.model.Funcionario;

import java.util.List;

/**
 * Created by Samsung on 26/03/2018.
 */


public class FuncionarioAdapter extends RecyclerView.Adapter {

    private List<Funcionario> funcionarios;
    private Context context;
    private static ClickRecyclerViewListener clickRecyclerViewListener;

    public FuncionarioAdapter(List<Funcionario> funcionarios, Context context, ClickRecyclerViewListener clickRecyclerViewListener) {
        this.funcionarios = funcionarios;
        this.context = context;
        this.clickRecyclerViewListener = clickRecyclerViewListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_funcionario, parent, false);
       FuncionarioViewHolder funcionarioViewHolder = new FuncionarioViewHolder(view);
        return funcionarioViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        FuncionarioViewHolder holder = (FuncionarioViewHolder) viewHolder;

        Funcionario funcionario = funcionarios.get(position);

        holder.nome.setText(funcionario.getNome());
        holder.cpf.setText(funcionario.getCpf());
        holder.projeto.setText(funcionario.getProjeto());
        holder.tel_fixo.setText(funcionario.getTel_fixo());
        holder.celular.setText(funcionario.getCelular());
        holder.endereco.setText(funcionario.getEndereco());
        holder.cargo.setText(funcionario.getCargo());
        holder.identificacao.setText(funcionario.getIdentificacao());
    }

    @Override
    public int getItemCount() {
        return funcionarios.size();
    }

    private void updateItem(int position) {
    }

    private void removerItem(int position) {
    }

    public class FuncionarioViewHolder extends RecyclerView.ViewHolder {

        private final TextView nome;
        private final TextView cpf;
        private final TextView projeto;
        private final TextView tel_fixo;
        private final TextView celular;
        private final TextView endereco;
        private final TextView cargo;
        private final TextView identificacao;

        public FuncionarioViewHolder(View itemView) {
            super(itemView);
            nome = (TextView) itemView.findViewById(R.id.nome);
            cpf = (TextView) itemView.findViewById(R.id.cpf);
            projeto = (TextView) itemView.findViewById(R.id.projeto);
            tel_fixo = (TextView) itemView.findViewById(R.id.tel_fixo);
            celular = (TextView) itemView.findViewById(R.id.celular);
            endereco = (TextView) itemView.findViewById(R.id.endereco);
            cargo = (TextView) itemView.findViewById(R.id.cargo);
            identificacao = (TextView) itemView.findViewById(R.id.identificacao);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickRecyclerViewListener.onClick(funcionarios.get(getLayoutPosition()));
                }
            });
        }
    }
}