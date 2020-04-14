package fr.hagenbourger.acdt.command

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.choice
import com.github.ajalt.clikt.parameters.types.file
import fr.hagenbourger.acdt.internal.PlayStoreImpl
import fr.hagenbourger.acdt.internal.ReleaseNoteImpl
import java.io.File

abstract class PlayStore(name: String, help: String) : CliktCommand(name = name, help = help) {

    protected val credentialsP12: File by option(
        "-c",
        "--credentials-p12",
        help = "Credentials p12 file"
    ).file(mustExist = true, mustBeReadable = true, canBeFile = true).required()
    protected val serviceAccountId: String by option(
        "-i",
        "--service-account-id",
        help = "Service account id"
    ).required()
    protected val appName: String by option(
        "-N",
        "--app-name",
        help = "App name"
    ).required()
    protected val appPackageName: String by option(
        "-P",
        "--app-package-name",
        help = "App package name"
    ).required()
}

class PlayStorePublish : PlayStore(name = "play-store-publish", help = "Publish app on the play store") {

    private val appVersion by option(
        "-V",
        "--app-version",
        help = "App version"
    ).required()
    private val apk: File by option(
        "-K",
        "--apk",
        help = "APK file"
    ).file(mustExist = true, mustBeReadable = true, canBeFile = true).required()
    private val track: String by option(
        "-T",
        "--track",
        help = "Set track (alpha / beta / production)"
    ).choice("alpha", "beta", "production").required()
    private val releaseNotes: File by option(
        "-R",
        "--release-notes",
        help = "Release notes folder"
    ).file(mustExist = true, mustBeReadable = true, canBeDir = true).required()

    override fun run() {
        echo("Start publishing app")
        PlayStoreImpl(
            serviceAccountId = serviceAccountId,
            credentialsP12 = credentialsP12,
            appName = appName,
            appPackageName = appPackageName
        ).publish(appVersion, apk, track, ReleaseNoteImpl().generate(releaseNotes, appVersion))
        echo("App published")
    }
}

class PlayStoreListApks : PlayStore(name = "play-store-list-apks", help = "List all apk published on the play store") {
    override fun run() {
        echo("Start listing all apk")
        PlayStoreImpl(
            serviceAccountId = serviceAccountId,
            credentialsP12 = credentialsP12,
            appName = appName,
            appPackageName = appPackageName
        )
            .listApks()
            .forEach { echo(it) }
        echo("Apks listed")
    }
}