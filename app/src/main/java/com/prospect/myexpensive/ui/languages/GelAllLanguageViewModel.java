package com.prospect.myexpensive.ui.languages;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.prospect.myexpensive.data.models.Language;
import com.prospect.myexpensive.data.models.LanguagesResponse;
import com.prospect.myexpensive.data.repository.LanguageRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class GelAllLanguageViewModel extends ViewModel {

    private final LanguageRepository languageRepository;
    private final MutableLiveData<List<Language>> languages = new MutableLiveData<>();
    private boolean isFetched = false; // Flag to check if data has been fetched

    @Inject
    public GelAllLanguageViewModel(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public LiveData<List<Language>> getAllLanguages() {
        if (!isFetched) { // Check if the data is already fetched
            fetchLanguages();
        }
        return languages;
    }

    private void fetchLanguages() {
        Call<LanguagesResponse> call = languageRepository.getAllLanguages();
        call.enqueue(new Callback<LanguagesResponse>() {
            @Override
            public void onResponse(Call<LanguagesResponse> call, Response<LanguagesResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    languages.setValue(response.body().getData());
                    isFetched = true; // Mark as fetched
                } else {
                    languages.setValue(null); // Optionally, set an error message here
                }
            }

            @Override
            public void onFailure(Call<LanguagesResponse> call, Throwable t) {
                languages.setValue(null); // Handle failure
                Log.e("LanguageViewModel", "Error fetching languages", t);
            }
        });
    }


    public Call<Void> deleteLanguage(String id) {
        return languageRepository.deleteLanguage(id);
    }

}
