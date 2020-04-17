package fr.hagenbourger.acdt.api.ast.visitor

import fr.hagenbourger.acdt.api.ast.Changelog
import fr.hagenbourger.acdt.api.ast.ChangelogVisitor
import fr.hagenbourger.acdt.api.ast.element.ChangelogEntry

class JsonVisitor : ChangelogVisitor {

    override fun visit(changelog: Changelog): String {
        return "{${changelog.entries.joinToString(",") { it.accept(this) }}}"
    }

    override fun visit(changelogEntry: ChangelogEntry): String {
        return "\"${escape(changelogEntry.header)}\":\"${escape(changelogEntry.content)}\""
    }

    private fun escape(value: String): String {
        return value.replace("\"", "\\\"")
    }
}