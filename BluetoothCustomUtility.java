package com.charpixel.baseandroidproject.common;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by VISHALGUPTAHMH@gmail.com on 20/03/18.
 */



/*
        ADD PERMISSION TO MANIFEST

        <uses-permission android:name="android.permission.BLUETOOTH" />
        <uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />
*/

public class BluetoothCustomUtility {

    String TAG = this.getClass().getSimpleName();
    BluetoothAdapter bluetoothAdapter;
    FragmentActivity fragmentActivity;
    IntentFilter filter;
    ListenerOnConnected listenerOnConnected;
    ListenerDialog listenerDialog;

    public static BluetoothCustomUtility with(FragmentActivity fragmentActivity) {

        BluetoothCustomUtility bluetoothCustomUtility = new BluetoothCustomUtility();
        bluetoothCustomUtility.fragmentActivity = fragmentActivity;
        bluetoothCustomUtility.bluetoothAdapter= BluetoothAdapter.getDefaultAdapter();
        return bluetoothCustomUtility;

    }




    //on Bluetooth connected with Device

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            Log.d(TAG, "BroadcastReceiver : " + action);
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                //Device found
            } else if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
                //Device is now connected
                listenerOnConnected.onConnectedWithDevice(device);
                Log.d(TAG, "BroadcastReceiver onReceive: " + device.getName() + " address " + device.getAddress() + " type:" + device.getType());


            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                //Done searching
            } else if (BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED.equals(action)) {
                //Device is about to disconnect
            } else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
                //Device has disconnected
            }
        }
    };



    public BluetoothCustomUtility getOnBluetoothConnectedWithDevice(ListenerOnConnected listener) {
        setListenerOnConnected(listener);
        filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        register();
        return this;
    }




    //getpaired Device list




     void bluthoothDiaglog(String[] entries, List<BluetoothDevice> pairedDevices) {
        AlertDialog.Builder builder = new AlertDialog.Builder(fragmentActivity);
        builder.setTitle("Choose Blutooth Device")
                .setCancelable(true)
                .setItems(entries, (DialogInterface.OnClickListener) (dialog, which) -> {
                    BluetoothDevice bluetoothDevice = pairedDevices.get(which);
                    Log.d(TAG, "bluthoothDiaglog: " + bluetoothDevice.getName() + " is Selected.");
                    listenerDialog.onItemClick(bluetoothDevice);
                }).show();
    }


    public void getBluetoothPairedDevicesDialog(ListenerDialog listener) {
        setListenerDialog(listener);


        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {


            Log.d(TAG, "bluetoothCon: Bluetooth is not enabled.");

        } else {
            final List<String> entries = new ArrayList<>();
            final List<String> entryValues = new ArrayList<>();
            List<BluetoothDevice> pairedDevices = new ArrayList<>(bluetoothAdapter.getBondedDevices());
            if (pairedDevices.size() > 0) {

                String[] listDeviceNames = new String[pairedDevices.size()];
                int count = 0;
                for (BluetoothDevice device : pairedDevices) {
                    Log.d(TAG, "bluetooth: DeviceName " + device.getName() + "  Address : " + device.getAddress());
                    listDeviceNames[count++] = device.getName();
                    entries.add(device.getName());
                    entryValues.add(device.getAddress());
                }

                bluthoothDiaglog(listDeviceNames, pairedDevices);
            } else {

                Log.d(TAG, "bluetoothPairedDevices: ");

            }

        }


    }

     public interface ListenerOnConnected{
        public void onConnectedWithDevice(BluetoothDevice bluetoothDevice);
    }

    public interface ListenerDialog{
        public void onItemClick(BluetoothDevice bluetoothDevice);
    }

    public void setListenerOnConnected(ListenerOnConnected listenerOnConnected) {
        this.listenerOnConnected = listenerOnConnected;
    }

    public void setListenerDialog(ListenerDialog listenerDialog) {
        this.listenerDialog = listenerDialog;
    }

    void register() {
        fragmentActivity.registerReceiver(mReceiver, filter);
    }

    public void unRegister() {
        fragmentActivity.unregisterReceiver(mReceiver);
    }


//    public PairedDevices getPariedDeviceList(){
//
//       return new PairedDevices();
//    }
//
//    public  class PairedDevices{
//
//        public   void getPairedDevices(){}
//    }
}
