package com.a256devs.friendsbattles.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.a256devs.friendsbattles.R;
import com.a256devs.friendsbattles.SettingsAdapter;

import java.util.ArrayList;


public class SettingsDialog extends DialogFragment implements View.OnClickListener, SettingsAdapter.OnItemClick {


    @Override
    public void onItemClickListener(int color) {
        //On color int color pressed
        Log.v("flow", "Red: " + Color.RED + " Yellow: " + Color.YELLOW + " tape: " + color);
    }

    public interface SettingsListener {
        void onSettingsClickReset(DialogFragment dialog);
        void onSettingsClickChangeItemColor(DialogFragment dialog);
    }

    SettingsListener mListener;
    private ArrayList<Integer> colorsArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SettingsAdapter mAdapter;


    public Dialog onCreateDialog(Bundle saveInstanceState) {
        mListener = (SettingsListener) getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialog = inflater.inflate(R.layout.settings_dialog, null);
        builder.setView(dialog);
        dialog.findViewById(R.id.settings_dialog_reset_button).setOnClickListener(this);
        dialog.findViewById(R.id.settings_dialog_save_button).setOnClickListener(this);
        dialog.findViewById(R.id.settings_dialog_ok_button).setOnClickListener(this);
        recyclerView = (RecyclerView) dialog.findViewById(R.id.recycler_view);
        mAdapter = new SettingsAdapter(colorsArrayList);
        mAdapter.setSettingsDialog(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        addColorsToDataForAdapter();
        mAdapter.notifyDataSetChanged();
        return builder.create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.settings_dialog_ok_button:
                dismiss();
                break;
            case R.id.settings_dialog_reset_button:
                mListener.onSettingsClickReset(this);
                dismiss();
                break;
            case R.id.settings_dialog_save_button:
                mListener.onSettingsClickChangeItemColor(this);
                dismiss();
                break;
        }
    }

    private void addColorsToDataForAdapter() {
        colorsArrayList.clear();
        colorsArrayList.add(Color.RED);
        colorsArrayList.add(Color.BLUE);
        colorsArrayList.add(Color.GREEN);
        colorsArrayList.add(Color.YELLOW);
        colorsArrayList.add(Color.LTGRAY);
        colorsArrayList.add(Color.GRAY);
        colorsArrayList.add(Color.DKGRAY);
        colorsArrayList.add(Color.MAGENTA);
        colorsArrayList.add(Color.CYAN);
    }
}


