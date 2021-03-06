package com.KrishanRoy.krishanroy.bookswappers.ui.view;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.KrishanRoy.krishanroy.bookswappers.R;
import com.KrishanRoy.krishanroy.bookswappers.ui.FragmentCommunication;
import com.KrishanRoy.krishanroy.bookswappers.ui.model.Book;
import com.jakewharton.rxbinding3.view.RxView;
import com.squareup.picasso.Picasso;

public class BookViewHolder extends RecyclerView.ViewHolder {
    private FragmentCommunication listener;
    private View view;
    private String name;
    private String email;
    private String city;
    private Book book;
    private ActionMode actionMode;
    public static final String TAG = "BookViewHolder";

    public BookViewHolder(@NonNull View itemView) {
        super(itemView);
        this.view = itemView;

    }

    @SuppressLint("CheckResult")
    public void onBind(final Book book,
                       final FragmentCommunication listener) {
        this.book = book;
        this.listener = listener;
        this.email = book.getUploaderEmail();
        this.name = book.getUploaderName();
        this.city = book.getUploaderCity();
        final TextView bookTitleTextView = itemView.findViewById(R.id.title_textView);
        final ImageView bookCoverImageView = itemView.findViewById(R.id.coverpage_imageView);
        final TextView locationTextView = itemView.findViewById(R.id.location_textView);

        bookTitleTextView.setText(book.getTitle());
        Log.d(TAG, "onBind: " + book.getBookImage());
        Picasso.get()
                .load(book.getBookImage())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(bookCoverImageView);
        locationTextView.setText(book.getUploaderCity());
        RxView.clicks(itemView)
                .subscribe(click -> alertDialoguePopUp());
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (actionMode != null) {
                    return false;
                }
                actionMode = ((AppCompatActivity) itemView.getContext()).startSupportActionMode(actionModeCallback);
                return true;
            }
        });

    }

    private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.getMenuInflater().inflate(R.menu.contextual_action_bar_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
//            if (itemView.getContext() == R.id.cab_delete_books) {
//                Toast.makeText(itemView.getContext(), "deleted", Toast.LENGTH_SHORT).show();
//            }
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            actionMode = null;
        }
    };

    @SuppressLint("CheckResult")
    private void alertDialoguePopUp() {
        final View view = LayoutInflater.from(itemView.getContext()).inflate(R.layout.alert_dialogue_layout, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());

        final TextView alertDonorNameTextView = view.findViewById(R.id.alertd_donor_name_textView);
        final TextView alertDonorEmailTextView = view.findViewById(R.id.alertd_email_donor_textView);
        final ImageView alertImageView = view.findViewById(R.id.alert_imageView);

        alertDonorNameTextView.setText(name);
        Picasso.get().load(book.getBookImage()).into(alertImageView);
        alertDonorEmailTextView.setText(email);
        builder.setView(view);
        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.show();
        RxView.clicks(alertDonorNameTextView)
                .subscribe(fromAlertDialogue -> {
                    dialog.dismiss();
                    listener.moveToUserDetailFragment(name, city, email);

                });
    }
}
