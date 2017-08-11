package ramos.taller_hilos_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    List<CalcPrimesTask> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        taskList = new ArrayList<CalcPrimesTask>();

        Button btn_iniciar = (Button) findViewById(R.id.btn_mostrar);
        Button btn_fin = (Button) findViewById(R.id.btn_finish);

        btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText maximumEditText = (EditText) findViewById(R.id.txt_number);
                int maxNum = Integer.parseInt(maximumEditText.getText().toString());
                CalcPrimesTask task = new CalcPrimesTask(MainActivity.this);
                taskList.add(task);
                task.execute(maxNum);
                Toast.makeText(getApplicationContext(), "Nuevo proceso en marcha.", Toast.LENGTH_SHORT).show();

            }
        });

        btn_fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (CalcPrimesTask task : taskList) {
                    task.cancel(true);
                }
                Toast.makeText(getApplicationContext(), "Procesos Cancelados.", Toast.LENGTH_SHORT).show();

            }
        });


    }

}
