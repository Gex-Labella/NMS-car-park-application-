package com.lizyaver.lizyaverorg.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lizyaver.lizyaverorg.Contact;
import com.lizyaver.lizyaverorg.ContactsRecViewAdapter;
import com.lizyaver.lizyaverorg.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final RecyclerView contactRecView = binding.contactRecView;

        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Tom Mboya Street", "yabsmullo0@gmail.com",
                "40 parking spaces available",
                "https://cdn.standardmedia.co.ke/images/friday/fall_of_tom_mb5f644267c2e1d.jpg"));
        contacts.add(new Contact("Kenyatta Avenue", "yabsmullo0@gmail.com",
                "80 parking spaces available",
                "https://c8.alamy.com/comp/D3H10G/kenyatta-avenue-street-scene-nairobi-kenya-D3H10G.jpg"));
        contacts.add(new Contact("Moi Avenue", "yabsmullo0@gmail.com",
                "75 parking spaces available",
                "https://a4.pbase.com/u34/bmcmorrow/upload/41245526.nbofeb05040.jpg"));

        ContactsRecViewAdapter adapter = new ContactsRecViewAdapter(this.getActivity());
        adapter.setContacts(contacts);

        contactRecView.setAdapter(adapter);

        contactRecView.setLayoutManager(new GridLayoutManager(this.getActivity(), 2));


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}