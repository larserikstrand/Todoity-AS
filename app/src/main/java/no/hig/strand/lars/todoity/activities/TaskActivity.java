package no.hig.strand.lars.todoity.activities;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

import no.hig.strand.lars.todoity.R;
import no.hig.strand.lars.todoity.adapters.PlacesAutoCompleteAdapter;
import no.hig.strand.lars.todoity.data.Constant;
import no.hig.strand.lars.todoity.data.Task;
import no.hig.strand.lars.todoity.helpers.TimePickerFragment;
import no.hig.strand.lars.todoity.helpers.TimePickerFragment.OnTimeSetListener;
import no.hig.strand.lars.todoity.helpers.Todoity;

/**
 * Activity for viewing, editing or creating a single task.
 * @author LarsErik
 *
 */
public class TaskActivity extends FragmentActivity implements OnTimeSetListener {

    private Task mTask;
    private int mPosition;
    private AutoCompleteTextView mLocationText;
    private Spinner mCategory;
    private EditText mDescription;
    private CheckBox mFixedTime;
    private Button mTimeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        setupActionBar();

        Intent data = getIntent();
        // Check if data exists for this task, ie. the
        //  task is being edited.
        mPosition = data.getIntExtra(Constant.POSITION_EXTRA, -1);
        if (data.hasExtra(Constant.TASK_EXTRA)) {
            mTask = data.getParcelableExtra(Constant.TASK_EXTRA);
        } else {
            mTask = new Task();
        }

        setupUI();
    }



    /**
     * Set up the {@link android.app.ActionBar}.
     */
    private void setupActionBar() {
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_task, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            // Save task when save is pressed.
            case R.id.action_save:
                // Track how much the users click save button.
                Tracker t = ((Todoity)getApplication()).getTracker(
                        Todoity.TrackerName.APP_TRACKER);
                t.send(new HitBuilders.EventBuilder()
                        .setCategory("TaskActivity")
                        .setAction("Save")
                        .setLabel("ActionBar")
                        .setValue(1)
                        .build());
                saveTask();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    /**
     * Initializes the UI with necessary listeners.
     */
    private void setupUI() {
        // Set a click listener on the entire screen. This is to make areas
        //  outside child views clickable so that focus can be moved away from
        //  the child views in question.
        ScrollView container = (ScrollView) findViewById(R.id.container);
        container.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // If location text view is focused,
                //  remove 'keyboard' and focus.
                if (mLocationText.isFocused()) {
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(
                            getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    mLocationText.clearFocus();
                    mLocationText.clearFocus();
                }
            }
        });

        // Set behavior of the category spinner.
        mCategory = (Spinner) findViewById(R.id.category_spinner);
        // Read occupation from preferences and set
        //  predefined categories accordingly.
        int spinnerArray = R.array.tasks_array;

        // TODO - should also be revised:
