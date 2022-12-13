package com.lizyaver.lizyaverorg.ui.uncles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.lizyaver.lizyaverorg.databinding.FragmentUnclesBinding;

public class UnclesFragment extends Fragment {

    private FragmentUnclesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        UncleViewModel homeViewModel =
                new ViewModelProvider(this).get(UncleViewModel.class);

        binding = FragmentUnclesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}