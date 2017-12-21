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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anilxpert.food.R;
import com.anilxpert.food.adapter.CuponCodeListAdapter;
import com.anilxpert.food.adapter.MalaAdapter;
import com.anilxpert.food.custom_class.MyDividerItemDecoration;
import com.anilxpert.food.custom_class.RecyclerTouchListener;
import com.anilxpert.food.dilogs.DilogCustom;
import com.anilxpert.food.loopjServcice.CmdParams;
import com.anilxpert.food.loopjServcice.ConstantField;
import com.anilxpert.food.loopjServcice.JsonDeserializer;
import com.anilxpert.food.loopjServcice.NetworkManager;
import com.anilxpert.food.models.CheckCoupModel;
import com.anilxpert.food.models.CuponCodeListModel;
import com.anilxpert.food.models.FindUsModel;
import com.anilxpert.food.utils.AppUrl;
import com.anilxpert.food.utils.SharedPref;
import com.anilxpert.food.utils.Utils;
import com.loopj.android.http.RequestParams;

import java.util.List;

/**
 * Created by AnilXpert 9887230800 on 11-Dec-17.
 */

public class CuponCodeActivity extends BaseActivity_ implements View.OnClickListener, NetworkManager.onCallback {

    private TextView activity_title;
    private Button btnCheckCode;
    private EditText edtEnterCuponCode;
    private Context mContext;
    private DilogCustom dilogCustom;
    private View.OnClickListener retryClick;
    private List<CuponCodeListModel.CouponDetail> couponDetails;

    private RecyclerView recyclerCuponCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cupon_code);
        setupToolbar(getString(R.string.coupon_code));
        mContext = CuponCodeActivity.this;
        dilogCustom = new DilogCustom();
        initialize();
        retryClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dilogCustom.dismissRetryAlert();
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            }
        };
    }

    private void initialize() {
        edtEnterCuponCode = (EditText) findViewById(R.id.edtEnterCuponCode);
        btnCheckCode = (Button) findViewById(R.id.btnCheckCode);

        btnCheckCode.setOnClickListener(this);

        recyclerCuponCode = (RecyclerView) findViewById(R.id.recyclerCuponCode);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        recyclerCuponCode.setLayoutManager(mLayoutManager);
        recyclerCuponCode.setItemAnimator(new DefaultItemAnimator());
        recyclerCuponCode.addItemDecoration(new MyDividerItemDecoration(mContext, LinearLayoutManager.VERTICAL, 0));
        recyclerCuponCode.addOnItemTouchListener(new RecyclerTouchListener(mContext, recyclerCuponCode, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                resultActivity(couponDetails.get(position).discountAmount, couponDetails.get(position).id, getString(R.string.coupon_code) + "  " + couponDetails.get(position).promoCode + "  " + getString(R.string.applied));

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        apiCall(getString(R.string.please_wait), AppUrl.GET_COUPON_CODE_LIST, null, true, ConstantField.WHITCH_2);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnCheckCode:
                if (edtEnterCuponCode.getText().length() > 3) {
                    RequestParams params = CmdParams.cuponCode(Utils.getEditText(edtEnterCuponCode));
                    apiCall(getString(R.string.please_wait), AppUrl.CHECK_COUPON_CODE, params, true, ConstantField.WHITCH_1);
                } else {
                    Toast.makeText(mContext, "Enter vaild Cuponcode. ", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    private void resultActivity(int coupon_code_discount, String couponID, String msg) {
        Activity activity = (Activity) mContext;
        Intent returnIntent = new Intent();
        returnIntent.putExtra(ConstantField.COUPON_CODE_MSG, msg);
        returnIntent.putExtra(ConstantField.COUPON_CODE_DISCOUNT, coupon_code_discount);
        returnIntent.putExtra(ConstantField.COUPON_ID, couponID);
        activity.setResult(Activity.RESULT_OK, returnIntent);
        activity.finish();
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    private void apiCall(String apiTitle, String apiUrl, RequestParams requestParams, boolean progressBar, int apiWhitch) {
        NetworkManager networkManager = new NetworkManager();
        networkManager.callAPI(mContext, ConstantField.VAL_POST, apiUrl, requestParams, apiTitle, this, progressBar, apiWhitch);
    }

    @Override
    public void onSuccess(boolean success, String response, int which) {
        if (which == ConstantField.WHITCH_1) {
            CheckCoupModel coupModel = JsonDeserializer.deserializeJson(response, CheckCoupModel.class);
            if (coupModel.status == 1) {
                //  int coupon_code_discount, String coupon_code, String msg
                resultActivity(coupModel.couponDetails.discountAmount, coupModel.couponDetails.id, getString(R.string.coupon_code) + "  " + coupModel.couponDetails.promoCode + "  " + getString(R.string.applied));
            }
        } else if (which == ConstantField.WHITCH_2) {
            CuponCodeListModel model = JsonDeserializer.deserializeJson(response, CuponCodeListModel.class);
            if (model.status == 1) {
                if (model.couponDetails != null && model.couponDetails.size() > 0) {
                    this.couponDetails = model.couponDetails;
                    CuponCodeListAdapter codeListAdapter = new CuponCodeListAdapter(mContext, model.couponDetails);
                    recyclerCuponCode.setAdapter(codeListAdapter);
                }
            }
        }
    }
    @Override
    public void onFailure(boolean success, String response, int which) {

    }
}
