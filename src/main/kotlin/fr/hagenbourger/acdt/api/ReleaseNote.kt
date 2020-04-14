package fr.hagenbourger.acdt.api

import java.io.File

interface ReleaseNote {

    fun generate(folder: File, version: String): Map<String, String>
}