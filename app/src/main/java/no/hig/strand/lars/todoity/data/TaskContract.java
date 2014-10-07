package no.hig.strand.lars.todoity.data;

import android.provider.BaseColumns;

public final class TaskContract {


    public TaskContract() {}



    public static abstract class ListEntry implements BaseColumns {
        public static final String TABLE_NAME = "list";
        public static final String COLUMN_NAME_DATE = "date";
    }



    public static abstract class TaskEntry implements BaseColumns {
        public static final String TABLE_NAME = "task";
        public static final String COLUMN_NAME_LIST = "list";
        public static final String COLUMN_NAME_CATEGORY = "category";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_LOCATION_LAT = "location_lat";
        public static final String COLUMN_NAME_LOCATION_LNG = "location_lng";
        public static final String COLUMN_NAME_ADDRESS = "address";
        public static final String COLUMN_NAME_PRIORITY = "priority";
        public static final String COLUMN_NAME_IS_ACTIVE = "is_active";
        public static final String COLUMN_NAME_TEMP_START = "temp_start";
        public static final String COLUMN_NAME_TIME_START = "time_start";
        public static final String COLUMN_NAME_TIME_END = "time_end";
        public static final String COLUMN_NAME_TIME_SPENT = "time_spent";
        public static final String COLUMN_NAME_IS_FINISHED = "is_finished";
    }



    public static abstract class ContextEntry implements BaseColumns {
        public static final String TABLE_NAME = "context";
        public static final String COLUMN_NAME_TASK = "task";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_CONTEXT = "context";
        public static final String COLUMN_NAME_DETAILS = "details";
        public static final String TYPE_LOCATION = "location";
        public static final String TYPE_ACTIVITY = "activity";
    }



    public static abstract class TimesEntry implements BaseColumns {
        public static final String TABLE_NAME = "times";
        public static final String COLUMN_NAME_TASK_ID = "taskid";
        public static final String COLUMN_NAME_START_TIME = "starttime";
        public static final String COLUMN_NAME_END_TIME = "endtime";
    }
}
