package fr.hagenbourger.acdt.internal

import org.junit.Assert
import org.junit.Test

class ReleaseNoteImplTest {

    @Test
    fun shouldGenerateAll() {
        val releaseNotes = ReleaseNoteImpl().generateAll("in french", "in english")

        Assert.assertEquals(releaseNotes.size, 9)
        Assert.assertEquals(releaseNotes["fr-FR"], "in french")
        Assert.assertEquals(releaseNotes["fr-CA"], "in french")
        Assert.assertEquals(releaseNotes["en-AU"], "in english")
        Assert.assertEquals(releaseNotes["en-CA"], "in english")
        Assert.assertEquals(releaseNotes["en-GB"], "in english")
        Assert.assertEquals(releaseNotes["en-IN"], "in english")
        Assert.assertEquals(releaseNotes["en-SG"], "in english")
        Assert.assertEquals(releaseNotes["en-US"], "in english")
        Assert.assertEquals(releaseNotes["en-ZA"], "in english")
        Assert.assertNull(releaseNotes[""])
    }
}