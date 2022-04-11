package net.larntech.ankhdls.ui.view_gallery

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import net.larntech.ankhdls.R
import net.larntech.ankhdls.databinding.ActivityBarGalleryBinding
import net.larntech.ankhdls.ui.bars.BarModel

class BarGalleryActivity : AppCompatActivity() {


    private lateinit var binding: ActivityBarGalleryBinding;
    private lateinit var images: MutableList<BarModel>
    private var selectedPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBarGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true);
        supportActionBar!!.setDisplayShowHomeEnabled(true);
        val intent = intent;


        images = intent.getSerializableExtra("images") as ArrayList<BarModel>
        var selectedPosition = intent.getIntExtra("position", 0)

        var myViewPagerAdapter = MyViewPagerAdapter(images,this)
        binding.viewpager.adapter = myViewPagerAdapter
        binding.viewpager.addOnPageChangeListener(viewPagerPageChangeListener)
        setCurrentItem(selectedPosition)
    }

    private fun setCurrentItem(position: Int) {
        binding.viewpager.setCurrentItem(position, false)
        displayMetaInfo(selectedPosition)
    }

    //  adapter
    class MyViewPagerAdapter(var pieModel: List<BarModel>, var context: Context) : PagerAdapter() {
        private var layoutInflater: LayoutInflater? = null
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            layoutInflater = context.getSystemService(LAYOUT_INFLATER_SERVICE)!! as LayoutInflater?
            val image: BarModel = pieModel[position]
            val view: View =
                layoutInflater!!.inflate(R.layout.image_fullscreen_preview, container, false)
            val imageViewPreview = view.findViewById<View>(R.id.image_preview) as ImageView
            imageViewPreview.setImageResource(image.image);
            container.addView(view)
            return view
        }


        override fun getCount(): Int {
            return pieModel.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj as View
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }
    }


    var viewPagerPageChangeListener: ViewPager.OnPageChangeListener = object :
        ViewPager.OnPageChangeListener {
        override fun onPageSelected(position: Int) {
            displayMetaInfo(position)
        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}
        override fun onPageScrollStateChanged(arg0: Int) {}
    }

    private fun displayMetaInfo(position: Int) {
        var title = images[position].text
        supportActionBar!!.title = title
        selectedPosition = position
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId === android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }

}