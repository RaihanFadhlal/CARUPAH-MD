package com.carupahmobiledev.data.local.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.carupahmobiledev.data.remote.response.ChatbotResponse

/*@Dao
interface ChatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addChat(listChatItem: ChatbotResponse)

    @Query("SELECT * FROM stories ORDER BY createdAt ASC")
    fun getStory(): PagingSource<Int, ChatbotResponse>

}*/