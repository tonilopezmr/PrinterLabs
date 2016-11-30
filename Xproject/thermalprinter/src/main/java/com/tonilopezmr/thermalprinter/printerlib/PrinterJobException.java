package com.tonilopezmr.thermalprinter.printerlib;

/**
 * Created by adriano on 30/11/16.
 */

public class PrinterJobException extends Exception {
  public PrinterJobException(String message) {
    super(message);
  }
  public PrinterJobException(Exception e) {
    super(e);
  }
}
