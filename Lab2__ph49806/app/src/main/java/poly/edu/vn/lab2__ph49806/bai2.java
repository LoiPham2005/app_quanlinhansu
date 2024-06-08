package poly.edu.vn.lab2__ph49806;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

public class bai2 extends AppCompatActivity {
    EditText tilTen, tilMa, tilTuoi;
    Button btnLuu;
    RadioGroup rgGioiTinh;
    RadioButton rdNam, rdnu;
    CheckBox cbDaBong, cbChoiGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai2);

        tilTen = findViewById(R.id.input_layout_hoten);
        tilMa = findViewById(R.id.input_layout_masv);
        tilTuoi = findViewById(R.id.input_layout_tuoi);
        btnLuu = findViewById(R.id.btnLuu);
        rgGioiTinh = findViewById(R.id.rdGioiTinh);
        rdNam = findViewById(R.id.rdNam);
        rdnu = findViewById(R.id.rdNu);

        cbDaBong = findViewById(R.id.cbDaBong);
        cbChoiGame = findViewById(R.id.cbChoiGame);

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = tilTen.getText().toString();
                String ma = tilMa.getText().toString();
                String tuoi = tilTuoi.getText().toString();


                // kiểm tra rỗng thông tin
                if (ten.isEmpty() || ma.isEmpty() || tuoi.isEmpty() ) {
                    Toast.makeText(bai2.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                // kiểm tra độ dài kí tự
                if (ten.length() < 3) {
                    tilTen.setError("Tên phải có ít nhất 3 ký tự");
                    return;
                }else {
                    tilTen.setError(null);
                }

                if (ma.length() < 3) {
                    tilMa.setError("Mã phải có ít nhất 3 ký tự");
                    return;
                }else {
                    tilMa.setError(null);
                }

                int tuoiInt = Integer.parseInt(tuoi);
                if (tuoiInt < 1 || tuoiInt > 60) {
                    tilTuoi.setError("Tuổi phải từ 1 đến 60");
                    return;
                }else {
                    tilTuoi.setError(null);
                }

                // kiểm tra yêu cầu chọn giới tính
                if (rgGioiTinh.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(bai2.this, "Vui lòng chọn giới tính", Toast.LENGTH_SHORT).show();
                    return;
                }

                // kiểm tra yêu cầu chọn sở thích
                if (!cbDaBong.isChecked() && !cbChoiGame.isChecked()) {
                    Toast.makeText(bai2.this, "Vui lòng chọn ít nhất một sở thích", Toast.LENGTH_SHORT).show();
                    return;
                }

                // thông báo nhập thành công
                Toast.makeText(bai2.this,"nhập thành công", Toast.LENGTH_SHORT).show();
            }
        });


    }
}