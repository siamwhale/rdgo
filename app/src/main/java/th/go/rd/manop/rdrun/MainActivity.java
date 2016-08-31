package th.go.rd.manop.rdrun;

import android.content.Intent;
import android.opengl.ETC1;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private EditText userEditText, passwordEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Bind widget
        imageView = (ImageView) findViewById(R.id.imageView6);
        userEditText = (EditText) findViewById(R.id.editText5);
        passwordEditText = (EditText) findViewById(R.id.editText6);

    } // Main method

    // SignIn click
    public void clickSignInMain(View view) {
        // get logo from internet


    }
    // Get Event from Click button
    public void clickSignUpMain(View view) { // view ctrl+space และจะแสดงในหน้า layout ด้วย ctrl+shift+enter complete code
        startActivity(new Intent(MainActivity.this,SignUpActivity.class));
    }

} // Main class
