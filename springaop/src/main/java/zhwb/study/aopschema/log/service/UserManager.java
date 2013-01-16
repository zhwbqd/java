package zhwb.study.aopschema.log.service;



public interface UserManager {

	public void add(String name, String password);
	public void del(String id);
	public void modify(int id ,String name, String password);
}

