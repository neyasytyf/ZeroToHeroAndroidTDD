package ru.easycode.zerotoheroandroidtdd

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items_table")
data class ItemCache(
    @PrimaryKey val id: Long,
    @ColumnInfo val text: String
)