package fr.hagenbourger.acdt.internal

import fr.hagenbourger.acdt.api.ReleaseNote
import java.io.File
import java.nio.charset.Charset

private const val DEFAULT_FILENAME: String = "default.txt"
private const val CHANGELOGS_FILENAME: String = "changelogs"
private const val MAX_DEPTH: Int = 2

class ReleaseNoteImpl : ReleaseNote {

    override fun generate(folder: File, version: String): Map<String, String> {
        val versionFileName: String = "${version}.txt"

        return folder
            .walkTopDown()
            .maxDepth(MAX_DEPTH)
            .filter { it.isDirectory }
            .filter { it.name != CHANGELOGS_FILENAME }
            .filter { it.parentFile.name != CHANGELOGS_FILENAME }
            .map { f ->
                Pair(
                    "${f.parentFile.name}-${f.name}",
                    (getVersionOrDefault(f, versionFileName)
                        ?: getVersionOrDefault(f.parentFile, versionFileName)
                        ?: getVersionOrDefault(f.parentFile.parentFile, versionFileName))
                        ?.readText(Charset.forName("UTF8"))
                        .orEmpty()
                )
            }
            .toMap()
    }

    private fun getVersionOrDefault(folder: File, versionFileName: String): File? {
        return folder.listFiles { _, name -> name == versionFileName }?.firstOrNull()
            ?: folder.listFiles { _, name -> name == DEFAULT_FILENAME }?.firstOrNull()
    }
}
