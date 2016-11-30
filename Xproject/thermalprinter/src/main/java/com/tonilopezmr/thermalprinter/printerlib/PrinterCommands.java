package com.tonilopezmr.thermalprinter.printerlib;

public class PrinterCommands {
  public static final byte ESC = 0x1B;
  public static final byte[] INITIALIZE = {ESC, '@'};

  public static final byte ALIGN = (byte)'a';

  enum Align {
    ALIGNMENT_LEFT(new byte[]{ESC, ALIGN, 0x00}),
    ALIGNMENT_CENTER(new byte[]{ESC, ALIGN, 0x01}),
    ALIGNMENT_RIGHT(new byte[]{ESC, ALIGN, 0x02});

    byte[] value;

    Align(byte[] value) {
      this.value = value;
    }
  }

}
