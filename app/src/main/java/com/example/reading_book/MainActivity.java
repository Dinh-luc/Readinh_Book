package com.example.reading_book;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.widget.SearchView;

import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<subjectModel>list, filteredList;
    subjectAdapter adapter,suggestionAdapter;
    RecyclerView recyclerSuggestions;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageView menu,search,closeSearch;
    TextView txtAppName;
    SearchView searchView;
    View Header,searchBar;
    FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Toast.makeText(getApplicationContext(),"onCreate", Toast.LENGTH_SHORT).show();

        // ánh xạ id từ .xml sang .java
        toolbar = findViewById(R.id.toolbar);
        fragmentContainer = findViewById(R.id.fragmentContainer);
        menu = findViewById(R.id.imgMenu);
        search = findViewById(R.id.imgSearch);
        txtAppName = findViewById(R.id.txtAppName);
        drawerLayout = findViewById(R.id.main);
        navigationView = findViewById(R.id.navView);
        searchView = findViewById(R.id.searchView);
        searchBar = findViewById(R.id.searchBar);
        closeSearch = findViewById(R.id.imgCancel);
        recyclerSuggestions = findViewById(R.id.recyclerSuggestions);

        Toast.makeText(getApplicationContext(),"mapping", Toast.LENGTH_SHORT).show();

        // edit text trong searchview
        EditText searchEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(Color.BLACK);  // Đổi màu chữ thành đen
        searchEditText.setHintTextColor(Color.DKGRAY);  // Đổi màu chữ gợi ý (hint) thành xám

        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        list = new ArrayList<>();
        filteredList = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerSuggestions.setLayoutManager(new LinearLayoutManager(this));

        list.add(new subjectModel("Beginning Android Programming with Android Studio","file:///android_asset/image/bg_1.png"));
        list.add(new subjectModel("Object oriented programming using java","file:///android_asset/image/bg_2.png"));
        list.add(new subjectModel("Giao trinh CTDLGT tham khao","file:///android_asset/image/bg_3.png"));
        list.add(new subjectModel("Ly thuyet do thi","file:///android_asset/image/bg_4.png"));
        list.add(new subjectModel("Beginning Android Programming with Android Studio","file:///android_asset/image/bg_1.png"));
        list.add(new subjectModel("Object oriented programming using java","file:///android_asset/image/bg_2.png"));
        list.add(new subjectModel("Giao trinh CTDLGT tham khao","file:///android_asset/image/bg_3.png"));
        list.add(new subjectModel("Ly thuyet do thi","file:///android_asset/image/bg_4.png"));
        list.add(new subjectModel("Beginning Android Programming with Android Studio","file:///android_asset/image/bg_1.png"));
        list.add(new subjectModel("Object oriented programming using java","file:///android_asset/image/bg_2.png"));
        list.add(new subjectModel("Giao trinh CTDLGT tham khao","file:///android_asset/image/bg_3.png"));
        list.add(new subjectModel("Ly thuyet do thi","file:///android_asset/image/bg_4.png"));

        filteredList.addAll(list);
        adapter = new subjectAdapter(list,this);
        Header = navigationView.getHeaderView(0);

        suggestionAdapter = new subjectAdapter(filteredList,this);
        recyclerSuggestions.setAdapter(suggestionAdapter);

        loadFragment(new HomeFragment());

        Toast.makeText(getApplicationContext(),"add list book", Toast.LENGTH_SHORT).show();

        // thiết lập sự kiện nhấn cho App name
        txtAppName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new HomeFragment());
                Toast.makeText(getApplicationContext(),"app name", Toast.LENGTH_SHORT).show();
            }
        });

        // thiết lập sự kiện nhấn cho icon menu
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.getDrawerLockMode(GravityCompat.START) == DrawerLayout.LOCK_MODE_LOCKED_CLOSED) {
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                    drawerLayout.openDrawer(GravityCompat.START);
                }
                else {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                }
            }
        });

        // thiết lập sự kiện nhấn cho icon search
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchBar.setVisibility(View.VISIBLE);   // Hiện thanh tìm kiếm
                Toast.makeText(getApplicationContext(),"search", Toast.LENGTH_SHORT).show();
            }
        });

        // thiết lập sự kiện nhấn cho icon cancel
        closeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchBar.setVisibility(View.GONE); // Ẩn thanh tìm kiếm
                recyclerSuggestions.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),"cancel", Toast.LENGTH_SHORT).show();
            }
        });

        // Thiết lập sự kiện cho SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Khi người dùng nhấn nút Search trên bàn phím
                recyclerSuggestions.setVisibility(View.GONE);
                searchBar.setVisibility(View.GONE);
                loadFragment(new SearchResultFragment(query)); // Chuyển sang fragment kết quả
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Cập nhật danh sách gợi ý khi người dùng nhập
                filteredList.clear();
                if (newText.isEmpty()) {
                    recyclerSuggestions.setVisibility(View.GONE); // Ẩn gợi ý nếu không nhập gì
                }
                else {
                    for (subjectModel item : list) {
                        if (item.getSubjectName().toLowerCase().contains(newText.toLowerCase())) {
                            filteredList.add(item);
                        }
                    }
                    if (filteredList.isEmpty()) {
                        recyclerSuggestions.setVisibility(View.GONE); // Không có gợi ý
                    }
                    else {
                        recyclerSuggestions.setVisibility(View.VISIBLE); // Hiện gợi ý
                        suggestionAdapter.notifyDataSetChanged();
                    }
                }
                return true;
            }
        });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else if(getSupportFragmentManager().getBackStackEntryCount() > 0){
                    getSupportFragmentManager().popBackStack();
                }
                else {
                    moveTaskToBack(true); // Đưa ứng dụng vào chế độ nền
                }
            }
        });

    }

    public ArrayList<subjectModel> getList() {
        return list;
    }

    public void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();
    }

    // hàm lọc danh sách
    private void filterList(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(list);
        }
        else {
            for (subjectModel item : list) {
                if (item.getSubjectName().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }
        adapter.notifyDataSetChanged(); // Cập nhật lại RecyclerView
        Toast.makeText(getApplicationContext(),"sort list", Toast.LENGTH_SHORT).show();
    }
}