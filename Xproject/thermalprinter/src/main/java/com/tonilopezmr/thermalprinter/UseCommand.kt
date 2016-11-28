package com.tonilopezmr.thermalprinter

object UseCommand {

    private val ESC: Byte = 0x1B
    private val NL: Byte = 0x0A
    private val GS: Byte = 0x1D

    private val a: Byte = 'a'.toByte()
    private val M: Byte = 'M'.toByte()
    private val AT: Byte = '@'.toByte()
    private val exclamation: Byte = '!'.toByte()
    private val J: Byte = 'J'.toByte()

    //Actions
    val ESC_INIT = byteArrayOf(ESC, AT)
    val LF_NEW_LINE = byteArrayOf(NL)

    //font
    var ESC_M = byteArrayOf(ESC, M, 0x00)

    //Align
    var ESC_ALIGN = byteArrayOf(ESC, a, 0x00)
    var ESC_ALIGN_CENTER = byteArrayOf(ESC, a, 0x01)

    //Select print mode
    var ESC_FONT_A_DEFAULT = byteArrayOf(GS, exclamation, 0x00)
    var ESC_FONT_B = byteArrayOf(GS, exclamation, 0x01)

    //Feed
    var ESC_J_FEED = byteArrayOf(ESC, J, 0x00)
    var ESC_FEED_LINE = byteArrayOf(ESC, J, 10.toByte())
    var ESC_FEED_END = byteArrayOf(ESC, J, 50.toByte())

}
