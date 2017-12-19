package com.genius.whatnumbersays;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.genius.whatnumbersays.quest_facts.QuestFactsMenuActivity;
import com.genius.whatnumbersays.random_facts.RandomFactsMenuActivity;

/**
 * Created by ADMIN on 10/6/2017.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Button randomFactsScreen = (Button) findViewById(R.id.activity_splash_randomfactsbutton);
        Button questFactsScreen = (Button) findViewById(R.id.activity_splash_questfactsbutton);

        randomFactsScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RandomFactsMenuActivity.class));
            }
        });

        questFactsScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), QuestFactsMenuActivity.class));
            }
        });
    }
}
