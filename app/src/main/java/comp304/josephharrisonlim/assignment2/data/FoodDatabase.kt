package comp304.josephharrisonlim.assignment2.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Food::class], version = 1)
abstract class FoodDatabase: RoomDatabase() {
    abstract fun foodDataDao(): FoodDAO

    companion object {
        private var instance: FoodDatabase? = null

        fun getInstance(context: Context): FoodDatabase? {
            if (instance == null) {
                synchronized(FoodDatabase::class) {
                    instance = Room.databaseBuilder(context.applicationContext,
                            FoodDatabase::class.java, "food.db")
                            .build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }
}
