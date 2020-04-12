package fr.hagenbourger.acdt.api

interface Gitlab {
    fun release(name: String, description: String, tagName: String, milestones: List<String>)
}