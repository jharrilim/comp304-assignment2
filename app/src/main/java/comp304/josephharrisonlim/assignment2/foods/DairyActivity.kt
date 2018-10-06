package comp304.josephharrisonlim.assignment2.foods

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import comp304.josephharrisonlim.assignment2.data.FoodDatabase
import comp304.josephharrisonlim.assignment2.R
import comp304.josephharrisonlim.assignment2.data.Food
import comp304.josephharrisonlim.assignment2.data.FoodType
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_dairy.*

class DairyActivity : AppCompatActivity() {

    private val dairyItems: Array<Food> = arrayOf(
            Food("Milk", 2.99, FoodType.DAIRY.name),
            Food("Cheddar", 5.99, FoodType.DAIRY.name),
            Food("Yogurt", 4.99, FoodType.DAIRY.name)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dairy)
        dairyItems.map { food ->
            val btn = CheckBox(this)
            btn.text = food.name
            FoodDatabase.getInstance(this)!!.foodDataDao().getAll()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { foods ->
                        for (f in foods) {
                            if (f.name == food.name) {
                                btn.isChecked = true
                            }
                        }
                    }

            btn.setOnCheckedChangeListener { _, isChecked ->
                Single.fromCallable {
                    val dao = FoodDatabase.getInstance(this)!!.foodDataDao()
                    if (isChecked) {
                        dao.insert(food)
                    } else {
                        dao.delete(food)
                    }
                }.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
            }
            dairyLayout.addView(btn)
        }
    }
}
