package geekbrains.android.mynote;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StartFragment extends Fragment {

    static private String ARG_INDEX = "index";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_start, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        Button btn_start_note = view.findViewById(R.id.btn_start_note);
        btn_start_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ListOfNoteNamesFragment listOfNoteNamesFragment = new ListOfNoteNamesFragment();

                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container_1, listOfNoteNamesFragment)
                        .commit();
            }
        });

        Button btn_about = view.findViewById(R.id.btn_about);
        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotePropertiesFragment npf = NotePropertiesFragment.newInstance(0);
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_1, npf)
                        .addToBackStack(null)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
            }
        });
    }

}