package com.digir.criminalintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class CrimeListActivity extends SingleFragmentActivity {
//Klasa uruchamiana jako pierwsza, wykonuje klase anonimowa
    //Anonimowa utworzyla i zaimplementowala fragment
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment(); //Utworzenie tego konkretnego fragmentu
    }

}