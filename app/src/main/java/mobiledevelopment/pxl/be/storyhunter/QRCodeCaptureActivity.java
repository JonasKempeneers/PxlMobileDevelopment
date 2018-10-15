package mobiledevelopment.pxl.be.storyhunter;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

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
        //TODO: Get data from qr
        //TODO: Post to database (foundbooks)
        //TODO: Return to mapactivity, camera will freeze on read

    }


}
