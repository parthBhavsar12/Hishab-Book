package com.example.myhishabbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button add;
    Button clear;
    EditText custName;
    EditText desc;
    EditText amount;
    String txtCustName, txtDesc, txtAmount, toast, text;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        custName = findViewById(R.id.custName);
        desc = findViewById(R.id.desc);
        amount = findViewById(R.id.amount);
        add = findViewById(R.id.add);
        clear = findViewById(R.id.clear);
        toast = "";
        db = new DBHelper(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtCustName = custName.getText().toString();
                txtDesc = desc.getText().toString();
                txtAmount = amount.getText().toString();
                if (txtCustName.equals("")){
                    custName.setError("Please enter Customer Name");
                    toast += "Customer Name can't be empty...";
                }
                if (txtDesc.equals("")){
                    desc.setError("Please enter Description");
                    if(toast.equals(""))
                        toast += "Description can't be empty...";
                    else
                        toast += "\nDescription can't be empty...";
                }
                if (txtAmount.equals("")){
                    amount.setError("Please enter Amount");
                    if(toast.equals(""))
                    toast += "Amount can't be empty...";
                    else
                    toast += "\nAmount can't be empty...";
                }
                if(!(txtCustName.equals("")) && !(txtDesc.equals("")) && !(txtAmount.equals(""))){
                    Boolean checkinsertdata = db.insertData(txtCustName, txtDesc, txtAmount);
                    if(checkinsertdata)
                        text = "New Entry Inserted..."+"\nCustomer Name : "+txtCustName+ "\nDescription : "+txtDesc+"\nAmount : "+txtAmount;
                    else
                        text ="New Entry Not Inserted...";

                    Toast t = Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG);
                    t.show();
                }
                else{
                    Toast.makeText(MainActivity.this, toast, Toast.LENGTH_LONG).show();
                    toast="";
                }
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                custName.setText("");
                desc.setText("");
                amount.setText("");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
//        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.checkHishab: Intent checkIntent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(checkIntent);
                break;
            case R.id.exit : AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setTitle("Exit");
                b.setIcon(R.drawable.exit);
                b.setMessage("Are you sure?");
                b.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                });
                b.setNegativeButton("No",null);
                AlertDialog alert = b.create();
                alert.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}