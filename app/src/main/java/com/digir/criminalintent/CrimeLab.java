package com.digir.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {
    //Singleton

    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimes;
    public static CrimeLab get(Context context) {   //Publiczny get do utworzenia instancji klasy CrimeLab
        if(sCrimeLab == null) { //Wraz z przekazaniem kontekstu aktywnosci
            sCrimeLab = new CrimeLab(context);  //Jesli nie istnieje to utworz
        }
        return sCrimeLab;   //Jesli istnieje, to zwroc ten ktory istnieje
    }

    private CrimeLab(Context context) { //Utworzenie listy 100 spraw
        mCrimes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();  //Utworzenie 100 pojedynczych obiektow warstwy modelu
            crime.setTitle("Sprawa #" + i);
            crime.setSolved(i % 2 == 0); //Co drugi obiekt jest odfajkowany
            mCrimes.add(crime); //Dodanie do listy
        }
    }
    public List<Crime> getCrimes() {    //Zwraca obiekt listy zawierajacy liczbe przestepstw
        return  mCrimes;
    }
    public Crime getCrime(UUID id) {    //Zwraca obiekt crime o danym identyfikatorze
        for(Crime crime : mCrimes) {
            if(crime.getId().equals(id)) {
                return crime;
            }
        }
        return null;
    }

}
