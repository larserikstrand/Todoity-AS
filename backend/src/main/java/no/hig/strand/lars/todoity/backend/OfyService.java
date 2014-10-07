package no.hig.strand.lars.todoity.backend;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

/**
 * Created by lars.strand on 30.09.2014.
 */
public class OfyService {
    static {
        ObjectifyService.register(TaskEntity.class);
        ObjectifyService.register(ContextEntity.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}
