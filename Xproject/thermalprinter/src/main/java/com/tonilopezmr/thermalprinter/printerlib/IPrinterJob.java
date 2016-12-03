package com.tonilopezmr.thermalprinter.printerlib;

import java.util.List;

public interface IPrinterJob {


  /**
   * Method that initializes the printer to start a Job Session
   */
  IPrinterJob initializePrinter() throws PrinterJobException;
  /**
   * Method that prints the arg line using the settings passed for the builder and resets the settings
   * By default it prints a plain line
   * @param line
   */
  void printLine(String line) throws PrinterJobException;

  void printAllLines(List<String> lines) throws PrinterJobException;

  /**
   * Method that prints a separator.
   * Default: ----------------
   */
  IPrinterJob printSeparator() throws PrinterJobException;

  IPrinterJob setSeparator(String separator);


  /** Method that sets the number of newLines that goes after a separator
   * Default: 2 new lines
   * @param spacing
   */
  IPrinterJob setSeparatorSpacing(int spacing);

  IPrinterJob setAlignment(PrinterCommands.Align align) throws PrinterJobException;

  IPrinterJob setFont(PrinterCommands.Font font) throws PrinterJobException;

  IPrinterJob feedPaper(PrinterCommands.FeedPaper feed) throws PrinterJobException;

  IPrinterJob config();
}
