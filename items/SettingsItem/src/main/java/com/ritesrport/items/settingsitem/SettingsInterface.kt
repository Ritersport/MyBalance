package com.ritesrport.items.settingsitem

interface SettingsInterface {
    fun onSettingsItemClick()
}

val settingsInterfacePreview = object : SettingsInterface {
    override fun onSettingsItemClick() {}
}