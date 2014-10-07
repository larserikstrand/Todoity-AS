package no.hig.strand.lars.todoity.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import no.hig.strand.lars.todoity.R;
import no.hig.strand.lars.todoity.activities.MainActivity;
import no.hig.strand.lars.todoity.activities.TaskActivity;
import no.hig.strand.lars.todoity.adapters.TodayListAdapter;
import no.hig.strand.lars.todoity.data.Constant;
import no.hig.strand.lars.todoity.data.Task;
import no.hig.strand.lars.todoity.data.Task.TaskPriorityComparator;
import no.hig.strand.lars.todoity.helpers.DatabaseUtilities.DeleteTask;
import no.hig.strand.lars.todoity.helpers.DatabaseUtilities.GetTasksByDateTask;
import no.hig.strand.lars.todoity.helpers.DatabaseUtilities.MoveTask;
import no.hig.strand.lars.todoity.helpers.DatabaseUtilities.UpdateMultipleTask;
import no.hig.strand.lars.todoity.helpers.DatabaseUtilities.UpdateTask;
import no.hig.strand.lars.todoity.helpers.DatePickerFragment;
import no.hig.strand.lars.todoity.helpers.DatePickerFragment.OnDateSetListener;
import no.hig.strand.lars.todoity.helpers.Recommender;
import no.hig.strand.lars.todoity.helpers.Utilities;
import no.hig.strand.lars.todoity.helpers.Utilities.OnConfirmListener;
import no.hig.strand.lars.todoity.services.ContextService;
import no.hig.strand.lars.todoity.views.DynamicListView;


public class TodayFragment extends Fragment implements OnDateSetListener {

    private View mRootView;
    private DynamicListView mListView;
    private TodayListAdapter mAdapter;
    private ArrayList<Task> mTasks;
    private ActionMode mActionMode;
    private int mSelectedTasks;
    private static SparseBooleanArray mCheckedTasks;

