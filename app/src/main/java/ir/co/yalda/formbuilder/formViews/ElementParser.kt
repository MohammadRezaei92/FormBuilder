package ir.co.yalda.formbuilder.formViews

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.transition.TransitionManager
import ir.co.yalda.formbuilder.entity.*

/**
 * Created by Rezaei_M on 3/6/2018.
 */
class ElementParser(private val activity: AppCompatActivity,
                    elementList: List<Model.DataItem>?,
                    private val containerView: ViewGroup,
                    private val elementsActivityRequestCallback: ElementsActivityRequestCallback) {


    init {
        TransitionManager.beginDelayedTransition(containerView)

        elementList?.forEach {
            when (it.dataType) {
                "String" -> {
                    createString(it)
                }
                "Date" -> {
                    createDate(it)
                }
                "File" -> {
                    createFile(it)
                }
                "List" -> {
                    createDropDown(it)
                }
            }
        }
    }

    fun isItemsValid(): Boolean {
        val listValidation = mutableListOf<Boolean>()
        for (x in 0..containerView.childCount) {
            val element = containerView.getChildAt(x)
            if (element is FormView)
                if (element.visibility == View.VISIBLE)
                    listValidation.add(element.validate())
        }
        return listValidation.all { it }
    }

    fun getResult(): FormResult {
        val formResult = FormResult(unsuccessful = Result(mutableListOf()))
        for (x in 0..containerView.childCount) {
            val element = containerView.getChildAt(x)
            if (element is FormView)
                if (element.visibility == View.VISIBLE)
                    formResult.unsuccessful!!.elements!!.add(element.result)
        }
        return formResult
    }

    private fun hideElement(elementIds: List<Int?>?) {
        elementIds?.let {
            for (x in 0..containerView.childCount) {
                val element = containerView.getChildAt(x)
                if (element is FormView)
                    if (elementIds.contains(element.elementId)) {
                        element.visibility = View.GONE
                    }

            }
        } ?: kotlin.run {
            for (x in 0..containerView.childCount) {
                val element = containerView.getChildAt(x)
                if (element is FormView) {
                    element.visibility = View.VISIBLE
                }

            }
        }
    }

    private fun createString(structure: Model.DataItem) {
        val param = ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val component = TextInputView(activity, structure)
        component.layoutParams = param
        containerView.addView(component)
    }

    private fun createDropDown(structure: Model.DataItem) {
        val param = ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val component = ListView(activity, structure, object : OnListItemSelectedCallback {
            override fun onItemSelected(ignoreViewIds: List<Int?>?) {
                hideElement(ignoreViewIds)
            }

            override fun courtListNeeded(courtList: MutableLiveData<List<Court>>) {
                elementsActivityRequestCallback.courtListNeeded(courtList)
            }

            override fun sheriffListNeeded(sheriffList: MutableLiveData<List<Sheriff>>) {
                elementsActivityRequestCallback.sheriffListNeeded(sheriffList)
            }
        })

        component.layoutParams = param
        containerView.addView(component)
    }

    private fun createDate(structure: Model.DataItem) {
        val param = ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val component = DatePicker(activity, structure)
        component.layoutParams = param
        containerView.addView(component)
    }

    private fun createFile(structure: Model.DataItem) {
        val param = ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val component = FileView(activity, structure, object : FileRequestsCallback {
            override fun requestPermission(permission: String) {
                elementsActivityRequestCallback.requestPermission(permission)
            }

            override fun onPhotoTaken(result: MutableLiveData<Intent>) {
                elementsActivityRequestCallback.onPhotoTaken(result)
            }
        })
        component.layoutParams = param
        containerView.addView(component)
    }
}

interface ElementsActivityRequestCallback {
    fun requestPermission(permission: String)
    fun onPhotoTaken(result: MutableLiveData<Intent>)
    fun courtListNeeded(courtList: MutableLiveData<List<Court>>)
    fun sheriffListNeeded(sheriffList: MutableLiveData<List<Sheriff>>)
}