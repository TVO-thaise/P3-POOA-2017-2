package com.example.samsung.p3_pooa_2017_2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.samsung.p3_pooa_2017_2.R;
import com.example.samsung.p3_pooa_2017_2.model.Projeto;

import io.realm.Realm;

/**
 * Created by Samsung on 26/03/2018.
 */

public class ProjetoDetalheActivity extends AppCompatActivity {
    EditText codigo, dataInicial, dataPrevista, engenheiro, descricao;
    Button btsalvar,btalterar, btdeletar;

    int id;
    Projeto projeto;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projeto_detalhe);

        codigo = (EditText) findViewById(R.id.ed_codigo);
        dataInicial = (EditText) findViewById(R.id.ed_dataInicial);
        dataPrevista = (EditText) findViewById(R.id.ed_dataPrevista);
        engenheiro = (EditText) findViewById(R.id.ed_engenheiro);
        descricao = (EditText) findViewById(R.id.ed_descricao);

        btsalvar = (Button) findViewById(R.id.bt_salvar_projeto);
        btalterar = (Button) findViewById(R.id.bt_alterar_projeto);
        btdeletar = (Button) findViewById(R.id.bt_deletar_projeto);

        Intent intent    = getIntent();
        id = (int) intent.getSerializableExtra("id");
        realm = Realm.getDefaultInstance();

        if (id !=0) {
            btsalvar.setEnabled(false);
            btsalvar.setClickable(false);
            btsalvar.setVisibility(View.INVISIBLE);

            projeto = realm.where(Projeto.class).equalTo("id",id).findFirst();

            codigo.setText(projeto.getCodigo());
            dataInicial.setText(projeto.getDataInicial());
            dataPrevista.setText(projeto.getDataPrevista());
            engenheiro.setText(projeto.getEngenheiro());
            descricao.setText(projeto.getDescricao());

        }else{
            btalterar.setEnabled(false);
            btalterar.setClickable(false);
            btalterar.setVisibility(View.INVISIBLE);
            btdeletar.setEnabled(false);
            btdeletar.setClickable(false);
            btdeletar.setVisibility(View.INVISIBLE);
        }

        btsalvar.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

        btalterar.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                alterar();
            }
        });

        btdeletar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                deletar();
            }
        });
    }

    public void deletar(){
        realm.beginTransaction();
        projeto.deleteFromRealm();
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Projeto deletado",Toast.LENGTH_LONG).show();
        this.finish();
    }

    public void salvar() {
        int proximoID = 1;
        if(realm.where(Projeto.class).max("id") !=null)
            proximoID = realm.where(Projeto.class).max("id").intValue()+1;

        realm.beginTransaction();
        Projeto projeto = new Projeto();
        projeto.setId(proximoID);
        projeto.setCodigo(codigo.getText().toString());
        projeto.setDataInicial(dataInicial.getText().toString());
        projeto.setDataPrevista(dataPrevista.getText().toString());
        projeto.setEngenheiro(engenheiro.getText().toString());
        projeto.setDescricao(descricao.getText().toString());

        realm.copyToRealm(projeto);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Projeto Cadastrado",Toast.LENGTH_LONG).show();
        this.finish();
    }

    public void alterar() {
        realm.beginTransaction();

        projeto.setCodigo(codigo.getText().toString());
        projeto.setDataInicial(dataInicial.getText().toString());
        projeto.setDataPrevista(dataPrevista.getText().toString());
        projeto.setEngenheiro(engenheiro.getText().toString());
        projeto.setDescricao(descricao.getText().toString());

        realm.copyToRealm(projeto);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Projeto Alterado",Toast.LENGTH_LONG).show();
        this.finish();
    }
}