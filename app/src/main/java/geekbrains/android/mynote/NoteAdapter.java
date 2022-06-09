package geekbrains.android.mynote;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<MyNote> list;

    public List<MyNote> getList() {
        return list;
    }

    private ListOfNoteNamesClickListener listener;

    public void setListener(ListOfNoteNamesClickListener listener) {
        this.listener = listener;
    }

    public void setList(List<MyNote> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.getItemView().<TextView>findViewById(R.id.tv_item_note_date).setText(list.get(position).getName());
        holder.getItemView().<TextView>findViewById(R.id.tv_item_note_body).setText(list.get(position).getNoteBody());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        private View itemView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            itemView.findViewById(R.id.tv_item_note_date).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onTextViewClick(list.get(getAdapterPosition()));
                }
            });

            itemView.findViewById(R.id.tv_item_note_body).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onTextViewClick(list.get(getAdapterPosition()));
                }
            });
        }

        public View getItemView() {
            return itemView;
        }
    }
}
