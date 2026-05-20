package com.test.actionzone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {

    private Context context;
    private ArrayList<Film> listeFilms;

    public interface OnFilmClickListener {
        void onFilmClick(Film film);
    }

    private OnFilmClickListener listener;

    public FilmAdapter(Context context, ArrayList<Film> listeFilms, OnFilmClickListener listener) {
        this.context = context;
        this.listeFilms = listeFilms;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vue = LayoutInflater.from(context).inflate(R.layout.item_film, parent, false);
        return new FilmViewHolder(vue);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
        Film film = listeFilms.get(position);

        holder.tvTitre.setText(film.titre);
        holder.tvNote.setText("Note : " + film.note);

        String urlAffiche = "https://image.tmdb.org/t/p/w500" + film.affiche;
        Glide.with(context).load(urlAffiche).into(holder.imgAffiche);

        holder.itemView.setOnClickListener(v -> listener.onFilmClick(film));

        holder.itemView.setOnLongClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle(film.titre)
                    .setItems(new String[]{"Supprimer", "Annuler"}, (dialog, which) -> {
                        if (which == 0) {
                            GestionFichier.supprimerFilm(context, position);
                            listeFilms.remove(position);
                            notifyDataSetChanged();
                        }
                    })
                    .show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return listeFilms.size();
    }

    public static class FilmViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAffiche;
        TextView tvTitre;
        TextView tvNote;

        public FilmViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAffiche = itemView.findViewById(R.id.imgAffiche);
            tvTitre = itemView.findViewById(R.id.tvTitre);
            tvNote = itemView.findViewById(R.id.tvNote);
        }
    }
}