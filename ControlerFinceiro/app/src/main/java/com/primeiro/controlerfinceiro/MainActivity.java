package com.primeiro.controlerfinceiro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.primeiro.controlerfinceiro.Cadastro.CadastroContas;
import com.primeiro.controlerfinceiro.Cadastro.CadastroSaldo;
import com.primeiro.controlerfinceiro.Consultar.ConsultaContas;
import com.primeiro.controlerfinceiro.Consultar.ConsultaSaldo;

public class MainActivity extends AppCompatActivity {
    //DECLARANDO UM OBJETO LISTVIEW
    ListView mListMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*CARREGA O MÉTODO DE CRIAÇÃO DOS COMPONENTES*/
        this.CriarComponentes();

        /*CARREGA AS OPÇÕES DA LISTA*/
        this.CarregaOpcoesLista();

        /*CRIA EVENTOS DA LISTA*/
        this.CriarEventos();
    }
    //**************************************************************************************************
//CRIA A OPÇÕES DA NOSSA LISTA E ADICIONA AO LISTVIEW DA NOSSA TELA.
    protected void CarregaOpcoesLista() {

        String[] itens = new String[4];

        itens[0] = "ADICIONAR";
        itens[1] = "SALDO";
        itens[2] = "CONSULTAR";
        itens[3] = "CONSULTAR SALDO";

        ArrayAdapter<String> arrayItens = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,itens);

        mListMenu.setAdapter(arrayItens);
    }

    //***************************************************************************************************
    //VINCULA O COMPONENTE DA NOSSA TELA AO OBJETO DA NOSSA ATIVIDADE
    protected void CriarComponentes() {
//VINCULANDO A LISTA DA TELA AO LISTVIEW QUE DECLARAMOS
        mListMenu = (ListView) this.findViewById(R.id.ListMenu);
    }

    //**************************************************************************************************
    //CRIA EVENTO PARA A LISTA
    protected void CriarEventos() {
        mListMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String opcMenu = ((TextView) view).getText().toString();

                RedirecionaTela(opcMenu);
            }
        });
    }
    //*********************************************************************************************
    //REDIRECIONA PARA A TELA SELECIONADA NO MENU
    protected void RedirecionaTela(String opcaoMenu){

        Intent intentRedirecionar;

        if(opcaoMenu.equals("ADICIONAR")){

            intentRedirecionar = new Intent(this, CadastroContas.class);
            startActivity(intentRedirecionar);
            finish();
        }else if (opcaoMenu.equals("SALDO")){
            intentRedirecionar = new Intent(this, CadastroSaldo.class);
            startActivity(intentRedirecionar);
            finish();

        }else if (opcaoMenu.equals("CONSULTAR")){
            intentRedirecionar = new Intent(this, ConsultaContas.class);
            startActivity(intentRedirecionar);
            finish();

        } else if (opcaoMenu.equals("CONSULTAR SALDO")){
            intentRedirecionar = new Intent(this, ConsultaSaldo.class);
            startActivity(intentRedirecionar);
            finish();

        }else
            Toast.makeText(getApplicationContext(), "Opção inválida!", Toast.LENGTH_SHORT).show();
    }
}
