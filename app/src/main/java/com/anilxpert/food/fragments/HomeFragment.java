package com.anilxpert.food.fragments;

import com.anilxpert.food.dilogs.DilogCustom;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import android.app.Dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.anilxpert.food.R;
import com.anilxpert.food.activity.DashBordActivity;
import com.anilxpert.food.activity.LogRegActivity;
import com.anilxpert.food.activity.OrderSelectActivity;
import com.anilxpert.food.loopjServcice.ConstantField;
import com.anilxpert.food.utils.SharedPref;
import com.anilxpert.food.utils.Utils;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by ANDROID.
 */

public class HomeFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

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

    static final int TIME_DIALOG_ID = 999;
    private int hour;
    private int minute;

    private String format = "";
    private String date = null;
    private String time = null;
    private int timeHH;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_home, container, false);
        mContext = getActivity();
        dilogCustom = new DilogCustom();
        Calendar calander = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
        timeHH = Integer.parseInt(simpleDateFormat.format(calander.getTime()));

        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        int h = Calendar.getInstance().get(Calendar.HOUR);

        slidingImage(getList(), view);
        selectOutlet = (TextView) view.findViewById(R.id.select_outlet);

        txtDate = (TextView) view.findViewById(R.id.txtDate);
        txtTime = (TextView) view.findViewById(R.id.txtTime);

        txtAddress = (TextView) view.findViewById(R.id.txtAddress);
        imgClose = (ImageView) view.findViewById(R.id.imgClose);

        selectOutlet = (TextView) view.findViewById(R.id.select_outlet);
        selectOutlet.setOnClickListener(this);
        relative1 = (RelativeLayout) view.findViewById(R.id.relative1);
        btnProceed = (Button) view.findViewById(R.id.btnProceed);
        btnProceed.setOnClickListener(this);
        if (SharedPref.getboolSP(ConstantField.FIND_US)) {
            visableAddress();
        } else {
            hideAddress();
        }

        txtDate.setOnClickListener(this);
        txtTime.setOnClickListener(this);

        imgClose.setOnClickListener(this);
        return view;
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

    private List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("http://thesmartlocal.com/images/easyblog_images/2088/Burger-Grp-Shot_4601.jpg");
        list.add("http://thesmartlocal.com/images/easyblog_images/2088/Burger-Grp-Shot_4601.jpg");
        list.add("http://thesmartlocal.com/images/easyblog_images/2088/Burger-Grp-Shot_4601.jpg");
        list.add("http://thesmartlocal.com/images/easyblog_images/2088/Burger-Grp-Shot_4601.jpg");
        return list;
    }

    public void slidingImage(List<String> datums, View view) {
        mDemoSlider = (SliderLayout) view.findViewById(R.id.slider);
        mDemoSlider.removeAllSliders();
        for (int i = 0; i < datums.size(); i++) {
            DefaultSliderView textSliderView = new DefaultSliderView(getContext());
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
        mDemoSlider.setCustomIndicator((PagerIndicator) view.findViewById(R.id.custom_indicator));
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(5000);
        // mDemoSlider.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.select_outlet:
                // Utils.startActivity(getContext(), OrderSelectActivity.class);
                if (date == null || time == null) {
                    String msg = "";
                    if (date == null && time == null) {
                        msg = "* Select date and time";
                    } else if (date == null) {
                        msg = "* Select date";
                    } else if (time == null) {
                        msg = "* Select time";
                    }
                    dilogCustom.retryAlertDialog(mContext, getString(R.string.app_name), msg, getString(R.string.cancel), "", this);

                } else {
                    ((DashBordActivity) getActivity()).gotoNextScreen(new FindFragment(), mContext.getString(R.string.n_find_us));
                }

                break;
            case R.id.btnProceed:


                Utils.startActivity(getContext(), OrderSelectActivity.class);
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
                dpd.show(getActivity().getFragmentManager(), "Select Date");
                break;
            case R.id.txtTime:
                if (date == null) {
                    dilogCustom.retryAlertDialog(mContext, getString(R.string.app_name), "Select date", getString(R.string.cancel), "", this);

                } else {

//
                    Calendar today = Calendar.getInstance();
                    int month = today.get(Calendar.MONTH) + 1;
                    int year = today.get(Calendar.YEAR);
                    int dates = today.get(Calendar.DAY_OF_MONTH);
                    //  Toast.makeText(mContext, "" + dates + "-" + month + "-" + year, Toast.LENGTH_SHORT).show();

                    if (date.equals(Utils.getDateWithFormat(dates, month, year))) {
//                    final CharSequence[] items = {"11:00am", "12:00pm", "1:00pm", "2:00pm", "3:00pm", "4:00pm", "5:00pm", "6:00pm", "7:00pm"};
                        if (timeHH > 10 || timeHH < 21) {
                            alerts(listItems().toArray(new CharSequence[listItems().size()]));
                        }
//                        else {
//                            dilogCustom.retryAlertDialog(mContext, getString(R.string.app_name), "Select vlaid date", getString(R.string.cancel), "", this);
//
//                        }

                    } else {
                        alerts(listItemsAllday().toArray(new CharSequence[listItemsAllday().size()]));

                    }
//                    java.util.Calendar time = java.util.Calendar.getInstance();
//                    TimePickerDialog ttp = TimePickerDialog.newInstance(
//                            this,
//                            time.get(java.util.Calendar.HOUR_OF_DAY),
//                            time.get(java.util.Calendar.MINUTE),
//                            false
//                    );
//                    //ttp.setMinDate(now);
//                    ttp.setAccentColor(Color.parseColor("#ff5103"));
//                    ttp.show(getActivity().getFragmentManager(), "Time");
                }


                break;
            case R.id.imgClose:
                SharedPref.putboolSP(ConstantField.FIND_US, false);
                SharedPref.removeSP(ConstantField.ADDRESS_NAME);
                SharedPref.removeSP(ConstantField.DATE);
                SharedPref.removeSP(ConstantField.TIME);
                hideAddress();
                break;
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

    private List<String> listItems() {
        List<String> listItems = new ArrayList<String>();
        //"11:00am", "12:00pm", "1:00pm", "2:00pm", "3:00pm", "4:00pm", "5:00pm", "6:00pm", "7:00pm"
        //  int h = Calendar.getInstance().get(Calendar.HOUR);


        if (timeHH > 10 || timeHH < 21) {
            for (int i = timeHH; i < 20; i++) {
                int timeXXX = i + 1;
                switch (timeXXX) {
                    case 11:
                        listItems.add("11:00 am");
                        break;
                    case 12:
                        listItems.add("12:00 pm");
                        break;
                    case 13:
                        listItems.add("1:00 pm");
                        break;
                    case 14:
                        listItems.add("2:00 pm");
                        break;
                    case 15:
                        listItems.add("3:00 pm");
                        break;
                    case 16:
                        listItems.add("4:00 pm");
                        break;
                    case 17:
                        listItems.add("5:00 pm");
                        break;
                    case 18:
                        listItems.add("6:00 pm");
                        break;
                    case 19:
                        listItems.add("7:00 pm");
                        break;
                }
            }
        }


        return listItems;
        // final CharSequence[] charSequenceItems = listItems.toArray(new CharSequence[listItems.size()]);
    }

    private List<String> listItemsAllday() {
        List<String> listItems = new ArrayList<String>();
        listItems.add("11:00 am");
        listItems.add("12:00 pm");
        listItems.add("1:00 pm");
        listItems.add("2:00 pm");
        listItems.add("3:00 pm");
        listItems.add("4:00 pm");
        listItems.add("5:00 pm");
        listItems.add("6:00 pm");
        listItems.add("7:00 pm");
        return listItems;


    }

    private void alerts(final CharSequence[] items) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select time");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selection
                txtTime.setText(items[item]);
                time = "" + items[item];
                SharedPref.putSP(ConstantField.TIME, "" + items[item]);
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
