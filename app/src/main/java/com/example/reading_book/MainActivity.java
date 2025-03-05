package com.example.reading_book;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<subjectModel>list;
    subjectAdapter adapter;
    RecyclerView recyclerView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menu,search;
    View Header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recySubject);
        menu = findViewById(R.id.imgMenu);
        search = findViewById(R.id.imgSearch);
        drawerLayout = findViewById(R.id.main);
        navigationView = findViewById(R.id.navView);
        Toast.makeText(getApplicationContext(),"1", Toast.LENGTH_SHORT).show();
        list = new ArrayList<>();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        list.add(new subjectModel("Beginning Android Programming with Android Studio","/Reading_Book/image"));
        list.add(new subjectModel("Beginning Android Programming with Android Studio","/Reading_Book/image"));
        list.add(new subjectModel("Beginning Android Programming with Android Studio","/Reading_Book/image"));
        list.add(new subjectModel("Beginning Android Programming with Android Studio","/Reading_Book/image"));
        list.add(new subjectModel("Beginning Android Programming with Android Studio","/Reading_Book/image"));
        list.add(new subjectModel("Beginning Android Programming with Android Studio","/Reading_Book/image"));
        list.add(new subjectModel("Beginning Android Programming with Android Studio","/Reading_Book/image"));
        list.add(new subjectModel("Beginning Android Programming with Android Studio","/Reading_Book/image"));
        list.add(new subjectModel("Beginning Android Programming with Android Studio","/Reading_Book/image"));
        list.add(new subjectModel("Beginning Android Programming with Android Studio","/Reading_Book/image"));

        adapter = new subjectAdapter(list,this);
        recyclerView.setAdapter(adapter);
        Header = navigationView.getHeaderView(0);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

//        search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        Toast.makeText(getApplicationContext(),"3", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}