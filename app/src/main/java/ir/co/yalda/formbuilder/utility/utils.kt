package ir.co.yalda.formbuilder.utility

import android.content.Context
import android.content.res.Configuration
import ir.co.yalda.formbuilder.R

/**
 * Created by Rezaei_M on 3/6/2018.
 */
object utils {

    enum class DeviceDimensionMode{PhoneVertical,PhoneHorizontal,TabletVertical,TabletHorizontal}

    fun getDeviceDimensionMode(context: Context): DeviceDimensionMode{
        val resources = context.resources
        val isTablet = resources.getBoolean(R.bool.isTablet)
        val isLandscape = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

        return if (isTablet){
            if(isLandscape){
                DeviceDimensionMode.TabletHorizontal
            } else {
                DeviceDimensionMode.TabletVertical
            }
        } else{
            if(isLandscape){
                DeviceDimensionMode.PhoneHorizontal
            } else {
                DeviceDimensionMode.PhoneVertical
            }
        }
    }


}