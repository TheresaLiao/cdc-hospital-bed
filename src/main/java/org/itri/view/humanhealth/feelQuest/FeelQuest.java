package org.itri.view.humanhealth.feelQuest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.itri.view.humanhealth.feelQuest.Imp.FeelQuestDaoHibernateImpl;
import org.itri.view.humanhealth.feelQuest.dao.Item;
import org.itri.view.humanhealth.feelQuest.dao.QuestInfo;
import org.itri.view.humanhealth.hibernate.SurveyQuestion;
import org.itri.view.humanhealth.hibernate.SurveyQuestionChoice;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.event.Event;

public class FeelQuest {

	private FeelQuestDaoHibernateImpl hqe;
	private List<QuestInfo> questList;

	private String seleted_1;
	private String seleted_2;
	private String seleted_3;

	@Init
	public void init() {

		hqe = new FeelQuestDaoHibernateImpl();
		queryStates();
	}

	@Command
	public void radioClick(Event e) {
		System.out.println(seleted_1);
	}

	@Command
	public void buttonClick() {

	}

	private void queryStates() {

		// Get patientId Today selected
//		long patientId = 1;
//		List<SurveyResult> temp = hqe.getSurveyResultList(patientId);

		// Get quest from db
		questList = new ArrayList<QuestInfo>();
		List<SurveyQuestion> surveyQuestList = hqe.getSurveyQuestionList();

		// Set Each quest
		for (SurveyQuestion quest : surveyQuestList) {
			QuestInfo questInfo = new QuestInfo();
			questInfo.setQuestionId(quest.getSurveyQuestionId());
			questInfo.setQuestLabel(quest.getSurveyQuestionText());

			// Each quest multiple choice
			LinkedList<Item> itemList = new LinkedList<Item>();
			for (SurveyQuestionChoice option : quest.getSurveyQuestionChoices()) {
				Item item = new Item();
				item.setId((int) option.getSurveyQuestionChoiceId());
				item.setName(option.getSurveyQuestionChoiceText());
				itemList.add(item);
			}
			questInfo.setItemList(itemList);
			questList.add(questInfo);
		}
	}

	public List<QuestInfo> getQuestList() {
		return questList;
	}

	public String getSeleted_1() {
		return seleted_1;
	}

	public void setSeleted_1(String seleted_1) {
		this.seleted_1 = seleted_1;
	}

	public String getSeleted_2() {
		return seleted_2;
	}

	public void setSeleted_2(String seleted_2) {
		this.seleted_2 = seleted_2;
	}

	public String getSeleted_3() {
		return seleted_3;
	}

	public void setSeleted_3(String seleted_3) {
		this.seleted_3 = seleted_3;
	}
}
