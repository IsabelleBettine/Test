package com.example.carteiramensal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.BatchUpdateException;
import java.util.Calendar;

import ferramentas.EventosDB;

public class MainActivity extends AppCompatActivity {

    private TextView titulo;
    private TextView entrada;
    private TextView saida;
    private TextView slado;
    private ImageButton entradaBtn;
    private ImageButton saidaBtn;
    private Button anteriorBtn;
    private Button proxBtn;
    private Button novoBtn;
    private Calendar hoje;
    private Calendar dataAPP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //criando o link entre os componentes Java x XML
        titulo = (TextView) findViewById(R.id.tituloMain);
        entrada = (TextView) findViewById(R.id.entradaText);
        saida = (TextView)findViewById(R.id.saidaText);
        slado= (TextView)findViewById(R.id.saldoText);

        entradaBtn = (ImageButton) findViewById(R.id.entradaBotton);
        saidaBtn = (ImageButton) findViewById(R.id.saidaBottom);

        anteriorBtn = (Button) findViewById(R.id.anteriorBottom);
        proxBtn =(Button) findViewById(R.id.proximoBottom);
        novoBtn = (Button) findViewById(R.id.novoBottom);

        //reponsavel pro implementar todos os eventos de botoes
        cadastroEventos();

        // recupero a data atual
        dataAPP = Calendar.getInstance();
        hoje = Calendar.getInstance();


        MostraDataApp();

    }

    private void cadastroEventos(){
        anteriorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vaiMesAnterior();
            }
        });
        proxBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizaMes(1);
            }
        });
        novoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventosDB db = new EventosDB(MainActivity.this);
                db.insereEvento();

                Toast.makeText(MainActivity.this, db.getDatabaseName(), Toast.LENGTH_LONG).show();

            }
        });
    }

    private void MostraDataApp(){
        //0-janeiro; 1-fevereiro...;11-dezembro
        String nomeMes[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio"
                , "Junho", "Julho", "Agosto", "Novembro"," Dezembro"};

        int mes=dataAPP.get(Calendar.MONTH);
        int ano = dataAPP.get(Calendar.YEAR);

        titulo.setText(nomeMes[mes]+ "/"+ ano);
    }

    private void vaiMesAnterior(){

        dataAPP.add(Calendar.MONTH, -1 );

        //aqui  temos que realizar uma busca no banco de dados(avaliar se existem meses anteriores cadastrados)
        MostraDataApp();
    }

    private void atualizaMes(int ajuste){

        dataAPP.add(Calendar.MONTH, ajuste );

        // nao pode passar do mes atual (prox mes)
        if(ajuste>0){
                if(dataAPP.after(hoje)){
                    dataAPP.add(Calendar.MONTH, -1);
                }
        }

        //aqui  temos que realizar uma busca no banco de dados(avaliar se existem meses anteriores cadastrados)
        MostraDataApp();
    }
}