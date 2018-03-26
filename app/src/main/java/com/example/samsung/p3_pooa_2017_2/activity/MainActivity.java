package com.example.samsung.p3_pooa_2017_2.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.samsung.p3_pooa_2017_2.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button projetoBT = (Button) findViewById(R.id.bt_projetos);
        Button engenheiroBT = (Button) findViewById(R.id.bt_engenheiros);
        Button funcionarioBT = (Button) findViewById(R.id.bt_funcionarios);
        Button descricaoBT = (Button) findViewById(R.id.bt_descricaos);
        Button almoxarifadoBT = (Button) findViewById(R.id.bt_almoxarifados);

        projetoBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProjetoActivity.class);
                startActivity(intent);
            }
        });

        engenheiroBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EngenheiroActivity.class);
                startActivity(intent);
            }
        });

        funcionarioBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FuncionarioActivity.class);
                startActivity(intent);
            }
        });

        descricaoBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DescricaoActivity.class);
                startActivity(intent);
            }
        });

        almoxarifadoBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AlmoxarifadoActivity.class);
                startActivity(intent);
            }
        });
    }

    private Context getContext(){
        return this;
    }
}
