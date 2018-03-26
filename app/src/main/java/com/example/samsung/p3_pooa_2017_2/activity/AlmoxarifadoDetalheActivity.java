package com.example.samsung.p3_pooa_2017_2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.samsung.p3_pooa_2017_2.R;
import com.example.samsung.p3_pooa_2017_2.model.Almoxarifado;

import io.realm.Realm;

/**
 * Created by Samsung on 26/03/2018.
 */

public class AlmoxarifadoDetalheActivity extends AppCompatActivity {
    EditText ferramenta, horaRetirada, horaEntrega, funcionario;
    Button btsalvar, btalterar, btdeletar;

    int id;
    Almoxarifado almoxarifado;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_almoxarifado_detalhe);

        ferramenta = (EditText) findViewById(R.id.ed_ferramenta);
        horaRetirada = (EditText) findViewById(R.id.ed_horaRetirada);
        horaEntrega = (EditText) findViewById(R.id.ed_horaEntrega);
        funcionario = (EditText) findViewById(R.id.ed_funcionario);

        btsalvar = (Button) findViewById(R.id.bt_salvar_almoxarifado);
        btalterar = (Button) findViewById(R.id.bt_alterar_almoxarifado);
        btdeletar = (Button) findViewById(R.id.bt_deletar_almoxarifado);

        Intent intent = getIntent();
        id = (int) intent.getSerializableExtra("id");
        realm = Realm.getDefaultInstance();

        if (id != 0) {
            btsalvar.setEnabled(false);
            btsalvar.setClickable(false);
            btsalvar.setVisibility(View.INVISIBLE);

            almoxarifado = realm.where(Almoxarifado.class).equalTo("id", id).findFirst();

            ferramenta.setText(almoxarifado.getFerramenta());
            horaRetirada.setText(almoxarifado.getHoraRetirada());
            horaEntrega.setText(almoxarifado.getHoraEntrega());
            funcionario.setText(almoxarifado.getFuncionario());

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
        almoxarifado.deleteFromRealm();
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this, "Almoxarifado deletado", Toast.LENGTH_LONG).show();
        this.finish();
    }

    public void salvar() {
        int proximoID = 1;
        if (realm.where(Almoxarifado.class).max("id") != null)
            proximoID = realm.where(Almoxarifado.class).max("id").intValue() + 1;

        realm.beginTransaction();
        Almoxarifado almoxarifado = new Almoxarifado();
        almoxarifado.setId(proximoID);
        almoxarifado.setFerramenta(ferramenta.getText().toString());
        almoxarifado.setHoraRetirada(horaRetirada.getText().toString());
        almoxarifado.setHoraEntrega(horaEntrega.getText().toString());
        almoxarifado.setFuncionario(funcionario.getText().toString());

        realm.copyToRealm(almoxarifado);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this, "Almoxarifado Cadastrada", Toast.LENGTH_LONG).show();
        this.finish();
    }

    public void alterar() {
        realm.beginTransaction();

        almoxarifado.setFerramenta(ferramenta.getText().toString());
        almoxarifado.setHoraRetirada(horaRetirada.getText().toString());
        almoxarifado.setHoraEntrega(horaEntrega.getText().toString());
        almoxarifado.setFuncionario(funcionario.getText().toString());

        realm.copyToRealm(almoxarifado);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this, "Almoxarifado Alterada", Toast.LENGTH_LONG).show();
        this.finish();
    }
}
