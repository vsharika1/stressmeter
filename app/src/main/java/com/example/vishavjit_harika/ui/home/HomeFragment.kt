package com.example.vishavjit_harika.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.vishavjit_harika.MainActivity
import com.example.vishavjit_harika.R
import com.example.vishavjit_harika.SubmitImageActivity
import com.example.vishavjit_harika.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var mGridNumber: Int = 0
    private var startImageActivityForResult: ActivityResultLauncher<Intent>? = null
    var activityMain = MainActivity()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val mGridView = root.findViewById<GridView>(R.id.images_gridView)
        val imageAdapter = ImageGridAdapter(requireActivity(), mGridNumber)
        mGridView.adapter = imageAdapter

        startImageActivityForResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                Log.v("Result", "Result OK")
            }
        }

        mGridView.setOnItemClickListener { parent, v, position, id ->
            val stressLevelIntent = Intent(requireActivity(), SubmitImageActivity::class.java)
            stressLevelIntent.putExtra("position", position.toString())
            stressLevelIntent.putExtra("grid_number", mGridNumber.toString())
            startImageActivityForResult?.launch(stressLevelIntent)
        }

        val mMoreImagesButton = root.findViewById<Button>(R.id.more_images_button)
        mMoreImagesButton.setOnClickListener {
            mGridNumber = (mGridNumber + 1) % 3
            val newImageAdapter = ImageGridAdapter(requireActivity(), mGridNumber)
            mGridView.adapter = newImageAdapter
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}