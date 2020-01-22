package ir.co.yalda.formbuilder.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class MainForm {

    @SerializedName("MainFormCode")
    @Expose
    var mainFormCode: Int = 0
    @SerializedName("MainFormVersion")
    @Expose
    var mainFormVersion: String = ""
    @SerializedName("MainFormTitle")
    @Expose
    var mainFormTitle: String = ""
    @SerializedName("MainFormEnglishName")
    @Expose
    var mainFormEnglishName: String = ""
    @SerializedName("MainFormSubmitUrl")
    @Expose
    var mainFormSubmitUrl: String = ""
    @SerializedName("MainFormOutputType")
    @Expose
    var mainFormOutputType: String = ""
    @SerializedName("FormsViewMode")
    @Expose
    var formsViewMode: String = ""
    @SerializedName("Forms")
    @Expose
    var forms: List<Form>? = null

    inner class Form {

        @SerializedName("FormCode")
        @Expose
        var formCode: Int = 0
        @SerializedName("FormVersion")
        @Expose
        var formVersion: String = ""
        @SerializedName("FormTitle")
        @Expose
        var formTitle: String = ""
        @SerializedName("FormEnglishName")
        @Expose
        var formEnglishName: String = ""
        @SerializedName("SubmitUrl")
        @Expose
        var submitUrl: String = ""
        @SerializedName("OutputType")
        @Expose
        var outputType: String = ""
        @SerializedName("FormCondition")
        @Expose
        var formCondition: String = ""
        @SerializedName("Elements")
        @Expose
        var elements: List<Element>? = null

    }

    inner class InputList {

        @SerializedName("Text")
        @Expose
        var text: String = ""
        @SerializedName("Value")
        @Expose
        var value: Int = 0

    }

    inner class Style {

        @SerializedName("Dimension")
        @Expose
        var dimension: Dimension? = null
        @SerializedName("Type")
        @Expose
        var type: String = ""
        @SerializedName("BackgroundColor")
        @Expose
        var backgroundColor: String = ""
        @SerializedName("FontColor")
        @Expose
        var fontColor: String = ""
        @SerializedName("Font")
        @Expose
        var font: String = ""
        @SerializedName("FontSize")
        @Expose
        var fontSize: String = ""
        @SerializedName("IconStyle")
        @Expose
        var iconStyle: String = ""
        @SerializedName("Height")
        @Expose
        var height: String = ""
        @SerializedName("TextAlign")
        @Expose
        var textAlign: String = ""
        @SerializedName("Direction")
        @Expose
        var direction: String = ""

    }

    inner class Dimension {

        @SerializedName("PhoneVerticalUnit")
        @Expose
        var phoneVerticalUnit: String = ""
        @SerializedName("PhoneHorizontalUnit")
        @Expose
        var phoneHorizontalUnit: String = ""
        @SerializedName("TabletVerticalUnit")
        @Expose
        var tabletVerticalUnit: String = ""
        @SerializedName("TabletHorizontalUnit")
        @Expose
        var tabletHorizontalUnit: String = ""

    }

    inner class Element {

        @SerializedName("Type")
        @Expose
        var type: String = ""
        @SerializedName("Name")
        @Expose
        var name: String = ""
        @SerializedName("Title")
        @Expose
        var title: String = ""
        @SerializedName("IsUsedAsInputParam")
        @Expose
        var isUsedAsInputParam: Boolean = false
        @SerializedName("DefaultValue")
        @Expose
        var defaultValue: String = ""
        @SerializedName("Validation")
        @Expose
        var validation: Validation? = null
        @SerializedName("Style")
        @Expose
        var style: Style? = null
        @SerializedName("List")
        @Expose
        var list: List<InputList>? = null
        @SerializedName("GetDataUrl")
        @Expose
        var getDataUrl: String = ""
        @SerializedName("SubmitUrl")
        @Expose
        var submitUrl: String = ""
        @SerializedName("OutputType")
        @Expose
        var outputType: String = ""

    }

    inner class Validation{
        @SerializedName("IsRequired")
        @Expose
        var isRequired: Boolean = false
    }

}

