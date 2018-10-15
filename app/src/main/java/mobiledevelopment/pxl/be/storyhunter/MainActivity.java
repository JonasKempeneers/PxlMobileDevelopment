package mobiledevelopment.pxl.be.storyhunter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Button colorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void changeColor(View view) {
        colorButton = findViewById(R.id.button);
        ColorDrawable buttonColor = (ColorDrawable) colorButton.getBackground();

        if(buttonColor.getColor() == Color.BLUE){
            colorButton.setBackgroundColor(Color.WHITE);
        } else {
            colorButton.setBackgroundColor(Color.BLUE);
        }
    }
}
