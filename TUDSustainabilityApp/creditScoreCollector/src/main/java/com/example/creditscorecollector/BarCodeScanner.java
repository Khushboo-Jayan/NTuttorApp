package com.example.creditscorecollector;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BarCodeScanner extends AppCompatActivity implements View.OnClickListener {

    Button barCodeScannerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_code_scanner);
        barCodeScannerBtn = (Button) findViewById(R.id.barCodeScannerButton);

        barCodeScannerBtn.setOnClickListener(this);
    }

    private void scanCode(){
//        ScanOptions
    }
    @Override
    public void onClick(View view) {
        scanCode();
    }
}