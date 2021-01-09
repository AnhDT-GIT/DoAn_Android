package com.example.doan_android.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.doan_android.Fragment.MusicPlayerFragment;

public class AdapterPlayerMusic extends FragmentStatePagerAdapter {

  public AdapterPlayerMusic(@NonNull FragmentManager fm, int behavior) {
    super(fm, behavior);
  }

  @NonNull
  @Override
  public Fragment getItem(int position) {
    switch (position){
      case 0:
        return (new MusicPlayerFragment());
      default:
        return (new MusicPlayerFragment());
    }
  }

  @Override
  public int getCount() { return 1; }

  @Nullable
  @Override
  public CharSequence getPageTitle(int position) {
    String title = "";
    return title;
  }

}
