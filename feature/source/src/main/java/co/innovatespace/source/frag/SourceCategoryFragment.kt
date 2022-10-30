package co.innovatespace.source.frag

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import co.innovatespace.source.R
import co.innovatespace.source.databinding.FragmentSourceBinding
import co.innovatespace.source.databinding.FragmentSourceCategoryBinding
import co.innovatespace.ui.SourceAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

const val ARG_OBJECT = "object"

@AndroidEntryPoint
class SourceCategoryFragment : Fragment() {

    companion object {
        fun newInstance(data: String): Fragment {
            val instance = SourceCategoryFragment()
            instance.arguments = Bundle().apply {
                putString(ARG_OBJECT, data)
            }
            return instance
        }
    }

    private val binding get() = _binding!!
    private var _binding: FragmentSourceCategoryBinding? = null

    private val viewModel: SourceCategoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSourceCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setupRecyclerView(newsAdapter: SourceAdapter) {

        binding.recyclerSource.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        val adapter = SourceAdapter()
        setupRecyclerView(adapter)
        observeViewStateUpdates(adapter)
    }

    private fun observeViewStateUpdates(adapter: SourceAdapter) {
        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.pagingDataFlow.collectLatest{
                    adapter.submitData(it)
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}