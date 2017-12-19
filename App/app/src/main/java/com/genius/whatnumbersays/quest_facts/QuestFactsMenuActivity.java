package com.genius.whatnumbersays.quest_facts;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.genius.whatnumbersays.DisplayFactActivity;
import com.genius.whatnumbersays.R;
import com.genius.whatnumbersays.utils.Constants;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.genius.whatnumbersays.R.drawable.date;
import static com.genius.whatnumbersays.R.drawable.year;
import static com.genius.whatnumbersays.R.id.radioGroup;

/**
 * Created by ADMIN on 10/13/2017.
 */

public class QuestFactsMenuActivity extends AppCompatActivity {
    String TAG = "MainActivity.java";

    private String[] categories = {"Date", "Math", "Trivia", "Year"};
    private int year, month, day;

    private RadioGroup radioGroup;
    private EditText dateOrNumber;
    private Button submitButton;

    private String selectedCategory;
    private String input;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_screen_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        selectedCategory = "date";

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        dateOrNumber = (EditText) findViewById(R.id.activity_quest_screen_number_edittext);
        submitButton = (Button) findViewById(R.id.activity_quest_screen_submit_button);

        dateOrNumber.setInputType(InputType.TYPE_NULL);

        dateOrNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedCategory.equals("date") || selectedCategory.equals("year")) {
                    setDate(null);
                }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedCategory.trim().equals("")) {
                    Toast.makeText(QuestFactsMenuActivity.this, "Please select a category first", Toast.LENGTH_SHORT).show();
                } else {
                    input = dateOrNumber.getText().toString().trim();

                    if (input.equals("")) {
                        Toast.makeText(QuestFactsMenuActivity.this, "Please provide some input", Toast.LENGTH_SHORT).show();
                    } else {

                        if (isInternetOn()) {
                            register(selectedCategory, input);
                        } else {
                            Toast.makeText(QuestFactsMenuActivity.this, "Unable To Connect To Internet", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    selectedCategory = rb.getText().toString().toLowerCase();

                    if (selectedCategory.equals("math")) {
                        dateOrNumber.setInputType(InputType.TYPE_CLASS_NUMBER);
                    } else if (selectedCategory.equals("trivia")) {
                        dateOrNumber.setInputType(InputType.TYPE_CLASS_NUMBER);
                    } else {
                        dateOrNumber.setInputType(InputType.TYPE_NULL);
                    }

                    dateOrNumber.setText("");
                }

            }
        });

    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    showDate(arg1, arg2 + 1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        if (selectedCategory.equals("date")) {
            dateOrNumber.setText(new StringBuilder().append(month).append("/").append(day));
        } else if (selectedCategory.equals("year")) {
            dateOrNumber.setText(new StringBuilder().append(year));
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


    private void register(final String selectedCategory, String input) {
        class GetFact extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = new ProgressDialog(QuestFactsMenuActivity.this);
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
                String category = params[0];
                String input = params[1];

                String urlDate = Constants.URL_FACTS + input + "/" + category;
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(urlDate);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    StringBuffer sb = new StringBuffer();
                    String line;

                    while ((line = bufferedReader.readLine()) != null)
                        sb.append(line);
                    return sb.toString();
                } catch (Exception e) {
                    return null;
                }
            }
        }

        GetFact ru = new GetFact();
        ru.execute(selectedCategory.toLowerCase(), input);
    }

    private void displayFact(String fact) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.BUNDLE_CATEGORY, selectedCategory);
        bundle.putString(Constants.BUNDLE_TITLE, selectedCategory + " - Fact For " + input);
        bundle.putString(Constants.BUNDLE_FACT, fact);

        bundle.putInt(Constants.BUNDLE_ICON, R.drawable.facts);

        Intent intent = new Intent(getApplicationContext(), DisplayFactActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}
