package org.itri.view.humanhealth.feelQuest.dao;

import java.util.LinkedList;
import java.util.List;

public class QuestInfo {
	private long questionId;
	private String questLabel;
	private LinkedList<Item> itemList = new LinkedList<Item>();

	public List<Item> getItemList() {
		return itemList;
	}

	public String getQuestLabel() {
		return questLabel;
	}

	public void setQuestLabel(String questLabel) {
		this.questLabel = questLabel;
	}

	public void setItemList(LinkedList<Item> itemList) {
		this.itemList = itemList;
	}

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}
}
