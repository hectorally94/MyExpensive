package com.prospect.myexpensive.adapters.langue;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.prospect.myexpensive.R;
import com.prospect.myexpensive.data.models.Language;

import java.util.List;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder> {

    private List<Language> languages;
    private NavController navController;
    private OnLanguageDeleteListener deleteListener;

    // Step 1: Define the interface
    public interface OnLanguageDeleteListener {
        void onLanguageDelete(String languageId); // Method to handle delete action
    }

    public LanguageAdapter(List<Language> languages, NavController navController, OnLanguageDeleteListener listener) {
        this.languages = languages;
        this.navController = navController;
        this.deleteListener = listener;
    }

    @NonNull
    @Override
    public LanguageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_language, parent, false);
        return new LanguageViewHolder(view, navController);
    }

    @Override
    public void onBindViewHolder(@NonNull LanguageViewHolder holder, int position) {
        Language language = languages.get(position);
        holder.bind(language);
    }

    @Override
    public int getItemCount() {
        return languages != null ? languages.size() : 0;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
        notifyDataSetChanged();
    }

    class LanguageViewHolder extends RecyclerView.ViewHolder {

        private final TextView textLanguageName;
        private final ImageView iconEdit, iconDelete;

        public LanguageViewHolder(@NonNull View itemView, NavController navController) {
            super(itemView);
            textLanguageName = itemView.findViewById(R.id.textLanguageName);
            iconEdit = itemView.findViewById(R.id.iconEdit);
            iconDelete = itemView.findViewById(R.id.iconDelete);
        }

        public void bind(Language language) {
            textLanguageName.setText(language.getName());

            // Set click listener on the edit icon
            iconEdit.setOnClickListener(v -> {
                // Pass the current language object or ID to the EditFragment
                Bundle bundle = new Bundle();
                bundle.putParcelable("languageModel", language); // No need for casting if Language implements Parcelable
                navController.navigate(R.id.action_FirstFragment_to_editLanguage, bundle);
            });

            // Set click listener on the delete icon
            iconDelete.setOnClickListener(v -> {
                String languageId = language.getId(); // Get the ID of the language to delete
                if (deleteListener != null) {
                    deleteListener.onLanguageDelete(languageId); // Notify the listener
                }
            });
        }
    }
}