package fr.hagenbourger.acdt

import com.github.ajalt.clikt.core.subcommands
import fr.hagenbourger.acdt.command.CommandLine
import fr.hagenbourger.acdt.command.GitlabRelease
import fr.hagenbourger.acdt.command.PlayStoreListApks
import fr.hagenbourger.acdt.command.PlayStorePublish

object Application {

    @JvmStatic
    fun main(args: Array<String>) = CommandLine().subcommands(
        PlayStorePublish(),
        PlayStoreListApks(),
        GitlabRelease()
    ).main(args)
}