//        SharedPreferences sharedPref = PreferenceManager
//                .getDefaultSharedPreferences(this);
//        String occupationPref = sharedPref.getString(
//                SettingsActivity.PREF_OCCUPATION_KEY, "");
//        int spinnerArray;
//        if (occupationPref.equals(getString(R.string.pref_undergraduate))) {
//            spinnerArray = R.array.undergraduate_tasks_array;
//        } else {
//
//            spinnerArray = R.array.postgraduate_tasks_array;
//        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource(this, spinnerArray,
                        android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        mCategory.setAdapter(adapter);
        if (! mTask.getCategory().equals("")) {
            mCategory.setSelection(adapter.getPosition(mTask.getCategory()));
        }

        mDescription = (EditText) findViewById(R.id.description_edit);
        mDescription.setText(mTask.getDescription());

        // Set up the auto complete text view with listeners.
        mLocationText = (AutoCompleteTextView) findViewById(R.id.location_text);
        mLocationText.setText(mTask.getAddress());
        // Create a new adapter for the AutoComplete text. Uses Google Places
        //  API to find addresses.
        ArrayAdapter<String> autoCompleteAdapter = new PlacesAutoCompleteAdapter(this,
                android.R.layout.simple_dropdown_item_1line);
        mLocationText.setAdapter(autoCompleteAdapter);
        mLocationText.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int position, long id) {
                // Track how much the users selects a suggested location.
                Tracker t = ((Todoity)getApplication()).getTracker(
                        Todoity.TrackerName.APP_TRACKER);
                t.send(new HitBuilders.EventBuilder()
                        .setCategory("TaskActivity")
                        .setAction("Selected suggestion")
                        .setLabel("Location")
                        .setValue(1)
                        .build());
                // Set task location when item in AutoComplete dropdown
                //  is selected.
                String location = (String) adapterView
                        .getItemAtPosition(position);
                mLocationText.setText(location);
                mTask.setAddress(location);
                // Hide 'keyboard'.
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                mLocationText.clearFocus();
            }
        });
        // Also provide a listener for when the user clicks outside the
        //  AutoComplete text, but has typed some location.
        mLocationText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (! hasFocus) {
                    new GetLocationFromNameTask().execute(
                            mLocationText.getText().toString());
                }
            }
        });

        // Set behavior of the location button.
        Button button = (Button) findViewById(R.id.location_button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Track how much the users open the map.
                Tracker t = ((Todoity)getApplication()).getTracker(
                        Todoity.TrackerName.APP_TRACKER);
                t.send(new HitBuilders.EventBuilder()
                        .setCategory("TaskActivity")
                        .setAction("Open map")
                        .setLabel("Location")
                        .setValue(1)
                        .build());
                // Open map when user clicks the button.
                Intent intent = new Intent(
                        TaskActivity.this, MapActivity.class);
                if (mTask.getLatitude() != 0) {
                    intent.putExtra(Constant.LOCATION_EXTRA, new LatLng(
                            mTask.getLatitude(), mTask.getLongitude()));
                }
                startActivityForResult(intent, Constant.MAP_REQUEST);
            }
        });

        // Set behavior of the fixed time check box.
        mFixedTime = (CheckBox) findViewById(R.id.fixed_time_check);
        mFixedTime.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                Button fromButton = (Button) findViewById(R.id.from_button);
                Button toButton = (Button) findViewById(R.id.to_button);
                // If task has fixed times, enable buttons.
                if (isChecked) {
                    fromButton.setEnabled(true);
                    toButton.setEnabled(true);
                } else {
                    fromButton.setEnabled(false);
                    toButton.setEnabled(false);
                }
            }
        });
        if (! mTask.getFixedStart().equals("")) {
            mFixedTime.setChecked(true);
        }

        // Set behavior of the fixed time buttons.
        // Create listener for the buttons. The listener uses a TimePicker
        //  and is shared between both time buttons.
        OnClickListener timeButtonListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimeButton = (Button) v;
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "timePicker");
            }
        };
        button = (Button) findViewById(R.id.from_button);
        button.setOnClickListener(timeButtonListener);
        if (! mTask.getFixedStart().equals("")) {
            button.setText(mTask.getFixedStart());
        }
        button = (Button) findViewById(R.id.to_button);
        button.setOnClickListener(timeButtonListener);
        if (! mTask.getFixedEnd().equals("")) {
            button.setText(mTask.getFixedEnd());
        }
    }



    /**
     * Checks that the user has entered some data into the required fields,
     *  and sends the task back to the calling Activity, where the task is
     *  saved to database (both internal and external).
     */
    private void saveTask() {

        // Check if a location is chosen.
        if (mTask.getLatitude() != 0 || ! mTask.getAddress().isEmpty() || ! mLocationText.getText().toString().isEmpty()) {

            // Check start time if the fixed time box is ticked.
            if (mFixedTime.isChecked()) {
                Button button = (Button) findViewById(R.id.from_button);
                String fixedStart = button.getText().toString();
                button = (Button) findViewById(R.id.to_button);
                String fixedEnd = button.getText().toString();
                if (! fixedStart.equals(getString(R.string.from))) {
                    // Has fixed times and times are properly set.
                    mTask.setFixedStart(fixedStart);
                    if (! fixedEnd.equals(getString(R.string.to))) {
                        mTask.setFixedEnd(fixedEnd);
                    }
                } else {
                    // Let user know that if 'fixed time' is checked,
                    //  a time is required.
                    Toast.makeText(this, getString(R.string.set_time_message),
                            Toast.LENGTH_LONG).show();
                    return;
                }
                // If task doesn't have fixed times, reset values in case the task
                //  is being edited.
            } else {
                mTask.setFixedStart("");
                mTask.setFixedEnd("");
            }

            mTask.setCategory(mCategory.getSelectedItem().toString());
            mTask.setDescription(mDescription.getText().toString());

            Intent data = new Intent();
            data.putExtra(Constant.TASK_EXTRA, mTask);
            if (mPosition > -1) {
                data.putExtra(Constant.POSITION_EXTRA, mPosition);
            }
            setResult(RESULT_OK, data);
            finish();

        } else {
            // Let user know that a location is required.
            Toast.makeText(TaskActivity.this, getString(
                    R.string.set_location_message), Toast.LENGTH_LONG).show();
        }
    }



    /**
     *  Called from TimePickerFragment when a time has been selected.
     *   Updates the time text in the time boxes.
     */
    @Override
    public void onTimeSet(String time) {
        mTimeButton.setText(time);
    }



    /**
     * Handles the result from MapActivity.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == Constant.MAP_REQUEST) {
            if (resultCode == RESULT_OK) {
                // If it was a map request and the result was ok, save the
                //  location and get the address.
                LatLng location = data.getParcelableExtra(
                        Constant.LOCATION_EXTRA);
                mTask.setLatitude(location.latitude);
                mTask.setLongitude(location.longitude);
                new GetLocationFromValueTask().execute(location);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



    /**
     * Class that finds address from location coordinates.
     * Runs in the background as an AsyncTask.
     * @author LarsErik
     *
     */
    private class GetLocationFromValueTask extends
            AsyncTask<LatLng, Void, String> {

        @Override
        protected String doInBackground(LatLng... params) {
            LatLng location = params[0];
            try {
                // Get all available addresses from the coordinates.
                List<Address> addresses = new Geocoder(getBaseContext())
                        .getFromLocation(location.latitude, location.longitude, 1);

                // If any address was found.
                if (addresses.size() > 0) {
                    Address address = addresses.get(0);
                    String value = "";
                    // Loop through all address attributes.
                    for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                        value += address.getAddressLine(i);
                        if (i < address.getMaxAddressLineIndex() -1) {
                            value += ", ";
                        }
                    }
                    return value;
                }
            } catch (IOException e) {
                Log.d("MTP", "Could not get GeoCoder");
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                // Set location of task when AsyncTask is finished.
                mTask.setAddress(result);
                mLocationText.setText(result);
                //Button button = (Button) findViewById(R.id.location_button);
                // Just move the focus away from the editable text
                //button.requestFocus();
            }
        }

    }



    /**
     * Class that finds coordinates in laitude and
     * longitude from location address.
     * Runs in the background as an AsyncTask.
     * @author LarsErik
     *
     */
    private class GetLocationFromNameTask extends
            AsyncTask<String, Void, LatLng> {

        @Override
        protected LatLng doInBackground(String... params) {
            String value = params[0];
            try {
                // Get all addresses from the given value.
                List<Address> addresses = new Geocoder(getBaseContext())
                        .getFromLocationName(value, 1);
                if (addresses.size() > 0) {
                    // Get the coordinates of first (most likely one) address.
                    return new LatLng(addresses.get(0).getLatitude(),
                            addresses.get(0).getLongitude());
                }
            } catch (IOException e) {
                Log.d("MTP", "Could not get GeoCoder");
            }

            return null;
        }

        @Override
        protected void onPostExecute(LatLng result) {
            if (result != null) {
                // Set task location when AsyncTask is finished.
                mTask.setLatitude(result.latitude);
                mTask.setLongitude(result.longitude);
            }
        }

    }

}
