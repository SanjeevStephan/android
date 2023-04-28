package com.sanjeev.stephan.murmu.kotlinbeginnercodes

/**
 *  @author Sanjeev Stephan Murmu
 *  @since 17-Jan-20
 */
import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sanjeev.stephan.murmu.kotlinbeginnercodes.databinding.FragmentWebviewBinding

/**
 * This Fragment Class displays the URL using the WebView widgets.
 */
class Android_WebView(private var goToThisURL : String = "https://www.google.com/") : Fragment() {
    lateinit var binding: FragmentWebviewBinding
    lateinit var webView: WebView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO : Receving URL Link from the caller fragment.
        goToThisURL = arguments?.getString("url").toString()
    }

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_webview, null, false)
        return binding.root
    }

    /**
     * @param view
     * @param savedInstanceState
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        webView = binding.idAndroidWebview
        val webClient = MyWebClient(context as Activity)
        webView.webViewClient = webClient
        webView.loadUrl(goToThisURL)
    }


    private class MyWebClient internal constructor(context: Activity) : WebViewClient() {

        /**
         * @param view
         * @param url
         */
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url)
            return true
        }

        /**
         * @param view
         * @param request
         */
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            val url = request?.url.toString()
            view?.loadUrl(url)
            return true
        }

        /**
         * @param view
         * @param request
         * @param error
         */
        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            super.onReceivedError(view, request, error)
            Log.i("TAG", "Error @Android_WebView Class")
        }
    }

}