package com.example.android.justjava;

import java.text.NumberFormat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int quantity = 0;

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {

        quantity = quantity + 1;
        if (quantity > 100)
            quantity = 100;
        display(quantity);

    }

    public void decrement(View view) {

        quantity = quantity - 1;
        if (quantity < 1) {
            quantity = 1;
            Toast.makeText(MainActivity.this,
                    "Your order cannot be less than 1", Toast.LENGTH_SHORT).show();
        }
        display(quantity);

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int noofcoffees = quantity;
        CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        boolean hascholate = chocolateCheckBox.isChecked();
        EditText text = (EditText) findViewById(R.id.name_field);
        String value = text.getText().toString();
        String order = ordersum(hasWhippedCream, hascholate, value);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee order for " + value);
        intent.putExtra(Intent.EXTRA_TEXT, order);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }

    public String ordersum(boolean addWhippedCream, boolean addchocolate, String val) {
        String name = val;
        String priceMessage;
        priceMessage = "Name:" + val;
        priceMessage += "\n" + "Add whipped cream:" + addWhippedCream;
        priceMessage += "\n" + "Add chocolate:" + addchocolate;
        priceMessage += "\n" + "Quantity=" + quantity + "\n";
        if (addWhippedCream == true && addchocolate == false) {
            priceMessage += ("Total:Rs. " + (quantity * (10 + 1)));
        }
        if (addchocolate == true && addWhippedCream == false) {
            priceMessage += ("Total:Rs. " + (quantity * (10 + 2)));
        }
        if (addchocolate == true && addWhippedCream == true) {
            priceMessage += ("Total:Rs. " + (quantity * (10 + 2 + 1)));
        }
        if (addchocolate == false && addWhippedCream == false) {
            priceMessage += ("Total:Rs. " + (quantity * (10)));
        }
        priceMessage += "\nThank you!";
        return priceMessage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);

    }
    /**
     *
     */

}