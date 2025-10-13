package com.example.demo.db.model;

public enum RewardType {
	POST_10_TIMES(0, "post10Times", 10),
	POST_20_TIMES(1, "post20Times", 20),
	PERFORMANCE_10_TIMES(2, "performance10Times", 10),
	PERFORMANCE_20_TIMES(3, "performance20Times", 20);
	
	int rewardId;
	String type;
	int count;
	
	RewardType(int rewardId, String type, int count) {
		this.rewardId = rewardId;
		this.type = type;
		this.count = count;
	}
	
	public int getRewardId() {
		return this.rewardId;
	}
	
	public int getCount() {
		return this.count;
	}
	
	public String getType() {
		return this.type;
	}
}
