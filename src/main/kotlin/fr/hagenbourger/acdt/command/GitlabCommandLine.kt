package fr.hagenbourger.acdt.command

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.options.split
import fr.hagenbourger.acdt.internal.GitlabImpl

class GitlabRelease : CliktCommand(name = "gitlab-release", help = "Create gitlab release") {

    private val url: String by option(
        "-u",
        "--url",
        help = "Gitlab url"
    ).required()
    private val accessToken: String by option(
        "-t",
        "--access-token",
        help = "Your Gitlab access token"
    ).required()
    private val projectId: String by option(
        "-p",
        "--project-id",
        help = "Your Gitlab project id"
    ).required()
    private val releaseName: String by option(
        "-N",
        "--release-name",
        help = "Release name"
    ).required()
    private val releaseDescription: String by option(
        "-D",
        "--release-description",
        help = "Release description"
    ).required()
    private val releaseTagName: String by option(
        "-T",
        "--tag-name",
        help = "Tag name associated to the release"
    ).required()
    private val releaseMilestone: List<String> by option(
        "-M",
        "--release-milestone",
        help = "Milestone associated to the release, if several separated by semicolon"
    ).split(";").required()

    override fun run() {
        echo("Start creating release")
        GitlabImpl(
            url = url,
            accessToken = accessToken,
            projectId = projectId
        ).release(releaseName, releaseDescription, releaseTagName, releaseMilestone)
        echo("Release created")
    }
}