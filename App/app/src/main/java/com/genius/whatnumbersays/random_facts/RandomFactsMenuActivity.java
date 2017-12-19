package com.genius.whatnumbersays.random_facts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.genius.whatnumbersays.R;

import java.util.ArrayList;
import java.util.List;

public class RandomFactsMenuActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private RecyclerViewAdapterMenu adapter;

    private View view;
    private List<MenuTiles> titles;

    static final String TILE_TRIVIA = "Trivia";
    static final String TILE_MATH = "Math";
    static final String TILE_DATE = "Date";
    static final String TILE_YEAR = "Year";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();
    }

    private void init() {
        titles = initTitlesList();
        layoutManager = new GridLayoutManager(RandomFactsMenuActivity.this, 2);
        view = findViewById(R.id.coordinator_layout_menu_activity);
        recyclerView = (RecyclerView) findViewById(R.id.menu_activity_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter = new RecyclerViewAdapterMenu(titles, view, this);
        recyclerView.setAdapter(adapter);
    }

    private List<MenuTiles> initTitlesList() {
        List<MenuTiles> temp = new ArrayList<>();

        temp.add(new MenuTiles(TILE_TRIVIA, R.drawable.trivia));
        temp.add(new MenuTiles(TILE_MATH, R.drawable.math));
        temp.add(new MenuTiles(TILE_DATE, R.drawable.date));
        temp.add(new MenuTiles(TILE_YEAR, R.drawable.year));

        return temp;
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

}
