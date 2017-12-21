package com.anilxpert.food.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anilxpert.food.R;
import com.anilxpert.food.adapter.FindUsAdapter;
import com.anilxpert.food.adapter.OrderSummaryAdapter;
import com.anilxpert.food.custom_class.MyDividerItemDecoration;
import com.anilxpert.food.loopjServcice.ConstantField;
import com.anilxpert.food.loopjServcice.JsonDeserializer;
import com.anilxpert.food.models.SummeryModel;
import com.anilxpert.food.utils.SharedPref;
import com.anilxpert.food.utils.Utils;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by AnilXpert 9887230800 on 11/28/2017.
 */

public class OrderSummryActivity extends BaseActivity_ implements View.OnClickListener {
    private static final int GET_COUPON = 1010;
    static final int FIND_US_RESULT = 9999;

    private TextView activity_title;

    private TextView txtAddress;
    private TextView txtTimeAndDate;
    private TextView txtSpineies;
    private TextView txtDryndShop;
    private LinearLayout llCoupnLayout;
    private TextView txtSubTotal;
    private TextView txtCuponcode;
    private TextView txtCouponMsg;
    private EditText edtNote;

    private ImageView close_coupon;

    private Button btnPlaceOrder;
    private Button btnFindUs;
    private RecyclerView recyclerOrderSummery;
    private Context mContext;
    private int totalAmount = 0;
    private int discount = 0;
    private SummeryModel summeryModel;
    private int subTotal = 0;
    private String couponId = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_summery);
        setupToolbar(getString(R.string.order_summary));
        mContext = OrderSummryActivity.this;

        initialize();
        //  Toast.makeText(mContext, SharedPref.getSP(ConstantField.USER_ORDER), Toast.LENGTH_LONG).show();

    }

    private void initialize() {

        txtAddress = (TextView) findViewById(R.id.txtAddress);
        txtTimeAndDate = (TextView) findViewById(R.id.txtTimeAndDate);
        txtSpineies = (TextView) findViewById(R.id.txtSpineies);
        txtDryndShop = (TextView) findViewById(R.id.txtDryndShop);
        txtSubTotal = (TextView) findViewById(R.id.txtSubTotal);
        txtCuponcode = (TextView) findViewById(R.id.txtCuponcode);
        txtCouponMsg = (TextView) findViewById(R.id.txtCouponMsg);

        edtNote = (EditText) findViewById(R.id.edtNote);

        btnPlaceOrder = (Button) findViewById(R.id.btnPlaceOrder);
        btnFindUs = (Button) findViewById(R.id.btnFindUs);

        close_coupon = (ImageView) findViewById(R.id.close_coupon);


        llCoupnLayout = (LinearLayout) findViewById(R.id.llCoupnLayout);

        recyclerOrderSummery = (RecyclerView) findViewById(R.id.recyclerOrderSummery);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerOrderSummery.setLayoutManager(mLayoutManager);
        recyclerOrderSummery.setItemAnimator(new DefaultItemAnimator());
        recyclerOrderSummery.addItemDecoration(new MyDividerItemDecoration(mContext, LinearLayoutManager.VERTICAL, 0));


        setData();
        btnFindUs.setOnClickListener(this);
        txtCuponcode.setOnClickListener(this);
        close_coupon.setOnClickListener(this);
        btnPlaceOrder.setOnClickListener(this);
    }

    private void setData() {
        txtAddress.setText(SharedPref.getSP(ConstantField.ADDRESS_NAME));
        txtTimeAndDate.setText(SharedPref.getSP(ConstantField.DATE) + "\n" + SharedPref.getSP(ConstantField.TIME));
        txtSpineies.setText(SharedPref.getSP(ConstantField.SPICINESSLEVEL_NAME).toUpperCase());
        txtDryndShop.setText(SharedPref.getSP(ConstantField.DRYSHOP_NAME).toUpperCase());


        summeryModel = JsonDeserializer.deserializeJson(SharedPref.getSP(ConstantField.USER_ORDER), SummeryModel.class);
        if (summeryModel.data != null && summeryModel.data.size() > 0) {
            OrderSummaryAdapter mAdapter = new OrderSummaryAdapter(mContext, summeryModel.data);
            recyclerOrderSummery.setAdapter(mAdapter);
            Log.e("Price", "" + priceOrder(summeryModel.data));
            totalAmount = priceOrder(summeryModel.data);
            subTotal = totalAmount;
            setAmount(subTotal, totalAmount);
        } else {
            finish();
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
        }


    }

    public void setAmount(int amountSub, int totalAmount) {

        btnPlaceOrder.setText(getString(R.string.place_order) + "  " + getString(R.string.price) + String.valueOf(totalAmount - Utils.setDiscount(totalAmount, discount)));
        txtSubTotal.setText(getString(R.string.price) + String.valueOf(amountSub));
    }

    public void setAmountFroAdapter(int type, int amount) {
        if (type == 1)//add{
        {
            totalAmount = totalAmount + (1 * amount);
        } else if (type == 2) {//remove
            totalAmount = totalAmount - (1 * amount);
        }
        btnPlaceOrder.setText(getString(R.string.place_order) + "  " + getString(R.string.price) + String.valueOf(totalAmount - Utils.setDiscount(totalAmount, discount)));
        txtSubTotal.setText(getString(R.string.price) + String.valueOf(totalAmount));
        subTotal = totalAmount;
    }

    private int priceOrder(List<SummeryModel.Datum> data) {
        int pp = 0;
        for (int i = 0; data.size() > i; i++) {
            pp = pp + (data.get(i).price * data.get(i).qty);
        }
        return pp;
    }



    @Override
    public void onBackPressed() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txtCuponcode:
                Intent intent = new Intent(getApplicationContext(), CuponCodeActivity.class);
                startActivityForResult(intent, GET_COUPON);
                break;
            case R.id.close_coupon:
                setDiscountLayoutGone();
                break;
            case R.id.btnFindUs:
                Intent intent2 = new Intent(getApplicationContext(), FindActivity.class);
                startActivityForResult(intent2, FIND_US_RESULT);
                break;
            case R.id.btnPlaceOrder:
                if (SharedPref.getSP(ConstantField.USER_ID) != null) {
                    sendDataPayment();
                } else {
                    Utils.startActivityPutValueResultCode(mContext, LogRegActivity.class, getString(R.string.str_register), ConstantField.LOGIN_RESULT);
                }
                break;
        }
    }

    private void sendDataPayment() {
        Gson gson = new Gson();
        String orderData = gson.toJson(summeryModel);
//Context context, Class<?> class1, String data, String payPrice, String cupon_code, String cupon_percent, String notes, String total_amount
        Utils.startActivityPlaceOrder(mContext, PaymentOrder.class, orderData, String.valueOf(totalAmount - Utils.setDiscount(totalAmount, discount)), couponId, Utils.getEditText(edtNote), String.valueOf(subTotal));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_COUPON) {
            if (resultCode == Activity.RESULT_OK) {
                String coupon_code_msg = data.getStringExtra(ConstantField.COUPON_CODE_MSG);
                couponId = data.getStringExtra(ConstantField.COUPON_ID);
                discount = data.getIntExtra(ConstantField.COUPON_CODE_DISCOUNT, 0);
                setAmountFroAdapter(subTotal, totalAmount);
                setDiscountLayoutVisable(coupon_code_msg);
            }

        } else if (requestCode == FIND_US_RESULT) {
            if (resultCode == Activity.RESULT_OK) {
                txtAddress.setText(SharedPref.getSP(ConstantField.ADDRESS_NAME));
            }

        } else if (requestCode == ConstantField.LOGIN_RESULT) {
            if (resultCode == Activity.RESULT_OK) {
                sendDataPayment();
            }
        }


    }

    private void setDiscountLayoutVisable(String msg) {
        llCoupnLayout.setVisibility(View.VISIBLE);
        txtCouponMsg.setText(msg);
        txtCuponcode.setVisibility(View.GONE);

    }

    private void setDiscountLayoutGone() {
        llCoupnLayout.setVisibility(View.GONE);
        txtCouponMsg.setText("");
        txtCuponcode.setVisibility(View.VISIBLE);

        couponId = "";
        totalAmount = subTotal;
        discount = 0;
        setAmount(subTotal, totalAmount);
    }
}
