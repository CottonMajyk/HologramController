package me.noip.teamconege.spiral6.hologramcontroller;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.net.URI;
import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {
    static boolean power = false;
    static UDPClient serverSendThingy;
    private String[] options;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        options = getResources().getStringArray(R.array.string_array_name);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.activity_navdrawer, options));
        // Set the list's click listener
      //TODO  mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        serverSendThingy = new UDPClient();
        buttons();
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

