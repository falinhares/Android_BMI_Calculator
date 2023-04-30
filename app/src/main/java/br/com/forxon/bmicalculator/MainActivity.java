package br.com.forxon.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    // Class variables = Fields
    TextView resultText;
    Button btnCalc;
    RadioButton radioHomem;
    RadioButton radioMulher;
    EditText txtIdade;
    EditText txtAltura;
    EditText txtPeso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonClickListener();
    }

    private void findViews() {
        //Declarar elementos da tela
        resultText=findViewById(R.id.text_resultado);
        radioHomem= findViewById(R.id.radio_button_homem);
        radioMulher=findViewById(R.id.radio_button_mulher);
        txtIdade=findViewById(R.id.text_idade);
        txtAltura=findViewById(R.id.text_altura);
        txtPeso=findViewById(R.id.text_peso);
        btnCalc=findViewById(R.id.button_calcular);
    }

    private void setupButtonClickListener() {
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularBmi();
            }
        });
    }

    private void calcularBmi() {
        String idadeText=txtIdade.getText().toString();
        String alturaText=txtAltura.getText().toString();
        String pesoText=txtPeso.getText().toString();
        double idade=Double.valueOf(idadeText);
        double altura=Double.valueOf(alturaText);
        double peso=Double.valueOf(pesoText);
        double altM=altura/100;
        double imc=peso/(altM*altM);
        DecimalFormat myDecFormat=new DecimalFormat("0.00");
        String imcText=myDecFormat.format(imc);
        String fullResultStr;
        if(idade>=18) {
            if(imc<18.5) {
                //peso baixo
                fullResultStr=imcText+" - Você está abaixo do peso ideal";
            } else if (imc>25) {
                //peso alto
                fullResultStr=imcText+" - Você está acima do peso ideal";
            } else {
                //peso correto
                fullResultStr=imcText+" - Você está no peso ideal";
            }
            resultText.setText(fullResultStr);
        } else {
            if (radioHomem.isChecked()) {
                fullResultStr="Você é um menino abaixo de 18 anos. Não é recomendado calcular o IMC.";
            } else if (radioMulher.isChecked()) {
                fullResultStr="Você é uma menina abaixo de 18 anos. Não é recomendado calcular o IMC.";
            } else {
                fullResultStr="Você é uma pessoa abaixo de 18 anos. Não é recomendado calcular o IMC.";
            }
            resultText.setText(fullResultStr);
        }
    }
}