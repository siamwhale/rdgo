package th.go.rd.manop.rdrun;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SignUpActivity extends AppCompatActivity {
    // explicit การประกาศตัวแปร
    private EditText nameEditText,surnameEditText,useridEditText,passwdEditText;
    private RadioGroup radioGroup;
    private RadioButton avatar1RadioButton, avatar2RadioButton, avatar3RadioButton, avatar4RadioButton, avatar5RadioButton;
    private String nameString,surnameString,useridString, passwdString, avatarString;

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
        avatar1RadioButton = (RadioButton) findViewById(R.id.radioButton2);
        avatar3RadioButton = (RadioButton) findViewById(R.id.radioButton3);
        avatar4RadioButton = (RadioButton) findViewById(R.id.radioButton4);
        avatar5RadioButton = (RadioButton) findViewById(R.id.radioButton5);

        // radio controller
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

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
        }

        //check select avatar

    }

    private boolean checkSpace() {
        boolean result = false;
        if (nameString.equals("") || surnameString.equals("") || useridString.equals("") || passwdString.equals("")) {
            result = true;
        }
        return result;
    }
}
