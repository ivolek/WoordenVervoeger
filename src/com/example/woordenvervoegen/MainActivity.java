package com.example.woordenvervoegen;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity  {

	public ArrayList<String> groups= new ArrayList<String>();
	public ArrayList<ArrayList<ArrayList<String>>> childs= new ArrayList<ArrayList<ArrayList<String>>>();
	public int lastExpandedGroupPosition;
	public ExpandableListView l;
	public final static String EXTRA_MESSAGE = "WERKT NIET";
	public final static String MODULE_NAAM = "STANDAARD NAAM";
	public String result; //Wat er in de edittext wordt getypt.

	DataRetriever dr = new DataRetriever();
	DataExtracter de = new DataExtracter();

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void zoekButtonPressed( View sender )
	{		
		result = ((EditText) findViewById( R.id.WoordenEditText )).getText().toString();
		try
		{
			String str = dr.DownloadText("http://www.mijnwoordenboek.nl/ww.php?woord=" +result);
			de.extractData(str);
			ExpandableList(); //Start de expandablelist.
			EditText et = (EditText)findViewById(R.id.WoordenEditText);
			
			InputMethodManager imm = (InputMethodManager)getSystemService(
				      Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
		}
		catch(IndexOutOfBoundsException e)
		{
			if(isNetworkAvailable() == false)
			{
				Toast tempMessage = Toast.makeText(this, "Er is geen internet verbinding.", Toast.LENGTH_SHORT);
				tempMessage.setGravity(Gravity.CENTER, 0, 0);
				tempMessage.show();
			}
			else{
			Toast tempMessage = Toast.makeText(this, "Voer een volledig werkwoord in.", Toast.LENGTH_SHORT);
			tempMessage.setGravity(Gravity.CENTER, 0, 0);
			tempMessage.show();
			}
		}
	}

	private boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager 
		= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null;
	}


	public void ExpandableList() 
	{

		l = (ExpandableListView) findViewById(R.id.expandableListView1);
		groups.clear();
		childs.clear();
		loadData();


		myExpandableAdapter adapter = new myExpandableAdapter(this, groups, childs);
		l.setAdapter(adapter);

	}





	public class myExpandableAdapter extends BaseExpandableListAdapter {

		private ArrayList<String> groups;

		private ArrayList<ArrayList<ArrayList<String>>> children;

		private Context context;

		public myExpandableAdapter(Context context, ArrayList<String> groups, ArrayList<ArrayList<ArrayList<String>>> children) {
			this.context = context;
			this.groups = groups;
			this.children = childs;
		}

		public void onGroupExpanded(int groupPosition){
			//collapse the old expanded group, if not the same
			//as new group to expand
			if(groupPosition != lastExpandedGroupPosition){
				l.collapseGroup(lastExpandedGroupPosition);
			}

			super.onGroupExpanded(groupPosition);           
			lastExpandedGroupPosition = groupPosition;
		}



		public boolean areAllItemsEnabled()
		{
			return true;
		}



		public ArrayList<String> getChild(int groupPosition, int childPosition) {
			return children.get(groupPosition).get(childPosition);
		}


		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}



		public View getChildView(int groupPosition, int childPosition, boolean isLastChild,View convertView, ViewGroup parent) {

			String child = (String) ((ArrayList<String>)getChild(groupPosition, childPosition)).get(0);

			if (convertView == null) {
				LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = infalInflater.inflate(R.layout.expandablelistview_child, null);
			}

			TextView childtxt = (TextView) convertView.findViewById(R.id.TextViewChild01);

			childtxt.setText(child);

			return convertView;
		}


		public int getChildrenCount(int groupPosition) {
			return children.get(groupPosition).size();
		}


		public String getGroup(int groupPosition) {
			return groups.get(groupPosition);
		}


		public int getGroupCount() {
			return groups.size();
		}


		public long getGroupId(int groupPosition) {
			return groupPosition;
		}


		public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

			String group = (String) getGroup(groupPosition);

			if (convertView == null) {
				LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = infalInflater.inflate(R.layout.expandablelistview_group, null);
			}

			TextView grouptxt = (TextView) convertView.findViewById(R.id.TextViewGroup);

			grouptxt.setText(group);

			return convertView;
		}


		public boolean hasStableIds() {
			return true;
		}


		public boolean isChildSelectable(int arg0, int arg1) {
			return true;
		}

	}

	private void loadData()
	{
		groups.add("Onvoltooid tegenwoordige tijd"); //0
		groups.add("Voltooid tegenwoordige tijd"); //1
		groups.add("Onvoltooid verleden tijd"); //2
		groups.add("Voltooid verleden tijd"); //3
		groups.add("Onvoltooid tegenwoordige toekomende tijd"); //4
		groups.add("Voltooid tegenwoordige toekomende tijd"); //5
		groups.add("Onvoltooid verleden toekomende tijd"); //6       
		groups.add("Voltooid verleden toekomende tijd"); //7

for(int all =0; all < 8; all++)
{
	childs.add(new ArrayList<ArrayList<String>>());
	for( int i = 0 ; i < 6 ; ++i ) 
	{
		childs.get(all).add(new ArrayList<String>());
		if(all == 0)childs.get(all).get(i).add(DataExtracter.ottLijst.get(i));	
		if(all == 1)childs.get(all).get(i).add(DataExtracter.vttLijst.get(i));	
		if(all == 2)childs.get(all).get(i).add(DataExtracter.ovtLijst.get(i));	
		if(all == 3)childs.get(all).get(i).add(DataExtracter.vvtLijst.get(i));	
		if(all == 4)childs.get(all).get(i).add(DataExtracter.otttLijst.get(i));	
		if(all == 5)childs.get(all).get(i).add(DataExtracter.vtttLijst.get(i));	
		if(all == 6)childs.get(all).get(i).add(DataExtracter.ovttLijst.get(i));	
		if(all == 7)childs.get(all).get(i).add(DataExtracter.vvttLijst.get(i));	
		
	}
}
	}


}




