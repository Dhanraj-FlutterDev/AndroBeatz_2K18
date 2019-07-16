package com.example.texttospeech;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener
{

    int res;
    String txt;
    TextToSpeech textToSpeech;
    EditText inputText;




    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = (EditText)findViewById(R.id.inputText);     //Mapping id from GUI to java code

        textToSpeech = new TextToSpeech(this,this);      //Intiallization of texttospeech constructor

    }




    @Override
    public void onInit(int status)                  //method of OnInitListener
    {
        if(status == TextToSpeech.SUCCESS)
        {
            res = textToSpeech.setLanguage(Locale.US);   //set language for text to speech
        }
    }






    @Override
    public void onClick(View v)                      //method for OnClickListener
    {
        if(res == TextToSpeech.LANG_MISSING_DATA || res == TextToSpeech.LANG_NOT_SUPPORTED)       //checking for errors
        {
            Toast.makeText(this,"Language Not Supported", Toast.LENGTH_SHORT).show();            //showing toast message
        }



        else
        {
            txt = inputText.getText().toString();                //taking inputed string



            Toast.makeText(this, "SPEAKING", Toast.LENGTH_SHORT).show();     //showing toast message

            textToSpeech.setPitch(1);                    //setting pitch for speech

            textToSpeech.speak(txt, TextToSpeech.QUEUE_FLUSH, null);    // conversion for text to speexh

        }
    }
}
