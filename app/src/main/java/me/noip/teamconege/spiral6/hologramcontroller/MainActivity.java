package me.noip.teamconege.spiral6.hologramcontroller;

import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.net.URI;
import java.net.URISyntaxException;

public class MainActivity extends ActionBarActivity {
    static boolean power = false;
    static UDPClient serverSendThingy;
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerList = (ListView)findViewById(R.id.navList);
        addDrawerItems();
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Time for an upgrade!", Toast.LENGTH_SHORT).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        setupDrawer();

        serverSendThingy = new UDPClient();
        buttons();
    }

    protected void addDrawerItems() {
        String[] osArray = { "Options", "Settings", "Windows", "OS X", "Linux" };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }


        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttons()
    {
        ImageButton la = (ImageButton) findViewById(R.id.leftarrow);
        ImageButton dp = (ImageButton) findViewById(R.id.dankpower);
        ImageButton ra = (ImageButton) findViewById(R.id.rightarrow);
        ImageButton da = (ImageButton) findViewById(R.id.downarrow);
        ImageButton ua = (ImageButton) findViewById(R.id.uparrow);
        la.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Left", Toast.LENGTH_SHORT).show();
                //serverSendThingy.doInBackground("left");
                new UDPClient().execute("left");
            }
        });
        ra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Right", Toast.LENGTH_SHORT).show();
                //  serverSendThingy.doInBackground("right");
                new UDPClient().execute("right");
            }
        });
        da.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Down", Toast.LENGTH_SHORT).show();
                //  serverSendThingy.doInBackground("down");
                new UDPClient().execute("down");
            }
        });
        ua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Up", Toast.LENGTH_SHORT).show();
                //   serverSendThingy.doInBackground("up");
                new UDPClient().execute("up");
            }
        });
        dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (power) {
                    Toast.makeText(v.getContext(), "Off", Toast.LENGTH_SHORT).show();
                    power = false;
                    ((ImageButton) findViewById(R.id.dankpower)).setImageResource(R.mipmap.dankpower);
                    // serverSendThingy.doInBackground("off");
                    new UDPClient().execute("off");
                } else {
                    Toast.makeText(v.getContext(), "On", Toast.LENGTH_SHORT).show();
                    power = true;
                    ((ImageButton) findViewById(R.id.dankpower)).setImageResource(R.mipmap.greenpower);
                    // serverSendThingy.doInBackground("on");
                    new UDPClient().execute("on");
                }
            }
        });
    }
}