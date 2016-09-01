package th.go.rd.manop.rdrun;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

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

    //Create Inner Class
    // การสร้างเทรด
    // ทำงานที่ไม่สำเร็จจะทำใหม่ AsyncTask<ก่อนโหลด,ระหว่างโหลด,หลังโหลด>
    private class SynUser extends AsyncTask<Void, Void, String> {
        // Explicit
        private Context context;
        private String myUserString, myPasswordString, truePasswordString, nameString,surnameString,id;
        //private static final String urlJSON = "http://swiftcodingthai.com/rd/get_user_manop.php";
        private static final String urlJSON = "http://swiftcodingthai.com/rd/get_user_master.php";
        private boolean status = true;

        public SynUser(Context context, String myUserString, String myPasswordString) {
            this.context = context;
            this.myUserString = myUserString;
            this.myPasswordString = myPasswordString;
        }

        @Override
        protected String doInBackground(Void... params) {
            // ใช้ป้องกันการการผิดพลาด แล้วทำงานใหม่
            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(urlJSON).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();
            } catch (Exception e) {
                Log.d("31AugV2", "e doInBack == " + e.toString());
                return null;
            }
        }  // Alt+Enter เพื่อ Implement and insert override with alt+insert

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("31AugV2", "JSON == " +s);
            try {
                JSONArray jsonArray = new JSONArray(s);
                for (int i=0;i<jsonArray.length();i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (myUserString.equals(jsonObject.getString("User"))) {
                        status = false;
                        truePasswordString = jsonObject.getString("Password");
                        nameString = jsonObject.getString("User");
                        surnameString = jsonObject.getString("Surname");
                        id = jsonObject.getString("id");
                    }
                }

                if (status) {
                    // User not found
                    MyAlert myAlert = new MyAlert();
                    myAlert.myDialog(context,R.drawable.kon48,"User wrong","ไม่มี "+ myUserString +" ในระบบ" );
                } else if (myPasswordString.equals(truePasswordString)) {
                    // Password true
                    // Toast หน้าจอแสดงผลชั่วคราว แล้วหายเอง
                    Intent intent = new Intent(MainActivity.this, ServiceActivity.class);
                    startActivity(intent);
                    Toast.makeText(context, "Welcome " + nameString + " " + surnameString,
                            Toast.LENGTH_SHORT).show();

                } else {
                    // Password false
                    MyAlert myAlert = new MyAlert();
                    myAlert.myDialog(context,R.drawable.bird48,"Password wrong","Please try again.");
                }
            } catch (Exception e) {
                Log.d("31AugV3", "e onPost ==" + e.toString());
            }

        }
    } // SynUser class

    // SignIn click
    public void clickSignInMain(View view) {
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();
        // check space
        if (userString.equals("") || passwordString.equals("")) {
            // Have space
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this,R.drawable.rat48,"มีช่องว่าง","โปรดกรอกข้อมูลทุกช่อง");
        } else {
            // No space
            // เมื่อไม่พบข้อผิดพลาด
            SynUser synUser = new SynUser(this,userString,passwordString);
            synUser.execute();
        }


    }
    // Get Event from Click button
    public void clickSignUpMain(View view) { // view ctrl+space และจะแสดงในหน้า layout ด้วย ctrl+shift+enter complete code
        startActivity(new Intent(MainActivity.this,SignUpActivity.class));
    }

} // Main class
