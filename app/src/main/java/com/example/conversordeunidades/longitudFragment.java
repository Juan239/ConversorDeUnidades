package com.example.conversordeunidades;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class longitudFragment extends Fragment {
    Spinner spinnerLongitud, spinnerGrados;
    EditText valorEntradaLongitud, valorEntradaGrados;
    TextView resMilimetros, resCentimetros, resMetros, resKilometros, resCentesimal, resSexagesimal;
    ScrollView scrollView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_longitud, container, false);

        spinnerLongitud = rootView.findViewById(R.id.spinnerLongitud);
        spinnerGrados = rootView.findViewById(R.id.spinnerGrados);

        valorEntradaLongitud = rootView.findViewById(R.id.valorEntrada);
        valorEntradaGrados = rootView.findViewById(R.id.valorEntradaGrados);

        resMilimetros = rootView.findViewById(R.id.resultadoMilimetros);
        resCentimetros = rootView.findViewById(R.id.resultadoCentimetros);
        resMetros = rootView.findViewById(R.id.resultadoMetros);
        resKilometros = rootView.findViewById(R.id.resultadoKilometros);

        resCentesimal = rootView.findViewById(R.id.resultadoCentesimal);
        resSexagesimal = rootView.findViewById(R.id.resultadoSexagesimal);


        String[] valoresLongitud = {"Milímetros", "Centímetros", "Metros", "Kilometros"};
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(valoresLongitud));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.style_spinner, arrayList);
        spinnerLongitud.setAdapter(arrayAdapter);

        String[] valoresGrados = {"Centesimales", "Sexagesimales"};
        ArrayList<String> arrayList2 = new ArrayList<>(Arrays.asList(valoresGrados));
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(getActivity(), R.layout.style_spinner, arrayList2);
        spinnerGrados.setAdapter(arrayAdapter2);

        valorEntradaLongitud.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                String opcion = spinnerLongitud.getSelectedItem().toString();
                try {
                    Float valor = Float.parseFloat(valorEntradaLongitud.getText().toString());
                        if(opcion.equals("Milímetros")){
                            resMilimetros.setText(escribe(igualdad(valor)));
                            resCentimetros.setText(escribe(milimetro_a_centimetro(valor)));
                            resMetros.setText(escribe(milimetro_a_metro(valor)));
                            resKilometros.setText(Float.toString(milimetro_a_kilometro(valor)));
                        } else if (opcion.equals("Centímetros")) {
                            resMilimetros.setText(escribe(centimetro_a_milimetro(valor)));
                            resCentimetros.setText(escribe(igualdad(valor)));
                            resMetros.setText(escribe(centimetro_a_metro(valor)));
                            resKilometros.setText(escribe(centimetro_a_kilometro(valor)));
                        } else if (opcion.equals("Metros")) {
                            resMilimetros.setText(escribe(metro_a_milimetro(valor)));
                            resCentimetros.setText(escribe(metro_a_centimetro(valor)));
                            resMetros.setText(escribe(igualdad(valor)));
                            resKilometros.setText(escribe(metro_a_kilometro(valor)));
                        } else if (opcion.equals("Kilometros")) {
                            resMilimetros.setText(escribe(kilometro_a_milimetro(valor)));
                            resCentimetros.setText(escribe(kilometro_a_centimetro(valor)));
                            resMetros.setText(escribe(kilometro_a_metro(valor)));
                            resKilometros.setText(escribe(igualdad(valor)));
                        }
                }catch (Exception e){
                    System.out.println(e);
                }
                return false;
            }
        });

        valorEntradaGrados.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                String opcion2 = spinnerGrados.getSelectedItem().toString();
                try {
                    Float valor = Float.parseFloat(valorEntradaGrados.getText().toString());
                    if(opcion2.equals("Centesimales")){
                        resCentesimal.setText(escribe(igualdad(valor)));
                        resSexagesimal.setText(escribe(cen_a_sex(valor)));
                    } else if (opcion2.equals("Sexagesimales")) {
                        resCentesimal.setText(escribe(sex_a_cen(valor)));
                        resSexagesimal.setText(escribe(igualdad(valor)));
                    }
                }catch (Exception e){
                    System.out.println(e);
                }

                return false;
            }
        });

        return rootView;
    }

    //Operaciones con milimetros
    public Float milimetro_a_centimetro(float x) {
        float resultado = x / 10;
        return resultado;
    }

    public Float milimetro_a_metro(float x) {
        float resultado = x / 1000;
        return resultado;
    }

    public Float milimetro_a_kilometro(float x) {
        float resultado = x / 1000000;
        return resultado;
    }

    //Operaciones con centímetros
    public Float centimetro_a_milimetro(float x) {
        float resultado = x * 10;
        return resultado;
    }

    public Float centimetro_a_metro(float x) {
        float resultado = x / 100;
        return resultado;
    }

    public Float centimetro_a_kilometro(float x) {
        float resultado = x / 100000;
        return resultado;
    }

    //Operaciones con metros
    public Float metro_a_milimetro(float x) {
        float resultado = x / 100000;
        return resultado;
    }

    public Float metro_a_centimetro(float x) {
        float resultado = x * 100;
        return resultado;
    }

    public Float metro_a_kilometro(float x) {
        float resultado = x * 1000;
        return resultado;
    }
    //Operaciones con kilometros
    public Float kilometro_a_milimetro(float x){
        float resultado = x * 1000000;
        return resultado;
    }
    public Float kilometro_a_centimetro(float x){
        float resultado = x * 100000;
        return resultado;
    }
    public Float kilometro_a_metro(float x){
        float resultado = x * 1000;
        return resultado;
    }
    //Operaciones con grados
    public Float cen_a_sex(float x){
        float resultado = x*(180/200F);
        return resultado;
    }
    public Float sex_a_cen(float x){
        float resultado = x*(200/180F);
        return resultado;
    }
    //Caso de igualdad
    public Float igualdad(float x){
        return x;
    }
    static String escribe(double d) {
        NumberFormat nf = new DecimalFormat("##.####");
        return (nf.format(d).replace(",","."));
    }
    public void reestablecerLongitudes(){
        resMilimetros.setText("0");
        resCentimetros.setText("0");
        resMetros.setText("0");
        resKilometros.setText("0");
    }
    public void reestablecerGrados(){
        resCentesimal.setText("0");
        resSexagesimal.setText("0");
    }
}