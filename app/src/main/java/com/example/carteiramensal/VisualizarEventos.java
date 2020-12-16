package com.example.carteiramensal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class VisualizarEventos extends AppCompatActivity {

        private TextView tituloTxt;
        private ListView listaEventos;
        private TextView totalTxt;
        private Button novoBtn;
        private Button cancelarBtn;

        //peraçao = 0 idica entrada e operacao = 1 indica saida
        private int operacao = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_eventos);

        tituloTxt = (TextView) findViewById(R.id.tituloTxt);
        listaEventos= (ListView) findViewById(R.id.listaEventos);
        totalTxt = (TextView) findViewById(R.id.valorTotalTxt);
        novoBtn = (Button) findViewById(R.id.novoBtn);
        cancelarBtn =  (Button) findViewById(R.id.cancelarBtn);

        Intent intencao =getIntent();
        operacao = intencao.getIntExtra("acao", -1);
        // 0- entrada e 1- saida

        ajustaOperacao();
    }

    private void ajustaOperacao(){
        //vamos precisar realizar uma busca no banco a respeito dos eventos a serem apresentados na lista

        if(operacao == 0){
            tituloTxt.setText("Entradas");
        }else{
            if(operacao ==1){
                tituloTxt.setText("Saídas"); 
            }else{
                //erro na configuracao da internet
                Toast.makeText(VisualizarEventos.this, "erro no paramentro acao", Toast.LENGTH_LONG).show();
            }
        }

    }
}