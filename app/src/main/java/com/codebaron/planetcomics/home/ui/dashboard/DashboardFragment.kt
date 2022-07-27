package com.codebaron.planetcomics.home.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.codebaron.planetcomics.R
import com.codebaron.planetcomics.Utils.DIALOG_MESSAGE
import com.codebaron.planetcomics.databinding.FragmentDashboardBinding
import com.codebaron.planetcomics.home.ui.dashboard.adapter.LocalComics
import com.codebaron.planetcomics.models.ComicDTO
import com.codebaron.planetcomics.models.dummyComicDTOList
import dmax.dialog.SpotsDialog

/**
 * @author Anyanwu Nicholas
 * @since July 27 - 2022
 */
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val dashboardViewModel: DashboardViewModel by viewModels()
    private lateinit var dialog: SpotsDialog
    private var comicList = mutableListOf<ComicDTO>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /**
         * checks for internet connection and performs the required action based on the result
         * of the condition
         */
        activity?.let {
            dialog = SpotsDialog(it, DIALOG_MESSAGE, R.style.Custom)
            loadLocalComics()
        }
        return root
    }

    /**
     * this function load comics from local database and passes it to [Adapter]
     * to be displayed on [RecyclerView]
     */
    private fun loadLocalComics() {
        activity?.let {
            val localComicsAdapter = LocalComics(comicList, R.layout.local_comics)
            _binding?.comicRecyclerView?.adapter = localComicsAdapter
            _binding?.comicRecyclerView?.layoutManager = LinearLayoutManager(it)

            /**
             * read from local database
             * check if database [isEmpty] and perform the required action based on the
             * result of the condition
             */
            val comics = if (dashboardViewModel.loadLocalComics(it).isEmpty()) {
                dummyComicDTOList /*Dummy Data*/
            } else dashboardViewModel.loadLocalComics(it)
            comicList.addAll(comics)
            localComicsAdapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}