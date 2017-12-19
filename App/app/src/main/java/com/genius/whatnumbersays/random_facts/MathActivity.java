package com.genius.whatnumbersays.random_facts;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.genius.whatnumbersays.DisplayFactActivity;
import com.genius.whatnumbersays.utils.Constants;
import com.genius.whatnumbersays.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ADMIN on 10/6/2017.
 */

public class MathActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (isInternetOn()) {
            register("");
        } else {
            Toast.makeText(this, "Unable To Connect To Internet", Toast.LENGTH_SHORT).show();
        }

    }

    boolean isInternetOn() {
        ConnectivityManager conn = (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conn.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnected())
            return true;
        else
            return false;
    }


    private void register(String number) {
        class GetMathFact extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = new ProgressDialog(MathActivity.this);
                loading.setCancelable(false);
                loading.setMessage("Please Wait");
                loading.setProgress(0);
                loading.setMax(100);
                loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                loading.show();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    displayFact(s);
                    loading.dismiss();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                }
            }

            @Override
            protected String doInBackground(String... params) {
                String urlDate = Constants.RANDOM_URL_MATH;
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(urlDate);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    StringBuffer sb = new StringBuffer();
                    String line;

                    while((line=bufferedReader.readLine())!=null)
                        sb.append(line);
                    return sb.toString();
                } catch (Exception e) {
                    return null;
                }
            }
        }

        GetMathFact ru = new GetMathFact();
        ru.execute(number);
    }
    private void displayFact(String fact) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.BUNDLE_CATEGORY, "Math");
        bundle.putString(Constants.BUNDLE_TITLE, "Math - Fact");
        bundle.putString(Constants.BUNDLE_FACT, fact);
        bundle.putInt(Constants.BUNDLE_ICON, R.drawable.math);

        Intent intent = new Intent(getApplicationContext(), DisplayFactActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();

    }
}