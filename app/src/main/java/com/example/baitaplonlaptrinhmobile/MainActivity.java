package com.example.baitaplonlaptrinhmobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

//import com.example.baitaplonlaptrinhmobile.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity {
    FloatingActionButton addDiaryBtn;
    RecyclerView recyclerView;
    ImageButton menuBtn;
    DiaryAdapter diaryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addDiaryBtn = findViewById(R.id.add_diary_btn);
        recyclerView = findViewById(R.id.recyler_view);
        menuBtn = findViewById(R.id.menu_btn);

        addDiaryBtn.setOnClickListener((v) -> startActivity(new Intent(MainActivity.this, DiaryDetailsActivity.class)));
        menuBtn.setOnClickListener((v) -> showMenu());
        setupRecyclerView();
    }

    void showMenu() {

    }
    void setupRecyclerView() {

    }
}

