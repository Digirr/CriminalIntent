package com.digir.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
    //To juz jest kontroler
public class CrimeListFragment extends Fragment {   //Tak tak, to jest fragment od CrimeListFragment
    //Tu jest kod calego fragmentu jak ma wygladac
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    private Crime mCrime;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { //To wykona sie jako pierwsze bo to onCreateView dla fragmentu
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        //LayoutManager to po prostu obiekt niezbedny do prawidlowego funkcjonowania RecyclerView
        //LayoutManager umieszcza elementy w odpowiednich miejscach oraz okresla jak dziala przewijanie ekranu, bez niego RecyclerView ulegnie awarii
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;    //Zwraca widok juz bezposrednio do managera fragmentow
    }

        @Override
        public void onResume() {
            super.onResume();
            updateUI();
        }

        private void updateUI() {   //Tutaj odpalany jest Adapter, z ktorego odpalany jest Holder
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        if(mAdapter == null) {
            mAdapter = new CrimeAdapter(crimes);    //Przekazywane sa dane z warstwy modelu
            mCrimeRecyclerView.setAdapter(mAdapter);    //Polaczenie kontenera RecicleView z adapterem
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }
    //Typowa podklasa ViewHolder
//        public class ListRow extends RecyclerView.ViewHolder {
//            public ImageView mThumbnail;  - nastepnie moge utworzyc instancje ListRow i pobierac wartosci zmiennej mThumbnail
//            public ListRow(View view) {   - oraz itemView, czyli pola, ktore wyznacza superklasa RecyclerView.ViewHolder
//                super(view);  - pole itemView jest powodem, dla ktorego istnieje klasa ViewHolder, przechowuje odwolanie do obiektu View,
//                mThumbnail = (ImageView) view.findViewById(R.id.thumbnail);   - ktory przekazuje do klasy nadrzednej super(view)
//            }
//        }

        //Typowe zastosowanie klasy ViewHolder
//        ListRow row = new ListRow(inflater.inflate(R.layout.list_row, parent, false));
//        View view = row.itemView;
//        ImageView thumbnailView = row.mThumbnail;
    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{  //Klasa ViewHolder wykonuje jedno zadanie - przechowuje obiekt View
        //Kontener RecyclerView jest odpowiedzialny za recykling widokow TextView
        private Crime mCrime;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mSolvedImageView;

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime, parent, false));  //Tworzony jest pojedynczy obiekt pliku xml dla obiektu listy
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title); //?
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date); //?
            mSolvedImageView = (ImageView) itemView.findViewById(R.id.crime_solved);
        }
        public void bind(Crime crime) { //Wywolane z Adaptera przez onBindViewHolder
            mCrime = crime; //Wypelnianie danymi z modelu i umieszczenie na ekranie
            mTitleTextView.setText(mCrime.getTitle());


            String pattern = "EEEE, dd LLLL yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(mCrime.getDate());

            mDateTextView.setText(date);
            mSolvedImageView.setVisibility(crime.isSolved() ? View.VISIBLE : View.GONE);
        }

        @Override
        public void onClick(View view) {
            Intent intent = CrimeActivity.newIntent(getActivity(), mCrime.getId());
            startActivity(intent);
//            Toast.makeText(getActivity(),
//                    mCrime.getTitle() + " klik!", Toast.LENGTH_SHORT)
//                    .show();
        }
    }
    //Adapter jest odpowiedzialny za:
        //Tworzenie niezbednych obiektow ViewHolder
        //Wiazanie obiektow ViewHolder z danymi znajdujacymi sie w warstwie modelu
        //Klasa ta opakowuje liste popelnionych przestepstw pobieranych z obiektu CrimeLab - singletonu
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {  //Klasa Adapter spelnia role kontrolera znajdujacego sie miedzy kontenerem RecyclerView a zestawem danych,
        //ktore RecyclerView powinien wyswietlac
        //Adapter tworzy instancje klasy ViewHolder
        private List<Crime> mCrimes;
        public CrimeAdapter(List<Crime> crimes) {   //Dostaje liste wszystkich przestepstw na dzien dobry
            mCrimes = crimes;
        }

        //onCreateView jest wywolywany przez RecyclerView kiedy potrzebny jest nowy obiekt ViewHolder do wyswietlenia kolejnego elementu listy
        @Override   //Na start jest wywolywany 10 razy i potem wiecej jak ktos scrolluje - oszczednosc pamieci
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) { //Tutaj tworzona jest instancja klasy ViewHolder
            //Jest napisane ze LayoutInflanter jest po prostu uzyty do utworzenia nowego obiektu CrimeHolder
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity()); //Adapter jest utworzony w createView glownej glownej klasy fragmentu
            return new CrimeHolder(layoutInflater, parent); //RecyclerView potrzebuje obiektu View
        }

//        @Override
//        public int getItemViewType(int position) {
//            return super.getItemViewType(position);
//        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {    //RecyclerView wywoluje ta metode i przekazuje obiekt ViewHolder wraz z jego pozycja
            //Adapter poszukuje danych modelu dla tej pozycji i wiaze ja z odpowiednim widokiem obiektu ViewHolder
            //Aby wykonac takie dowiazanie, adapter wypelnia obiekt View danymi z warstwy modelu
            Crime crime = mCrimes.get(position);
            holder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }
    //Ogolnie obiekty ViewHolder i Adapter ze soba mocno wspolpracuja
        //Holder oraz Adapter wspolnie tworza obiekty listy
}
