package com.example.yorankerbusch.nykdtwitterapplication;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yorankerbusch.nykdtwitterapplication.fragments.OAuthWebViewFragment;
import com.example.yorankerbusch.nykdtwitterapplication.fragments.TweetListStartFragment;
import com.example.yorankerbusch.nykdtwitterapplication.fragments.UserPageFragment;

import java.io.IOException;

public class TwitterMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, TweetListStartFragment.OnFragmentInteractionListener {
    public static final String REQUESTED_USER = "GIVEN USER";

    private static String currentFragment = "NULL";
    private static final String TWITTER_HOME_FRAGMENT = "HOME FRAGMENT";
    private static final String SEARCH_FRAGMENT = "SEARCH FRAGMENT";
    private static final String LOG_IN_FRAGMENT = "LOG IN FRAGMENT";
    private static final String MY_TIMELINE_FRAGMENT = "MY TIMELINE FRAGMENT";
    private static final String OTHER_TIMELINE_FRAGMENT = "OTHER TIMELINE FRAGMENT";
    private static final String SETTINGS_FRAGMENT = "SETTINGS FRAGMENT";
    private static final String ABOUT_FRAGMENT = "ABOUT FRAGMENT";

    private FrameLayout tweetListFrameLayout;
    private NavigationView navigationView;
    private boolean userWantsToLog = false;
    private TextView userNameTV, tagUserTV;
    private ImageView imageUser;

