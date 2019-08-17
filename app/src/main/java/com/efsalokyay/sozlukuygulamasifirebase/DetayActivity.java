package com.efsalokyay.sozlukuygulamasifirebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetayActivity extends AppCompatActivity {

    private TextView textView_ingilizce;
    private TextView textView_turkce;

    private Kelimeler kelime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        textView_ingilizce = findViewById(R.id.detay_textView_ingilizce);
        textView_turkce = findViewById(R.id.detay_textView_turkce);

        kelime = (Kelimeler) getIntent().getSerializableExtra("nesne");

        textView_ingilizce.setText(kelime.getIngilizce());
        textView_turkce.setText(kelime.getTurkce());

    }
}
