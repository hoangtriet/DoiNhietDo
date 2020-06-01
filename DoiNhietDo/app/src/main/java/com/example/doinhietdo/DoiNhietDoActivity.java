package com.example.doinhietdo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class DoiNhietDoActivity extends AppCompatActivity {
    private EditText txtInput,txtOutput;
    private TextView txtNhietDo;
    private Button btnChuyenDoi,btnXoa;
    private Spinner sp;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doi_nhiet_do);

        getSupportActionBar().setTitle("Đổi nhiệt độ");
        mapping();
        setupSpinner();
        chonNhietDo();
        setupButtonDoiNhietDo();
        clearText();
    }
    public void mapping() {
        txtInput = findViewById(R.id.txtInput);
        txtOutput = findViewById(R.id.txtOutput);
        txtNhietDo = findViewById(R.id.txtNhietDo);

        btnChuyenDoi = findViewById(R.id.btnChuyenDoi);
        btnXoa = findViewById(R.id.btnXoa);

        sp = findViewById(R.id.spinner);
    }

    private void setupSpinner() {
        ArrayList<String> nhietDo = new ArrayList<String>();
        nhietDo.add("Độ C");
        nhietDo.add("Độ F");
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,nhietDo);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
    }

    public void chonNhietDo() {
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        text = Convert.TEMPERATURE.C.toString();
                        txtNhietDo.setText("Độ F");
                        doiNhietDo();
                        break;
                    case 1:
                        text = Convert.TEMPERATURE.F.toString();
                        txtNhietDo.setText("Độ C");
                        doiNhietDo();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private  void setupButtonDoiNhietDo() {
        btnChuyenDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doiNhietDo();
            }
        });
    }

    private void CtoF(double number) {
        Convert c = new Convert(number);
        double DoF = c.convertCtoF();
        txtOutput.setText(String.valueOf(DoF));
    }

    private void FtoC(double number) {
        Convert c =new Convert(number);
        double DoC = c.convertFtoC();
        txtOutput.setText(String.valueOf(DoC));
    }

    private void clearText() {
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtInput.setText("");
                txtOutput.setText("");
            }
        });
    }

    private boolean validateInput(String s){
        String numberPattern="^(\\+|-|)?([0-9]+(\\.[0-9]+|))$";
        if( TextUtils.isEmpty(s) ){
            Toast.makeText(DoiNhietDoActivity.this,"Input nhiệt độ trống",Toast.LENGTH_LONG).show();
            return false;
        }

        s=s.replaceAll("\\s+","");
        if( !Pattern.matches(numberPattern,s) ){
            Toast.makeText(DoiNhietDoActivity.this,"Phải nhập số âm hoặc dương hợp lệ",Toast.LENGTH_LONG).show();
            return false;
        }

        txtInput.setText(s);
        return true;
    }

    private void doiNhietDo(){
        String s = txtInput.getText().toString().trim();
        if(validateInput(s)==false)
            return;
        s = txtInput.getText().toString();
        double number = Double.parseDouble(s);

        if(text.equals("C"))
            CtoF(number);
        else if (text.equals("F"))
            FtoC(number);
    }
}
