package comp304.josephharrisonlim.assignment2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import comp304.josephharrisonlim.assignment2.data.Food
import comp304.josephharrisonlim.assignment2.data.FoodDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_checkout.*

class CheckoutActivity : AppCompatActivity() {

    private var cost: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        FoodDatabase.getInstance(this)!!.foodDataDao().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { foods ->
                    for (food in foods) {
                        fillLayout(food)
                        cost += food.price
                    }
                    totalCostTxt.setText("Total: $${cost}")
                }
    }

    private fun fillLayout(food: Food) {
        val nameTv = TextView(this)
        nameTv.text = food.name
        checkoutItemNamesLayout.addView(nameTv)

        val costTv = TextView(this)
        costTv.text = "$ ${food.price}"
        checkoutItemPricesLayout.addView(costTv)
    }
}
