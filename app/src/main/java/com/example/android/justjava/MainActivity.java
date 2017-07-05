package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {
    public int no=2,rate=5;
    public boolean b=false;
    String name2,name1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox checkBox =(CheckBox) findViewById(R.id.checkBox);
        if(checkBox.isChecked())
        {
            rate=7;
            b=true;
        }
        else
        {
            b=false;
            rate=5;
        }
        EditText name=(EditText) findViewById(R.id.editText);
        name1=name.getText().toString();
        name2="Name:"+name1 +"\nCream added ???      "+b+ "\nQuantity:" + no + "\nprice:" + no * rate + "\nThank you...";
        display(name2);
    }
    public void increment(View view)
    {
        no+=1;
        displayCount(no);
    }
    public void decrement(View view)
    {
        if (no>1)
        no-=1;
        displayCount(no);

    }
    public void passIntent(View view)
    {
        Intent intent =new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(intent.EXTRA_CC,"coffee@gmail.com");
        intent.putExtra(intent.EXTRA_SUBJECT,"Coffee order for "+name1);
        intent.putExtra(intent.EXTRA_TEXT,""+name2);
        if(intent.resolveActivity(getPackageManager())!=null)
        {
            startActivity(intent);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(String details) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_summary_text_view);
        quantityTextView.setText(details);
    }
    private void displayCount(int number) {
        TextView quantitySummaryTextView = (TextView) findViewById(
                R.id.price);
        quantitySummaryTextView.setText("" + number);

    }

}