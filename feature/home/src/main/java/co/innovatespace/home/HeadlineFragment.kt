package co.innovatespace.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.innovatespace.home.databinding.FragmentHeadlineBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeadlineFragment : Fragment() {

    private val binding get() = _binding!!
    private var _binding: FragmentHeadlineBinding? = null


    private lateinit var viewModel: HeadlineViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHeadlineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}