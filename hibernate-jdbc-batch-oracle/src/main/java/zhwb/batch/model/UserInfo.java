package zhwb.batch.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author <a href="liu.anxin13@gmail.com">Tony</a>
 */
@Entity
@Table(name = "T_USERINFO")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
public class UserInfo implements Serializable {

	private static final long serialVersionUID = -4855456169220894250L;

	@Id
	@Column(name = "ID", length = 32)
	private String id = UUID.randomUUID().toString().replaceAll("-", "");

	@Column(name = "CREATE_TIME", updatable = false)
	private Timestamp createTime = new Timestamp(System.currentTimeMillis());

	@Column(name = "UPDATE_TIME", insertable = false)
	private Timestamp updateTime = new Timestamp(System.currentTimeMillis());

	public String getId(){	return id;}
	public void setId(String id){this.id = id;}

	public Timestamp getCreateTime(){return createTime;}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime(){return updateTime;}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}
