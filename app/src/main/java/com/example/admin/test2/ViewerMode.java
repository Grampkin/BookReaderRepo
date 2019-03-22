package com.example.admin.test2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.example.admin.test2.R.drawable.ic_brightness_high_black_24dp;

public class ViewerMode extends AppCompatActivity {

    public TextView textView;


    public SeekBar seekBar;
    public Toolbar toolbar;
    private StringBuilder text = new StringBuilder();
    int doubleTap = 0;
    public FrameLayout fr;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPrefs();
        fr = findViewById(R.id.my_frame_layout);
        setContentView(R.layout.activity_viewer_mode);
        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        toolbar.setVisibility(View.INVISIBLE);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setVisibility(View.INVISIBLE);

        String title = getIntent().getStringExtra("bookName");



//        String fileName = "just";
//        String fileReso = ".txt";
//        String fullName = fileName+fileReso;
//
//        BufferedReader reader = null;
//
//        try {
//            reader = new BufferedReader(
//                    new InputStreamReader(getAssets().open(fullName))
//            );
//
//            String mLine;
//            while ((mLine = reader.readLine())!=null) {
//                text.append(mLine);
//                text.append('\n');
//            }
//        } catch (IOException e) {
//            Toast.makeText(getApplicationContext(),"Error reading file", Toast.LENGTH_LONG).show();
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                }
//                catch (IOException e) {
//
//                }
//            }
//
//            textView = findViewById(R.id.texting);
//            textView.setText(text);
//
//        }
        getIncomingIntent();
        getSupportActionBar().setTitle(title);
        show();
    }

    @Override
    public void onStart() {
        super.onStart();
        getPrefs();
    }

    boolean CheckboxPreference;
    boolean SwitchPreference;
    String ListPreference;
    String editTextPreference;
    String ringtonePreference;
    String secondEditTextPreference;
    String customPref;


    private void getIncomingIntent() {
        if(getIntent().hasExtra("bookName")) {
            String bookName = getIntent().getStringExtra("bookName");

            setBook(bookName);

        }
    }

    private void setBook(String bookName) {

        String fileReso = ".txt";
        String fullName = bookName + fileReso;
        String path = "books/"+fullName;

        Log.d("TAG",path);

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open(path))
            );

            String mLine;
            while ((mLine = reader.readLine()) != null) {
                text.append(mLine);
                text.append('\n');
            }
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Error reading file", Toast.LENGTH_LONG).show();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {

                }
            }

            textView = findViewById(R.id.texting);
            textView.setText(text);

        }
    }


    private void getPrefs() {
        // Get the xml/preferences.xml preferences
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext());
//        CheckboxPreference = prefs.getBoolean("checkboxPref", true);
        ListPreference = prefs.getString("listPref", "1");
        SwitchPreference = prefs.getBoolean("nightModePref",true);
        editTextPreference = prefs.getString("editTextPref",
                "Nothing has been entered");
        ringtonePreference = prefs.getString("ringtonePref",
                "DEFAULT_RINGTONE_URI");
        secondEditTextPreference = prefs.getString("SecondEditTextPref",
                "Nothing has been entered");
        // Get the custom preference
        SharedPreferences mySharedPreferences = getSharedPreferences(
                "myCustomSharedPrefs", ViewerMode.MODE_PRIVATE);
        customPref = mySharedPreferences.getString("myCusomPref", "");
    }

    public void show() {
        textView = findViewById(R.id.texting);
        textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doubleTap++;

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(doubleTap==2){
                                if (seekBar.getVisibility() == View.VISIBLE) {
                                    seekBar.setVisibility(View.INVISIBLE);
                                    toolbar.setVisibility(View.INVISIBLE);


                                } else {
                                    seekBar.setVisibility(View.VISIBLE);
                                    toolbar.setVisibility(View.VISIBLE);
                                }
                            }
                            doubleTap=0;
                        }
                    },250);


                }
            });

    }

