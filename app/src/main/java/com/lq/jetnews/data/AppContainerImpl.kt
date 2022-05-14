package com.lq.jetnews.data

import com.lq.jetnews.data.posts.FakePostsRepository
import com.lq.jetnews.data.posts.PostsRepository

/**
 * Dependency Injection container at the application level.
 */
interface AppContainer {
    val postsRepository: PostsRepository
}


class AppContainerImpl :AppContainer{
    override val postsRepository: PostsRepository by lazy {
        FakePostsRepository()
    }
}