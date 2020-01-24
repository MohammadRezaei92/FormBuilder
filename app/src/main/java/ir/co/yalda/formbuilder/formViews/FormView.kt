package ir.co.yalda.formbuilder.formViews

import ir.co.yalda.formbuilder.entity.ElementResult

interface FormView {
    fun validate():Boolean
    val elementId: Int
    val result: ElementResult?
}