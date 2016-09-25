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

public class ShareDialog extends DialogFragment implements View.OnClickListener {

    public interface ShareYesListener {
        void onSharePositiveClick(DialogFragment dialog);
    }

    ShareYesListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (ShareYesListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }


    public Dialog onCreateDialog(Bundle saveInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialog = inflater.inflate(R.layout.yes_no_dialog, null);
        builder.setView(dialog);
        ((TextView) dialog.findViewById(R.id.yes_no_dialog_tv)).setText(getResources().getText(R.string.rate_dialog_text));

        return builder.create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.yes_no_dialog_no_button:
                dismiss();
                break;
            case R.id.yes_no_dialog_yes_button:
                mListener.onSharePositiveClick(this);
                dismiss();
                break;

        }

    }
}
