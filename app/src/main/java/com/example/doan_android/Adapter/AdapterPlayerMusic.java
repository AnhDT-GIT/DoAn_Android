package com.example.doan_android.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.doan_android.Fragment.lyric_player;
import com.example.doan_android.Fragment.music_player;

public class AdapterPlayerMusic extends FragmentStatePagerAdapter {

  public AdapterPlayerMusic(@NonNull FragmentManager fm, int behavior) {
    super(fm, behavior);
  }

  @NonNull
  @Override
  public Fragment getItem(int position) {
    switch (position){
      case 0:
        return new music_player();
      case 1:
        return new lyric_player();
      default:
        return new music_player();
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
