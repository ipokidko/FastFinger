package com.a256devs.friendsbattles;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a256devs.friendsbattles.Dialogs.SettingsDialog;

import java.util.ArrayList;

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.MyViewHolder> {

    public interface OnItemClick {
        void onItemClickListener(int color);
    }

    OnItemClick colorlistener;

    private ArrayList<Integer> colorList;
    private SettingsDialog settingsDialog;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public RelativeLayout relativeLayout;

        public MyViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.settings_row);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.settings_rv_rlayout);
        }
    }


    public SettingsAdapter(ArrayList<Integer> colorList) {
        this.colorList = colorList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.settings_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorlistener.onItemClickListener(colorList.get(position));
            }
        });
        holder.textView.setText("9");
        holder.textView.setTextColor(colorList.get(position));
        GradientDrawable newBackground = new GradientDrawable();
        newBackground.setStroke(5, colorList.get(position));
        newBackground.setColor(Color.WHITE);

        holder.textView.setBackground(newBackground);

    }

    @Override
    public int getItemCount() {
        return colorList.size();
    }

    public void setSettingsDialog(SettingsDialog sd) {
        this.settingsDialog = sd;
        this.colorlistener = sd;
    }
}