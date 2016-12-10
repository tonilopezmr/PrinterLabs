package com.tonilopezmr.thermalprinter.usb

import android.hardware.usb.UsbDevice
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.tonilopezmr.thermalprinter.R

class DevAdapter(private val onclick: (UsbDevice) -> Unit) : RecyclerView.Adapter<DeviceVH>() {

  val list = mutableListOf<UsbDevice>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceVH? {
    val view = LayoutInflater
        .from(parent.context)
        .inflate(R.layout.device_item, parent, false)
    return DeviceVH(view)
  }

  override fun onBindViewHolder(holder: DeviceVH, position: Int) {
    val device = list[position]
    holder.textView.text = device.deviceName
    holder.itemView.setOnClickListener { onclick(device) }
  }

  override fun getItemCount(): Int {
    return list.size
  }

  fun addAll(list: List<UsbDevice>) {
    this.list.addAll(list)
    notifyDataSetChanged()
  }
}
