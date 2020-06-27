package com.project.queuesystem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mazenrashed.printooth.Printooth;
import com.mazenrashed.printooth.data.printable.Printable;
import com.mazenrashed.printooth.data.printable.RawPrintable;
import com.mazenrashed.printooth.data.printable.TextPrintable;
import com.mazenrashed.printooth.data.printer.DefaultPrinter;
import com.mazenrashed.printooth.ui.ScanningActivity;
import com.mazenrashed.printooth.utilities.Printing;
import com.mazenrashed.printooth.utilities.PrintingCallback;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView queue_no;
    TextView textDate;
    ImageButton btn_next;
    ImageButton btn_exit;
    int counter;
//    ImageButton btn_print;
//    Button btn_pair_unpair;
//    Printing printing;



    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_next :
                    plusCounter();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Date currentTime = Calendar.getInstance().getTime();
        String formatedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);

        Log.d("myLOG", currentTime.toString());
        Log.d("myLOG", formatedDate);

        textDate = (TextView) findViewById(R.id.textDate);
        textDate.setText(formatedDate);


        initView();
    }

    private void initView() {
        queue_no = (TextView) findViewById(R.id.queue_no);
        btn_next = (ImageButton) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(clickListener);
        btn_exit = (ImageButton) findViewById(R.id.btn_exit);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogConfirm();

            }
        });


//        btn_print = (ImageButton) findViewById(R.id.btn_print);
//        btn_pair_unpair = (Button) findViewById(R.id.btnPairUnpair);

//        if(printing != null) {
//            printing.setPrintingCallback(this);
//        }
//        //Event
//        btn_pair_unpair.setOnClickListener(this);
//
//        btn_print.setOnClickListener(view -> {
//            if(!Printooth.INSTANCE.hasPairedPrinter())
//                startActivityForResult(new Intent(MainActivity.this,ScanningActivity.class),ScanningActivity.SCANNING_FOR_PRINTER);
//            else
//                printText();
//        });
    }
    private void plusCounter() {
        counter++;
        queue_no.setText(counter + "");
    }

    private void dialogConfirm(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Do you want to exit this app?");
        builder.setCancelable(true);
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                moveTaskToBack(true);
            }
        });
        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }

            public void OnClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


//    private void changePairAndUnpair() {
//        if (Printooth.INSTANCE.hasPairedPrinter())
//            btn_pair_unpair.setText(new StringBuilder("Unpair ")
//            .append(Printooth.INSTANCE.getPairedPrinter().getName()).toString());
//        else btn_pair_unpair.setText("Pair with Printer");
//    }
//
//    private void printText(){
//        ArrayList<Printable> printables = new ArrayList<>();
//        printables.add(new RawPrintable.Builder(new byte[]{27,100,4}).build());
//
//        //Add Text
//        printables.add(new TextPrintable.Builder()
//        .setText("Antrian Nomor : ")
//        .setCharacterCode(DefaultPrinter.Companion.getCHARCODE_PC1252())
//        .setNewLinesAfter(1)
//        .build());
//    }

//    @Override
//    public void connectingWithPrinter() {
//        Toast.makeText(this,"Connecting to printer", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void connectingFailed(String s) {
//        Toast.makeText(this,"Failed"+s, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onError(String s) {
//        Toast.makeText(this,"Error"+s, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onMessage(String s) {
//        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void printingQueueSentSuccessfully() {
//        Toast.makeText(this, "Print Success", Toast.LENGTH_SHORT).show();
//    }

//    //cTRL+O
//    @Override
//    protected void onActivityResult (int requestcode, int resultcode, @Nullable Intent data) {
//        super.onActivityResult(requestcode, requestcode, data);
//        if (requestcode == ScanningActivity.SCANNING_FOR_PRINTER &&
//        resultcode == Activity.RESULT_OK)
//            initPrinting();
//        changePairAndUnpair();
//    }
//
//    private void initPrinting() {
//        if(!Printooth.INSTANCE.hasPairedPrinter())
//            printing = Printooth.INSTANCE.printer();
//        if(printing != null)
//            printing.setPrintingCallback(this);
//    }


//    private void onClick(View view) {
//        if (Printooth.INSTANCE.hasPairedPrinter())
//            Printooth.INSTANCE.removeCurrentPrinter();
//        else {
//            startActivityForResult(new Intent(MainActivity.this, ScanningActivity.class, ScanningActivity.SCANNING_FOR_PRINTER));
//            changePairAndUnpair();
//        }
//    }
}