package com.oyster.makebarcodeimg;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String barcode = "a123456789101";
        imageView = (ImageView)findViewById(R.id.imageView);
        Bitmap bm = makeBarcode(barcode, 300, 100);
        imageView.setImageBitmap(bm);

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
