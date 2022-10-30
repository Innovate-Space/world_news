package co.innovatespace.source.frag

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import co.innovatespace.source.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SourceCategoryFragment : Fragment() {

    companion object {
        fun newInstance() = SourceCategoryFragment()
    }

    private val viewModel: SourceCategoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_source_category, container, false)
    }

}