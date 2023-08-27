package com.example.tudsustainabilityapp;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;



public class CreditScore extends AppCompatActivity {

    Button barCodeScannerBtn;
    TextView StudentData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_score);
        barCodeScannerBtn = findViewById(R.id.barCodeScannerButton);
        StudentData = findViewById(R.id.scannedValue);

        barCodeScannerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(CreditScore.this);
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.setPrompt("Scan a Barcode");
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                intentIntegrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(intentResult != null){
            String content = intentResult.getContents();
            if(content != null){
                StudentData.setText(intentResult.getContents() );
            }else {
                super.onActivityResult(requestCode, resultCode, data);
            }//end else
        }//if endd
    }//check and get data from barcode if exists
}