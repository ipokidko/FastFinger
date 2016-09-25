package com.a256devs.fastfinger.Dialogs;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.a256devs.fastfinger.R;

public class ResetScoreDialog extends DialogFragment implements View.OnClickListener {

    public interface ResetScoreYesListener {
        void onResetScorePositiveClick(DialogFragment dialog);
    }

    ResetScoreYesListener mListener;

    public Dialog onCreateDialog(Bundle saveInstanceState) {
        mListener = (ResetScoreYesListener) getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialog = inflater.inflate(R.layout.yes_no_dialog, null);
        builder.setView(dialog);
        ((TextView) dialog.findViewById(R.id.yes_no_dialog_tv)).setText(getResources().getText(R.string.reset_score_dialog_text));
        dialog.findViewById(R.id.yes_no_dialog_yes_button).setOnClickListener(this);
        dialog.findViewById(R.id.yes_no_dialog_no_button).setOnClickListener(this);
        return builder.create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.yes_no_dialog_no_button:
                dismiss();
                break;
            case R.id.yes_no_dialog_yes_button:
                mListener.onResetScorePositiveClick(this);
                dismiss();
                break;

        }

    }

}
