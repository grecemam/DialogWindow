package com.example.dilogwindow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private AlertDialog dialog;
    EditText dateTxt, timeTxt;
    ImageButton dateBtn, timeBtn;
    Button applyBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateTxt = findViewById(R.id.date_txt);
        timeTxt = findViewById(R.id.time_txt);
        dateBtn = findViewById(R.id.date_pick_btn);
        timeBtn = findViewById(R.id.time_pick_btn);
        applyBtn = findViewById(R.id.apply_btn);

        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year = 2018;
                int month = 9;
                int day = 23;

                DatePickerDialog.OnDateSetListener datePick = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        dateTxt.setText(""+i2+"-"+(i1+1)+"-"+i);
                    }
                };

                DatePickerDialog datePickerDialog  = new DatePickerDialog(MainActivity.this, R.style.DatePickerDialogTheme, datePick, year, month, day);
                //datePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                datePickerDialog.show();
            }
        });


        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = 12;
                int minute = 45;
                boolean is24Format = true;

                TimePickerDialog.OnTimeSetListener timePick = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        timeTxt.setText(i+":"+i1);
                    }
                };

                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        MainActivity.this,
                        R.style.CustomTimePickerDialog, // Используйте ваш созданный стиль
                        timePick,
                        hour,
                        minute,
                        is24Format
                );


// Установка прозрачного фона
                timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                timePickerDialog.show();
            }
        });


        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                // Получите ссылку на надуваемый макет
                View dialogView = getLayoutInflater().inflate(R.layout.custom_dialog, null);

                // Найдите кнопки внутри вашего макета
                Button positiveButton = dialogView.findViewById(R.id.dialog_positive_button);
                Button negativeButton = dialogView.findViewById(R.id.dialog_negative_button);

                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Действие при нажатии на ПОДТВЕРДИТЬ
                        dialog.dismiss(); // Закрыть диалог
                        Toast.makeText(MainActivity.this, "ВЫ ЗАПИСАНЫ!", Toast.LENGTH_SHORT).show();
                    }
                });

                negativeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Действие при нажатии на ОТМЕНИТЬ
                        dialog.dismiss(); // Закрыть диалог
                    }
                });

                builder.setView(dialogView);

                // Создайте AlertDialog из AlertDialog.Builder и присвойте его переменной dialog
                dialog = builder.create();
                dialog.show();
            }
        });
    }
}