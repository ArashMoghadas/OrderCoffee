package com.example.asus.ordercoffe;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int count;
    private int price = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void plusClick(View view) {
        /* TextView countTextView = findViewById(R.id.count);
        String coutValue = countTextView.getText().toString();
        int count = Integer.parseInt(coutValue);
        if(count < 20) {
            count++;
        }

        coutValue = Integer.toString(count);
        countTextView.setText(coutValue); */

        increase();
        display();
    }


    public void minesClick(View view) {
        /* TextView countTextView = findViewById(R.id.count);
        String coutValue = countTextView.getText().toString();
        int count = Integer.parseInt(coutValue);
        if (count > 0) {
            count--;
        }

        coutValue = Integer.toString(count);
        countTextView.setText(coutValue); */

        decrease();
        display();

    }

    public void orderClick(View view) {
        order();
    }


    public int calculatePrice(){
        return price * count;
    }

    public void increase(){
        if (count < 20){
            count++;
        }
    }

    public void decrease(){
        if(count > 0){
            count--;
        }
    }

    public void display(){
        TextView countTextView = findViewById(R.id.count);
        countTextView.setText(String.valueOf(count));
    }

    public void order(){

        TextView priceTextView = findViewById(R.id.price);
        priceTextView.setText("Total Price is: " + calculatePrice());
        priceTextView.setVisibility(View.VISIBLE);

        TextView summaryTextView = findViewById(R.id.summ);
        summaryTextView.setText("Customer Infos are: \n" + makeSummary());
        summaryTextView.setVisibility(View.VISIBLE);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, "mooseskywalker@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order Coffee Info");
        intent.putExtra(Intent.EXTRA_TEXT, makeSummary());

        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }

    }

    private String makeSummary(){

        String summary = "";

        EditText nameEditText = findViewById(R.id.name);
        summary += "Name: " + nameEditText.getText().toString() + "\n";

        EditText addressEditText = findViewById(R.id.address);
        summary += "Address: " + addressEditText.getText().toString() + "\n";

        CheckBox sugerCheckBox = findViewById(R.id.suger);
        summary += "Suger: " + sugerCheckBox.isChecked() + "\n";

        CheckBox milkCheckBox = findViewById(R.id.milk);
        summary += "Milk: " + milkCheckBox.isChecked() + "\n";

        return summary;

    }

    public void callClick(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel: " + "09153184568"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void smsClick(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:"));
        intent.putExtra("sms_body", "message");
        intent.putExtra(Intent.EXTRA_STREAM, "attachment");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    public void mapClick(View view) {
        Uri uri = Uri.parse("geo:47.6,-122.3");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
