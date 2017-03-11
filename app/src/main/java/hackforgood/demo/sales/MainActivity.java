package hackforgood.demo.sales;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.TextView;

import java.util.HashMap;

import hackforgood.demo.sales.entities.Offer;
import hackforgood.demo.sales.fragments.MapFragment;
import hackforgood.demo.sales.fragments.OfferListFragment;
import hackforgood.demo.sales.fragments.ProductListFragment;
import hackforgood.demo.sales.myapplication.R;
import hackforgood.demo.sales.util.CustomViewPager;
import hackforgood.demo.sales.util.DelayUtil;
import hackforgood.demo.sales.util.PageTransformer;

public class MainActivity extends FragmentActivity implements ActivityInterface, TabLayout.OnTabSelectedListener {

    private static final int OFFERS_PAGE = 0;
    private static final int STORE_PAGE = 1;
    private static final int MAP_PAGE = 2;

    private static final int NUM_PAGES = 3;
    private CustomViewPager viewPager;
    private SlidePagerAdapter slidePagerAdapter;

    private TextView appBarTitle;

    private Offer selectedOffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        (appBarTitle = (TextView) findViewById(R.id.app_bar_title)).setText(R.string.app_name);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.offers_title));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.offer_datails_title));
        tabLayout.addTab(tabLayout.newTab().setText("Ubicación"));
        tabLayout.addOnTabSelectedListener(this);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        slidePagerAdapter = new SlidePagerAdapter(getSupportFragmentManager());
        viewPager = (CustomViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(slidePagerAdapter);
        viewPager.setPageTransformer(true, new PageTransformer());
        viewPager.setScrollDurationFactor(2);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    public void onResume() {
        super.onResume();
        DelayUtil.postDelay(new DelayUtil.OnDelayListener() {
            @Override
            public void onDelayEnd() {
                Snackbar.make(viewPager, R.string.new_offer_published, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }, 10000);
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    @Override
    public void onSelectOffer(Offer offer) {
        this.selectedOffer = offer;
        DelayUtil.postDelay(new DelayUtil.OnDelayListener() {
            @Override
            public void onDelayEnd() {
                viewPager.setCurrentItem(STORE_PAGE, true);
            }
        }, 300);
    }

    @Override
    public void selectOffer(Offer offer) {
        this.selectedOffer = offer;
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition(), true);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {}

    @Override
    public void onTabReselected(TabLayout.Tab tab) {}


    private class SlidePagerAdapter extends FragmentStatePagerAdapter {
        private HashMap<Integer, Fragment> fragments = new HashMap<>();

        public SlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (fragments.containsKey(position)) return fragments.get(position);

            Fragment fragment;
            switch (position) {
                case STORE_PAGE: fragment = new ProductListFragment(); break;
                case MAP_PAGE: fragment = new MapFragment(); break;
                default: fragment = new OfferListFragment(); break;
            }

            fragments.put(position, fragment);
            return fragment;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
