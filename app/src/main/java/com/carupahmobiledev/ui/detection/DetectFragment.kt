package com.carupahmobiledev.ui.detection

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.carupahmobiledev.R
import com.carupahmobiledev.data.repo.DetectRepo
import com.carupahmobiledev.databinding.FragmentDetectBinding
import com.carupahmobiledev.util.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.*

class DetectFragment : Fragment() {
    private lateinit var detectBinding: FragmentDetectBinding
    private lateinit var detectViewModel: DetectViewModel
    private lateinit var detectRepo: DetectRepo
    private var getFile: File? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        detectBinding = FragmentDetectBinding.inflate(inflater, container, false)
        detectRepo = DetectRepo()
        detectViewModel = ViewModelProvider(requireActivity(), DetectViewModelFactory(detectRepo)) [DetectViewModel::class.java]
        return detectBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

        detectBinding.useCam.setOnClickListener { startCameraX() }
        detectBinding.useGallery.setOnClickListener { startGallery() }
        detectBinding.buttonDetect.setOnClickListener {
            uploadImage()
        }
    }
    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        @Suppress("DEPRECATION")
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    requireActivity(),
                    "Tidak mendapatkan permission.",
                    Toast.LENGTH_SHORT
                ).show()
                requireActivity().finish()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    private fun startCameraX() {
        val intent = Intent(requireActivity(), CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERA_X_RESULT) {
            val myFile = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.data?.getSerializableExtra("picture", File::class.java)
            } else {
                @Suppress("DEPRECATION")
                it.data?.getSerializableExtra("picture")
            } as? File

            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean

            myFile?.let { file ->
                rotateFile(file, isBackCamera)
                detectBinding.imgPreview.setImageBitmap(BitmapFactory.decodeFile(file.path))
            }
            getFile = myFile
        }
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val selectedImg = result.data?.data as Uri
            val myFile = uriToFile(selectedImg, requireActivity())
            getFile = myFile
            detectBinding.imgPreview.setImageURI(selectedImg)
        }
    }

    private fun uploadImage() {
        if (getFile != null) {
            val file = reduceFileImage(getFile as File)
            val reqImage = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imageMultiPart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "file", file.name, reqImage
            )
            detectViewModel.detectImg(imageMultiPart)
            detectViewModel.detectImage.observe(requireActivity()){ response ->
                val predictedClass = response.predicted_class
                val intent = Intent(requireActivity(), ResultActivity::class.java)
                intent.putExtra("result", predictedClass)
                intent.putExtra("photo", getFile?.path)
                startActivity(intent)
            }

        } else showToast(requireActivity(), getString(R.string.error))
    }

    companion object {
        const val CAMERA_X_RESULT = 200
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }

}