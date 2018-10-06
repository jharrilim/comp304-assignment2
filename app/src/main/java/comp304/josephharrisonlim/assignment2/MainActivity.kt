package comp304.josephharrisonlim.assignment2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    protected fun enterBtnClicked(view: View?) {
        if (view == null) return
        startActivity(Intent(this, MenuActivity::class.java))
    }
}