//
//    public void setTheme(int colorOfFont) {
//
//        fr = findViewById(R.id.my_frame_layout);
//        textView = findViewById(R.id.texting);
//
//        if(fr.getDrawingCacheBackgroundColor()==Color.WHITE){
//            fr.setBackgroundColor(Color.BLACK);
//            textView.setTextColor(Color.WHITE);
//        }
//        else if (fr.getDrawingCacheBackgroundColor()==Color.BLACK) {
//            fr.setBackgroundColor(Color.WHITE);
//            textView.setTextColor(Color.BLACK);
//        }
//
//    }

    public int getFont() {

        if (textView.getTypeface()==Typeface.SANS_SERIF)
            return 0;
        else if (textView.getTypeface()==Typeface.SERIF)
            return 1;
        else if (textView.getTypeface()==Typeface.MONOSPACE)
            return 2;
        else
            return 3;
    }

    public void setFont(int item) {

        switch (item){
            case 0:
                textView.setTypeface(Typeface.SANS_SERIF);
                break;
            case 1:
                textView.setTypeface(Typeface.SERIF);
                break;
            case 2:
                textView.setTypeface(Typeface.MONOSPACE);
                break;
            case 3:
                textView.setTypeface(Typeface.SANS_SERIF,Typeface.ITALIC);
                break;
            default:
                textView.setTypeface(textView.getTypeface());
        }
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.viewer_mode, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        fr = findViewById(R.id.my_frame_layout);
        textView = findViewById(R.id.texting);

        switch (item.getItemId()){
            case R.id.night_mode:
                fr.setBackgroundColor(Color.BLACK);
                textView.setTextColor(Color.WHITE);
                break;

            case R.id.day_mode:
                fr.setBackgroundColor(Color.WHITE);
                textView.setTextColor(Color.BLACK);
                break;


            case R.id.font_type:
                final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this,R.style.AlertDialogCustom));
                    builder.setTitle("Select text font");
                final String[] fonts = new String[] {
                        "Sans",
                        "Serif",
                        "Monospace",
                        "Cursive"
                };



                builder.setSingleChoiceItems(fonts, getFont(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setFont(which);
                    }
                });


                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
                break;

            case R.id.font_size:


//                final Dialog size_builder = new Dialog(new ContextThemeWrapper(this,R.style.AlertDialogCustom));
//                size_builder.setTitle("Set font size");
//                size_builder.setContentView(R.layout.dialog_font_size);
//                final NumberPicker picker = size_builder.findViewById(R.id.font_size_picker);
//                picker.setMinValue(8);
//                picker.setMaxValue(36);
//                picker.setValue((int)textView.getTextSize());
//                picker.setWrapSelectorWheel(false);
//                picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
//                    @Override
//                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//                        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,picker.getValue());
//                    }
//                });
//
//                size_builder.show();
                final NumberPicker picker = new NumberPicker(new ContextThemeWrapper(this,R.style.AlertDialogCustom));
                picker.setMinValue(8);
                picker.setMaxValue(36);
                picker.setValue(18);
                picker.setWrapSelectorWheel(false);
                picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,picker.getValue());
                    }
                });

                final FrameLayout layout = new FrameLayout(new ContextThemeWrapper(this,R.style.AlertDialogCustom));
                layout.addView(picker, new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.WRAP_CONTENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER));


                new AlertDialog.Builder(new ContextThemeWrapper(this,R.style.AlertDialogCustom))
                        .setTitle("Select text size")
                        .setView(layout)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .show();



            break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }


//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode,resultCode,data);
//
//        if(data == null)
//            return;
//        int fontColor = data.getIntExtra("fontColor",Color.BLACK);
//        int viewColor = data.getIntExtra("viewColor",Color.WHITE);
//        textView.setTextColor(fontColor);
//        fr.setBackgroundColor(viewColor);
//        int fontType = data.getIntExtra("fontType",1);
//        switch (fontType) {
//            case 1:
//                textView.setTypeface(Typeface.SANS_SERIF);
//                break;
//            case 2:
//                textView.setTypeface(Typeface.SERIF);
//                break;
//            case 3:
//                textView.setTypeface(Typeface.MONOSPACE);
//                break;
//            case 4:
//                textView.setTypeface(Typeface.SANS_SERIF,Typeface.ITALIC);
//                break;
//            default:
//                textView.setTypeface(Typeface.SANS_SERIF);
//        }
//
//
//    }



//    @Override
//    public void onBackPressed() {
//        Intent i = new Intent(this,MainActivity.class);
//        startActivity(i);
//    }


}
