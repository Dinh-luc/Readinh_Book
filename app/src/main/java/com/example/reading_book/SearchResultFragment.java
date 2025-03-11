package com.example.reading_book;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class SearchResultFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<subjectModel> filteredList;
    subjectAdapter adapter,suggestionAdapter;
    private String query;

    public SearchResultFragment(String query) {
        this.query = query;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);

        recyclerView = view.findViewById(R.id.recyclerSearchResult);
        filteredList = new ArrayList<>();
        adapter = new subjectAdapter(filteredList, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        suggestionAdapter = new subjectAdapter(filteredList, getContext());
        recyclerView.setAdapter(suggestionAdapter);

        suggestionAdapter.setOnItemClickListener(item -> {
            // Khi click vào item -> load SearchResultFragment
            ((MainActivity) getActivity()).loadFragment(new SearchResultFragment(item.getSubjectName()));
        });

        searchBooks(query);
        return view;

    }

    private void searchBooks(String query) {
        ArrayList<subjectModel> mainList = ((MainActivity) getActivity()).getList();
        for (subjectModel item : mainList) {
            if (item.getSubjectName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.notifyDataSetChanged();
    }
}