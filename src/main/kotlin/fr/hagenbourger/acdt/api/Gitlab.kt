package fr.hagenbourger.acdt.api

import org.gitlab4j.api.models.Milestone

interface Gitlab {
    fun createRelease(name: String, description: String, tagName: String, milestones: List<String>)

    fun updateRelease(name: String, description: String, tagName: String, milestones: List<String>)

    fun deleteRelease(tagName: String)

    fun closeMilestone(name: String): Milestone?
}