package fr.hagenbourger.acdt.api.ast

import fr.hagenbourger.acdt.api.ast.visitor.JsonVisitor
import fr.hagenbourger.acdt.api.ast.visitor.MarkdownVisitor
import fr.hagenbourger.acdt.api.ast.visitor.XmlVisitor

enum class ChangelogFormat(val visitor: ChangelogVisitor) {
    MARKDOWN(MarkdownVisitor()),
    JSON(JsonVisitor()),
    XML(XmlVisitor())
}