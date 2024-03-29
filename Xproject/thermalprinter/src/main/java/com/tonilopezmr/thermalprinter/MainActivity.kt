package com.tonilopezmr.thermalprinter

import android.annotation.SuppressLint
import com.tonilopezmr.thermalprinter.printerlib.PrinterJobImpl
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.tonilopezmr.bluetoothprinter.BluetoothService
import com.tonilopezmr.bluetoothprinter.commands.Command
import com.tonilopezmr.thermalprinter.printerlib.PrintConfig


import com.tonilopezmr.thermalprinter.printerlib.PrinterBluetooth
import com.tonilopezmr.thermalprinter.printerlib.PrinterCommands
import kotlinx.android.synthetic.main.activity_main.*
import zj.com.customize.sdk.Other
import java.io.UnsupportedEncodingException

class MainActivity : AppCompatActivity() {

    private val CHINESE = "GBK"
    private val THAI = "CP874"
    private val KOREAN = "EUC-KR"
    private val BIG5 = "BIG5"

    val MESSAGE_STATE_CHANGE = 1
    val MESSAGE_READ = 2
    val MESSAGE_WRITE = 3
    val MESSAGE_DEVICE_NAME = 4
    val MESSAGE_TOAST = 5
    val MESSAGE_CONNECTION_LOST = 6
    val MESSAGE_UNABLE_CONNECT = 7

