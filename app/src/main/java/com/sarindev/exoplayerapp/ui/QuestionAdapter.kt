package com.sarindev.exoplayerapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sarindev.exoplayerapp.databinding.AdapterQuestionItemBinding
import com.sarindev.exoplayerapp.model.Question

class QuestionAdapter(val context: Context, var listData: MutableList<Question>, val itemClickListener: ( Int, Question) -> Unit) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    lateinit var binding: AdapterQuestionItemBinding

    fun onRefresh(listData: MutableList<Question>) {
        this.listData = listData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = AdapterQuestionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(listData[position])
        (holder as ViewHolder).binding.tvQuestion.setOnClickListener {
            itemClickListener(position, listData[position])
        }

    }

    inner class ViewHolder(val binding: AdapterQuestionItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun setData(model: Question) {
            with(binding) {
                question = model
                executePendingBindings()
            }
        }

    }
}