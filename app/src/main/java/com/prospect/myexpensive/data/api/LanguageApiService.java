package com.prospect.myexpensive.data.api;


import com.prospect.myexpensive.data.models.Language;
import com.prospect.myexpensive.data.models.LanguagesResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface LanguageApiService {

    @GET("languagesAll")
    Call<LanguagesResponse> getAllLanguages();

    @GET("languages/{id}")
    Call<LanguagesResponse> getLanguageById(@Path("id") String id);

    @POST("languages")
    Call<Language> createLanguage(@Body Language newLanguage);

    @PUT("languages/{id}")
    Call<Language> updateLanguage(@Path("id") String id, @Body Language language);

    @DELETE("languages/{id}")
    Call<Void> deleteLanguage(@Path("id") String id);
}
