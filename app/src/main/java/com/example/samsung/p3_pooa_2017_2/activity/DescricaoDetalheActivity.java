package com.example.samsung.p3_pooa_2017_2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.samsung.p3_pooa_2017_2.R;
import com.example.samsung.p3_pooa_2017_2.model.Descricao;

import io.realm.Realm;

/**
 * Created by Samsung on 26/03/2018.
 */

public class DescricaoDetalheActivity extends AppCompatActivity {
    EditText codigo, complemento, quartos, andares, garagem, churrasqueira, valor, banheiros, outros;
    Button btsalvar, btalterar, btdeletar;

    int id;
    Descricao descricao;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_detalhe);

        codigo = (EditText) findViewById(R.id.ed_codigo);
        complemento = (EditText) findViewById(R.id.ed_complemento);
        quartos = (EditText) findViewById(R.id.ed_quartos);
        andares = (EditText) findViewById(R.id.ed_andares);
        garagem = (EditText) findViewById(R.id.ed_garagem);
        churrasqueira = (EditText) findViewById(R.id.ed_churrasqueira);
        valor = (EditText) findViewById(R.id.ed_valor);
        banheiros = (EditText) findViewById(R.id.ed_banheiros);
        outros = (EditText) findViewById(R.id.ed_outros);

        btsalvar = (Button) findViewById(R.id.bt_salvar_descricao);
        btalterar = (Button) findViewById(R.id.bt_alterar_descricao);
        btdeletar = (Button) findViewById(R.id.bt_deletar_descricao);

        Intent intent = getIntent();
        id = (int) intent.getSerializableExtra("id");
        realm = Realm.getDefaultInstance();

        if (id != 0) {
            btsalvar.setEnabled(false);
            btsalvar.setClickable(false);
            btsalvar.setVisibility(View.INVISIBLE);

            descricao = realm.where(Descricao.class).equalTo("id", id).findFirst();

            codigo.setText(descricao.getCodigo());
            complemento.setText(descricao.getComplemento());
            quartos.setText(descricao.getQuartos());
            andares.setText(descricao.getAndares());
            garagem.setText(descricao.getGaragem());
            churrasqueira.setText(descricao.getChurrasqueira());
            valor.setText(descricao.getValor());
            banheiros.setText(descricao.getBanheiros());
            outros.setText(descricao.getOutros());
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
        descricao.deleteFromRealm();
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this, "Descricao deletado", Toast.LENGTH_LONG).show();
        this.finish();
    }

    public void salvar() {
        int proximoID = 1;
        if (realm.where(Descricao.class).max("id") != null)
            proximoID = realm.where(Descricao.class).max("id").intValue() + 1;

        realm.beginTransaction();
        Descricao descricao = new Descricao();
        descricao.setId(proximoID);
        descricao.setCodigo(codigo.getText().toString());
        descricao.setComplemento(complemento.getText().toString());
        descricao.setQuartos(quartos.getText().toString());
        descricao.setAndares(andares.getText().toString());
        descricao.setGaragem(garagem.getText().toString());
        descricao.setChurrasqueira(churrasqueira.getText().toString());
        descricao.setValor(valor.getText().toString());
        descricao.setBanheiros(banheiros.getText().toString());

        realm.copyToRealm(descricao);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this, "Descricao Cadastrada", Toast.LENGTH_LONG).show();
        this.finish();
    }

    public void alterar() {
        realm.beginTransaction();

        descricao.setCodigo(codigo.getText().toString());
        descricao.setComplemento(complemento.getText().toString());
        descricao.setQuartos(quartos.getText().toString());
        descricao.setAndares(andares.getText().toString());
        descricao.setGaragem(garagem.getText().toString());
        descricao.setChurrasqueira(churrasqueira.getText().toString());
        descricao.setValor(valor.getText().toString());
        descricao.setBanheiros(banheiros.getText().toString());
        descricao.setOutros(outros.getText().toString());

        realm.copyToRealm(descricao);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this, "Descricao Alterada", Toast.LENGTH_LONG).show();
        this.finish();
    }
}
