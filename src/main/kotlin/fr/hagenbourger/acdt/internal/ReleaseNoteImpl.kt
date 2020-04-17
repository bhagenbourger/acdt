package fr.hagenbourger.acdt.internal

import fr.hagenbourger.acdt.api.ReleaseNote
import fr.hagenbourger.acdt.api.ast.ChangelogFormat
import fr.hagenbourger.acdt.api.ast.ChangelogParser
import java.io.File
import java.nio.charset.Charset

private const val DEFAULT_FILENAME: String = "default.txt"
private const val CHANGELOGS_FILENAME: String = "changelogs"
private const val MAX_DEPTH: Int = 2

class ReleaseNoteImpl : ReleaseNote {

    override fun generateWithDefault(folder: File, version: String): Map<String, String> {
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

    override fun generateForVersion(folder: File, version: String, outputFormat: ChangelogFormat): String {
        val versionFileName: String = "${version}.txt"

        return folder
            .walkTopDown()
            .maxDepth(MAX_DEPTH + 1)
            .filter { it.isFile }
            .filter { it.name == versionFileName }
            .filter { it.parentFile.name != CHANGELOGS_FILENAME }
            .map { f ->
                val grandParentName: String = f.parentFile.parentFile.name
                val parentName: String = f.parentFile.name
                Pair(
                    if (grandParentName != CHANGELOGS_FILENAME) "$grandParentName-$parentName" else parentName,
                    f.readText(Charset.forName("UTF8"))
                )
            }
            .toMap()
            .let { ChangelogParser.parse(it) }
            .let { outputFormat.visitor.visit(it) }
    }

    private fun getVersionOrDefault(folder: File, versionFileName: String): File? {
        return folder.listFiles { _, name -> name == versionFileName }?.firstOrNull()
            ?: folder.listFiles { _, name -> name == DEFAULT_FILENAME }?.firstOrNull()
    }
}
