package co.innovatespace.source

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
import co.innovatespace.source.databinding.FragmentSourceBinding
import co.innovatespace.ui.SourceAdapter
import co.innovatespace.utility.Event
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SourceFragment : Fragment() {

    private val binding get() = _binding!!
    private var _binding: FragmentSourceBinding? = null

    private val viewModel: SourceViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSourceBinding.inflate(inflater, container, false)
        return binding.root
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

    private fun setupRecyclerView(newsAdapter: SourceAdapter) {

        binding.recyclerSource.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)

        }
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

    private fun updateScreenState(state: SourceViewState) {

    }

    private fun handleFailure(failure: Event<Throwable>?) {

    }


}