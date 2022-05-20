package com.lq.jetnews.data.posts

import com.lq.jetnews.data.Result
import com.lq.jetnews.model.PostsFeed
import com.lq.jetnews.utils.addOrRemove
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext


class FakePostsRepository : PostsRepository {

    //暂时存在内存里
    private val favorites = MutableStateFlow<Set<String>>(setOf())

    //协程互斥锁,保证多协程调用的数据安全
    private val mutex = Mutex()

    override suspend fun getPostsFeed(): Result<PostsFeed> {
        return withContext(Dispatchers.IO) {
            delay(800) // pretend we're on a slow network
            if (shouldRandomlyFail()) {
                Result.Error(IllegalStateException())
            } else {
                Result.Success(posts)
            }
        }
    }

    override fun observeFavorites(): Flow<Set<String>> = favorites

    override suspend fun toggleFavorite(postId: String) {
        mutex.withLock {
            val set = favorites.value.toMutableSet()
            set.addOrRemove(postId)
            favorites.value = set
        }
    }


    // used to drive "random" failure in a predictable pattern, making the first request always
    // succeed
    private var requestCount = 0

    /**
     * Randomly fail some loads to simulate a real network.
     *
     * This will fail deterministically every 5 requests
     */
    private fun shouldRandomlyFail(): Boolean = ++requestCount % 5 == 0
}