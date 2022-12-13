package com.lizyaver.lizyaverorg.ui.aunts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.lizyaver.lizyaverorg.databinding.FragmentAuntsBinding;

public class AuntsFragment extends Fragment {

    private FragmentAuntsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AuntsViewModel homeViewModel =
                new ViewModelProvider(this).get(AuntsViewModel.class);

        binding = FragmentAuntsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAunt;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}