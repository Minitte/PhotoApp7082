package c7082.davisp.photoapp7082.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import c7082.davisp.photoapp7082.R;
import c7082.davisp.photoapp7082.data.ImageData;

public class EditDialogFragment extends DialogFragment {

    /**
     * Edit listeners
     */
    public interface EditDialogListener {
        public void onDialogPositiveClick(String newCaption);
    }

    /**
     * Listener subscribed to events
     */
    public EditDialogListener listener;

    public ImageData data;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.dialog_edit, null);

        final EditText editCaption = (EditText)dialogView.findViewById(R.id.editCaption);

        editCaption.setText(data.getCaption());

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(dialogView)
                // Add action buttons
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDialogPositiveClick(
                                editCaption.getText().toString());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditDialogFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }
}
