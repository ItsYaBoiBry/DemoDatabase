package android.myapplicationdev.com.demodatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String task = "Submit RJ";
    String date = "25 Apr 2016";
    ListView lv;
    ArrayList<String> al;
    ArrayAdapter<String> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnInsert = (Button) findViewById(R.id.btnInsert);
        Button btnGetTask = (Button) findViewById(R.id.btnGetTask);
        lv = (ListView) findViewById(R.id.lv_task);
        al = new ArrayList<String>();
        aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,al);
        lv.setAdapter(aa);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

            // Insert a task
                db.insertTask(task,date);
                db.close();
        }
        });
        btnGetTask.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<Task> data = db.getTasks();
                db.close();


                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", (data.get(i).getId()) +". "+data.get(i).getDescription());
                    String id = String.valueOf(data.get(i).getId());
                    String description = data.get(i).getDescription();
                    String date = data.get(i).getDate();
                    al.add(id +".\n"+ description +"\n"+ date);

                }aa.notifyDataSetChanged();
            }
        });

    }
}
