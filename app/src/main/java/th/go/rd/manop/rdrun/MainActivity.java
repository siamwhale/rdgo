package th.go.rd.manop.rdrun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    } // Main method

    // Get Event from Click button
    public void clickSignUpMain(View view) { // view ctrl+space และจะแสดงในหน้า layout ด้วย ctrl+shift+enter complete code
        startActivity(new Intent(MainActivity.this,SignUpActivity.class));
    }

} // Main class
