package com.primeiro.controlerfinceiro.Uteis;

import androidx.annotation.NonNull;
/*
 * BY @david
 * 12/03/20
 * versao 1.0*/

//================================================================================================
//CLASSE QUE CRIA O OBJETO MES.
public class ObjMes {
    private String  codigo;
    private String  descricao;
//********************************************************************************
    // METODOS GET E SET

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    /*RETORNA A DESCRICAO NO COMPONENTE SPINNER */

    public ObjMes() {
    }

    @Override
    public String toString() {
        return this.descricao;
    }

    public ObjMes(String codigo, String descricao){
        this.codigo    = codigo;
        this.descricao = descricao;

    }
}
