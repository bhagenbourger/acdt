package fr.hagenbourger.acdt.internal

import fr.hagenbourger.acdt.api.Gitlab
import org.gitlab4j.api.GitLabApi
import org.gitlab4j.api.models.ReleaseParams

class GitlabImpl(
    private val url: String,
    private val accessToken: String,
    private val projectId: String
) : Gitlab {

    override fun release(name: String, description: String, tagName: String, milestones: List<String>) {
        val params: ReleaseParams = ReleaseParams()
            .withName(name)
            .withDescription(description)
            .withTagName(tagName)
            .withMilestones(milestones)
        GitLabApi(url, accessToken).releasesApi.createRelease(projectId, params)
    }
}