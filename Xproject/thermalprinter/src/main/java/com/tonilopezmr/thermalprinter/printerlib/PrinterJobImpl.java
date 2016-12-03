package com.tonilopezmr.thermalprinter.printerlib;

import com.tonilopezmr.bluetoothprinter.commands.PrinterCommand;

import java.util.List;

public class PrinterJobImpl implements IPrinterJob {
  private IPrinter printer;
  private String separator = "--------------------------";
  private String separator_spacing = "" + PrinterCommands.NEW_LINE + PrinterCommands.NEW_LINE;
  private PrinterCommands.Font font = PrinterCommands.Font.FONT_DEFAULT;
  private PrinterCommands.Align alignment = PrinterCommands.Align.ALIGNMENT_LEFT;


  //Implementation following the Builder pattern -> The product is each print
  public PrinterJobImpl(IPrinter printer) {
    this.printer = printer;
  }

  @Override
  public IPrinterJob initializePrinter() throws PrinterJobException {
    try {
      printer.initialize();
    } catch (PrinterException e) {
      throw new PrinterJobException(e.getMessage());
    }
    return this;
  }

  @Override
  public void printLine(String line) throws PrinterJobException {
    try {
      printer.write(line + PrinterCommands.NEW_LINE);
    } catch (PrinterException e) {
      throw new PrinterJobException(e.getMessage());
    }
    //After a print we reset the settings
    initializePrinter();
  }

  @Override
  public void printAllLines(List<String> lines) throws PrinterJobException {
    try {
      for (String line : lines) {
        printer.write(line + PrinterCommands.NEW_LINE);
      }
    } catch (PrinterException e) {
      throw new PrinterJobException(e.getMessage());
    }
    initializePrinter();
  }

  @Override
  public IPrinterJob printSeparator() throws PrinterJobException{
    try {
      printer.write(separator + separator_spacing);
    } catch (PrinterException e) {
      throw new PrinterJobException(e.getMessage());
    }
    return this;
  }

  @Override
  public IPrinterJob setSeparator(String separator) {
    this.separator = separator;
    return this;
  }

  @Override
  public IPrinterJob setSeparatorSpacing(int spacing) {
    separator_spacing = "";
    for(int i = 0; i < spacing; i++) {
      separator_spacing += + PrinterCommands.NEW_LINE;
    }
    return this;
  }

  @Override
  public IPrinterJob setAlignment(PrinterCommands.Align align) throws PrinterJobException {
    try {
      printer.setAlignment(align);
    } catch (PrinterException e) {
      throw new PrinterJobException(e.getMessage());
    }
    return this;
  }

  @Override
  public IPrinterJob setFont(PrinterCommands.Font font) throws PrinterJobException {
    try {
      printer.setFont(font);
    } catch (PrinterException e) {
      throw new PrinterJobException(e.getMessage());
    }
    return this;
  }

  @Override
  public IPrinterJob feedPaper(PrinterCommands.FeedPaper feed) throws PrinterJobException {
    try {
      printer.feedPaper(feed);
    } catch (PrinterException e) {
      throw new PrinterJobException(e.getMessage());
    }
    return this;
  }

  @Override
  public IPrinterJob config() {
    return this;
  }
}
