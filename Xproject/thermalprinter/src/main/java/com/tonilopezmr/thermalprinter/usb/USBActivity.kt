package com.tonilopezmr.thermalprinter.usb

import android.hardware.usb.UsbDevice
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.tonilopezmr.thermalprinter.R
import com.tonilopezmr.thermalprinter.usb.lib.UsbController

import kotlinx.android.synthetic.main.activity_usb.*
import java.util.HashMap

class USBActivity : AppCompatActivity() {

  lateinit var usbCtrl: UsbController
  var device: UsbDevice? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_usb)

    val mHandler = object : Handler() {
      override fun handleMessage(msg: Message) {
        when (msg.what) {
          UsbController.USB_CONNECTED -> {
            Toast.makeText(applicationContext, "SE HA CONECTADO",
                Toast.LENGTH_SHORT).show()
          }
        }
      }
    }
    usbCtrl = UsbController("simple.name", this, mHandler)

    val adapter = DevAdapter({
        connectDevice(it)
    })

    recyclerView.adapter = adapter
    recyclerView.layoutManager = LinearLayoutManager(this)
    connectButton.setOnClickListener {
      usbCtrl.close()
      val usbList: HashMap<String, UsbDevice> = usbCtrl.usbList
      val list: List<UsbDevice> = usbList.map { it.value }
      Toast.makeText(applicationContext, "" + list.size,
          Toast.LENGTH_SHORT).show()
      adapter.addAll(list)
    }
    kitchenButton.setOnClickListener { usbCtrl.sendMsg("Cotel gay :muscle:", "GBK", device) }
  }

  private fun connectDevice(device: UsbDevice) {
    if (!usbCtrl.isHasPermission(device)) {
      usbCtrl.getPermission(device)
    } else {
      Toast.makeText(applicationContext, "Illo ya tenias permiso y te has conectado",
          Toast.LENGTH_SHORT).show()
    }
    this.device = device
  }

  override fun onDestroy() {
    usbCtrl.close()
    super.onDestroy()
  }
}
