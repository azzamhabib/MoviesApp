package com.az.moviesapp.core.baseClasses


import am.appwise.components.ni.NoInternetDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.az.moviesapp.R
import com.az.moviesapp.databinding.LayoutBaseFragmentBinding



abstract class BaseFragment: Fragment() , BaseFragmentView {

    private var _binding: LayoutBaseFragmentBinding? = null
    lateinit var noInternetDialog: NoInternetDialog
    // This property is only valid between onCreateView and
// onDestroyView.
    protected val binding get() = _binding

    protected var viewGroup: ViewGroup? = null

     abstract fun whichLayout(): Int
     abstract fun setTitle(): Int


     override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
     ): View? {
         //return inflater.inflate(R.layout.layout_base_fragment, container, false)

         viewGroup = container
         _binding = LayoutBaseFragmentBinding.inflate(inflater, container, false)
         val view = binding?.root

         binding?.bottomNavigationView?.setupWithNavController(findNavController())
         noInternetDialog = NoInternetDialog.Builder(requireContext()).build()

         return view
     }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.fragmentContent?.addView(LayoutInflater.from(view.context).inflate(whichLayout(), null))
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        noInternetDialog.onDestroy()
    }


 }