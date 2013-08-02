package zhwb.study.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class EasyRestApplication extends Application {

	HashSet<Object> singletons = new HashSet<Object>();

	public EasyRestApplication() {
		singletons.add(new Library());
		singletons.add(new EmpInfo());
	}

	@Override
	public Set<Class<?>> getClasses() {
		HashSet<Class<?>> set = new HashSet<Class<?>>();
		// set.add(Library.class);
		// set.add(Book.class);
		return set;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}