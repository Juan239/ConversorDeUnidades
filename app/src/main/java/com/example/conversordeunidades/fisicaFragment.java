package com.example.conversordeunidades;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class fisicaFragment extends Fragment {
    Spinner spinner;
    String opcionSpinner;
    EditText entrada1, entrada2, entrada3;
    TextView resultado;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fisica, container, false);

        spinner = rootView.findViewById(R.id.spinnerFisica);
        entrada1 = rootView.findViewById(R.id.valorEntrada1);
        entrada2 = rootView.findViewById(R.id.valorEntrada2);
        entrada3 = rootView.findViewById(R.id.valorEntrada3);
        resultado = rootView.findViewById(R.id.resultadoFisica);

        String[] valoresLongitud = {"Aceleración lineal","Velocidad lineal","Frecuencia","Fuerza","Presión","Densidad"};
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(valoresLongitud));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.style_spinner, arrayList);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                opcionSpinner = spinner.getSelectedItem().toString();
                if(opcionSpinner.equals("Aceleración lineal")){
                    reestablecer();
                    entrada1.setHint("Ingrese la variación de velocidad");
                    entrada2.setHint("Ingrese la variación de tiempo");
                    entrada3.setVisibility(View.GONE);
                } else if (opcionSpinner.equals("Velocidad lineal")) {
                    reestablecer();
                    entrada1.setHint("Ingrese el cambio en la posición");
                    entrada2.setHint("Ingrese la variación de tiempo");
                    entrada3.setVisibility(View.GONE);
                } else if (opcionSpinner.equals("Frecuencia")) {
                    reestablecer();
                    entrada1.setHint("Ingrese el periodo");
                    entrada2.setVisibility(View.GONE);
                    entrada3.setVisibility(View.GONE);
                } else if (opcionSpinner.equals("Fuerza")) {
                    reestablecer();
                    entrada1.setHint("Ingrese la masa");
                    entrada2.setHint("Ingrese la aceleración");
                    entrada3.setVisibility(View.GONE);
                } else if (opcionSpinner.equals("Presión")) {
                    reestablecer();
                    entrada1.setHint("Ingrese la fuerza");
                    entrada2.setHint("Ingrese el área");
                    entrada3.setVisibility(View.GONE);
                } else if (opcionSpinner.equals("Densidad")) {
                    reestablecer();
                    entrada1.setHint("Ingrese la masa");
                    entrada2.setHint("Ingrese el volumen");
                    entrada3.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        resultado.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                opcionSpinner = spinner.getSelectedItem().toString();
                if(opcionSpinner.equals("Aceleración lineal")){
                    if(!entrada1.getText().toString().isEmpty() && !entrada2.getText().toString().isEmpty()) {
                        resultado.setText(aceleracionLineal() + " m/s²");
                    }
                    else {
                        Toast.makeText(getActivity(), "No deben quedar campos vacios", Toast.LENGTH_LONG).show();
                    }
                } else if (opcionSpinner.equals("Velocidad lineal")) {
                    if(!entrada1.getText().toString().isEmpty() && !entrada2.getText().toString().isEmpty()) {
                        resultado.setText(velocidadLineal() + " m/s");
                    }
                    else {
                        Toast.makeText(getActivity(), "No deben quedar campos vacios", Toast.LENGTH_LONG).show();
                    }
                } else if (opcionSpinner.equals("Frecuencia")) {
                    if(!entrada1.getText().toString().isEmpty()) {
                        resultado.setText(frecuencia() + " Hz");
                    }else {
                        Toast.makeText(getActivity(), "No deben quedar campos vacios", Toast.LENGTH_LONG).show();
                    }
                } else if (opcionSpinner.equals("Fuerza")) {
                    if(!entrada1.getText().toString().isEmpty() && !entrada2.getText().toString().isEmpty()) {
                        resultado.setText(fuerza() + " N");
                    }else {
                        Toast.makeText(getActivity(), "No deben quedar campos vacios", Toast.LENGTH_LONG).show();
                    }
                } else if (opcionSpinner.equals("Presión")) {
                    if(!entrada1.getText().toString().isEmpty() && !entrada2.getText().toString().isEmpty()) {
                        resultado.setText(presion() + " N/m²");
                    }else {
                        Toast.makeText(getActivity(), "No deben quedar campos vacios", Toast.LENGTH_LONG).show();
                    }
                } else if (opcionSpinner.equals("Densidad")) {
                    if(!entrada1.getText().toString().isEmpty() && !entrada2.getText().toString().isEmpty()) {
                        resultado.setText(densidad() + " kg/m³");
                    }else {
                        Toast.makeText(getActivity(), "No deben quedar campos vacios", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


        return rootView;
    }

    public String aceleracionLineal(){
        float x = Float.parseFloat(entrada1.getText().toString());
        float y = Float.parseFloat(entrada2.getText().toString());
        float resultado = x/y;
        return escribe(resultado);
    }
    public String velocidadLineal(){
        float x = Float.parseFloat(entrada1.getText().toString());
        float y = Float.parseFloat(entrada2.getText().toString());
        float resultado = x/y;
        return escribe(resultado);
    }
    public String frecuencia(){
        float x = Float.parseFloat(entrada1.getText().toString());
        float resultado = 1/x;
        return escribe(resultado);
    }
    public String fuerza(){
        float x = Float.parseFloat(entrada1.getText().toString());
        float y = Float.parseFloat(entrada2.getText().toString());
        float resultado = x*y;
        return escribe(resultado);
    }
    public String presion(){
        float x = Float.parseFloat(entrada1.getText().toString());
        float y = Float.parseFloat(entrada2.getText().toString());
        float resultado = x/y;
        return escribe(resultado);
    }
    public String densidad(){
        float x = Float.parseFloat(entrada1.getText().toString());
        float y = Float.parseFloat(entrada2.getText().toString());
        float resultado = x/y;
        return escribe(resultado);
    }

    public void reestablecer(){
        entrada1.setVisibility(View.VISIBLE);
        entrada2.setVisibility(View.VISIBLE);
        entrada3.setVisibility(View.VISIBLE);
        entrada1.setText("");
        entrada2.setText("");
        entrada3.setText("");
        resultado.setText("Calcular");
    }
    static String escribe(double d) {
        NumberFormat nf = new DecimalFormat("##.####");
        return (nf.format(d).replace(",","."));
    }
}