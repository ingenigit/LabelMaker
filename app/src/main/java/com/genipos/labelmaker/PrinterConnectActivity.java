package com.genipos.labelmaker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class PrinterConnectActivity extends AppCompatActivity {
    private static final int BLUETOOTH_CODE = 101;
    private static final int BLUETOOTH_SCAN = 105;
    Context mContext;
    AppEnv gAppEnv;
    private ListView listView;
    private ArrayList<String> mDeviceList = new ArrayList<String>();
    private BluetoothAdapter mBluetoothAdapter;
    ArrayList<BluetoothDevice> devices;
    private BluetoothDevice deviceToPrint;

    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_printer);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, BLUETOOTH_CODE);
        }

        mContext = this;
        gAppEnv = (AppEnv) getApplicationContext();

        listView = (ListView) findViewById(R.id.btPairedListView);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	        /*mBluetoothAdapter.startDiscovery();

	        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
	        registerReceiver(mReceiver, filter);
	        */

        getPairedDevices();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (ActivityCompat.checkSelfPermission(PrinterConnectActivity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED ) {
                    return;
                }
                deviceToPrint = devices.get(position);
                final String macaddr = deviceToPrint.getAddress();
                final String devname = deviceToPrint.getName();


                Log.i("BT Printer", "Selected Device : " + devname);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
                alertDialogBuilder.setTitle(Html.fromHtml("<font color='#FF7F27'>Would you like to use " + devname + " as the Label Printer?</font>"));

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        if (ActivityCompat.checkSelfPermission(PrinterConnectActivity.this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED ) {
                                            ActivityCompat.requestPermissions(PrinterConnectActivity.this,
                                                    new String[]{Manifest.permission.BLUETOOTH_SCAN},
                                                    BLUETOOTH_SCAN);
                                        }
                                        boolean connres = gAppEnv.getTscPrinterManager().setPrinter(devname, macaddr);
                                        if (connres == true) {
                                            Toast.makeText(getApplicationContext(), "Printer is Online", Toast.LENGTH_SHORT).show();
                                            //startActivity(new Intent(PrinterConnectActivity.this, MainActivity.class));
                                        } else
                                            Toast.makeText(getApplicationContext(), "Printer connection failed", Toast.LENGTH_SHORT).show();


                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                //testPrint(macaddr);
            }
        });

    }

    private void getPairedDevices() {

        if (devices == null)
            devices = new ArrayList<BluetoothDevice>();
        else
            devices.clear();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice curDevice : pairedDevices) {
                devices.add(curDevice);

                mDeviceList.add(curDevice.getName() + "\n" + curDevice.getAddress());
            }
            //Log.i(TAG, "Paired Number of Devices : " + pairedDevices.size());

            listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDeviceList));
            //showPairedList();
        }
    }
}