package com.example.documents_rewrite.mainApplication.app.profile

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.invalidateOptionsMenu
import androidx.navigation.fragment.findNavController
import com.example.documents_rewrite.R
import com.example.documents_rewrite.databinding.ProfileFragmentBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import io.getstream.avatarview.coil.loadImage

class ProfileFragment : Fragment() {

    private var _binding: ProfileFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProfileFragmentBinding.inflate(inflater, container,false)
        setHasOptionsMenu(true)


        binding.avatarView.loadImage(
            data = R.drawable.img,
            onError = {
                _, _ ->
                binding.avatarView.avatarInitials = "II"
            }
        )


        initPieChart()
        setDataToPieChart()
        return binding.root

    }
    private fun initPieChart() {
        with(binding) {
            pieChartProfile.setUsePercentValues(true)
            pieChartProfile.description.text = ""
            //hollow pie chart
            pieChartProfile.isDrawHoleEnabled = false
            pieChartProfile.setTouchEnabled(false)
            pieChartProfile.setDrawEntryLabels(false)
            //adding padding
            pieChartProfile.setExtraOffsets(20f, 0f, 20f, 20f)
            pieChartProfile.setUsePercentValues(true)
            pieChartProfile.isRotationEnabled = false
            pieChartProfile.setDrawEntryLabels(false)
            pieChartProfile.legend.orientation = Legend.LegendOrientation.VERTICAL
            pieChartProfile.legend.isWordWrapEnabled = true

        }


    }

    private fun setDataToPieChart() {
        val dataEntries = ArrayList<PieEntry>()
        dataEntries.add(PieEntry(72f, "Android"))
        dataEntries.add(PieEntry(26f, "Ios"))
        dataEntries.add(PieEntry(2f, "Other"))

        val colors: ArrayList<Int> = ArrayList()
        colors.add(Color.parseColor("#4DD0E1"))
        colors.add(Color.parseColor("#FFF176"))
        colors.add(Color.parseColor("#FF8A65"))

        val dataSet = PieDataSet(dataEntries, "")
        val data = PieData(dataSet)

        // In Percentage
        data.setValueFormatter(PercentFormatter())

        with(binding) {
            dataSet.sliceSpace = 3f
            dataSet.colors = colors
            pieChartProfile.data = data
            data.setValueTextSize(15f)
            pieChartProfile.setExtraOffsets(5f, 10f, 5f, 5f)
            pieChartProfile.animateY(1400, Easing.EasingOption.EaseInOutQuad)

            //create hole in center
            pieChartProfile.holeRadius = 58f
            pieChartProfile.transparentCircleRadius = 61f
            pieChartProfile.isDrawHoleEnabled = true
            pieChartProfile.setHoleColor(Color.WHITE)
        }
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.nav_search).setVisible(false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}