package net.larntech.ankhdls.ui.gallery

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import net.larntech.ankhdls.R
import net.larntech.ankhdls.ui.webview.WebviewActivity
import net.larntech.ankhdls.databinding.FragmentGalleryBinding
import net.larntech.ankhdls.ui.info.InfoActivity
import net.larntech.ankhdls.ui.bars.BarActivity
import net.larntech.ankhdls.ui.delicious.DeliciousActivity
import net.larntech.ankhdls.ui.fashioned.FashionedPieActivity

class GalleryFragment : Fragment(), PieAdapter.ClickedPieListener, OrderAdapter.ClickedBarListener {

    private var _binding: FragmentGalleryBinding? = null
    private lateinit var pieAdapter: PieAdapter;
    private lateinit var orderAdapter: OrderAdapter;

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
        ViewModelProvider(this).get(GalleryViewModel::class.java)
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        intPieData()
        intOrderData()
        clickListener()
        return root
    }

    private fun intPieData(){
        pieAdapter = PieAdapter(this);
        binding.rvPie.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)
        binding.rvPie.adapter = pieAdapter
        loadPieData()
    }

    private fun intOrderData(){
        orderAdapter = OrderAdapter(this);
        binding.rvOrder.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)
        binding.rvOrder.adapter = orderAdapter
        loadOrderData()
    }

    private fun loadPieData(){
        val pieMutableList: MutableList<PieModel> = arrayListOf()
        pieMutableList.add(PieModel(R.drawable.allpies, "Old Fashioned Pies"))
        pieMutableList.add(PieModel(R.drawable.allpies, "Delicious Cakes"))
        pieMutableList.add(PieModel(R.drawable.allbars, "Bars, Candy, Breads & More"))

        pieAdapter.setData(pieMutableList)
    }
    private fun loadOrderData(){
        val pieMutableList: MutableList<PieModel> = arrayListOf()
        pieMutableList.add(PieModel(R.drawable.spbwebsite, "S.P.B Website"))
        pieMutableList.add(PieModel(R.drawable.allpies, "View Pricing"))
        pieMutableList.add(PieModel(R.drawable.orderform, "Online Order Form"))
        pieMutableList.add(PieModel(R.drawable.allbars, "S.P.B on Facebook"))
        pieMutableList.add(PieModel(R.drawable.sugar_pie, "Meet Sugar Pie"))

        orderAdapter.setData(pieMutableList)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun clickListener(){
        binding.llCall.setOnClickListener {
            val i = Intent(Intent.ACTION_DIAL)
            val p = "tel:" + "6877431456"
            i.data = Uri.parse(p)
            startActivity(i)
        }

        binding.llEmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            val data = Uri.parse(
                "mailto:" + "pies@lolassugarpiebakery.com" + "?subject=&body="
            )
            intent.data = data
            startActivity(intent)

        }

        binding.llInfo.setOnClickListener {
            startActivity(Intent(requireContext(), InfoActivity::class.java));

        }
    }

    override fun PieClicked(pie: PieModel) {
        when(pie.text){
            "Old Fashioned Pies"->{
                startActivity(Intent(requireContext(), FashionedPieActivity::class.java))

            }
            "Delicious Cakes"->{
                startActivity(Intent(requireContext(), DeliciousActivity::class.java))

            }
            "Bars, Candy, Breads & More"->{
                startActivity(Intent(requireContext(), BarActivity::class.java))

            }
        }

    }

    private fun showMessage(message: String){
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun BarClicked(pie: PieModel) {
        when(pie.text){
           "S.P.B Website"->{
               viewData("https://lolassugarpiebakery.com/","S.P.B Website")
           }
            "View Pricing"->{
                viewData("https://lolassugarpiebakery.com/goodies", "View Pricing")

           }
            "Online Order Form"->{
                viewData("https://lolassugarpiebakery.com/#contact-section", "Online Order Form")

           }
            "S.P.B on Facebook"->{
                viewData("https://facebook.com/lolassugarpiebakery.com/","S.P.B on Facebook")

           }
            "Meet Sugar Pie"->{
                viewData("https://lolassugarpiebakery.com/stories", "S.P.B on Facebook")

           }

        }
    }

    private fun viewData(url: String, title: String){
        startActivity(Intent(requireContext(), WebviewActivity::class.java).putExtra("url",url).putExtra("title",title))
    }
}