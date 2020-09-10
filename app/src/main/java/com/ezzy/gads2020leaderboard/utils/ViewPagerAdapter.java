package com.ezzy.gads2020leaderboard.utils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ezzy.gads2020leaderboard.SkillIQFragment;
import com.ezzy.gads2020leaderboard.TopLearnersFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    final int tabsNumber = 2;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
//                fragment = new LearningLeadersFragment();
//                break;
                return new TopLearnersFragment();
            case 1:
                return new SkillIQFragment();
//                fragment = new SkillIQLeadersFragment();
//                break;
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return tabsNumber;
    }

}
