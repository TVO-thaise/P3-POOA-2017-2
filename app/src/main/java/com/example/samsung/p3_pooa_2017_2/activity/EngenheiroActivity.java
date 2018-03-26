package com.example.samsung.p3_pooa_2017_2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.samsung.p3_pooa_2017_2.R;
import com.example.samsung.p3_pooa_2017_2.adapter.ClickRecyclerViewListener;
import com.example.samsung.p3_pooa_2017_2.adapter.EngenheiroAdapter;
import com.example.samsung.p3_pooa_2017_2.model.Engenheiro;

import java.util.List;

import io.realm.Realm;

/**
 * Created by Samsung on 26/03/2018.
 */

public class EngenheiroActivity extends AppCompatActivity implements ClickRecyclerViewListener {

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engenheiro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        realm = Realm.getDefaultInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EngenheiroActivity.this,EngenheiroDetalheActivity.class);
                intent.putExtra("id",0);
                startActivity(intent);
            }
        });
    }

    private List<Engenheiro> getEngenheiros(){
        return (List)realm.where(Engenheiro.class).findAll();
    }

    @Override
    public void onClick(Object object) {
        Engenheiro engenheiro = (Engenheiro) object;
        Intent intent = new Intent(EngenheiroActivity.this,EngenheiroDetalheActivity.class);
        intent.putExtra("id",engenheiro.getId());
        startActivity(intent);
    }

    protected void onResume() {
        super.onResume();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_Engenheiro);

        recyclerView.setAdapter(new EngenheiroAdapter(getEngenheiros(),this,this));
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layout);
    }

    @Override
    public void finish(){
        realm.close();
    }
}