package net.larntech.ankhdls.ui.info

import android.R
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import net.larntech.ankhdls.databinding.ActivityInfoBinding

class InfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.title = "Info"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true);
        supportActionBar!!.setDisplayShowHomeEnabled(true);

        clickListener()
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

        binding.llWebsite.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://lolassugarpiebakery.com/"))
            startActivity(browserIntent)
        }

        binding.llPin.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://lolassugarpiebakery.com/"))
            startActivity(browserIntent)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId === R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }

}