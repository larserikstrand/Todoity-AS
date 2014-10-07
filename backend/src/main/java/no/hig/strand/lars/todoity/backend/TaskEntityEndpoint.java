package no.hig.strand.lars.todoity.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;

import java.util.logging.Logger;

import javax.inject.Named;

import static no.hig.strand.lars.todoity.backend.OfyService.ofy;

/** An endpoint class we are exposing */
@Api(name = "taskEntityEndpoint", version = "v1", namespace = @ApiNamespace(ownerDomain = "backend.todoity.lars.strand.hig.no", ownerName = "backend.todoity.lars.strand.hig.no", packagePath=""))
public class TaskEntityEndpoint {

    // Make sure to add this endpoint to your web.xml file if this is a web application.

    private static final Logger LOG = Logger.getLogger(TaskEntityEndpoint.class.getName());

    /**
     * This method gets the <code>TaskEntity</code> object associated with the specified <code>id</code>.
     * @param id The id of the object to be returned.
     * @return The <code>TaskEntity</code> associated with <code>id</code>.
     */
    @ApiMethod(name = "getTaskEntity")
    public TaskEntity getTaskEntity(@Named("id") String id) {
        LOG.info("Calling getTaskEntity method");

        return ofy().load().type(TaskEntity.class).id(id).now();
    }

    /**
     * This inserts a new <code>TaskEntity</code> object.
     * @param taskEntity The object to be added.
     * @return The object to be added.
     */
    @ApiMethod(name = "insertTaskEntity")
    public TaskEntity insertTaskEntity(TaskEntity taskEntity) throws ConflictException {
        LOG.info("Calling insertTaskEntity method");

        if (taskEntity.getId() != null) {
            if (getTaskEntity(taskEntity.getId()) != null) {
                throw new ConflictException("Object already exists");
            }
        }

        ofy().save().entity(taskEntity).now();
        return taskEntity;
    }

    /**
     * This method updates the <code>TaskEntity</code> object associated with the specified <code>TaskEntity</code>.
     * @param taskEntity The object to be updated.
     * @return The updated <code>TaskEntity</code>.
     */
    @ApiMethod(name = "updateTaskEntity")
    public TaskEntity updateTaskEntity(TaskEntity taskEntity) throws NotFoundException{
        if (getTaskEntity(taskEntity.getId()) == null) {
            throw new NotFoundException("TaskEntity does not exist");
        }

        ofy().save().entity(taskEntity).now();
        return taskEntity;
    }

    /**
     * This method removes the <code>TaskEntity</code> object associated with the specified <code>id</code>.
     * @param id The id of the object to be removed.
     */
    @ApiMethod(name = "removeTaskEntity")
    public void removeTaskEntity(@Named("id") String id) throws NotFoundException{
        TaskEntity taskEntity = getTaskEntity(id);
        if (taskEntity == null) {
            throw new NotFoundException("TaskEntity does not exist");
        }

        ofy().delete().entity(taskEntity).now();
    }
}