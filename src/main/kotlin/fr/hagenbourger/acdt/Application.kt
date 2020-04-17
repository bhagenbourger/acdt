package fr.hagenbourger.acdt

import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.options.versionOption
import fr.hagenbourger.acdt.command.*

object Application {

    @JvmStatic
    fun main(args: Array<String>) = CommandLine()
        .versionOption("0.1.0")
        .subcommands(
            PlayStorePublish(),
            PlayStoreListApks(),
            GitlabCreateRelease(),
            GitlabUpdateRelease(),
            GitlabDeleteRelease(),
            ChangelogGenerate()
        ).main(args)
}