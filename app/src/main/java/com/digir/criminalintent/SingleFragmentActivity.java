package com.digir.criminalintent;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public abstract class SingleFragmentActivity extends AppCompatActivity {
    //Klasa anonimowa do tworzenia pojedynczego fragmentu
    protected abstract Fragment createFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment); //Uniwersalny kontener dla fragmentu
        FragmentManager fm = getSupportFragmentManager();   //Utworzenie menadzera fragmentow
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);   //Wyszukanie tego samego co 2 linijki wyzej FrameLayout

        if(fragment == null) {  //Jesli fragment nie istnieje
            fragment = createFragment();    //Utworz fragment

            //179P.
            fm.beginTransaction()   //Rozpoczni tranzakcji, czyli dodanie FrameLayout i polaczenie z fragmentem oraz wykonanie
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
