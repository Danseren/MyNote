package geekbrains.android.mynote;

public class MyNote {

    private String name;
    private String noteBody;
    private String CreationDate;

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

    public String getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(String creationDate) {
        this.CreationDate = creationDate;
    }

    @Override
    public String toString() {
        return "MyNote{" +
                "name='" + name + '\'' +
                ", noteBody='" + noteBody + '\'' +
                ", noteDate='" + CreationDate + '\'' +
                '}';
    }
}
