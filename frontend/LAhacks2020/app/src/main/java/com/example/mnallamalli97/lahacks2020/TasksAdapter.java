package com.example.mnallamalli97.lahacks2020;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> {

    // List to store all the contact details
    private ArrayList<TaskInstance> tasksList;
    private OnTaskListener mOnTaskListener;
    private Context mContext;

    // Counstructor for the Class
    public TasksAdapter(ArrayList<TaskInstance> tasksList, Context context, OnTaskListener onTaskListener) {
        this.tasksList = tasksList;
        this.mContext = context;
        this.mOnTaskListener = onTaskListener;
    }

    // This method creates views for the RecyclerView by inflating the layout
    // Into the viewHolders which helps to display the items in the RecyclerView
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        // Inflate the layout view you have created for the list rows here
        View view = layoutInflater.inflate(R.layout.teammate_row_item, parent, false);
        return new ViewHolder(view, mOnTaskListener);
    }

    @Override
    public int getItemCount() {
        return tasksList == null? 0: tasksList.size();
    }

    // This method is called when binding the data to the views being created in RecyclerView
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final TaskInstance task = tasksList.get(position);
        // Set the data to the views here
        holder.setTaskName(task.getMasterTask().getMasterChallenge().getName());
    }

    // This is your ViewHolder class that helps to populate data to the view
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView taskName;
        OnTaskListener onTaskListener;

        public ViewHolder(View itemView, OnTaskListener onTaskListener) {
            super(itemView);
            taskName = itemView.findViewById(R.id.taskName);
            this.onTaskListener = onTaskListener;

            itemView.setOnClickListener(this);

        }
        public void setTaskName(String name) {
            taskName.setText(name);
        }

        @Override
        public void onClick(View view) {
            onTaskListener.onTaskClick(getAdapterPosition());
        }
    }

    public interface OnTaskListener {
        void onTaskClick(int position);
    }
}