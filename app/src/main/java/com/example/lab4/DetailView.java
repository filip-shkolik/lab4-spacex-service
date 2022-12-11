package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailView extends AppCompatActivity {
    static String dataKey = "data";
    TextView textView;

    private void showDetailInfo(String data) throws JSONException {
        JSONObject shipInfo = new JSONObject(data);

        String shipId = shipInfo.getString("ship_id");
        String shipName = shipInfo.getString("ship_name");
        String shipType = shipInfo.getString("ship_type");
        String weightKg = shipInfo.getString("weight_kg");
        String yearBuilt = shipInfo.getString("year_built");
        String honePort = shipInfo.getString("home_port");


        textView.setText("Ship name is " + shipName + "\n"
                        + "Ship id is " + shipId + "\n"
                        + "Ship type is " + shipType + "\n"
                        + "Weight kg is " + weightKg + "\n"
                        + "Year built is " + yearBuilt + "\n"
                        + "Home port is " + honePort);




    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        textView = (TextView) findViewById(R.id.textView);

        Bundle extras = getIntent().getExtras();
        String value = extras.getString(dataKey);

        try {
            showDetailInfo(value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}