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
@Api(name = "contextEntityEndpoint", version = "v1", namespace = @ApiNamespace(ownerDomain = "backend.todoity.lars.strand.hig.no", ownerName = "backend.todoity.lars.strand.hig.no", packagePath=""))
public class ContextEntityEndpoint {

    // Make sure to add this endpoint to your web.xml file if this is a web application.

    private static final Logger LOG = Logger.getLogger(ContextEntityEndpoint.class.getName());

    /**
     * This method gets the <code>ContextEntity</code> object associated with the specified <code>id</code>.
     * @param id The id of the object to be returned.
     * @return The <code>ContextEntity</code> associated with <code>id</code>.
     */
    @ApiMethod(name = "getContextEntity")
    public ContextEntity getContextEntity(@Named("id") Long id) {
        LOG.info("Calling getContextEntity method");

        return ofy().load().type(ContextEntity.class).id(id).now();
    }

    /**
     * This inserts a new <code>ContextEntity</code> object.
     * @param contextEntity The object to be added.
     * @return The object to be added.
     */
    @ApiMethod(name = "insertContextEntity")
    public ContextEntity insertContextEntity(ContextEntity contextEntity) throws ConflictException {
        LOG.info("Calling insertContextEntity method");

        if (contextEntity.getId() != null) {
            if (getContextEntity(contextEntity.getId()) != null) {
                throw new ConflictException("Object already exists");
            }
        }

        ofy().save().entity(contextEntity).now();
        return contextEntity;
    }

    /**
     * This method updates the <code>ContextEntity</code> object associated with the specified <code>ContextEntity</code>.
     * @param contextEntity The object to be updated.
     * @return The updated <code>ContextEntity</code>.
     */
    @ApiMethod(name = "updateContextEntity")
    public ContextEntity updateContextEntity(ContextEntity contextEntity) throws NotFoundException {
        if (getContextEntity(contextEntity.getId()) == null) {
            throw new NotFoundException("ContextEntity does not exist");
        }

        ofy().save().entity(contextEntity).now();
        return contextEntity;
    }

    /**
     * This method removes the <code>ContextEntity</code> object associated with the specified <code>id</code>.
     * @param id The id of the object to be removed.
     */
    @ApiMethod(name = "removeContextEntity")
    public void removeContextEntity(@Named("id") Long id) throws NotFoundException{
        ContextEntity contextEntity = getContextEntity(id);
        if (contextEntity == null) {
            throw new NotFoundException("ContextEntity does not exist");
        }

        ofy().delete().entity(contextEntity).now();
    }
}