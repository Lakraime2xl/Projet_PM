package com.test.actionzone;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private ImageView imgAffiche;
    private TextView tvTitre, tvNote, tvSynopsis;
    private Spinner spinnerStatut;
    private RatingBar ratingBar;
    private EditText etAvis;
    private Button btnAjouter;

    // Le film reçu depuis RechercheActivity
    private Film film;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // On relie les éléments XML au Java
        imgAffiche = findViewById(R.id.imgAfficheDetail);
        tvTitre = findViewById(R.id.tvTitreDetail);
        tvNote = findViewById(R.id.tvNoteDetail);
        tvSynopsis = findViewById(R.id.tvSynopsisDetail);
        spinnerStatut = findViewById(R.id.spinnerStatut);
        ratingBar = findViewById(R.id.ratingBar);
        etAvis = findViewById(R.id.etAvis);
        btnAjouter = findViewById(R.id.btnAjouter);

        // On récupère les données envoyées depuis RechercheActivity
        String titre = getIntent().getStringExtra("titre");
        String synopsis = getIntent().getStringExtra("synopsis");
        String affiche = getIntent().getStringExtra("affiche");
        double note = getIntent().getDoubleExtra("note", 0);

        // On crée l'objet Film
        film = new Film(titre, synopsis, affiche, note);

        // On affiche les données du film
        tvTitre.setText(titre);
        tvNote.setText("Note TMDB : " + note);
        tvSynopsis.setText(synopsis);

        // On charge l'affiche avec Glide
        String urlAffiche = "https://image.tmdb.org/t/p/w500" + affiche;
        Glide.with(this).load(urlAffiche).into(imgAffiche);

        // On remplit le Spinner avec les statuts possibles
        String[] statuts = {"A voir", "En cours", "Vu"};
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                statuts
        );
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatut.setAdapter(adapterSpinner);

        // Quand on clique sur Ajouter
        btnAjouter.setOnClickListener(v -> {
            // On récupère les données personnelles
            film.statut = spinnerStatut.getSelectedItem().toString();
            film.maNote = ratingBar.getRating();
            film.avis = etAvis.getText().toString().trim();

            // On sauvegarde le film dans le fichier
            GestionFichier.ajouterFilm(this, film);

            Toast.makeText(this, film.titre + " ajouté à ta collection !", Toast.LENGTH_SHORT).show();

            // On ferme DetailActivity et on retourne à MainActivity
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}