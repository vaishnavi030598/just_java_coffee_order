package com.example.justjavacoffeeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText names=findViewById(R.id.NamesEditText);
        CheckBox checkBox=findViewById(R.id.checkBox);
        CheckBox checkBox1=findViewById(R.id.checkBoxChoco);
        String name=names.getText().toString();
        boolean Checkedwhippedcream=checkBox.isChecked();
        boolean checkedChoco=checkBox1.isChecked();
        int price=calculatePrice(Checkedwhippedcream,checkedChoco);
       String priceMessage=createOrderSummary(price,Checkedwhippedcream,checkedChoco,name);



//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
//        intent.putExtra(Intent.EXTRA_EMAIL, "vaishnaviburadkar@gmail.com");
//        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJavaCoffeeOrder for "+name);
//        intent.putExtra(android.content.Intent.EXTRA_TEXT,createOrderSummary(price,Checkedwhippedcream,checkedChoco,name));
//
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJavaCoffeeOrder for "+name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Calculates the price of the order.
     *
     *
     */
    private int calculatePrice(boolean whippedcream,boolean chocolate) {
        int baseprice=5;
        if(whippedcream)
            baseprice+=1;
        if(chocolate)
            baseprice+=2;
        return quantity * baseprice;
    }

    private String createOrderSummary(int price,boolean addWhippedCream,boolean chocoCheckBox,String CostumerName)
    {
            String priceMessage=CostumerName;
            priceMessage+="\nAdd Whipped Cream? " +addWhippedCream;
            priceMessage+="\nAdd Chocolate? "+chocoCheckBox;
            priceMessage+="\nQuantity: " +quantity;
            priceMessage+="\nTotal: $" + price + "\nThank you!";
            return priceMessage;
    }

    public void increment(View view)
    {

        if(quantity==100)
        {
            Toast.makeText(this,"you cannot order more than 100 coffees",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity+1;
        display(quantity);

    }
    public void decrement(View view)
    {
        if(quantity==1)
        {
            Toast.makeText(this,"you cannot order less than one coffee",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity-1;
        display(quantity);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView .setText(message);
//    }
}



