package zhwb.study.memcache.serialize;

/****
 * 序列化接口
 * @author ZhichengHan
 *
 */
public interface Serialization {
	
	/***
	 * 序列化协议.
	 * 
	 * @author ZhichengHan
	 *
	 */
	public String getProtocol();
	
	/****
	 * 序列化
	 * @param t
	 * @return
	 */
	public <T> Object serialize(T t);
	
	/****
	 * 反序列化
	 * @param clazz
	 * @param bytes
	 * @return
	 */
	public <T> T deserialize(Class<T> clazz, Object obj);
}
