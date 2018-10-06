package comp304.josephharrisonlim.assignment2.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "foodData")
data class Food(
        @PrimaryKey() var name: String,
        var price: Double,
        var type: String
)

