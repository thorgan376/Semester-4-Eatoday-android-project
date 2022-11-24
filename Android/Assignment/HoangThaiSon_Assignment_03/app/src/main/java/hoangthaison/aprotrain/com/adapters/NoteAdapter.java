package hoangthaison.aprotrain.com.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import hoangthaison.aprotrain.com.R;
import hoangthaison.aprotrain.com.models.Note;

public class NoteAdapter extends BaseAdapter {
    private List<Note> notes;

    public NoteAdapter(List<Note> allNotes) {
        this.notes = allNotes;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int i) {
        return notes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view, viewGroup, false);
        }
        TextView tvNoteTitle = view.findViewById(R.id.tv_note_title);
        TextView tvTimeCreated = view.findViewById(R.id.tv_time_created);
        Note noteItem = notes.get(i);

        tvNoteTitle.setText(noteItem.getNote());
        tvTimeCreated.setText(noteItem.getTime());
        return view;
    }
}

