package com.tonilopezmr.thermalprinter.printerlib;


public class PrinterJobException extends Exception {
  public PrinterJobException(String message) {
    super(message);
  }
  public PrinterJobException(Exception e) {
    super(e);
  }
}
