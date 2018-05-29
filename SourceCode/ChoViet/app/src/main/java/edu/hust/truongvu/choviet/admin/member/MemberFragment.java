package edu.hust.truongvu.choviet.admin.member;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.Constants;
import edu.hust.truongvu.choviet.model.entity.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemberFragment extends Fragment implements MemberView{

    private TextView tvTotalUser;
    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private MemberPresenter memberPresenter;

    public MemberFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_member, container, false);
        initView(view);
        memberPresenter = new MemberPresenterImp(getContext(), this);
        memberPresenter.getListMember();
        return view;
    }

    private void initView(View view){
        tvTotalUser = view.findViewById(R.id.tv_total_user);
        recyclerView = view.findViewById(R.id.list_user);
    }

    @Override
    public void loadTotalMember(int number) {
        tvTotalUser.setText(getContext().getString(R.string.total_user) + ": " + number);
    }

    @Override
    public void loadListMemberSucessful(ArrayList<User> data) {
        adapter = new UserAdapter(getContext(), data, new UserAdapter.UserListener() {
            @Override
            public void onClick(User user) {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void loadListMemberFalse() {

    }
}
