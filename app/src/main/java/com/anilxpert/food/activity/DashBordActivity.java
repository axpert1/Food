package com.anilxpert.food.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.anilxpert.food.R;
import com.anilxpert.food.fragments.ContectUsFragment;
import com.anilxpert.food.fragments.FaqFragment;
import com.anilxpert.food.fragments.FindFragment;
import com.anilxpert.food.fragments.HomeFragment;
import com.anilxpert.food.fragments.MyOrderFragment;
import com.anilxpert.food.fragments.PolicyFragment;
import com.anilxpert.food.fragments.ProfileFragment;
import com.anilxpert.food.fragments.TermsFragment;
import com.anilxpert.food.loopjServcice.ConstantField;
import com.anilxpert.food.utils.SharedPref;
import com.anilxpert.food.utils.Utils;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;

public class DashBordActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BaseSliderView.OnSliderClickListener, View.OnClickListener {
    private SliderLayout mDemoSlider;
    private Fragment fragment = null;
    private DrawerLayout drawer = null;


    // Text view
    private TextView home;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Utils.gethashKey(getApplicationContext());
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

        home = (TextView) findViewById(R.id.myprofile);
        myprofile = (TextView) findViewById(R.id.home);
        register = (Button) findViewById(R.id.register);
        sign_in = (Button) findViewById(R.id.sign_in);
        logout = (Button) findViewById(R.id.logout);
        myorder = (TextView) findViewById(R.id.myorder);
        find = (TextView) findViewById(R.id.find);
        contect_us = (TextView) findViewById(R.id.contect_us);
        faq = (TextView) findViewById(R.id.faq);
        terms_of_use = (TextView) findViewById(R.id.terms_of_use);
        privacy_policy = (TextView) findViewById(R.id.privacu_policy);

        home.setOnClickListener(this);
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

//        fragment = new HomeFragment();
//        if (fragment != null) {
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            fragmentManager.beginTransaction().replace(R.id.fmContainer, fragment).addToBackStack(null).commit();
//        }
        gotoHomeScreen();
    }

    private void gotoHomeScreen() {
        fragment = new HomeFragment();
        gotoNextScreen(fragment, getString(R.string.n_home));
    }

    public void gotoNextScreen(Fragment fragment, String name) {
        if (fragment != null) {
            Bundle bundle = new Bundle();
            fragment.setArguments(bundle);
            activity_title.setText(name);
            Utils.replaceFrg(DashBordActivity.this, fragment, true, R.id.fmContainer);

        }
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
            case R.id.home:
                if (fragment instanceof HomeFragment) {

                } else {
                    fragment = new HomeFragment();
                    gotoNextScreen(fragment, getString(R.string.n_home));
                }

                break;
            case R.id.myprofile:
                if (fragment instanceof ProfileFragment) {

                } else {
                    fragment = new ProfileFragment();
                    gotoNextScreen(fragment, getString(R.string.n_my_profile));
                }

                break;
            case R.id.myorder:
                if (fragment instanceof MyOrderFragment) {

                } else {
                    fragment = new MyOrderFragment();
                    gotoNextScreen(fragment, getString(R.string.n_my_orders));
                }

                break;
            case R.id.find:
                if (fragment instanceof FindFragment) {

                } else {
                    fragment = new FindFragment();
                    gotoNextScreen(fragment, getString(R.string.n_find_us));
                }

                break;
            case R.id.contect_us:
                if (fragment instanceof ContectUsFragment) {

                } else {
                    fragment = new ContectUsFragment();
                    gotoNextScreen(fragment, getString(R.string.n_contact_us));
                }

                break;
            case R.id.faq:
                if (fragment instanceof FaqFragment) {

                } else {
                    fragment = new FaqFragment();
                    gotoNextScreen(fragment, getString(R.string.n_faq));
                }
                break;
            case R.id.terms_of_use:
                if (fragment instanceof TermsFragment) {

                } else {
                    fragment = new TermsFragment();
                    gotoNextScreen(fragment, getString(R.string.n_terms_of_use));
                }
                break;
            case R.id.privacu_policy:
                if (fragment instanceof PolicyFragment) {

                } else {
                    fragment = new PolicyFragment();
                    gotoNextScreen(fragment, getString(R.string.n_privacy_policy));
                }
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

        }
        drawerClose();

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
}
