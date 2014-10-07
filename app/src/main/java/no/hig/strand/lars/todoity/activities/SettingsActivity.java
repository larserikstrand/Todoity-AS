package no.hig.strand.lars.todoity.activities;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;

import no.hig.strand.lars.todoity.R;


/**
 * Simple PreferenceActivity for viewing and editing preferences (settings).
 * @author LarsErik
 *
 */
@SuppressWarnings("deprecation")
public class SettingsActivity extends PreferenceActivity {

    public static final String PREF_OCCUPATION_KEY = "pref_occupation";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        setupUI();
    }



    private void setupUI() {
        // Add summary (secondary text) to the occupation preference.
        ListPreference pref = (ListPreference) findPreference(
                PREF_OCCUPATION_KEY);
        pref.setSummary(pref.getEntry());
        pref.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference,
                                              Object newValue) {
                preference.setSummary(newValue.toString());
                return true;
            }
        });
    }
}
