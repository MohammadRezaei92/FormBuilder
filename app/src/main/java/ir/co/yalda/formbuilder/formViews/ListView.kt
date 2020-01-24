package ir.co.yalda.formbuilder.formViews

import android.view.View
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.transition.TransitionManager
import com.tiper.MaterialSpinner
import ir.co.yalda.formbuilder.R
import ir.co.yalda.formbuilder.entity.*
import kotlinx.android.synthetic.main.list_view.view.*
import java.lang.ref.WeakReference

open class ListView(
        context: AppCompatActivity,
        private val structure: Model.DataItem,
        private val onListItemSelectedCallback: OnListItemSelectedCallback
) : LinearLayout(context), FormView {

    private val activity = WeakReference(context)

    private var selectedItem: Model.ListItem? = null
    private val courtList = MutableLiveData<List<Court>>()
    private val sheriffList = MutableLiveData<List<Sheriff>>()
    private var selectedCourt: Court? = null
    private var selectedSheriff: Sheriff? = null
    private var selectedGps: Pair<Double,Double>? = null

    init {
        View.inflate(context, R.layout.list_view, this)
        setStructure()
    }

    private fun setStructure() {
        spnItems.hint = structure.label
        setItems()
        spnItems.onItemSelectedListener = object : MaterialSpinner.OnItemSelectedListener {
            override fun onItemSelected(parent: MaterialSpinner, view: View?, position: Int, id: Long) {
                selectedItem = structure.list?.get(position)
                onListItemSelectedCallback.onItemSelected(selectedItem?.ignoredStatusQueryJson?.map { it.statusQueryId })
                initViewForSelectedItem()
            }

            override fun onNothingSelected(parent: MaterialSpinner) {
            }
        }
    }

    private fun setItems() {
        val items = mutableListOf<String?>()
        structure.list?.mapTo(items, {
            it.description
        })

        val adapter = ArrayAdapter<String>(context, R.layout.spinner_item, items)
        spnItems.adapter = adapter
    }

    private fun initViewForSelectedItem() {
        inputComment.visibility = View.GONE
        spnCustomAction.visibility = View.GONE
        progressBar.visibility = View.GONE
        when {
            selectedItem?.commentIsNeeded == 1 -> {

                inputComment.visibility = View.VISIBLE
            }
            selectedItem?.gPSIsNeeded == 1 -> {

            }
            selectedItem?.customActionCode?.trim() == "WrongCourt" -> {
                onListItemSelectedCallback.courtListNeeded(courtList)
                initCourtList()
            }
            selectedItem?.customActionCode?.trim() == "WrongSheriff" -> {
                onListItemSelectedCallback.sheriffListNeeded(sheriffList)
                initSheriffList()
            }
        }
    }

    private fun initGps(){
        selectedGps = null

    }

    private fun initCourtList() {
        progressBar.visibility = View.VISIBLE
        selectedCourt = null
        activity.get()?.let {
            courtList.observe(it, Observer<List<Court>> { courts ->
                if (courts.isNotEmpty()) {
                    spnCustomAction.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                    spnCustomAction.hint = "Select right court"

                    val items = mutableListOf<String?>()
                    courts?.mapTo(items, {
                        it.courtName
                    })

                    val adapter = ArrayAdapter<String>(context, R.layout.spinner_item, items)
                    spnCustomAction.adapter = adapter

                    spnCustomAction.onItemSelectedListener = object : MaterialSpinner.OnItemSelectedListener {
                        override fun onItemSelected(parent: MaterialSpinner, view: View?, position: Int, id: Long) {
                            selectedCourt = courts[position]
                        }

                        override fun onNothingSelected(parent: MaterialSpinner) {
                        }
                    }
                }
            })
        }
    }

    private fun initSheriffList() {
        progressBar.visibility = View.VISIBLE
        selectedSheriff = null
        activity.get()?.let {
            sheriffList.observe(it, Observer<List<Sheriff>> { sheriffs ->
                if (sheriffs.isNotEmpty()) {
                    spnCustomAction.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                    spnCustomAction.hint = "Select right sheriff"

                    val items = mutableListOf<String?>()
                    sheriffs?.mapTo(items, {
                        it.sheriffAreaName
                    })

                    val adapter = ArrayAdapter<String>(context, R.layout.spinner_item, items)
                    spnCustomAction.adapter = adapter

                    spnCustomAction.onItemSelectedListener = object : MaterialSpinner.OnItemSelectedListener {
                        override fun onItemSelected(parent: MaterialSpinner, view: View?, position: Int, id: Long) {
                            selectedSheriff = sheriffs[position]
                        }

                        override fun onNothingSelected(parent: MaterialSpinner) {
                        }
                    }
                }
            })
        }
    }

    override fun validate(): Boolean {
        return if (structure.isMandatory == 0) {
            true
        } else {
            if (selectedItem == null) {
                spnItems.error = "This field is mandatory."
                false
            } else {
                if(selectedItem?.gPSIsNeeded == 1){
                    return if (selectedGps == null){
                        spnItems.error = "Gps data not available."
                        false
                    } else{
                        spnItems.error = null
                        true
                    }
                }
                if (selectedItem?.commentIsNeeded == 1){
                    return if(inputComment.editText?.text.toString().isEmpty()){
                        inputComment.error = "This field is mandatory."
                        false
                    } else {
                        inputComment.error = null
                        true
                    }
                }
                return if(spnCustomAction.visibility == View.VISIBLE){
                    if(selectedCourt == null || selectedSheriff == null){
                        spnCustomAction.error = "This field is mandatory."
                        false
                    } else {
                        spnCustomAction.error = null
                        true
                    }
                } else {
                    spnItems.error = null
                    true
                }
            }
        }
    }

    override val elementId: Int = structure.statusQueryId ?: 0

    override val result: ElementResult?
        get() = ElementResult.ListResult(elementId,ListItem(
                id = selectedItem?.statusQueryIssueId,
                text = selectedItem?.description,
                comment = inputComment.editText?.text.toString(),
                customAction = CustomAction(Data(
                        courtId = selectedCourt?.courtId,
                        courtName = selectedCourt?.courtName,
                        sheriffAreaName = selectedSheriff?.sheriffAreaName,
                        sheriffOfficeId = selectedSheriff?.sheriffOfficeId
                ))
        ))

}

interface OnListItemSelectedCallback {
    fun onItemSelected(ignoreViewIds: List<Int?>?)
    fun courtListNeeded(courtList: MutableLiveData<List<Court>>)
    fun sheriffListNeeded(sheriffList: MutableLiveData<List<Sheriff>>)
}