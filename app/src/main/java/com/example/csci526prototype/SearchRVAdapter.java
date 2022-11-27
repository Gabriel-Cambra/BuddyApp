package com.example.csci526prototype;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchRVAdapter extends RecyclerView.Adapter<SearchRVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<Sessions> sessionModalArrayList;
    private User mainUser;
    private DBUserHandler userDB;
    private Context context;


    // constructor
    public SearchRVAdapter(ArrayList<Sessions> sessionModalArrayList, User mainUser, Context context) {
        this.sessionModalArrayList = sessionModalArrayList;
        this.mainUser = mainUser;
        this.context = context;
        userDB = new DBUserHandler(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        int pos = position;
        Sessions modal = sessionModalArrayList.get(position);
        holder.sessionName.setText(modal.getName());
        holder.sessionDate.setText(modal.getDate());
        holder.sessionTime.setText(modal.getTime());
        holder.sessionDesc.setText(modal.getDesc());
        int muscle = modal.getMuscles();
        switch (muscle){
            case 0:
                holder.muscleImage.setImageResource(R.drawable.base_muscle_select);
                break;
            case 1:
                holder.muscleImage.setImageResource(R.drawable.arm_select);
                break;
            case 2:
                holder.muscleImage.setImageResource(R.drawable.chest_select);
                break;
            case 3:
                holder.muscleImage.setImageResource(R.drawable.leg_select);
                break;
            case 4:
                holder.muscleImage.setImageResource(R.drawable.back_select);
                break;
        }
        holder.join.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(context, "Joined Session", Toast.LENGTH_SHORT).show();
                userDB.addNewParticipant(modal.getId(),mainUser.getId());
                sessionModalArrayList.remove(pos);
                notifyDataSetChanged();
                //userDB.
            }
        });
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return sessionModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView sessionName, sessionDate, sessionTime, sessionDesc;
        private Button join;
        private ImageView muscleImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            sessionName = itemView.findViewById(R.id.sessionName);
            sessionDate = itemView.findViewById(R.id.sessionDate);
            sessionTime = itemView.findViewById(R.id.sessionTime);
            sessionDesc = itemView.findViewById(R.id.sessionDesc);
            muscleImage = itemView.findViewById(R.id.sessionMuscle);
            join = itemView.findViewById(R.id.join);
        }
    }
}
