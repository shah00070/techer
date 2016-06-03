package com.schoolteacher.pojos;

import java.util.List;

/**
 * Created by chandan on 29/1/16.
 */
public class MemberDetailsList {

    private List<FamilyMemberData> memberAssociation;

    public List<FamilyMemberData> getMemberAssociation() {
        return memberAssociation;
    }

    public void setMemberAssociation(List<FamilyMemberData> memberAssociation) {
        this.memberAssociation = memberAssociation;
    }
}
