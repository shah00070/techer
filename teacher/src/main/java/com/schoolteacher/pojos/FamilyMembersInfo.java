package com.schoolteacher.pojos;

import java.io.Serializable;
import java.util.List;

public class FamilyMembersInfo implements Serializable {
	private List<MemberAssociation> memberAssociation;

	/**
	 * @return the memberAssociation
	 */
	public List<MemberAssociation> getMemberAssociation() {
		return memberAssociation;
	}

	/**
	 * @param memberAssociation
	 *            the memberAssociation to set
	 */
	public void setMemberAssociation(List<MemberAssociation> memberAssociation) {
		this.memberAssociation = memberAssociation;
	}
}
