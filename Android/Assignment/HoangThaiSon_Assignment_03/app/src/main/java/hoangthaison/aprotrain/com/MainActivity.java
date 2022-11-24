package hoangthaison.aprotrain.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import hoangthaison.aprotrain.com.adapters.NoteAdapter;
import hoangthaison.aprotrain.com.database.DBHelper;
import hoangthaison.aprotrain.com.models.Note;
import hoangthaison.aprotrain.com.databinding.AddNewNoteLayoutBinding;

public class MainActivity extends AppCompatActivity {

    private ListView lvNotes;
    private NoteAdapter noteAdapter;
    private DBHelper noteDatabase;
    private List<Note> allNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configView();
        configListView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (allNotes.size() == 0) {
            allNotes.addAll(noteDatabase.getAllNotes());
            noteAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_add_new_note:
                showDialogAddNoteViewBinding();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void configView() {
        lvNotes = findViewById(R.id.lv_notes);
        noteDatabase = DBHelper.getInstance(this);
    }

    private void configListView() {
        allNotes = new ArrayList<>();
        noteAdapter = new NoteAdapter(allNotes);
        lvNotes.setAdapter(noteAdapter);
    }

    private void showDialogAddNoteViewBinding() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AddNewNoteLayoutBinding binding = AddNewNoteLayoutBinding.inflate(getLayoutInflater());
        builder.setView(binding.getRoot());

        AlertDialog dialogAddNote = builder.create();
        dialogAddNote.show();

        binding.btnAddNew.setOnClickListener(view -> {
            Note note = new Note();
            note.setId((int) ((Calendar.getInstance().getTimeInMillis())/1000));
            note.setNote(binding.edtNewNoteTitle.getText().toString());
            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            note.setTime(dateFormat.format(date));

            if(binding.edtNewNoteTitle.getText().toString().isEmpty()) {
                Toast.makeText(
                        MainActivity.this,
                        "Ghi chú không được bỏ trống",
                        Toast.LENGTH_SHORT).show();
            } else {
                DBHelper noteDatabase = DBHelper.getInstance(MainActivity.this);
                noteDatabase.addNote(note);

                allNotes.add(note);
                noteAdapter.notifyDataSetChanged();

                binding.edtNewNoteTitle.setText("");
            }

            dialogAddNote.dismiss();
        });

        binding.btnNewCancel.setOnClickListener(view -> {
            dialogAddNote.dismiss();
        });
    }

}