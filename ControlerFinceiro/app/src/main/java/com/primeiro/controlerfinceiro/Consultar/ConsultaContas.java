package com.primeiro.controlerfinceiro.Consultar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.primeiro.controlerfinceiro.Banco.BancoDados;
import com.primeiro.controlerfinceiro.Banco.ControlerDB;
import com.primeiro.controlerfinceiro.MainActivity;
import com.primeiro.controlerfinceiro.Objetos.Conta;
import com.primeiro.controlerfinceiro.Objetos.Saldo;
import com.primeiro.controlerfinceiro.R;
import com.primeiro.controlerfinceiro.Receita.ReceitaMensal;

import java.util.ArrayList;
import java.util.List;
/*
 * BY @david
 * 12/03/20
 * versao 1.0*/

//================================================================================================
//CLASSE QUE FAZ A CONSULTA NO BANCO DE DADOS E LISTA AS CONTAS.
public class ConsultaContas extends AppCompatActivity {
    ListView mlistar;
    Button mVoltar, mReceita;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
   // ControlerDB db = new ControlerDB(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulta_contas);
        mlistar = (ListView) findViewById(R.id.lista);
        mVoltar = (Button)findViewById(R.id.buttonVoltar);
        mReceita = (Button)findViewById(R.id.btnReceita);

       //listarConta();
        //listarSaldo();
        //CHAMA O MÉTODO QUE CARREGA AS PESSOAS CADASTRADAS NA BASE DE DADOS
        this.CarregarCOntaCadastradas();

        //CHAMA O MÉTODO QUE CRIA O EVENTO PARA O BOTÃO VOLTAR
        this.CriarEvento();
    }
    //************************************************************************************************

    protected  void CriarEvento(){
//*************************************************************************************************
        //MÉTODO QUE CRIA EVENTO PARA O BOTÃO VOLTAR
       mVoltar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                //REDIRECIONA PARA A TELA PRINCIPAL
                Intent intentInicio = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentInicio);

                //FINALIZA A ATIVIDADE ATUAL
                finish();
            }
        });
        //*************************************************************************************************
        //MÉTODO QUE LISTAR A ACTIVE RECEITA
       mReceita.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //REDIRECIONA PARA A TELA PRINCIPAL
               Intent intentInicio = new Intent(getApplicationContext(), ReceitaMensal.class);
               startActivity(intentInicio);

               //FINALIZA A ATIVIDADE ATUAL
               finish();
           }
       });
    }
    //*************************************************************************************************
    //MÉTODO QUE CONSULTA AS CONTA CADASTRADAS
    public void CarregarCOntaCadastradas(){
        ControlerDB db = new ControlerDB(this);

        //BUSCA AS CONTAS CADASTRADAS
        List<Conta> contaList = db.mListarTodas();

        //SETA O ADAPTER DA LISTA COM OS REGISTROS RETORNADOS DA BASE
        mlistar.setAdapter(new Linhas(this,contaList));
    }
}
