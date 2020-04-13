package fr.hagenbourger.acdt.api

interface Gitlab {
    fun createRelease(name: String, description: String, tagName: String, milestones: List<String>)

    fun updateRelease(name: String, description: String, tagName: String, milestones: List<String>)

    fun deleteRelease(tagName: String)
}