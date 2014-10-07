package no.hig.strand.lars.todoity.services;

import android.app.IntentService;
import android.content.Intent;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.ArrayList;

import no.hig.strand.lars.todoity.data.Task;
import no.hig.strand.lars.todoity.data.TaskContext;
import no.hig.strand.lars.todoity.data.TaskContract;
import no.hig.strand.lars.todoity.data.TasksDatabase;
import no.hig.strand.lars.todoity.helpers.DatabaseUtilities;

public class ActivityIntentService extends IntentService {

    private ArrayList<Task> mActiveTasks;
    private TasksDatabase mTasksDb;
    private final static String ACTIVITY_UNKNOWN = "unknown";

    public ActivityIntentService() {
        super("ActivityIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mTasksDb = TasksDatabase.getInstance(this);
        mActiveTasks = mTasksDb.getActiveTasks();
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if (ActivityRecognitionResult.hasResult(intent)) {
            ActivityRecognitionResult result =
                    ActivityRecognitionResult.extractResult(intent);

            DetectedActivity mostProbableActivity =
                    result.getMostProbableActivity();
            //int confidence = mostProbableActivity.getConfidence();
            int activityType = mostProbableActivity.getType();
            String activityName = getNameFromType(activityType);

            if (! activityName.equals(ACTIVITY_UNKNOWN)) {
                saveActivity(activityName);
            }
        }
    }

    private String getNameFromType(int activityType) {
        switch (activityType) {
            case DetectedActivity.IN_VEHICLE:
                return "in vehicle";
            case DetectedActivity.ON_BICYCLE:
                return "on bicycle";
            case DetectedActivity.ON_FOOT:
                return "on foot";
            case DetectedActivity.STILL:
                return "still";
            case DetectedActivity.TILTING:
                return "tilting";
            case DetectedActivity.UNKNOWN:
                return ACTIVITY_UNKNOWN;
        }
        return ACTIVITY_UNKNOWN;
    }

    private void saveActivity(String activityName) {
        ArrayList<TaskContext> activities;
        boolean newActivity;

        // For each task, get the previously stored contexts (if any).
        for (Task task : mActiveTasks) {
            activities = mTasksDb.getContextByTaskId(task.getId(), TaskContract.ContextEntry.TYPE_ACTIVITY);
            newActivity = true;

            for (TaskContext taskContext : activities) {
                if (activityName.equals(taskContext.getContext())) {
                    newActivity = false;
                }
            }

            // New activity context for this task, save to db.
            if (newActivity) {
                TaskContext taskContext = new TaskContext();
                taskContext.setTaskId(task.getId());
                taskContext.setType(TaskContract.ContextEntry.TYPE_ACTIVITY);
                taskContext.setContext(activityName);
                DatabaseUtilities.saveContext(this, taskContext);
            }
        }
    }
}
