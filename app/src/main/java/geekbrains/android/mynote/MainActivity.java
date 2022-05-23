package geekbrains.android.mynote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] my_notes = getResources().getStringArray(R.array.my_notes);
        MyNote myNote[] = new MyNote[my_notes.length];

        //Toast.makeText(MainActivity.this, my_notes[1], Toast.LENGTH_SHORT).show();

        for (int i = 0; i < my_notes.length; i++) {

            myNote[i] = new MyNote();
            myNote[i].setName(my_notes[i].substring(0, 5));
            myNote[i].setNoteBody(my_notes[i]);
            //Toast.makeText(MainActivity.this, myNote[i].toString(), Toast.LENGTH_LONG).show();
        }

        ListOfNoteNamesFragment listOfNoteNamesFragment = new ListOfNoteNamesFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_1, listOfNoteNamesFragment)
                .commit();

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            MyNoteFragment mnf = MyNoteFragment.newInstance(0);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_2, mnf)
                    .commit();
        }


    }
}

/*
С этого урока мы начинаем разработку приложения с заметками.
        1. Почитайте документацию методов requireActivity(), requireContext(), getActivity(), getContext() и объясните разницу между ними;
        2. Создайте класс данных со структурой заметок: название заметки, описание заметки, дата создания и т. п.
        3. Создайте фрагмент для вывода этих данных.
        4. Встройте этот фрагмент в активити. У вас должен получиться экран с заметками, который мы будем улучшать с каждым новым уроком.
        5. Добавьте фрагмент, в котором открывается заметка. По аналогии с примером из урока:
        если нажать на элемент списка в портретной ориентации — открывается новое окно,
        если нажать в ландшафтной — окно открывается рядом.
6. * Разберитесь, как можно сделать, и сделайте корректировку даты создания при помощи DatePicker.
 */