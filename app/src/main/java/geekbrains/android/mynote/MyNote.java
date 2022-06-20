package geekbrains.android.mynote;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class MyNote implements Parcelable {

    //@SerializedName("name")
    private String name;

    //@SerializedName("noteBody")
    private String noteBody;

    //@SerializedName("CreationDate")
    private String CreationDate;

    protected MyNote(Parcel in) {
        name = in.readString();
        noteBody = in.readString();
        CreationDate = in.readString();
    }

    public static final Creator<MyNote> CREATOR = new Creator<MyNote>() {
        @Override
        public MyNote createFromParcel(Parcel in) {
            return new MyNote(in);
        }

        @Override
        public MyNote[] newArray(int size) {
            return new MyNote[size];
        }
    };

    public MyNote(String name, String noteBody, String creationDate) {
        this.name = name;
        this.noteBody = noteBody;
        CreationDate = creationDate;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(noteBody);
        parcel.writeString(CreationDate);
    }
}
