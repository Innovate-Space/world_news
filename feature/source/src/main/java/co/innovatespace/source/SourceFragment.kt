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
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

//data class TabsData(val txtRes: Int )
val tabsText = intArrayOf(R.string.business, R.string.entertainment, R.string.environment, R.string.food, R.string.health, R.string.politics, R.string.science, R.string.sports, R.string.technology, R.string.top, R.string.world )

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
        setupPager()
    }

    private fun setupUI() {
        val adapter = SourceAdapter()
        setupRecyclerView(adapter)
        observeViewStateUpdates(adapter)
    }

    private fun setupPager(){
        val adapter = SourcePagerAdapter(this)
        binding.pager.apply {
            this.adapter = adapter
        }

        TabLayoutMediator(binding.tab, binding.pager){
                tab, position ->
            tab.text =  context?.getString(tabsText[position])
        }.attach()
    }

    private fun setupRecyclerView(newsAdapter: SourceAdapter) {

        binding.recyclerSource.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(false)
            isNestedScrollingEnabled = false

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}