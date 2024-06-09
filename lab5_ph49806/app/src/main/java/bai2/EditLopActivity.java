package bai2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import poly.edu.vn.lab5_ph49806.AddStudentActivity;
import poly.edu.vn.lab5_ph49806.R;
import poly.edu.vn.lab5_ph49806.School;
import poly.edu.vn.lab5_ph49806.SchoolSpinnerAdapter;

public class EditLopActivity extends AppCompatActivity {
    EditText ed_name, ed_diachi;
    TextView tv_info;
    Button btnSave;
    int studentId;
    String name, diaChi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_lop);

        ed_name = findViewById(R.id.ed_name);
        ed_diachi = findViewById(R.id.ed_diachi);
        tv_info = findViewById(R.id.tv_info);
        btnSave = findViewById(R.id.btn_save);



        Intent intent = getIntent();
        studentId = intent.getIntExtra("id", -1);
        name = intent.getStringExtra("name");
        diaChi = intent.getStringExtra("diachi");

        ed_name.setText(name);
        ed_diachi.setText(diaChi);

        tv_info.setText("Sửa tên: \n NAME = " + name + "\n Địa chỉ = " + diaChi);

        OnBackPressedDispatcher dispatcher = this.getOnBackPressedDispatcher();
        dispatcher.addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("id", studentId);
                resultIntent.putExtra("name", ed_name.getText().toString());
                resultIntent.putExtra("diachi", ed_diachi.getText().toString());
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        btnSave.setOnClickListener(view -> dispatcher.onBackPressed());
    }


}
