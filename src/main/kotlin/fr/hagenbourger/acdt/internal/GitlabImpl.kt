package fr.hagenbourger.acdt.internal

import fr.hagenbourger.acdt.api.Gitlab
import org.gitlab4j.api.Constants
import org.gitlab4j.api.GitLabApi
import org.gitlab4j.api.models.Milestone
import org.gitlab4j.api.models.ReleaseParams

class GitlabImpl(
    private val url: String,
    private val accessToken: String,
    private val projectId: String
) : Gitlab {

    override fun createRelease(name: String, description: String, tagName: String, milestones: List<String>) {
        val params: ReleaseParams = ReleaseParams()
            .withName(name)
            .withDescription(description)
            .withTagName(tagName)
            .withMilestones(milestones)
        GitLabApi(url, accessToken).releasesApi.createRelease(projectId, params)
    }

    override fun updateRelease(name: String, description: String, tagName: String, milestones: List<String>) {
        val params: ReleaseParams = ReleaseParams()
            .withName(name)
            .withDescription(description)
            .withTagName(tagName)
            .withMilestones(milestones)
        GitLabApi(url, accessToken).releasesApi.updateRelease(projectId, params)
    }

    override fun deleteRelease(tagName: String) {
        GitLabApi(url, accessToken).releasesApi.deleteRelease(projectId, tagName)
    }

    override fun closeMilestone(name: String): Milestone? {
        val milestonesApi = GitLabApi(url, accessToken).milestonesApi
        return milestonesApi
            .getMilestones(projectId, Constants.MilestoneState.ACTIVE, name)
            .firstOrNull { it.title == name }
            ?.let { milestonesApi.closeMilestone(projectId, it.id) }
    }
}