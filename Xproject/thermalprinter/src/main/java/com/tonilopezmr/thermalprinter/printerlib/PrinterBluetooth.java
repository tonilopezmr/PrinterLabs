package com.tonilopezmr.thermalprinter.printerlib;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Handler;


import com.tonilopezmr.bluetoothprinter.BluetoothService;

public class PrinterBluetooth implements IPrinter {
  private BluetoothService bluetoothService;

  @Override

  //TODO Create the message handler in PrinterBluetooth
  public void connect(Context context, Handler messageHandler) throws PrinterException{
    bluetoothService = new BluetoothService(context, messageHandler);
    BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();
    //TODO Bluetooth Selection Activity?
    //We assume the printer was the most recent connection
    BluetoothDevice printer = bAdapter.getBondedDevices().iterator().next();
    if(printer != null) {
      System.out.println("Conexion establecida");
      bluetoothService.connect(printer);
      //initialize();
      System.out.println(bluetoothService.getState() == BluetoothService.STATE_CONNECTED);
    }
    else {
      throw new PrinterException("NO PAIRED DEVICES FOUND");
    }
  }

  @Override
  public void disconnect() {

  }

  @Override
  public void write(String line) throws PrinterException {
    write(line.getBytes());
  }

  @Override
  public void write(byte[] data) throws PrinterException {
    if(bluetoothService.getState() != BluetoothService.STATE_CONNECTED)
      throw new PrinterException("NO DEVICE CONNECTED");
    bluetoothService.write(data);
  }

  @Override
  public void setAlignment(PrinterCommands.Align alignment) throws PrinterException {
    write(alignment.value);
  }

  @Override
  public void setFont(PrinterCommands.Font font) throws PrinterException {
    write(font.value);
  }

  @Override
  public void initialize() throws PrinterException{
    System.out.println(bluetoothService.getState() == BluetoothService.STATE_CONNECTED);
    write(PrinterCommands.INITIALIZE);
  }
}
