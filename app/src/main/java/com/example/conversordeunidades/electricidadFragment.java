package com.example.conversordeunidades;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

public class electricidadFragment extends Fragment {
    Spinner spinner;
    String opcionSpinner;
    EditText entrada1, entrada2, entrada3;
    TextView resultado;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_electricidad, container, false);
        spinner = rootView.findViewById(R.id.spinnerElectricidad);
        entrada1 = rootView.findViewById(R.id.EvalorEntrada1);
        entrada2 = rootView.findViewById(R.id.EvalorEntrada2);
        entrada3 = rootView.findViewById(R.id.EvalorEntrada3);
        resultado = rootView.findViewById(R.id.resultadoElectricidad);

        String[] valoresLongitud = {"Energía o trabajo", "Potencia","Carga eléctrica", "Potencial eléctrico", "Resistencia eléctrica", "Conductancia eléctrica", "Capacitancia eléctrica", "Flujo magnético", "Inductancia"};
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(valoresLongitud));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.style_spinner, arrayList);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                opcionSpinner = spinner.getSelectedItem().toString();
                if(opcionSpinner.equals("Energía o trabajo")){
                    reestablecer();
                    entrada3.setVisibility(View.GONE);
                    entrada1.setHint("Carga eléctrica en Coulombs:");
                    entrada2.setHint("Potencial eléctrico en Voltios:");
                } else if (opcionSpinner.equals("Potencia")) {
                    reestablecer();
                    entrada3.setVisibility(View.GONE);
                    entrada1.setHint("Energía en Joules:");
                    entrada2.setHint("Tiempo en segundos:");
                } else if (opcionSpinner.equals("Carga eléctrica")) {
                    reestablecer();
                    entrada3.setVisibility(View.GONE);
                    entrada1.setHint("Intensidad en Amperios:");
                    entrada2.setHint("Tiempo en segundos:");
                } else if (opcionSpinner.equals("Potencial eléctrico")) {
                    reestablecer();
                    entrada3.setVisibility(View.GONE);
                    entrada1.setHint("Energía en Joules:");
                    entrada2.setHint("Carga eléctrica en Coulombs:");
                } else if (opcionSpinner.equals("Resistencia eléctrica")) {
                    reestablecer();
                    entrada3.setVisibility(View.GONE);
                    entrada1.setHint("Diferencia de potencial en Voltios:");
                    entrada2.setHint("Corriente eléctrica en Amperios:");
                } else if (opcionSpinner.equals("Conductancia eléctrica")) {
                    reestablecer();
                    entrada2.setVisibility(View.GONE);
                    entrada3.setVisibility(View.GONE);
                    entrada1.setHint("Ingrese la resistencia:");

                } else if (opcionSpinner.equals("Capacitancia eléctrica")) {
                    reestablecer();
                    entrada3.setVisibility(View.GONE);
                    entrada1.setHint("Carga máxima del condensador:");
                    entrada2.setHint("Voltaje aplicado:");

                } else if (opcionSpinner.equals("Flujo magnético")) {
                    reestablecer();
                    entrada3.setVisibility(View.GONE);
                    entrada1.setHint("Intensidad del campo magnético:");
                    entrada2.setHint("Área en metros:");
                } else if (opcionSpinner.equals("Inductancia")) {
                    reestablecer();
                    entrada1.setHint("Flujo magnético:");
                    entrada2.setHint("Número de bobinas:");
                    entrada3.setHint("Intensidad de la corriente:");
                }
            }            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        resultado.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                opcionSpinner = spinner.getSelectedItem().toString();
                if(opcionSpinner.equals("Energía o trabajo")){
                    if(!entrada1.getText().toString().isEmpty() && !entrada2.getText().toString().isEmpty()) {
                        resultado.setText(energia() + " J");
                    }
                    else {
                        Toast.makeText(getActivity(), "No deben quedar campos vacios", Toast.LENGTH_LONG).show();
                    }
                } else if (opcionSpinner.equals("Potencia")) {
                    if(!entrada1.getText().toString().isEmpty() && !entrada2.getText().toString().isEmpty()) {
                        resultado.setText(potencia()+ " W");
                    }
                    else {
                        Toast.makeText(getActivity(), "No deben quedar campos vacios", Toast.LENGTH_LONG).show();
                    }
                } else if (opcionSpinner.equals("Carga eléctrica")) {
                    if(!entrada1.getText().toString().isEmpty() && !entrada2.getText().toString().isEmpty()) {
                        resultado.setText(cargaElectrica() + " C");
                    }
                    else {
                        Toast.makeText(getActivity(), "No deben quedar campos vacios", Toast.LENGTH_LONG).show();
                    }
                } else if (opcionSpinner.equals("Potencial eléctrico")) {
                    if(!entrada1.getText().toString().isEmpty() && !entrada2.getText().toString().isEmpty()) {
                        resultado.setText(potencial()+ " V");
                    }
                    else {
                        Toast.makeText(getActivity(), "No deben quedar campos vacios", Toast.LENGTH_LONG).show();
                    }
                } else if (opcionSpinner.equals("Resistencia eléctrica")) {
                    if(!entrada1.getText().toString().isEmpty() && !entrada2.getText().toString().isEmpty()) {
                        resultado.setText(resistencia() + " Ω");
                    }
                    else {
                        Toast.makeText(getActivity(), "No deben quedar campos vacios", Toast.LENGTH_LONG).show();
                    }

                } else if (opcionSpinner.equals("Conductancia eléctrica")) {
                    if(!entrada1.getText().toString().isEmpty()) {
                        resultado.setText(conductancia() + " S");
                    }
                    else {
                        Toast.makeText(getActivity(), "No deben quedar campos vacios", Toast.LENGTH_LONG).show();
                    }
                } else if (opcionSpinner.equals("Capacitancia eléctrica")) {
                    if(!entrada1.getText().toString().isEmpty() && !entrada2.getText().toString().isEmpty()) {
                        resultado.setText(capacitancia() + " F");
                    }
                    else {
                        Toast.makeText(getActivity(), "No deben quedar campos vacios", Toast.LENGTH_LONG).show();
                    }
                } else if (opcionSpinner.equals("Flujo magnético")) {
                    if(!entrada1.getText().toString().isEmpty() && !entrada2.getText().toString().isEmpty()) {
                        resultado.setText(flujoMagnetico() + " Wb");
                    }
                    else {
                        Toast.makeText(getActivity(), "No deben quedar campos vacios", Toast.LENGTH_LONG).show();
                    }
                } else if (opcionSpinner.equals("Inductancia")) {
                    if(!entrada1.getText().toString().isEmpty() && !entrada2.getText().toString().isEmpty()) {
                        resultado.setText(inductancia() + " H");
                    }
                    else {
                        Toast.makeText(getActivity(), "No deben quedar campos vacios", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


        return rootView;
    }

    public String energia(){
        float x = Float.parseFloat(entrada1.getText().toString());
        float y = Float.parseFloat(entrada2.getText().toString());
        float resultado = x * y;
        return escribe(resultado);
    }
    public String potencia(){
        float x = Float.parseFloat(entrada1.getText().toString());
        float y = Float.parseFloat(entrada2.getText().toString());
        float resultado = x / y;
        return escribe(resultado);
    }
    public String cargaElectrica(){
        float x = Float.parseFloat(entrada1.getText().toString());
        float y = Float.parseFloat(entrada2.getText().toString());
        float resultado = x * y;
        return escribe(resultado);
    }

    public String potencial(){
        float x = Float.parseFloat(entrada1.getText().toString());
        float y = Float.parseFloat(entrada2.getText().toString());
        float resultado = x / y;
        return escribe(resultado);
    }

    public String resistencia(){
        float x = Float.parseFloat(entrada1.getText().toString());
        float y = Float.parseFloat(entrada2.getText().toString());
        float resultado = x / y;
        return escribe(resultado);
    }
    public String conductancia(){
        float x = Float.parseFloat(entrada1.getText().toString());
        float resultado = 1 / x;
        return escribe(resultado);
    }

    public String capacitancia(){
        float x = Float.parseFloat(entrada1.getText().toString());
        float y = Float.parseFloat(entrada2.getText().toString());
        float resultado = x / y;
        return escribe(resultado);
    }
    public String flujoMagnetico(){
        float x = Float.parseFloat(entrada1.getText().toString());
        float y = Float.parseFloat(entrada2.getText().toString());
        float resultado = x * y;
        return escribe(resultado);
    }

    public String inductancia(){
        float x = Float.parseFloat(entrada1.getText().toString());
        float y = Float.parseFloat(entrada2.getText().toString());
        float z = Float.parseFloat(entrada3.getText().toString());
        float resultado = (x * y)/z;
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