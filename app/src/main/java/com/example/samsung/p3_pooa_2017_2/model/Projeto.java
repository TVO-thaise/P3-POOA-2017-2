package com.example.samsung.p3_pooa_2017_2.model;

import java.io.Serializable;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


/**
 * Created by Samsung on 26/03/2018.
 */

public class Projeto  extends RealmObject implements Serializable  {
    @PrimaryKey
    private int id;
    private String codigo;
    private String dataInicial;
    private String dataPrevista;
    private String engenheiro;
    private String descricao;

    //Getters and Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(String dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public String getEngenheiro() {
        return engenheiro;
    }

    public void setEngenheiro(String engenheiro) {
        this.engenheiro = engenheiro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
