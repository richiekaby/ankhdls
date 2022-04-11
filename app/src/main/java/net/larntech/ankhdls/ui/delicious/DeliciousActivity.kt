package net.larntech.ankhdls.ui.delicious

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
class DeliciousActivity : AppCompatActivity() {

    private lateinit var binding: FragBarsBinding;
    private lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragBarsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.title = "Delicious Cake"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true);
        supportActionBar!!.setDisplayShowHomeEnabled(true);
        var pieMutableList = ArrayList<PieModel>()
        pieMutableList.add(PieModel(R.drawable.cake_coconut_cake_sliced,"Coconut Cake"))
        pieMutableList.add(PieModel(R.drawable.cake_tres_leches_cake,"Tres Leches Cake"))
        pieMutableList.add(PieModel(R.drawable.cake_strawberry_cake_ps,"Strawberry Cake"))
        pieMutableList.add(PieModel(R.drawable.cake_red_velvet_cake,"Red Velvet Cake"))
        pieMutableList.add(PieModel(R.drawable.cake_raspberry_chocolate_cake,"Raspberry Chocolate"))
        pieMutableList.add(PieModel(R.drawable.cake_coconut_cake_sliced,"Classic Pound Cake"))
        pieMutableList.add(PieModel(R.drawable.cake_pineapple_upside,"Pineapple Upside"))
        pieMutableList.add(PieModel(R.drawable.cake_orange_flower_wedding,"Custom Wedding"))
        pieMutableList.add(PieModel(R.drawable.cake_vr,"Vegan Pear Crumb"))
        pieMutableList.add(PieModel(R.drawable.cake_guinness,"Guiness Beer Cake"))
        pieMutableList.add(PieModel(R.drawable.cake_german_chocolate,"German Chocolate"))
        pieMutableList.add(PieModel(R.drawable.cake_red_velvet_cake,"Splenda Cream"))
        pieMutableList.add(PieModel(R.drawable.cake_chocolate_cake,"Chocolate Cake"))
        pieMutableList.add(PieModel(R.drawable.cake_chocolate,"Chocolate Pound"))
        pieMutableList.add(PieModel(R.drawable.cake_cheesecake_whole_ps,"Cheesecake"))
        pieMutableList.add(PieModel(R.drawable.cake_caramel_cake_ps,"Caramel Cake"))
        pieMutableList.add(PieModel(R.drawable.cake_african_american_doll_pink,"Doll Cakes"))
        pieMutableList.add(PieModel(R.drawable.cake_caramel_cake_ps,"Birthday Cakes"))



        customAdapter = CustomAdapter(pieMutableList,this)
        binding.gridView.adapter = customAdapter;
        binding.gridView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                startActivity(
                    Intent(this@DeliciousActivity, GalleryActivity::class.java)
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
                convertView = layoutInflater!!.inflate(R.layout.row_delicious, null)
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