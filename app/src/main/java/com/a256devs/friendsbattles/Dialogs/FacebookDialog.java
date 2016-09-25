package com.a256devs.friendsbattles.Dialogs;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.a256devs.friendsbattles.R;

public class FacebookDialog extends DialogFragment implements View.OnClickListener {


    public interface FacebookYesListener {
        void onFacebookPositiveClick(DialogFragment dialog);
    }

    FacebookYesListener mListener;

    public Dialog onCreateDialog(Bundle saveInstanceState) {
        mListener = (FacebookYesListener) getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialog = inflater.inflate(R.layout.yes_no_dialog, null);
        builder.setView(dialog);
        ((TextView) dialog.findViewById(R.id.yes_no_dialog_tv)).setText(getResources().getText(R.string.facebook_dialog_text));
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
                mListener.onFacebookPositiveClick(this);
                dismiss();
                break;

        }

    }
}
