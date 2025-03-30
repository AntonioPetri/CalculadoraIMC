package br.com.fecapccp.calculadoraimc;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Obesidade2Activity extends AppCompatActivity {

    private Button btnT3Fechar;
    private TextView textT3Categoria, textT3Altura, textT3IMC, textT3Peso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_obesidade2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Pegar as informações
        Bundle bundle = getIntent().getExtras();
        double imc = bundle.getDouble("IMC");
        String Peso = bundle.getString("Peso");
        String Altura = bundle.getString("Altura");
        String Classificacao = bundle.getString("Classificacao");

        // Mostrar as informações
        textT3Categoria = findViewById(R.id.textT3Categoria);
        textT3Categoria.setText(Classificacao);

        textT3Altura = findViewById(R.id.textT3Altura);
        textT3Altura.setText("Altura:\n" + Altura);

        textT3IMC = findViewById(R.id.textT3IMC);
        textT3IMC.setText("IMC:\n" + String.format("%.2f", imc)); // Utiliza para mostrar apenas dois números após a virgula.

        textT3Peso = findViewById(R.id.textT3Peso);
        textT3Peso.setText("Peso:\n" + Peso);

        // Fechar e voltar para o inicio
        btnT3Fechar = findViewById(R.id.btnT3Fechar);
        btnT3Fechar.setOnClickListener( view -> {
            finish();
        });
    }
}