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

public class FaqActivity extends BaseActivity_ {

    TextView activity_title;
    TextView txtFaqOne;
    TextView txtFaqTwo;
    TextView txtFaqThree;
    TextView txtFaqFour;
    TextView txtFaqFive;


    TextView txtFaqOneAns;
    TextView txtFaqTwoAns;
    TextView txtFaqThreeAns;
    TextView txtFaqFourAns;
    TextView txtFaqFiveAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faq);
        setupToolbar(getString(R.string.n_faq));
        // threadSplace();
        initialize();
    }

    private void initialize() {


        txtFaqOne = (TextView) findViewById(R.id.txtFaqOne);
        txtFaqOneAns = (TextView) findViewById(R.id.txtFaqOneAns);

        txtFaqTwo = (TextView) findViewById(R.id.txtFaqTwo);
        txtFaqTwoAns = (TextView) findViewById(R.id.txtFaqTwoAns);

        txtFaqThree = (TextView) findViewById(R.id.txtFaqThree);
        txtFaqThreeAns = (TextView) findViewById(R.id.txtFaqThreeAns);

        txtFaqFour= (TextView) findViewById(R.id.txtFaqFour);
        txtFaqFourAns = (TextView) findViewById(R.id.txtFaqFourAns);

        txtFaqFive = (TextView) findViewById(R.id.txtFaqFive);
        txtFaqFiveAns = (TextView) findViewById(R.id.txtFaqFiveAns);
        // String s = "<h3>FAQ</h3><br><p>Why order from Xiao Man Niu Mala Xiang Guo?</p></br><br><p>Xiao Man Niu Mala Xiang Guo provides customers with an easy and quick access way to order the best takeaways and reservations orders in their area as food will then be ready when they reached our vicinity to avoid queues and waits.</p></br><br><p>Do I sign up for a account?</p></br><br><p>Yes</p></br><br><p>How does order work? </p></br> <br> <p>You can place order in our one of the food stall and collect.</p></br>        <br>  <p>How do I make payment? </p></br>        <br>  <p>You can make payment by credit card.</p></br>        <br>  <p>How do I provide feedback? </p></br>         <br> <p>Send us your enquiry and we will get back to you.</p></br>";

        txtFaqOne.setText(getResources().getString(R.string.que_one));
        txtFaqOneAns.setText(getResources().getString(R.string.ans_one));

        txtFaqTwo.setText(getResources().getString(R.string.que_two));
        txtFaqTwoAns.setText(getResources().getString(R.string.ans_two));

        txtFaqThree.setText(getResources().getString(R.string.que_three));
        txtFaqThreeAns.setText(getResources().getString(R.string.ans_three));

        txtFaqFour.setText(getResources().getString(R.string.que_foue));
        txtFaqFourAns.setText(getResources().getString(R.string.ans_foue));

        txtFaqFive.setText(getResources().getString(R.string.que_five));
        txtFaqFiveAns.setText(getResources().getString(R.string.ans_five));
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//            txtFaq.setText(Html.fromHtml(s, Html.FROM_HTML_MODE_COMPACT));
//        } else {
//            txtFaq.setText(Html.fromHtml(s));
//        }
//

    }



}