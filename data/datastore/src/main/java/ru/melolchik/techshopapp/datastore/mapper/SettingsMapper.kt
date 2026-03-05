package ru.melolchik.techshopapp.datastore.mapper

import ru.melolchik.techshopapp.datastore.components.LanguageParam
import ru.melolchik.techshopapp.datastore.components.ThemeParam


fun String.toThemeParam(): ThemeParam{

    for(theme in ThemeParam.entries){
        if(theme.value == this) return theme
    }
    return ThemeParam.LIGHT

}


fun String.toLanguageParam(): LanguageParam{
    for(lang in LanguageParam.entries){
        if(lang.value == this) return lang
    }
    return LanguageParam.RU

}