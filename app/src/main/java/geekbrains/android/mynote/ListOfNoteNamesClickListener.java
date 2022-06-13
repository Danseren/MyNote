package geekbrains.android.mynote;

import android.view.View;

public interface ListOfNoteNamesClickListener {

    void onTextViewClick(MyNote myNote);
    void onLongItemClick(View view, int position);
}
