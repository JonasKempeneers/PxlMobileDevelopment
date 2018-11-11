package mobiledevelopment.pxl.be.storyhunter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;

public class QRCodeCreateActivity extends AppCompatActivity {

    private ImageView qrImageView;
    private EditText title;
    private EditText author;
    private EditText language;
    private EditText isbn;
    private Toolbar toolbar;

    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_create);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        title = findViewById(R.id.titleInput);
        author = findViewById(R.id.authorInput);
        language = findViewById(R.id.languageInput);
        isbn = findViewById(R.id.isbnInput);

        qrImageView = findViewById(R.id.qrImageView);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public void generateQRCode(View view) throws WriterException {
        StringBuilder qrTextBuilder = new StringBuilder();
        String separator = ":";

        if(title == null || author == null || language == null || isbn == null){
            mToast = Toast.makeText(this, "All fields must be filled out!", Toast.LENGTH_LONG);
            return;
        }

        qrTextBuilder.append(title.getText() + separator)
                .append(author.getText() + separator)
                .append(language.getText() + separator)
                .append(isbn.getText() + separator);

        MultiFormatWriter qrCodeWriter = new MultiFormatWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(qrTextBuilder.toString(), BarcodeFormat.QR_CODE, 200,200);

        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);

        qrImageView.setImageBitmap(bitmap);

        View b = findViewById(R.id.shareQr);
        b.setVisibility(View.VISIBLE);
    }

    public void shareQRCode(View view) {
        Bitmap bitmap = getBitmapFromView(qrImageView);
        if(Build.VERSION.SDK_INT>=24){
            try{
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        try {
            File file = new File(this.getExternalCacheDir(),"logicchip.png");
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, false);
            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.setType("image/png");
            startActivity(Intent.createChooser(intent, "Share image via"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null) {
            bgDrawable.draw(canvas);
        }   else{
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnedBitmap;
    }
}
