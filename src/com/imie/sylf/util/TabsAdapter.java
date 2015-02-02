package com.imie.sylf.util;

import java.util.ArrayList;
import java.util.List;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class TabsAdapter extends FragmentPagerAdapter implements TabListener, ViewPager.OnPageChangeListener {
	 
    private final SherlockFragmentActivity mActivity;
    private final ActionBar mActionBar;
    private final ViewPager mPager;
    private List<TabInfo> mTabs = new ArrayList<TabInfo>();

    /**
     * @param fm
     * @param fragments
     */
    public TabsAdapter(SherlockFragmentActivity activity, ViewPager pager) {
        super(activity.getSupportFragmentManager());
        this.mActivity = activity;
        this.mActionBar = activity.getSupportActionBar();
        this.mPager = pager;

        mActionBar.setNavigationMode( ActionBar.NAVIGATION_MODE_TABS );
    }

    private static class TabInfo {
        public final Class fragmentClass;
        public final Bundle args;
        public TabInfo(Class fragmentClass,
                Bundle args) {
            this.fragmentClass = fragmentClass;
            this.args = args;
        }
    }

    public void addTab( int resId, Class fragmentClass, Bundle args ) {
        final TabInfo tabInfo = new TabInfo( fragmentClass, args );

        Tab tab = mActionBar.newTab();
        //tab.setText( title );
        tab.setIcon(resId);
        tab.setTabListener( this );
        tab.setTag( tabInfo );

        mTabs.add( tabInfo );

        mActionBar.addTab( tab );
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        final TabInfo tabInfo = (TabInfo) mTabs.get( position );
        return (Fragment) Fragment.instantiate( mActivity, tabInfo.fragmentClass.getName(), tabInfo.args );
    }

    @Override
    public int getCount() {
        return mTabs.size();
    }

    public void onPageScrollStateChanged(int arg0) {
    }

    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    public void onPageSelected(int position) {
        mActionBar.setSelectedNavigationItem( position );
    }

    @Override
	public void onTabSelected(Tab tab,
			android.support.v4.app.FragmentTransaction ft) {
            TabInfo tabInfo = (TabInfo) tab.getTag();
            for ( int i = 0; i < mTabs.size(); i++ ) {
                if ( mTabs.get( i ) == tabInfo ) {
                    mPager.setCurrentItem( i );
                }
            }		
	}

	@Override
	public void onTabUnselected(Tab tab,
			android.support.v4.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabReselected(Tab tab,
			android.support.v4.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	
}
