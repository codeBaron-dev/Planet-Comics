package com.codebaron.planetcomics.home.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.codebaron.planetcomics.R
import com.codebaron.planetcomics.Utils.*
import com.codebaron.planetcomics.databinding.FragmentComicExplanationBinding
import dmax.dialog.SpotsDialog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author Anyanwu Nicholas
 * @since July 27 - 2022
 */
class ComicExplanationFragment : Fragment() {

    private var _binding: FragmentComicExplanationBinding? = null
    private val binding get() = _binding!!
    private lateinit var dialog: SpotsDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentComicExplanationBinding.inflate(layoutInflater, container, false)
        activity?.let {
            dialog = SpotsDialog(it, LOADING_WEB_CONTENT, R.style.Custom)
            if (isNetworkAvailable(it)) {
                dialog.show()
                lifecycleScope.launch {
                    delay(4000)
                    dialog.dismiss()
                }
            } else showMessageDialog(it, INTERNET_ERROR_MESSAGE_TYPE, INTERNET_ERROR_MESSAGE)
        }
        val root = binding.root
        loadWebView()
        return root
    }

    /**
     * this function handles displaying webView and webView back navigation
     */
    private fun loadWebView() {
        _binding?.webView?.loadUrl(COMIC_EXPLANATION_URL)
        _binding?.webView?.settings?.javaScriptEnabled = true
        _binding?.webView?.settings?.setSupportZoom(true)

        if (_binding?.webView?.canGoBack() == true) {
            _binding?.webView?.goBack()
        }
    }

}