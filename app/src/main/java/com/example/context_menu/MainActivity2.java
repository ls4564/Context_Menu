package com.example.context_menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity implements AdapterView.OnItemLongClickListener
,View.OnCreateContextMenuListener{

    ListView lv;
    TextView tv1;
    Intent gi;

    double[] arr;
    int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        gi = getIntent();
        lv = findViewById(R.id.lv);
        tv1 = findViewById(R.id.tv1);


        double x1 = gi.getDoubleExtra("x1",-1);
        tv1.setText(String.valueOf(x1));

        arr = new double[20];
        boolean b1 = gi.getBooleanExtra("bool",true);
        //tv2.setText(" "  + b1);
        double k = gi.getDoubleExtra("k",0);

        give_arr(x1,k,b1);

        String[] arrStr = new String[arr.length];//הרשימה תומכת רק באובייקטים אז צריך להעביר את המערך double שהוא "טיפוס פשוט" למערך של אןבייקטים
        for (int i = 0; i < arr.length; i++)
        {
            arrStr[i] = String.format("%.2f", arr[i]);  // המרה מ-double ל-Double
        }

        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arrStr);
        lv.setAdapter(adp);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setOnItemLongClickListener(this);
        lv.setOnCreateContextMenuListener(this);
    }

    public void give_arr(double a , double k ,boolean option)
    {
        //input - the function get 2 doubles variable and one boolean, false - Engineering, true - Invoice
        //output - the function field the arr with the Invoicing or engineering series
        arr[0] = a;
        if (option)//Invoice
        {
            for (int i = 1; i < 20; i++) {
                arr[i] = arr[i-1] + k;
            }
        }
        else//Engineering
        {
            for (int i = 1; i < 20; i++)
            {
                arr[i] =  arr[i-1] * k;
            }
        }
    }

    public double sum_numbers(int index)
    {
        //input - the function get index of the arr
        //output - the function return the sum of the number between 0-index
        double sum = 0;
        for(int i = 0 ; i <= index ; i++)
        {
            sum = sum + arr[i];
        }
        return sum;
    }

    public void go_back(View view)
    {
        finish();
    }



    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        pos = position;
        return false;
    }

    public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        menu.setHeaderTitle("Sequence options");
        menu.add("n");
        menu.add("Sn");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        String action = item.getTitle().toString();
        if(action.equals("n"))
        {
            tv1.setText("" + (pos+1));
            return true;
        }
        else if (action.equals("Sn")) {
            if ((sum_numbers(pos) > 1000000) || (sum_numbers(pos) < -1000000))
            {
                tv1.setText("" + bigNumSimplifier(arr[pos]));
                return true;
            } else if(sum_numbers(pos) > -1 && sum_numbers(pos) < 1)
            {
                tv1.setText("" + sum_numbers(pos));
                return true;
            } else
            {
                tv1.setText("" + String.format("%.2f", sum_numbers(pos)));
                return true;
            }
        }
        return onContextItemSelected(item);

    }
    public String bigNumSimplifier(double value){
        String scientificNotation = String.format("%.4e", value);
        String[] parts = scientificNotation.split("e");
        double base = Double.parseDouble(parts[0]) / 10.0;
        int exponent = Integer.parseInt(parts[1]) + 1;

        return String.format("%.4f * 10^%d", base, exponent);
}

    /*
     } else if (action.equals("Sn")) {
            if ((sumValuesArr[posN] > 1000000) || (sumValuesArr[posN] < -1000000))
            {
                tv1.setText("Sn: " + bigNumSimplifier(sumValuesArr[posN]));
                return true;
            } else if(sumValuesArr[posN] > -1 && sumValuesArr[posN] < 1)
            {
                tv1.setText("Sn: " + sumValuesArr[posN]);
                return true;
            } else
            {
                tv1.setText("Sn: " + String.format("%.2f", sumValuesArr[posN]));
                return true;
            }
        }
     */


}