package fr.hagenbourger.acdt.api

import fr.hagenbourger.acdt.api.ast.ChangelogFormat
import java.io.File

interface ReleaseNote {

    fun generateWithDefault(folder: File, version: String): Map<String, String>

    fun generateForVersion(folder: File, version: String, outputFormat: ChangelogFormat): String
}