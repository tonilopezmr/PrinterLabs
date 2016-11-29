package com.tonilopezmr.thermalprinter.printerlib;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Handler;

import com.tonilopezmr.bluetoothprinter.BluetoothService;

public class PrinterBluetooth implements IPrinter {
    private BluetoothService bluetoothService;

    @Override
    public void connect(Context context, Handler messageHandler) throws PrinterException{
        bluetoothService = new BluetoothService(context, messageHandler);
        BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();
        //TODO Bluetooth Selection Activity?
        //Selects the most recent paired device -> We assume it was the most recent connection
        BluetoothDevice printer = bAdapter.getBondedDevices().iterator().next();
        if(printer != null) {
          bluetoothService.connect(printer);
        }
        else {
          throw new PrinterException("NO PAIRED DEVICES FOUND");
        }
    }

    @Override
    public void disconnect() {

    }

    @Override
    public void write(String linea) throws PrinterException {
        if(bluetoothService.getState() != BluetoothService.STATE_CONNECTED)
            throw new PrinterException()
    }

    @Override
    public void initialize() {

    }
}
