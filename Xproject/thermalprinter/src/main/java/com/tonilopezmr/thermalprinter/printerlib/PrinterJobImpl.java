package com.tonilopezmr.thermalprinter.printerlib;

class PrinterJobImpl implements IPrinterJob {
  private IPrinter printer;
  private String separator = "--------------------------";
  private String separator_spacing = "\n\n";

  public PrinterJobImpl(IPrinter printer) {
    this.printer = printer;
  }

  @Override
  public void initializePrinter() throws PrinterJobException {
    try {
      printer.initialize();
    } catch (PrinterException e) {
      throw new PrinterJobException(e.getMessage());
    }
  }

  @Override
  public void printLine(String line) throws PrinterJobException {
    try {
      printer.write(line);
    } catch (PrinterException e) {
      throw new PrinterJobException(e.getMessage());
    }
  }

  @Override
  public void printSeparator() throws PrinterJobException{
    try {
      printer.write(separator + separator_spacing);
    } catch (PrinterException e) {
      throw new PrinterJobException(e.getMessage());
    }
  }

  @Override
  public void setSeparator(String separator) {
    this.separator = separator;
  }

  @Override
  public void setSeparatorSpacing(int spacing) {
    separator_spacing = "";
    for(int i = 0; i < spacing; i++) {
      separator_spacing += '\n';
    }
  }
}