    private static final String PROTECTED_RESOURCE_URL = "https://api.twitter.com/1.1/account/verify_credentials.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TweetListStartFragment tweetListStartFragment = new TweetListStartFragment();
        tweetListFrameLayout = (FrameLayout) findViewById(R.id.fl_list_fragment);
        getSupportFragmentManager().beginTransaction().replace(tweetListFrameLayout.getId(), tweetListStartFragment).commit();
        currentFragment = TWITTER_HOME_FRAGMENT;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //TODO: Find a better place for this, so that switching between layout modes doesn't add the tweet
        //TODO:  list to the list again.
        SingletonTweets.getInstance().loadJSONFromAsset(getApplicationContext());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        userNameTV = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tv_username_nav_drawer);
        tagUserTV = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tv_tag_nav_drawer);
        imageUser = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.nav_user_iv);

        //TODO: Change this, as OAuth users who close and start the app again will be logged out upon
        //TODO   the second startup.
        try {
            handleNavUserLogged();
        } catch (IOException e) {
            e.printStackTrace();
        }
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * Method that handles what the app should do when one of the nav drawer's items has been clicked.
     *
     * @param item is the clicked item.
     * @return true to let the system know it has done the handling, failed or not.
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_twitter_home) {
            if (currentFragment.equals(TWITTER_HOME_FRAGMENT)) {
                //Do nothing, as the home fragment is already on the screen and the app will crash.
            } else {
                TweetListStartFragment tweetListStartFragment = new TweetListStartFragment();
                getSupportFragmentManager().beginTransaction().replace(tweetListFrameLayout.getId(), tweetListStartFragment).commit();
                currentFragment = TWITTER_HOME_FRAGMENT;
            }
        } else if (id == R.id.nav_search) {
            if (currentFragment.equals(SEARCH_FRAGMENT)) {
                //Do nothing, as the search fragment is already on the screen and the app will crash.
            } else {
                //TODO: Replace the current fragment with the search fragment
            }

            Toast.makeText(this, "Twitter search menu option placeholder", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_my_timeline) {
            if (currentFragment.equals(MY_TIMELINE_FRAGMENT)) {
                //Do nothing, as the my timeline fragment is already on the screen and the app will crash.
            } else {
                //TODO: Replace the current fragment with the my timeline fragment
                //Add bundles to send the current logged in user's data to the user page fragment.
                UserPageFragment userPageFragment = new UserPageFragment();
                getSupportFragmentManager().beginTransaction().replace(tweetListFrameLayout.getId(), userPageFragment).commit();
            }

            Toast.makeText(this, "My timeline menu option placeholder", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_log_in) {
            if (currentFragment == LOG_IN_FRAGMENT) {
                //Do nothing, as the login fragment is already on screen.
            }
            else {
                userWantsToLog = true;

                try {
                    handleNavUserLogged();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (id == R.id.nav_log_out) {
            userWantsToLog = false;

            try {
                handleNavUserLogged();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (id == R.id.nav_settings) {
            if (currentFragment.equals(SETTINGS_FRAGMENT)) {
                //Do nothing, as the settings fragment is already on the screen and the app will crash.
            } else {
                //TODO: Replace the current fragment with the settings fragment
            }

            Toast.makeText(this, "Twitter settings menu option placeholder", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_about) {
            if (currentFragment.equals(ABOUT_FRAGMENT)) {
                //Do nothing, as the about fragment is already on the screen and the app will crash.
            } else {
                //TODO: Replace the current fragment with the about fragment
            }

            Toast.makeText(this, "About Twitter menu option placeholder", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    public void handleNavUserLogged() throws IOException {
        Menu menu = navigationView.getMenu();

        if (!userWantsToLog) {
            //TODO: Logout feature, delete the OAuth logged in user's information and the like.
            menu.findItem(R.id.nav_my_account_sub1).getSubMenu().findItem(R.id.nav_log_out).setVisible(false);
            menu.findItem(R.id.nav_my_account_sub1).getSubMenu().findItem(R.id.nav_my_timeline).setVisible(false);
            menu.findItem(R.id.nav_my_account_sub2).getSubMenu().findItem(R.id.nav_settings).setVisible(false);

            userNameTV.setText("TwitterGuest");
            tagUserTV.setText("@IShouldGetAnAccount");
            imageUser.setImageResource(R.mipmap.ic_launcher_round);

            menu.findItem(R.id.nav_my_account_sub1).getSubMenu().findItem(R.id.nav_log_in).setVisible(true);
            Toast.makeText(this, "You have been logged out.", Toast.LENGTH_SHORT).show();
        } else {
            //Display the OAuth web view fragment so the user can log in using actual Twitter
            OAuthWebViewFragment oAuthWebViewFragment = new OAuthWebViewFragment();
            getSupportFragmentManager().beginTransaction().replace(tweetListFrameLayout.getId(), oAuthWebViewFragment).commit();
            currentFragment = LOG_IN_FRAGMENT;

            //TODO
            //TODO: OAuth handling has to start here!
            //TODO

//            if (correctUser == true) {
            menu.findItem(R.id.nav_my_account_sub1).getSubMenu().findItem(R.id.nav_log_in).setVisible(false);

            //TODO: Change these to the retrieved user's name, tag and image.
            userNameTV.setText("AuthenticTwitterUser");
            tagUserTV.setText("@IAmPartOfTheClub");
            imageUser.setImageResource(R.mipmap.ic_launcher_round);

            menu.findItem(R.id.nav_my_account_sub1).getSubMenu().findItem(R.id.nav_log_out).setVisible(true);
            menu.findItem(R.id.nav_my_account_sub1).getSubMenu().findItem(R.id.nav_my_timeline).setVisible(true);
            menu.findItem(R.id.nav_my_account_sub2).getSubMenu().findItem(R.id.nav_settings).setVisible(true);

//            Toast.makeText(this, "You have been successfully logged in!", Toast.LENGTH_SHORT).show();
//            } else {
//                //Notify the user that their login attempt failed.
//                Toast.makeText(this, "Login attempt failed - We could not log you in!", Toast.LENGTH_SHORT).show();
//            }
//
//            //Regardless of if the user failed or succeeded to log in, return to the main tweet menu.
//            TweetListStartFragment tweetListStartFragment = new TweetListStartFragment();
//            getSupportFragmentManager().beginTransaction().replace(tweetListFrameLayout.getId(), tweetListStartFragment).commit();
//            currentFragment = TWITTER_HOME_FRAGMENT;
        }
    }

    /**
     * Method to handle when the navigation drawer button has been pressed (which occupies the
     * back button's space)
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Method that sets the exact menu item from the nav drawer that has been clicked by the user.
     *
     * @param item is the menu item clicked, telling the rest of the system what to do.
     * @return a super.onOptionsItemSelected(item) call.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    /**
     * Required method from the TweetListStartFragment to handle when a list item has been clicked.
     *
     * @param userName is the name of the person who wrote the tweet that was clicked.
     */
    @Override
    public void onFragmentInteraction(String userName) {
        Bundle bundle = new Bundle();
        bundle.putString(REQUESTED_USER, userName);

        UserPageFragment userPageFragment = new UserPageFragment();
        userPageFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(tweetListFrameLayout.getId(), userPageFragment).commit();
        currentFragment = OTHER_TIMELINE_FRAGMENT;
    }
}
