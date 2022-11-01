package co.innovatespace.source

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import co.innovatespace.source.databinding.FragmentSourceBinding
import co.innovatespace.ui.SourceAdapter
import co.innovatespace.utility.Event
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
        observeViewStateUpdates()
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

    private fun observeViewStateUpdates() {
        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collectLatest{
                    renderIt(it)
                }
            }

        }
    }

    private fun renderIt(state: SourceViewState) {
        with(binding){
            val prob = state.failure?.getContentIfNotHandled()
            progressIndicator.isVisible = state.isLoading
            tab.isVisible = !state.isLoading && prob==null
            swipeRefresh.isVisible = !state.isLoading && prob==null

            if(prob != null){
                //errorBox.isVisible = true
               val v = if(errorBox.parent != null) errorBox.inflate() else  null;

                val btn = v?.findViewById<Button>(co.innovatespace.ui.R.id.try_again)
                val message = v?.findViewById<TextView>(co.innovatespace.ui.R.id.msg)
                message?.text = prob.message
               btn?.setOnClickListener { viewModel.fetchData() }
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