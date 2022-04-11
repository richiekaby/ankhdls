package net.larntech.ankhdls.ui.fashioned

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.larntech.ankhdls.ui.view_gallery.GalleryActivity
import net.larntech.ankhdls.R
import net.larntech.ankhdls.databinding.FragBarsBinding
import net.larntech.ankhdls.ui.gallery.PieModel

class FashionedPieActivity : AppCompatActivity() {

    private lateinit var binding: FragBarsBinding;
    private lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragBarsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.title = "Ord Fashioned Pies"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true);
        supportActionBar!!.setDisplayShowHomeEnabled(true);
        var pieMutableList = ArrayList<PieModel>()
        pieMutableList.add(PieModel(R.drawable.sweet_potato_pie_ps,"Sweet Potato Pie"))
        pieMutableList.add(PieModel(R.drawable.sugar_pie,"Sugar Pie"))
        pieMutableList.add(PieModel(R.drawable.strawberry_rhubarb,"Strawbelly Rhubarb"))
        pieMutableList.add(PieModel(R.drawable.pumpkin,"PumpKin"))
        pieMutableList.add(PieModel(R.drawable.pecan_pie_pd,"Pecan Pie"))
        pieMutableList.add(PieModel(R.drawable.pie_peachpie,"Peach Pie"))
        pieMutableList.add(PieModel(R.drawable.pie_cupcake_pies,"Cupcake Pies"))
        pieMutableList.add(PieModel(R.drawable.pie_coconut_custard_pie_ps,"Coconut Custard Pie"))
        pieMutableList.add(PieModel(R.drawable.pie_chocolate,"Chocolate Chip"))
        pieMutableList.add(PieModel(R.drawable.pie_buttermilk_chess_pie,"Buttermilk Chess Pie"))
        pieMutableList.add(PieModel(R.drawable.pie_blueberry_ps,"Bluebelly Pie"))
        pieMutableList.add(PieModel(R.drawable.pie_banana_pudding,"Banana Pudding "))
        pieMutableList.add(PieModel(R.drawable.peachpie,"Peach Cobbler"))
        customAdapter = CustomAdapter(pieMutableList, this)
        binding.gridView.adapter = customAdapter;
        binding.gridView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->

                startActivity(
                    Intent(this@FashionedPieActivity, GalleryActivity::class.java)
                        .putExtra("title", "Delicious Cake")
                        .putExtra("position", position)
                        .putExtra("images", pieMutableList)
                )
            }
    }

    class CustomAdapter(var pieModel: List<PieModel>?, var context: Context) : BaseAdapter() {

        private var layoutInflater: LayoutInflater? = null


        override fun getCount(): Int {
            return pieModel!!.size
        }

        override fun getItem(position: Int): Any {
            return pieModel!![position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

            var convertView = convertView
            if (layoutInflater == null) {
                layoutInflater =
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            }
            if (convertView == null) {
                convertView = layoutInflater!!.inflate(R.layout.row_pie, null)
            }

            val pieModelVi = pieModel!![position]
            val names = convertView!!.findViewById<TextView>(R.id.tvNamePie)
            val image = convertView.findViewById<ImageView>(R.id.ivPie)
            names.text = pieModelVi.text
            image.setImageResource(pieModelVi.image);

            return convertView
        }



    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId === android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }

}