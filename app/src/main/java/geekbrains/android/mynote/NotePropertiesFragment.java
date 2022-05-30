package geekbrains.android.mynote;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NotePropertiesFragment extends Fragment {
    static private String ARG_INDEX = "index";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_properties, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
//        Bundle arguments = getArguments();
//        if (arguments != null) {
//            int index = arguments.getInt(ARG_INDEX);
//            TextView tv_my_note = view.findViewById(R.id.tv_my_note);
//            TypedArray str = getResources().obtainTypedArray(R.array.my_notes);
//            tv_my_note.setText(str.getText(index));
//            str.recycle();
//        }
    }

    public static NotePropertiesFragment newInstance(int index) {
        NotePropertiesFragment fragment = new NotePropertiesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }
}