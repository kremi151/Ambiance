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

package lu.kremi151.ambiance.fragment;

import lu.kremi151.ambiance.Ambiance;
import lu.kremi151.ambiance.Main;
import lu.kremi151.ambiance.PlayerService;
import lu.kremi151.ambiance.R;
import lu.kremi151.ambiance.playlist.PlaylistDatabase;
import lu.kremi151.ambiance.util.Song;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentTracks extends Fragment implements OnItemClickListener{
	
	ListView lv;

	@Override
	public View onCreateView(LayoutInflater li, ViewGroup vg, Bundle b){
		lv = new ListView(li.getContext());
		lv.setAdapter(new TrackAdapter());
		lv.setOnItemClickListener(this);
		return lv;
	}
	
	private class TrackAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return Ambiance.getMediaDatabase().getPlayableList().length;
		}

		@Override
		public Object getItem(int position) {
			return Ambiance.getMediaDatabase().getPlayableList()[position];
		}

		@Override
		public long getItemId(int position) {
			return 1l;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater li = (LayoutInflater) Ambiance.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View v = li.inflate(R.layout.list_track, null);
			ImageView cover = (ImageView) v.findViewById(R.id.imageView1);
			TextView track = (TextView) v.findViewById(R.id.textView1);
			TextView artist = (TextView) v.findViewById(R.id.textView2);
			Song s = (Song) getItem(position);
			track.setText(s.getTitle());
			artist.setText(s.getArtist());
			return v;
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		PlayerService ps = Main.instance().getPlayerService();
		ps.setSongFromPlaylist(Ambiance.getMediaDatabase().generatePlaylist(), arg2);
		ps.playSong();
	}
}
