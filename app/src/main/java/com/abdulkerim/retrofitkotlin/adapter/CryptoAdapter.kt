package com.abdulkerim.retrofitkotlin.adapter

import android.graphics.Color
import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdulkerim.retrofitkotlin.databinding.RowLayoutBinding
import com.abdulkerim.retrofitkotlin.model.CryptoModel



class CryptoAdapter(private var cryptoModels:ArrayList<CryptoModel>): RecyclerView.Adapter<CryptoAdapter.CryptoHolder>() {
    private val colors : Array<String> = arrayOf("#B0B0B0","#808080")
    class CryptoHolder(val binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
        val binding=RowLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CryptoHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {
        holder.binding.currencyName.text=cryptoModels.get(position).currency
        holder.binding.priceName.text=cryptoModels.get(position).price
        holder.binding.linearLayout.setBackgroundColor(Color.parseColor(colors.get(position%2)))//bu sayade dögüye sokuyoruz
    }

    override fun getItemCount(): Int {
       return cryptoModels.size
    }

}