package phongban.suanhanvien;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import phongban.dulieuchung.NhanVien;
import poly.edu.vn.asm.R;

public class suanhanvien extends AppCompatActivity {
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sua_nhan_vien);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        btnUpdate = findViewById(R.id.btn_sua);
//        // Bổ sung sự kiện cho nút sửa
//        btnUpdate.setOnClickListener(v -> {
//            // Gọi phương thức sửa thông tin nhân viên
//            editNhanVien(position);
//        });
    }

//    private void editNhanVien(int position) {
//        NhanVien nhanVien = list.get(position);
//
//        // Tạo Intent để mở giao diện sửa thông tin nhân viên
//        Intent intent = new Intent(context, SuaNhanVienActivity.class);
//        // Đính kèm thông tin nhân viên cần sửa vào Intent
//        intent.putExtra("ma", nhanVien.getMaNV());
//        intent.putExtra("ten", nhanVien.getHoTen());
//        intent.putExtra("chucvu", nhanVien.getChucVu());
//        // Khởi động activity để sửa thông tin nhân viên
//        context.startActivity(intent);
//    }
}