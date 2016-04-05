package com.zeus.bookcase.app.cabinet;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.cabinet.ui.activity.AddBookActivity;
import com.zeus.bookcase.app.cabinet.ui.activity.BookLibraryActivity;
import com.zeus.bookcase.app.cabinet.ui.activity.CaptureActivity;
import com.zeus.bookcase.app.cabinet.ui.activity.FilmRoomActivity;
import com.zeus.bookcase.app.cabinet.ui.activity.MusicCardActivity;
import com.zeus.bookcase.app.cabinet.ui.activity.MyBookActivity;

/**
 * Created by zeus_coder on 2016/2/3.
 */
public class CaseFragment extends Fragment {

    private LinearLayout bookStore;
    private LinearLayout musicStore;
    private LinearLayout filmStore;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.case__fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bookStore = (LinearLayout) view.findViewById(R.id.book_store);
        musicStore = (LinearLayout) view.findViewById(R.id.music_store);
        filmStore = (LinearLayout) view.findViewById(R.id.film_store);
        bookStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity(), DetailActivity.class));
                //startActivity(new Intent(getActivity(), BookLibraryActivity.class));
                //startActivity(new Intent(getActivity(), BookCaseMenuActivity.class));
//                startActivity(new Intent(getActivity(), BookLibraryActivity.class));
                startActivity(new Intent(getActivity(), MyBookActivity.class));
            }
        });
        musicStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), MusicCardActivity.class));
//                startActivityForResult(new Intent(getActivity(), CaptureActivity.class), 100);
                startActivity(new Intent(getActivity(), AddBookActivity.class));
            }
        });
        filmStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FilmRoomActivity.class));
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
