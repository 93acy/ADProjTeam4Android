package com.example.adprojteam4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class UserPreferencesActivity extends AppCompatActivity {

    String foodType;
    ArrayList<String> carbType = new ArrayList<>();
    ArrayList<String> proteinType = new ArrayList<>();
    Button userPrefsSubmit;
    Spinner spinner;
    CheckBox checkbox_rice;
    CheckBox checkbox_noodles;
    CheckBox checkbox_potato;
    CheckBox checkbox_others;
    CheckBox checkbox_none;
    CheckBox checkbox_proteinPork;
    CheckBox checkbox_proteinChicken;
    CheckBox checkbox_proteinBeef;
    CheckBox checkbox_proteinLamb;
    CheckBox checkbox_proteinFish;
    CheckBox checkbox_proteinSeafood;
    CheckBox checkbox_proteinOthers;
    CheckBox checkbox_proteinNone;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_preferences);

        spinner = (Spinner) findViewById(R.id.foodTypeSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.foodTypeArray, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        checkbox_rice = findViewById(R.id.checkbox_rice);
        checkbox_noodles = findViewById(R.id.checkbox_noodles);
        checkbox_potato = findViewById(R.id.checkbox_potato);
        checkbox_others = findViewById(R.id.checkbox_others);
        checkbox_none = findViewById(R.id.checkbox_none);

        checkbox_proteinPork = findViewById(R.id.checkbox_proteinPork);
        checkbox_proteinChicken = findViewById(R.id.checkbox_proteinChicken);
        checkbox_proteinBeef = findViewById(R.id.checkbox_proteinBeef);
        checkbox_proteinLamb = findViewById(R.id.checkbox_proteinLamb);
        checkbox_proteinFish = findViewById(R.id.checkbox_proteinFish);
        checkbox_proteinSeafood = findViewById(R.id.checkbox_proteinSeafood);
        checkbox_proteinOthers = findViewById(R.id.checkbox_proteinOthers);
        checkbox_proteinNone = findViewById(R.id.checkbox_proteinNone);

        userPrefsSubmit = findViewById(R.id.userPrefsSubmit);
        userPrefsSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodType = spinner.getSelectedItem().toString();
                SharedPreferences userPrefs = getSharedPreferences("userPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = userPrefs.edit();
                Gson gson = new Gson();

                String json1 = gson.toJson(carbType);
                String json2 = gson.toJson(proteinType);

                editor.putString("foodType", foodType);
                editor.putString("carbType", json1);
                editor.putString("proteinType", json2);
                editor.commit();
                Intent intent = new Intent(UserPreferencesActivity.this, ViewHawkerListingActivity.class);
                startActivity(intent);
            }
        });


    }



    public void onCarbTypeCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.checkbox_rice:
                if (checked)
                    carbType.add(checkbox_rice.getText().toString());
                else
                    carbType.remove(checkbox_rice.getText().toString());
                break;
            case R.id.checkbox_noodles:
                if (checked)
                    carbType.add(checkbox_noodles.getText().toString());
                else
                    carbType.remove(checkbox_noodles.getText().toString());
                break;
            case R.id.checkbox_potato:
                if (checked)
                    carbType.add(checkbox_potato.getText().toString());
                else
                    carbType.remove(checkbox_potato.getText().toString());
                break;
            case R.id.checkbox_others:
                if (checked)
                    carbType.add(checkbox_others.getText().toString());
                else
                    carbType.remove(checkbox_others.getText().toString());
                break;
            case R.id.checkbox_none:
                if (checked)
                    carbType.add(checkbox_none.getText().toString());
                else
                    carbType.remove(checkbox_none.getText().toString());
                break;

        }

    }

    public void onProteinTypeCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {
            case R.id.checkbox_proteinPork:
                if (checked)
                    proteinType.add(checkbox_proteinPork.getText().toString());
                else
                    proteinType.remove(checkbox_proteinPork.getText().toString());
                break;
            case R.id.checkbox_proteinChicken:
                if (checked)
                    proteinType.add(checkbox_proteinChicken.getText().toString());
                else
                    proteinType.remove(checkbox_proteinChicken.getText().toString());
                break;
            case R.id.checkbox_proteinBeef:
                if (checked)
                    proteinType.add(checkbox_proteinBeef.getText().toString());
                else
                    proteinType.remove(checkbox_proteinBeef.getText().toString());
                break;
            case R.id.checkbox_proteinLamb:
                if (checked)
                    proteinType.add(checkbox_proteinLamb.getText().toString());
                else
                    proteinType.remove(checkbox_proteinLamb.getText().toString());
                break;
            case R.id.checkbox_proteinFish:
                if (checked)
                    proteinType.add(checkbox_proteinFish.getText().toString());
                else
                    proteinType.remove(checkbox_proteinFish.getText().toString());
                break;
            case R.id.checkbox_proteinSeafood:
                if (checked)
                    proteinType.add(checkbox_proteinSeafood.getText().toString());
                else
                    proteinType.remove(checkbox_proteinSeafood.getText().toString());
                break;
            case R.id.checkbox_proteinOthers:
                if (checked)
                    proteinType.add(checkbox_proteinOthers.getText().toString());
                else
                    proteinType.remove(checkbox_proteinOthers.getText().toString());
                break;
            case R.id.checkbox_proteinNone:
                if (checked)
                    proteinType.add(checkbox_proteinNone.getText().toString());
                else
                    proteinType.remove(checkbox_proteinNone.getText().toString());
                break;
        }

    }
}