package fr.hagenbourger.acdt.api.ast

import fr.hagenbourger.acdt.api.ast.element.ChangelogEntry

object ChangelogParser {

    fun parse(releaseNotes: Map<String, String>): Changelog {
        return Changelog(releaseNotes.map { ChangelogEntry(it.key, it.value) }.toList())
    }
}