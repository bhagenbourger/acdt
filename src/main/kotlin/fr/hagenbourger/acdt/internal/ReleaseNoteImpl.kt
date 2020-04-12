package fr.hagenbourger.acdt.internal

import fr.hagenbourger.acdt.api.ReleaseNote

private val FR_LANGUAGES: Set<String> = setOf("fr-FR", "fr-CA")
private val EN_LANGUAGES: Set<String> = setOf("en-AU", "en-CA", "en-GB", "en-IN", "en-SG", "en-US", "en-ZA")

class ReleaseNoteImpl : ReleaseNote {

    override fun generateAll(fr: String, en: String): Map<String, String> {
        return (FR_LANGUAGES.map { Pair(it, fr) } + EN_LANGUAGES.map { Pair(it, en) }).toMap()
    }
}
