package com.ritesrport.core.model

enum class IconKey(val value: String) {
    Bus("Bus");

    companion object {
        fun getFromValue(value: String): IconKey {
            for (entry in entries) {
                if (entry.value == value) {
                    return entry
                }
            }
            return default
        }

        val default = Bus
    }
}