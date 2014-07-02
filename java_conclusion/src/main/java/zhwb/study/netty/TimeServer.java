package zhwb.study.netty;

/**
 * Date: 14-6-30
 * Time: 下午9:40
 *
 * @author jack.zhang
 */
public class TimeServer {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        MutiplexerTimeServer server = new MutiplexerTimeServer(port);
        new Thread(server,"NIO-Server-001").start();
    }
}
