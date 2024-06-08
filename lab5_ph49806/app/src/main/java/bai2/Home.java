package bai2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import poly.edu.vn.lab5_ph49806.AddStudentActivity;
import poly.edu.vn.lab5_ph49806.R;

public class Home extends AppCompatActivity {

    private ListView lvStudents;
    private ListStudentAdapter adapter;
    private List<Student> studentList;
    Button btnThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai2_home);

        lvStudents = findViewById(R.id.lvStudents);
        studentList = new ArrayList<>();

        btnThem = findViewById(R.id.btn_them);

        btnThem.setOnClickListener(v -> launcherAddStudent.launch(new Intent(Home.this, AddStudentActivity.class)));

        populateStudentList();
        adapter = new ListStudentAdapter(this, studentList, launcherEditStudent); // Truyền cả launcherEditStudent
        lvStudents.setAdapter(adapter);
    }

    private void populateStudentList() {
        studentList.add(new Student("FPoly Hà Nội", "Nguyễn Văn B", "Lào Cai"));
        studentList.add(new Student("FPoly Đà Nẵng", "Nguyễn Văn C", "Quảng Nam"));
        studentList.add(new Student("FPoly Tây Nguyên", "Nguyễn Văn D", "Đắk Lắk"));
        studentList.add(new Student("FPoly Cần Thơ", "Nguyễn Văn E", "Kiên Giang"));
        studentList.add(new Student("FPoly Hồ Chí Minh", "Nguyễn Văn F", "Hồ Chí Minh"));
    }

    //thêm danh sách list
    private final ActivityResultLauncher<Intent> launcherAddStudent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        String hoten = data.getStringExtra("hoten");
                        String diachi = data.getStringExtra("diachi");
                        String chon = data.getStringExtra("chon");

                        if (hoten != null && diachi != null && chon != null) {
                            studentList.add(new Student(chon, hoten, diachi));
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }
    );

    // sửa danh sách list
    private final ActivityResultLauncher<Intent> launcherEditStudent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        int id = data.getIntExtra("id", -1);
                        String name = data.getStringExtra("name");
                        String diachi = data.getStringExtra("diachi");

                        for (Student student : studentList) {
                            if (student.getId() == id) { // Assuming Student class has a getId method
                                student.setName(name);
                                student.setAddress(diachi);
                                adapter.notifyDataSetChanged();
                                break;
                            }
                        }
                    }
                }
            }
    );
}
