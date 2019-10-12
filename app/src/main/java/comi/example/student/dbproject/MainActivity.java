package comi.example.student.dbproject;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import comi.example.student.dbproject.Database.DBHelper;

public class MainActivity extends AppCompatActivity {

    Button btnadd,btnSelect,btnsign,btnupdate,btndel;
    EditText uname,pwd;
    DBHelper helper;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnadd = (Button) findViewById(R.id.button2);
        uname = (EditText) findViewById(R.id.editText);
        pwd = (EditText) findViewById(R.id.editText2);
        btnSelect = (Button) findViewById(R.id.button);
        btnsign = (Button) findViewById(R.id.button3);
        btndel = (Button) findViewById(R.id.button4);
        btnupdate = (Button) findViewById(R.id.button5);

        helper = new DBHelper(getApplicationContext());
        context = getApplicationContext();

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.addInfo(uname.getText().toString(),pwd.getText().toString());
                Toast.makeText(getApplicationContext(),"Data Added Successfully...",Toast.LENGTH_SHORT).show();
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List list = helper.readAllInfo();

                for (int i = 0; i<list.size();i++){
                    Log.i("Data "+i,list.get(i).toString());
                }
            }
        });

        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.readInfo(uname.getText().toString(),pwd.getText().toString(),context);
            }
        });

        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.deleteInfo(uname.getText().toString());
                Toast.makeText(context,"Deleted Successfully..",Toast.LENGTH_SHORT).show();
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.updateInfo(uname.getText().toString(),pwd.getText().toString());
                Toast.makeText(context,"Updated Successfully...",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
