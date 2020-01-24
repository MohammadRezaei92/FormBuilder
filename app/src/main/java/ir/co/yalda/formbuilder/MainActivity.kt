package ir.co.yalda.formbuilder

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import ir.co.yalda.formbuilder.entity.Court
import ir.co.yalda.formbuilder.entity.Model
import ir.co.yalda.formbuilder.entity.Sheriff
import ir.co.yalda.formbuilder.formViews.ElementParser
import ir.co.yalda.formbuilder.formViews.ElementsActivityRequestCallback
import ir.co.yalda.formbuilder.utility.Json

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var elementParser: ElementParser
    private lateinit var cameraResult: MutableLiveData<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            if (elementParser.isItemsValid()) {
                val json = GsonBuilder()
                        .setPrettyPrinting()
                        .setLenient()
                        .create()
                        .toJson(elementParser.getResult())
                Log.d("Elements", json)
            }
        }

        getJsonFrom()
    }

    private fun getJsonFrom() {
        val form = Gson().fromJson<Model>(Json.json, Model::class.java)
        elementParser = ElementParser(this, form.data, layoutContainer, object : ElementsActivityRequestCallback {
            override fun requestPermission(permission: String) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(arrayOf(permission), 123)
                }
            }

            override fun onPhotoTaken(result: MutableLiveData<Intent>) {
                cameraResult = result
            }

            override fun courtListNeeded(courtList: MutableLiveData<List<Court>>) {
                val courts = Gson().fromJson<List<Court>>(Json.courts, object : TypeToken<List<Court>>() {}.type)
                courtList.value = courts
            }

            override fun sheriffListNeeded(sheriffList: MutableLiveData<List<Sheriff>>) {
                val sheriffs = Gson().fromJson<List<Sheriff>>(Json.sheriffs, object : TypeToken<List<Sheriff>>() {}.type)
                sheriffList.value = sheriffs
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        cameraResult.value = data
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
