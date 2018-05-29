package edu.hust.truongvu.choviet.admin.member;

import android.content.Context;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.UserModel;
import edu.hust.truongvu.choviet.model.entity.User;

/**
 * Created by truon on 5/29/2018.
 */

public class MemberPresenterImp implements MemberPresenter{
    private Context mContext;
    private MemberView memberView;
    private UserModel userModel;

    public MemberPresenterImp(Context context, MemberView memberView){
        this.mContext = context;
        this.memberView = memberView;
        userModel = new UserModel(mContext);
    }
    @Override
    public void getListMember() {
        ArrayList<User> data = userModel.getAllMember();
        memberView.loadTotalMember(data.size());
        if (data.isEmpty()){
            memberView.loadListMemberFalse();
        }else {
            memberView.loadListMemberSucessful(data);
        }
    }
}
