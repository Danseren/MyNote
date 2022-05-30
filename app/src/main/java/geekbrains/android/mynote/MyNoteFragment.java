package geekbrains.android.mynote;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MyNoteFragment extends Fragment {

    static private String ARG_INDEX = "index";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            int index = arguments.getInt(ARG_INDEX);
            TextView tv_my_note = view.findViewById(R.id.tv_my_note);
            TypedArray str = getResources().obtainTypedArray(R.array.my_notes);
            tv_my_note.setText(str.getText(index));
            str.recycle();
        }

        Button btn_properties = view.findViewById(R.id.btn_properties);
        btn_properties.setOnClickListener(new View.OnClickListener() {
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

    public static MyNoteFragment newInstance(int index) {
        MyNoteFragment fragment = new MyNoteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }
}
