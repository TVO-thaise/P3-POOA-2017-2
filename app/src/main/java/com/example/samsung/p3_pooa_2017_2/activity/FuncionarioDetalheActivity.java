package com.example.samsung.p3_pooa_2017_2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.samsung.p3_pooa_2017_2.R;
import com.example.samsung.p3_pooa_2017_2.model.Funcionario;

import io.realm.Realm;

/**
 * Created by Samsung on 26/03/2018.
 */

public class FuncionarioDetalheActivity extends AppCompatActivity {
    EditText nome, cpf, projeto, tel_fixo, celular, endereco, cargo, identificacao;
    Button btsalvar, btalterar, btdeletar;

    int id;
    Funcionario funcionario;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funcionario_detalhe);

        nome = (EditText) findViewById(R.id.ed_nome);
        cpf = (EditText) findViewById(R.id.ed_cpf);
        projeto = (EditText) findViewById(R.id.ed_projeto);
        tel_fixo = (EditText) findViewById(R.id.ed_tel_fixo);
        celular = (EditText) findViewById(R.id.ed_celular);
        endereco = (EditText) findViewById(R.id.ed_endereco);
        cargo = (EditText) findViewById(R.id.ed_cargo);
        identificacao = (EditText) findViewById(R.id.ed_identificacao);

        btsalvar = (Button) findViewById(R.id.bt_salvar_funcionario);
        btalterar = (Button) findViewById(R.id.bt_alterar_funcionario);
        btdeletar = (Button) findViewById(R.id.bt_deletar_funcionario);

        Intent intent = getIntent();
        id = (int) intent.getSerializableExtra("id");
        realm = Realm.getDefaultInstance();

        if (id != 0) {
            btsalvar.setEnabled(false);
            btsalvar.setClickable(false);
            btsalvar.setVisibility(View.INVISIBLE);

            funcionario = realm.where(Funcionario.class).equalTo("id", id).findFirst();

            nome.setText(funcionario.getNome());
            cpf.setText(funcionario.getCpf());
            projeto.setText(funcionario.getProjeto());
            tel_fixo.setText(funcionario.getTel_fixo());
            celular.setText(funcionario.getCelular());
            endereco.setText(funcionario.getEndereco());
            cargo.setText(funcionario.getCargo());
            identificacao.setText(funcionario.getIdentificacao());
        } else {
            btalterar.setEnabled(false);
            btalterar.setClickable(false);
            btalterar.setVisibility(View.INVISIBLE);
            btdeletar.setEnabled(false);
            btdeletar.setClickable(false);
            btdeletar.setVisibility(View.INVISIBLE);
        }

        btsalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

        btalterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alterar();
            }
        });

        btdeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletar();
            }
        });
    }

    public void deletar() {
        realm.beginTransaction();
        funcionario.deleteFromRealm();
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this, "Funcionario deletado", Toast.LENGTH_LONG).show();
        this.finish();
    }

    public void salvar() {
        int proximoID = 1;
        if (realm.where(Funcionario.class).max("id") != null)
            proximoID = realm.where(Funcionario.class).max("id").intValue() + 1;

        realm.beginTransaction();
        Funcionario funcionario = new Funcionario();
        funcionario.setId(proximoID);
        funcionario.setNome(nome.getText().toString());
        funcionario.setCpf(cpf.getText().toString());
        funcionario.setProjeto(projeto.getText().toString());
        funcionario.setTel_fixo(tel_fixo.getText().toString());
        funcionario.setCelular(celular.getText().toString());
        funcionario.setEndereco(endereco.getText().toString());
        funcionario.setCargo(cargo.getText().toString());
        funcionario.setIdentificacao(identificacao.getText().toString());

        realm.copyToRealm(funcionario);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this, "Funcionario Cadastrado", Toast.LENGTH_LONG).show();
        this.finish();
    }

    public void alterar() {
        realm.beginTransaction();

        funcionario.setNome(nome.getText().toString());
        funcionario.setCpf(cpf.getText().toString());
        funcionario.setProjeto(projeto.getText().toString());
        funcionario.setTel_fixo(tel_fixo.getText().toString());
        funcionario.setCelular(celular.getText().toString());
        funcionario.setEndereco(endereco.getText().toString());
        funcionario.setCargo(cargo.getText().toString());
        funcionario.setIdentificacao(identificacao.getText().toString());

        realm.copyToRealm(funcionario);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this, "Funcionario Alterado", Toast.LENGTH_LONG).show();
        this.finish();
    }
}