package com.example.calculadora;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String[] arrayResult;
    Double resultado = 0.0;
    String ope = "";
    boolean flag = false;
    TextView textField;
    ArrayList<String> resulNumList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textField = findViewById(R.id.textField);
    }

    @SuppressLint("SetTextI18n")
    public void pulsarNumero(View view, String numero) {
        if (flag) {
            textField.setText("");
            flag = false;
        }
        textField.setText(textField.getText() + numero);
        resulNumList.add(numero);
    }

    public void pulsarCero(View view) {
        pulsarNumero(view, "0");
    }

    public void pulsarUno(View view) {
        pulsarNumero(view, "1");
    }

    public void pulsarDos(View view) {
        pulsarNumero(view, "2");
    }

    public void pulsarTres(View view) {
        pulsarNumero(view, "3");
    }

    public void pulsarCuatro(View view) {
        pulsarNumero(view, "4");
    }

    public void pulsarCinco(View view) {
        pulsarNumero(view, "5");
    }

    public void pulsarSeis(View view) {
        pulsarNumero(view, "6");
    }

    public void pulsarSiete(View view) {
        pulsarNumero(view, "7");
    }

    public void pulsarOcho(View view) {
        pulsarNumero(view, "8");
    }

    public void pulsarNueve(View view) {
        pulsarNumero(view, "9");
    }

    public void pulsar20(View view) {
        if (!textField.getText().equals("")) {
            textField.setText(textField.getText() + " para 20 dias");
            ope = "20";
        }
    }

    public void pulsar30(View view) {
        if (!textField.getText().equals("")) {
            textField.setText(textField.getText() + " para 30 dias");
            ope = "30";
        }
    }

    public void pulsar60(View view) {
        if (!textField.getText().equals("")) {
            textField.setText(textField.getText() + " para 60 dias");
            ope = "60";
        }
    }

    public void pulsar90(View view) {
        if (!textField.getText().equals("")) {
            textField.setText(textField.getText() + " para 90 dias");
            ope = "90";
        }
    }

    public void pulsarResultado(View view) {
        try {
            String inputText = textField.getText().toString();
            arrayResult = inputText.split("para", 2);

            if (arrayResult.length == 0 || arrayResult[0].isEmpty()) {
                throw new NumberFormatException("Entrada no válida");
            }

            double baseValue = Double.parseDouble(arrayResult[0]);

            switch (ope) {
                case "20":
                    resultado = (((baseValue * 0.11) + baseValue) / 20);
                    break;
                case "30":
                    resultado = ((baseValue * 0.13) + baseValue) / 30;
                    break;
                case "60":
                    resultado = ((baseValue * 0.26) + baseValue) / 60;
                    break;
                case "90":
                    resultado = ((baseValue * 0.39) + baseValue) / 90;
                    break;
            }
            resultado = Math.ceil(resultado * 100.0) / 100.0;
            String numAux = String.format("%.2f", resultado);
            String[] arrayAux = numAux.split("\\.");

            if (arrayAux.length > 1 && arrayAux[1].equals("00")) {
                int resultadoAux = Integer.parseInt(arrayAux[0]);
                textField.setText(Integer.toString(resultadoAux) + " diarios");
            } else {
                textField.setText(numAux + " diarios");
            }

            flag = true; // Indica que se ha mostrado un resultado

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Entrada no válida: " + textField.getText().toString(), Toast.LENGTH_SHORT).show();
        } catch (IllegalArgumentException e) {
            Toast.makeText(this, "Operación no válida: " + ope, Toast.LENGTH_SHORT).show();
        }
    }

    public void pulsarComa(View view) {
        if (flag) {
            textField.setText("");
            flag = false;
        }
        textField.setText(textField.getText() + ".");
    }

    public void pulsarBorrar(View view) {
        if (!textField.getText().equals("")) {
            textField.setText(textField.getText().toString().substring(0, textField.getText().length() - 1));
            if (!resulNumList.isEmpty()) {
                resulNumList.remove(resulNumList.size() - 1); // Elimina el último número del ArrayList
            }
        }
    }

    public void pulsarVaciar(View view) {
        textField.setText("");
        ope = "";
        resultado = 0.0;
        flag = false;
        resulNumList.clear();
    }

    public String[] getResulNumArray() {
        // Convierte el ArrayList a un array y lo retorna
        return resulNumList.toArray(new String[0]);
    }
}
