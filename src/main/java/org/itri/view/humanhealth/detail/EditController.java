package org.itri.view.humanhealth.detail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.itri.view.humanhealth.dao.SelectboxModel;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Selectbox;

public class EditController extends SelectorComposer<Component> {

//	private SelectboxModel defaultSelected;
//	private List<SelectboxModel> typesModel = new ArrayList<SelectboxModel>();

	private List<String> datas = Arrays.asList(new String[] { "3����", "5����", "1�p��", "3�p��", "12�p��", "24�p��" });
	private ListModel<String> typesModel = new ListModelList<String>(datas);

	@Wire
	private Selectbox typesSelectbox;

	public EditController() {

	}

//	public ListModel<String> getTypesModel() {
//
//	}

	@Listen("onSelect = #typesSelectbox")
	public void changeType() {

	}

}
