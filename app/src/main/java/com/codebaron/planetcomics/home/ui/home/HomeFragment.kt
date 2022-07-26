package com.codebaron.planetcomics.home.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.codebaron.planetcomics.R
import com.codebaron.planetcomics.Utils.isNetworkAvailable
import com.codebaron.planetcomics.Utils.showMessageDialog
import com.codebaron.planetcomics.databinding.FragmentHomeBinding
import com.codebaron.planetcomics.repository.ResponseStateHandler
import com.squareup.picasso.Picasso
import dmax.dialog.SpotsDialog

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var dialog: SpotsDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        activity?.let {
            dialog = SpotsDialog(it, "Loading latest comic", R.style.Custom)
            if (isNetworkAvailable(it)) {
                getLatestComic(1)
            } else showMessageDialog(
                it,
                "Internet Connection Error",
                "Please ensure you have a stable internet connection"
            )
        }
        clickEvents()
        return root
    }

    private fun clickEvents() {
        var id = 1
        _binding?.nextBtn?.setOnClickListener {
            getLatestComic(id++)
        }
        _binding?.previousBtn?.setOnClickListener {
            if (id == 1) getLatestComic(id) else getLatestComic(id--) }
    }

    private fun getLatestComic(comicId: Int) {
        homeViewModel.homeComics(comicId.toString())?.observe(viewLifecycleOwner) {
            it?.let {
                when (it) {
                    is ResponseStateHandler.Loading -> dialog.show()
                    is ResponseStateHandler.Success -> {
                        dialog.dismiss()
                        _binding?.viewModel = it.data
                        Picasso.get().load(it.data?.img).into(_binding?.comicImage)
                    }
                    is ResponseStateHandler.ErrorMessage ->  {
                        dialog.dismiss()
                        showMessageDialog(requireActivity(), "Request Failed", it.message)
                    }
                    is ResponseStateHandler.Exception ->  {
                        dialog.dismiss()
                        showMessageDialog(requireActivity(), "Request Failed", it.exception.message)
                    }
                    is ResponseStateHandler.Throwable ->  {
                        dialog.dismiss()
                        showMessageDialog(requireActivity(), "Request Failed", it.throwable.message)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}