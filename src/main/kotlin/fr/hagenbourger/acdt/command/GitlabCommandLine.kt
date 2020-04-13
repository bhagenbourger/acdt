package fr.hagenbourger.acdt.command

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.convert
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.options.split
import fr.hagenbourger.acdt.internal.GitlabImpl

abstract class Gitlab(name: String, help: String) : CliktCommand(name = name, help = help) {
    protected val url: String by option(
        "-u",
        "--url",
        help = "Gitlab url"
    ).required()
    protected val accessToken: String by option(
        "-t",
        "--access-token",
        help = "Your Gitlab access token"
    ).required()
    protected val projectId: String by option(
        "-p",
        "--project-id",
        help = "Your Gitlab project id"
    ).required()
}

abstract class GitlabRelease(name: String, help: String) : Gitlab(name = name, help = help) {

    protected val releaseTagName: String by option(
        "-T",
        "--tag-name",
        help = "Tag name associated to the release"
    ).required()
}

abstract class GitlabEditRelease(name: String, help: String) : GitlabRelease(name = name, help = help) {

    protected val releaseName: String by option(
        "-N",
        "--release-name",
        help = "Release name"
    ).required()
    protected val releaseDescription: String by option(
        "-D",
        "--release-description",
        help = "Release description"
    ).convert { it.replace("\\n", "\n") }.required()
    protected val releaseMilestone: List<String> by option(
        "-M",
        "--release-milestone",
        help = "Milestone associated to the release, if several separated by semicolon"
    ).split(";").required()
}

class GitlabCreateRelease : GitlabEditRelease(name = "gitlab-create-release", help = "Create gitlab release") {
    override fun run() {
        echo("Start creating release")
        GitlabImpl(
            url = url,
            accessToken = accessToken,
            projectId = projectId
        ).createRelease(releaseName, releaseDescription, releaseTagName, releaseMilestone)
        echo("Release created")
    }
}

class GitlabUpdateRelease : GitlabEditRelease(name = "gitlab-update-release", help = "Update gitlab release") {
    override fun run() {
        echo("Start updating release")
        GitlabImpl(
            url = url,
            accessToken = accessToken,
            projectId = projectId
        ).updateRelease(releaseName, releaseDescription, releaseTagName, releaseMilestone)
        echo("Release updated")
    }
}

class GitlabDeleteRelease : GitlabRelease(name = "gitlab-delete-release", help = "Delete gitlab release") {
    override fun run() {
        echo("Start deleting release")
        GitlabImpl(
            url = url,
            accessToken = accessToken,
            projectId = projectId
        ).deleteRelease(releaseTagName)
        echo("Release deleted")
    }
}