package com.ritesrport.core.designsystem.formatters

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun LocalDate.toUserString(): String {
    val formatter = DateTimeFormatter.ofPattern("d MMMM", Locale.forLanguageTag("ru"))
    return when (this) {
        LocalDate.now() -> "Сегодня, ${format(formatter)}"
        LocalDate.now().minusDays(1) -> "Вчера, ${format(formatter)}"
        else -> format(formatter)
    }
}
