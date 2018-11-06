package mobiledevelopment.pxl.be.storyhunter;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import mobiledevelopment.pxl.be.storyhunter.api.BooksApi;
import mobiledevelopment.pxl.be.storyhunter.api.RetroFitInstance;
import mobiledevelopment.pxl.be.storyhunter.entities.Book;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QRCodeCaptureActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final int CAMERA_REQUEST_CODE = 2000;
    ZXingScannerView qrCodeScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_capture);

        qrCodeScanner = findViewById(R.id.qrCodeScanner);
        setScannerProperties();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
            return;
        }

        qrCodeScanner.startCamera();
        qrCodeScanner.setResultHandler(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        qrCodeScanner.stopCamera();
    }

    @Override
    protected void onStop() {
        super.onStop();
        qrCodeScanner.stopCamera();
    }

    private void setScannerProperties() {
        qrCodeScanner.setAutoFocus(true);
        qrCodeScanner.setLaserColor(R.color.colorAccent);
        qrCodeScanner.setMaskColor(R.color.colorAccent);
        qrCodeScanner.setAspectTolerance(0.5f);
    }

    @Override
    public void handleResult(Result result) {
        String separator = ":";
        String[] bookData = result.getText().split(separator);

        Book bookToPost = new Book(bookData[0], bookData[1], bookData[2], bookData[3]);

        postBook(bookToPost);
    }

    private void postBook(Book book){
        Intent previousIntent = getIntent();
        Call<Book> call;
        BooksApi service = RetroFitInstance.getRetrofitInstance().create(BooksApi.class);

        /*Call the method with parameter in the interface to get the book data*/
        if(previousIntent.getBooleanExtra("placeBooks", true)){
            call = service.postPlacedBook(book);
        }
        else {
            call = service.postFoundBook(book);
        }

        /*Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                Toast.makeText(QRCodeCaptureActivity.this, "Book successfully added to database", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Toast.makeText(QRCodeCaptureActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                Log.e("Error", t.getMessage());
            }
        });
        
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }


}
