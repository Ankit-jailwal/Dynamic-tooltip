package com.example.plotline_tooltip.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.plotline_tooltip.data.model.TooltipDataEntity

@Database(entities = arrayOf(TooltipDataEntity::class), version = 1, exportSchema = false)
abstract class TooltipDatabase: RoomDatabase() {

    abstract fun getTooltipDao() : TooltipDao

    companion object{
        private var INSTANCE: TooltipDatabase? = null

        fun getDatabase(context: Context): TooltipDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TooltipDatabase::class.java,
                    "tooltip_database"
                ).build()
                INSTANCE = instance

                instance
            }
        }
    }
}