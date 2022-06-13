package geekbrains.android.mynote;

import java.util.ArrayList;

public class DataSource implements IDataSource {
    
    private ArrayList<MyNote> list = new ArrayList<>();
    
    public DataSource() {
        initList();
    }

    private void initList() {
//        list.add(new MyNote("name01", "noteBody01", "date01"));
//        list.add(new MyNote("name02", "noteBody02", "date01"));
//        list.add(new MyNote("name03", "noteBody03", "date01"));
//        list.add(new MyNote("name04", "noteBody04", "date01"));
    }

    @Override
    public ArrayList<MyNote> getMyNote() {
        return list;
    }

    @Override
    public void addMyNote(MyNote myNote) {
        list.add(0, myNote);
    }

    @Override
    public void updateMyNote(MyNote myNote, int position) {
        list.remove(position);
        list.add(position, myNote);
    }

    @Override
    public void deleteMyNote(int position) {
        list.remove(position);
    }

}
