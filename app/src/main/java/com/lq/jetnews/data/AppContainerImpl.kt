package com.lq.jetnews.data

import com.lq.jetnews.data.interests.FakeInterestsRepository
import com.lq.jetnews.data.interests.InterestsRepository
import com.lq.jetnews.data.posts.FakePostsRepository
import com.lq.jetnews.data.posts.PostsRepository

/**
 * Dependency Injection container at the application level.
 */
interface AppContainer {
    val postsRepository: PostsRepository
    val interestsRepository: InterestsRepository
}


class AppContainerImpl :AppContainer{
    override val postsRepository: PostsRepository by lazy {
        FakePostsRepository()
    }
    override val interestsRepository: InterestsRepository by lazy {
        FakeInterestsRepository()
    }
}