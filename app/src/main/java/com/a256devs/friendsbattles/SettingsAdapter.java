package com.a256devs.friendsbattles;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.MyViewHolder> {

    private List<String> stringsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public MyViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.settings_row);
        }
    }



    public SettingsAdapter(List<String> stringsList) {
        this.stringsList = stringsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.settings_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String string = stringsList.get(position);
        holder.textView.setText(String.valueOf(position));

    }

    @Override
    public int getItemCount() {
        return stringsList.size();
    }
}