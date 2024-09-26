package com.prospect.myexpensive;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.prospect.myexpensive.data.models.Language;
import com.prospect.myexpensive.databinding.FragmentAddLanguageBinding;
import com.prospect.myexpensive.ui.languages.AddLanguageViewModel;

import java.io.IOException;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@AndroidEntryPoint

public class AddLanguageFragment extends Fragment {

    private FragmentAddLanguageBinding binding; // Update to use your generated binding class
    private AddLanguageViewModel mViewModel;

    public static AddLanguageFragment newInstance() {
        return new AddLanguageFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAddLanguageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize the ViewModel using Hilt's ViewModelProvider
        mViewModel = new ViewModelProvider(this).get(AddLanguageViewModel.class);

        // Handle button click to save the new language
        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String languageName = binding.editTextLanguageName.getText().toString().trim();
                if (!languageName.isEmpty()) {
                    createLanguage(languageName);
                } else {
                    Toast.makeText(getContext(), "Please enter a language name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void createLanguage(String languageName) {
        Language newLanguage = new Language(); // Create a new Language object
        newLanguage.setName(languageName); // Set the name of the language

        // Call the repository method to create the language
        // Use the ViewModel, e.g., calling createLanguage
        mViewModel.createLanguage(newLanguage).enqueue(new Callback<Language>() {
            @Override
            public void onResponse(Call<Language> call, Response<Language> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(getContext(), "Language added successfully!", Toast.LENGTH_SHORT).show();
                    // Optionally navigate back or clear the input field
                    binding.editTextLanguageName.setText(""); // Clear input field
                } else {
                    try {
                        // Print error message if response is not successful
                        String errorMessage = response.errorBody().string();
                        Toast.makeText(getContext(), "Error: " + errorMessage, Toast.LENGTH_LONG).show();
                        // You can also log the error message for debugging purposes
                        Log.e("AddLanguage", "Error response: " + errorMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "Error parsing the error response", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Language> call, Throwable t) {
                // Print the network error message
                Toast.makeText(getContext(), "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                // You can also log the failure for debugging
                Log.e("AddLanguage", "Failure: " + t.getMessage());
            }
        });

    }
}