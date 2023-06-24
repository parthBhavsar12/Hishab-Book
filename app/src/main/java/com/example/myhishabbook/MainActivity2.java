package com.example.myhishabbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    Button  clearData;
    DBHelper db;
    String text, total;
    TextView tv;
    double totalSum;
    AlertDialog.Builder shareAlert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        clearData = findViewById(R.id.clearData);
        db = new DBHelper(this);
        tv = findViewById(R.id.info);
        Cursor information = db.getData();
        if (information.getCount() == 0)
            tv.append("No Data Found...");
        else{
            while(information.moveToNext()){
                tv.append("Customer Name : "+information.getString(0)+"\n");
                tv.append("Description : "+information.getString(1)+"\n");
                tv.append("Amount : "+information.getDouble(2)+"\n\n");
            }
            totalSum = db.getTotal();
            tv.append("Total Sum : " + totalSum);
            total = "Total Sum : " + totalSum;
        }

        AlertDialog.Builder clearDataAlert = new AlertDialog.Builder(this);


        clearData.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clearDataAlert.setTitle("Clear All Data");
                clearDataAlert.setMessage("Are you sure?");
                clearDataAlert.setIcon(R.drawable.clear);
                clearDataAlert.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Boolean checkDeletedata = db.deleteData();
                        if(checkDeletedata)
                            text = "Data Deleted Successfully...";
                        else
                            text ="Data can't be Deleted...";

                        Toast t = Toast.makeText(MainActivity2.this, text, Toast.LENGTH_LONG);
                        t.show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);

                    }
                });
                clearDataAlert.setNegativeButton("No",null);
                AlertDialog alertClearData = clearDataAlert.create();
                alertClearData.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu2, menu);
        return true;
//        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add: Intent addIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(addIntent);
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


//share.setOnClickListener(new View.OnClickListener() {
//public void onClick(View v) {
//        shareAlert.setTitle("Check Total Sum");
//        shareAlert.setMessage("Are you sure?");
//        shareAlert.setIcon(R.drawable.check);
//
//        getTotalAlert.setTitle("Total Sum");
//        getTotalAlert.setIcon(R.drawable.total);
//        getTotalAlert.setNegativeButton("OK",null);
//
//        shareAlert.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
//public void onClick(DialogInterface dialog, int which) {
//        double share = db.getTotal();
//        getTotalAlert.setMessage((int) share);
//        AlertDialog alertGetData = getTotalAlert.create();
//        alertGetData.show();
//        }
//        });
//        shareAlert.setNegativeButton("No",null);
//        AlertDialog alertshare = shareAlert.create();
//        alertshare.show();
//        }
//        });

//    Uri webpage = Uri.parse("https://wa.me/917621088674/text=?"+tv.getText());
//    Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);

//case R.id.shareData: shareAlert = new AlertDialog.Builder(this);
//        shareAlert.setTitle("Share Data");
//        shareAlert.setIcon(R.drawable.share);
//        shareAlert.setMessage("Are you sure?");
//        shareAlert.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
//public void onClick(DialogInterface dialog, int which) {
//        Uri webpage = Uri.parse("https://wa.me/917621088674/text=?"+total);
//        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
//        startActivity(webIntent);
//        }
//        });
//        shareAlert.setNegativeButton("No",null);
//        AlertDialog alertShare = shareAlert.create();
//        alertShare.show();
//        break;