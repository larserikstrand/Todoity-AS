package no.hig.strand.lars.todoity.helpers;

import no.hig.strand.lars.todoity.backend.taskEntityEndpoint.model.TaskEntity;
import no.hig.strand.lars.todoity.data.Task;

/**
 * Created by lars.strand on 30.09.2014.
 */
public class AppEngineUtilities {
    public static TaskEntity getTaskEntityFromTask(Task task) {
        TaskEntity taskEntity = new TaskEntity();

        taskEntity.setDate(task.getDate())
                .setCategory(task.getCategory())
                .setDescription(task.getDescription())
                .setLatitude(task.getLatitude())
                .setLongitude(task.getLongitude())
                .setAddress(task.getAddress())
                .setPriority(task.getPriority())
                .setActive(task.isActive())
                .setTimeStarted(Utilities.millisToDate(task.getTimeStarted()))
                .setTimeEnded(Utilities.millisToDate(task.getTimeEnded()))
                .setTimeSpent(task.getTimeSpent())
                .setFinished(task.isFinished())
                .setFixedStart(task.getFixedStart())
                .setFixedEnd(task.getFixedEnd());

        return taskEntity;
    }
}
