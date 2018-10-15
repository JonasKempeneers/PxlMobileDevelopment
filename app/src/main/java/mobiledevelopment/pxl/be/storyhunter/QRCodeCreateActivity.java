package mobiledevelopment.pxl.be.storyhunter;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QRCodeCreateActivity extends AppCompatActivity {

    private ImageView qrImageView;
    private EditText title;
    private EditText author;
    private EditText language;
    private EditText isbn;

    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_create);

        title = findViewById(R.id.titleInput);
        author = findViewById(R.id.authorInput);
        language = findViewById(R.id.languageInput);
        isbn = findViewById(R.id.isbnInput);

        qrImageView = findViewById(R.id.qrImageView);
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
    }
}
