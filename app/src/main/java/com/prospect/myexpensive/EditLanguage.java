package com.prospect.myexpensive;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.prospect.myexpensive.data.models.Language;
import com.prospect.myexpensive.databinding.FragmentEditLanguageBinding;
import com.prospect.myexpensive.ui.languages.EditLanguageViewModel;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@AndroidEntryPoint
public class EditLanguage extends Fragment {

    private EditLanguageViewModel mViewModel;
    private FragmentEditLanguageBinding binding;
    private Language languageParcable;

    public static EditLanguage newInstance() {
        return new EditLanguage();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentEditLanguageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(EditLanguageViewModel.class);

        // Retrieve the Language object from arguments
        if (getArguments() != null) {
            languageParcable = getArguments().getParcelable("languageModel");
            if (languageParcable != null) {
                // Set the EditText to the name of the passed Language object
                binding.editTextLanguageName.setText(languageParcable.getName());
            } else {
                Toast.makeText(getContext(), "Error retrieving language data", Toast.LENGTH_SHORT).show();
            }
        }

        // Set up the save button click listener
        binding.buttonSave.setOnClickListener(v -> {
            saveLanguage();
        });
    }

    private void saveLanguage() {
        // Get the updated name from the EditText
        String updatedName = binding.editTextLanguageName.getText().toString().trim();

        if (updatedName.isEmpty()) {
            Toast.makeText(getContext(), "Language name cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // Update the language object
        if (languageParcable != null) {
            languageParcable.setName(updatedName);

            // Call the ViewModel to save the updated language
            mViewModel.updateLanguage(languageParcable.getId(), languageParcable).enqueue(new Callback<Language>() {
                @Override
                public void onResponse(Call<Language> call, Response<Language> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getContext(), "Language updated successfully", Toast.LENGTH_SHORT).show();
                        // Optionally navigate back or clear fields
                        NavController navController= Navigation.findNavController(requireView()); // when you are trying to find navController within fragment

                        navController.navigateUp(); // Uncomment if you have a NavController
                    } else {
                        Toast.makeText(getContext(), "Failed to update language", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Language> call, Throwable t) {
                    Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}