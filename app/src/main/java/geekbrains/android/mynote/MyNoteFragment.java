package geekbrains.android.mynote;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MyNoteFragment extends Fragment {

    static private String ARG_INDEX = "index";
    private IDataSource dataSource = new DataSource();

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

//            tv_my_note.setText(dataSource.getMyNote().get(0).getNoteBody());
//            TypedArray str = getResources().obtainTypedArray(R.array.my_notes);

            Toast.makeText(requireContext(), String.valueOf(dataSource.getMyNote().size()), Toast.LENGTH_SHORT).show();
//            tv_my_note.setText(str.getText(index));
//            str.recycle();
        }

        Button btn_properties = view.findViewById(R.id.btn_properties);
        btn_properties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CustomDialogFragment().show(requireActivity().getSupportFragmentManager(), CustomDialogFragment.TAG);
            }
        });
    }

    protected static MyNoteFragment newInstance(MyNote myNote) {
        MyNoteFragment fragment = new MyNoteFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_INDEX, myNote);
        fragment.setArguments(args);
        return fragment;
    }

}
