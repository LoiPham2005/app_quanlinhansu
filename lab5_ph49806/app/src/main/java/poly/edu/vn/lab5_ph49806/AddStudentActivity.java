package poly.edu.vn.lab5_ph49806;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import bai2.Home;
import bai2.Student;

public class AddStudentActivity extends AppCompatActivity {
    Spinner spnSchool;
    Button btnTao;
    EditText edtHoTen, edtDiaChi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.bai1_addstudent);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnTao = findViewById(R.id.btn_taobai1);
        edtHoTen = findViewById(R.id.edt_hoten);
        edtDiaChi = findViewById(R.id.edt_diachi);
        spnSchool = findViewById(R.id.spinner_tp);


        ArrayList<School> list = new ArrayList<>();
        list.add(new School(R.drawable.hanoi, "Hà Nội "));
        list.add(new School(R.drawable.hcm, "TP Hồ Chí Minh "));
        list.add(new School(R.drawable.danang, "Đà Nẵng "));
        list.add(new School(R.drawable.taynguyen, "Tây Nguyên "));
        list.add(new School(R.drawable.cantho, "Cần Thơ "));

        SchoolSpinnerAdapter sSA = new SchoolSpinnerAdapter(this, list);
        spnSchool.setAdapter(sSA);


        spnSchool.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = list.get(position).getName();
                Toast.makeText(AddStudentActivity.this, selectedItem, Toast.LENGTH_SHORT).show();
                TextView tvName = view.findViewById(R.id.tv_name);
                tvName.setText(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnTao.setOnClickListener(v -> {
            String hoTen = edtHoTen.getText().toString();
            String diaChi = edtDiaChi.getText().toString();
            String chon = ((School) spnSchool.getSelectedItem()).getName();

            if (hoTen.isEmpty() || diaChi.isEmpty()) {
                if (hoTen.isEmpty()) {
                    edtHoTen.setError("Bạn chưa nhập họ tên");
                }
                if (diaChi.isEmpty()) {
                    edtDiaChi.setError("Bạn chưa nhập địa chỉ");
                }
                if (chon.isEmpty()) {
                    Toast.makeText(AddStudentActivity.this, "bạn chưa chọn thành phố", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            Toast.makeText(AddStudentActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(AddStudentActivity.this, Home.class);
            Bundle bundle = new Bundle();
            bundle.putString("hoten", hoTen);
            bundle.putString("diachi", diaChi);
            bundle.putString("chon", chon);
            intent.putExtras(bundle);

//            startActivity(intent);
            setResult(RESULT_OK, intent);
            finish();


        });




    }


}