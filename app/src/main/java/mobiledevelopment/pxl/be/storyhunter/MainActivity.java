package mobiledevelopment.pxl.be.storyhunter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }



    public void goToMenu(View view) {
        Intent intent = new Intent(MainActivity.this, BookListActivity.class);
        startActivity(intent);
    }

    public void navigateToMap(View view){
        Intent intent = new Intent (MainActivity.this, MapsActivity.class);
        startActivity(intent);
    }

    public void navigateToQRScanner(View view){
        Intent intent = new Intent(MainActivity.this, QRCodeCaptureActivity.class);
        startActivity(intent);
    }

    public void navigateToQRGenerator(View view){
        Intent intent = new Intent(this, QRCodeCreateActivity.class);
        startActivity(intent);
    }
}
