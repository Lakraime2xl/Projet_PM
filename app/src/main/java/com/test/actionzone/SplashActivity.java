package com.test.actionzone;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    // Handler : c'est lui qui va gérer le timer de 2 secondes
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // On dit à Android quel fichier XML utiliser pour l'affichage
        setContentView(R.layout.activity_splash);

        // On crée le timer : après 2 secondes, on va vers MainActivity
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                // Créer l'intention d'aller vers MainActivity
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                // finish() empêche de revenir au Splash avec le bouton Retour
                finish();
            }
        };
        // postDelayed lance le runnable après 2000 millisecondes = 2 secondes
        handler.postDelayed(runnable, 2000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Si l'utilisateur quitte l'app pendant le Splash,
        // on annule le timer pour éviter un problème
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}