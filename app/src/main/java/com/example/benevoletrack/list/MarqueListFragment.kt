package com.example.benevoletrack.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.benevoletrack.R
import com.example.benevoletrack.Singletons
import com.example.benevoletrack.api.MarqueListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/** MarqueListFragment : Affichage des données */
class MarqueListFragment : Fragment() {

    private lateinit var recyclerview: RecyclerView
    private lateinit var loader: ProgressBar
    private lateinit var textViewError: TextView

    /** Création de l'adapter */
    private val adapter = MarqueAdapter(listOf(), ::onClickedMarque)

    /** Création du viewModel (MVVM) */
    private val viewModel: MarqueListViewModel by viewModels()


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_marque_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        /** Recuperer un élément de ma vue */
        recyclerview= view.findViewById(R.id.marque_recyclerview)
        loader= view.findViewById(R.id.marque_Loader)
        textViewError= view.findViewById(R.id.marque_Error)

        val apply = recyclerview.apply {
            adapter = this@MarqueListFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }


        viewModel.marqueList.observe(viewLifecycleOwner, Observer { marqueModel ->
            loader.isVisible = marqueModel is MarqueLoader
            textViewError.isVisible = marqueModel is MarqueError

           if( marqueModel is MarqueSuccess) {
               adapter.updateList(marqueModel.marqueList)
           }



        })


    }


    private fun onClickedMarque(id: String) {
        findNavController().navigate(R.id.navigateToMarqueDetailFragment, bundleOf(
                "marqueName" to id

        ))
    }
}