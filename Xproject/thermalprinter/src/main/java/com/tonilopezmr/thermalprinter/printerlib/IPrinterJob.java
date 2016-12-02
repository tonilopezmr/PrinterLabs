package com.tonilopezmr.thermalprinter.printerlib;

public interface IPrinterJob {


  /**
   * Method that initializes the printer to start a Job Session
   */
  public void initializePrinter() throws PrinterJobException;
  /**
   * Method that prints the arg line using the settings used in the printer
   * @param line
   */
  public void printLine(String line) throws PrinterJobException;

  /**
   * Method that prints a separator.
   * Default: ----------------
   */

  public void printSeparator() throws PrinterJobException;

  public void setSeparator(String separator);


  /** Method that sets the number of newLines that goes after a separator
   * Default: 2 new lines
   * @param spacing
   */
  public void setSeparatorSpacing(int spacing);
}
