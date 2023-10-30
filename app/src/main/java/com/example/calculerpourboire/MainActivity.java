package com.example.calculerpourboire;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText cost;
    private Button calculate;
    private RadioGroup options;
    private SwitchCompat roundUpSwitch;
    private TextView result;
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button send;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        cost = (EditText) findViewById(R.id.service_cost);
        calculate = (Button) findViewById(R.id.calculate_button);
        options = (RadioGroup) findViewById(R.id.options);
        roundUpSwitch = (SwitchCompat) findViewById(R.id.round_up_switch);
        result = (TextView) findViewById(R.id.result);
        send = (Button) findViewById(R.id.send_button);

        Map<Integer, Double> optionsMap = Map.of(
                R.id.option_dix_height_percent, 0.18,
                R.id.option_quinze_percent, 0.15,
                R.id.option_vingt_percent, 0.2
        );

        calculate.setOnClickListener(
                view -> {
                    double percent = optionsMap.get(options.getCheckedRadioButtonId());

                    double tip = cost.getText().toString().equals("") ? 0.0 : Double.parseDouble(cost.getText().toString()) * percent;
                    if (roundUpSwitch.isChecked())
                        tip = Math.round(tip * 100.0) / 100.0;
                    result.setText("");
                    result.append("Tip amount : " + tip);
                    result.setTextSize(48);

                }
        );

        send.setOnClickListener(
                view -> {
                    Log.d(TAG, "Button send clicked !");
                    Intent intent = new Intent(MainActivity.this, secondActivity.class);
                    intent.putExtra("msg", result.getText().toString());

                    startActivity(intent);
                }
        );

    }
}