package com.nookdev.bigdigtestapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.nookdev.bigdigtestapp.adapter.FeedAdapter;

public class MainActivity extends AppCompatActivity  implements FeedAdapter.IFragmentDialog {

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgressDialog() {
        if (dialog != null) {
            if (!dialog.isShowing())
                dialog.show();
        }
        else{
            dialog = new ProgressDialog(this);
            dialog.setMessage(getString(R.string.progress_dialog_message));
            dialog.show();
        }
    }

    @Override
    public void dismissProgressDialog() {
        if (dialog!=null)
            if(dialog.isShowing())
                dialog.dismiss();
    }

    @Override
    public void showErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(getString(R.string.loading_error_title))
                .setMessage(getString(R.string.loading_error))
                .setPositiveButton(R.string.loading_error_close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        closeApp();
                    }
                });
        builder.create().show();
    }

    private void closeApp(){
        finish();
    }

}
