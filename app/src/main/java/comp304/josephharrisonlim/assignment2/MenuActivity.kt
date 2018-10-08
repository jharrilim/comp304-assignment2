package comp304.josephharrisonlim.assignment2

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.View
import android.widget.TextView
import comp304.josephharrisonlim.assignment2.data.Food
import comp304.josephharrisonlim.assignment2.data.FoodDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.Single
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AbstractOptionsMenuActivity(), ClearCartFragment.NoticeDialogListener {


    private var cost: Double = 0.0
    private var disposable: CompositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val disp = FoodDatabase.getInstance(this)!!.foodDataDao().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { foods ->
                    refresh()
                    for (food in foods) {
                        fillLayout(food)
                        cost += food.price
                    }
                    totalCostTxt.setText("Total: $%.2f".format(cost))
                }
        this.disposable.add(disp)
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        val disp = Single.fromCallable {
            FoodDatabase.getInstance(this)!!.foodDataDao().deleteAllFood()
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        this.disposable.add(disp)
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    fun openClearCartDialog(view: View) {
        when (view.id) {
            R.id.clearCartBtn -> {
                val fragment = ClearCartFragment()
                fragment.show(supportFragmentManager, "Clear Cart")
            }
        }
    }

    fun goToPaymentScreen(view: View) {
        when (view.id) {
            R.id.proceedToPaymentBtn -> {
                startActivity(Intent(this, PaymentActivity::class.java))
            }
        }
    }


    private fun refresh() {
        cost = 0.0
        checkoutItemNamesLayout.removeAllViews()
        checkoutItemPricesLayout.removeAllViews()
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
