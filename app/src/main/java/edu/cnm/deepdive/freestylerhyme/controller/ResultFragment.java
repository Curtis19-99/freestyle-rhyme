package edu.cnm.deepdive.freestylerhyme.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import edu.cnm.deepdive.freestylerhyme.R;
//import edu.cnm.deepdive.freestylerhyme.view.ResultAdapter;
import viewmodel.MainViewModel;

public class ResultFragment extends Fragment {

  private MainViewModel mainViewModel;
  private RecyclerView resultList;

//  @Override
//  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//    super.onViewCreated(view, savedInstanceState);
//    mainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
//    mainViewModel.getResults().observe(getViewLifecycleOwner(),
//        (results) -> resultList.setAdapter(new ResultAdapter(getContext(), results, this)));
//  }
//
//  public View onCreateView(@NonNull LayoutInflater inflater,
//      ViewGroup container, Bundle savedInstanceState) {
//    View root = inflater.inflate(R.layout.fragment_result, container, false);
//    resultList = root.findViewById(R.id.result_list);
//    addResult =
//  }
}