    private var bluetoothService: BluetoothService? = null
    private var printer: PrinterBluetooth = PrinterBluetooth();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        connectButton.setOnClickListener { connect() }
        kitchenButton.setOnClickListener { printKitchenTicket() }
        qrButton.setOnClickListener { printQr() }
        disconnected()
    }

    private fun printQr() {

    }

    private fun printother() {
        Command.ESC_ALIGN[2] = 0x02
        val allbuf: Array<ByteArray>
        try {
            allbuf = arrayOf(

                    Command.ESC_Init, Command.ESC_Three, String.format("┏━━┳━━━━━━━┳━━┳━━━━━━━━┓\n").toByteArray(charset("BIG5")), String.format("┃XXXX┃%-14s┃XXXX┃%-16s┃\n", "XXXX", "XXXX").toByteArray(charset("BIG5")), String.format("┣━━╋━━━━━━━╋━━╋━━━━━━━━┫\n").toByteArray(charset("BIG5")), String.format("┃XXXX┃%6d/%-7d┃XXXX┃%-16d┃\n", 1, 222, 55555555).toByteArray(charset("BIG5")), String.format("┣━━┻┳━━━━━━┻━━┻━━━━━━━━┫\n").toByteArray(charset("BIG5")), String.format("┃XXXXXX┃%-34s┃\n", "【XX】XXXX/XXXXXX").toByteArray(charset("BIG5")), String.format("┣━━━╋━━━━━━┳━━┳━━━━━━━━┫\n").toByteArray(charset("BIG5")), String.format("┃XXXXXX┃%-12s┃XXXX┃%-16s┃\n", "XXXX", "XXXX").toByteArray(charset("BIG5")), String.format("┗━━━┻━━━━━━┻━━┻━━━━━━━━┛\n").toByteArray(charset("BIG5")), Command.ESC_ALIGN, "\n".toByteArray(charset("BIG5")))
            val buf = Other.byteArraysToBytes(allbuf)
            sendDataByte(buf)
            sendDataByte(Command.GS_V_m_n)
        } catch (e: UnsupportedEncodingException) {
            // TODO 自动生成的 catch 块
            e.printStackTrace()
        }

    }

    private fun printKitchenTicket() {
        var printerJob: PrinterJobImpl = PrinterJobImpl(printer)

        printerJob.printSeparator()
        printerJob.setAlignment(PrinterCommands.Align.ALIGNMENT_CENTER)
                .setFont(PrinterCommands.Font.FONT_STYLE_C)
                .printLine("Numero 50")
        printerJob.setFont(PrinterCommands.Font.FONT_STYLE_B)
                .printAllLines(mutableListOf<String>(
                    "1x Tortilla de patatas",
                    "1x Sandwitch mixto",
                    "1x Zumo de naranja"
                ))
        printerJob.feedPaper(PrinterCommands.FeedPaper.FEED)


        printerJob.setSeparator("**********************")
                .setSeparatorSpacing(5)
                .printSeparator()
        printerJob.setAlignment(PrinterCommands.Align.ALIGNMENT_CENTER)
                .setFont(PrinterCommands.Font.FONT_STYLE_C)
                .printLine("Numero 51")
        printerJob.printAllLines(mutableListOf<String>(
                        "1x Tortilla de patatas",
                        "1x Sandwitch mixto",
                        "1x Zumo de naranja"
                ))
        printerJob.feedPaper(PrinterCommands.FeedPaper.FEED_END)

        printerJob.printSeparator()
        var titleConfig : PrintConfig = PrintConfig()
        titleConfig.font = PrinterCommands.Font.FONT_STYLE_C
        titleConfig.alignment = PrinterCommands.Align.ALIGNMENT_CENTER
        printerJob.config = titleConfig
        printerJob.printLine("Numero 52")
        var orderConfig : PrintConfig = PrintConfig()
        orderConfig.font = PrinterCommands.Font.FONT_STYLE_B
        printerJob.setConfig(orderConfig).printAllLines(mutableListOf<String>(
                "1x Tortilla de patatas",
                "1x Sandwitch mixto",
                "1x Zumo de naranja"
        ))
        printerJob.feedPaper(PrinterCommands.FeedPaper.FEED_END)



        /*
        printer.setAlignment(PrinterCommands.Align.ALIGNMENT_CENTER)
        printer.write("--------------------------\n\n")
        printer.setFont(PrinterCommands.Font.FONT_STYLE_C)
        printer.write("Numero 50\n\n")
        printer.setAlignment(PrinterCommands.Align.ALIGNMENT_LEFT)
        printer.setFont(PrinterCommands.Font.FONT_STYLE_B)
        printer.write("1x Tortilla de patatas\n")
        printer.write("1x Sandwitch mixto\n")
        printer.write("1x Zumo de naranja\n\n")
        sendDataByte(PrinterCommand.POS_Set_PrtAndFeedPaper(50))
        */
    }

    private fun connect() {
        printer.connect(this, mHandler)

//        val intent = Intent(Intent.ACTION_MAIN, null)
//        intent.addCategory(Intent.CATEGORY_LAUNCHER)
//        val cn = ComponentName("com.android.settings",
//                "com.android.settings.bluetooth.BluetoothSettings")
//        intent.component = cn
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//        startActivity(intent)
    }

    /*
     *SendDataByte
     */
    private fun sendDataByte(data: ByteArray) {

        if (bluetoothService?.state != BluetoothService.STATE_CONNECTED) {
            Toast.makeText(this, "No conectado", Toast.LENGTH_SHORT).show()
            return
        }
        bluetoothService?.write(data)
    }

    private fun connected(): Unit {
        kitchenButton.isEnabled = true
        qrButton.isEnabled = true
    }

    fun disconnected(): Unit {
        kitchenButton.isEnabled = false
        qrButton.isEnabled = false
    }

    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MESSAGE_STATE_CHANGE -> {
                    when (msg.arg1) {
                        BluetoothService.STATE_CONNECTED -> {
                            showMessage("Conectado")
                            connected()
                        }
                        BluetoothService.STATE_CONNECTING -> {
                            showMessage("Connectado")
                        }
                        BluetoothService.STATE_LISTEN, BluetoothService.STATE_NONE -> {
                        }
                    }
                }
                MESSAGE_WRITE -> {
                }
                MESSAGE_READ -> {
                }
                MESSAGE_DEVICE_NAME -> {
                    // save the connected device's name
                    
                }
                MESSAGE_TOAST -> {}
                MESSAGE_CONNECTION_LOST    //蓝牙已断开连接
                -> {
                    showMessage("Connection lost")
                }
                MESSAGE_UNABLE_CONNECT     //无法连接设备
                -> {
                    showMessage("no se puede conectar")
                }
            }
        }
    }

    private fun showMessage(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

}
