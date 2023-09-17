package com.example.carease.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.carease.data.local.room.Card.CardEntity
import com.example.carease.R

class CardAdapter(private val ctx: Context, val listener: CarDItemClickAdapter) : RecyclerView.Adapter<CardAdapter.cardViewHolder>() {

    private val cardList: ArrayList<CardEntity> = arrayListOf()

    class cardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val cardNumber: TextView = itemView.findViewById(R.id.cardNumber_singleCard)
        val cardHName: TextView = itemView.findViewById(R.id.cardHolderName_singleCard)
        val exp: TextView = itemView.findViewById(R.id.expiryDate_singleCard)
        val layD : LinearLayout = itemView.findViewById(R.id.useDefault_Layout)
        val checkbox : CheckBox = itemView.findViewById(R.id.checkBox_SingleCard)
        val cardImage: ImageView = itemView.findViewById(R.id.cardBrandImage_singleCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cardViewHolder {
        val cardView = LayoutInflater.from(ctx).inflate(R.layout.single_card,parent,false)
        return cardViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: cardViewHolder, position: Int) {
        val cardItem: CardEntity = cardList[position]

        when (cardItem.brandC) {
            "MASTERCARD" -> holder.cardImage.setImageResource(R.drawable.ic_mastercard)
            "VISA" -> holder.cardImage.setImageResource(R.drawable.ic_visa)
            "AMERICAN_EXPRESS" -> holder.cardImage.setImageResource(R.drawable.ic_american_express)
            "DINERS_CLUB" -> holder.cardImage.setImageResource(R.drawable.ic_diners_club)
            "DISCOVER" -> holder.cardImage.setImageResource(R.drawable.ic_discover)
            "JCB" -> holder.cardImage.setImageResource(R.drawable.ic_jcb)
            "CHINA_UNION_PAY" -> holder.cardImage.setImageResource(R.drawable.ic_unionpay)

            else -> {
                holder.cardImage.setImageResource(R.drawable.ic_mastercard)
            }
        }

        holder.cardHName.text = cardItem.nameCH.toString()
        holder.exp.text = cardItem.exp
        holder.cardNumber.text = cardItem.number
        if(cardList.size > 1){
            holder.layD.visibility = View.VISIBLE

        }
        else{
            holder.layD.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    fun updateList(newList: List<CardEntity>){
        cardList.clear()
        cardList.addAll(newList)
        notifyDataSetChanged()
    }
}


interface CarDItemClickAdapter{
    fun onItemDeleteClick(cardEntity: CardEntity)
    fun onItemUpdateClick(cardEntity: CardEntity)
}