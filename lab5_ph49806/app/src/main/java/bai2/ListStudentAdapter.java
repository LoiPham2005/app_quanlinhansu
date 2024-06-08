package bai2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;

import java.util.List;

import poly.edu.vn.lab5_ph49806.R;

public class ListStudentAdapter extends BaseAdapter {
    private Context context;
    private List<Student> studentList;
    private ActivityResultLauncher<Intent> launcherEditStudent;

    public ListStudentAdapter(Context context, List<Student> studentList, ActivityResultLauncher<Intent> launcherEditStudent) {
        this.context = context;
        this.studentList = studentList;
        this.launcherEditStudent = launcherEditStudent;
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        if (convertView != null) {
            row = convertView;
        } else {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.bai2_list_view, parent, false);
        }

        Student student = studentList.get(position);

        TextView tvBranch = row.findViewById(R.id.tv_branch);
        TextView tvName = row.findViewById(R.id.tv_name);
        TextView tvAddress = row.findViewById(R.id.tv_diachi);
        Button btnUpdate = row.findViewById(R.id.btn_update);
        Button btnDelete = row.findViewById(R.id.btn_delete);

        tvBranch.setText(student.getBranch());
        tvName.setText("Họ tên: " + student.getName());
        tvAddress.setText("Địa chỉ: " + student.getAddress());

        btnDelete.setOnClickListener(v -> confirmDelete(position));

        btnUpdate.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditLopActivity.class);
            intent.putExtra("id", student.getId());
            intent.putExtra("name", student.getName());
            intent.putExtra("diachi", student.getAddress());
            launcherEditStudent.launch(intent);
        });

        return row;
    }

    private void confirmDelete(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xác nhận xóa");
        builder.setMessage("Bạn có chắc chắn muốn xóa?");

        builder.setPositiveButton("Đồng ý xoá", (dialog, which) -> {
            studentList.remove(position);
            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
            notifyDataSetChanged();
        });

        builder.setNegativeButton("Không xoá", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
