package fr.hagenbourger.acdt.ast

import fr.hagenbourger.acdt.api.ast.ChangelogParser
import org.junit.Assert
import org.junit.Test

class ChangelogParserTest {

    @Test
    fun shouldParseReleaseNotes() {
        val releaseNotes: Map<String, String> = mapOf(
            Pair("fr", "contentFR"),
            Pair("en", "contentEN")
        )

        val result = ChangelogParser.parse(releaseNotes)

        Assert.assertEquals(2, result.entries.size)
        Assert.assertEquals("en", result.entries[0].header)
        Assert.assertEquals("contentEN", result.entries[0].content)
        Assert.assertEquals("fr", result.entries[1].header)
        Assert.assertEquals("contentFR", result.entries[1].content)
    }
}