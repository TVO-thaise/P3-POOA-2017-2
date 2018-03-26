package com.example.samsung.p3_pooa_2017_2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.samsung.p3_pooa_2017_2.R;
import com.example.samsung.p3_pooa_2017_2.model.Engenheiro;

import io.realm.Realm;

/**
 * Created by Samsung on 26/03/2018.
 */

public class EngenheiroDetalheActivity extends AppCompatActivity {
    EditText nome, cnpj, projeto, tel_fixo, celular, endereco, email, identificacao;
    Button btsalvar, btalterar, btdeletar;

    int id;
    Engenheiro engenheiro;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engenheiro_detalhe);

        nome = (EditText) findViewById(R.id.ed_nome);
        cnpj = (EditText) findViewById(R.id.ed_cnpj);
        projeto = (EditText) findViewById(R.id.ed_projeto);
        tel_fixo = (EditText) findViewById(R.id.ed_tel_fixo);
        celular = (EditText) findViewById(R.id.ed_celular);
        endereco = (EditText) findViewById(R.id.ed_endereco);
        email = (EditText) findViewById(R.id.ed_email);
        identificacao = (EditText) findViewById(R.id.ed_identificacao);

        btsalvar = (Button) findViewById(R.id.bt_salvar_engenheiro);
        btalterar = (Button) findViewById(R.id.bt_alterar_engenheiro);
        btdeletar = (Button) findViewById(R.id.bt_deletar_engenheiro);

        Intent intent = getIntent();
        id = (int) intent.getSerializableExtra("id");
        realm = Realm.getDefaultInstance();

        if (id != 0) {
            btsalvar.setEnabled(false);
            btsalvar.setClickable(false);
            btsalvar.setVisibility(View.INVISIBLE);

            engenheiro = realm.where(Engenheiro.class).equalTo("id", id).findFirst();

            nome.setText(engenheiro.getNome());
            cnpj.setText(engenheiro.getCnpj());
            projeto.setText(engenheiro.getProjeto());
            tel_fixo.setText(engenheiro.getTel_fixo());
            celular.setText(engenheiro.getCelular());
            endereco.setText(engenheiro.getEndereco());
            email.setText(engenheiro.getEmail());
            identificacao.setText(engenheiro.getIdentificacao());
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
        engenheiro.deleteFromRealm();
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this, "Engenheiro deletado", Toast.LENGTH_LONG).show();
        this.finish();
    }

    public void salvar() {
        int proximoID = 1;
        if (realm.where(Engenheiro.class).max("id") != null)
            proximoID = realm.where(Engenheiro.class).max("id").intValue() + 1;

        realm.beginTransaction();
        Engenheiro engenheiro = new Engenheiro();
        engenheiro.setId(proximoID);
        engenheiro.setNome(nome.getText().toString());
        engenheiro.setCnpj(cnpj.getText().toString());
        engenheiro.setProjeto(projeto.getText().toString());
        engenheiro.setTel_fixo(tel_fixo.getText().toString());
        engenheiro.setCelular(celular.getText().toString());
        engenheiro.setEndereco(endereco.getText().toString());
        engenheiro.setEmail(email.getText().toString());
        engenheiro.setIdentificacao(identificacao.getText().toString());

        realm.copyToRealm(engenheiro);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this, "Engenheiro Cadastrado", Toast.LENGTH_LONG).show();
        this.finish();
    }

    public void alterar() {
        realm.beginTransaction();

        engenheiro.setNome(nome.getText().toString());
        engenheiro.setCnpj(cnpj.getText().toString());
        engenheiro.setProjeto(projeto.getText().toString());
        engenheiro.setTel_fixo(tel_fixo.getText().toString());
        engenheiro.setCelular(celular.getText().toString());
        engenheiro.setEndereco(endereco.getText().toString());
        engenheiro.setEmail(email.getText().toString());
        engenheiro.setIdentificacao(identificacao.getText().toString());

        realm.copyToRealm(engenheiro);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this, "Engenheiro Alterado", Toast.LENGTH_LONG).show();
        this.finish();
    }
}
