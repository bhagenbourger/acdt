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
    fun shouldGenerateWithDefaultForV100() {
        val releaseNotes: Map<String, String> =
            folder?.let { ReleaseNoteImpl().generateWithDefault(it, "v1.0.0") }.orEmpty()

        Assert.assertEquals(5, releaseNotes.size)
        Assert.assertEquals("changelogs/en/AU/default.txt", releaseNotes["en-AU"])
        Assert.assertEquals("changelogs/en/GB/default.txt", releaseNotes["en-GB"])
        Assert.assertEquals("changelogs/en/default.txt", releaseNotes["en-US"])
        Assert.assertEquals("changelogs/fr/v1.0.0.txt", releaseNotes["fr-CA"])
        Assert.assertEquals("changelogs/fr/v1.0.0.txt", releaseNotes["fr-FR"])
        Assert.assertNull(releaseNotes[""])
    }

    @Test
    fun shouldGenerateWithDefaultForV110() {
        val releaseNotes: Map<String, String> =
            folder?.let { ReleaseNoteImpl().generateWithDefault(it, "v1.1.0") }.orEmpty()

        Assert.assertEquals(5, releaseNotes.size)
        Assert.assertEquals("changelogs/en/AU/default.txt", releaseNotes["en-AU"])
        Assert.assertEquals("changelogs/en/GB/v1.1.0.txt", releaseNotes["en-GB"])
        Assert.assertEquals("changelogs/en/default.txt", releaseNotes["en-US"])
        Assert.assertEquals("changelogs/default.txt", releaseNotes["fr-CA"])
        Assert.assertEquals("changelogs/fr/FR/v1.1.0.txt", releaseNotes["fr-FR"])
        Assert.assertNull(releaseNotes[""])
    }

    @Test
    fun shouldGenerateWithDefaultForV120() {
        val releaseNotes: Map<String, String> =
            folder?.let { ReleaseNoteImpl().generateWithDefault(it, "v1.2.0") }.orEmpty()

        Assert.assertEquals(5, releaseNotes.size)
        Assert.assertEquals("changelogs/en/AU/default.txt", releaseNotes["en-AU"])
        Assert.assertEquals("changelogs/en/GB/default.txt", releaseNotes["en-GB"])
        Assert.assertEquals("changelogs/en/default.txt", releaseNotes["en-US"])
        Assert.assertEquals("changelogs/v1.2.0.txt", releaseNotes["fr-CA"])
        Assert.assertEquals("changelogs/v1.2.0.txt", releaseNotes["fr-FR"])
        Assert.assertNull(releaseNotes[""])
    }

    @Test
    fun shouldGenerateWithDefaultForV200() {
        val releaseNotes: Map<String, String> =
            folder?.let { ReleaseNoteImpl().generateWithDefault(it, "v2.0.0") }.orEmpty()

        Assert.assertEquals(5, releaseNotes.size)
        Assert.assertEquals("changelogs/en/AU/default.txt", releaseNotes["en-AU"])
        Assert.assertEquals("changelogs/en/GB/default.txt", releaseNotes["en-GB"])
        Assert.assertEquals("changelogs/en/default.txt", releaseNotes["en-US"])
        Assert.assertEquals("changelogs/default.txt", releaseNotes["fr-CA"])
        Assert.assertEquals("changelogs/default.txt", releaseNotes["fr-FR"])
        Assert.assertNull(releaseNotes[""])
    }

    @Test
    fun shouldGenerateForVersionForV100() {
        val releaseNotes: Map<String, String> =
            folder?.let { ReleaseNoteImpl().generateForVersion(it, "v1.0.0") }.orEmpty()

        Assert.assertEquals(1, releaseNotes.size)
        Assert.assertEquals("changelogs/fr/v1.0.0.txt", releaseNotes["fr"])
    }

    @Test
    fun shouldGenerateForVersionForV110() {
        val releaseNotes: Map<String, String> =
            folder?.let { ReleaseNoteImpl().generateForVersion(it, "v1.1.0") }.orEmpty()

        Assert.assertEquals(2, releaseNotes.size)
        Assert.assertEquals("changelogs/en/GB/v1.1.0.txt", releaseNotes["en-GB"])
        Assert.assertEquals("changelogs/fr/FR/v1.1.0.txt", releaseNotes["fr-FR"])
    }

    @Test
    fun shouldGenerateForVersionForV120() {
        val releaseNotes: Map<String, String> =
            folder?.let { ReleaseNoteImpl().generateForVersion(it, "v1.2.0") }.orEmpty()

        Assert.assertTrue(releaseNotes.isEmpty())
    }

    @Test
    fun shouldGenerateForVersionForV200() {
        val releaseNotes: Map<String, String> =
            folder?.let { ReleaseNoteImpl().generateForVersion(it, "v2.0.0") }.orEmpty()

        Assert.assertTrue(releaseNotes.isEmpty())
    }
}