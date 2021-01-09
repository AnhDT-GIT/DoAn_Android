package com.example.doan_android.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.doan_android.Fragment.HomeFragment;
import com.example.doan_android.Fragment.SearchFragment;

public class AdapterUI extends FragmentStatePagerAdapter{

  public AdapterUI(@NonNull FragmentManager fm, int behavior) {
    super(fm, behavior);
  }

  @NonNull
  @Override
  public Fragment getItem(int position) {
    switch (position){
      case 0:
        return new HomeFragment();
      case 1:
        return new SearchFragment();
      default:
        return new HomeFragment();
    }
  }

  @Override
  public int getCount() {
    return 2;
  }

  @Nullable
  @Override
  public CharSequence getPageTitle(int position) {
    String title = "";
    return title;
  }
}
