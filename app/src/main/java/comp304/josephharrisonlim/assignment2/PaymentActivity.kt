package comp304.josephharrisonlim.assignment2

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_payment.*

class PaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
    }

    fun proceedToOrder(view: View) {
        when (view.id) {
            R.id.proceedToFinalOrderBtn -> {
                val bundle = Bundle()
                when (paymentOptionGroup.checkedRadioButtonId) {
                    0 -> {
                        bundle.putInt("T_PYMT", 0)
                        return startActivity(Intent(this, FinalOrderActivity::class.java))
                    }
                    1 -> {
                        bundle.putInt("T_PYMT", 1)
                    }
                    2 -> {
                        bundle.putInt("T_PYMT", 2)
                    }
                }
                startActivity(Intent(this, PaymentInformationActivity::class.java), bundle)
            }
        }
    }
}
