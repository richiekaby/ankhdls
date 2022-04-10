package net.larntech.ankhdls.ui.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.larntech.ankhdls.R

class OrderAdapter (val clicklistener: ClickedBarListener)
    : RecyclerView.Adapter<OrderAdapter.OrderAdapterVh>() {

    var pieModel = listOf<PieModel>()

    fun setData(pieModel: List<PieModel>){
        this.pieModel = pieModel
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderAdapterVh {
        return OrderAdapterVh(
            LayoutInflater.from(parent.context).inflate(R.layout.row_main_order_galley,parent,false)
        )
    }

    override fun onBindViewHolder(holder: OrderAdapterVh, position: Int) {
        var pie = pieModel[position]

        holder.tvName.text = pie.text
        holder.imageView.setImageResource(pie.image);
        holder.itemView.setOnClickListener {
            clicklistener.BarClicked(pie)
        }
    }

    override fun getItemCount(): Int {
        return pieModel.size
    }

    interface ClickedBarListener {
        fun BarClicked(pie: PieModel)
    }

    class OrderAdapterVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView = itemView.findViewById<ImageView>(R.id.imView)
        var tvName = itemView.findViewById<TextView>(R.id.tvName)

    }


}