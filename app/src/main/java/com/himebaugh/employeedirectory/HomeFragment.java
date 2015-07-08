package com.himebaugh.employeedirectory;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button teacherBut;
    private Button roomBut;
    private Button secroomBut;
    private Button otherrromBut;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment() {
        // Required empty public constructor
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
        View v = inflater.inflate(R.layout.fragment_home, container,false);

        final GifMovieView gif1 = (GifMovieView) v.findViewById(R.id.gifMovieView);
        gif1.setMovieResource(R.drawable.clickme);

        teacherBut = (Button) v.findViewById(R.id.sBut1);
        teacherBut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // TODO ???????1
                Intent myIntent = new Intent(getActivity(),STeacherActivity.class);
                startActivity(myIntent);
            }

        });

        roomBut = (Button) v.findViewById(R.id.sBut2);
        roomBut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // TODO ???????1
                Intent myIntent = new Intent(getActivity(),SRoomActivity.class);
                startActivity(myIntent);
            }

        });
        secroomBut = (Button) v.findViewById(R.id.sBut3);
        secroomBut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // TODO ???????1
                Intent myIntent = new Intent(getActivity(),SSecroomActivity.class);
                startActivity(myIntent);
            }

        });
        otherrromBut = (Button) v.findViewById(R.id.sBut4);
        otherrromBut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // TODO ???????1
                Intent myIntent = new Intent(getActivity(), SOtherroomActivity.class);
                startActivity(myIntent);
            }

        });
        return v;

    }


}
