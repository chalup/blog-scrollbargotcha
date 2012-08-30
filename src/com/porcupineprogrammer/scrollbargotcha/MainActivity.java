/*
 * Copyright (C) 2012 Jerzy Chalupski
 * Portions (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 

package com.porcupineprogrammer.scrollbargotcha;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class MainActivity extends FragmentActivity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
    viewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager()));
  }

  public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int i) {
      Fragment fragment = new LongListFragment();
      Bundle args = new Bundle();
      args.putInt(LongListFragment.ARG_LAYOUT_RESID, getLayoutResId(i));
      fragment.setArguments(args);
      return fragment;
    }

    private int getLayoutResId(int position) {
      switch (position) {
      case 0:
        return R.layout.bad_list;
      case 1:
        return R.layout.good_list;
      }
      throw new IllegalArgumentException();
    }

    @Override
    public int getCount() {
      return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      switch (position) {
      case 0:
        return getString(R.string.bad_style_title).toUpperCase();
      case 1:
        return getString(R.string.good_style_title).toUpperCase();
      }
      return null;
    }
  }

  public static class LongListFragment extends ListFragment {
    public LongListFragment() {
    }

    public static final String ARG_LAYOUT_RESID = "layout_resid";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
      final int layoutResId = getArguments().getInt(ARG_LAYOUT_RESID);
      return inflater.inflate(layoutResId, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      setListAdapter(new ArrayAdapter<String>(getActivity(),
          android.R.layout.simple_list_item_1, Cheeses.sCheeseStrings));
    }
  }
}
