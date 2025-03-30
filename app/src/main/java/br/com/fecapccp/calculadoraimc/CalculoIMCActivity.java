package br.com.fecapccp.calculadoraimc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculoIMCActivity extends AppCompatActivity {

    // Criação de Variáveis:
    private Button btnT2CalcularIMC, btnT2Limpar, btnT2Fechar;
    private EditText editTextT2Peso, editTextT2Altura;
    private String stringPeso, stringAltura, classificacao, stringIMC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculo_imcactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Vincular variável com ID:
        btnT2CalcularIMC = findViewById(R.id.btnT2CalcularIMC);
        btnT2Limpar = findViewById(R.id.btnT2Limpar);
        btnT2Fechar = findViewById(R.id.btnT2Fechar);
        editTextT2Peso = findViewById(R.id.editTextT2Peso);
        editTextT2Altura = findViewById(R.id.editTextT2Altura);


        // Calcular IMC:
        btnT2CalcularIMC.setOnClickListener( view -> {
            // Chama as Booleans para validar os campos
            boolean validoPeso = validarCampo(editTextT2Peso);
            boolean validoAltura = validarCampo(editTextT2Altura);


            // Se ambas forem verdadeiras, vai para a tela desejada do resultado.
            if(validoPeso && validoAltura){
                double imc = CalcularIMC(); // Define o imc para achar o destino certo
                Class destino = pegarDestino(imc); //Define o destino usado o Metodo
                classificacao = definirClassificao(imc); // Define a classificação
                stringPeso = editTextT2Peso.getText().toString().trim();
                stringAltura = editTextT2Altura.getText().toString().trim();

                Intent intent = new Intent(this, destino);
                // Levar os dados juntos para a próxima tela.
                intent.putExtra("IMC", imc);
                intent.putExtra("Peso", stringPeso);
                intent.putExtra("Altura", stringAltura);
                intent.putExtra("Classificacao", classificacao);

                startActivity(intent);
                finish(); // para fechar a utilização dessa tela
            }
        });

        // Limpar os dados:
        btnT2Limpar.setOnClickListener( view -> {
            Limpar();
        });

        // Fechar a tela e voltar para o menu
        btnT2Fechar.setOnClickListener( view -> {
            finish();
        });


    }

    // Metodo para verificar se são números, caso não, retorna erro
    public boolean validarCampo(EditText editText) {
        String texto = editText.getText().toString().trim(); // Pega o texto e remove os espaços

        if (texto.isEmpty()) { // Verifica se está vazio
            editText.setError("Campo não pode estar vazio!");
            return false;
        }
        try {
            double valor = Double.parseDouble(texto); // Tenta converter para double
            editText.setError(null); // Remove erro se a conversão der certo
            return true;
        } catch (NumberFormatException e) {
            editText.setError("Digite um número válido (ex: 75 ou 1.75)!");
            return false;
        }
    }

    // Metodo para calcular e retornar o IMC:
    public double CalcularIMC(){
        // Pega os valores definidos pelo user
        stringPeso = editTextT2Peso.getText().toString();
        stringAltura = editTextT2Altura.getText().toString();

        // Transforma String em double
        double peso = Double.parseDouble(stringPeso);
        double altura = Double.parseDouble(stringAltura);

        return peso / (altura * altura);
    }

    // Metodo que retorna uma classe para definir destino:
    public Class pegarDestino(double imc) {
        if(imc < 18.5){
            return AbaixoDoPesoActivity.class;
        } else if (imc < 25){
            return PesoNormalActivity.class;
        } else if (imc < 30){
            return SobrepesoActivity.class;
        } else if (imc < 35) {
            return Obesidade1Activity.class;
        } else if (imc < 40){
            return Obesidade2Activity.class;
        } else {
            return Obesidade3Activity.class;
        }
    }

    // Metodo para definir a classifição
    public String definirClassificao(double imc){
        if(imc < 18.5){
            return "Abaixo do Peso";
        } else if (imc < 25){
            return "Peso Normal";
        } else if (imc < 30){
            return "Sobrepeso";
        } else if (imc < 35) {
            return "Obesidade grau 1";
        } else if (imc < 40){
            return "Obesidade grau 2";
        } else {
            return "Obesidade grau 3";
        }
    }

    // Metodo Limpar dados:
    public void Limpar(){
        editTextT2Peso.setText("");
        editTextT2Altura.setText("");
    }
}