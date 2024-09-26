package com.prospect.myexpensive.di;

import com.prospect.myexpensive.data.api.ApiClient;
import com.prospect.myexpensive.data.api.LanguageApiService;
import com.prospect.myexpensive.data.repository.LanguageRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    public LanguageApiService provideLanguageApiService() {
        return ApiClient.getRetrofitInstance().create(LanguageApiService.class);
    }

    @Provides
    public LanguageRepository provideLanguageRepository(LanguageApiService languageApiService) {
        return new LanguageRepository(languageApiService);
    }
}
