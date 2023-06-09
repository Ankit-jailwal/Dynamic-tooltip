//package com.example.plotline_tooltip.data.db
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.example.plotline_tooltip.data.model.TooltipDataEntity
//
//@Database(entities = [TooltipDataEntity::class], version = 1)
//abstract class TooltipDatabase : RoomDatabase() {
//    abstract fun tooltipDataDao(): TooltipDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: TooltipDatabase? = null
//
//        fun getDatabase(context: Context): TooltipDatabase {
//            val tempInstance = INSTANCE
//            if (tempInstance != null) {
//                return tempInstance
//            }
//            synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    TooltipDatabase::class.java,
//                    "tooltip_database"
//                ).build()
//                INSTANCE = instance
//                return instance
//            }
//        }
//    }
//}