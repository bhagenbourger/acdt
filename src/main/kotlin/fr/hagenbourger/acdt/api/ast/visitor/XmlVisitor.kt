package fr.hagenbourger.acdt.api.ast.visitor

import fr.hagenbourger.acdt.api.ast.Changelog
import fr.hagenbourger.acdt.api.ast.ChangelogVisitor
import fr.hagenbourger.acdt.api.ast.element.ChangelogEntry

class XmlVisitor : ChangelogVisitor {

    override fun visit(changelog: Changelog): String {
        return "<changelog>${changelog.entries.joinToString("") { it.accept(this) }}</changelog>"
    }

    override fun visit(changelogEntry: ChangelogEntry): String {
        return "<${changelogEntry.header}>${changelogEntry.content}</${changelogEntry.header}>"
    }
}