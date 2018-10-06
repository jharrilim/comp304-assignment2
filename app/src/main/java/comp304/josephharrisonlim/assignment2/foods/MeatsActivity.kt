package comp304.josephharrisonlim.assignment2.foods

import android.os.Bundle
import comp304.josephharrisonlim.assignment2.AbstractOptionsMenuActivity
import comp304.josephharrisonlim.assignment2.R

class MeatsActivity : AbstractOptionsMenuActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meats)
    }
}
