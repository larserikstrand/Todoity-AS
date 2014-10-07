package no.hig.strand.lars.todoity.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import no.hig.strand.lars.todoity.R;
import no.hig.strand.lars.todoity.activities.ListActivity;
import no.hig.strand.lars.todoity.activities.MainActivity;
import no.hig.strand.lars.todoity.activities.TaskActivity;
import no.hig.strand.lars.todoity.data.Constant;
import no.hig.strand.lars.todoity.data.Task;
import no.hig.strand.lars.todoity.fragments.WeekFragment;
import no.hig.strand.lars.todoity.helpers.DatabaseUtilities.DeleteTask;
import no.hig.strand.lars.todoity.helpers.DatePickerFragment;
import no.hig.strand.lars.todoity.helpers.Utilities;
import no.hig.strand.lars.todoity.helpers.Utilities.OnConfirmListener;

public class WeekListAdapter extends BaseExpandableListAdapter implements
        OnMenuItemClickListener {

    private Context mContext;
    private List<String> mDates;
    private HashMap<String, List<Task>> mTasks;
    private LayoutInflater mInflater;
    private ChildViewHolder mCurrentHolder;
    private Task mCurrentTask;

    private OnClickListener mEditListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            GroupViewHolder holder = (GroupViewHolder)
                    ((View) v.getParent()).getTag();

            Intent intent = new Intent(mContext, ListActivity.class);
            intent.putExtra(Constant.DATE_EXTRA,
                    holder.dateText.getText().toString());

            ((MainActivity) mContext).startActivity(intent);
        }
    };

    private OnClickListener mDeleteListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            final GroupViewHolder holder = (GroupViewHolder)
                    ((View) v.getParent()).getTag();

            String dialogTitle = mContext.getString(R.string.delete) + " " +
                    mTasks.get(mDates.get(holder.position)).size() + " " +
                    mContext.getString(R.string.tasks);
            String dialogMessage = mContext.getString(
                    R.string.delete_list_message_pre) + " " +
                    mDates.get(holder.position) + " " +
                    mContext.getString(R.string.delete_list_message);
            Utilities.showConfirmDialog(mContext, dialogTitle, dialogMessage,
                    mContext.getString(R.string.delete), new OnConfirmListener() {
                        @Override
                        public void onConfirm(DialogInterface dialog, int id) {
                            List<Task> tasks = mTasks.remove(mDates.get(holder.position));
                            mDates.remove(holder.position);
                            notifyDataSetChanged();

                            for (Task task : tasks) {
                                new DeleteTask(mContext, task).execute();
                            }
                            ((MainActivity) mContext).updateNeighborFragments();
                        }
                    });
        }
    };

    private OnClickListener mOptionsListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            mCurrentHolder = (ChildViewHolder) ((View) v.getParent()).getTag();
            mCurrentTask = (Task) getChild(mCurrentHolder.groupPosition,
                    mCurrentHolder.childPosition);
            showPopupMenu(v);
        }
    };

    public WeekListAdapter(Context context, List<String> dates,
                           HashMap<String, List<Task>> tasks) {
        mContext = context;
        mDates = dates;
        mTasks = tasks;
        mInflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mTasks.get(mDates.get(groupPosition)).get(childPosition);
    }



    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }



    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(
                    R.layout.adapter_expandablelist_child, parent, false);

            holder = new ChildViewHolder();
            holder.taskText = (TextView) convertView.findViewById(
                    R.id.child_task_text);
            holder.subText = (TextView) convertView.findViewById(
                    R.id.child_sub_text);
            holder.optionsButton = (ImageButton) convertView.findViewById(
                    R.id.child_options_button);

            convertView.setTag(holder);
        } else {
            holder = (ChildViewHolder) convertView.getTag();
        }

        holder.groupPosition = groupPosition;
        holder.childPosition = childPosition;

        Task task = (Task) getChild(groupPosition, childPosition);

        holder.taskText.setText(Task.getTaskTextFromTask(task));
        holder.subText.setText(Task.getSubTextFromTask(task));

        if (task.isFinished()) {
            holder.optionsButton.setVisibility(View.GONE);
            setStrikeThrough(holder, true);
        } else {
            holder.optionsButton.setVisibility(View.VISIBLE);
            holder.optionsButton.setOnClickListener(mOptionsListener);
        }

        if (task.isActive()) {

        }

        return convertView;
    }



    @Override
    public int getChildrenCount(int groupPosition) {
        return mTasks.get(mDates.get(groupPosition)).size();
    }



    @Override
    public Object getGroup(int groupPosition) {
        return mDates.get(groupPosition);
    }



    @Override
    public int getGroupCount() {
        return mDates.size();
    }



    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }



    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        GroupViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(
                    R.layout.adapter_expandablelist_group, parent, false);

            holder = new GroupViewHolder();
            holder.dateText = (TextView) convertView.findViewById(
                    R.id.group_date_text);
            holder.editButton = (ImageButton) convertView.findViewById(
                    R.id.group_edit_button);
            holder.deleteButton = (ImageButton) convertView.findViewById(
                    R.id.group_delete_button);

            convertView.setTag(holder);
        } else {
            holder = (GroupViewHolder) convertView.getTag();
        }

        holder.position = groupPosition;

        String date = (String) getGroup(groupPosition);
        holder.dateText.setText(date);

        holder.editButton.setOnClickListener(mEditListener);
        holder.deleteButton.setOnClickListener(mDeleteListener);

        return convertView;
    }



    @Override
    public boolean hasStableIds() {
        return false;
    }



    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }



    private void setStrikeThrough(ChildViewHolder holder, boolean isFinished) {
        if (isFinished) {
            holder.taskText.setPaintFlags(holder.taskText.getPaintFlags()
                    | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.subText.setPaintFlags(holder.subText.getPaintFlags()
                    | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.taskText.setPaintFlags(holder.taskText.getPaintFlags()
                    & (~Paint.STRIKE_THRU_TEXT_FLAG));
            holder.subText.setPaintFlags(holder.subText.getPaintFlags()
                    & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }
    }



    private static class ChildViewHolder {
        public TextView taskText;
        public TextView subText;
        public ImageButton optionsButton;
        public int groupPosition;
        public int childPosition;
    }



    private static class GroupViewHolder {
        public TextView dateText;
        public ImageButton editButton;
        public ImageButton deleteButton;
        public int position;
    }



    private void showPopupMenu(View v) {
        PopupMenu popup = new PopupMenu(mContext, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.options, popup.getMenu());
        popup.setOnMenuItemClickListener(this);
        popup.show();
    }



    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.options_edit:
                Intent intent = new Intent(mContext, TaskActivity.class);
                intent.putExtra(Constant.TASK_EXTRA, mCurrentTask);
                intent.putExtra(Constant.POSITION_EXTRA, mCurrentHolder.childPosition);
                ((WeekFragment) ((MainActivity) mContext).getFragmentAt(1))
                        .startActivityForResult(intent, Constant.EDIT_TASK_REQUEST);
                return true;

            case R.id.options_move:
                DialogFragment datePicker = new DatePickerFragment();
                Bundle args = new Bundle();
                args.putString(Constant.DATEPICKER_TITLE_EXTRA,
                        mContext.getString(R.string.move_task));
                args.putParcelable(Constant.TASK_EXTRA, mCurrentTask);
                datePicker.setArguments(args);
                datePicker.setTargetFragment(
                        ((MainActivity) mContext).getFragmentAt(1),
                        Constant.MOVE_TASK_REQUEST);
                datePicker.show(((MainActivity) mContext)
                        .getSupportFragmentManager(), "datePicker");
                return true;

            case R.id.options_delete:
                // Show dialog to the user asking for confirmation of deletion.
                String dialogMessage = mCurrentHolder.taskText.getText().toString()
                        + " " +  mContext.getString(R.string.delete_list_message);
                Utilities.showConfirmDialog(mContext, null, dialogMessage,
                        mContext.getString(R.string.delete),
                        new OnConfirmListener() {
                            @Override
                            public void onConfirm(DialogInterface dialog, int id) {
                                String date = (String) getGroup(mCurrentHolder.groupPosition);
                                if (mTasks.get(date).size() == 1) {
                                    mDates.remove(date);
                                    mTasks.remove(date);
                                } else {
                                    mTasks.get(date).remove(mCurrentTask);
                                }
                                notifyDataSetChanged();
                                new DeleteTask(mContext, mCurrentTask).execute();
                                ((MainActivity) mContext).updateNeighborFragments();
                            }
                        });
                return true;

            default:
                return false;
        }
    }

}
