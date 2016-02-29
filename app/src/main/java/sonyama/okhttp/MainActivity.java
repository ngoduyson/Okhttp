package sonyama.okhttp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String URL = "http://i2.kym-cdn.com/photos/images/newsfeed/000/101/781/Y0UJC.png";
    Button downloadBtn;
    ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadBtn = (Button) findViewById(R.id.button);
        mImage = (ImageView) findViewById(R.id.imageView);

        downloadBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                downloadBtn.setVisibility(View.INVISIBLE);
                OkHttpHandler handler = new OkHttpHandler();
                byte[] image;

                try {
                    image = handler.execute(URL).get();

                    if (image != null && image.length > 0) {

                        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0,
                                image.length);
                        mImage.setImageBitmap(bitmap);
                        mImage.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Connect Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
