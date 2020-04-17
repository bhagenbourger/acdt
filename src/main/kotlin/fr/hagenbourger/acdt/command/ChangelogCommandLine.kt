package fr.hagenbourger.acdt.command

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.choice
import com.github.ajalt.clikt.parameters.types.file
import fr.hagenbourger.acdt.api.ast.ChangelogFormat
import fr.hagenbourger.acdt.internal.ReleaseNoteImpl
import java.io.File

class ChangelogGenerate : CliktCommand(name = "changelog-generate", help = "Generate changelog") {

    private val releaseNotes: File by option(
        "-R",
        "--release-notes",
        help = "Release notes folder"
    ).file(mustExist = true, mustBeReadable = true, canBeDir = true).required()
    private val version by option(
        "-V",
        "--version",
        help = "Version"
    ).required()
    private val outputFormat: String by option(
        "-F",
        "--output-format",
        help = "Set output format (Default MARKDOWN)"
    )
        .choice(ChangelogFormat.MARKDOWN.name, ChangelogFormat.JSON.name, ChangelogFormat.XML.name)
        .default(ChangelogFormat.MARKDOWN.name)

    override fun run() {
        echo(
            ReleaseNoteImpl().generateForVersion(releaseNotes, version, ChangelogFormat.valueOf(outputFormat))
        )
    }
}