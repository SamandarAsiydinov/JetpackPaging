package com.example.jetpackpaging.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.jetpackpaging.model.UserData
import com.example.jetpackpaging.network.RetroInstance

class UserSource : PagingSource<Int, UserData>() {
    override fun getRefreshKey(state: PagingState<Int, UserData>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserData> {
        return try {
            val nextPage = params.key ?: 1
            val userList = RetroInstance.apiService.getUserList(nextPage)
            LoadResult.Page(
                data = userList.data,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (userList.data.isEmpty()) null else userList.page + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}