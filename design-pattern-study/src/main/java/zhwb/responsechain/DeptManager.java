package zhwb.responsechain;
public class DeptManager extends Handler {

    @Override
    public String handleFeeRequest(String user, double fee) {
        
        String str = "";
        //部门经理的权限只能在1000以内
        if(fee < 1000)
        {
            //为了测试，简单点，只同意张三的请求
                str = "成功：部门经理同意【" + user + "】的聚餐费用，金额为" + fee + "元";    
        }else
        {
			System.out.println("转向：部门经理不同意【" + user + "】的聚餐费用，金额为" + fee + "元");
            //超过1000，继续传递给级别更高的人处理
            if(getSuccessor() != null)
            {
                return getSuccessor().handleFeeRequest(user, fee);
            }
        }
        return str;
    }

}