package fr.hagenbourger.acdt.api.ast

interface ChangelogEntry {
    fun accept(changelogVisitor: ChangelogVisitor): String
}