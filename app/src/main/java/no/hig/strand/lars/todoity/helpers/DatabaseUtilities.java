package no.hig.strand.lars.todoity.helpers;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.util.ArrayList;

import no.hig.strand.lars.todoity.data.Constant;
import no.hig.strand.lars.todoity.data.Task;
import no.hig.strand.lars.todoity.data.TaskContext;
import no.hig.strand.lars.todoity.data.TasksDatabase;
import no.hig.strand.lars.todoity.services.AppEngineIntentService;
import no.hig.strand.lars.todoity.services.AppEngineIntentService.Command;

public final class DatabaseUtilities {


    public DatabaseUtilities() {
    }


    public static class SaveTask extends AsyncTask<Void, Void, Void> {
        private TasksDatabase tasksDb;
        private Task task;
        private Context context;

        public SaveTask(Context context, Task task) {
            this.task = task;
            this.context = context;
            tasksDb = TasksDatabase.getInstance(context);
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Check if the list exists. If not, insert it.
            long listId = tasksDb.getListIdByDate(task.getDate());
            if (listId < 0) {
                listId = tasksDb.insertList(task.getDate());
            }
            long taskId = tasksDb.insertTask(listId, task);
            task.setId((int) taskId);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Intent intent = new Intent(context, AppEngineIntentService.class);
            intent.putExtra(Constant.APPENGINE_COMMAND_EXTRA, Command.SAVETASK);
            intent.putExtra(Constant.TASK_EXTRA, task);
            context.startService(intent);
        }
    }


    public static class DeleteTask extends AsyncTask<Void, Void, Void> {
        private TasksDatabase tasksDb;
        private Task task;
        private Context context;

        public DeleteTask(Context context, Task task) {
            this.task = task;
            this.context = context;
            tasksDb = TasksDatabase.getInstance(context);
        }

        @Override
        protected Void doInBackground(Void... params) {
            tasksDb.deleteTaskById(task.getId());

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Intent intent = new Intent(context, AppEngineIntentService.class);
            intent.putExtra(Constant.APPENGINE_COMMAND_EXTRA, Command.REMOVETASK);
            intent.putExtra(Constant.TASK_EXTRA, task);
            context.startService(intent);
        }
    }


    public static class UpdateTask extends AsyncTask<Void, Void, Void> {
        private TasksDatabase tasksDb;
        private Task task;
        private Context context;

        public UpdateTask(Context context, Task task) {
            this.task = task;
            this.context = context;
            tasksDb = TasksDatabase.getInstance(context);
        }

        @Override
        protected Void doInBackground(Void... params) {
            tasksDb.updateTask(task);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Intent intent = new Intent(context, AppEngineIntentService.class);
            intent.putExtra(Constant.APPENGINE_COMMAND_EXTRA, Command.UPDATETASK);
            intent.putExtra(Constant.TASK_EXTRA, task);
            context.startService(intent);
        }
    }


    public static class UpdateMultipleTask extends AsyncTask<Void, Void, Void> {
        private TasksDatabase tasksDb;
        private Context context;
        private ArrayList<Task> tasks;

        public UpdateMultipleTask(Context context, ArrayList<Task> tasks) {
            this.context = context;
            this.tasks = tasks;
            tasksDb = TasksDatabase.getInstance(context);
        }

        @Override
        protected Void doInBackground(Void... params) {
            for (Task task : tasks) {
                tasksDb.updateTask(task);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Intent intent = new Intent(context, AppEngineIntentService.class);
            intent.putExtra(Constant.APPENGINE_COMMAND_EXTRA, Command.UPDATEMULTIPLETASK);
            intent.putExtra(Constant.TASKS_EXTRA, tasks);
            context.startService(intent);
        }
    }


    public static class MoveTask extends AsyncTask<Void, Void, Void> {
        private TasksDatabase tasksDb;
        private Context context;
        private Task task;
        private String date;

        public MoveTask(Context context, Task task, String date) {
            this.context = context;
            this.task = task;
            tasksDb = TasksDatabase.getInstance(context);
            this.date = date;
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Move the task to the selected date.
            //  Create list on that date if none exist.
            long listId = tasksDb.getListIdByDate(date);
            if (listId < 0) {
                listId = tasksDb.insertList(date);
            }

            task.setDate(date);
            tasksDb.moveTaskToList(task.getId(), listId);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Intent intent = new Intent(context, AppEngineIntentService.class);
            intent.putExtra(Constant.APPENGINE_COMMAND_EXTRA, Command.UPDATETASK);
            intent.putExtra(Constant.TASK_EXTRA, task);
            context.startService(intent);
        }
    }


    public static class SaveContextTask extends AsyncTask<Void, Void, Void> {
        private Context context;
        private TaskContext taskContext;

        public SaveContextTask(Context context, TaskContext taskContext) {
            this.context = context;
            this.taskContext = taskContext;
        }

        @Override
        protected Void doInBackground(Void... params) {
            TasksDatabase tasksDb = TasksDatabase.getInstance(context);
            tasksDb.insertContext(taskContext);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Intent intent = new Intent(context, AppEngineIntentService.class);
            intent.putExtra(Constant.APPENGINE_COMMAND_EXTRA, Command.SAVECONTEXT);
            intent.putExtra(Constant.TASKCONTEXT_EXTRA, taskContext);
            context.startService(intent);
        }
    }


    public static void saveContext(Context context, TaskContext taskContext) {
        TasksDatabase tasksDb = TasksDatabase.getInstance(context);
        tasksDb.insertContext(taskContext);

        Intent intent = new Intent(context, AppEngineIntentService.class);
        intent.putExtra(Constant.APPENGINE_COMMAND_EXTRA, Command.SAVECONTEXT);
        intent.putExtra(Constant.TASKCONTEXT_EXTRA, taskContext);
        context.startService(intent);
    }


    // *************** Class for loading tasks from database ***************

    public interface OnTasksLoadedListener {
        public void onTasksLoaded(ArrayList<Task> tasks);
    }


    public static class GetTasksByDateTask extends
            AsyncTask<Void, Void, ArrayList<Task>> {

        private OnTasksLoadedListener callback;
        private TasksDatabase tasksDb;
        private String date;

        public GetTasksByDateTask(Context context, String date) {
            try {
                this.callback = (OnTasksLoadedListener) context;
            } catch (ClassCastException e) {
                throw new ClassCastException(context.toString() +
                        " must implement OnDateSetListener");
            }
            tasksDb = TasksDatabase.getInstance(context);
            this.date = date;
        }

        @Override
        protected ArrayList<Task> doInBackground(Void... params) {
            ArrayList<Task> tasks = tasksDb.getTasksByDate(date);
            return tasks;
        }

        @Override
        protected void onPostExecute(ArrayList<Task> result) {
            callback.onTasksLoaded(result);
        }
    }

}
