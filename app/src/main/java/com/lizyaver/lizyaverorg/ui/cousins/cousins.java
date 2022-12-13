package com.lizyaver.lizyaverorg.ui.cousins;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.lizyaver.lizyaverorg.databinding.FragmentCousinsBinding;

public class cousins extends Fragment {

    private FragmentCousinsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CousinsViewModel cousinsViewModel =
                new ViewModelProvider(this).get(CousinsViewModel.class);

        binding = FragmentCousinsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}