package com.example.reading_book;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchResultActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    subjectAdapter adapter;
    ArrayList<subjectModel> searchResults;
    String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        recyclerView = findViewById(R.id.recyclerSearchResults);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        query = getIntent().getStringExtra("query");

        // Lấy danh sách sách từ MainActivity (có thể sử dụng database)
        ArrayList<subjectModel> fullList = new ArrayList<>();
        fullList.add(new subjectModel("Beginning Android Programming with Android Studio","file:///android_asset/image/bg_1.png"));
        fullList.add(new subjectModel("Object oriented programming using java","file:///android_asset/image/bg_2.png"));
        fullList.add(new subjectModel("Giao trinh CTDLGT tham khao","file:///android_asset/image/bg_3.png"));

        // Lọc sách theo từ khóa tìm kiếm
        searchResults = new ArrayList<>();
        for (subjectModel item : fullList) {
            if (item.getSubjectName().toLowerCase().contains(query.toLowerCase())) {
                searchResults.add(item);
            }
        }

        adapter = new subjectAdapter(searchResults, this);
        recyclerView.setAdapter(adapter);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(SearchResultActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish(); // Đóng trang hiện tại
            }
        });

    }

}