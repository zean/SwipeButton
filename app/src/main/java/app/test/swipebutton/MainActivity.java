package app.test.swipebutton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SwipeButton next_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSwipeButton();
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

    void setSwipeButton() {
        next_button = (SwipeButton) findViewById(R.id.next_button);

        SwipeButtonItems swipeButtonSettings = new SwipeButtonItems() {
            @Override
            public void onSwipeConfirm() {
                Toast.makeText(MainActivity.this, "button swiped", Toast.LENGTH_SHORT).show();
            }
        };

        swipeButtonSettings
                .setTrackText("swipe ->")
                .setButtonText("next")
                .setThumbColor1(0xFFd73c3f)
                .setThumbColor2(0xFf26166)
                .setTrackColor1(0xFd8d8d8)
                .setTrackColor2(0xFf2f2f2)
                .setButtonSize(160)
                .setActionConfirmDistanceFraction(0.7);

        if (next_button != null) {
            next_button.setSwipeButtonCustomItems(swipeButtonSettings);
            next_button.setVisibility(View.VISIBLE);
        }
    }
}
