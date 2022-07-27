package com.codebaron.planetcomics.home.ui.favourites.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codebaron.planetcomics.Utils.openBottomSheet
import com.codebaron.planetcomics.databinding.LocalComicsBinding
import com.codebaron.planetcomics.models.ComicDTO
import com.squareup.picasso.Picasso

/**
 * @author Anyanwu Nicholas
 * @since July 27 - 2022
 */
class LocalComics(private val comics: List<ComicDTO>, private val layout: Int, private val context: Context) :
    RecyclerView.Adapter<LocalComics.ComicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val layout = DataBindingUtil.inflate<LocalComicsBinding>(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
        return ComicViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val comic = comics[position]
        comic.let { holder.bindViews(it) }
        Picasso.get().load(comic.img).into(holder.imageView)
        holder.imageView.setOnClickListener {
            openBottomSheet(comic, context)
        }
    }

    override fun getItemCount(): Int = comics.size

    class ComicViewHolder(private val binding: LocalComicsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var imageView = binding.comicImage
            fun bindViews(comic: ComicDTO) {
                binding.viewModel = comic
            }
    }
}