package th.go.rd.manop.rdrun;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class SignUpActivity extends AppCompatActivity {
    // explicit การประกาศตัวแปร
    private EditText nameEditText,surnameEditText,useridEditText,passwdEditText;
    private RadioGroup radioGroup;
    private RadioButton avatar1RadioButton, avatar2RadioButton, avatar3RadioButton, avatar4RadioButton, avatar5RadioButton;
    private String nameString,surnameString,useridString, passwdString, avatarString;
    private static final String urlPHP = "http://swiftcodingthai.com/rd/add_user_manop.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        // Bind or initial widget การผูกความสัมพันธ์ตัวแปรกับวิดเจ็ก
        nameEditText = (EditText) findViewById(R.id.editText);  // insert cast data alt+Enter key
        surnameEditText = (EditText) findViewById(R.id.editText2);
        useridEditText = (EditText) findViewById(R.id.editText3);
        passwdEditText = (EditText) findViewById(R.id.editText4);
        radioGroup = (RadioGroup) findViewById(R.id.ragAvatar);
        avatar1RadioButton = (RadioButton) findViewById(R.id.radioButton);
        avatar2RadioButton = (RadioButton) findViewById(R.id.radioButton2);
        avatar3RadioButton = (RadioButton) findViewById(R.id.radioButton3);
        avatar4RadioButton = (RadioButton) findViewById(R.id.radioButton4);
        avatar5RadioButton = (RadioButton) findViewById(R.id.radioButton5);

        // radio controller
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton:
                        avatarString = "0";
                        break;
                    case R.id.radioButton2:
                        avatarString = "1";
                        break;
                    case R.id.radioButton3:
                        avatarString = "2";
                        break;
                    case R.id.radioButton4:
                        avatarString = "3";
                        break;
                    case R.id.radioButton5:
                        avatarString = "4";
                        break;


                }
            }
        });
    }
    public void clickSignupSign(View view) {
        // Get value from edit text
        nameString = nameEditText.getText().toString().trim();
        surnameString = surnameEditText.getText().toString().trim();
        useridString = useridEditText.getText().toString().trim();
        passwdString = passwdEditText.getText().toString().trim();

        //check space
        if (checkSpace()) {
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this,R.drawable.kon48, "มีช่องว่าง","กรุณากรอกทุกช่อง คะ");
        } else if (checkCoose()) {
            // true have choose
            confirmValue();
        } else {
            // false not choose
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this,R.drawable.nobita48,"ยังไม่เลือก Avatar","กรุณาเลือก Avatar ก่อนคะ");
        }


        //check select avatar

    }

    private void confirmValue() {
        MyConstant myConstant = new MyConstant();
        int[] avatar = myConstant.getAvatarInts();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setIcon(avatar[Integer.parseInt(avatarString)]);
        builder.setTitle("โปรดตรวจสอบข้อมูล");
        builder.setMessage("Name : "+ nameString + "\n" +
                            "Surname : "+ surnameString + "\n" +
                            "User :" + useridString + "\n" +
                            "Password :" + passwdString + "\n");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                uploadValueToServer();
                dialog.dismiss();
            }
        });
        builder.show();

    }

    private void uploadValueToServer() {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd", "true")
                .add("Name", nameString)
                .add("Surname", surnameString)
                .add("User", useridString)
                .add("Passwd", passwdString)
                .add("Avatar", avatarString)
                .build();
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(urlPHP).post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                finish();
            }
        });
    }

    private boolean checkCoose() {
        boolean result = false;
        if (avatar1RadioButton.isChecked() ||
                avatar2RadioButton.isChecked() ||
                avatar3RadioButton.isChecked() ||
                avatar4RadioButton.isChecked() ||
                avatar5RadioButton.isChecked()) {
            result = true;
        }
        return result;
    }

    private boolean checkSpace() {
        boolean result = false;
        if (nameString.equals("") ||
                surnameString.equals("") ||
                useridString.equals("") ||
                passwdString.equals("")) {
            result = true;
        }
        return result;
    }
}
