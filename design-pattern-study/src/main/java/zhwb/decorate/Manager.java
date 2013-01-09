package zhwb.decorate;

//decorate
abstract class Manager implements Project {
    private Project project;        //实际上存放的是代码工人对象 

    public Manager(Project project) { 
        this.project = project; 
    } 

    /** 
     * 编码 
     */ 
    public void doCoding() { 
        //项目经理开始新的工作 
        startNewWork(); 
    } 

    /** 
     * 模板：定义项目经理自己的事情 
     */ 
    public void startNewWork() { 
        //项目经理在做早期工作 
        doEarlyWork(); 
        //项目经理很牛，做完需求和设计后，直接将编码委派给代码工人干 
        project.doCoding(); 
        //项目经理在做收尾工作 
        doEndWork(); 
    } 

    /** 
     * 项目经理自己的事情：做早期工作 
     */ 
	abstract void doEarlyWork();

    /** 
     * 项目经理做收尾工作 
     */ 
	abstract void doEndWork();
}
