package geekbrains.android.mynote;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ListOfNoteNamesFragment extends Fragment {

    private MyNote myNote = null;
    //private IDataSource dataSource = new DataSource();
    private RecyclerView recyclerView;
    private NoteAdapter adapter = new NoteAdapter();
    private final String PREFS = "MY_NOTE_PREFS";
    private final String PREFS_NOTE = "PREFS_MY_NOTE_KEY";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_of_note_names, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date currentTime = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("dd.MMM.yy", Locale.getDefault());
                DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                myNote = new MyNote(dateFormat.format(currentTime) + " " + timeFormat.format(currentTime), "My note body", dateFormat.format(currentTime) + " " + timeFormat.format(currentTime));
                //dataSource.addMyNote(myNote);
                MainActivity.dataSource.addMyNote(myNote);
                adapter.notifyItemInserted(0);

//                adapter.notifyItemInserted(dataSource.getMyNote().size() - 1);
//                recyclerView.scrollToPosition(dataSource.getMyNote().size() - 1);
            }
        });
        initList(view);
    }

    private void initList(View view) {
        //LinearLayout linearLayout = (LinearLayout) view;

        //ArrayList<MyNote> list = dataSource.getMyNote();
        //ArrayList<MyNote> list = MainActivity.dataSource.getMyNote();
        MainActivity.list = MainActivity.dataSource.getMyNote();
        recyclerView = view.findViewById(R.id.rv_notes);
        GridLayoutManager llm = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(llm);

        //adapter.setList(list);
        adapter.setList(MainActivity.list);
        adapter.setListener(new ListOfNoteNamesClickListener() {
            @Override
            public void onTextViewClick(MyNote myNote) {
//                dataSource.getMyNote().indexOf(myNote);
                //Toast.makeText(requireContext(), String.valueOf(dataSource.getMyNote().size()), Toast.LENGTH_SHORT).show();
                //Toast.makeText(requireContext(), "Size " + String.valueOf(MainActivity.dataSource.getMyNote().size()), Toast.LENGTH_SHORT).show();
                ListOfNoteNamesFragment.this.myNote = myNote;
                showMyNote(ListOfNoteNamesFragment.this.myNote);
                //String myNoteString = new GsonBuilder().create().toJson(myNote);
                String myNoteJson = new GsonBuilder().create().toJson(myNote);
                SharedPreferences prefs = requireActivity().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
                prefs.edit().putString(PREFS_NOTE, myNoteJson).apply();
            }

            @Override
            public void onLongItemClick(View view, int position) {
                Toast.makeText(requireContext(), "str", Toast.LENGTH_LONG).show();
                Activity activity = requireActivity();
                PopupMenu popupMenu = new PopupMenu(activity, view);
                activity.getMenuInflater().inflate(R.menu.menu_my_note_popup, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.action_update:
                                MyNote myNote = new MyNote("name01", "noteBody01", "date01");
                                //dataSource.updateMyNote(myNote, position);
                                MainActivity.dataSource.updateMyNote(myNote, position);
                                adapter.notifyItemChanged(position);
                                return true;
                            case R.id.action_delete:
                                //dataSource.deleteMyNote(position);
                                MainActivity.dataSource.deleteMyNote(position);
                                adapter.notifyItemRemoved(position);
                                return true;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

        recyclerView.setAdapter(adapter);
    }

    private void showMyNote(MyNote myNote) {
            showPortraitMyNote(myNote);
    }

    private void showPortraitMyNote(MyNote myNote) {
        MyNoteFragment myNoteFragment = MyNoteFragment.newInstance(MainActivity.list.indexOf(myNote));
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