    private ProgressBar mProgress;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_today, container, false);

        mListView = (DynamicListView) mRootView.findViewById(R.id.today_list);
        mProgress = (ProgressBar) mRootView.findViewById(R.id.progressBar);

        mActionMode = null;
        mSelectedTasks = 0;

        setupUI();

        return mRootView;
    }



    @Override
    public void onResume() {
        update();
        super.onResume();
    }



    private void setupUI() {
        mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        mListView.setMultiChoiceModeListener(new MultiChoiceModeListener() {
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                MenuItem item = menu.findItem(R.id.menu_edit);
                if (mSelectedTasks == 1) {
                    item.setVisible(true);
                } else {
                    item.setVisible(false);
                }
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                mActionMode = null;
                mSelectedTasks = 0;
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mActionMode = mode;
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.context, menu);
                return true;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_edit:
                        mCheckedTasks = mListView.getCheckedItemPositions();
                        Intent intent = new Intent(getActivity(),
                                TaskActivity.class);
                        intent.putExtra(Constant.TASK_EXTRA,
                                mTasks.get(mCheckedTasks.keyAt(0)));
                        intent.putExtra(Constant.POSITION_EXTRA,
                                mCheckedTasks.keyAt(0));
                        stopActionMode();
                        startActivityForResult(intent, Constant.EDIT_TASK_REQUEST);
                        return true;
                    case R.id.menu_move:
                        DialogFragment datePicker = new DatePickerFragment();
                        Bundle args = new Bundle();
                        args.putString(Constant.DATEPICKER_TITLE_EXTRA,
                                getString(R.string.move_task));
                        datePicker.setArguments(args);
                        datePicker.setTargetFragment(TodayFragment.this,
                                Constant.MOVE_TASK_REQUEST);
                        datePicker.show(getActivity().getSupportFragmentManager(),
                                "datePicker");
                        return true;
                    case R.id.menu_delete:
                        mCheckedTasks = mListView.getCheckedItemPositions();
                        // Show dialog to the user asking for confirmation of deletion.
                        String dialogMessage = mSelectedTasks + " " +
                                getString(R.string.tasks) + " " +
                                getString(R.string.delete_list_message);
                        Utilities.showConfirmDialog(getActivity(), null,
                                dialogMessage, getString(R.string.delete),
                                new OnConfirmListener() {
                                    @Override
                                    public void onConfirm(DialogInterface dialog, int id) {
                                        for (int i = mCheckedTasks.size() - 1; i >= 0; i--) {
                                            Task task = mTasks.get(mCheckedTasks.keyAt(i));
                                            new DeleteTask(getActivity(), task).execute();
                                            mTasks.remove(task);
                                        }
                                        stopActionMode();
                                        updateList(mTasks);
                                        ((MainActivity) getActivity())
                                                .updateNeighborFragments();
                                    }
                                });
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                  long id, boolean checked) {
                if (checked) {
                    mSelectedTasks++;
                } else {
                    mSelectedTasks--;
                }
                mode.setTitle(Integer.toString(mSelectedTasks));
                mode.invalidate();
            }
        });
    }



    public void stopActionMode() {
        if (mActionMode != null) {
            mActionMode.finish();
        }
    }



    public void update() {
        new GetTasksByDateTask(getActivity(),
                Utilities.getTodayDate()).execute();
    }



    public void updateList(ArrayList<Task> tasks) {
        Collections.sort(tasks, new TaskPriorityComparator());
        mTasks = tasks;
        mAdapter = new TodayListAdapter(getActivity(), mTasks);
        mListView.setTaskList(mTasks);
        mListView.setAdapter(mAdapter);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Constant.EDIT_TASK_REQUEST:
                if (resultCode == Activity.RESULT_OK) {
                    Task task = data.getParcelableExtra(Constant.TASK_EXTRA);
                    int position = data.getIntExtra(Constant.POSITION_EXTRA, -1);

                    mTasks.set(position, task);
                    new UpdateTask(getActivity(), task).execute();
                    updateList(mTasks);
                    ((MainActivity) getActivity()).updateNeighborFragments();
                }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



    @Override
    public void onDateSet(String date, Fragment target, Bundle args) {
        if (! date.equals(Utilities.getTodayDate())) {
            SparseBooleanArray checked = mListView
                    .getCheckedItemPositions();
            for (int i = checked.size() - 1; i >= 0; i--) {
                new MoveTask(getActivity(),
                        mTasks.get(checked.keyAt(i)), date).execute();
                mTasks.remove(checked.keyAt(i));
            }
            stopActionMode();
            updateList(mTasks);
            ((MainActivity) getActivity())
                    .updateNeighborFragments();
        }
    }



    public void startTask(Task task) {
        if (task.isFinished()) {
            task.setFinished(false);
        } else {
            task.setActive(true);

            long timeNow = Calendar.getInstance().getTimeInMillis();
            task.setTempStart(timeNow);

            if (Utilities.isGooglePlayServicesAvailable(getActivity())) {
                Intent intent = new Intent(getActivity(), ContextService.class);
                getActivity().startService(intent);
            }
        }

        new UpdateMultipleTask(getActivity(), mTasks).execute();

        updateList(mTasks);

        recommend();
    }



    public void stopTask(Task task) {
        // If task was stopped while being active. Log time.
        if (task.isActive()) {
            task.setActive(false);

            long timeNow = Calendar.getInstance().getTimeInMillis();

            if (timeNow - task.getTempStart() > Constant.MIN_TIME_TASK_START) {
                if (task.getTimeStarted() == 0) {
                    task.setTimeStarted(task.getTempStart());
                }
                task.setTimeEnded(timeNow);
                task.updateTimeSpent(timeNow - task.getTempStart());
            }
        }

        new UpdateMultipleTask(getActivity(), mTasks).execute();

        boolean isTasksActive = false;
        for (Task t : mTasks) {
            if (t.isActive()) {
                isTasksActive = true;
            }
        }
        updateList(mTasks);

        if (! isTasksActive) {
            Intent intent = new Intent(getActivity(), ContextService.class);
            getActivity().stopService(intent);
        }

        recommend();
    }



    private void recommend() {
        if (Utilities.isGooglePlayServicesAvailable(getActivity())) {
            // Only recommend tasks that are not active or finished.
            ArrayList<Task> tasks = new ArrayList<Task>(mTasks);
            for (int i = tasks.size(); i > 0; i--) {
                if (tasks.get(i-1).isFinished()) {
                    tasks.remove(i-1);
                }
            }
            Recommender.recommend(this, tasks);
        }
    }



    public ProgressBar getProgressBar() {
        return mProgress;
    }

}
