package com.example.tudsustainabilityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class BarCodeGenerator extends AppCompatActivity implements View.OnClickListener{

    Button barCodeGeneratorButton;
    ImageView barCodeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_code_generator);
        barCodeGeneratorButton = findViewById(R.id.barcodeGeneratorBtn);
        barCodeImage = findViewById(R.id.barCode);

        barCodeGeneratorButton.setOnClickListener(this);
    }

    public void barCodeGenerator_Method(View view){
        String dataForBarCode = "D20123668";

        MultiFormatWriter mfwriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = mfwriter.encode(dataForBarCode, BarcodeFormat.QR_CODE, barCodeImage.getWidth(), barCodeImage.getHeight());
            BarcodeEncoder barcodeencoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeencoder.createBitmap(bitMatrix);
            barCodeImage.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        barCodeGenerator_Method(view);
    }
}