package com.example.conversordeunidades;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class activity_principal extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private electricidadFragment fragmentoElectridad;
    private longitudFragment fragmentoLongitud;
    private fisicaFragment fragmentoFisica;
    private FloatingActionButton btnContacto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tabLayout);
        btnContacto = findViewById(R.id.botonFlotante);

        btnContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_principal.this, activity_contacto.class);
                startActivity(intent);
            }
        });

        fragmentoElectridad = new electricidadFragment();
        fragmentoLongitud = new longitudFragment();
        fragmentoFisica = new fisicaFragment();

        tabLayout.setupWithViewPager(viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),0);

        viewPagerAdapter.addFragments(fragmentoElectridad, "Electricidad");
        viewPagerAdapter.addFragments(fragmentoLongitud, "Longitud");
        viewPagerAdapter.addFragments(fragmentoFisica, "Física");
        viewPager.setAdapter(viewPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_desplegable, menu);
        return true;
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments = new ArrayList<>();
        private List<String> nombresFragmentos = new ArrayList<>();
        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void addFragments(Fragment fragment, String nombre){
            fragments.add(fragment);
            nombresFragmentos.add(nombre);

        }
        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return nombresFragmentos.get(position);
        }
    }
}