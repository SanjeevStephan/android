package com.sanjeev.stephan.murmu.smarthome.sample

import com.sanjeev.stephan.murmu.smarthome.R

class DeviceModel(val image: Int,val name: String) {

    constructor() : this(image= R.mipmap.ic_device_light_bulb_off_foreground,name = "My Light Bulb")
}