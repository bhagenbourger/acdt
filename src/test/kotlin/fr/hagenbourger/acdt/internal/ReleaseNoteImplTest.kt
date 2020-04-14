package fr.hagenbourger.acdt.internal

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.File

class ReleaseNoteImplTest {

    var folder: File? = null

    @Before
    fun setUp() {
        folder = this::class.java.classLoader.getResource("changelogs")?.toURI()?.let { File(it) }
    }

    @Test
    fun shouldGenerateAllForV100() {
        val releaseNotes: Map<String, String> = folder?.let { ReleaseNoteImpl().generate(it, "v1.0.0") }.orEmpty()

        Assert.assertEquals(releaseNotes.size, 5)
        Assert.assertEquals(releaseNotes["en-AU"], "changelogs/en/AU/default.txt")
        Assert.assertEquals(releaseNotes["en-GB"], "changelogs/en/GB/default.txt")
        Assert.assertEquals(releaseNotes["en-US"], "changelogs/en/default.txt")
        Assert.assertEquals(releaseNotes["fr-CA"], "changelogs/fr/v1.0.0.txt")
        Assert.assertEquals(releaseNotes["fr-FR"], "changelogs/fr/v1.0.0.txt")
        Assert.assertNull(releaseNotes[""])
    }

    @Test
    fun shouldGenerateAllForV110() {
        val releaseNotes: Map<String, String> = folder?.let { ReleaseNoteImpl().generate(it, "v1.1.0") }.orEmpty()

        Assert.assertEquals(releaseNotes.size, 5)
        Assert.assertEquals(releaseNotes["en-AU"], "changelogs/en/AU/default.txt")
        Assert.assertEquals(releaseNotes["en-GB"], "changelogs/en/GB/v1.1.0.txt")
        Assert.assertEquals(releaseNotes["en-US"], "changelogs/en/default.txt")
        Assert.assertEquals(releaseNotes["fr-CA"], "changelogs/default.txt")
        Assert.assertEquals(releaseNotes["fr-FR"], "changelogs/fr/FR/v1.1.0.txt")
        Assert.assertNull(releaseNotes[""])
    }

    @Test
    fun shouldGenerateAllForV120() {
        val releaseNotes: Map<String, String> = folder?.let { ReleaseNoteImpl().generate(it, "v1.2.0") }.orEmpty()

        Assert.assertEquals(releaseNotes.size, 5)
        Assert.assertEquals(releaseNotes["en-AU"], "changelogs/en/AU/default.txt")
        Assert.assertEquals(releaseNotes["en-GB"], "changelogs/en/GB/default.txt")
        Assert.assertEquals(releaseNotes["en-US"], "changelogs/en/default.txt")
        Assert.assertEquals(releaseNotes["fr-CA"], "changelogs/v1.2.0.txt")
        Assert.assertEquals(releaseNotes["fr-FR"], "changelogs/v1.2.0.txt")
        Assert.assertNull(releaseNotes[""])
    }

    @Test
    fun shouldGenerateAllForV200() {
        val releaseNotes: Map<String, String> = folder?.let { ReleaseNoteImpl().generate(it, "v2.0.0") }.orEmpty()

        Assert.assertEquals(releaseNotes.size, 5)
        Assert.assertEquals(releaseNotes["en-AU"], "changelogs/en/AU/default.txt")
        Assert.assertEquals(releaseNotes["en-GB"], "changelogs/en/GB/default.txt")
        Assert.assertEquals(releaseNotes["en-US"], "changelogs/en/default.txt")
        Assert.assertEquals(releaseNotes["fr-CA"], "changelogs/default.txt")
        Assert.assertEquals(releaseNotes["fr-FR"], "changelogs/default.txt")
        Assert.assertNull(releaseNotes[""])
    }
}