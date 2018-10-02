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
         colorButton = findViewById(R.id.button);
         colorButton.setBackgroundColor(Color.WHITE);

         colorButton.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View v) {
                 ColorDrawable buttonColor = (ColorDrawable) colorButton.getBackground();

                 if(buttonColor.getColor() == Color.BLUE){
                     colorButton.setBackgroundColor(Color.WHITE);
                 } else {
                     colorButton.setBackgroundColor(Color.BLUE);
                 }

             }
         });

    }

    public void goToMenu(View view) {
        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}
