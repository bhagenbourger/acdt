package fr.hagenbourger.acdt.api.ast.element

import fr.hagenbourger.acdt.api.ast.ChangelogEntry
import fr.hagenbourger.acdt.api.ast.ChangelogVisitor

class ChangelogEntry(val header: String, val content: String) :
    ChangelogEntry {
    override fun accept(changelogVisitor: ChangelogVisitor): String {
        return changelogVisitor.visit(this)
    }
}