package ir.co.yalda.formbuilder.formViews

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import ir.co.yalda.formbuilder.R
import ir.co.yalda.formbuilder.entity.ElementResult
import ir.co.yalda.formbuilder.entity.Model
import kotlinx.android.synthetic.main.string_view.view.*

open class TextInputView(context: Context?, private val structure: Model.DataItem) : LinearLayout(context), FormView {

    init {
        View.inflate(context, R.layout.string_view, this)
        setStructure()
    }

    private fun setStructure() {
        inputText.hint = structure.label
    }

    override fun validate(): Boolean {
        return if (structure.isMandatory == 0) {
            true
        } else {
            if (inputText.editText?.text?.isEmpty() == true) {
                inputText.error = "This field is mandatory."
                false
            } else {
                inputText.error = null
                true
            }
        }
    }

    override val elementId: Int = structure.statusQueryId ?: 0

    override val result: ElementResult?
        get() = ElementResult.StringResult(structure.statusQueryId,
                inputText.editText?.text.toString())
}