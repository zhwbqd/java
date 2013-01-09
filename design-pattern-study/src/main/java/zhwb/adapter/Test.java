
package zhwb.adapter;

public class Test
{

    /**
     * Description goes here.
     *
     * @param args
     */
    public static void main(String[] args)
    {
        ISpecialOrderService order = new CombinationOrderService();
        order.countSell();
        order.countSpecialSell();
    }

}
