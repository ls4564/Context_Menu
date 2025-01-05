package com.example.context_menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;


public class MainActivity extends AppCompatActivity {

    EditText eD1 , eD2;
    Switch sw1;
    Intent si;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weddings();

    }


    public void weddings ()
    {
        /*
        This function wedding all the widgets
         */
        eD1 = findViewById(R.id.eD1);
        eD2 = findViewById(R.id.eD2);
        sw1 = findViewById(R.id.sw1);

    }

    public void on_switch(View view)
    {
        if(sw1.isChecked())
        {
            eD2.setHint("Enter the difference");
        }
        else
        {
            eD2.setHint("Enter multiplication factor");
        }
    }

    public void reset_views()
    {
        /*
        This function reset views in the page
         */
        sw1.setChecked(false);
        eD1.setText("");
        eD2.setText("");
    }

    public boolean check_legal_input(String input)
    {
        // true not good
        // false  good
        return input.equals(".") || input.equals("-") ;
    }

    public void next_page(View view)
    {
        String str1, str2;
        str1 = eD1.getText().toString();
        if(!check_legal_input(str1) ) {
            if (!str1.isEmpty()) {
                str2 = eD2.getText().toString();
                if(!check_legal_input(str2) )
                {
                    if (!str2.isEmpty()) {
                        //code
                        si = new Intent(this, MainActivity2.class);

                        si.putExtra("x1", Double.parseDouble(str1));//convert str -> double
                        si.putExtra("k", Double.parseDouble(str2));
                        si.putExtra("bool", sw1.isChecked());
                        startActivity(si);

                        reset_views();

                    } else {
                        Toast.makeText(this, "You need Enter a difference or multiplication factor", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(this, " You Enter a Illegal input in Ed2", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "You need Enter a number", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, " You Enter a Illegal input in Ed1", Toast.LENGTH_SHORT).show();
        }
    }
}