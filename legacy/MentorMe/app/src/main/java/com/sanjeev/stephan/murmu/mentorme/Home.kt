package com.sanjeev.stephan.murmu.mentorme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sanjeev.stephan.murmu.mentorme.quotes.RobertKiyosaki

class Home : Fragment(),View.OnClickListener
{

    private lateinit var moreAuthorsBtn : CardView
    private lateinit var authorRobertKiyosaki: CardView
    private lateinit var authorAnother : CardView
    private lateinit var traderTimSykes : CardView
    private lateinit var traderStevenDux : CardView
    private lateinit var traderRickyGutierrez: CardView
    private lateinit var traderSanjeevStephan : CardView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home,null,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moreAuthorsBtn = view.findViewById(R.id.id_more_author_btn)
        authorRobertKiyosaki = view.findViewById(R.id.id_author_robert_kiyosaki)
        authorAnother = view.findViewById(R.id.id_another_author)
        traderTimSykes = view.findViewById(R.id.id_trader_tim_skyes)
        traderStevenDux = view.findViewById(R.id.id_trader_steven_dux)
        traderRickyGutierrez = view.findViewById(R.id.id_trader_ricky_gutierrez)
        traderSanjeevStephan = view.findViewById(R.id.id_trader_sanjeev_stephan)

        moreAuthorsBtn.setOnClickListener(this)
        authorRobertKiyosaki.setOnClickListener(this)
        authorAnother.setOnClickListener(this)
        traderTimSykes.setOnClickListener(this)
        traderStevenDux.setOnClickListener(this)
        traderRickyGutierrez.setOnClickListener(this)
        traderSanjeevStephan.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var id: Int = 0

        val bookList = arrayOf("RIch Dad","Poor Dad")
        val noBook = arrayOf("No book","Written")

        var bundle : Bundle? = null

        when (v?.id) {
            R.id.id_author_robert_kiyosaki -> {
                id = R.id.action_home_fragment_to_authorTemplate
                bundle = bundleOf("authorName" to "Robert Kiyosaki",
                    "bookList" to bookList,
                    "image" to R.drawable.robertkiyosaki
                    )
            }
            R.id.id_more_author_btn -> {
                id = R.id.action_home_fragment_to_allAuthors
                bundle = bundleOf("authorName" to "Steven Dux",
                    "bookList" to noBook,
                    "image" to R.drawable.stevendux
                    )
            }
            R.id.id_another_author -> {
                id = R.id.action_home_fragment_to_authorTemplate
                bundle = bundleOf("authorName" to "Steven Dux",
                    "bookList" to noBook,
                    "image" to R.drawable.stevendux
                )
            }
            R.id.id_trader_tim_skyes -> {
                id = R.id.action_home_fragment_to_traderTemplate
                bundle = bundleOf("traderName" to getString(R.string.timothy_sykes),
                    "arrayList" to resources.getStringArray(R.array.tim_sykes_array),
                    "traderImage" to R.drawable.timsykes

                )
            }
            R.id.id_trader_steven_dux -> {
                id = R.id.action_home_fragment_to_traderTemplate
                bundle = bundleOf("traderName" to getString(R.string.steven_dux),
                    "arrayList" to resources.getStringArray(R.array.steven_dux_array),
                    "traderImage" to R.drawable.stevendux

                )
            }

            R.id.id_trader_ricky_gutierrez -> {
                id = R.id.action_home_fragment_to_traderTemplate
                bundle = bundleOf("traderName" to getString(R.string.ricky_gutierrez),
                    "arrayList" to resources.getStringArray(R.array.ricky_gutierrez_array),
                    "traderImage" to R.drawable.ricky

                )
            }
            R.id.id_trader_sanjeev_stephan -> {
                id = R.id.action_home_fragment_to_traderTemplate
                bundle = bundleOf("traderName" to getString(R.string.sanjeev_stephan),
                    "arrayList" to resources.getStringArray(R.array.sanjeev_stephan_array),
                    "traderImage" to R.drawable.myself

                )
            }
        }

        findNavController().navigate(id,bundle)

    }
}