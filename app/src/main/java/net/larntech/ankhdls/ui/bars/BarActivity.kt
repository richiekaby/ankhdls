package net.larntech.ankhdls.ui.bars

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
import net.larntech.ankhdls.ui.view_gallery.BarGalleryActivity
import net.larntech.ankhdls.R
import net.larntech.ankhdls.databinding.FragBarsBinding

class BarActivity : AppCompatActivity() {

    private lateinit var binding: FragBarsBinding;
    private lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragBarsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.title = "Bars, Candy, Breads, & More"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true);
        supportActionBar!!.setDisplayShowHomeEnabled(true);

        var pieMutableList = ArrayList<BarModel>()
        pieMutableList.add(BarModel(R.drawable.cookie_wedding_ookies,"Weeding Cookie", "Solid by the dozen"))
        pieMutableList.add(BarModel(R.drawable.bar_vegan_apple,"Vegan Apple Cake", "https://lolassugarpiebakery.com/"))
        pieMutableList.add(BarModel(R.drawable.cake_chocolate_cake,"Chocaramel Pecan", " ( 1/2 or Full Pound )"))
        pieMutableList.add(BarModel(R.drawable.bar_spicy_fudge,"Spicy Chocolate ", " ( 1/2 or Full Pound ) "))
        pieMutableList.add(BarModel(R.drawable.bar_snickerdoodles,"Snickerdoodles ", " ( 1 Dozen )"))
        pieMutableList.add(BarModel(R.drawable.bar_smores,"Smore's Bar ", " (Individual or 1 Dozen)"))
        pieMutableList.add(BarModel(R.drawable.bar_alted_caramel,"Salted Caramel  ", "( 1/2 or Full Pound )"))
        pieMutableList.add(BarModel(R.drawable.bar_pumpkin_cheese,"Splenda Pumpkin  ", "( 1 or Dozen )"))
        pieMutableList.add(BarModel(R.drawable.bar_praline_brownie,"Praline Brown Bites  ", "( 1/2 or Full Pound )"))
        pieMutableList.add(BarModel(R.drawable.bar_peanut_butter,"Peanut Butter Fudge  ", "( 1/2 or Full Pound )"))
        pieMutableList.add(BarModel(R.drawable.bar_oatmeal_apple_cookies,"Oatmeal Apple   ", "( No Sugar Added )"))
        pieMutableList.add(BarModel(R.drawable.bar_oatmeal_apple_cookies,"Oatmeal Raisin   ", "( 1 Dozen )"))
        pieMutableList.add(BarModel(R.drawable.bar_chocolate_chip_cookies,"Chocolate Chip    ", " ( 1 Dozen )"))
        pieMutableList.add(BarModel(R.drawable.bar_alted_caramel,"Edible Chocolate    ", " "))
        pieMutableList.add(BarModel(R.drawable.cake_chocolate_cake,"Build-Your-Own Gift", " ( Custom )"))
        pieMutableList.add(BarModel(R.drawable.bar_banana_bar,"Banana Bread Loaf", "( No Nuts )"))
        pieMutableList.add(BarModel(R.drawable.bar_banana_bar,"Baby Shower Cakes", "( stroller, Rattle )"))
        pieMutableList.add(BarModel(R.drawable.bar_number,"Lemon BAR", "( Individual or 1 Dozen )"))



        customAdapter = CustomAdapter(pieMutableList, this)
        binding.gridView.adapter = customAdapter;
        binding.gridView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->

                startActivity(Intent(this@BarActivity, BarGalleryActivity::class.java)
                    .putExtra("title", "Bars, Candy, Breads, & More")
                    .putExtra("position", position)
                    .putExtra("images", pieMutableList)
                )

            }
    }


    class CustomAdapter(var pieModel: List<BarModel>?, var context: Context) : BaseAdapter() {

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
                convertView = layoutInflater!!.inflate(R.layout.row_bars, null)
            }

            val pieModelVi = pieModel!![position]
            val names = convertView!!.findViewById<TextView>(R.id.tvNamePie)
            val image = convertView.findViewById<ImageView>(R.id.ivPie)
            val desc = convertView.findViewById<TextView>(R.id.tvDescie)
            names.text = pieModelVi.text
            desc.text = pieModelVi.desc
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