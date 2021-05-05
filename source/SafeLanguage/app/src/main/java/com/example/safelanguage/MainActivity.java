package com.example.safelanguage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.safelanguage.Criptografia.Criptografia;
import com.example.safelanguage.Tela2.exibirConteudoActivity;

public class MainActivity extends AppCompatActivity {

    private Button cript, descript, limpar, colar;
    private EditText msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.cript = (Button) findViewById(R.id.criptografar);
        this.descript =(Button) findViewById(R.id.descriptografar);
        this.limpar =(Button) findViewById(R.id.limpar);
        this.colar = (Button) findViewById(R.id.colar);
        this.msg = (EditText) findViewById(R.id.mensagem_ou_codigo);

        //conjunto
        Ouvinte ouvinte = new Ouvinte();

        this.cript.setOnClickListener(ouvinte);
        this.descript.setOnClickListener(ouvinte);

        //Buttons individuais

        this.limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg.setText("");
            }
        });
        this.colar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                try{
                    String area = clipboard.getText().toString();
                    msg.setText(area);
                }catch (Exception e){
                    Toast.makeText(MainActivity.this,"Sem itens para colar",Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    private class Ouvinte implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Button btn = (Button) v;

            String mensagem = msg.getText().toString();
            String mensagemFinal="";


            if(btn.getText().equals("Criptografar")){
                mensagemFinal = Criptografia.criptografia(mensagem);
            }else{
                mensagemFinal = Criptografia.descriptografia(mensagem);
            }

            if(!mensagemFinal.equals("")){
                Intent intent = new Intent(MainActivity.this, exibirConteudoActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("msg",mensagemFinal);

                intent.putExtras(bundle);

                startActivity(intent);
            }else{
                Toast.makeText(MainActivity.this,"Por favor, \nDigite algo",Toast.LENGTH_SHORT).show();


            }


        }
    }
}