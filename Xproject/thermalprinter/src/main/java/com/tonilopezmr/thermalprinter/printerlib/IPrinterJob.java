package com.tonilopezmr.thermalprinter.printerlib;

public interface IPrinterJob {


    /**
     * Method that initializes the printer to start a Job Session
     */
    public void initializePrinter();
    /**
     * Method that prints the arg line using the settings used in the printer
     * @param line
     */
    public void printLine(String line);

    /**
     * Method that prints a line with underscores.
     * Example: ----------------
     */
    public void printUnderLine();

}
