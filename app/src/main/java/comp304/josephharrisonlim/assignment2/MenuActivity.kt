package comp304.josephharrisonlim.assignment2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import comp304.josephharrisonlim.assignment2.foods.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.menu_vegetableBtn-> {
                startActivity(Intent(this, VegetablesActivity::class.java))
                true
            }
            R.id.menu_fruitsBtn -> {
                startActivity(Intent(this, FruitsActivity::class.java))
                true
            }
            R.id.menu_dairyBtn -> {
                startActivity(Intent(this, DairyActivity::class.java))
                true
            }
            R.id.menu_grainsBtn -> {
                startActivity(Intent(this, GrainsActivity::class.java))
                true
            }
            R.id.menu_meatBtn -> {
                startActivity(Intent(this, MeatsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
