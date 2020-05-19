package com.`in`.sbnri


import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.`in`.sbnri.databinding.ItemBinding
import com.`in`.sbnri.entities.MainEntity
import java.util.*


public class AdapterRecyler(private val context: Context) :
    RecyclerView.Adapter<AdapterRecyler.ItemHolder>() {
    private var list: MutableList<MainEntity>? = null
    internal var activity: Activity? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterRecyler.ItemHolder {

        val binding = DataBindingUtil.inflate<ItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item,
            parent,
            false
        )
        return AdapterRecyler.ItemHolder(binding)

    }


    override fun onBindViewHolder(holder: AdapterRecyler.ItemHolder, position: Int) {
        val data = list!![position]
        holder.binding.nameTv.text = data.name
        holder.binding.descTv.text = data.description
        if (data.license != null && data.license!!.name != null)
            holder.binding.licenseTv.text = data.license!!.name
        /* holder.binding.permissions.text = data.permissions.pull*/
        holder.binding.openIssuesCountTv.text = data.openIssuesCount.toString()
    }


    override fun getItemCount(): Int {
        return if (list != null)
            list!!.size
        else
            0
    }

    fun setData(lists: List<MainEntity>?, position: Int) {
        this.list = lists!! as MutableList<MainEntity>
        notifyItemRangeInserted(position, list!!.size)
        Toast.makeText(context, list!!.size.toString(), Toast.LENGTH_SHORT).show()


    }

    class ItemHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)


}
