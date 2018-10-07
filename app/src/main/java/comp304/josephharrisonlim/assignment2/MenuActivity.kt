package comp304.josephharrisonlim.assignment2

import android.content.Intent
import android.os.Bundle
import android.view.View
import comp304.josephharrisonlim.assignment2.foods.*

class MenuActivity : AbstractOptionsMenuActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }

    fun btnClick(view: View) {
        when (view.id) {
            R.id.vegetableBtn-> {
                startActivity(Intent(this, VegetablesActivity::class.java))
            }
            R.id.fruitsBtn -> {
                startActivity(Intent(this, FruitsActivity::class.java))
            }
            R.id.dairyBtn -> {
                startActivity(Intent(this, DairyActivity::class.java))
            }
            R.id.grainsBtn -> {
                startActivity(Intent(this, GrainsActivity::class.java))
            }
            R.id.meatsBtn -> {
                startActivity(Intent(this, MeatsActivity::class.java))
            }
            R.id.checkoutBtn -> {
                startActivity(Intent(this, CheckoutActivity::class.java))
            }
        }
    }
}
