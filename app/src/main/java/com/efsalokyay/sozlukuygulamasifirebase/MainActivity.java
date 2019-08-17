package com.efsalokyay.sozlukuygulamasifirebase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ArrayList<Kelimeler> kelimelerArrayList;
    private KelimeAdapter adapter;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.main_toolbar);
        recyclerView = findViewById(R.id.main_recylerview);

        toolbar.setTitle("Sözlük");
        setSupportActionBar(toolbar);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("kelimeler");

        kelimelerArrayList = new ArrayList<>();
        adapter = new KelimeAdapter(this, kelimelerArrayList);
        recyclerView.setAdapter(adapter);

        tumKelimeler();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_ara);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        Log.e("onQueryTextSubmit", s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        Log.e("onQueryTextChange", s);
        kelimeArama(s);
        return false;
    }

    public void tumKelimeler() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                kelimelerArrayList.clear();

                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    Kelimeler kelime = d.getValue(Kelimeler.class);
                    kelime.setKelime_id(d.getKey());

                    kelimelerArrayList.add(kelime);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void kelimeArama(final String aranan_kelime) {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                kelimelerArrayList.clear();

                for (DataSnapshot d : dataSnapshot.getChildren()) {

                    Kelimeler kelime = d.getValue(Kelimeler.class);

                    if (kelime.getIngilizce().contains(aranan_kelime)) {
                        kelime.setKelime_id(d.getKey());
                        kelimelerArrayList.add(kelime);
                    }
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
