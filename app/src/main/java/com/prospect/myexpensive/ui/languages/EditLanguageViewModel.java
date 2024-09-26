package com.prospect.myexpensive.ui.languages;

import androidx.lifecycle.ViewModel;

import com.prospect.myexpensive.data.models.Language;
import com.prospect.myexpensive.data.repository.LanguageRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;

@HiltViewModel
public class EditLanguageViewModel extends ViewModel {
     final LanguageRepository languageRepository;

    // Hilt will inject LanguageRepository into the ViewModel
    @Inject
    public EditLanguageViewModel(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }
    // Expose a method that interacts with the repository
    public Call<Language> updateLanguage(String id, Language language) {
        return languageRepository.updateLanguage(id,language);
    }
}