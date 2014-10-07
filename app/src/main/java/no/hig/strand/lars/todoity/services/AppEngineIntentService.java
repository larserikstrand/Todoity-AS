package no.hig.strand.lars.todoity.services;

import android.app.IntentService;
import android.content.Intent;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;
import java.util.ArrayList;

import no.hig.strand.lars.todoity.R;
import no.hig.strand.lars.todoity.backend.contextEntityEndpoint.ContextEntityEndpoint;
import no.hig.strand.lars.todoity.backend.contextEntityEndpoint.model.ContextEntity;
import no.hig.strand.lars.todoity.backend.taskEntityEndpoint.TaskEntityEndpoint;
import no.hig.strand.lars.todoity.backend.taskEntityEndpoint.model.TaskEntity;
import no.hig.strand.lars.todoity.data.Constant;
import no.hig.strand.lars.todoity.data.Task;
import no.hig.strand.lars.todoity.data.TaskContext;
import no.hig.strand.lars.todoity.helpers.AppEngineUtilities;
import no.hig.strand.lars.todoity.helpers.Installation;

public class AppEngineIntentService extends IntentService {

    private static TaskEntityEndpoint taskEntityEndpoint = null;
    private static ContextEntityEndpoint contextEntityEndpoint = null;

    public static enum Command {
        SAVETASK,
        REMOVETASK,
        UPDATETASK,
        UPDATEMULTIPLETASK,
        SAVECONTEXT
    }


    public AppEngineIntentService() {
        super("AppEngineIntentService");
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        Command command = (Command) intent
                .getSerializableExtra(Constant.APPENGINE_COMMAND_EXTRA);
        if (command != null) {
            switch (command) {
                case SAVETASK:
                    saveTask((Task) intent.getParcelableExtra(
                            Constant.TASK_EXTRA));
                    break;
                case REMOVETASK:
                    removeTask((Task) intent.getParcelableExtra(
                            Constant.TASK_EXTRA));
                    break;
                case UPDATETASK:
                    updateTask((Task) intent.getParcelableExtra(
                            Constant.TASK_EXTRA));
                    break;
                case UPDATEMULTIPLETASK:
                    ArrayList<Task> tasks = intent.getParcelableArrayListExtra(
                            Constant.TASKS_EXTRA);
                    updateTasks(tasks);
                    break;
                case SAVECONTEXT:
                    saveContext((TaskContext) intent.getParcelableExtra(
                            Constant.TASKCONTEXT_EXTRA));
                    break;
            }
        }
    }



    private void saveTask(Task task) {
        // Save task externally to AppEngine
        if (taskEntityEndpoint == null) {
            TaskEntityEndpoint.Builder builder = new TaskEntityEndpoint.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null);
            builder.setApplicationName(getString(R.string.app_name));
            taskEntityEndpoint = builder.build();
        }

        TaskEntity taskEntity = AppEngineUtilities.getTaskEntityFromTask(task);
        String id = Installation.id(this) + " " + task.getId();
        taskEntity.setId(id);
        try {
            taskEntityEndpoint.insertTaskEntity(taskEntity).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void removeTask(Task task) {
        String id = Installation.id(this) + " " + task.getId();
        if (taskEntityEndpoint == null) {
            TaskEntityEndpoint.Builder builder = new TaskEntityEndpoint.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null);
            builder.setApplicationName(getString(R.string.app_name));
            taskEntityEndpoint = builder.build();
        }

        try {
            taskEntityEndpoint.removeTaskEntity(id).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void updateTask(Task task) {
        if (taskEntityEndpoint == null) {
            TaskEntityEndpoint.Builder builder = new TaskEntityEndpoint.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null);
            builder.setApplicationName(getString(R.string.app_name));
            taskEntityEndpoint = builder.build();
        }

        String id = Installation.id(this) + " " + task.getId();
        TaskEntity taskEntity = AppEngineUtilities.getTaskEntityFromTask(task);
        taskEntity.setId(id);

        try {
            taskEntityEndpoint.updateTaskEntity(taskEntity).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void updateTasks(ArrayList<Task> tasks) {
        if (taskEntityEndpoint == null) {
            TaskEntityEndpoint.Builder builder = new TaskEntityEndpoint.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null);
            builder.setApplicationName(getString(R.string.app_name));
            taskEntityEndpoint = builder.build();
        }

        String id;
        for (Task task : tasks) {
            id = Installation.id(this) + " " + task.getId();
            TaskEntity taskEntity = AppEngineUtilities.getTaskEntityFromTask(task);
            taskEntity.setId(id);

            try {
                taskEntityEndpoint.updateTaskEntity(taskEntity).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



    private void saveContext(TaskContext taskContext) {
        if (contextEntityEndpoint == null) {
            ContextEntityEndpoint.Builder builder = new ContextEntityEndpoint.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null);
            builder.setApplicationName(getString(R.string.app_name));
            contextEntityEndpoint = builder.build();
        }

        ContextEntity contextEntity = new ContextEntity();
        contextEntity.setTaskId(Installation.id(this) + " " + taskContext.getTaskId());
        contextEntity.setType(taskContext.getType());
        contextEntity.setContext(taskContext.getContext());
        contextEntity.setDetails(taskContext.getDetails());
        try {
            contextEntityEndpoint.insertContextEntity(contextEntity).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
