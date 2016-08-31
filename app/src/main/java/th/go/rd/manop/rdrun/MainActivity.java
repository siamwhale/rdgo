package th.go.rd.manop.rdrun;

import android.content.Intent;
import android.opengl.ETC1;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private EditText userEditText, passwordEditText;
    private String userString, passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Bind widget
        imageView = (ImageView) findViewById(R.id.imageView6);
        userEditText = (EditText) findViewById(R.id.editText5);
        passwordEditText = (EditText) findViewById(R.id.editText6);

        // Load img from server
        Picasso.with(this).load("http://swiftcodingthai.com/rd/Image/rd_logo.png")
                .resize(150, 150).into(imageView);
    } // Main me

    // SignIn click
    public void clickSignInMain(View view) {
        // get logo from internet


    }
    // Get Event from Click button
    public void clickSignUpMain(View view) { // view ctrl+space และจะแสดงในหน้า layout ด้วย ctrl+shift+enter complete code
        startActivity(new Intent(MainActivity.this,SignUpActivity.class));
    }

} // Main class
