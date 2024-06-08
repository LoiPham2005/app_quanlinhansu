package bai2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import poly.edu.vn.lab4_ph49806.R;

public class Activity1 extends AppCompatActivity {
    EditText edtMuonMua;
    Button btnGui;
    TextView tvGiaSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.bai2_activity_1);

        edtMuonMua = findViewById(R.id.edt_muonmua);
        btnGui = findViewById(R.id.btn_gui);
        tvGiaSP = findViewById(R.id.tv_gia);

        ActivityResultLauncher<Intent> getQuote = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                if (o.getResultCode() ==1){
                    Intent intent = o.getData();
                    if(intent != null){
                        String price = intent.getStringExtra("price");
                        tvGiaSP.setText(price);
                    }
                }
            }
        });

        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String muonGui = edtMuonMua.getText().toString();

                if (muonGui.isEmpty()) {
                    edtMuonMua.setError("Bạn chưa nhập gì cả");
                    return;
                }

                if(muonGui.length()<3){
                    edtMuonMua.setError("phải trên 3 kí tự");
                    return;
                }

                Intent intent = new Intent(Activity1.this, Activity2.class);
                intent.putExtra("name", muonGui);
                getQuote.launch(intent);
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
}