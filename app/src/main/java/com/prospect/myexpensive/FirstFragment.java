package com.prospect.myexpensive;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.prospect.myexpensive.adapters.langue.LanguageAdapter;
import com.prospect.myexpensive.data.models.Language;
import com.prospect.myexpensive.databinding.FragmentFirstBinding;
import com.prospect.myexpensive.ui.languages.GelAllLanguageViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@AndroidEntryPoint
public class FirstFragment extends Fragment implements LanguageAdapter.OnLanguageDeleteListener {

    private FragmentFirstBinding binding;
    private GelAllLanguageViewModel languageViewModel;
    private LanguageAdapter languageAdapter;

    private List<Language> languages; // Store the list of languages

    public FirstFragment() {
        // Default constructor required for fragments
        languageViewModel = null; // Initialize as null, will be set by Hilt
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Use the viewModels() delegate to inject the ViewModel
        languageViewModel = new ViewModelProvider(this).get(GelAllLanguageViewModel.class);

        // Get NavController
        NavController navController = Navigation.findNavController(requireView());

        // Initialize RecyclerView and Adapter with the delete listener
        languageAdapter = new LanguageAdapter(null, navController, this); // Start with null data
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(languageAdapter);

        // For progress bar
        binding.progressBar.setVisibility(View.VISIBLE);

        // Observe LiveData
        languageViewModel.getAllLanguages().observe(getViewLifecycleOwner(), languages -> {
            this.languages = languages; // Store the languages list
            if (languages != null) {
                binding.progressBar.setVisibility(View.GONE);
                Log.d("YourTag", "Languages fetched: " + languages.size());
                // Update the adapter with fetched languages
                languageAdapter.setLanguages(languages);
                binding.textviewFirst.setText(getString(R.string.languages_fetched, languages.size()));

            } else {
                Log.d("YourTag", "Languages list is null");
                // Optionally, set a default message if languages are null
                binding.textviewFirst.setText(getString(R.string.no_languages_available));
                binding.progressBar.setVisibility(View.GONE);
            }
        });

        binding.buttonFirst.setOnClickListener(v ->
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment)
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // Implement the delete method from the interface
    @Override
    public void onLanguageDelete(String languageId) {
        // Call the ViewModel to delete the language
        languageViewModel.deleteLanguage(languageId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Language deleted successfully", Toast.LENGTH_LONG).show();

                    // Remove the item from the list and notify the adapter
                    for (int i = 0; i < languages.size(); i++) {
                        if (languages.get(i).getId().equals(languageId)) {
                            languages.remove(i);
                            languageAdapter.notifyItemRemoved(i);
                            break; // Exit loop after removal
                        }
                    }

                    // Optionally refresh the UI text
                    binding.textviewFirst.setText(getString(R.string.languages_fetched, languages.size()));
                } else {
                    Toast.makeText(getContext(), "Failed to delete language", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}