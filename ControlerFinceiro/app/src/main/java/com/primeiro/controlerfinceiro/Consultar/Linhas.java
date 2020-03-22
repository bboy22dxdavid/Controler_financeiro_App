package com.primeiro.controlerfinceiro.Consultar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.primeiro.controlerfinceiro.Banco.ControlerDB;
import com.primeiro.controlerfinceiro.Cadastro.CadastroContas;
import com.primeiro.controlerfinceiro.Editar.EditarContas;
import com.primeiro.controlerfinceiro.Objetos.Conta;
import com.primeiro.controlerfinceiro.Objetos.Saldo;
import com.primeiro.controlerfinceiro.R;

import java.util.ArrayList;
import java.util.List;
/*
 * BY @david
 * 12/03/20
 * versao 1.0*/

//================================================================================================
//CLASSE QUE COLOCA OS REGISTROS DO BANBO EM LINHAS PERSONALIZADAS.
public class Linhas extends BaseAdapter {

    //CRIANDO UM OBJETO LayoutInflater PARA FAZER LINK A NOSSA VIEW(linha_consulta.xml)
    private static LayoutInflater layoutInflater = null;

    //CRIANDO UMA LISTA DE CONTAS
    List<Conta> contas = new ArrayList<Conta>();


    //CIRANDO UM OBJETO DA NOSSA CLASSE QUE FAZ ACESSO AO BANCO DE DADOS
    ControlerDB banco_controle;

    //CRIANDO UM OBJETO DA NOSSA ATIVIDADE QUE CONTEM A LISTA
    private ConsultaContas consultar;
    /*CONSTRUTOR QUE VAI RECEBER A NOSSA ATIVIDADE COMO PARAMETRO E A LISTA DE CONTAS
    QUE VAI RETORNAR, DA NOSSA BASE DE DADOS
    */
//*************************************************************************************************
    public  Linhas (ConsultaContas consultar, List<Conta> contas){
        this.contas = contas;
        this.consultar = consultar;
        this.layoutInflater = (LayoutInflater) this.consultar.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.banco_controle = new ControlerDB(consultar);
    }
    //*************************************************************************************************
    //RETORNA A QUANTIDADE DE REGISTROS DA LISTA
    @Override
    public int getCount() {
        return contas.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    //*************************************************************************************************
    //ESSE MÉTODO SETA OS VALORES DE UM ITEM DA NOSSA LISTA DE PESSOAS PARA UMA LINHA DO NOSSO LISVIEW
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //CRIANDO UM OBJETO DO TIPO View PARA ACESSAR O NOSSO ARQUIVO DE LAYOUT linha_consultar.xml
        final View viewLinhas = layoutInflater.inflate(R.layout.linhas, null);

        //VINCULANDO OS CAMPOS DO ARQUIVO DE LAYOUT(linha_consultar.xml) AOS OBJETOS DECLARADOS.
        //CAMPO QUE VAI MOSTRAR O CÓDIGO DA CONTA
        TextView codigo = (TextView) viewLinhas.findViewById(R.id.Codigo);
        //CAMPO QUE VAI MOSTRAR O NOME DA CONTA
        TextView nome = (TextView) viewLinhas.findViewById(R.id.nome);
        //CAMPO QUE VAI MOSTRAR O VALOR
        TextView valor = (TextView) viewLinhas.findViewById(R.id.valor);
        //CAMPO QUE VAI MOSTRAR O MES
        TextView mes = (TextView) viewLinhas.findViewById(R.id.mes);
        //CAMPO QUE VAI MOSTRAR O REGISTRO
        TextView registro = (TextView) viewLinhas.findViewById(R.id.RegistroAtivo);
        //CRIANDO O BOTÃO  EXCLUIR PARA DELETARMOS UM REGISTRO DO BANCO DE DADOS
        Button buttonExcluir = (Button) viewLinhas.findViewById(R.id.buttonExcluir);
        //CRIANDO O BOTÃO  EDITAR UM REGISTRO DO BANCO DE DADOS
        Button buttonEditar = (Button) viewLinhas.findViewById(R.id.buttonEditar);


        //SETANDO OS CAMPO DA NOSSA VIEW
        mes.setText(this.mGetMes(contas.get(position).getVencimento()));
        codigo.setText(String.valueOf(contas.get(position).getId()));
        nome.setText(contas.get(position).getTipo());
        valor.setText(contas.get(position).getValor());
        //SETANDO OS CAMPO DA NOSSA VIEW SAldo

        if (contas.get(position).getRegistro() == 1) {
            registro.setText("CONTA ATIVA:Sim");
        } else {
            registro.setText("CONTA ATIVA:Não");
        }
//*************************************************************************************************
        //CRIANDO EVENTO CLICK PARA O BOTÃO DE EXCLUIR REGISTRO
        buttonExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EXCLUINDO UM REGISTRO
                banco_controle.mExcluir(contas.get(position).getId());
                //MOSTRA A MENSAGEM APÓS EXCLUIR UM REGISTRO
                Toast.makeText(consultar, "Registro excluido com sucesso!", Toast.LENGTH_LONG).show();
                //CHAMA O MÉTODO QUE ATUALIZA A LISTA COM OS REGISTROS QUE AINDA ESTÃO NA BASE
                AtualizarLista();
            }
        });
//***************************************************************************************************
//CRIANDO EVENTO CLICK PARA O BOTÃO QUE VAI REDIRECIONAR PARA A TELA DE EDIÇÃO
        // DO REGISTRO.
        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentRedirecionar = new Intent(consultar, EditarContas.class);

                intentRedirecionar.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                intentRedirecionar.putExtra("id",contas.get(position).getId());

                consultar.startActivity(intentRedirecionar);

                consultar.finish();
            }
        });
        return viewLinhas;
    }
        //*************************************************************************************************
//MÉTODO QUE RETORNA A DESCRIÇÃO DO MES POR CÓDIGO
        public String mGetMes(String codigoMes){

            if (codigoMes.equals("1")) {
                return "JANEIRO";
            } else if (codigoMes.equals("2")) {
                return "FEVEREIRO";
            } else if (codigoMes.equals("3")) {
                return "MARÇO";
            } else if (codigoMes.equals("4")) {
                return "ABRIL";
            } else if (codigoMes.equals("5")) {
                return "MAIO";
            } else if (codigoMes.equals("6")) {
                return "JUNHO";
            } else if (codigoMes.equals("7")) {
                return "JULHO";
            } else if (codigoMes.equals("8")) {
                return "AGOSTO";
            } else if (codigoMes.equals("9")) {
                return "SETEMBRO";
            } else if (codigoMes.equals("10")) {
                return "OUTUBRO";
            } else if (codigoMes.equals("11")) {
                return "NOVEMBRO";
            } else {
                return "DEZEMBRO";
            }

        }
        //************************************************************************************************
//ATUALIZA A LISTTA DEPOIS DE EXCLUIR UM REGISTRO
        public void AtualizarLista(){

            this.contas.clear();
            this.contas = banco_controle.mListarTodas();
            this.notifyDataSetChanged();
        }

    }
