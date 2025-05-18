package me.baggi.furinasdk.utility

private data class TimeComponents(
    val seconds: Long,
    val minutes: Long,
    val hours: Long,
    val days: Long
) {
    companion object {
        fun fromMillis(milliseconds: Long): TimeComponents = TimeComponents(
            (milliseconds / 1000) % 60,
            (milliseconds / (1000 * 60)) % 60,
            (milliseconds / (1000 * 60 * 60)) % 24,
            (milliseconds / (1000 * 60 * 60 * 24)) % 7
        )
    }
}

fun formatMilliseconds(milliseconds: Long): String {
    val (seconds, minutes, hours, days) = TimeComponents.fromMillis(milliseconds)
    return when {
        days > 0 -> "%02d д. %02d ч. %02d мин. %02d сек.".format(days, hours, minutes, seconds)
        hours > 0 -> "%02d ч. %02d мин. %02d сек.".format(hours, minutes, seconds)
        minutes > 0 -> "%02d мин. %02d сек.".format(minutes, seconds)
        seconds > 0 -> "%02d сек.".format(seconds)
        else -> ""
    }
}

fun formatMillisecondsCompact(milliseconds: Long): String {
    val (seconds, minutes, hours, days) = TimeComponents.fromMillis(milliseconds)
    return when {
        days > 0 -> "%02d:%02d:%02d:%02d".format(days, hours, minutes, seconds)
        hours > 0 -> "%02d:%02d:%02d".format(hours, minutes, seconds)
        minutes > 0 -> "%02d:%02d".format(minutes, seconds)
        seconds > 0 -> "%02d сек.".format(seconds)
        else -> ""
    }
}

fun formatMillisecondsHeight(milliseconds: Long): String {
    val (seconds, minutes, hours, days) = TimeComponents.fromMillis(milliseconds)
    return when {
        days > 0 -> "%02d д.".format(days)
        hours > 0 -> "%02d ч.".format(hours)
        minutes > 0 -> "%02d мин.".format(minutes)
        seconds > 0 -> "%02d сек".format(seconds)
        else -> ""
    }
}