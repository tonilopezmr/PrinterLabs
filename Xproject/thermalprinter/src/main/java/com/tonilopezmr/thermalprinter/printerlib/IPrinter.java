package com.tonilopezmr.thermalprinter.printerlib;

import android.content.Context;
import android.os.Handler;

import com.tonilopezmr.bluetoothprinter.commands.PrinterCommand;

public interface IPrinter {
  void connect(Context context, Handler messageHandler) throws PrinterException;
  void disconnect();
  void write(String line) throws PrinterException;
  void write(byte[] data) throws PrinterException;
  void setAlignment(PrinterCommands.Align alignment) throws PrinterException;
  void setFont(PrinterCommands.Font font) throws PrinterException;
  void feedPaper(PrinterCommands.FeedPaper feed) throws PrinterException;
  void initialize() throws PrinterException;
}
