package com.lq.jetnews.data.posts

import com.lq.jetnews.model.PostsFeed
import com.lq.jetnews.data.Result

interface PostsRepository {


    /**
     * Get JetNews posts.
     */
    suspend fun getPostsFeed(): Result<PostsFeed>
}