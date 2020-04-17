package fr.hagenbourger.acdt.api.ast

import fr.hagenbourger.acdt.api.ast.element.ChangelogEntry

interface ChangelogVisitor {
    fun visit(changelog: Changelog): String
    fun visit(changelogEntry: ChangelogEntry): String
}