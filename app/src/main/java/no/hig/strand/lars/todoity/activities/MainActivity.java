package no.hig.strand.lars.todoity.activities;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.ArrayList;

import no.hig.strand.lars.todoity.R;
import no.hig.strand.lars.todoity.adapters.TabsPagerAdapter;
import no.hig.strand.lars.todoity.data.Constant;
import no.hig.strand.lars.todoity.data.Task;
import no.hig.strand.lars.todoity.fragments.AllTasksFragment;
import no.hig.strand.lars.todoity.fragments.TodayFragment;
import no.hig.strand.lars.todoity.fragments.WeekFragment;
import no.hig.strand.lars.todoity.helpers.DatabaseUtilities.OnTasksLoadedListener;
import no.hig.strand.lars.todoity.helpers.DatePickerFragment.OnDateSetListener;
import no.hig.strand.lars.todoity.helpers.Installation;
import no.hig.strand.lars.todoity.helpers.Todoity;

/**
 * Entry point of the app. This Activity contains the logic for the ViewPager
 *  that enables swiping between lists of tasks. Also handles the communication
 *  between the fragments in the ViewPager.
 * @author LarsErik
 *
 */
public class MainActivity extends FragmentActivity implements
        OnTasksLoadedListener, OnDateSetListener {

    // Adapter containing the logic for swiping between fragments.
    private TabsPagerAdapter mTabsPagerAdapter;
    private ViewPager mViewPager;

    // Keep track of asyncTask so that we can cancel them if necessary.
    //private ArrayList<AsyncTask<Void, Void, Void>> asyncTasks = new ArrayList<AsyncTask<Void,Void,Void>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // First launch: create an installation id and set default preferences.
        Installation.id(this);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (preferences.getBoolean(Constant.PREF_FIRST_RUN, true)) {
            showPolicy();
        }

        mTabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mTabsPagerAdapter);

        setupUI();
    }



    @Override
    protected void onResume() {
        super.onResume();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new_list:
                // Track how much the users opens list activity.
                Tracker t = ((Todoity)getApplication()).getTracker(
                        Todoity.TrackerName.APP_TRACKER);
                t.send(new HitBuilders.EventBuilder()
                        .setCategory("MainActivity")
                        .setAction("ListActivity")
                        .setLabel("ActionBar")
                        .setValue(1)
                        .build());
                startActivity(new Intent(this, ListActivity.class));
                return true;
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.action_about:
                startActivity(new Intent(this, AboutActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }



    private void setupUI() {
        // Apply tabs to action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabUnselected(Tab tab, FragmentTransaction ft) {}

            @Override
            public void onTabSelected(Tab tab, FragmentTransaction ft) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(Tab tab, FragmentTransaction ft) {}
        };

        actionBar.addTab(actionBar.newTab().setText(R.string.today)
                .setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText(R.string.week)
                .setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText(R.string.all)
                .setTabListener(tabListener));

        mViewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        getActionBar().setSelectedNavigationItem(position);
                        // Track how much the user swipes between the main fragments.
                        Tracker t = ((Todoity)getApplication()).getTracker(
                                Todoity.TrackerName.APP_TRACKER);
                        t.send(new HitBuilders.EventBuilder()
                                .setCategory("MainActivity")
                                .setAction("Swipe")
                                .setLabel("Tabs")
                                .setValue(position)
                                .build());
                    }
                });
    }



    /**
     * Returns the fragment at the specified position in the view pager if
     * it exists, null otherwise.
     * @param position
     * @return The Fragment at the specified position.
     */
    public Fragment getFragmentAt(int position) {
        return mTabsPagerAdapter.getRegisteredFragment(position);
    }



    /**
     * Calls update on the fragments that are neighbors to the currently
     *  visible fragment in the view pager. This essentially rereads their
     *  data from the database and redraws the lists.
     */
    public void updateNeighborFragments() {
        Fragment fragment;
        Fragment currentFragment = mTabsPagerAdapter.getRegisteredFragment(
                mViewPager.getCurrentItem());
        for (int i = 0; i < mTabsPagerAdapter.getCount(); i++) {
            fragment = mTabsPagerAdapter.getRegisteredFragment(i);
            if (fragment != currentFragment) {
                if (fragment instanceof TodayFragment) {
                    ((TodayFragment) fragment).update();
                } else if (fragment instanceof WeekFragment) {
                    ((WeekFragment) fragment).update();
                } else  if (fragment instanceof AllTasksFragment){
                    ((AllTasksFragment) fragment).update();
                }
            }
        }
    }



    /**
     *  Called from GetTasksByDateTask when tasks have been loaded from
     *   database.
     */
    @Override
    public void onTasksLoaded(ArrayList<Task> tasks) {
        Fragment fragment = mTabsPagerAdapter.getRegisteredFragment(0);
        // Call update on TodayFragment if TodayFragment exists.
        if (fragment instanceof TodayFragment) {
            ((TodayFragment) fragment).updateList(tasks);
        }
    }



    /**
     * Called when a date has been set in the date picker for the tasks.
     *  This function then calls onDateSet in the fragment that initiated
     *  the date change.
     */
    @Override
    public void onDateSet(String date, Fragment target, Bundle args) {
        if (target instanceof TodayFragment) {
            ((TodayFragment) target).onDateSet(date, null, args);
        } else if (target instanceof WeekFragment) {
            ((WeekFragment) target).onDateSet(date, null, args);
        } else if (target instanceof AllTasksFragment) {
            ((AllTasksFragment) target).onDateSet(date, null, args);
        }
    }


    /**
     * Show the privacy policy dialog. If accepted, the dialog sets the preference for indicating
     * first run to false. Otherwise, the app is just closed.
     */
    public void showPolicy() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Policy");
        builder.setMessage(getString(R.string.policy));
        builder.setCancelable(false);
        builder.setNegativeButton(getString(R.string.decline), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                preferences.edit().putBoolean(Constant.PREF_FIRST_RUN, false).commit();
            }
        });
        builder.create().show();
    }

}
