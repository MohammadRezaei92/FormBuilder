package ir.co.yalda.formbuilder

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.gson.Gson
import ir.co.yalda.formbuilder.entity.MainForm
import ir.co.yalda.formbuilder.utility.ElementParser
import ir.co.yalda.formbuilder.utility.Json

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.row_view.*
import kotlinx.android.synthetic.main.row_view.view.*

class MainActivity : AppCompatActivity() {

    private var rowWeight = 6
    private lateinit var row: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        getJsonFrom()
    }

    private fun getJsonFrom(){
        val form = Gson().fromJson<MainForm>(Json.json,MainForm::class.java)

        row = layoutInflater.inflate(R.layout.row_view,null,false) as ViewGroup
        form.forms?.get(0)?.elements?.forEach {
            addElement(it)
        }
        layoutContainer.addView(row)
    }

    private fun addElement(element: MainForm.Element){
        ElementParser(this,element).let {
            if(it.getDimension().second <= rowWeight.minus(it.getDimension().first)){
                when(it.getDimension().first){
                    0 -> {
                        for(x in 0..row.childCount.minus(1)){
                            if((row.getChildAt(x) as ViewGroup).childCount == 0){
                                (row.getChildAt(x) as ViewGroup).addView(it.getComponent())
                                val param = row.getChildAt(x).layoutParams as LinearLayout.LayoutParams
                                param.weight = it.getDimension().second.toFloat()
                                row.getChildAt(x).requestLayout()
                                break
                            }
                        }
                    }
                    1 -> {
                        row.cell1.addView(it.getComponent())
                        val param = row.cell1.layoutParams as LinearLayout.LayoutParams
                        param.weight = it.getDimension().second.toFloat()
                        row.cell1.requestLayout()
                    }
                    2 -> {
                        row.cell2.addView(it.getComponent())
                        val param = row.cell2.layoutParams as LinearLayout.LayoutParams
                        param.weight = it.getDimension().second.toFloat()
                        row.cell2.requestLayout()
                    }
                    3 -> {
                        row.cell3.addView(it.getComponent())
                        val param = row.cell3.layoutParams as LinearLayout.LayoutParams
                        param.weight = it.getDimension().second.toFloat()
                        row.cell3.requestLayout()
                    }
                    4 -> {
                        row.cell4.addView(it.getComponent())
                        val param = row.cell4.layoutParams as LinearLayout.LayoutParams
                        param.weight = it.getDimension().second.toFloat()
                        row.cell4.requestLayout()
                    }
                    5 -> {
                        row.cell5.addView(it.getComponent())
                        val param = row.cell5.layoutParams as LinearLayout.LayoutParams
                        param.weight = it.getDimension().second.toFloat()
                        row.cell5.requestLayout()
                    }
                    6 -> {
                        row.cell6.addView(it.getComponent())
                        val param = row.cell6.layoutParams as LinearLayout.LayoutParams
                        param.weight = it.getDimension().second.toFloat()
                        row.cell6.requestLayout()
                    }
                }
                rowWeight -= it.getDimension().second.plus(it.getDimension().first)
            } else {
                layoutContainer.addView(row)
                rowWeight = 6
                row = layoutInflater.inflate(R.layout.row_view,null,false) as ViewGroup
                addElement(element)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
