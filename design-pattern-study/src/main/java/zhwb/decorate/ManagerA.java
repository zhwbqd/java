package zhwb.decorate;

public class ManagerA extends Manager {

	public ManagerA(Project project) {
		super(project);
	}

    /**
	 * 项目经理自己的事情：做早期工作
	 */
	public void doEarlyWork() {
		System.out.println("项目经理A 在做需求分析");
		System.out.println("项目经理A 在做架构设计");
		System.out.println("项目经理A 在做详细设计");
	}

	@Override
	void doEndWork() {
		System.out.println("项目经理A 在做收尾工作");
	}
}
