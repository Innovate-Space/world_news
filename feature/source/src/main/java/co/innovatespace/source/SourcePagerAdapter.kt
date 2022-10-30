package co.innovatespace.source

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import co.innovatespace.source.frag.SourceCategoryFragment

class SourcePagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int  = tabsText.size

    override fun createFragment(position: Int): Fragment {
        return SourceCategoryFragment.newInstance()
    }

}