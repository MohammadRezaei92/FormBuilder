package ir.co.yalda.formbuilder.utility

import android.content.Context
import android.text.InputType
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import ir.co.yalda.YaldaMaterialSpinner
import ir.co.yalda.formbuilder.entity.MainForm
import ir.co.yalda.formbuilder.entity.SpinnerModel

/**
 * Created by Rezaei_M on 3/6/2018.
 */
class ElementParser(private val context: Context, private val element: MainForm.Element) {

    enum class ElementType{Numeric,Dropdown,Checkbox,TextArea,Submit}


    fun getComponent(): View?{
        return when(element.type){
            ElementType.Numeric.name -> createNumeric()
            ElementType.Dropdown.name -> createDropDown()
            ElementType.Checkbox.name -> createCheckBox()
            ElementType.TextArea.name -> createTextArea()
            ElementType.Submit.name -> createSubmit()
            else -> null
        }
    }

    private fun createNumeric(): EditText{
        val param = ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
        val component = EditText(context)
        component.inputType = InputType.TYPE_CLASS_NUMBER
        component.tag = element.name
        component.hint = element.title
        component.setText(element.defaultValue)
        component.layoutParams = param
        return component
    }

    private fun createDropDown(): YaldaMaterialSpinner{
        val param = ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
        val component = YaldaMaterialSpinner(context)
        component.tag = element.name
        component.hint = element.title

        val items: ArrayList<SpinnerModel> = arrayListOf()
        element.list?.forEach {
            items.add(SpinnerModel(it.value,it.text))
        }
        component.setDataSource(items)
        component.setSelectionByID(element.defaultValue.toInt())
        component.layoutParams = param
        return component
    }

    private fun createCheckBox(): CheckBox{
        val param = ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
        val component = CheckBox(context)
        component.tag = element.name
        component.text = element.title
        component.isChecked = element.defaultValue.toBoolean()
        component.layoutParams = param
        return component
    }

    private fun createTextArea(): EditText{
       val component = createNumeric()
        component.inputType = InputType.TYPE_CLASS_TEXT
        component.setLines(5)
        return component
    }

    private fun createSubmit(): Button{
        val param = ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
        val component = Button(context)
        component.tag = element.name
        component.text = element.title
        component.layoutParams = param
        return component
    }

    fun getDimension():Pair<Int,Int>{
        when(utils.getDeviceDimensionMode(context)){
            utils.DeviceDimensionMode.PhoneVertical -> {
                val dimension = element.style?.dimension?.phoneVerticalUnit
                dimension?.let {
                    val values = it.split(":")
                    return Pair(values[0].trim().toInt().also { if(it>0) it.minus(1) else it }
                            ,values[1].trim().toInt())
                }

            }
            utils.DeviceDimensionMode.PhoneHorizontal -> {
                val dimension = element.style?.dimension?.phoneHorizontalUnit
                dimension?.let {
                    val values = it.split(":")
                    return Pair(values[0].trim().toInt().also { if(it>0) it.minus(1) else it }
                            ,values[1].trim().toInt())
                }

            }
            utils.DeviceDimensionMode.TabletVertical -> {
                val dimension = element.style?.dimension?.tabletVerticalUnit
                dimension?.let {
                    val values = it.split(":")
                    return Pair(values[0].trim().toInt().also { if(it>0) it.minus(1) else it }
                            ,values[1].trim().toInt())
                }

            }
            utils.DeviceDimensionMode.TabletHorizontal -> {
                val dimension = element.style?.dimension?.tabletHorizontalUnit
                dimension?.let {
                    val values = it.split(":")
                    return Pair(values[0].trim().toInt().also { if(it>0) it.minus(1) else it }
                            ,values[1].trim().toInt())
                }

            }
        }
        return Pair(0,0)
    }
}