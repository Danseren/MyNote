package geekbrains.android.mynote;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListOfNoteNamesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_of_note_names, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        initList(view);
    }

    private void initList(View view) {

        LinearLayout linearLayout = (LinearLayout) view;
        String[] my_notes = getResources().getStringArray(R.array.my_notes);

        for (int i = 0; i < my_notes.length; i++) {
            String my_note = my_notes[i];
            TextView tv = new TextView(getContext());
            tv.setText(my_note);
            tv.setTextSize(30);
            linearLayout.addView(tv);
            final int position = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showMyNote(position);
                }
            });
        }
    }

    private void showMyNote(int position) {

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            showLandMyNote(position);
        } else {
            showPortraitMyNote(position);
        }

    }

    private void showPortraitMyNote(int position) {
        MyNoteFragment myNoteFragment = MyNoteFragment.newInstance(position);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container_1, myNoteFragment)
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    private void showLandMyNote(int position) {
        MyNoteFragment myNoteFragment = MyNoteFragment.newInstance(position);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container_2, myNoteFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}