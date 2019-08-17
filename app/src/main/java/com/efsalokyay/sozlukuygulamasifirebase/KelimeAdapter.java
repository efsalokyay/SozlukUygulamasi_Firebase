package com.efsalokyay.sozlukuygulamasifirebase;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class KelimeAdapter extends RecyclerView.Adapter<KelimeAdapter.CardTasarimTutucu> {

    private Context mContext;
    private List<Kelimeler> kelimelerListe;

    public KelimeAdapter(Context mContext, List<Kelimeler> kelimelerListe) {
        this.mContext = mContext;
        this.kelimelerListe = kelimelerListe;
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_sozluk, viewGroup, false);

        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu cardTasarimTutucu, int i) {

        final Kelimeler kelime = kelimelerListe.get(i);

        cardTasarimTutucu.textView_ingilizce.setText(kelime.getIngilizce());
        cardTasarimTutucu.textView_turkce.setText(kelime.getTurkce());
        cardTasarimTutucu.kelime_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetayActivity.class);
                intent.putExtra("nesne", kelime);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return kelimelerListe.size();
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder {

        private TextView textView_ingilizce;
        private TextView textView_turkce;
        private CardView kelime_card;

        public CardTasarimTutucu(@NonNull View itemView) {
            super(itemView);

            kelime_card = itemView.findViewById(R.id.kelime_card);
            textView_ingilizce = itemView.findViewById(R.id.textView_ingilizce);
            textView_turkce = itemView.findViewById(R.id.textView_turkce);
        }
    }
}
