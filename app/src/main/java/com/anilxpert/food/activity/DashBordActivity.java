package com.anilxpert.food.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anilxpert.food.R;
import com.anilxpert.food.dilogs.DilogCustom;
import com.anilxpert.food.loopjServcice.ConstantField;
import com.anilxpert.food.utils.SharedPref;
import com.anilxpert.food.utils.Utils;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.loopj.android.http.RequestParams;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DashBordActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BaseSliderView.OnSliderClickListener, View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private DrawerLayout drawer = null;


    // Text view
    private TextView activity_title;
    private TextView myorder;
    private TextView myprofile;
    private TextView find;
    private TextView contect_us;
    private TextView faq;
    private TextView privacy_policy;
    private TextView terms_of_use;
    private Button sign_in;
    private Button register;
    private Button logout;


    //constant
    private boolean doubleBackToExitPressedOnce;

    //home data
    private SliderLayout mDemoSlider;
    private TextView selectOutlet;
    private Context mContext;
    private Button btnProceed;
    private RelativeLayout relative1;

    private DilogCustom dilogCustom;

    private TextView txtDate;
    private TextView txtTime;
    private TextView txtAddress;

    private ImageView imgClose;

    static final int FIND_US_RESULT = 9999;
    private int hour;
    private int minute;

    private String format = "";
    private String date = null;
    private String time = null;
    private int timeHH;


    private int CODE_PROFILE = 1001;
    private int CODE_MYORDER = 1002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        mContext = DashBordActivity.this;
        dilogCustom = new DilogCustom();
        // Utils.gethashKey(getApplicationContext());
        initialize();
        clearSP();
    }

    public void clearSP() {
        SharedPref.removeSP(ConstantField.FIND_US);
        SharedPref.removeSP(ConstantField.DATE);
        SharedPref.removeSP(ConstantField.TIME);
    }

    private void initialize() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        activity_title = (TextView) findViewById(R.id.activity_title);
        activity_title.setText(getString(R.string.app_name));

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        myprofile = (TextView) findViewById(R.id.myprofile);
        register = (Button) findViewById(R.id.register);
        sign_in = (Button) findViewById(R.id.sign_in);
        logout = (Button) findViewById(R.id.logout);
        myorder = (TextView) findViewById(R.id.myorder);
        find = (TextView) findViewById(R.id.find);
        contect_us = (TextView) findViewById(R.id.contect_us);
        faq = (TextView) findViewById(R.id.faq);
        terms_of_use = (TextView) findViewById(R.id.terms_of_use);
        privacy_policy = (TextView) findViewById(R.id.privacu_policy);


        myprofile.setOnClickListener(this);

        myorder.setOnClickListener(this);
        find.setOnClickListener(this);
        contect_us.setOnClickListener(this);
        faq.setOnClickListener(this);
        terms_of_use.setOnClickListener(this);
        privacy_policy.setOnClickListener(this);

        register.setOnClickListener(this);
        sign_in.setOnClickListener(this);
        logout.setOnClickListener(this);

        loginCheck();

        //dashbord
        Calendar calander = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
        timeHH = Integer.parseInt(simpleDateFormat.format(calander.getTime()));

        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        int h = Calendar.getInstance().get(Calendar.HOUR);

        slidingImage(getList());
        selectOutlet = (TextView) findViewById(R.id.select_outlet);

        txtDate = (TextView) findViewById(R.id.txtDate);
        txtTime = (TextView) findViewById(R.id.txtTime);

        txtAddress = (TextView) findViewById(R.id.txtAddress);
        imgClose = (ImageView) findViewById(R.id.imgClose);

        selectOutlet = (TextView) findViewById(R.id.select_outlet);
        selectOutlet.setOnClickListener(this);
        relative1 = (RelativeLayout) findViewById(R.id.relative1);
        btnProceed = (Button) findViewById(R.id.btnProceed);
        btnProceed.setOnClickListener(this);


        txtDate.setOnClickListener(this);
        txtTime.setOnClickListener(this);
        imgClose.setOnClickListener(this);
    }

    private List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("http://thesmartlocal.com/images/easyblog_images/2088/Burger-Grp-Shot_4601.jpg");
        list.add("http://thesmartlocal.com/images/easyblog_images/2088/Burger-Grp-Shot_4601.jpg");
        list.add("http://thesmartlocal.com/images/easyblog_images/2088/Burger-Grp-Shot_4601.jpg");
        list.add("http://thesmartlocal.com/images/easyblog_images/2088/Burger-Grp-Shot_4601.jpg");
        return list;
    }

    private void visableAddress() {
        txtAddress.setText(SharedPref.getSP(ConstantField.ADDRESS_NAME));
        txtDate.setText(SharedPref.getSP(ConstantField.DATE));
        txtTime.setText(SharedPref.getSP(ConstantField.TIME));
        relative1.setVisibility(View.VISIBLE);
        btnProceed.setVisibility(View.VISIBLE);
    }

    private void hideAddress() {
        relative1.setVisibility(View.GONE);
        btnProceed.setVisibility(View.GONE);
    }

    public void slidingImage(List<String> datums) {
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        mDemoSlider.removeAllSliders();
        for (int i = 0; i < datums.size(); i++) {
            DefaultSliderView textSliderView = new DefaultSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(null)
                    .image(datums.get(i))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            //add your extra information
            textSliderView.bundle(new Bundle());
            //    textSliderView.setOnSliderClickListener(this);
            textSliderView.getBundle()
                    .putString("extra", "anil");
            mDemoSlider.addSlider(textSliderView);

        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setCustomIndicator((PagerIndicator) findViewById(R.id.custom_indicator));
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(5000);
        // mDemoSlider.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        backCountToExit();
    }

    private void backCountToExit() {
        if (doubleBackToExitPressedOnce) {
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
            finish();
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    private void drawerClose() {
        if (drawer != null) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.drawer, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the HomeFragment/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {


            case R.id.myprofile:
                drawerClose();
                if (SharedPref.getSP(ConstantField.USER_ID) != null) {
                    Utils.startActivity(mContext, ProfileActivity.class);
                } else {
                    Utils.startActivityPutValueResultCode(mContext, LogRegActivity.class, getString(R.string.str_register), CODE_PROFILE);
                }

                break;
            case R.id.myorder:
                drawerClose();
                if (SharedPref.getSP(ConstantField.USER_ID) != null) {
                    Utils.startActivity(mContext, MyOrderActivity.class);

                } else {
                    Utils.startActivityPutValueResultCode(mContext, LogRegActivity.class, getString(R.string.str_register), CODE_MYORDER);
                }

                break;
            case R.id.find:
                drawerClose();
                Utils.startActivity(mContext, FindActivity.class);
                break;
            case R.id.contect_us:
                drawerClose();
                Utils.startActivity(mContext, ContectUsActivity.class);
                break;
            case R.id.faq:
                drawerClose();
                Utils.startActivity(mContext, FaqActivity.class);
                break;
            case R.id.terms_of_use:
                drawerClose();
                Utils.startActivity(mContext, TermsActivity.class);
                break;
            case R.id.privacu_policy:
                drawerClose();
                Utils.startActivity(mContext, PolicyActivity.class);
                break;
            case R.id.sign_in:
                drawerClose();
                Utils.startActivityPutValue(DashBordActivity.this, LogRegActivity.class, getString(R.string.str_sign_in));

                break;
            case R.id.register:
                drawerClose();
                Utils.startActivityPutValue(DashBordActivity.this, LogRegActivity.class, getString(R.string.str_register));

                break;
            case R.id.logout:
                drawerClose();
                SharedPref.clearSp();
                Utils.clearPriviousActivity(DashBordActivity.this, Splace.class);

                break;
            case R.id.select_outlet:
                drawerClose();
                // Utils.startActivity(getContext(), OrderSelectActivity.class);
                if (SharedPref.getSP(ConstantField.DATE) == null || SharedPref.getSP(ConstantField.TIME) == null) {
                    String msg = "";
                    if (SharedPref.getSP(ConstantField.DATE) == null && SharedPref.getSP(ConstantField.TIME) == null) {
                        msg = "* Select date and time";
                    } else if (SharedPref.getSP(ConstantField.DATE) == null) {
                        msg = "* Select date";
                    } else if (SharedPref.getSP(ConstantField.TIME) == null) {
                        msg = "* Select time";
                    }
                    dilogCustom.retryAlertDialog(mContext, getString(R.string.app_name), msg, getString(R.string.cancel), "", this);

                } else {
                    Intent intent = new Intent(getApplicationContext(), FindActivity.class);
                    startActivityForResult(intent, FIND_US_RESULT);
                }

                break;
            case R.id.btnProceed:
                Utils.startActivity(mContext, OrderSelectActivity.class);
                break;
            case R.id.txtDate:
                java.util.Calendar now = java.util.Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        this,
                        now.get(java.util.Calendar.YEAR),
                        now.get(java.util.Calendar.MONTH),
                        now.get(java.util.Calendar.DAY_OF_MONTH)
                );
                dpd.setMinDate(now);
                dpd.setAccentColor(Color.parseColor("#ff5103"));
                dpd.show(getFragmentManager(), "Select Date");
                break;
            case R.id.txtTime:
                if (SharedPref.getSP(ConstantField.DATE) == null) {
                    dilogCustom.retryAlertDialog(mContext, getString(R.string.app_name), "Select date", getString(R.string.cancel), "", this);

                } else {
//
////
//                    Calendar today = Calendar.getInstance();
//                    int month = today.get(Calendar.MONTH) + 1;
//                    int year = today.get(Calendar.YEAR);
//                    int dates = today.get(Calendar.DAY_OF_MONTH);
//                    //  Toast.makeText(mContext, "" + dates + "-" + month + "-" + year, Toast.LENGTH_SHORT).show();
//
//                    if (date.equals(Utils.getDateWithFormat(dates, month, year))) {
////                    final CharSequence[] items = {"11:00am", "12:00pm", "1:00pm", "2:00pm", "3:00pm", "4:00pm", "5:00pm", "6:00pm", "7:00pm"};
//                        if (timeHH > 10 || timeHH < 21) {
//                            alerts(listItems().toArray(new CharSequence[listItems().size()]));
//                        }
////                        else {
////                            dilogCustom.retryAlertDialog(mContext, getString(R.string.app_name), "Select vlaid date", getString(R.string.cancel), "", this);
////
////                        }
//
//                    } else {
//                        alerts(listItemsAllday().toArray(new CharSequence[listItemsAllday().size()]));
//
//                    }
                    java.util.Calendar time = java.util.Calendar.getInstance();
                    TimePickerDialog ttp = TimePickerDialog.newInstance(
                            this,
                            time.get(java.util.Calendar.HOUR_OF_DAY),
                            time.get(java.util.Calendar.MINUTE),
                            false
                    );
                    setMinMaxTime(ttp);

                    ttp.setAccentColor(Color.parseColor("#ff5103"));
                    ttp.show(getFragmentManager(), "Time");
                }


                break;
            case R.id.imgClose:
                SharedPref.putboolSP(ConstantField.FIND_US, false);
                SharedPref.removeSP(ConstantField.ADDRESS_NAME);

                hideAddress();
                break;
        }


    }

    private void loginCheck() {
        if (SharedPref.getSP(ConstantField.USER_ID) == null) {
            register.setVisibility(View.VISIBLE);
            sign_in.setVisibility(View.VISIBLE);
            logout.setVisibility(View.GONE);
        } else {
            register.setVisibility(View.GONE);
            sign_in.setVisibility(View.GONE);
            logout.setVisibility(View.VISIBLE);
        }

    }

    public String showTime(int hour, int min) {
        StringBuilder stringBuilder = new StringBuilder();
        if (hour == 0) {
            hour += 12;
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }
        stringBuilder.append(String.format("%02d:%02d", hour, min)).append(format);

        return stringBuilder.toString();
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        showDate(year, monthOfYear + 1, dayOfMonth);
    }

    private void showDate(int year, int month, int day) {
        txtDate.setText(Utils.getDateWithFormat(day, month, year));
        date = Utils.getDateWithFormat(day, month, year);
        SharedPref.putSP(ConstantField.DATE, date);

    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int selectedHour, int selectedMinute, int second) {
        txtTime.setText(showTime(selectedHour, selectedMinute));
        time = showTime(selectedHour, selectedMinute);
        SharedPref.putSP(ConstantField.TIME, time);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (txtAddress != null) {
            loginCheck();
            txtAddress.setText(SharedPref.getSP(ConstantField.ADDRESS_NAME));
        }
    }

    private void setMinMaxTime(TimePickerDialog ttp) {
        Calendar today = Calendar.getInstance();
        int month = today.get(Calendar.MONTH) + 1;
        int year = today.get(Calendar.YEAR);
        int dates = today.get(Calendar.DAY_OF_MONTH);
        if (SharedPref.getSP(ConstantField.DATE).equals(Utils.getDateWithFormat(dates, month, year))) {
            Date dt = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dt);
            minTime(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
            ttp.setMinTime(hhMin, mmMin, 0);
            ttp.setMaxTime(21, 00, 0);
        } else {
            ttp.setMinTime(11, 00, 0);
            ttp.setMaxTime(21, 00, 0);
        }
    }

    int hhMin = 0;
    int mmMin = 0;

    private void minTime(int hour, int min) {
        int hh = hour;
        int mm = min;
        if (min > 30) {
            if (min > 40) {
                hhMin = hh + 1;
                mmMin = 00 + 30;
            } else {
                hhMin = hh + 1;
                mmMin = 00;
            }

        } else {
            hhMin = hh;
            mmMin = mm + 30;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FIND_US_RESULT) {
            if (resultCode == Activity.RESULT_OK) {
                //String anil = data.getStringExtra("anil");
                if (SharedPref.getboolSP(ConstantField.FIND_US)) {
                    visableAddress();
                } else {
                    hideAddress();
                }
            }

        } else if (requestCode == CODE_PROFILE) {
            if (resultCode == Activity.RESULT_OK) {
                Utils.startActivity(mContext, ProfileActivity.class);
            }

        } else if (requestCode == CODE_MYORDER) {

            if (resultCode == Activity.RESULT_OK) {
                Utils.startActivity(mContext, MyOrderActivity.class);

            }
        }


    }

}
