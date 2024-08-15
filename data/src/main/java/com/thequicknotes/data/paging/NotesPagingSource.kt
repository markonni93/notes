package com.thequicknotes.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.thequicknotes.data.dao.NoteDao
import com.thequicknotes.data.uimodel.NoteUiModel

class NotesPagingSource(
  private val noteDao: NoteDao
) : PagingSource<Int, NoteUiModel>() {

  override fun getRefreshKey(state: PagingState<Int, NoteUiModel>): Int? = state.anchorPosition?.let { anchorPosition ->
    val anchorPage = state.closestPageToPosition(anchorPosition)
    anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
  }

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NoteUiModel> {
    val page = params.key ?: 0
    return try {
      val items = noteDao.getAllNotesWithQuery(limit = params.loadSize, offset = page * params.loadSize)

      val data = items.map { entity ->
        NoteUiModel(id = entity.id!!, title = entity.title, description = entity.text, color = entity.color)
      }

      LoadResult.Page(
        data = data,
        prevKey = if (page == 0) null else page - 1,
        nextKey = if (items.isEmpty()) null else page + 1
      )

    } catch (exception: Exception) {
      return LoadResult.Error(exception)
    }
  }

}