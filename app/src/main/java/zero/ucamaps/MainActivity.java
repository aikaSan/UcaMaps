package zero.ucamaps;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends ActionBarActivity {

    public static DrawerLayout mDrawerLayout;
    public static boolean switchTheme;

    /**
     * The list of menu items in the navigation drawer
     */
    @InjectView(R.id.maps_app_activity_left_drawer) ListView mDrawerList;

    private final List<DrawerItem> mDrawerItems = new ArrayList<>();

    /**
     * Helper component that ties the action bar to the navigation drawer.
     */
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.maps_app_activity);
        ButterKnife.inject(this);
        setupDrawer();
        setView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateDrawer();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // first check if the drawer toggle button was selected
        boolean handled = mDrawerToggle.onOptionsItemSelected(item);
        if (!handled) {
            handled = super.onOptionsItemSelected(item);
        }
        return handled;
    }

    /**
     * Initializes the navigation drawer.
     */
    private void setupDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.maps_app_activity_drawer_layout);

        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // set a custom shadow that overlays the main content when the drawer
        // opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,GravityCompat.START);
        // set up the drawer's list view with items and click listener

        //ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the navigation drawer and the action bar app icon.
        mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
                mDrawerLayout, /* DrawerLayout object */
                R.string.drawer_open, /* "open drawer" description for accessibility */
                R.string.drawer_close /* "close drawer" description for accessibility */
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }
        };

        // Defer code dependent on restoration of previous instance state.
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        updateDrawer();
    }

    private void setView() {
        // show the default map
        showMap(null);
    }

    /**
     * Opens the map represented by the specified portal item or if null, opens a default map.
     */
    public void showMap(String basemapPortalItemId) {

        // remove existing MapFragment explicitly, simply replacing it can cause the app to freeze when switching basemaps
        FragmentTransaction transaction;
        FragmentManager fragmentManager = getFragmentManager();
        Fragment currentMapFragment = fragmentManager.findFragmentByTag(MapFragment.TAG);
        if (currentMapFragment != null) {
            transaction = fragmentManager.beginTransaction();
            transaction.remove(currentMapFragment);
            transaction.commit();
        }

        MapFragment mapFragment = MapFragment.newInstance(basemapPortalItemId);

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.maps_app_activity_content_frame, mapFragment,MapFragment.TAG);
        transaction.addToBackStack(null);
        transaction.commit();

        invalidateOptionsMenu(); // reload the options menu
    }

    /**
     * Updates the navigation drawer items.
     */
    private void updateDrawer() {
        mDrawerItems.clear();

        DrawerItem item = null;
        // Adding the theme item in the drawer
        LinearLayout view_basemap = (LinearLayout) getLayoutInflater().inflate(R.layout.drawer_item_layout, null);
        TextView text_drawer_basemap = (TextView) view_basemap.findViewById(R.id.drawer_item_textview);
        ImageView icon_drawer_basemap = (ImageView) view_basemap.findViewById(R.id.drawer_item_icon);

        text_drawer_basemap.setText(getString(R.string.menu_basemaps));
        icon_drawer_basemap.setImageResource(R.drawable.action_map);
        item = new DrawerItem(view_basemap, new DrawerItem.OnClickListener() {

            @Override
            public void onClick() {
                //Coming soon

                //Close and lock the drawer
                mDrawerLayout.closeDrawers();
            }
        });
        mDrawerItems.add(item);

        // Adding the QR item in the Drawer
        LinearLayout view_qr = (LinearLayout) getLayoutInflater().inflate(R.layout.drawer_item_layout, null);
        TextView text_drawer_measure = (TextView) view_qr.findViewById(R.id.drawer_item_textview);
        ImageView icon_drawer_measure = (ImageView) view_qr.findViewById(R.id.drawer_item_icon);

        text_drawer_measure.setText(getString(R.string.action_qr));
        icon_drawer_measure.setImageResource(R.drawable.action_qr_code);
        item = new DrawerItem(view_qr, new DrawerItem.OnClickListener() {

            @Override
            public void onClick() {
                // Coming Soon.

                //Close and lock the drawer
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }
        });
        mDrawerItems.add(item);

        // Adding the sound item
        LinearLayout view_sound = (LinearLayout) getLayoutInflater().inflate(R.layout.drawer_item_layout, null);
        TextView text_drawer_sound = (TextView) view_sound.findViewById(R.id.drawer_item_textview);
        ImageView icon_drawer_sound = (ImageView) view_sound.findViewById(R.id.drawer_item_icon);

        text_drawer_sound.setText(getString(R.string.action_sound));
        icon_drawer_sound.setImageResource(R.drawable.action_sound);
        item = new DrawerItem(view_sound, new DrawerItem.OnClickListener() {

            @Override
            public void onClick() {
                // Coming Soon.

                //Close and lock the drawer
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }
        });
        mDrawerItems.add(item);

        // Adding the voice item
        LinearLayout view_voice = (LinearLayout) getLayoutInflater().inflate(R.layout.drawer_item_layout, null);
        TextView text_drawer_voice = (TextView) view_voice.findViewById(R.id.drawer_item_textview);
        ImageView icon_drawer_voice = (ImageView) view_voice.findViewById(R.id.drawer_item_icon);

        text_drawer_voice.setText(getString(R.string.action_voice));
        icon_drawer_voice.setImageResource(R.drawable.action_voice);
        item = new DrawerItem(view_voice, new DrawerItem.OnClickListener() {

            @Override
            public void onClick() {
                // Coming Soon.

                //Close and lock the drawer
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }
        });
        mDrawerItems.add(item);


        // Adding the about item
        LinearLayout view_about = (LinearLayout) getLayoutInflater().inflate(R.layout.drawer_item_layout, null);
        TextView text_drawer_about = (TextView) view_about.findViewById(R.id.drawer_item_textview);
        ImageView icon_drawer_about = (ImageView) view_about.findViewById(R.id.drawer_item_icon);

        text_drawer_about.setText(getString(R.string.action_about));
        icon_drawer_about.setImageResource(R.drawable.action_about);
        item = new DrawerItem(view_about, new DrawerItem.OnClickListener() {

            @Override
            public void onClick() {
                // Coming Soon.

                //Close and lock the drawer
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }
        });
        mDrawerItems.add(item);


        BaseAdapter adapter = (BaseAdapter) mDrawerList.getAdapter();
        if (adapter == null) {
            adapter = new DrawerItemListAdapter();
            mDrawerList.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }

    }

    /**
     * Handles selection of items in the navigation drawer.
     */
    private class DrawerItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mDrawerItems.get(position).onClicked();
        }
    }

    /**
     * Populates the navigation drawer list with items.
     */
    private class DrawerItemListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mDrawerItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mDrawerItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            DrawerItem drawerItem = (DrawerItem) getItem(position);
            return drawerItem.getView();
        }
    }

}