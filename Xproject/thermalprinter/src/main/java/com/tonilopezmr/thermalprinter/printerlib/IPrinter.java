package com.tonilopezmr.thermalprinter.printerlib;

import android.content.Context;
import android.os.Handler;

public interface IPrinter {
    public void connect(Context context, Handler messageHandler) throws PrinterException;
    public void disconnect();
    public void write(String linea) throws PrinterException;
    void initialize();
}
