package com.tonilopezmr.thermalprinter.printerlib;

public class PrinterCommands {
  public static final byte ESC = 0x1B;
  public static final byte[] INITIALIZE = {ESC, '@'};

  public static final byte ALIGN = (byte)'a';

  public static final byte[] ALIGNMENT_LEFT = {ESC, ALIGN, 0x00};
  public static final byte[] ALIGNMENT_CENTER = {ESC, ALIGN, 0x01};
  public static final byte[] ALIGNMENT_RIGHT = {ESC, ALIGN, 0x02};

  
}
