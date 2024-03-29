package com.imie.sylf.util;

import com.actionbarsherlock.app.SherlockFragmentActivity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

/**
 * Class which create the swipe tab
 * @author Jean
 *
 */
public class TabSwipeActivity extends SherlockFragmentActivity {
	private ViewPager mViewPager;
    private TabsAdapter adapter;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        /*
         * Create the ViewPager and our custom adapter
         */
        mViewPager = new ViewPager(this);
        adapter = new TabsAdapter( this, mViewPager );
        mViewPager.setAdapter( adapter );
        mViewPager.setOnPageChangeListener( adapter );
 
        /*
         * We need to provide an ID for the ViewPager, otherwise we will get an exception like:
         *
         * java.lang.IllegalArgumentException: No view found for id 0xffffffff for fragment TestFragment{40de5b90 #0 id=0xffffffff android:switcher:-1:0}
         * at android.support.v4.app.FragmentManagerImpl.moveToState(FragmentManager.java:864)
         *
         * The ID 0x7F04FFF0 is large enough to probably never be used for anything else
         */
        mViewPager.setId( 0x7F04FFF0 );
 
        super.onCreate(savedInstanceState);
 
        /*
         * Set the ViewPager as the content view
         */
        setContentView(mViewPager);
    }
 
    /**
     * Add a tab with a backing Fragment to the action bar
     * @param titleRes A string resource pointing to the title for the tab
     * @param fragmentClass The class of the Fragment to instantiate for this tab
     * @param args An optional Bundle to pass along to the Fragment (may be null)
     */
//    protected void addTab(int titleRes, Class fragmentClass, Bundle args ) {
//        adapter.addTab( titleRes , fragmentClass, args );
//    }
    /**
     * Add a tab with a backing Fragment to the action bar
     * @param titleRes A string to be used as the title for the tab
     * @param fragmentClass The class of the Fragment to instantiate for this tab
     * @param args An optional Bundle to pass along to the Fragment (may be null)
     */
    protected void addTab(int res, Class fragmentClass, Bundle args ) {
        adapter.addTab( res, fragmentClass, args );
    }
}
