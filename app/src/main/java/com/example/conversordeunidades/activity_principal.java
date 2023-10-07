package com.example.conversordeunidades;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class activity_principal extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private electricidadFragment fragmentoElectridad;
    private longitudFragment fragmentoLongitud;
    private quimicaFragment fragmentoQuimica;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tabLayout);

        fragmentoElectridad = new electricidadFragment();
        fragmentoLongitud = new longitudFragment();
        fragmentoQuimica = new quimicaFragment();

        tabLayout.setupWithViewPager(viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),0);

        viewPagerAdapter.addFragments(fragmentoElectridad, "Electricidad");
        viewPagerAdapter.addFragments(fragmentoLongitud, "Longitud");
        viewPagerAdapter.addFragments(fragmentoQuimica, "Química");
        viewPager.setAdapter(viewPagerAdapter);
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