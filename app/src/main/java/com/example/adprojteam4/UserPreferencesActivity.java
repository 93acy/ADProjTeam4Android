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
                    carbType.add(findViewById(R.id.checkbox_rice).toString());
                else
                    carbType.remove(findViewById(R.id.checkbox_rice).toString());
                break;
            case R.id.checkbox_noodles:
                if (checked)
                    carbType.add(findViewById(R.id.checkbox_noodles).toString());
                else
                    carbType.remove(findViewById(R.id.checkbox_noodles).toString());
                break;
            case R.id.checkbox_potato:
                if (checked)
                    carbType.add(findViewById(R.id.checkbox_potato).toString());
                else
                    carbType.remove(findViewById(R.id.checkbox_potato).toString());
                break;
            case R.id.checkbox_others:
                if (checked)
                    carbType.add(findViewById(R.id.checkbox_others).toString());
                else
                    carbType.remove(findViewById(R.id.checkbox_others).toString());
                break;
            case R.id.checkbox_none:
                if (checked)
                    carbType.add(findViewById(R.id.checkbox_none).toString());
                else
                    carbType.remove(findViewById(R.id.checkbox_none).toString());
                break;

        }

    }

    public void onProteinTypeCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {
            case R.id.checkbox_proteinPork:
                if (checked)
                    proteinType.add(findViewById(R.id.checkbox_proteinPork).toString());
                else
                    proteinType.remove(findViewById(R.id.checkbox_proteinPork).toString());
                break;
            case R.id.checkbox_proteinChicken:
                if (checked)
                    proteinType.add(findViewById(R.id.checkbox_proteinChicken).toString());
                else
                    proteinType.remove(findViewById(R.id.checkbox_proteinChicken).toString());
                break;
            case R.id.checkbox_proteinBeef:
                if (checked)
                    proteinType.add(findViewById(R.id.checkbox_proteinBeef).toString());
                else
                    proteinType.remove(findViewById(R.id.checkbox_proteinBeef).toString());
                break;
            case R.id.checkbox_proteinLamb:
                if (checked)
                    proteinType.add(findViewById(R.id.checkbox_proteinLamb).toString());
                else
                    proteinType.remove(findViewById(R.id.checkbox_proteinLamb).toString());
                break;
            case R.id.checkbox_proteinFish:
                if (checked)
                    proteinType.add(findViewById(R.id.checkbox_proteinFish).toString());
                else
                    proteinType.remove(findViewById(R.id.checkbox_proteinFish).toString());
                break;
            case R.id.checkbox_proteinSeafood:
                if (checked)
                    proteinType.add(findViewById(R.id.checkbox_proteinSeafood).toString());
                else
                    proteinType.remove(findViewById(R.id.checkbox_proteinSeafood).toString());
                break;
            case R.id.checkbox_proteinOthers:
                if (checked)
                    proteinType.add(findViewById(R.id.checkbox_proteinOthers).toString());
                else
                    proteinType.remove(findViewById(R.id.checkbox_proteinOthers).toString());
                break;
            case R.id.checkbox_proteinNone:
                if (checked)
                    proteinType.add(findViewById(R.id.checkbox_proteinNone).toString());
                else
                    proteinType.remove(findViewById(R.id.checkbox_proteinNone).toString());
                break;
        }

    }
}