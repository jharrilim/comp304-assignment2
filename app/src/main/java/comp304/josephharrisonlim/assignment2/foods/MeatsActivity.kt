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
import kotlinx.android.synthetic.main.activity_meats.*

class MeatsActivity : AbstractOptionsMenuActivity() {

    private val meatItems: Array<Food> = arrayOf(
            Food("Chicken Wings", 9.00, FoodType.MEATS.name),
            Food("Striploin Steaks", 12.00, FoodType.MEATS.name),
            Food("Bacon", 5.00, FoodType.MEATS.name)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meats)
        meatItems.map { food ->
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
            meatsLayout.addView(btn)
        }
    }
}
