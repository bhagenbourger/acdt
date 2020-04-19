package fr.hagenbourger.acdt.internal

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.FileContent
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.AndroidPublisherScopes
import com.google.api.services.androidpublisher.model.*
import fr.hagenbourger.acdt.api.PlayStore
import java.io.File
import java.io.FileInputStream

private const val MIME_TYPE_APK: String = "application/vnd.android.package-archive"
private const val STATUS: String = "draft"

class PlayStoreImpl(
    private val serviceAccountId: String,
    private val credentialsP12: File,
    private val appName: String,
    private val appPackageName: String
) : PlayStore {

    override fun publish(appVersion: String, apk: File, track: String, releaseNotes: Map<String, String>) {
        val edits: AndroidPublisher.Edits = buildAndroidPublisher().edits()
        val edit: AppEdit = edits.insert(appPackageName, null).execute()
        val uploadedApk: Apk = edits.apks().upload(appPackageName, edit.id, FileContent(MIME_TYPE_APK, apk)).execute()
        edits
            .tracks()
            .update(
                appPackageName,
                edit.id,
                track,
                Track().setReleases(
                    listOf(
                        TrackRelease()
                            .setName(appVersion)
                            .setVersionCodes(listOf(uploadedApk.versionCode.toLong()))
                            .setStatus(STATUS)
                            .setReleaseNotes(releaseNotes.map { entry ->
                                LocalizedText().setLanguage(entry.key).setText(entry.value)
                            }.toList())
                    )
                )
            ).execute()
        edits.commit(appPackageName, edit.id).execute()
    }

    override fun listApks(): List<String> {
        val edits: AndroidPublisher.Edits = buildAndroidPublisher().edits()
        val edit: AppEdit = edits.insert(appPackageName, null).execute()
        return edits
            .Apks()
            .list(appPackageName, edit.id)
            .execute()
            .apks
            .map { it.toPrettyString() }
            .toList()
    }

    private fun getCredential(): Credential {
        return GoogleCredential.Builder()
            .setTransport(GoogleNetHttpTransport.newTrustedTransport())
            .setJsonFactory(JacksonFactory.getDefaultInstance())
            .setServiceAccountId(serviceAccountId)
            .setServiceAccountScopes(setOf(AndroidPublisherScopes.ANDROIDPUBLISHER))
            .setServiceAccountPrivateKeyFromP12File(FileInputStream(credentialsP12))
            .build()
    }

    private fun buildAndroidPublisher(): AndroidPublisher {
        return AndroidPublisher.Builder(
            GoogleNetHttpTransport.newTrustedTransport(),
            JacksonFactory.getDefaultInstance(),
            getCredential()
        )
            .setApplicationName(appName)
            .build()
    }
}