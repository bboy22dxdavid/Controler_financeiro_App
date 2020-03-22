package com.primeiro.controlerfinceiro.Banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.primeiro.controlerfinceiro.Objetos.Conta;
import com.primeiro.controlerfinceiro.Objetos.Saldo;

import java.util.ArrayList;
import java.util.List;
/*
 * BY @david
 * 12/03/20
 * versao 1.0*/

//================================================================================================
//CLASSE QUE CONTROLA O CRUD.
public class ControlerDB {
    // INSTANCIANDO CLASSE BANCO DADOS
    BancoDados db;
    /***
     * CONSTRUTOR
     * @param context
     */
    public ControlerDB(Context context){
        db = new BancoDados(context);
    }
//***************************************************************************************
    /***
     * SALVA UM NOVO REGISTRO NA BASE DE DADOS
     * @param conta
     */
    public void mSalvar(Conta conta){

        db.GetConexaoDataBase();//intanciando o banco para escrever na tabela
        ContentValues cv = new ContentValues();// instanciando objeto p receber valores

        /*MONTANDO OS PARAMETROS PARA SEREM SALVOS*/
        cv.put("vencimento", conta.getVencimento());
        cv.put("tipo", conta.getTipo());
        cv.put("valor", conta.getValor());
        cv.put("registro", conta.getRegistro());

        /*EXECUTANDO INSERT DE UM NOVO REGISTRO*/
        db.GetConexaoDataBase().insert("conta ", null, cv);

        db.GetConexaoDataBase().close();
    }
    //********************************************************************************************
    /***
     * CONSULTA UMA PESSOA CADASTRADA PELO CÓDIGO
     * @param codigo
     * @return
     */
    public Conta mConsultar(int codigo){
        db.GetConexaoDataBase();//intanciando o banco para escrever na tabela
        Cursor cursor = db.GetConexaoDataBase().rawQuery("SELECT * FROM conta WHERE id= "+ codigo,null);


        cursor.moveToFirst();

        ///CRIANDO UMA NOVA CONTA
        Conta conta = new Conta();

        //ADICIONANDO OS DADOS DA CONTA
        conta.setId(cursor.getInt(cursor.getColumnIndex("id")));
        conta.setVencimento(cursor.getString(cursor.getColumnIndex("vencimento")));
        conta.setTipo(cursor.getString(cursor.getColumnIndex("tipo")));
        conta.setValor(cursor.getString(cursor.getColumnIndex("valor")));
        conta.setRegistro((byte)cursor.getShort(cursor.getColumnIndex("registro")));

        //RETORNANDO A CONTA
        return conta;
    }
    //********************************************************************************************
    /***
     * ATUALIZA UM REGISTRO JÁ EXISTENTE NA BASE
     * @param conta
     */
    public void mAtualizar(Conta conta){

        db.GetConexaoDataBase();//intanciando o banco para escrever na tabela
        ContentValues values = new ContentValues();
        /*MONTANDO OS PARAMETROS PARA SEREM ATUALIZADO*/
        values.put("mes", conta.getVencimento());
        values.put("nome", conta.getTipo());
        values.put("valor", conta.getValor());
        values.put("registro", conta.getRegistro());

        /*ATUALIZANDO UM NOVO REGISTRO*/
        /*REALIZANDO UPDATE PELA CHAVE DA TABELA*/
        //db.GetConexaoDataBase().update("conta", values,"id = ?", new String[]{Integer.toString(conta.getId())});
         db.GetConexaoDataBase().update("conta", values, "id" + " = ? ",
                 new String[]{String.valueOf(conta.getId())});
    }
    //********************************************************************************************
    /***
     * EXCLUI UM REGISTRO PELO CÓDIGO
     * @param codigo
     * @return
     */
    public Integer mExcluir(int codigo){
        //EXCLUINDO  REGISTRO E RETORNANDO O NÚMERO DE LINHAS AFETADAS
        db.GetConexaoDataBase();//intanciando o banco para escrever na tabela

        return db.GetConexaoDataBase().delete("conta ", "id = ?",
                new String[]{Integer.toString(codigo)});

        //verificar se precisa fechar banco
    }
    //*************************************************************************************************
    /***
     * LISTAR TODAS AS CONTAS CADASTRADAS NA BASE
     * @return
     */
    public List<Conta> mListarTodas(){
        List<Conta> contas = new ArrayList<Conta>();
        String queryS = "SELECT * FROM conta " ;// Select campo From Tabela Where estado ='SP '
        db.GetConexaoDataBase();
        Cursor c = db.GetConexaoDataBase().rawQuery(queryS, null);
        if (c.moveToFirst()){
            do {
                /* CRIANDO UMA NOVA CONTA*/
                Conta conta = new Conta();

                //ADICIONANDO OS DADOS DA CONTA
                conta.setId(Integer.parseInt(c.getString(0)));
                conta.setVencimento(c.getString(1));
                conta.setTipo(c.getString(2));
                conta.setValor(c.getString(3));
                conta.setRegistro(Byte.parseByte(c.getString(4)));
                //ADICIONANDO UMA PESSOA NA CONTA
                contas.add(conta);
            }while (c.moveToNext());
        }
        //RETORNANDO A LISTA DE CONTA
        return contas;
    }
    //***************************************************************************************
    /***CRUD SALDO
     * SALVA UM NOVO REGISTRO NA BASE DE DADOS
     * @param saldo
     */
    public void mAddSaldo(Saldo saldo){

        db.GetConexaoDataBase();//intanciando o banco para escrever na tabela
        ContentValues cv = new ContentValues();// instanciando objeto p receber valores

        /*MONTANDO OS PARAMETROS PARA SEREM SALVOS*/
        cv.put("S_vencimento", saldo.getVencimento());
        cv.put("S_tipo", saldo.getTipo());
        cv.put("S_valor", saldo.getValor());
        cv.put("S_registro", saldo.getRegistro());

        /*EXECUTANDO INSERT DE UM NOVO REGISTRO*/
        db.GetConexaoDataBase().insert("saldo ", null, cv);

        db.GetConexaoDataBase().close();
    }
    //*************************************************************************************************
    /***
     * CONSULTA TODAS AS CONTAS CADASTRADAS NA BASE
     * @return
     */
    public List<Saldo> mListarSaldo(){
        List<Saldo> saldos = new ArrayList<Saldo>();
        String queryS = "SELECT * FROM saldo " ;
        db.GetConexaoDataBase();
        Cursor c = db.GetConexaoDataBase().rawQuery(queryS, null);
        if (c.moveToFirst()){
            do {
                /* CRIANDO UMA NOVA CONTA*/
                Saldo saldo = new Saldo();

                //ADICIONANDO OS DADOS DA CONTA
                saldo.setId(Integer.parseInt(c.getString(0)));
                saldo.setVencimento(c.getString(1));
                saldo.setTipo(c.getString(2));
                saldo.setValor(c.getString(3));
                saldo.setRegistro(Byte.parseByte(c.getString(4)));
                //ADICIONANDO UMA PESSOA NA CONTA
                saldos.add(saldo);
            }while (c.moveToNext());
        }
        //RETORNANDO A LISTA DE CONTA
        return saldos;
    }

    //********************************************************************************************
    /***
     * EXCLUI UM REGISTRO PELO CÓDIGO
     * @param codigo
     * @return
     */
    public Integer mExcluirSaldo(int codigo){
        //EXCLUINDO  REGISTRO E RETORNANDO O NÚMERO DE LINHAS AFETADAS
        db.GetConexaoDataBase();//intanciando o banco para escrever na tabela

        return db.GetConexaoDataBase().delete("saldo ", "S_id = ?",
                new String[]{Integer.toString(codigo)});

        //verificar se precisa fechar banco
    }
}
