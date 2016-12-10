package com.tonilopezmr.thermalprinter.usb;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.tonilopezmr.thermalprinter.R;

public class DeviceVH extends RecyclerView.ViewHolder {

  public TextView textView;

  public DeviceVH(View itemView) {
    super(itemView);
    textView = ((TextView) itemView.findViewById(R.id.textView));
  }
}
