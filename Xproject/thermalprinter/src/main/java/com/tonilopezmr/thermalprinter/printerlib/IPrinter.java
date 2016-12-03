package com.tonilopezmr.thermalprinter.printerlib;

import android.content.Context;
import android.os.Handler;

import com.tonilopezmr.bluetoothprinter.commands.PrinterCommand;

public interface IPrinter {
  public void connect(Context context, Handler messageHandler) throws PrinterException;
  public void disconnect();
  public void write(String line) throws PrinterException;
  public void write(byte[] data) throws PrinterException;
  public void setAlignment(PrinterCommands.Align alignment) throws PrinterException;
  public void setFont(PrinterCommands.Font font) throws PrinterException;
  public void feedPaper(PrinterCommands.FeedPaper feed) throws PrinterException;
  void initialize() throws PrinterException;
}
