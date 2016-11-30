package com.tonilopezmr.thermalprinter.printerlib;

public class PrinterCommands {
  public static final byte ESC = 0x1B;
  public static final byte GS = 0x1D;

  public static final byte[] INITIALIZE = {ESC, '@'};

  private static final byte ALIGN = (byte) 'a';
  private static final byte EXCLM = (byte) '!';

  public enum Align {
    ALIGNMENT_LEFT(new byte[]{ESC, ALIGN, 0x00}),
    ALIGNMENT_CENTER(new byte[]{ESC, ALIGN, 0x01}),
    ALIGNMENT_RIGHT(new byte[]{ESC, ALIGN, 0x02});

    byte[] value;

    Align(byte[] value) {
      this.value = value;
    }

  }

  public enum Font {
    FONT_DEFAULT(new byte[]{GS, EXCLM, 0x00}),
    FONT_STYLE_B(new byte[]{GS, EXCLM, 0x01}),
    FONT_STYLE_C(new byte[]{GS, EXCLM, 0x11});
    /*FONT_DEFAULT(new byte[]{ESC, EXCLM, 0x00}),
    FONT_TITLTE(new byte[]{ESC, EXCLM, 0x11}})*/;

    byte[] value;

    Font(byte[] value) {
      this.value = value;
    }
  }
}
