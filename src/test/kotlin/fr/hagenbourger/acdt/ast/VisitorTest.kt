package fr.hagenbourger.acdt.ast

import fr.hagenbourger.acdt.api.ast.Changelog
import fr.hagenbourger.acdt.api.ast.element.ChangelogEntry
import fr.hagenbourger.acdt.api.ast.visitor.JsonVisitor
import fr.hagenbourger.acdt.api.ast.visitor.MarkdownVisitor
import fr.hagenbourger.acdt.api.ast.visitor.XmlVisitor
import org.junit.Assert
import org.junit.Test

class VisitorTest {

    private val changelog = Changelog(
        listOf(
            ChangelogEntry("header1", "con\"t\"ent1"),
            ChangelogEntry("header2", "content2")
        )
    )

    @Test
    fun shouldRenderMarkdownChangelog() {
        val result = MarkdownVisitor().visit(changelog)

        Assert.assertEquals("# header1\ncon\"t\"ent1\n\n# header2\ncontent2\n", result)
    }

    @Test
    fun shouldRenderJsonChangelog() {
        val result = JsonVisitor().visit(changelog)

        Assert.assertEquals("{\"header1\":\"con\\\"t\\\"ent1\",\"header2\":\"content2\"}", result)
    }

    @Test
    fun shouldRenderXmlChangelog() {
        val result = XmlVisitor().visit(changelog)

        Assert.assertEquals("<changelog><header1>con\"t\"ent1</header1><header2>content2</header2></changelog>", result)
    }
}