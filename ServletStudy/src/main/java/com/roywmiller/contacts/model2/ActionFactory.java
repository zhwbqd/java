package com.roywmiller.contacts.model2;

import java.util.HashMap;
import java.util.Map;

import com.roywmiller.contacts.actions.Action;
import com.roywmiller.contacts.actions.AddContactAction;
import com.roywmiller.contacts.actions.BootstrapAction;
import com.roywmiller.contacts.actions.LoginAction;
import com.roywmiller.contacts.actions.LogoutAction;
import com.roywmiller.contacts.actions.RemoveContactAction;


public class ActionFactory {
	protected Map map = defaultMap();
	
	public ActionFactory() {
		super();
	}
	public Action create(String actionName) {
		Class klass = (Class) map.get(actionName);
		if (klass == null)
			throw new RuntimeException(getClass() + " was unable to find an action named '" + actionName + "'.");
		
		Action actionInstance = null;
		try {
			actionInstance = (Action) klass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return actionInstance;
	}
	protected Map defaultMap() {
		Map map = new HashMap();

		map.put("index", BootstrapAction.class);
		map.put("addContactAction", AddContactAction.class);
		map.put("removeContactAction", RemoveContactAction.class);
		map.put("loginAction", LoginAction.class);
		map.put("logoutAction", LogoutAction.class);

		return map;
	}
}