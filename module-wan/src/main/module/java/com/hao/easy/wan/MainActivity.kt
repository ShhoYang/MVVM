package com.hao.easy.wan

import android.content.Intent
import com.hao.easy.wan.databinding.WanActivityMainBinding
import com.hao.easy.wan.fragment.KnowledgeFragment
import com.hao.easy.wan.fragment.ProjectFragment
import com.hao.easy.wan.fragment.SearchFragment
import com.hao.easy.wan.fragment.WechatFragment
import com.mvvm.adapter.FragmentAdapter
import com.mvvm.adapter.FragmentCreator
import com.mvvm.annotation.AndroidEntryPoint
import com.mvvm.ui.BaseActivity
import com.mvvm.ui.UIParams
import com.mvvm.viewmodel.PlaceholderViewModel

/**
 * @author Yang Shihao
 */
@AndroidEntryPoint(injectViewModel = false)
class MainActivity : BaseActivity<WanActivityMainBinding, PlaceholderViewModel>() {

    override fun prepare(uiParams: UIParams, intent: Intent?) {
        uiParams.isTransparentStatusBar = true
    }

    override fun initView() {
        val fragments = listOf(
            object : FragmentCreator {
                override fun createFragment() = WechatFragment()
            },
            object : FragmentCreator {
                override fun createFragment() = ProjectFragment()
            },
            object : FragmentCreator {
                override fun createFragment() = KnowledgeFragment()
            },
            object : FragmentCreator {
                override fun createFragment() = SearchFragment()
            }
        )
        viewBinding {
            viewPager.apply {
                isUserInputEnabled = false
                offscreenPageLimit = 3
                adapter = FragmentAdapter(supportFragmentManager, lifecycle, fragments)
            }

            bottomNavigationView.setOnNavigationItemSelectedListener { item ->
                viewPager.setCurrentItem(
                    when (item.itemId) {
                        R.id.tab_project -> 1
                        R.id.tab_knowledge -> 2
                        R.id.tab_search -> 3
                        else -> 0
                    }, false
                )
                true
            }
        }
    }

    override fun initData() {
    }
}