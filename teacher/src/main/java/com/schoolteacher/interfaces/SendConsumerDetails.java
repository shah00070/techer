package com.schoolteacher.interfaces;

import com.schoolteacher.pojos.BloodGroup;
import com.schoolteacher.pojos.ConsumerDetailsResponse;
import com.schoolteacher.pojos.FamilyMembersResponse;

import java.util.List;


public interface SendConsumerDetails {

	void sendConsumerDetails(double bmi, double bmr,
							 FamilyMembersResponse familyDetails, List<BloodGroup> bloodGroups,
							 ConsumerDetailsResponse consumer);
}
