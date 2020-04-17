package fr.hagenbourger.acdt.api.ast.visitor

import fr.hagenbourger.acdt.api.ast.Changelog
import fr.hagenbourger.acdt.api.ast.ChangelogVisitor
import fr.hagenbourger.acdt.api.ast.element.ChangelogEntry

private const val NEW_LINE: String = "\n"

class MarkdownVisitor : ChangelogVisitor {

    override fun visit(changelog: Changelog): String {
        return changelog.entries.joinToString(NEW_LINE) { it.accept(this) }
    }

    override fun visit(changelogEntry: ChangelogEntry): String {
        return "# ${changelogEntry.header}$NEW_LINE${changelogEntry.content}$NEW_LINE"
    }
}