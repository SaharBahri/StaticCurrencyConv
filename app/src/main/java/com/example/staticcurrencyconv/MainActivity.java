package com.example.staticcurrencyconv;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.inappmessaging.model.Button;

public class MainActivity extends AppCompatActivity {
    private EditText editTextAmount;
    private TextView textViewResult;
    private Button buttonConvert;
    private static final double CONVERSION_RATE = 0.30;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Initialize views
        editTextAmount = findViewById(R.id.inputTND);
        textViewResult = findViewById(R.id.result);
        buttonConvert = findViewById(R.id.convertButton);

        // Set up button click listener
        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCurrency();
            }
        });
    }
    private void convertCurrency() {
        String amountStr = editTextAmount.getText().toString();

        if (!amountStr.isEmpty()) {
            double amountTND = Double.parseDouble(amountStr);
            double amountEUR = amountTND * CONVERSION_RATE;
            String resultText = String.format("Résultat : %.2f TND = %.2f €", amountTND, amountEUR);
            textViewResult.setText(resultText);
        } else {
            textViewResult.setText("Veuillez entrer un montant");
        }
    }
}