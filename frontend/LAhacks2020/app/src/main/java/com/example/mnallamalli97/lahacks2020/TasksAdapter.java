package com.example.mnallamalli97.lahacks2020;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TaskHolder> {

    // List to store all the contact details
    private ArrayList<MasterTask> tasksList;
    private Context mContext;

    // Counstructor for the Class
    public TasksAdapter(ArrayList<MasterTask> tasksList, Context context) {
        this.tasksList = tasksList;
        this.mContext = context;
    }

    // This method creates views for the RecyclerView by inflating the layout
    // Into the viewHolders which helps to display the items in the RecyclerView
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        // Inflate the layout view you have created for the list rows here
        View view = layoutInflater.inflate(R.layout.teammate_row_item, parent, false);
        return new TaskHolder(view);
    }

    @Override
    public int getItemCount() {
        return tasksList == null? 0: tasksList.size();
    }

    // This method is called when binding the data to the views being created in RecyclerView
    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, final int position) {
        final MasterTask task = tasksList.get(position);

        // Set the data to the views here
        holder.setTaskName(task.getMasterChallenge().getName());
    }

    // This is your ViewHolder class that helps to populate data to the view
    public class TaskHolder extends RecyclerView.ViewHolder {

        private TextView taskName;


        public TaskHolder(View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.taskName);

        }
        public void setTaskName(String name) {
            taskName.setText(name);
        }
    }
}