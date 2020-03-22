package com.primeiro.controlerfinceiro.Editar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.primeiro.controlerfinceiro.Banco.ControlerDB;
import com.primeiro.controlerfinceiro.MainActivity;
import com.primeiro.controlerfinceiro.Objetos.Conta;
import com.primeiro.controlerfinceiro.R;
import com.primeiro.controlerfinceiro.Uteis.ObjMes;

import java.util.ArrayList;
import java.util.List;
/*
 * BY @david
 * 12/03/20
 * versao 1.0*/

//================================================================================================
//CLASSE QUE EDITA OS REGISTROS SALVOS NO BANCO.
public class EditarContas extends AppCompatActivity {

    /*COMPONENTES DA TELA*/
    EditText mValor, mConta, mCodigo;
    Spinner mSpinnerMes;
    CheckBox mCheckBoxConta;
    Button mbtnVoltar, mbtnAlterar;
    ArrayAdapter<ObjMes> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_contas);

        //CHAMA O MÉTODO PARA CRIAR OS COMPONENTES DA TELA
        this.mCriarComponentes();
        //CHAMA O MÉTODO QUE CRIA EVENTOS PARA OS COMPONENTES
        this.mCriarEventos();
        //CARREGA AS OPÇÕES DE ESTADO CIVIL
        this.mCarregarMes();
        //CARREGA OS VALORES NOS CAMPOS DA TELA.
        this.CarregaValoresCampos();
    }

    //************************************************************************************************
//VINCULA OS COMPONENTES DA TELA COM OS DA ATIVIDADE
    protected void mCriarComponentes() {
        mCodigo = (EditText) this.findViewById(R.id.edtiCodigo);
        mConta = (EditText) this.findViewById(R.id.editNome);
        mValor = (EditText) this.findViewById(R.id.editValor);
        mSpinnerMes = (Spinner) this.findViewById(R.id.SpinnerMes);
        mCheckBoxConta = (CheckBox) this.findViewById(R.id.checkboxAtivo);
        mbtnVoltar = (Button) this.findViewById(R.id.btnVoltar);
        mbtnAlterar = (Button) this.findViewById(R.id.btnAlterar);
    }
    //*************************************************************************************************
    protected void mCriarEventos() {
        //CRIANDO EVENTO CLICK PARA O BOTÃO ALTERAR
        mbtnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alterar_onClick();
            }
        });
//*************************************************************************************************
        //CRIANDO EVENTO NO BOTÃO VOLTAR
        mbtnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Inicio = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(Inicio);
                finish();
            }
        });
    }
    //ALTERA UM REGISTRO
    protected  void Alterar_onClick(){
        if(mConta.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(),"CAMPO OBRIGATORIO!", Toast.LENGTH_LONG).show();
            mConta.requestFocus();
        } else if (mValor.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(),"CAMPO OBRIGATORIO!", Toast.LENGTH_LONG).show();
        }else {
            /*CRIANDO UM OBJETO CONTA*/
            Conta conta = new Conta();

            /*SETANDO OS CAMPO CONTA*/
            conta.setTipo(mConta.getText().toString().trim());
            conta.setValor(mValor.getText().toString().trim());

            /*REALIZANDO UM CAST PARA PEGAR O OBJETO DO MES SELECIONADO*/
            ObjMes mes = (ObjMes)mSpinnerMes.getSelectedItem();
            /*SETANDO codigo MES*/
            conta.setVencimento(mes.getCodigo());

            /*SETA O REGISTRO COMO INATIVO*/
            conta.setRegistro((byte)0);
            /*SE TIVER SELECIONADO SETA COMO ATIVO*/
            if(mCheckBoxConta.isChecked())
                conta.setRegistro((byte)1);

            /*ALTERANDO O REGISTRO*/
            //new BancoDados(this).addConta(conta);

            new ControlerDB(this).mAtualizar(conta);
            Toast.makeText(getApplicationContext(),"CONTA ALTERADA COM SUCESSO!", Toast.LENGTH_LONG).show();
        }
    }
    //************************************************************************************************
//CARREGA AS OPÇÕES DE MES PARA O COMPONENTE SPINNER
    protected void mCarregarMes() {
        List<ObjMes> itens = new ArrayList<ObjMes>();

        itens.add(new ObjMes("1", "JANEIRO"));
        itens.add(new ObjMes("2", "FEVEREIRO"));
        itens.add(new ObjMes("3", "MARÇO"));
        itens.add(new ObjMes("4", "ABRIL"));
        itens.add(new ObjMes("5", "MAIO"));
        itens.add(new ObjMes("6", "JUNHO"));
        itens.add(new ObjMes("7", "JULHO"));
        itens.add(new ObjMes("8", "AGOSTO"));
        itens.add(new ObjMes("9", "SETEMBRO"));
        itens.add(new ObjMes("10", "OUTUBRO"));
        itens.add(new ObjMes("11", "NOVEMBRO"));
        itens.add(new ObjMes("12", "DEZEMBRO"));

        arrayAdapter = new ArrayAdapter<ObjMes>(this, android.R.layout.simple_spinner_item, itens);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinnerMes.setAdapter(arrayAdapter);
    }
//=================================================================================================================
    //POSICIONA O MES PARA EDIÇÃO
   protected void PosicionaMes(String chave) {


        for (int index = 0; index < arrayAdapter.getCount();index++) {

            if (((ObjMes) arrayAdapter.getItem(index)).getCodigo().equals(chave)) {

                mSpinnerMes.setSelection(index);
                break;
            }
        }
    }
//=============================================================================================================
    //CARREGA OS VALORES NOS CAMPOS APÓS RETORNAR DO SQLITE
    protected void CarregaValoresCampos() {

        ControlerDB db  = new ControlerDB(this);


        //PEGA O ID PESSOA QUE FOI PASSADO COMO PARAMETRO ENTRE AS TELAS
        Bundle extra = this.getIntent().getExtras();
        int id = extra.getInt("id");

        //CONSULTA UMA PESSOA POR ID
        Conta conta = db.mConsultar(id);

        //SETA O CÓDIGO NA VIEW
        mCodigo.setText(String.valueOf(conta.getId()));

        //SETA O NOME NA VIEW
        mConta.setText(conta.getTipo());

        //SETA O ENDEREÇO NA VIEW
        mValor.setText(conta.getValor());


        //POSICIONA O MES
        this.PosicionaMes(conta.getVencimento());

        //SETA SE O  REGISTRO ESTÁ ATIVO
        if (conta.getRegistro() == 1)
            mCheckBoxConta.setChecked(true);
    }
}
