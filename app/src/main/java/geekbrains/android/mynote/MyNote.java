package geekbrains.android.mynote;

public class MyNote {

    private String name;
    private String noteBody;
    private String noteDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNoteBody() {
        return noteBody;
    }

    public void setNoteBody(String noteBody) {
        this.noteBody = noteBody;
    }

    public String getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(String noteDate) {
        this.noteDate = noteDate;
    }

    @Override
    public String toString() {
        return "MyNote{" +
                "name='" + name + '\'' +
                ", noteBody='" + noteBody + '\'' +
                ", noteDate='" + noteDate + '\'' +
                '}';
    }
}
