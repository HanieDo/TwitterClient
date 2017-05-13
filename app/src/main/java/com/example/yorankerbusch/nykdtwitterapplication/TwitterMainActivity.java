package com.example.yorankerbusch.nykdtwitterapplication;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

public class TwitterMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, TweetListStartFragment.OnFragmentInteractionListener {
    private String currentFragment = "NULL";
    private final String TWITTER_HOME_FRAGMENT = "HOME";

    private TweetListStartFragment tweetListStartFragment;
    private FrameLayout tweetListFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tweetListStartFragment = (TweetListStartFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_tweet_list);
        tweetListFrameLayout = (FrameLayout) findViewById(R.id.fl_list_fragment);
        currentFragment = TWITTER_HOME_FRAGMENT;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SingletonTweets.getInstance().loadJSONFromAsset(getApplicationContext());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_twitter_home) {
            if (currentFragment == TWITTER_HOME_FRAGMENT) {
                //Do nothing, as the home fragment is already on the screen and the app will crash.
            }
            else {
                getSupportFragmentManager().beginTransaction().replace(tweetListFrameLayout.getId(), tweetListStartFragment).commit();
            }
        }
        else if (id == R.id.nav_search) {
            Toast.makeText(this, "Twitter search placeholder", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_my_timeline) {
            Toast.makeText(this, "My timeline placeholder", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_log_out) {
            Toast.makeText(this, "Twitter log out placeholder", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_settings) {
            Toast.makeText(this, "Twitter settings placeholder", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_about) {
            Toast.makeText(this, "About Twitter placeholder", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onFragmentInteraction() {
        //...
    }
}
