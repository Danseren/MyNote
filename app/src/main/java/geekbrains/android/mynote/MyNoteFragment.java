package geekbrains.android.mynote;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.GsonBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MyNoteFragment extends Fragment {

    static private String ARG_INDEX = "index";
    private String note_body;

    //private IDataSource dataSource = new DataSource();

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
            EditText tv_my_note = view.findViewById(R.id.et_my_note);
            tv_my_note.setText(MainActivity.dataSource.getMyNote().get(index).getNoteBody());
//            tv_my_note.setText(dataSource.getMyNote().get(0).getNoteBody());
//            TypedArray str = getResources().obtainTypedArray(R.array.my_notes);

            Toast.makeText(requireContext(), "Vot " + String.valueOf(index), Toast.LENGTH_SHORT).show();

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
//
//    protected static MyNoteFragment newInstance(MyNote myNote) {
//        MyNoteFragment fragment = new MyNoteFragment();
//        Bundle args = new Bundle();
//        args.putParcelable(ARG_INDEX, myNote);
//        fragment.setArguments(args);
//        return fragment;
//    }

    protected static MyNoteFragment newInstance(int index) {
        MyNoteFragment fragment = new MyNoteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStop() {
        super.onStop();
        Bundle arguments = getArguments();

        Date currentTime = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd.MMM.yy", Locale.getDefault());
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        MainActivity.list.remove(arguments.getInt(ARG_INDEX));
        EditText et = MyNoteFragment.this.getView().findViewById(R.id.et_my_note);
        String str = String.valueOf(et.getText());
        MainActivity.list.add(arguments.getInt(ARG_INDEX), new MyNote(dateFormat.format(currentTime) + " " + timeFormat.format(currentTime), str, dateFormat.format(currentTime) + " " + timeFormat.format(currentTime)));
        //String myNoteString = new GsonBuilder().create().toJson(MainActivity.list.get(0));
    }
}
