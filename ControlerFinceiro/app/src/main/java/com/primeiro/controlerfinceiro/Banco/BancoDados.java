package com.primeiro.controlerfinceiro.Banco;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/*
 * BY @david
 * 12/03/20
 * versao 1.0*/

//================================================================================================
//CLASSE QUE CRIA O BANCO DE DADOS.
public class BancoDados extends SQLiteOpenHelper {
    // CRIANDO BANCO.
    private static final String DATABASE = "banco";
    private static final int VERSION = 1;
    private static final String TB_CONTA = "conta";
    private static final String ID = "id";
    private static final String VENCI = "vencimento";
    private static final String TIPO = "tipo";
    private static final String VALOR = "valor";
    private static final String REGISTRO = "registro";

    private static final String TB_SALDO = "saldo";
    private static final String ID_SALDO = "S_id";
    private static final String MES = "S_vencimento";
    private static final String TIPO_SALDO = "S_tipo";
    private static final String VALOR_SALDO = "S_valor";
    private static final String REGISTRO_SALDO = "S_registro";
    // INSTANCIANDO CLASSE BANCO DADOS.
    public BancoDados(Context context) {
        super(context, DATABASE, null, VERSION);
    }
//METODO QUE CRIA AS TABELAS
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_conta = "CREATE TABLE "+TB_CONTA+"("
                + ID + " INTEGER PRIMARY KEY,"
                + VENCI + " TEXT,"
                + TIPO + " TEXT,"
                + VALOR + " TEXT,"
                + REGISTRO + " TEXT"
                +")";
        db.execSQL(sql_conta);

        String sql_saldo = "CREATE TABLE "+TB_SALDO+"("
                + ID_SALDO + " INTEGER PRIMARY KEY,"
                + MES + " TEXT,"
                + TIPO_SALDO + " TEXT,"
                + VALOR_SALDO + " TEXT,"
                + REGISTRO_SALDO + " TEXT"
                +") ";

        db.execSQL(sql_saldo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_CONTA);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + TB_SALDO);
        onCreate(db);

    }
    //*************************************************************************************************
    /*MÉTODO QUE VAI EXECUTAR AS ROTINAS NO
    BANCO DE DADOS*/
    public SQLiteDatabase GetConexaoDataBase(){

        return this.getWritableDatabase();
    }
        
}
