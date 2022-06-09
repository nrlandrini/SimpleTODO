package sg.edu.rp.c346.id21021749.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTask;
    Button btnAdd;
    Button btnDel;
    Button btnClear;
    ListView lvTask;
    Spinner spnAddDel;

    ArrayList<String> alTasks; //arrayList all the task
    ArrayAdapter<String> aaTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.btnadd);
        btnDel = findViewById(R.id.btndelete);
        btnClear = findViewById(R.id.btnclear);
        spnAddDel = findViewById(R.id.spinner);
        lvTask = findViewById(R.id.listViewTask);


        alTasks = new ArrayList<>();
        aaTasks = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTasks);
        lvTask.setAdapter(aaTasks);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alTasks.removeAll(alTasks);
                aaTasks.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "All Tasks Removed",
                        Toast.LENGTH_LONG).show();
            }
        });

        spnAddDel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position) {
                    case 0:

                        btnDel.setEnabled(false);
                        btnAdd.setEnabled(true);
                        etTask.setHint("Enter new task");
                        btnAdd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String task = etTask.getText().toString();
                                alTasks.add(task);
                                aaTasks.notifyDataSetChanged();
                            }
                        });
                        break;
                    case 1:

                        btnAdd.setEnabled(false);
                        btnDel.setEnabled(true);
                        etTask.setHint("Enter index position to be removed");
                        btnDel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (etTask.getText().toString().trim().length() > 0) {
                                    int pos = Integer.parseInt(etTask.getText().toString());
                                    alTasks.remove(pos);
                                    aaTasks.notifyDataSetChanged();
                                    Toast.makeText(MainActivity.this, "Task removed",
                                            Toast.LENGTH_LONG).show();
                                } else if (etTask.getText().toString().trim().length() == 0) {
                                    Toast.makeText(MainActivity.this, "You don't have any task to remove",
                                            Toast.LENGTH_LONG).show();
                                }

                            }
                        });
                        break;

                }

            }

        @Override
        public void onNothingSelected (AdapterView < ? > adapterView){

        }
    });


}
}
