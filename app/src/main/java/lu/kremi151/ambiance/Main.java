/*
 * Copyright 2014 Michel Kremer (kremi151)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package lu.kremi151.ambiance;

import lu.kremi151.ambiance.PlayerService.PlayerBinder;
import lu.kremi151.ambiance.fragment.FragmentTracks;
import lu.kremi151.ambiance.playlist.PlaylistDatabase;
import lu.kremi151.ambiance.util.AmbianceNavigationAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import android.os.IBinder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

public class Main extends Activity implements OnItemClickListener {
	
	private PlayerService musicSrv;
	private Intent playIntent;
	private boolean musicBound=false;
	
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	
	private AmbianceNavigationAdapter navAdapter;
	
	private static Main m;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		navAdapter = new AmbianceNavigationAdapter(this);
		mDrawerList.setAdapter(navAdapter);
		mDrawerList.setOnItemClickListener(this);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		m = this;
		if(playIntent==null){
			playIntent = new Intent(this, PlayerService.class);
			bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
			startService(playIntent);
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		stopService(playIntent);
		musicSrv=null;
		m = null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void openFragment(Fragment f){
		getFragmentManager().beginTransaction().add(R.id.content_frame, f).commit();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		int idx = arg2;
		if(arg2 == 0 && Ambiance.isPlayerActive()){
			
		}else if(!Ambiance.isPlayerActive()){
			idx++;
		}
		switch(idx){
		case 1:
			openFragment(new FragmentTracks());
			break;
		}
	}
	
	//connect to the service
	private ServiceConnection musicConnection = new ServiceConnection(){
	 
	  @Override
	  public void onServiceConnected(ComponentName name, IBinder service) {
		  PlayerBinder binder = (PlayerBinder)service;
	    //get service
	    musicSrv = binder.getService();
	    //pass list
//	    musicSrv.setList(new PlaylistDatabase());
	    musicBound = true;
	  }
	 
	  @Override
	  public void onServiceDisconnected(ComponentName name) {
	    musicBound = false;
	  }
	};
	
	public PlayerService getPlayerService(){
		return this.musicSrv;
	}
	
	public static Main instance(){
		return m;
	}

}
