package ir.co.yalda.formbuilder.formViews

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.provider.MediaStore
import android.util.Base64
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.files.fileChooser
import ir.co.yalda.formbuilder.R
import ir.co.yalda.formbuilder.entity.ChoosenFile
import ir.co.yalda.formbuilder.entity.ElementResult
import ir.co.yalda.formbuilder.entity.Model
import kotlinx.android.synthetic.main.file_view.view.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.lang.ref.WeakReference


class FileView(context: AppCompatActivity,
               private val structure: Model.DataItem,
               private val fileRequestsCallback: FileRequestsCallback
) : LinearLayout(context), FormView {

    private val activity = WeakReference(context)

    private val cameraRequest = 2364
    private val onActivityResult = MutableLiveData<Intent>()
    private var selectedFile: File? = null
    private var takenPhoto: Bitmap? = null

    init {
        View.inflate(context, R.layout.file_view, this)
        setStructure()
    }

    private fun setStructure() {
        txtLabel.text = structure.label
        txtFileExtensions.text = structure.dataTypeSetting?.file?.exnetions

        if (structure.dataTypeSetting?.file?.cameraIsNeeded == true) {
            btnBrowseFile.text = ""
            btnBrowseFile.icon = ContextCompat.getDrawable(context, R.drawable.ic_camera)
            btnBrowseFile.setOnClickListener { takePictureClick.invoke() }
            setupOnActivityResult()
        } else {
            btnBrowseFile.setOnClickListener { filePickerClick.invoke() }
        }
    }

    private val filePickerClick = {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                MaterialDialog(context).show {
                    fileChooser { dialog, file ->
                        selectedFile = file
                        validate()
                    }
                }
            } else {
                fileRequestsCallback.requestPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        } else {
            MaterialDialog(context).show {
                fileChooser { dialog, file ->
                    selectedFile = file
                    validate()
                }
            }
        }

    }

    private val takePictureClick = {
        fileRequestsCallback.onPhotoTaken(onActivityResult)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(context, cameraIntent, cameraRequest, null)
            } else {
                fileRequestsCallback.requestPermission(android.Manifest.permission.CAMERA)
            }
        } else {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(context, cameraIntent, cameraRequest, null)
        }
    }

    override fun validate(): Boolean {
        errors.clear()
        return if (structure.isMandatory == 0) {
            true
        } else {
            if (selectedFile == null && takenPhoto == null) {
                setError("This field is mandatory.")
                false
            } else {
                val acceptableExtensions = structure.dataTypeSetting?.file?.exnetions
                        ?.split(",")?.apply { forEach { it.trim() } }
                val maxFileSize = structure.dataTypeSetting?.file?.maxSize?.toLong() ?: 0
                val minFileSize = structure.dataTypeSetting?.file?.minSize?.toLong() ?: 0
                if (takenPhoto == null && acceptableExtensions?.contains(selectedFile!!.extension) == false) {
                    setError("File extension is not acceptable.")
                    return false
                }
                if (takenPhoto == null && ((selectedFile!!.totalSpace < minFileSize) && (selectedFile!!.totalSpace > maxFileSize))) {
                    setError("File is too large or too small.")
                    return false
                }
                true
            }
        }
    }

    private val errors = mutableListOf<String>()
    private fun setError(error: String) {
        if (!errors.contains(error))
            errors.add(error)
        txtError.text = errors.joinToString(", ")
    }

    override val elementId: Int = structure.statusQueryId ?: 0

    override val result: ElementResult?
        get() = ElementResult.FileResult(elementId,
                if(selectedFile != null)
                ChoosenFile(selectedFile?.extension,
                        selectedFile?.readBytes()?.toBase64(),
                        selectedFile?.totalSpace?.toInt(),
                        selectedFile?.name)
        else
        ChoosenFile("jpg",
                takenPhoto?.toByteArray()?.toBase64(),
                takenPhoto?.toByteArray()?.size,
                System.currentTimeMillis().toString()))

    private fun setupOnActivityResult(){
        activity.get()?.let { activity ->
            onActivityResult.observe(activity, Observer<Intent> {
                takenPhoto = it?.extras?.get("data") as Bitmap
                validate()
            })
        }
    }


}

interface FileRequestsCallback {
    fun requestPermission(permission: String)
    fun onPhotoTaken(result: MutableLiveData<Intent>)
}

fun ByteArray.toBase64():String? = null
//    Base64.encodeToString(this,Base64.NO_WRAP)

fun Bitmap.toByteArray():ByteArray {
    val stream = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 100, stream)
    return stream.toByteArray()
}