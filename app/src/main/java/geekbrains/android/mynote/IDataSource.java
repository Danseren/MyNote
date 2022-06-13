package geekbrains.android.mynote;

import java.util.ArrayList;

public interface IDataSource {
    ArrayList<MyNote> getMyNote();
    void addMyNote(MyNote myNote);
    void updateMyNote(MyNote myNote, int position);
    void deleteMyNote(int position);
}
