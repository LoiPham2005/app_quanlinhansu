package bai2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import poly.edu.vn.lab4_ph49806.R;

public class Activity2 extends AppCompatActivity {
    TextView tvHangCanMua;
    EditText edtBaoGia;
    Button btnBaoGia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.bai2_activity_2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvHangCanMua = findViewById(R.id.tv_hangcanmua);
        edtBaoGia = findViewById(R.id.edt_baogia);
        btnBaoGia = findViewById(R.id.btn_baogia);

        Intent intent = getIntent();
        String tenHang = intent.getStringExtra("name");
        tvHangCanMua.setText(tenHang);

        btnBaoGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String baoGia = edtBaoGia.getText().toString();
                if (baoGia.isEmpty()) {
                    tvHangCanMua.setText("Bạn chưa nhập báo giá");
                    return;
                }

                Intent intent2 = new Intent();
                intent2.putExtra("price", baoGia);

                setResult(1, intent2);
                finish();
            }
        });
    }



}