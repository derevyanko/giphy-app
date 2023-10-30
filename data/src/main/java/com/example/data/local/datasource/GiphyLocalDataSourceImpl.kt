package com.example.data.local.datasource

import com.example.data.local.dao.DeletedGiphyDao
import com.example.data.local.dao.GiphySearchDao
import com.example.data.local.entities.DeletedGiphyIdEntity
import com.example.data.local.entities.GiphySearchDbEntity
import javax.inject.Inject

class GiphyLocalDataSourceImpl @Inject constructor(
    private val searchDao: GiphySearchDao,
    private val deletedDao: DeletedGiphyDao
): GiphyLocalDataSource {

    override fun getGiphyBySearch(search: String) = searchDao.get(search)

    override fun saveGiphyBySearch(giphy: List<GiphySearchDbEntity>) {
        searchDao.save(giphy)
    }

    override fun deleteGiphy(id: String) {
        searchDao.delete(id)
    }

    override fun getDeletedGiphy() = deletedDao.get()

    override fun addDeletedGiphy(giphy: DeletedGiphyIdEntity) {
        deletedDao.add(giphy)
    }
}