package com.a256devs.friendsbattles.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.a256devs.friendsbattles.R;


public class HelpDialog extends DialogFragment implements View.OnClickListener {

    public Dialog onCreateDialog(Bundle saveInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialog = inflater.inflate(R.layout.help_dialog, null);
        builder.setView(dialog);
        ((TextView) dialog.findViewById(R.id.help_dialog_tv)).setText(getResources().getText(R.string.help_dialog_text));
        dialog.findViewById(R.id.help_dialog_ok_button).setOnClickListener(this);

        return builder.create();
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
