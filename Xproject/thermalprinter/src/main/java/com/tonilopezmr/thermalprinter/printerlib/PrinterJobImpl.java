package com.tonilopezmr.thermalprinter.printerlib;

class PrinterJobImpl implements IPrinterJob {


    private IPrinter printer;

    public PrinterJobImpl(IPrinter printer) {
        this.printer = printer;
    }

    @Override
    public void initializePrinter() { }

    @Override
    public void printLine(String line) {

    }

    @Override
    public void printUnderLine() {

    }
}
