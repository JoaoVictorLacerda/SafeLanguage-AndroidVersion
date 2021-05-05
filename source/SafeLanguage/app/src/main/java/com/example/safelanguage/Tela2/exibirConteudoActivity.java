package com.example.safelanguage.Tela2 ;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.safelanguage.MainActivity;
import com.example.safelanguage.R;

public class exibirConteudoActivity extends AppCompatActivity {
    private String codigo;
    private EditText texto;
    private Button copiar, voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir_conteudo);

        this.texto = (EditText) findViewById(R.id.textoCodigo);
        this.copiar = (Button) findViewById(R.id.copiarcodigo);
        this.voltar = (Button) findViewById(R.id.voltar);

        this.voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(exibirConteudoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        this.copiar.setOnClickListener(new Ouvinte());

        Intent recebedora = getIntent();
        Bundle msg = recebedora.getExtras();

        this.codigo = msg.getString("msg");
        this.texto.setText(codigo);
    }
    private class Ouvinte implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Toast.makeText(exibirConteudoActivity.this,"copiado para Área de Transferência",Toast.LENGTH_SHORT).show();
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

            ClipData clip = ClipData.newPlainText("exemple",codigo);

            clipboard.setPrimaryClip(clip);





        }
    }
}