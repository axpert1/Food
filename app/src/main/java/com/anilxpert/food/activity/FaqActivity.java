package com.anilxpert.food.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anilxpert.food.R;


/**
 * Created by this pc on 11/24/2017.
 */

public class FaqActivity extends AppCompatActivity {

    TextView activity_title;
    TextView txtFaq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faq);
        // threadSplace();
        initialize();
    }

    private void initialize() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.left);


        activity_title = (TextView) findViewById(R.id.activity_title);
        activity_title.setText(getString(R.string.n_faq));

        txtFaq = (TextView) findViewById(R.id.txtFaq);
        String s = "<h3>FAQ</h3><br><p>Why order from Xiao Man Niu Mala Xiang Guo?</p></br><br><p>Xiao Man Niu Mala Xiang Guo provides customers with an easy and quick access way to order the best takeaways and reservations orders in their area as food will then be ready when they reached our vicinity to avoid queues and waits.</p></br><br><p>Do I sign up for a account?</p></br><br><p>Yes</p></br><br><p>How does order work? </p></br> <br> <p>You can place order in our one of the food stall and collect.</p></br>        <br>  <p>How do I make payment? </p></br>        <br>  <p>You can make payment by credit card.</p></br>        <br>  <p>How do I provide feedback? </p></br>         <br> <p>Send us your enquiry and we will get back to you.</p></br>";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            txtFaq.setText(Html.fromHtml(s, Html.FROM_HTML_MODE_COMPACT));
        } else {
            txtFaq.setText(Html.fromHtml(s));
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

}