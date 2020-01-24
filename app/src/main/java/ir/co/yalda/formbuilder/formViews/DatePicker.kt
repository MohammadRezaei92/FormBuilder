package ir.co.yalda.formbuilder.formViews

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import ir.co.yalda.formbuilder.entity.Model
import kotlinx.android.synthetic.main.string_view.view.*
import java.util.*

class DatePicker(context: Context?, structure: Model.DataItem)
    : TextInputView(context, structure), DatePickerDialog.OnDateSetListener {

    init {
        initDatePicker()
    }

    private fun initDatePicker(){
        val date = Calendar.getInstance()
        inputText.editText?.setOnClickListener {
            DatePickerDialog(context,this,
                    date.get(Calendar.YEAR),
                    date.get(Calendar.MONTH),
                    date.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun disableEditable(){

    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        inputText.editText?.setText("$year/${month.plus(1)}/$day")
    }
}