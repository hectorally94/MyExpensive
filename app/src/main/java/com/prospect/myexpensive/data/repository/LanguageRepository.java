package com.prospect.myexpensive.data.repository;

import com.prospect.myexpensive.data.api.LanguageApiService;
import com.prospect.myexpensive.data.models.Language;
import com.prospect.myexpensive.data.models.LanguagesResponse;

import retrofit2.Call;

/**
 * Repository class for managing language data.
 */
public class LanguageRepository {
    private final LanguageApiService languageApiService;

     // Constructor that accepts LanguageApiService
    public LanguageRepository(LanguageApiService languageApiService) {
        this.languageApiService = languageApiService;
    }

    /**
     * Fetches all languages from the API.
     * @return Call<List<Language>> - A Retrofit Call object for a list of languages.
     */
    public Call<LanguagesResponse> getAllLanguages() {
        return languageApiService.getAllLanguages();
    }

    /**
     * Fetches a specific language by its ID.
     * @param id The ID of the language to fetch.
     * @return Call<Language> - A Retrofit Call object for the requested language.
     */
    public Call<LanguagesResponse> getLanguageById(String id) {
        return languageApiService.getLanguageById(id);
    }

    /**
     * Creates a new language.
     * @param newLanguage The Language object to create.
     * @return Call<Language> - A Retrofit Call object for the created language.
     */
    public Call<Language> createLanguage(Language newLanguage) {
        return languageApiService.createLanguage(newLanguage);
    }

    /**
     * Updates an existing language.
     * @param id The ID of the language to update.
     * @param language The updated Language object.
     * @return Call<Language> - A Retrofit Call object for the updated language.
     */
    public Call<Language> updateLanguage(String id, Language language) {
        return languageApiService.updateLanguage(id, language);
    }

    /**
     * Deletes a language by its ID.
     * @param id The ID of the language to delete.
     * @return Call<Void> - A Retrofit Call object for the delete operation.
     */
    public Call<Void> deleteLanguage(String id) {
        return languageApiService.deleteLanguage(id);
    }
}
