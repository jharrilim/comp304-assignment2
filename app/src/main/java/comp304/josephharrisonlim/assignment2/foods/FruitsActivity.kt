package comp304.josephharrisonlim.assignment2.foods

import android.os.Bundle
import android.widget.CheckBox
import comp304.josephharrisonlim.assignment2.AbstractOptionsMenuActivity
import comp304.josephharrisonlim.assignment2.R
import comp304.josephharrisonlim.assignment2.data.Food
import comp304.josephharrisonlim.assignment2.data.FoodDatabase
import comp304.josephharrisonlim.assignment2.data.FoodType
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_fruits.*

class FruitsActivity : AbstractOptionsMenuActivity() {

    private val fruitItems: Array<Food> = arrayOf(
            Food("Apple", 0.99, FoodType.FRUITS.name),
            Food("Orange", 1.29, FoodType.FRUITS.name),
            Food("Banana", 1.19, FoodType.FRUITS.name)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruits)
        fruitItems.map { food ->
            val btn = CheckBox(this)
            btn.text = food.name
            FoodDatabase.getInstance(this)!!.foodDataDao().getAll()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { foods ->
                        for (f in foods) {
                            if (f == food) {
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
            fruitsLayout.addView(btn)
        }
    }
}
