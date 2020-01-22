package ir.co.yalda.formbuilder.entity

import ir.co.yalda.YaldaSpinnerModel

/**
 * Created by Rezaei_M on 2/24/2018.
 */
class SpinnerModel(id:Int, title:String):YaldaSpinnerModel() {


    init {
        this.id = id
        this.title = title
    }
}