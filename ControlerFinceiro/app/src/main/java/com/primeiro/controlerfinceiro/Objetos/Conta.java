package com.primeiro.controlerfinceiro.Objetos;
/*
 * BY @david
 * 12/03/20
 * versao 1.0*/

//================================================================================================
//CLASSE QUE CRIA O OBJETO CONTAS.
public class Conta {
    private Integer id;
    private String vencimento;
    private String tipo;
    private String valor;
    private byte registro;

    //********************************************************************************
    // METODOS GET E SET

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVencimento() {
        return vencimento;
    }

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public byte getRegistro() {
        return registro;
    }

    public void setRegistro(byte registro) {
        this.registro = registro;
    }
}
