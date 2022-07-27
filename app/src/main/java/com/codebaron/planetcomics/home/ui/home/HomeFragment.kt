package com.codebaron.planetcomics.home.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.codebaron.planetcomics.R
import com.codebaron.planetcomics.Utils.*
import com.codebaron.planetcomics.databinding.FragmentHomeBinding
import com.codebaron.planetcomics.models.ComicDTO
import com.codebaron.planetcomics.repository.ResponseStateHandler
import com.codebaron.planetcomics.roomdb.ComicRoomDatabase
import com.squareup.picasso.Picasso
import dmax.dialog.SpotsDialog

/**
 * @author Anyanwu Nicholas(codeBaron)
 * @since July 25 - 2022
 */

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var dialog: SpotsDialog
    private var comicRoomDatabase: ComicRoomDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /**
         * checks for internet connection and performs the required action based on the result
         * of the condition
         */
        activity?.let {
            dialog = SpotsDialog(it, DIALOG_MESSAGE, R.style.Custom)
            comicRoomDatabase = ComicRoomDatabase(it)
            if (isNetworkAvailable(it)) {
                getLatestComic(1)
            } else showMessageDialog(it, INTERNET_ERROR_MESSAGE_TYPE, INTERNET_ERROR_MESSAGE)
        }
        clickEvents()
        return root
    }

    /**
     * this function is been triggered when the next and previous button is clicked,
     * it has a default value for comicID which will either increment of decrement
     * based on the button user clicks
     */
    private fun clickEvents() {
        var comicId = homeViewModel.comicId.value!!.toInt()
        _binding?.nextBtn?.setOnClickListener {
            getLatestComic(comicId++)
        }

        _binding?.previousBtn?.setOnClickListener {
            if (comicId == 1) getLatestComic(comicId) else getLatestComic(comicId--)
        }

        _binding?.comicSearch?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.toInt()?.let { getLatestComic(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    /**
     * this function handles manages api request response, which receives it data from the [homeViewModel]
     * and updates the UI
     */
    private fun getLatestComic(comicId: Int) {
        homeViewModel.homeComics(comicId.toString())?.observe(viewLifecycleOwner) {
            it?.let {
                when (it) {
                    is ResponseStateHandler.Loading -> dialog.show()
                    is ResponseStateHandler.Success -> {
                        dialog.dismiss()
                        _binding?.viewModel = it.data
                        Picasso.get().load(it.data?.img).into(_binding?.comicImage)
                        homeViewModel.comicObject?.postValue(it.data)
                        insertToLocalDatabase(it.data)
                    }
                    is ResponseStateHandler.ErrorMessage -> {
                        dialog.dismiss()
                        showMessageDialog(requireActivity(), REQUEST_FAILED, it.message)
                    }
                    is ResponseStateHandler.Exception -> {
                        dialog.dismiss()
                        showMessageDialog(requireActivity(), REQUEST_FAILED, it.exception.message)
                    }
                    is ResponseStateHandler.Throwable -> {
                        dialog.dismiss()
                        showMessageDialog(requireActivity(), REQUEST_FAILED, it.throwable.message)
                    }
                }
            }
        }
    }

    /**
     * this function handles inserting comics to local database
     * @param data
     */
    private fun insertToLocalDatabase(data: ComicDTO?) {
        _binding?.likedComic?.setOnClickListener {
            data?.let { it1 -> comicRoomDatabase?.ComicDao()?.insertComic(it1) }
            successToast(requireActivity(), "Added to your favourites", Toast.LENGTH_SHORT)
        }

        _binding?.comicImage?.setOnClickListener {
            openBottomSheet(data, requireActivity())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}