package edu.hust.truongvu.choviet.admin.member;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.entity.User;

/**
 * Created by truon on 5/29/2018.
 */

public interface MemberView {
    void loadTotalMember(int number);
    void loadListMemberSucessful(ArrayList<User> data);
    void loadListMemberFalse();
}
