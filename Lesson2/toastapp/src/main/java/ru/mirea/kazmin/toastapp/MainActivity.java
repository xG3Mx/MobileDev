package ru.mirea.kazmin.toastapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editTextTextPersonName);
    }

    public void onToastButtonClick(View view) {
        String count = String.valueOf(editText.length());
        Toast toast = Toast.makeText(getApplicationContext(),
                String.format("Студент №6 Группа БСБО-02-20 Количество символов - %s", count),
                Toast.LENGTH_SHORT);
        toast.show();

    }
}