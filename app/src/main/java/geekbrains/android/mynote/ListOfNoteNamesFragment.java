package geekbrains.android.mynote;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class ListOfNoteNamesFragment extends Fragment {

    private MyNote myNote = null;

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
        //LinearLayout linearLayout = (LinearLayout) view;
        ArrayList<MyNote> list = new ArrayList<>();
        String[] my_notes = getResources().getStringArray(R.array.my_notes);
        for (int i = 0; i < my_notes.length; i++) {
            MyNote myNote = new MyNote(my_notes[i].substring(0, 5), my_notes[i], "date");
            list.add(myNote);
        }
        RecyclerView recyclerView = view.findViewById(R.id.rv_notes);
        GridLayoutManager llm = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(llm);

        NoteAdapter adapter = new NoteAdapter();
        adapter.setList(list);
        adapter.setListener(new ListOfNoteNamesClickListener() {
            @Override
            public void onTextViewClick(MyNote myNote) {
                ListOfNoteNamesFragment.this.myNote = myNote;
                showMyNote(ListOfNoteNamesFragment.this.myNote);
            }
        });

        recyclerView.setAdapter(adapter);
    }

    private void showMyNote(MyNote myNote) {
            showPortraitMyNote(myNote);
    }

    private void showPortraitMyNote(MyNote myNote) {
        MyNoteFragment myNoteFragment = MyNoteFragment.newInstance(myNote);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container_1, myNoteFragment)
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

//    private void showLandMyNote(int position) {
//        MyNoteFragment myNoteFragment = MyNoteFragment.newInstance(position);
//        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
//
//        fragmentManager.beginTransaction()
//                .replace(R.id.fragment_container_2, myNoteFragment)
//                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                .commit();
//    }
}