package com.oyster.makebarcodeimg;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.oned.EAN13Writer;
/*
*  바코드 이미지 생성기(Barcode Image Generator)
*  출처: http://stupidsoft.tistory.com/34
* */
public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imageView);
        editText = (EditText)findViewById(R.id.editText);
        button = (Button)findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String  barcode = editText.getText().toString();

                if(barcode.length() == 13){
                    Bitmap bm = makeBarcode(barcode, 300, 100);
                    imageView.setImageBitmap(bm);
                }else{
                    Toast.makeText(MainActivity.this, "13자리를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public Bitmap makeBarcode(String str, int w, int h){
        Bitmap bitmap = null;
        Writer c13 = new Code128Writer();
        try{
            BitMatrix bm = c13.encode(str, BarcodeFormat.CODE_128, w, h);
            bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            for(int i=0; i<w; i++){
                for(int j=0; j<h; j++){
                    bitmap.setPixel(i,j, bm.get(i,j) ? Color.BLACK: Color.WHITE);
                }
            }

        }catch (WriterException e){
            e.printStackTrace();
        }
        return  bitmap;
    }


}
