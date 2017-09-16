package example.com.android.asynctaskloaderr;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Preferencex extends AppCompatActivity {

    SeekBar seekBar;
    TextView textView;
    Button button;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencex);
        seekBar=(SeekBar)findViewById(R.id.maxEarthquake);
        textView=(TextView)findViewById(R.id.tvMax);
        button=(Button)findViewById(R.id.submit);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(""+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 pref=getSharedPreferences("pref file", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor;
                editor=pref.edit();
                editor.putInt("minMag",Integer.parseInt(textView.getText().toString()));
                editor.commit();
            }
        });


    }
}
