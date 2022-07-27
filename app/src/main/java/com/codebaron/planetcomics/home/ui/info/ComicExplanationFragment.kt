package com.codebaron.planetcomics.home.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.codebaron.planetcomics.Utils.COMIC_EXPLANATION_URL
import com.codebaron.planetcomics.databinding.FragmentComicExplanationBinding

/**
 * @author Anyanwu Nicholas
 * @since July 27 - 2022
 */
class ComicExplanationFragment : Fragment() {

    private var _binding: FragmentComicExplanationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentComicExplanationBinding.inflate(layoutInflater, container, false)
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