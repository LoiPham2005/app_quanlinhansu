package phongban.themnhanvien;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import phongban.developer;
import poly.edu.vn.asm.R;

public class them_nv extends AppCompatActivity {
    EditText edtMa, edtHoTen, edtChucVu;
    Button btnTao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.layout_them_nv);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtMa = findViewById(R.id.edt_maNV);
        edtHoTen = findViewById(R.id.edt_hoten);
        edtChucVu = findViewById(R.id.edt_chucvu);
        btnTao = findViewById(R.id.btn_tao);

        btnTao.setOnClickListener(v -> {
            String ma = edtMa.getText().toString();
            String hoTen = edtHoTen.getText().toString();
            String chucVu = edtChucVu.getText().toString();

            if (ma.isEmpty() || hoTen.isEmpty() || chucVu.isEmpty()) {
                if (ma.isEmpty()) {
                    edtMa.setError("vui lòng nhập tên");
                }
                if (hoTen.isEmpty()) {
                    edtHoTen.setError("vui lòng nhập mật khẩu");
                }
                if (chucVu.isEmpty()) {
                    edtChucVu.setError("vui lòng nhập lại mật khẩu");
                }
                return;
            }

            Intent intent = new Intent(them_nv.this, developer.class);
            Bundle bundle = new Bundle();
            bundle.putString("ma",ma);
            bundle.putString("hoten",hoTen);
            bundle.putString("chucvu",chucVu);
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
        });


    }

}
