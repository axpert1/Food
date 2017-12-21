package com.anilxpert.food.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anilxpert.food.R;
import com.anilxpert.food.adapter.OrderDetailsAdapter;
import com.anilxpert.food.adapter.OrderSummaryAdapter;
import com.anilxpert.food.custom_class.MyDividerItemDecoration;
import com.anilxpert.food.loopjServcice.CmdParams;
import com.anilxpert.food.loopjServcice.ConstantField;
import com.anilxpert.food.loopjServcice.JsonDeserializer;
import com.anilxpert.food.loopjServcice.NetworkManager;
import com.anilxpert.food.models.OrderDetailsModel;
import com.anilxpert.food.models.SummeryModel;
import com.anilxpert.food.utils.AppUrl;
import com.anilxpert.food.utils.SharedPref;
import com.anilxpert.food.utils.Utils;
import com.loopj.android.http.RequestParams;

/**
 * Created by AnilXpert 9887230800 on 13-Dec-17.
 */

public class OrderDetails extends BaseActivity_ implements NetworkManager.onCallback {
    private Context mContext;

    private TextView txtAddress;
    private TextView txtTimeAndDate;
    private TextView txtSpineies;
    private TextView txtDryndShop;
    private TextView txtScane;
    private TextView note;
    private LinearLayout llCoupnLayout;
    private TextView txtSubTotal;
    private TextView btnPayamount;


    private RecyclerView recyclerOrderSummery;

    private SummeryModel summeryModel;
    private String intentData;

    private String qrCode = "";


    public static int CAMERA_CODE = 1001;
    private RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details);

        mContext = OrderDetails.this;
        intentData = Utils.getintentString(ConstantField.INTENT_1, getIntent());
        setupToolbar(getString(R.string.app_name));
        initialize();

    }

    private void initialize() {
        txtAddress = (TextView) findViewById(R.id.txtAddress);
        txtTimeAndDate = (TextView) findViewById(R.id.txtTimeAndDate);
        txtSpineies = (TextView) findViewById(R.id.txtSpineies);
        txtDryndShop = (TextView) findViewById(R.id.txtDryndShop);
        txtSubTotal = (TextView) findViewById(R.id.txtSubTotal);
        txtScane = (TextView) findViewById(R.id.txtScane);
        note = (TextView) findViewById(R.id.note);

        layout = (RelativeLayout) findViewById(R.id.layout);


        btnPayamount = (TextView) findViewById(R.id.btnPayamount);


        llCoupnLayout = (LinearLayout) findViewById(R.id.llCoupnLayout);

        recyclerOrderSummery = (RecyclerView) findViewById(R.id.recyclerOrderSummery);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerOrderSummery.setLayoutManager(mLayoutManager);
        recyclerOrderSummery.setItemAnimator(new DefaultItemAnimator());
        recyclerOrderSummery.addItemDecoration(new MyDividerItemDecoration(mContext, LinearLayoutManager.VERTICAL, 0));
        setData();
        txtScane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.startActivityPutValue(mContext, ShowQrCode.class, qrCode);
                //camerOpenFronQRcode();
            }
        });
    }

    private void setData() {
        RequestParams params = CmdParams.orderDetails(intentData);
        apiCall(getString(R.string.please_wait), AppUrl.GET_ORDER_DETAILS, params, true, ConstantField.WHITCH_1);
    }

    private void apiCall(String apiTitle, String apiUrl, RequestParams requestParams, boolean progressBar, int apiWhitch) {
        NetworkManager networkManager = new NetworkManager();
        networkManager.callAPI(mContext, ConstantField.VAL_POST, apiUrl, requestParams, apiTitle, this, progressBar, apiWhitch);
    }

    @Override
    public void onSuccess(boolean success, String response, int which) {
        OrderDetailsModel detailsModel = JsonDeserializer.deserializeJson(response, OrderDetailsModel.class);
        if (detailsModel.status == 1) {
            qrCode = detailsModel.orderDetails.orderId;
            txtAddress.setText(detailsModel.orderDetails.locations.landMark + ", " + detailsModel.orderDetails.locations.location + ", " + detailsModel.orderDetails.locations.pincode + "\n" + detailsModel.orderDetails.locations.city + ", " + detailsModel.orderDetails.locations.state + ", " + detailsModel.orderDetails.locations.country);
            txtTimeAndDate.setText(detailsModel.orderDetails.date + "\n" + detailsModel.orderDetails.time);
            txtSpineies.setText(detailsModel.orderDetails.spicenessLevel.toUpperCase());
            txtDryndShop.setText(detailsModel.orderDetails.dryOrSoup.toUpperCase());
            txtSubTotal.setText(getString(R.string.price) + detailsModel.orderDetails.totalAmount);
            btnPayamount.setText(getString(R.string.payableAmount) + detailsModel.orderDetails.restAmount);
            note.setText("Note: " + detailsModel.orderDetails.notes);
            layout.setVisibility(View.VISIBLE);

            summeryModel = JsonDeserializer.deserializeJson(detailsModel.orderDetails.orderJson, SummeryModel.class);
            if (summeryModel.data != null && summeryModel.data.size() > 0) {
                OrderDetailsAdapter mAdapter = new OrderDetailsAdapter(mContext, summeryModel.data);
                recyclerOrderSummery.setAdapter(mAdapter);

            }
        }
    }

    @Override
    public void onFailure(boolean success, String response, int which) {

    }

    private void camerOpenFronQRcode() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG", "Permission is granted");
                Utils.startActivity(mContext, ScaneCode.class);
            } else {
                Log.v("TAG", "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_CODE);

            }
        } else {
            Utils.startActivity(mContext, ScaneCode.class);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("requestCode", "" + requestCode);
        if (requestCode == CAMERA_CODE) {

            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(mContext, "granted", Toast.LENGTH_SHORT).show();
                Utils.startActivity(mContext, ScaneCode.class);
            } else {
                Toast.makeText(mContext, "cancel", Toast.LENGTH_SHORT).show();
            }
            return;
        }

    }
}
