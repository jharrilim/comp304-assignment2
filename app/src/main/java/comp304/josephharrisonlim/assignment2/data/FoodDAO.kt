package comp304.josephharrisonlim.assignment2.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface FoodDAO {

    @Query("SELECT * FROM foodData")
    fun getAll(): Flowable<List<Food>>

    @Query("SELECT * FROM foodData WHERE name = :name")
    fun getByName(name: String): Single<Food>

    @Insert(onConflict = REPLACE)
    fun insert(food: Food)

    @Delete
    fun delete(food: Food)
}