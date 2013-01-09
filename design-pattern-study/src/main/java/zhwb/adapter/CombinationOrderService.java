
package zhwb.adapter;

//类适配器 adapter
public class CombinationOrderService extends OrderService implements ISpecialOrderService
{
    public void countSpecialSell()
    {
        System.out.println("This is a special Sell");
    }
}
