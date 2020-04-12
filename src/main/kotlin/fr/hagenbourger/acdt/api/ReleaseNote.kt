package fr.hagenbourger.acdt.api

interface ReleaseNote {

    fun generateAll(fr: String, en: String): Map<String, String>
}