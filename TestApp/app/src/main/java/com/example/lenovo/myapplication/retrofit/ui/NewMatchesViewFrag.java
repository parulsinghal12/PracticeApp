package com.example.lenovo.myapplication.retrofit.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.databinding.FragmentNewMatchesBinding;
import com.example.lenovo.myapplication.retrofit.model.MatchData;
import com.example.lenovo.myapplication.retrofit.presenter.CricketNewMatchesPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewMatchesViewFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewMatchesViewFrag extends Fragment implements CricketNewMatchesContract.CricketNewMatchesView {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FragmentNewMatchesBinding binding;
    CricketNewMatchesPresenter presenter;
    List<MatchData> matches_list;
    NewMatchesAdapter myRecyclerViewAdapter;

    public NewMatchesViewFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewMatchesFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static NewMatchesViewFrag newInstance(String param1, String param2) {
        NewMatchesViewFrag fragment = new NewMatchesViewFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_new_matches, container, false);
        View view = binding.getRoot();
        matches_list = new ArrayList<>();
        presenter = new CricketNewMatchesPresenter(this);
        presenter.getNewMatchesData(); // getting data from server
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myRecyclerViewAdapter = new NewMatchesAdapter(matches_list, getContext());
        binding.setMyAdapter(myRecyclerViewAdapter);


    }

    @Override
    public void onGetNewMatchesSuccess(List<MatchData> matches) {
        matches_list.addAll(matches);
        myRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetNewMatchesFailure(String localizedMessage) {
        Toast.makeText(getContext(),localizedMessage,Toast.LENGTH_SHORT).show();
    }
}