package com.lq.jetnews.data.posts

import com.lq.jetnews.model.PostsFeed
import com.lq.jetnews.data.Result
import kotlinx.coroutines.flow.Flow

interface PostsRepository {


    /**
     * Get JetNews posts.
     */
    suspend fun getPostsFeed(): Result<PostsFeed>

    /**
     * Observe the current favorites
     */
    fun observeFavorites(): Flow<Set<String>>

    suspend fun toggleFavorite(postId: String)
}