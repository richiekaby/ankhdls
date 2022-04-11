package net.larntech.ankhdls.ui.bars

import java.io.Serializable

data class BarModel(
    var image: Int,
    var text: String,
    var desc: String,
): Serializable