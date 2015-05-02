//4-30-15 JChoy A blank android app created in AndroidStudio on windows
//
//5-1-2015 JChoy - mTextView as public member accessible from context.

package com.ok88.andydev.jcblankapp_windev;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.bluetooth.*;
import android.os.*;
import android.widget.*;
import android.content.Intent;
import android.content.IntentFilter;
import java.sql.Timestamp;
import java.util.Date;
import android.content.Context;
import android.content.BroadcastReceiver;

public class MainActivity extends ActionBarActivity {
    private int prevState;
    public TextView actTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actTextView = (TextView)findViewById(R.id.hello_tv); 

        prevState=-1;
        for (int i=0; i<1; i++) {
            checkBTconnection();
            //java.util.Date date= new java.util.Date();
            //String x= " "+new Timestamp(date.getTime()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {}
        }
        
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED);
        
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "bt-evt", Toast.LENGTH_LONG).show();
                ((MainActivity)context).actTextView.setText("evt");
                Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(200);
            }
        };
        registerReceiver(receiver, filter);
     
        actTextView.setText("Started");
    }

    private void checkBTconnection(){
        int state;
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        String[] msg= {"NOT connected","connecting","connectED","DISconnecting"};
        if (!mBluetoothAdapter.isEnabled()) return;
        state=mBluetoothAdapter.getProfileConnectionState( BluetoothProfile.A2DP);
        //if (prevState != state){
        //    prevState = state;
        //    getSupportActionBar().setTitle( msg[state] );  
        //}
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
