package danhsach;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import poly.edu.vn.lab_tonghop_ph49806.R;

public class SuaThongTin extends AppCompatActivity {
    EditText edLop, edID, edName;
    TextView tv_info;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.layout_sua_thong_tin);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Tìm các view bằng ID
        edLop = findViewById(R.id.ed_lop);
//        edID = findViewById(R.id.ed_ID);
        edName = findViewById(R.id.ed_name);
        tv_info = findViewById(R.id.tv_info);
        btnSave = findViewById(R.id.btn_save);

        // Lấy dữ liệu từ Intent
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String lop = intent.getStringExtra("lop");

        // Đặt giá trị vào các EditText
//        edID.setText(id);
        edName.setText(name);
        edLop.setText(lop);

        // Hiển thị thông tin
        tv_info.setText("Sửa thông tin:\n ID: " + id + "\n Name: " + name + "\n Lớp: " + lop);

        // Xử lý sự kiện nút back
        OnBackPressedDispatcher dispatcher = this.getOnBackPressedDispatcher();
        dispatcher.addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("id", id);
                resultIntent.putExtra("name", edName.getText().toString());
                resultIntent.putExtra("lop", edLop.getText().toString());
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        // Xử lý sự kiện nút lưu
        btnSave.setOnClickListener(view -> dispatcher.onBackPressed());
    }
}
