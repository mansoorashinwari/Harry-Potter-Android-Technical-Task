package com.example.harrypottercaracters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.harrypottercaracters.databinding.AdapterCharacterRowBinding
import com.example.harrypottercaracters.model.Character

class CharacterAdapter : RecyclerView.Adapter<MainViewHolder>() {

    var characterList = mutableListOf<Character>()

    fun setCharacter(characters: List<Character>) {
        this.characterList = characters.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterCharacterRowBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val character = characterList[position]
        holder.binding.name.text = character.name
        Glide.with(holder.itemView.context).load(character.image).into(holder.binding.imageview)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }
}

class MainViewHolder(val binding: AdapterCharacterRowBinding) : RecyclerView.ViewHolder(binding.root) {

}