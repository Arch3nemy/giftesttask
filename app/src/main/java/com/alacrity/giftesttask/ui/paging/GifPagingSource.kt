package com.alacrity.giftesttask.ui.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alacrity.giftesttask.entity.Gif
import com.alacrity.giftesttask.repository.Repository

class GifPagingSource(
    private val query: String,
    private val repo: Repository
) : PagingSource<Int, Gif>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Gif> {
        val pageIndex = params.key ?: 0
        val loadSize = params.loadSize
        val page = repo.getGifs(query, (loadSize * pageIndex) + 1, LIMIT)

        val newCount = page.data.size
        val total = page.pagination.totalCount.toInt()
        val itemsBefore = pageIndex * LIMIT
        val itemsAfter = total - (itemsBefore + newCount)

        val prevKey = if (pageIndex == 0) null else pageIndex - 1
        val nextKey = if (itemsAfter == 0) null else pageIndex + 1

        return LoadResult.Page(
            data = page.data,
            prevKey = prevKey,
            nextKey = nextKey,
            itemsBefore = itemsBefore,
            itemsAfter = itemsAfter,
        )
    }


    override fun getRefreshKey(state: PagingState<Int, Gif>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        const val LIMIT = 10
    }
}

