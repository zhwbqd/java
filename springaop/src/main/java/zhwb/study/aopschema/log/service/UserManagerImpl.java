package zhwb.study.aopschema.log.service;



public class UserManagerImpl implements UserManager {

	public void add(final String name, final String password) {
		System.out.println("add method");
	}

	public void del(final String id) {
		System.out.println("del method");
	}

	public void modify(final int id, final String name, final String password) {
		System.out.println("modify method");
	}

}

