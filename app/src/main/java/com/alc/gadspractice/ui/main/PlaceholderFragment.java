package com.alc.gadspractice.ui.main;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alc.gadspractice.ApiUtil;
import com.alc.gadspractice.LeadersClass;
import com.alc.gadspractice.GADSRecycclerAdapter;
import com.alc.gadspractice.R;

import java.io.IOException;
import java.net.URL;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    LeadersClass[] leadersClasses = new LeadersClass[20];

    LeadersClass[] leaders = new LeadersClass[20];

    int alpha;

    String title;

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
        alpha = index;
        if (alpha == 1)
            title = "hours";
        else
            title = "skilliq";
        URL buildurl = ApiUtil.BuildUrl(title);
        Log.d("message",buildurl.toString());
        try {
            new GadsQueryTask().execute(buildurl);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_leaderboard, container, false);

        final RecyclerView recyclerView = (RecyclerView)root.findViewById(R.id.list_scores);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        resetREcyclerview(root,recyclerView);

        return root;
    }

    public void resetREcyclerview(View view, RecyclerView recyclerView){
        int myindex = alpha;
        if (myindex == 1) {
            final GADSRecycclerAdapter gadsRecycclerAdapter = new GADSRecycclerAdapter(view.getContext(), leadersClasses);
            recyclerView.setAdapter(gadsRecycclerAdapter);
        }else {
            final GADSRecycclerAdapter gadsRecycclerAdapter = new GADSRecycclerAdapter(view.getContext(),leaders);
            recyclerView.setAdapter(gadsRecycclerAdapter);
        }
    }

    public class GadsQueryTask extends AsyncTask<URL,Void,String>{

        @Override
        protected String doInBackground(URL... urls) {
            URL SearchUrl = urls[0];
            String result = null;
            try{
                result = ApiUtil.getJson(SearchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("mymessage", result);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("mymessage", s);
            if (title == "skilliq")
                leadersClasses = ApiUtil.getleadersfromjson(s);
            else
                leaders = ApiUtil.getlogesthoursfromjson(s);
        }
    }
}