package com.genius.whatnumbersays;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.genius.whatnumbersays.utils.Constants;

/**
 * Created by ADMIN on 10/6/2017.
 */

public class DisplayFactActivity extends AppCompatActivity {

    private ImageView iconImageView;
    private TextView titleTextView;
    private TextView factTextView;

    private int icon;
    private String fact, title, category;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_fact);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        iconImageView = (ImageView) findViewById(R.id.activity_display_fact_icon_imageview);
        titleTextView = (TextView) findViewById(R.id.activity_display_fact_title_textview);
        factTextView = (TextView) findViewById(R.id.activity_display_fact_fact_textview);

        Bundle bundle = getIntent().getExtras();

        icon = bundle.getInt(Constants.BUNDLE_ICON);
        fact = bundle.getString(Constants.BUNDLE_FACT);
        title = bundle.getString(Constants.BUNDLE_TITLE);
        category = bundle.getString(Constants.BUNDLE_CATEGORY);

        iconImageView.setImageResource(icon);
        titleTextView.setText(title);
        factTextView.setText(fact);
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}
