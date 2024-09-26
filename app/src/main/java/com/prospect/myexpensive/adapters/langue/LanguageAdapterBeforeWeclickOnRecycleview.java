package com.prospect.myexpensive.adapters.langue;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prospect.myexpensive.R;
import com.prospect.myexpensive.data.models.Language;

import java.util.List;

public class LanguageAdapterBeforeWeclickOnRecycleview extends RecyclerView.Adapter<LanguageAdapterBeforeWeclickOnRecycleview.LanguageViewHolder> {

    private List<Language> languages;

    public LanguageAdapterBeforeWeclickOnRecycleview(List<Language> languages) {
        this.languages = languages;
    }

    @NonNull
    @Override
    public LanguageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_language, parent, false);
        return new LanguageViewHolder(view);
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
        notifyDataSetChanged(); // Notify the adapter that the data has changed
    }

    static class LanguageViewHolder extends RecyclerView.ViewHolder {

        private final TextView textLanguageName;

        public LanguageViewHolder(@NonNull View itemView) {
            super(itemView);
            textLanguageName = itemView.findViewById(R.id.textLanguageName);
        }

        public void bind(Language language) {
            textLanguageName.setText(language.getName()); // Assuming Language has a method getName()
        }
    }
}
