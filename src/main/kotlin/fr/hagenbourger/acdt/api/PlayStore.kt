package fr.hagenbourger.acdt.api

import java.io.File

interface PlayStore {

    fun publish(appVersion: String, apk: File, track: String, releaseNotes: Map<String, String>)

    fun listApks(): List<String>
}