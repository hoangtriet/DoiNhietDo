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
        mapping();
        SetupSpinner();
        ChonNhietDo();
        doinhietdo();
        clearText();
    }
    public void mapping()
    {
        txtInput = findViewById(R.id.txtInput);
        txtOutput = findViewById(R.id.txtOutput);
        txtNhietDo = findViewById(R.id.txtNhietDo);

        btnChuyenDoi = findViewById(R.id.btnChuyenDoi);
        btnXoa = findViewById(R.id.btnXoa);

        sp = findViewById(R.id.spinner);
    }

    private void SetupSpinner() {
        ArrayList<String> nhietDo = new ArrayList<String>();
        //nhietDo.add("--Chọn nhiệt độ--");
        nhietDo.add("C");
        nhietDo.add("F");
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,nhietDo);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
    }

    public void ChonNhietDo()
    {


        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                text = sp.getSelectedItem().toString();
                String s = txtInput.getText().toString().trim().replaceAll("\\s+","");

                String numberPattern="^(\\+|-|)?([0-9]+(\\.[0-9]+|))$";
                Convert c = new Convert();
                switch (text)
                {
                    case "C": {
                        txtNhietDo.setText("Độ F");
                        if(!TextUtils.isEmpty(s))
                        {
                            double number = Double.parseDouble(s);
                            CtoF(number);
                        }
                        /*if( !Pattern.matches(numberPattern,s) ){
                            Toast.makeText(DoiNhietDoActivity.this,
                                    "Phải nhập số âm hoặc dương hợp lệ",Toast.LENGTH_LONG).show();
                            return;
                        }*/
                        break;
                    }

                    case "F":
                    {
                        txtNhietDo.setText("Độ C");
                        if(!TextUtils.isEmpty(txtInput.getText().toString().trim()))
                        {
                            double number = Double.parseDouble(s);
                            FtoC(number);

                        }
                        /*if( !Pattern.matches(numberPattern,s) ){
                            Toast.makeText(DoiNhietDoActivity.this,
                                    "Phải nhập số âm hoặc dương hợp lệ",Toast.LENGTH_LONG).show();
                            return;
                        }*/
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private  void doinhietdo()
    {
        btnChuyenDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numberPattern="^(\\+|-|)?([0-9]+(\\.[0-9]+|))$";
                String s = txtInput.getText().toString().trim();
                if( TextUtils.isEmpty(s) ){
                    Toast.makeText(DoiNhietDoActivity.this,"Input nhiệt độ trống",Toast.LENGTH_LONG).show();
                    return;
                }
                s=s.replaceAll("\\s+","");
                if( !Pattern.matches(numberPattern,s) ){
                    Toast.makeText(DoiNhietDoActivity.this,"Phải nhập số âm hoặc dương hợp lệ",Toast.LENGTH_LONG).show();
                    return;
                }
                // set lại input sau khi loại bỏ khoảng trắng nếu đúng với pattern
                txtInput.setText(s);

                if (TextUtils.isEmpty(s)) {
                    Toast.makeText(DoiNhietDoActivity.this, "Vui lòng nhập nhiệt dộ "
                            , Toast.LENGTH_SHORT).show();
                    return;
                }

                double number = Double.parseDouble(s);

                if(text == "C")
                    CtoF(number);
                else if (text == "F")
                    FtoC(number);

            }
        });
    }


    private void CtoF(double number)
    {
        Convert c = new Convert();
        c.setDoC(number);
        c.convertCtoF();
        double DoF = c.getDoF();
        DoF = Math.ceil(DoF*1000)/1000;
        txtOutput.setText(String.valueOf(DoF));
    }

    private void FtoC(double number)
    {

        Convert c =new Convert();
        c.setDoF(number);
        c.convertFtoC();
        double DoC = c.getDoC();
        DoC = Math.ceil(DoC*1000)/1000;
        txtOutput.setText(String.valueOf(DoC));
    }
    private void clearText()
    {
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtInput.setText("");
                txtOutput.setText("");
            }
        });
    }
}
