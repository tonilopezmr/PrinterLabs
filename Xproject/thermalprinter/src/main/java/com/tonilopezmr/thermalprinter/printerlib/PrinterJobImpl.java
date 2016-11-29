package com.tonilopezmr.thermalprinter.printerlib;

class PrinterJobImpl implements IPrinterJob {
    private static final String ESC = "ESC";
    private static final String INITIALIZE = ESC + " @";

    private IPrinter printer;

    public PrinterJobImpl(IPrinter printer) {
        this.printer = printer;
    }

    @Override
    public void initializePrinter() {
        printer.write(INITIALIZE);
    }

    @Override
    public void printLine(String line) {

    }

    @Override
    public void printUnderLine() {

    }
}
