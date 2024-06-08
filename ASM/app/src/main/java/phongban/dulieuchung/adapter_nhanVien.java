package phongban.dulieuchung;

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

import java.util.ArrayList;

import phongban.suanhanvien.suanhanvien;
import poly.edu.vn.asm.R;

public class adapter_nhanVien extends BaseAdapter {
    Context context;
    ArrayList<NhanVien> list;
    ActivityResultLauncher<Intent> launcher;

//    public adapter_nhanVien(Context context, ArrayList<NhanVien> list) {
//        this.context = context;
//        this.list = list;
//    }

    public adapter_nhanVien(Context context, ArrayList<NhanVien> list, ActivityResultLauncher<Intent> launcher) {
        this.context = context;
        this.list = list;
        this.launcher = launcher;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.developer_phu, parent, false);

            TextView tvID = convertView.findViewById(R.id.tv_maNV);
            TextView tvName = convertView.findViewById(R.id.tv_name);
            TextView tvAddress = convertView.findViewById(R.id.tv_chucvu);
            Button btnUpdate = convertView.findViewById(R.id.btn_update);
            Button btnDelete = convertView.findViewById(R.id.btn_delete);

            NhanVien devo = list.get(position);

            tvID.setText("Mã: "+devo.getMaNV());
            tvName.setText("Họ tên: "+devo.getHoTen());
            tvAddress.setText("Chức vụ: "+devo.getChucVu());

            btnDelete.setOnClickListener(v -> confirmDelete(position));

            btnUpdate.setOnClickListener(v -> {
                // Gọi phương thức sửa thông tin nhân viên
                editNhanVien(position);
            });
            

        }
        return convertView;
    }

    private void editNhanVien(int position) {
        NhanVien nhanVien = list.get(position);

        // Tạo Intent để mở giao diện sửa thông tin nhân viên
        Intent intent = new Intent(context, suanhanvien.class);
        // Đính kèm thông tin nhân viên cần sửa vào Intent
        intent.putExtra("ma", nhanVien.getMaNV());
        intent.putExtra("ten", nhanVien.getHoTen());
        intent.putExtra("chucvu", nhanVien.getChucVu());
        // Khởi động activity để sửa thông tin nhân viên
//        context.startActivity(intent);

    }

    private void confirmDelete(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xác nhận xóa");
        builder.setMessage("Bạn có chắc chắn muốn xóa?");

        builder.setPositiveButton("Đồng ý xoá", (dialog, which) -> {
            list.remove(position);
            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
            notifyDataSetChanged();
        });

        builder.setNegativeButton("Không xoá", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
