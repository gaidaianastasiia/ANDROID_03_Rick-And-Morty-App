package com.example.rickandmorty.presentation.fragment.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.CharactersListItemLayoutBinding
import com.example.rickandmorty.entity.Character
import com.squareup.picasso.Picasso

private val DIFF_CALLBACK: DiffUtil.ItemCallback<Character> =
    object : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }

class CharactersAdapter : ListAdapter<Character, CharactersAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = CharactersListItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }

    class ViewHolder(
        private val itemBinding: CharactersListItemLayoutBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(character: Character) {

            itemBinding.let {
                Picasso.get()
                    .load(character.image)
                    .placeholder(R.drawable.default_placeholder)
                    .error(R.drawable.default_placeholder)
                    .into(it.characterPictureImageView)

                it.characterNameTextView.text = character.name

                it.characterStatusTextView.setText(character.status.stringRes)

                it.characterSpeciesTextView.setText(
                    character.species?.stringRes ?: R.string.character_species_default_value
                )

                it.characterGenderTextView.setText(
                    character.gender?.stringRes ?: R.string.character_gender_default_value
                )
            }
        }
    }
}