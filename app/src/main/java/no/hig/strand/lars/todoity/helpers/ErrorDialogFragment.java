package no.hig.strand.lars.todoity.helpers;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by lars.strand on 30.09.2014.
 */
public class ErrorDialogFragment extends DialogFragment {

    private Dialog mDialog;

    public ErrorDialogFragment() {
        super();
        mDialog = null;
    }

    public void setDialog(Dialog dialog) {
        mDialog = dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return mDialog;
    }
}
