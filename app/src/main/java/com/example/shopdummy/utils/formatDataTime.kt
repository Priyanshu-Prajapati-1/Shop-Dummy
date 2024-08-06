package com.example.shopdummy.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun formatDateTime(dateTime: String): String {
    val instant = Instant.parse(dateTime)
    val readableFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy h:mm a", Locale.getDefault())
    return readableFormatter.format(instant.atZone(ZoneId.systemDefault()))
}