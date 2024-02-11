package com.example.vishavjit_harika.ui.results

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vishavjit_harika.databinding.FragmentResultsBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.opencsv.CSVReader
import java.io.FileReader
import java.io.IOException


class ResultsFragment : Fragment() {

    private var _binding: FragmentResultsBinding? = null
    private val stressDataList = mutableListOf<StressData>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var stressLevelLineChart: LineChart

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        stressDataList.addAll(readCsvFile())

        val recyclerAdapter = StressDataTableAdapter(stressDataList)
        recyclerView.adapter = recyclerAdapter

        stressLevelLineChart = binding.stressLevelLineChart

        stressLevelLineChart.description.isEnabled = false
        stressLevelLineChart.setTouchEnabled(true)
        stressLevelLineChart.isDragEnabled = true
        stressLevelLineChart.setScaleEnabled(true)
        stressLevelLineChart.setPinchZoom(true)

        val entries = ArrayList<Entry>()
        stressDataList.forEachIndexed { index, stressData ->
            entries.add(Entry(index.toFloat(), stressData.userStress.toFloat()))
        }

        val dataSet = LineDataSet(entries, "Stress Levels")
        dataSet.colors = ColorTemplate.MATERIAL_COLORS.toMutableList()

        val lineData = LineData(dataSet)

        stressLevelLineChart.data = lineData

        stressLevelLineChart.invalidate()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun readCsvFile(): List<StressData> {
        val filePath =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                .toString() + "/stress_timestamp.csv"

        try {
            val fileReader = FileReader(filePath)
            val csvReader = CSVReader(fileReader)

            csvReader.readNext()

            var nextRecord: Array<String>?
            while (csvReader.readNext().also { nextRecord = it } != null) {
                val dateTime = nextRecord!![0]
                val stressValue = nextRecord!![1]
                val stressData = StressData(dateTime, stressValue)
                stressDataList.add(stressData)
            }

            csvReader.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return stressDataList
    }
}
